import java.io.*;
import java.util.ArrayList;

/**
 * @author Yasahan Zengin
 * Matrikelnummer: a1367563
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
			throw new IllegalArgumentException("Error: Parameter ungueltig.");
		}

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
				try{
					fahrzeugTyp = args[2];
					grundPreis = Double.parseDouble(args[7]);
					id = Integer.parseInt(args[3]);
					baujahr = Integer.parseInt(args[6]);
					marke = args[4];
					modell = args[5];
				} catch (Exception e) {
					throw new IllegalArgumentException("Error: Parameter ungueltig.");
				}
				if (fahrzeugTyp.toUpperCase().equals("LKW")) {
					fahrzeugManagement.addNewFahrzeug(new Lkw(id, marke, modell, baujahr, grundPreis));
				}
				else if (fahrzeugTyp.toUpperCase().equals("PKW")){
					if(args.length != 9){
						System.out.println("Error: Parameter ungueltig.");
						return;
					}
					letzteService = Integer.parseInt(args[8]);
					if(letzteService < baujahr){
						System.out.println("Error: Servicejahr ungueltig.");
						return;
					}
					fahrzeugManagement.addNewFahrzeug(new Pkw(id, marke, modell, baujahr, grundPreis, letzteService));
				}
				else {
					System.out.println("Error: Parameter ungueltig.");
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
				fahrzeugManagement.getOldestFahrzeug();
				break;
			default:
				System.out.println("Error: Parameter ungueltig.");
				break;

		}


	}



}