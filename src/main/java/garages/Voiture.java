package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();
    private final Set<Garage> myGarages;

    public Voiture(String i) {
        this.myGarages = new HashSet<>();
        if (null == i) {
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        }

        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Fait rentrer la voiture au garage Précondition : la voiture ne doit pas
     * être déjà dans un garage
     *
     * @param g le garage où la voiture va stationner
     * @throws java.lang.Exception Si déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws Exception {
        // Et si la voiture est déjà dans un garage ?
        if (estDansUnGarage()) {
            throw new Exception("La voiture est déjà garée");
        } else {
            Stationnement s = new Stationnement(this, g);
            myStationnements.add(s);
            myGarages.add(g);
        }
    }

    /**
     * Fait sortir la voiture du garage Précondition : la voiture doit être dans
     * un garage
     *
     * @throws java.lang.Exception si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws Exception {
        if (!estDansUnGarage()) {
            throw new Exception("La voiture n'est pas garée");
        } else {
            Stationnement lastStat = myStationnements.get(myStationnements.size() - 1);
            lastStat.terminer();
        }
    }

    /**
     * @return l'ensemble des garages visités par cette voiture
     */
    public Set<Garage> garagesVisites() {
        return myGarages;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        if (myStationnements.size() > 0) {
            if (myStationnements.get(myStationnements.size() - 1).estEnCours()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste
     * des dates d'entrée / sortie dans ce garage
     * <br>Exemple :
     * <pre>
     * Garage Castres:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     *		Stationnement{ entree=28/01/2019, en cours }
     *  Garage Albi:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     * </pre>
     *
     * @param out l'endroit où imprimer (ex: System.out)
     */
    public void imprimeStationnements(PrintStream out) {
        for (Garage g : myGarages){
            out.println(g.toString()+ " ;");
            for (Stationnement s : myStationnements){
                if(g.equals(s.getGarage())){
                    out.println("\t" + s.toString());
                }
            }
        }
    }

}
