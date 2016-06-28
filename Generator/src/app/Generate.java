package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import model.FMClass;
import model.FMEnum;
import model.FMModel;
import parser.UMLParser;

public class Generate {

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
		List<FMEnum> prop = model.getEnums();
		System.out.println(classes.size() + " " + prop.size());
	}

}
