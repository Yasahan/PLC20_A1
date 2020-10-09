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

    public void getFahrzeugInfo(Fahrzeug fahrzeug){
        System.out.println(fahrzeugDAO.getFahrzeugbyId(fahrzeug.getId()));
    }

    public void addNewFahrzeug(Fahrzeug fahrzeug){
        fahrzeugDAO.speichereFahrzeug(fahrzeug);
    }

    public void deleteFahrzeug(Fahrzeug fahrzeug){
        fahrzeugDAO.loescheFahrzeug(fahrzeug.getId());
    }

    public int amountOfAllFahrzeuge(){
        return fahrzeugDAO.getFahrzeugList().size();
    }

    public int amountOfAllLkw(){
        return (int) fahrzeugDAO.getFahrzeugList()
                .stream()
                .filter(x -> x.getClass().equals(Lkw.class))
                .count();
    }

    public int amountOfAllPkw(){
        return (int) fahrzeugDAO.getFahrzeugList()
                .stream()
                .filter(x -> x.getClass().equals(Pkw.class))
                .count();
    }

    public double averagePriceOfFahrzeuge(){
        return fahrzeugDAO.getFahrzeugList().stream().mapToDouble(Fahrzeug::getGrundPreis)
                .sum() / fahrzeugDAO.getFahrzeugList().size();
    }


	
	
}
