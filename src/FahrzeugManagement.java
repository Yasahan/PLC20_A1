import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * @author <your-name-here>
 * Matrikelnummer:
 */

public class FahrzeugManagement {

    private FahrzeugDAO fahrzeugDAO;

    public FahrzeugManagement(String dataName) {
        this.fahrzeugDAO = new SerializedFahrzeugDAO(dataName);
    }

    public void getAllFahrzeugeInfo(){
        fahrzeugDAO.getFahrzeugList().forEach(System.out::println);
    }

    public void getFahrzeugInfo(int id){
        Fahrzeug fahrzeug = fahrzeugDAO.getFahrzeugbyId(id);
        if(fahrzeug == null){
            System.out.println("Fahrzeug konnte nicht gefunden werden!");
            return;
        }
        System.out.println();
    }

    public void addNewFahrzeug(Fahrzeug fahrzeug){
        fahrzeugDAO.speichereFahrzeug(fahrzeug);
    }

    public void deleteFahrzeug(int id){
        fahrzeugDAO.loescheFahrzeug(id);
    }

    public int amountOfAllFahrzeuge(){
        return fahrzeugDAO.getFahrzeugList().size();
    }

    public int amountOfAllLkw(){
        return (int) fahrzeugDAO.getFahrzeugList()
                .stream()
                .filter(x -> x instanceof Lkw)
                .count();
    }

    public int amountOfAllPkw(){
        return (int) fahrzeugDAO.getFahrzeugList()
                .stream()
                .filter(x -> x instanceof Pkw)
                .count();
    }

    public double averagePriceOfFahrzeuge(){
        return fahrzeugDAO.getFahrzeugList().stream().mapToDouble(Fahrzeug::getGrundPreis)
                .sum() / fahrzeugDAO.getFahrzeugList().size();
    }

    public Fahrzeug getOldestFahrzeug(){
        return fahrzeugDAO.getFahrzeugList().stream().max(Comparator.comparing(Fahrzeug::getAlter)).get();
    }

	
	
}
