import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author Yasahan Zengin
 * Matrikelnummer: a1367563
 */

public class SerializedFahrzeugDAO implements FahrzeugDAO {

    List<Fahrzeug> fahrzeuge;
    String dateiName;

    public SerializedFahrzeugDAO(String dateiName) {
        this.dateiName = dateiName;
        this.fahrzeuge = new ArrayList<>();
    }

    @Override
    public List<Fahrzeug> getFahrzeugList() {
        try (FileInputStream fis = new FileInputStream(dateiName); ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.fahrzeuge = (ArrayList<Fahrzeug>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fehler bei Serialisierung:");
            System.exit(1);
        }
        return fahrzeuge;
    }

    @Override
    public Fahrzeug getFahrzeugbyId(int id) {
        getFahrzeugList();
        return fahrzeuge.stream()
                .parallel()
                .filter(x -> x.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public void speichereFahrzeug(Fahrzeug fahrzeug) {
        fahrzeuge.add(fahrzeug);
        speichereAktuelleListe();
    }

    @Override
    public void loescheFahrzeug(int id) {
        Fahrzeug fahrzeug = getFahrzeugbyId(id);
        if(fahrzeug == null){
            System.out.println("Error: Fahrzeug nicht vorhanden. " +  "(id=" + id + ")");
            return;
        }
        fahrzeuge.remove(fahrzeug);
        speichereAktuelleListe();
    }

    public void speichereAktuelleListe(){
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            fileOut = new FileOutputStream(dateiName);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(fahrzeuge);
        } catch (IOException e) {
            System.out.println("Fehler bei Deserialisierung:");
            System.exit(1);
        } finally {
            closeStream(fileOut);
            closeStream(out);
        }
    }

    private static void closeStream(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
