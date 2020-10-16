/**
 * @author <your-name-here>
 * Matrikelnummer:
 */

import java.io.Serializable;
import java.rmi.server.UID;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public abstract class Fahrzeug implements Serializable {

	private static final long serialVersionUID = 1L;
	private String marke;
	private String modell;
	private int baujahr;
	private double grundPreis;
	private static int idGenerator = 100001;
	private int id;

	public static DecimalFormat getDecimalFormat() {
		DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
		dfs.setDecimalSeparator('.');
		return new DecimalFormat("0.00", dfs);
	}

    public Fahrzeug(int id, String marke, String modell, int baujahr, double grundPreis) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        this.id = id;
        this.marke = marke;
        this.modell = modell;
        if(baujahr > currentYear){
            throw new IllegalArgumentException("das Baujahrdarf nicht in der Zukunft liegen");
        } else {
            this.baujahr = baujahr;
        }
        this.grundPreis = grundPreis;
        this.id = idGenerator++;
    }


    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(int baujahr) {
        this.baujahr = baujahr;
    }

    public double getGrundPreis() {
        return grundPreis;
    }

    public void setGrundPreis(double grundPreis) {
        this.grundPreis = grundPreis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlter(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	    return currentYear-this.baujahr;
    }

    abstract int getRabatt();

    public double getPreis(){
        return this.grundPreis-getRabatt();
    }

    @Override
    public String toString() {
        return "Fahrzeug{" +
                "marke='" + marke + '\'' +
                ", modell='" + modell + '\'' +
                ", baujahr=" + baujahr +
                ", grundPreis=" + grundPreis +
                ", id=" + id +
                '}';
    }
}
