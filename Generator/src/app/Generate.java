package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import freemarker.cache.ClassTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import model.FMClass;
import model.FMEnum;
import model.FMModel;
import model.FMProperty;
import model.MainForm;
import model.StandardForm;
import parser.UMLParser;

@SuppressWarnings("deprecation")
public class Generate {
	private static Configuration cfg = new Configuration();
	private static Template enumTemplate;
	private static Template abstractFormTemplate;
	private static Template abstractPanelDetailTemplate;
	private static Template dialogStatusBarTemplate;
	private static Template dialogToolbarTemplate;

	public static void generate(String modelPath, String destPath) throws Exception {
		File f = new File(modelPath);
		UMLParser umlParser = null;
		try {
			umlParser = new UMLParser(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FMModel model = umlParser.parse();
		List<FMClass> classes = model.getClasses();
		List<FMEnum> en = model.getEnums();
		cfg.setTemplateLoader(new ClassTemplateLoader(Generate.class, "/templates"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		String path = destPath + "/src";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		generateDaoBean(classes, model, path);
		generateJavaBean(classes, path);
		generateAction(classes, path);
		generatePersistence(classes, model.getName(), path);
		generateAbstractForm(model, path);
		generateAbstractPanelDetail(path);
		generateDialogStatusBar(path);
		generateDialogToolbar(path);
		generateMainForm(classes, path);
		
		for (FMEnum enumElement : en) {
			generateEnum(enumElement, path);
		}

		file = new File(path + "/gui");
		if (!file.exists()) {
			file.mkdirs();
		}
		
		generateForms(classes, path+ "/gui");
		
		JOptionPane.showMessageDialog(null, "Generisanje uspesno zavrseno.");
	}

	public static void generateJavaBean(List<FMClass> classes, String path) throws Exception {
		File f = new File(path + "/bean");
		if (!f.exists()) {
			f.mkdir();
		}

		for (FMClass fmClass : classes) {
			Map<String, Object> data = new HashMap<String, Object>();
			
			if(fmClass.getUiClass() != null && fmClass.getUiClass() instanceof MainForm) {
				continue;
			}
			
			List<FMProperty> toString = new ArrayList<>();
			for(FMProperty p : fmClass.getProperties()){
				if(p.getUiProperty() != null && p.getUiProperty().getToString()){
					toString.add(p);
				}
			}
			
			if(fmClass.getParent() != null) {
				for(FMProperty p : fmClass.getParent().getProperties()){
					if(p.getUiProperty() != null && p.getUiProperty().getToString()){
						toString.add(p);
					}
				}
			}
			
			data.put("class", fmClass);
			data.put("toString",toString);
			data.put("str", !toString.isEmpty());

			Template temp = cfg.getTemplate("javaBean.ftl");
			FileWriter out = new FileWriter(path + "/bean/" + fmClass.getName() + ".java");
			temp.process(data, out);
			out.flush();
		}
		System.out.println("Java Bean klase su uspesno generisane");

	}

	public static void generateAction(List<FMClass> classes, String path) throws Exception {
		File f = new File(path + "/action");
		if (!f.exists()) {
			f.mkdir();
		}
		for (FMClass fmClass : classes) {
			Map<String, Object> data = new HashMap<String, Object>();
			if (fmClass.getUiClass() != null && !(fmClass.getUiClass() instanceof MainForm)) {
				data.put("class", fmClass);
				List<FMProperty> reference = new ArrayList<>();
				for(FMProperty p : fmClass.getProperties()){
					if(p.getUiProperty() == null && p.getUpper()==1){
						reference.add(p);
					}
				}
				data.put("reference", reference);
				data.put("refer", !reference.isEmpty());
				//System.out.println(++i + "." + fmClass.getUiClass());
				Template temp = cfg.getTemplate("action.ftl");
				FileWriter out = new FileWriter(
						path + "/action/" + "Open" + fmClass.getName() + "FormAction.java");
				temp.process(data, out);
				out.flush();
			}
		}
		System.out.println("Akcije za pokretanje standardnih formi su uspesno generisane");

	}

	public static void generateDaoBean(List<FMClass> classes, FMModel model, String path) {
		File f = new File(path + "/dao");
		if (!f.exists()) {
			f.mkdir();
		}
		for (FMClass c : classes) {
			if (!(c.getUiClass() instanceof MainForm)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("class", c);
				map.put("properties", c.getProperties());
				boolean ok = false;
				for (FMProperty p : c.getProperties()) {
					if (p.getDbProperty() != null && p.getDbProperty().isId()) {
						ok = true;
						map.put("type", p.getType());
						break;
					}
				}
				if (!ok && c.getParent() != null) {
					for (FMProperty p : c.getParent().getProperties()) {
						if (p.getDbProperty() != null && p.getDbProperty().isId()) {
							ok = true;
							map.put("type", p.getType());
							break;
						}
					}
				}

				if (ok) {
					try {
						Template temp = cfg.getTemplate("daoBean.ftl");

						FileWriter out = new FileWriter(path + "/dao/" + c.getName() + "DaoBean.java");
						temp.process(map, out);
						out.flush();

					} catch (IOException e) {
						// TODO cfg.getTemplate može izazvati ovaj izuzetak
						// ukoliko šablon nije pronađen.
						e.printStackTrace();
					} catch (TemplateException e) {
						// TODO template.process može da izazove ovaj izuzetak.
						// Npr. zbog sintaksne greške u šablonu.
						e.printStackTrace();
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("persistenceName", model.getName());
		try {
			Template temp = cfg.getTemplate("genericDaoBean.ftl");

			FileWriter out = new FileWriter(path + "/dao/GenericDaoBean.java");
			temp.process(map, out);
			out.flush();

		} catch (IOException e) {
			// TODO cfg.getTemplate može izazvati ovaj izuzetak ukoliko šablon
			// nije pronađen.
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO template.process može da izazove ovaj izuzetak. Npr. zbog
			// sintaksne greške u šablonu.
			e.printStackTrace();
		}
		System.out.println("DaoBean klase uspesno izgenerisane.");
	}

	public static void generateEnum(FMEnum enumElement, String path) {

		File f = new File(path + "/enumeration");
		if (!f.exists()) {
			f.mkdirs();
		}

		String fullPath = f + "/" + enumElement.getName() + ".java";

		try {
			enumTemplate = cfg.getTemplate("enum.ftl");

			Map<String, Object> data = new HashMap<String, Object>();
			data.put("enum", enumElement);

			FileWriter writer = null;

			try {
				writer = new FileWriter(fullPath);
				enumTemplate.process(data, writer);
				writer.close();
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
		} catch (TemplateNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedTemplateNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Klase za enumeraciju uspesno izgenerisane.");
	}

	public static void generatePersistence(List<FMClass> classes, String persistenceName, String path) {
		File f = new File(path + "/META-INF");
		if (!f.exists()) {
			f.mkdirs();
		}

		List<FMClass> listaKlasa = new ArrayList<>();
		for (FMClass c : classes) {
			if (c.getUiClass() != null && c.getUiClass() instanceof MainForm) {
				continue;
			}
			listaKlasa.add(c);
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("classes", listaKlasa);
		data.put("persistenceName", persistenceName);

		try {

			Template temp = cfg.getTemplate("persistence.ftl");
			FileWriter out;
			out = new FileWriter(path + "/META-INF/persistence.xml");
			temp.process(data, out);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("persistence.xml je uspesno generisan");
	}

	public static void generateAbstractForm(FMModel model, String path) {
		File f = new File(path + "/gui/forms");
		if (!f.exists()) {
			f.mkdirs();
		}

		String fullPath = f + "/" + "AbstractForm.java";

		try {
			abstractFormTemplate = cfg.getTemplate("abstractForm.ftl");

			Map<String, Object> data = new HashMap<String, Object>();

			FileWriter writer = null;

			try {
				writer = new FileWriter(fullPath);
				abstractFormTemplate.process(data, writer);
				writer.close();
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void generateAbstractPanelDetail(String path){
		File f = new File(path + "/gui/forms/detailpanels");
		if (!f.exists()) {
			f.mkdirs();
		}
		
		String fullPath = f + "/" + "AbstractPanelDetail.java";

		try {
			abstractPanelDetailTemplate = cfg.getTemplate("abstractPanelDetail.ftl");

			Map<String, Object> data = new HashMap<String, Object>();

			FileWriter writer = null;

			try {
				writer = new FileWriter(fullPath);
				abstractPanelDetailTemplate.process(data, writer);
				writer.close();
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void generateDialogStatusBar(String path){
		File f = new File(path + "/gui/forms");
		if (!f.exists()) {
			f.mkdirs();
		}
		
		String fullPath = f + "/" + "DialogStatusBar.java";

		try {
			dialogStatusBarTemplate = cfg.getTemplate("dialogStatusBar.ftl");

			Map<String, Object> data = new HashMap<String, Object>();

			FileWriter writer = null;

			try {
				writer = new FileWriter(fullPath);
				dialogStatusBarTemplate.process(data, writer);
				writer.close();
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void generateDialogToolbar(String path){
		File f = new File(path + "/gui/forms");
		if (!f.exists()) {
			f.mkdirs();
		}
		
		String fullPath = f + "/" + "DialogToolbar.java";

		try {
			dialogToolbarTemplate = cfg.getTemplate("dialogToolbar.ftl");

			Map<String, Object> data = new HashMap<String, Object>();

			FileWriter writer = null;

			try {
				writer = new FileWriter(fullPath);
				dialogToolbarTemplate.process(data, writer);
				writer.close();
			} catch (IOException | TemplateException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void generateForms(List<FMClass> classes, String path){
		File f = new File(path + "/forms/detailpanels");
		if (!f.exists()) {
			f.mkdirs();
		}
		for(FMClass c : classes){
			Map<String, Object> map = new HashMap<String, Object>();
			if(c.getUiClass() == null){
				continue;
			}else if(!(c.getUiClass() instanceof StandardForm)){
				continue;
			}
			map.put("class", c);
			FMProperty ID = c.getID();
			List<FMProperty> properties = new ArrayList<>();
			properties.add(ID);
			List<FMProperty> zoomProperties = new ArrayList<>();
			List<FMProperty> reference = new ArrayList<>();
			for(FMProperty p : c.getProperties()){
				if(p.getName().equals(ID.getName())){
					continue;
				}
				if(p.getUiProperty() == null && p.getUpper()!=1){
					if(((FMClass)p.getReference()).getUiClass()!=null)
					zoomProperties.add(p);
					continue;
				}else if(p.getUiProperty() == null && p.getUpper()==1){
					reference.add(p);
				}
				properties.add(p);
			}
			
			if(c.getParent() != null) {
				for(FMProperty p : c.getParent().getProperties()){
					if(p.getName().equals(ID.getName())){
						continue;
					}
					if(p.getUiProperty() == null && p.getUpper()!=1){
						if(((FMClass)p.getReference()).getUiClass()!=null)
						zoomProperties.add(p);
						continue;
					}else if(p.getUiProperty() == null && p.getUpper()==1){
						reference.add(p);
					}
					properties.add(p);
				}
			}
			
			map.put("id", ID);
			map.put("properties", properties);
			map.put("zoom",!zoomProperties.isEmpty());
			map.put("zoomProperties", zoomProperties);
			map.put("reference", reference);
			List<FMProperty> colons = new ArrayList<>();
			for(FMProperty fm : properties){
				if((fm.getUiProperty()!= null && fm.getUiProperty().getDisplay()) || (fm.getUiProperty()== null && fm.getUpper()==1)){
					colons.add(fm);
				}
			}
			map.put("columns", colons);
			try {
				Template temp = cfg.getTemplate("standardForm.ftl");

				FileWriter out = new FileWriter(path + "/forms/" + c.getName() + "Form.java");
				temp.process(map, out);
				out.flush();
				
				temp = cfg.getTemplate("panelDetail.ftl");
				out = new FileWriter(path + "/forms/detailpanels/PanelDetail" + c.getName() + ".java");
				temp.process(map, out);
				out.flush();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Forme su uspesno izgenerisane.");
	}
	
	public static void generateMainForm(List<FMClass> classes, String path) {
		File f = new File(path + "/gui");
		if (!f.exists()) {
			f.mkdirs();
		}
		
		List<FMClass> listaKlasa = new ArrayList<>();
		FMClass main = null;
		for(FMClass c : classes) {
			if(c.getUiClass() != null && c.getUiClass() instanceof StandardForm) {
				listaKlasa.add(c);
			}
			if(c.getUiClass() != null && c.getUiClass() instanceof MainForm) {
				main = c;
			}
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("classes", listaKlasa);
		data.put("main", main);
		
		try {
			Template temp = cfg.getTemplate("MainForm.ftl");
			FileWriter out;
			out = new FileWriter(path + "/gui/MainForm.java");
			temp.process(data, out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
		System.out.println("MainForm klasa je uspesno generisana.");
	}
}
