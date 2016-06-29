package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import parser.UMLParser;

@SuppressWarnings("deprecation")
public class Generate {
	private static Configuration cfg = new Configuration();
	private static Template enumTemplate;

	public static void main(String[] args) {
		File f = new File("model.uml");
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
		String path = "generated/";
		File file = new File(path + "src");
		if (!file.exists()) {
			file.mkdirs();
		}
		generateDaoBean(classes, model, path + "src");

		for (FMEnum enumElement : en) {
			generateEnum(enumElement, path + "src");
		}

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
				} else {
					System.out.println("Ne postoji ID!");
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
		
		File f = new File(path + "/enum");
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
}
