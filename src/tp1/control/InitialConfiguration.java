package tp1.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitialConfiguration {
	
	public static final InitialConfiguration NONE = new InitialConfiguration();
		
	private List<String> descriptions;
	
	private InitialConfiguration() {}
	
	private InitialConfiguration(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
	public List<String> getShipDescription(){
		return Collections.unmodifiableList(descriptions);
	}
	
	public static InitialConfiguration readFromFile(String filename) throws FileNotFoundException, IOException {
		BufferedReader inChars = null;
		List<String> descriptions = new ArrayList<String>();
		try {
			inChars = new BufferedReader(new FileReader(filename));
			String l;
			while ((l = inChars.readLine()) != null) {
			    descriptions.add(l);
			}
		} finally {
		    if (inChars != null) { inChars.close(); }
		    
		}
		return new InitialConfiguration(descriptions);
	}
	
}
