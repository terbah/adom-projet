package fil.adom.tp4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fil.adom.tp1.Glouton;

/***
 * Classe utile pour faire les tests des premieres questions
 *
 */
public class ParserMultiCriteres {

	public static String PATH = "C:\\Users\\aghil\\Documents\\M2MIAGE\\ADOM\\adom-projet\\Ressources\\fichiersGeneres\\";

	private TwoMatrice matrices;

	public static void main(String[] args) throws IOException {
		ParserMultiCriteres p = new ParserMultiCriteres("A", "B");
		p.createFileRandomMultiObjectiveObject(p.getTwoMatrice(),500, PATH, "multiAlea.txt");
		System.out.println("end");
	}
	
	public ParserMultiCriteres(String instanceA, String instanceB) throws IOException {
		this.matrices = new TwoMatrice(instanceA, instanceB);
		this.matrices.load();
	}
	
	public TwoMatrice getTwoMatrice() {
		return matrices;
	}
	
	/***
	 * 
	 * @param chemin une liste de villes
	 * @param ms un couple de matrices
	 * @return un objet MultiObjectiveObject dont les attributs correspondent aux couts du chemin pour les 2 matrices
	 * @throws IOException
	 */
	public static MultiObjectiveObject fonctionEvaluation(Integer[] chemin, TwoMatrice ms) throws IOException {
		MultiObjectiveObject o = new MultiObjectiveObject();
		o.setChemin(chemin);
		o.setCritere1(Glouton.valChemin(chemin, ms.getMatriceA()));
		o.setCritere2(Glouton.valChemin(chemin, ms.getMatriceB()));
		return o;
	}
	
	
	/***
	 * Renvoie les couts(critères) d'un chemin aléatoire pour le couple de matrices passé en paramètre
	 * @param ms un couple de matrices
	 * @throws IOException
	 */
	public static MultiObjectiveObject  objetAleatoire(TwoMatrice ms) throws IOException {
		Integer[] chemin = Glouton.villesAleatoire(100);
		MultiObjectiveObject o = fonctionEvaluation(chemin,ms );
		return o;
	}


	
	
	/***
	 * Renvoie les couts(critères) d'un chemin aléatoire pour le couple de matrices passé en paramètre
	 * @param ms un couple de matrices
	 * @throws IOException
	 */
	public String  testQualite(TwoMatrice ms) throws IOException {
		return objetAleatoire(ms).toString();
	}
	
	/***
	 * Enregistre dans un fichier les instances générées
	 * @param ms un couple de matrices
	 * @param n nombre d'instances genérées 
	 * @throws IOException 
	 */
	public void createFileRandomMultiObjectiveObject(TwoMatrice ms, int n, String path, String filename) throws IOException {
		//String path = "/home/m2miage/terbah/Documents/ADOM/fichiers/";
		//String path = "/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/";
		//String filename = "villesAleatoireMulti"+".txt";
		File f = new File(path+filename);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		for (int i = 0; i <= n; i++) {
			System.out.println(testQualite(ms) + "\n");
			writer.write(testQualite(ms) + "\n");
		}
	    writer.close();
	}
	


}
