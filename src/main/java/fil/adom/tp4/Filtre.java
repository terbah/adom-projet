package fil.adom.tp4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fil.adom.tp1.Glouton;


/***
 * Represente un filtre sur l'ensembles des instances
 *
 */
public abstract class Filtre {
	
	//public static String PATH = "C:\\Users\\aghil\\Documents\\M2MIAGE\\ADOM\\adom-projet\\Ressources\\fichiersGeneres\\";
	public static String PATH = "/home/m2miage/terbah/Documents/ADOM/adom-projet/Ressources/fichiersGeneres/tp4/";

	protected TwoMatrice matricesAB;
	protected TwoMatrice matricesCD;
	protected TwoMatrice matricesEF;

	protected List<List<MultiObjectiveObject>> instanceinit;
	
	public Filtre() {
		this.instanceinit = new ArrayList<>();
		this.matricesAB = new TwoMatrice("A", "B");
		this.matricesCD = new TwoMatrice("C", "D");
		this.matricesEF = new TwoMatrice("E", "F");

	}
	
	public void load(int n) throws IOException {
		loadMatrices();
		loadList(n);
	}
	
	public void loadMatrices() throws IOException {
		matricesAB.load();
		matricesCD.load();
		matricesEF.load();
	}
	
	public void loadList(int n) throws IOException {
		
	}
	
	public List<MultiObjectiveObject> listInstance(int nbInstances, TwoMatrice ms ) throws IOException{
		return null;
	}

	public TwoMatrice getMatricesAB() {
		return matricesAB;
	}

	public void setMatricesAB(TwoMatrice matricesAB) {
		this.matricesAB = matricesAB;
	}

	public TwoMatrice getMatricesCD() {
		return matricesCD;
	}

	public void setMatricesCD(TwoMatrice matricesCD) {
		this.matricesCD = matricesCD;
	}

	public TwoMatrice getMatricesEF() {
		return matricesEF;
	}

	public void setMatricesEF(TwoMatrice matricesEF) {
		this.matricesEF = matricesEF;
	}

	public List<List<MultiObjectiveObject>> getInstanceinit() {
		return instanceinit;
	}

	public void setInstanceinit(List<List<MultiObjectiveObject>> instanceinit) {
		this.instanceinit = instanceinit;
	}	
	
	
	/***
	 * 
	 * @param chemin une liste de villes
	 * @param ms un couple de matrices
	 * @return un objet MultiObjectiveObject dont les attributs correspondent aux couts du chemin pour les 2 matrices
	 * @throws IOException
	 */
	public static MultiObjectiveObject createObject(Integer[] chemin, TwoMatrice ms) throws IOException {
		MultiObjectiveObject o = new MultiObjectiveObject();
		o.setChemin(chemin);
		o.setCritere1(Glouton.valChemin(chemin, ms.getMatriceA()));
		o.setCritere2(Glouton.valChemin(chemin, ms.getMatriceB()));
		return o;
	}	
	
	
	
	/**
	 * Supprime tous les elements domines de la liste.
	 * @param list la liste a filtrer 
	 */
	public static void supprimerDomines(List<MultiObjectiveObject> list) {
		list.removeIf(o -> o.isDomine());
	}
	
	/***
	 * Enregistre dans un fichier les instances générées
	 * @param ms un couple de matrices
	 * @param n nombre d'instances genérées 
	 * @throws IOException 
	 */
	public static void createFileListMultiObjectiveObject(List<MultiObjectiveObject> list, String path, String filename) throws IOException {
		//String path = "/home/m2miage/terbah/Documents/ADOM/fichiers/";
		//String filename = "villesAleatoireMulti"+".txt";
		File f = new File(path+filename);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		for (MultiObjectiveObject o : list) {
			writer.write(o + "\n");
		}
	    writer.close();
	}
	
	public static void stringToFile(String string, String path, String filename) throws IOException {
		File f = new File(path+filename);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		writer.write(string);
	    writer.close();
	}
	
	
}
