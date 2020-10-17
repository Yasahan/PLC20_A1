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
        if(fahrzeugDAO.getFahrzeugList().isEmpty()){
            System.out.println("Es gibt momentan keine Fahrzeuge.");
        }
        fahrzeugDAO.getFahrzeugList().forEach(System.out::println);
    }

    public void getFahrzeugInfo(int id){
        Fahrzeug fahrzeug = fahrzeugDAO.getFahrzeugbyId(id);
        if(fahrzeug == null){
            System.out.println("Fahrzeug konnte nicht gefunden werden!");
            return;
        }
        System.out.println(fahrzeug);
    }

    public void addNewFahrzeug(Fahrzeug fahrzeug){
        if(fahrzeugCheck(fahrzeug)){
            System.out.println("Fahrzeug existiert bereits");
            return;
        }
        fahrzeugDAO.speichereFahrzeug(fahrzeug);
        System.out.println("Fahrzeug wurde erfolgreich hinzugefuegt!");
    }

    public boolean fahrzeugCheck(Fahrzeug fahrzeug){
        return fahrzeugDAO.getFahrzeugList().stream().anyMatch(x -> x.getId() == fahrzeug.getId());
    }
    public void deleteFahrzeug(int id){
        fahrzeugDAO.loescheFahrzeug(id);
        System.out.println("Fahrzeug wurde erfolgreich geloescht!");
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
