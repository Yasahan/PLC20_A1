/**
 * @author <your-name-here>
 * Matrikelnummer:
 */

public class Lkw extends Fahrzeug {

	private static final long serialVersionUID = 1L;

	public Lkw(String marke, String modell, int baujahr, double grundPreis) {
		super(marke, modell, baujahr, grundPreis);
	}

	@Override
	int getRabatt() {
		int rabatt = this.getAlter()*5;
		return Math.min(rabatt, 20);
	}
}
