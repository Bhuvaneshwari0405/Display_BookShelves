package objectImplementation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ObjectReader {
	Properties pro;
	FileInputStream fileInput;
	
	//Loads the file 
	public ObjectReader() throws IOException {
		try {
			pro = new Properties();
			String path = "C:\\Users\\2487361\\eclipse-workspace\\myworkspace\\com.finalproject.bookshelves\\ObjectRepository\\object.properties";
			fileInput = new FileInputStream(path);
			pro.load(fileInput);
		}catch(FileNotFoundException e) {
			System.out.println("File Not found" + e);
		}
		
	}
	
	//Fetches the url
	public String get_baseUrl() {
		return pro.getProperty("BaseUrl");
	}
}
