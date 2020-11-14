import java.util.Calendar;

/**
 * @author Yasahan Zengin
 * Matrikelnummer: a1367563
 */

public class Pkw extends Fahrzeug {

	private static final long serialVersionUID = 1L;
	private int letzteService;
	public Pkw(int id, String marke, String modell, int baujahr, double grundPreis, int letzteService) {
		super(id, marke, modell, baujahr, grundPreis);
		this.letzteService = letzteService;
	}

	@Override
	int getRabatt() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int rabatt = this.getAlter()*5 + (currentYear - letzteService)*2;
		return Math.min(rabatt, 15);
	}

	public int getLetzteService() {
		return letzteService;
	}

	public void setLetzteService(int letzteService) {
		this.letzteService = letzteService;
	}

	@Override
	public String toString() {
		return  "Typ:         " + "PKW\n" +
				"Id:          " + this.getId() + "\n" +
				"Marke:       " + this.getMarke() + "\n" +
				"Modell:      " + this.getModell() + "\n" +
				"Baujahr:     " + this.getBaujahr() + "\n" +
				"Grundpreis:  " + getDecimalFormat().format(this.getGrundPreis()) + "\n" +
				"Servicejahr: " + this.getLetzteService() + "\n" +
				"Preis:       " + getDecimalFormat().format(this.getPreis()) + "\n";
	}
}
