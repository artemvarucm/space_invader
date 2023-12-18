package tp1.control;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitialConfiguration {
	// Clase que se encarga de guardar la configuracion dado un fichero.
	// Se usa por AlienManager para spawnear las naves necesarias de la lista descriptions.
	
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
		// Lee el fichero filename y guarda cada linea como elemento de descriptions
		List<String> descriptions = new ArrayList<String>();
		
		try (BufferedReader inChars = new BufferedReader(new FileReader(filename))){ 
			// try-with-resources, para manejo mas sencillo del fichero
			String l;
			while ((l = inChars.readLine()) != null) {
			    descriptions.add(l);
			}
		}
		return new InitialConfiguration(descriptions);
	}
	
}
