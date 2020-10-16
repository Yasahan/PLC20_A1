/**
 * @author <your-name-here>
 * Matrikelnummer:
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
		int rabatt = this.getAlter()*5 + letzteService*2;
		return Math.min(rabatt, 15);
	}

	public int getLetzteService() {
		return letzteService;
	}

	public void setLetzteService(int letzteService) {
		this.letzteService = letzteService;
	}

}
