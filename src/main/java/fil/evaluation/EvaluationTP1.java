package fil.evaluation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fil.adom.tp1.Glouton;
import fil.adom.tp1.Matrice;

public class EvaluationTP1 {

	private static String[] instances = { "A", "B", "C", "D", "E", "F" };
	private static int[] bests = { 10659, 9234, 9529, 9108, 8899, 8989 };

	public static void main(String[] args) throws IOException {
		analyse_experimentale_villes(50);
		//analyse_experimentale_villesAleatoire(50);
	}
	
	/**
	 * Fonction qui execute n fois, le calcul de cout pour un chemin aléatoire, sur toutes les instances et écris le resultat dans un fichier
	 * @param n
	 * @throws IOException
	 */
	public static void analyse_experimentale_villesAleatoire(int n) throws IOException {
		//String path = "/home/m2miage/terbah/Documents/ADOM/fichiers/";
		String path = "/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/";
		String filename = "villesAleatoire"+".txt";
		File f = new File(path+filename);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {

				// son cout
				int cout = Glouton.cout_chemin_villesAleatoire(matrice);
				float rapport = (float) cout / best;
			    writer.write(Float.toString(rapport)+"\n");
				System.out.println(
						"instance " + instance + ", iteration " + i + ", cout = " + cout + ", rapport = " + rapport);
			}
			j++;
		}
	    writer.close();
	}

	/**
	 * Fonction qui execute n fois, le calcul de cout pour l'algo de l'heuristique constructive, sur toutes les instances et écris le resultat dans un fichier
	 * @param ville
	 * @param n
	 * @throws IOException
	 */
	public static void analyse_experimentale(int ville, int n) throws IOException {
		//String path = "/home/m2miage/terbah/Documents/ADOM/fichiers/";
		String path = "/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/";
		String filename = "ville"+ville+".txt";
		File f = new File(path+filename);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				// le chemin optimal
				List<Integer> chemin = Glouton.cheminOptimal(ville,
						new ArrayList<>(Arrays.asList(Glouton.villesAleatoire(100))), matrice);
				// son cout
				int cout = Glouton.valChemin(chemin.toArray(new Integer[chemin.size()]), matrice);
				float rapport = (float) cout / best;
			    writer.write(Float.toString(rapport)+"\n");
				System.out.println(
						"instance " + instance + ", iteration " + i + ", cout = " + cout + ", rapport = " + rapport);
			}
			j++;
		}
	    writer.close();
	}

	/***
	 * Fait varier le nombre de villes initiales et ecrit les résultats dans n fichiers.
	 * @param n nombre de villes à tester 
	 * @throws IOException
	 */
	public static void analyse_experimentale_villes(int n) throws IOException {
		for (int i = 1; i < 6; i++) {
			analyse_experimentale(i, n);
		}
	}
}
