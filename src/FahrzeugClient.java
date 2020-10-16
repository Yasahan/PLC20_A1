import java.io.*;
import java.util.ArrayList;

/**
 * @author <your-name-here>
 * Matrikelnummer:
 */

public class FahrzeugClient {

	public static void main(String[] args) throws IOException {

		String dataName = args[0];
		File f = new File(dataName);
		FahrzeugManagement fahrzeugManagement;
		String marke;
		String modell;
		int baujahr;
		double grundPreis;
		int letzteService;


		if(args.length < 2){
			throw new IllegalArgumentException("Parameter ungueltig.");
		}

		// <Parameter>: show, add, del, count,meanprice, oldest.
		if (f.isFile() && f.canRead()) {
			fahrzeugManagement = new FahrzeugManagement(dataName);
		}
		else {
			FileOutputStream fileOut = new FileOutputStream(dataName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(new ArrayList<Fahrzeug>());
			fahrzeugManagement = new FahrzeugManagement(dataName);
			fileOut.close();
			out.close();
		}

		switch (args[1]){
			case "show" :
				if(args[2].isEmpty()){
					fahrzeugManagement.getAllFahrzeugeInfo();
				}
				else {
					fahrzeugManagement.getFahrzeugInfo(Integer.parseInt(args[3]));
				}
				break;
			case "add" :
				if(args[3].isEmpty()){
					System.out.println("Bitte geben Sie das Fahrzeugtyp an");
				}
				else if (args[3].toUpperCase().equals("LKW")) {
					fahrzeugManagement.addNewFahrzeug(new Lkw(Integer.parseInt(args[4]), args[5], args[6], Integer.parseInt(args[7]), Double.parseDouble(args[8])));
				}
				else if (args[3].toUpperCase().equals("PKW")){
					fahrzeugManagement.addNewFahrzeug(new Pkw(Integer.parseInt(args[4]), args[5], args[6], Integer.parseInt(args[7]), Double.parseDouble(args[8]), Integer.parseInt(args[9])));
				}
				break;
			case "del" :
				fahrzeugManagement.deleteFahrzeug(Integer.parseInt(args[2]));
				break;
			case "count" :
				if(args[2].isEmpty()){
					fahrzeugManagement.amountOfAllFahrzeuge();
				}
				else if(args[2].toUpperCase().equals("LKW")){
					fahrzeugManagement.amountOfAllLkw();
				}
				else if (args[2].toUpperCase().equals("PKW")){
					fahrzeugManagement.amountOfAllPkw();
				}
				break;
			case "meanprice" :
				fahrzeugManagement.averagePriceOfFahrzeuge();
				break;
			case "oldest" :
				fahrzeugManagement.getOldestFahrzeug();
				break;

		}


		Pkw neuPkw = new Pkw(14, "Mercedes", "C-Classe", 1998, 1000.250, 2005);
		Pkw neuPkw2 = new Pkw(15, "Bmw", "M3", 2015, 512000.250, 2015);
		Lkw neuPkw3 = new Lkw(16,"Audi", "A8", 2020, 25000);
		Lkw neuPkw4 = new Lkw(17,"Ford", "Mustang", 2001, 1000.250);

		fahrzeugManagement.addNewFahrzeug(neuPkw);
		fahrzeugManagement.addNewFahrzeug(neuPkw2);
		fahrzeugManagement.addNewFahrzeug(neuPkw3);
		fahrzeugManagement.addNewFahrzeug(neuPkw4);

		fahrzeugManagement.getAllFahrzeugeInfo();
		System.out.println(fahrzeugManagement.amountOfAllFahrzeuge());
		System.out.println(fahrzeugManagement.amountOfAllLkw());
		System.out.println(fahrzeugManagement.amountOfAllPkw());
		fahrzeugManagement.deleteFahrzeug(neuPkw2.getId());
		System.out.println(fahrzeugManagement.averagePriceOfFahrzeuge());
		fahrzeugManagement.getAllFahrzeugeInfo();




	}



}