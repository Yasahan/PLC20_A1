import java.io.*;
import java.util.ArrayList;

/**
 * @author <your-name-here>
 * Matrikelnummer:
 */

public class FahrzeugClient {

	public static void main(String[] args) throws IOException {
		// java -jar PLC_Aufgabe1.jar app Fahrzeuge

		String dataName = args[0];
		String method =  args[1];
		String fahrzeugTyp = args[2];
		int id = Integer.parseInt(args[3]);
		String marke = args[4];
		String modell = args[5];
		int baujahr = Integer.parseInt(args[6]);
		double grundPreis = Double.parseDouble(args[7]);
		int letzteService = Integer.parseInt(args[8])
				;
		File f = new File(dataName);
		FahrzeugManagement fahrzeugManagement;

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

		switch (method){
			case "show" :
				if(String.valueOf(id).isEmpty()){
					fahrzeugManagement.getAllFahrzeugeInfo();
				}
				else {
					fahrzeugManagement.getFahrzeugInfo(id);
				}
				break;
			case "add" :
				if(fahrzeugTyp.isEmpty()){
					System.out.println("Bitte geben Sie das Fahrzeugtyp an");
				}
				else if (fahrzeugTyp.toUpperCase().equals("LKW")) {
					fahrzeugManagement.addNewFahrzeug(new Lkw(id, marke, modell, baujahr, grundPreis));
				}
				else if (fahrzeugTyp.toUpperCase().equals("PKW")){
					fahrzeugManagement.addNewFahrzeug(new Pkw(id, marke, modell, baujahr, grundPreis, letzteService));
				}
				break;
			case "del" :
				fahrzeugManagement.deleteFahrzeug(id);
				break;
			case "count" :
				if(fahrzeugTyp.isEmpty()){
					fahrzeugManagement.amountOfAllFahrzeuge();
				}
				else if(fahrzeugTyp.toUpperCase().equals("LKW")){
					fahrzeugManagement.amountOfAllLkw();
				}
				else if (fahrzeugTyp.toUpperCase().equals("PKW")){
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



/*
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
		fahrzeugManagement.getAllFahrzeugeInfo();*/

	}



}