/**
 * @author <your-name-here>
 * Matrikelnummer:
 */

public class FahrzeugClient {

	public static void main(String[] args) {

		Pkw neuPkw = new Pkw("Mercedes", "Pkw", 1998, 1000.250, 2005);
		Pkw neuPkw2 = new Pkw("Mercedes", "Pkw", 1998, 512000.250, 2005);
		Lkw neuPkw3 = new Lkw("Mercedes", "Pkw", 1998, 1000.250);
		Lkw neuPkw4 = new Lkw("Mercedes", "Pkw", 1998, 1000.250);

		FahrzeugManagement fahrzeugManagement = new FahrzeugManagement("Fahrzeuge");

		fahrzeugManagement.addNewFahrzeug(neuPkw);
		fahrzeugManagement.addNewFahrzeug(neuPkw2);
		fahrzeugManagement.addNewFahrzeug(neuPkw3);
		fahrzeugManagement.addNewFahrzeug(neuPkw4);

		fahrzeugManagement.getAllFahrzeugeInfo();
		System.out.println(fahrzeugManagement.amountOfAllFahrzeuge());
		System.out.println(fahrzeugManagement.amountOfAllLkw());
		System.out.println(fahrzeugManagement.amountOfAllPkw());

	}
}