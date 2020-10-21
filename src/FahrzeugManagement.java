import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * @author Yasahan Zengin
 * Matrikelnummer: a1367563
 */

public class FahrzeugManagement {

    private FahrzeugDAO fahrzeugDAO;
    private static final DecimalFormat doubleFormat = new DecimalFormat(".00");
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
            System.out.println("Error: Fahrzeug nicht vorhanden." +  "(id= " + id + ")");
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
    }

    public boolean fahrzeugCheck(Fahrzeug fahrzeug){
        return fahrzeugDAO.getFahrzeugList().stream().anyMatch(x -> x.getId() == fahrzeug.getId());
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

    public String averagePriceOfFahrzeuge(){
        return doubleFormat.format(fahrzeugDAO.getFahrzeugList().stream()
                .mapToDouble(Fahrzeug::getPreis)
                .sum() / fahrzeugDAO.getFahrzeugList().size());
    }

    public void getOldestFahrzeug(){
         int max_age = fahrzeugDAO.getFahrzeugList().stream().max(Comparator.comparing(Fahrzeug::getAlter)).get().getAlter();
         List<Fahrzeug> ids = fahrzeugDAO.getFahrzeugList().stream().filter(x -> x.getAlter() == max_age).collect(Collectors.toList());
         for(Fahrzeug fzg : ids){
             System.out.println("Id: " + fzg.getId());
         }
    }

}
