/**
 * @author Yasahan Zengin
 * Matrikelnummer: a1367563
 */

public class Lkw extends Fahrzeug {

	private static final long serialVersionUID = 1L;

	public Lkw(int id, String marke, String modell, int baujahr, double grundPreis) {
		super(id, marke, modell, baujahr, grundPreis);
	}

	@Override
	int getRabatt() {
		int rabatt = this.getAlter()*5;
		return Math.min(rabatt, 20);
	}

	@Override
	public String toString() {
		return "Typ:         " + "LKW\n" +
				"Id:          " + this.getId() + "\n" +
				"Marke:       " + this.getMarke() + "\n" +
				"Modell:      " + this.getModell() + "\n" +
				"Baujahr:     " + this.getBaujahr() + "\n" +
				"Grundpreis:  " + getDecimalFormat().format(this.getGrundPreis()) + "\n" +
				"Preis:       " + getDecimalFormat().format(this.getPreis()) + "\n";
	}
}
