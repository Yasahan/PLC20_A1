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
		String fahrzeugTyp;
		int id;
		String marke;
		String modell;
		int baujahr;
		double grundPreis;
		int letzteService;
		FahrzeugManagement fahrzeugManagement;
		File f = new File(dataName);

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
				if(args.length <= 2){
					fahrzeugManagement.getAllFahrzeugeInfo();
				}
				else {
					fahrzeugManagement.getFahrzeugInfo(Integer.parseInt(args[2]));
				}
				break;
			case "add" :
				fahrzeugTyp = args[2];
				grundPreis = Double.parseDouble(args[7]);
				id = Integer.parseInt(args[3]);
				baujahr = Integer.parseInt(args[6]);
				marke = args[4];
				modell = args[5];
				if(fahrzeugTyp.isEmpty()){
					System.out.println("Bitte geben Sie das Fahrzeugtyp an");
				}
				else if (fahrzeugTyp.toUpperCase().equals("LKW")) {
					fahrzeugManagement.addNewFahrzeug(new Lkw(id, marke, modell, baujahr, grundPreis));
				}
				else if (fahrzeugTyp.toUpperCase().equals("PKW")){
					if(args.length != 9){
						System.out.println("Parameter ungueltig.");
						return;
					}
					letzteService = Integer.parseInt(args[8]);
					fahrzeugManagement.addNewFahrzeug(new Pkw(id, marke, modell, baujahr, grundPreis, letzteService));
				}
				break;
			case "del" :
				fahrzeugManagement.deleteFahrzeug(Integer.parseInt(args[2]));
				break;
			case "count" :
				if(args.length == 2){
					System.out.println(fahrzeugManagement.amountOfAllFahrzeuge());
				}
				else if(args[2].toUpperCase().equals("LKW")){
					System.out.println(fahrzeugManagement.amountOfAllLkw());
				}
				else if (args[2].toUpperCase().equals("PKW")){
					System.out.println(fahrzeugManagement.amountOfAllPkw());
				}
				break;
			case "meanprice" :
				System.out.println(fahrzeugManagement.averagePriceOfFahrzeuge());
				break;
			case "oldest" :
				System.out.println(fahrzeugManagement.getOldestFahrzeug());
				break;
			default:
				System.out.println("Parameter ungueltig.");
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
*/

/*
		System.out.println(fahrzeugManagement.amountOfAllFahrzeuge());
		System.out.println(fahrzeugManagement.amountOfAllLkw());
		System.out.println(fahrzeugManagement.amountOfAllPkw());
		fahrzeugManagement.deleteFahrzeug(neuPkw2.getId());
		System.out.println(fahrzeugManagement.averagePriceOfFahrzeuge());
		fahrzeugManagement.getAllFahrzeugeInfo();*/

		//fahrzeugManagement.getAllFahrzeugeInfo();
	}



}