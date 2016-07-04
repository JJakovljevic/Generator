package parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import model.ComponentKind;
import model.DBProperty;
import model.Editable;
import model.FMClass;
import model.FMEnum;
import model.FMEnumLiteral;
import model.FMModel;
import model.FMNamedElement;
import model.FMProperty;
import model.FMType;
import model.Lookup;
import model.MainForm;
import model.ReadOnly;
import model.StandardForm;
import model.UIClass;
import model.UIProperty;

public class UMLParser extends Observable {

    private InputStream stream;
    private XMLStreamReader reader = null;

    public UMLParser(InputStream stream) {
        this.stream = stream;
    }

    public UMLParser(File file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }

    public int getCurrentPosition() {
        return reader.getLocation().getCharacterOffset();
    }

    public FMModel parse(){
        FMModel model = new FMModel();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        String xmiNamespaceURI = null;
        FMClass currentClass = null;
        FMProperty currentProperty = null;
        FMEnum currentEnum = null;
        Stack<String> packages = new Stack<String>();
        Map<String, FMNamedElement> stereotypes = new HashMap<String, FMNamedElement>();

        try {
            reader = factory.createXMLStreamReader(stream);

            while (reader.hasNext()) {
                switch (reader.next()) {
                case XMLEvent.START_ELEMENT:
                    String id = reader.getAttributeValue(xmiNamespaceURI, "id");
                    String type = reader.getAttributeValue(xmiNamespaceURI, "type");
                    String name = reader.getAttributeValue(null, "name");
                    String visibility = reader.getAttributeValue(null, "visibility");
                    if (visibility == null) visibility = "public";

                    if (reader.getLocalName().equals("XMI")) {
                        xmiNamespaceURI = reader.getNamespaceURI();
                    } else if (reader.getLocalName().equals("packagedElement") && type.equals("uml:Package")) {
                        packages.push(name);
                    } else if (reader.getLocalName().equals("packagedElement") && type.equals("uml:Class")) {
                        currentClass = new FMClass(name, String.join(".", packages), visibility,null);
                        currentClass.setAbstract(Boolean.parseBoolean(reader.getAttributeValue(null, "isAbstract")));
                        model.addElement(id,currentClass);
                    } else if (reader.getLocalName().equals("packagedElement") && type.equals("uml:Enumeration")) {
                        currentEnum = new FMEnum(name, String.join(".", packages));
                        model.addElement(id,currentEnum);
                    } else if (reader.getLocalName().equals("ownedAttribute") && type.equals("uml:Property")) {
                        String localType = reader.getAttributeValue("", "type");
                        if (localType == null) localType = "Object";
                        if (localType.equals("Real")) localType = "Double";
                        currentProperty = new FMProperty(name, localType,visibility,1,1);
                        model.addElement(id,currentProperty);
                        currentClass.getProperties().add(currentProperty);
                    }else if (reader.getLocalName().equals("ownedLiteral")) {
                        FMEnumLiteral literal = new FMEnumLiteral(id, name);
                        model.addElement(id,literal);
                        currentEnum.getLiterals().add(literal);
                    } else if (reader.getLocalName().equals("type") && type.equals("uml:PrimitiveType")) {
                        String href = reader.getAttributeValue(null, "href");
                        currentProperty.setType(href.substring(href.indexOf('#') + 1));
                    }else if (reader.getLocalName().equals("lowerValue") && currentProperty != null) {
                        String value = reader.getAttributeValue(null, "value");
                        if (value != null && value.equals("1")){
                        	currentProperty.setLower(1);
                        }
                        else{
                        	currentProperty.setLower(0);
                        }
                    } else if (reader.getLocalName().equals("upperValue") && currentProperty != null) {
                        String value = reader.getAttributeValue(null, "value");
                        if (value != null && value.equals("*")) {
                        	currentProperty.setUpper(100);
                        }
                        else {
                        	currentProperty.setUpper(1);
                        }
                    }else if(reader.getLocalName().equals("generalization") && currentClass !=null){
                    	FMClass parent = (FMClass)model
                                .getElementById(reader.getAttributeValue(null, "general"));
                    	currentClass.setParent(parent);
                    }  else if (reader.getLocalName().equals("Model")) {
                        model.setName(name);
                    } else if (reader.getLocalName().equals("UIClass")) {
                        FMClass refClass = (FMClass)model
                                .getElementById(reader.getAttributeValue(null, "base_Element"));
                        if (refClass != null) {
                            stereotypes.put(id, refClass);

                            String label = reader.getAttributeValue(null, "label");

                            if (label == null || label.equals("")) {
                                label = refClass.getName();
                            }

                            UIClass uiClass = new UIClass(label);

                            refClass.setUiClass(uiClass);
                        }
                    } else if (reader.getLocalName().equals("StandardForm")) {
                        FMClass refClass = (FMClass)model
                                .getElementById(reader.getAttributeValue(null, "base_Element"));
                        if (refClass != null) {
                            stereotypes.put(id, refClass);

                            String label = reader.getAttributeValue(null, "label");
                            if (label == null || label.equals("")) {
                                label = refClass.getName();
                            }
                            Boolean add = Boolean.parseBoolean(reader.getAttributeValue(null, "add"));
                            Boolean update = Boolean.parseBoolean(reader.getAttributeValue(null, "update"));
                            Boolean delete = Boolean.parseBoolean(reader.getAttributeValue(null, "delete"));
                            Boolean copy = Boolean.parseBoolean(reader.getAttributeValue(null, "copy"));
                            
                            

                            StandardForm stForm = new StandardForm(label, add, delete, update, copy);
         
                            refClass.setUiClass(stForm);
                        }
                    } else if (reader.getLocalName().equals("MainForm")) {
                        FMClass refClass = (FMClass)model
                                .getElementById(reader.getAttributeValue(null, "base_Element"));
                        if (refClass != null) {
                            stereotypes.put(id, refClass);

                            String label = reader.getAttributeValue(null, "label");
                            if (label == null || label.equals("")) {
                                label = refClass.getName();
                            }
                            String putanjaDoSlike = reader.getAttributeValue(null, "putanjaDoSlike");
                            String putanjaDoIkonice = reader.getAttributeValue(null, "putanjaDoIkonice");
                            
                            MainForm mf = new MainForm(label);
                            mf.setPutanjaDoIkonice(putanjaDoIkonice);
                            mf.setPutanjaDoSlike(putanjaDoSlike);

                            refClass.setUiClass(mf);
                        }
                    } else if (reader.getLocalName().equals("DBProperty")) {
                        FMProperty refProperty = (FMProperty)model
                                .getElementById(reader.getAttributeValue(null, "base_Property"));
                        if (refProperty != null) {
                            stereotypes.put(id, refProperty);
                           
                            String label = reader.getAttributeValue(null, "label");
                            if (label == null || label.equals("")) {
                                label = refProperty.getName();
                            }
                            DBProperty dbProperty = new DBProperty();
                            dbProperty.setId(Boolean.parseBoolean(reader.getAttributeValue(null,"ID")));
                            if(reader.getAttributeValue(null,"mandatory")==null){
                            	dbProperty.setMandatory(true);
                            }else{
                            	dbProperty.setMandatory(false);
                            }
                            dbProperty.setUnique(Boolean.parseBoolean(reader.getAttributeValue(null,"unique")));

                            refProperty.setDbProperty(dbProperty);
                        }
                    } else if (reader.getLocalName().equals("UIProperty")) {
                        FMProperty refProperty = (FMProperty)model
                                .getElementById(reader.getAttributeValue(null, "base_Element"));
                        if (refProperty != null) {
                            stereotypes.put(id, refProperty);
                            
                            String label = reader.getAttributeValue(null, "label");
                            if (label == null || label.equals("")) {
                                label = refProperty.getName();
                            }
                            
                            int length;
                            if(reader.getAttributeValue(null, "length")!=null)
                            	length = Integer.parseInt(reader.getAttributeValue(null, "length"));
                            else
                            	length = 0;
                            int precision;
                            if(reader.getAttributeValue(null, "precision")!=null)
                            	precision = Integer.parseInt(reader.getAttributeValue(null, "precision"));
                            else
                            	precision = 0;
                            ComponentKind component = null;
                            if(reader.getAttributeValue(null, "component") !=null){
	                            switch(reader.getAttributeValue(null, "component")){
	                            	case "textField": component = ComponentKind.textField;
	                            		break;
	                            	case "editor": component = ComponentKind.editor;
	                        			break;
	                            	case "combobox": component = ComponentKind.combobox;
	                        			break;
	                            	case "chechkbox": component = ComponentKind.checkbox;
	                        			break;
	                            	case "dateChooser": component = ComponentKind.dateChooser;
	                        			break;
	                            	case "textArea": component = ComponentKind.textArea;
	                            		break;
	                            }
                            }else{
                            	component = ComponentKind.textField;
                            }
                            boolean display = Boolean.parseBoolean(reader.getAttributeValue(null, "display"));
                            boolean toString = Boolean.parseBoolean(reader.getAttributeValue(null, "toString"));
                            UIProperty uiProp = new UIProperty(label, length, precision, component, display);
                            uiProp.setToString(toString);
                            refProperty.setUiProperty(uiProp);;
                        }
                    } else if (reader.getLocalName().equals("Editable")) {
                        FMProperty refProperty = (FMProperty)model
                                .getElementById(reader.getAttributeValue(null, "base_Element"));
                        if (refProperty != null) {
                            stereotypes.put(id, refProperty);

                            String label = reader.getAttributeValue(null, "label");
                            if (label == null || label.equals("")) {
                                label = refProperty.getName();
                            }
                            int length;
                            if(reader.getAttributeValue(null, "length")!=null)
                            	length = Integer.parseInt(reader.getAttributeValue(null, "length"));
                            else
                            	length = 0;
                            int precision;
                            if(reader.getAttributeValue(null, "precision")!=null)
                            	precision = Integer.parseInt(reader.getAttributeValue(null, "precision"));
                            else
                            	precision = 0;
                            ComponentKind component = null;
                            if(reader.getAttributeValue(null, "component") !=null){
	                            switch(reader.getAttributeValue(null, "component")){
	                            	case "textField": component = ComponentKind.textField;
	                            		break;
	                            	case "editor": component = ComponentKind.editor;
	                        			break;
	                            	case "combobox": component = ComponentKind.combobox;
	                        			break;
	                            	case "chechkbox": component = ComponentKind.checkbox;
	                        			break;
	                            	case "dateChooser": component = ComponentKind.dateChooser;
	                        			break;
	                            	case "textArea": component = ComponentKind.textArea;
	                            		break;
	                            }
                            }else{
                            	component = ComponentKind.textField;
                            }
                            boolean display = Boolean.parseBoolean(reader.getAttributeValue(null, "display"));
                            boolean toString = Boolean.parseBoolean(reader.getAttributeValue(null, "toString"));
                            Editable edit = new Editable(label, length, precision, component, display);
                            edit.setToString(toString);
                            refProperty.setUiProperty(edit);
                        }
                    } else if (reader.getLocalName().equals("ReadOnly")) {
                        FMProperty refProperty = (FMProperty)model
                                .getElementById(reader.getAttributeValue(null, "base_Element"));
                        if (refProperty != null) {
                            stereotypes.put(id, refProperty);

                            String label = reader.getAttributeValue(null, "label");
                            if (label == null || label.equals("")) {
                                label = refProperty.getName();
                            }
                            int length;
                            if(reader.getAttributeValue(null, "length")!=null)
                            	length = Integer.parseInt(reader.getAttributeValue(null, "length"));
                            else
                            	length = 0;
                            int precision;
                            if(reader.getAttributeValue(null, "precision")!=null)
                            	precision = Integer.parseInt(reader.getAttributeValue(null, "precision"));
                            else
                            	precision = 0;
                            ComponentKind component = null;
                            if(reader.getAttributeValue(null, "component") !=null){
	                            switch(reader.getAttributeValue(null, "component")){
	                            	case "textField": component = ComponentKind.textField;
	                            		break;
	                            	case "editor": component = ComponentKind.editor;
	                        			break;
	                            	case "combobox": component = ComponentKind.combobox;
	                        			break;
	                            	case "chechkbox": component = ComponentKind.checkbox;
	                        			break;
	                            	case "dateChooser": component = ComponentKind.dateChooser;
	                        			break;
	                            	case "textArea": component = ComponentKind.textArea;
	                            		break;
	                            }
                            }else{
                            	component = ComponentKind.textField;
                            }
                            boolean display = Boolean.parseBoolean(reader.getAttributeValue(null, "display"));
                            boolean toString = Boolean.parseBoolean(reader.getAttributeValue(null, "toString"));
                            ReadOnly read = new ReadOnly(label, length, precision, component, display);
                            read.setToString(toString);
                            refProperty.setUiProperty(read);
                        }
                    } else if (reader.getLocalName().equals("Lookup")) {
                        FMProperty refProperty = (FMProperty)model
                                .getElementById(reader.getAttributeValue(null, "base_Element"));
                        if (refProperty != null) {
                            stereotypes.put(id, refProperty);

                            String label = reader.getAttributeValue(null, "label");
                            if (label == null || label.equals("")) {
                                label = refProperty.getName();
                            }
                            int length;
                            if(reader.getAttributeValue(null, "length")!=null)
                            	length = Integer.parseInt(reader.getAttributeValue(null, "length"));
                            else
                            	length = 0;
                            int precision;
                            if(reader.getAttributeValue(null, "precision")!=null)
                            	precision = Integer.parseInt(reader.getAttributeValue(null, "precision"));
                            else
                            	precision = 0;
                            ComponentKind component = null;
                            if(reader.getAttributeValue(null, "component") !=null){
	                            switch(reader.getAttributeValue(null, "component")){
	                            	case "textField": component = ComponentKind.textField;
	                            		break;
	                            	case "editor": component = ComponentKind.editor;
	                        			break;
	                            	case "combobox": component = ComponentKind.combobox;
	                        			break;
	                            	case "chechkbox": component = ComponentKind.checkbox;
	                        			break;
	                            	case "dateChooser": component = ComponentKind.dateChooser;
	                        			break;
	                            	case "textArea": component = ComponentKind.textArea;
	                            		break;
	                            }
                            }else{
                            	component = ComponentKind.textField;
                            }
                            boolean display = Boolean.parseBoolean(reader.getAttributeValue(null, "display"));
                            boolean toString = Boolean.parseBoolean(reader.getAttributeValue(null, "toString"));
                            Lookup look = new Lookup(label, length, precision, component, display);
                            look.setToString(toString);
                            refProperty.setUiProperty(look);
                        }
                    }  

                    setChanged();
                    notifyObservers();
                    break;

                case XMLEvent.END_ELEMENT:
                    if (reader.getLocalName().equals("ownedAttribute")) {
                        currentProperty = null;
                    } else if (reader.getLocalName().equals("packagedElement")) {
                        currentClass = null;
                        currentEnum = null;
                    }

                    break;
                }
            }

            for (FMClass classElement : model.getClasses()) {
                for (FMProperty property : classElement.getProperties()) {
                    FMType type = (FMType)model.getElementById(property.getType());

                    if (type != null) {
                    	property.setReference(type);
                    	if (model.getEnums().contains(type)){ 
                    		property.setEnumerated(true);
                    	}
                        property.setType(type.getName());
                    }
                }
            }

        } catch (XMLStreamException e) {
           e.printStackTrace();
        }

        try {
            reader.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        reader = null;
        return model;
    }

}
