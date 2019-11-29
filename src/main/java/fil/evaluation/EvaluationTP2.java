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
import fil.adom.tp2.HillClimbing;

public class EvaluationTP2 {

	private static String[] instances = { "A", "B", "C", "D", "E", "F" };
	private static int[] bests = { 10659, 9234, 9529, 9108, 8899, 8989 };
	private static String path = "/home/m2miage/terbah/Documents/ADOM/fichiers/";
	//private static String path = "/home/m2miage/lela/Documents/S3/ADOM/TPs/rapports/";

	public static void main(String[] args) throws IOException {

		// SWAP

		analyse_experimentale_meilleurVoisinAmeliorantSwap_Alea(50);
		analyse_experimentale_meilleurVoisinAmeliorantSwap_HeurConst(50);

		analyse_experimentale_premierVoisinAmeliorantSwap_Alea(50);
		analyse_experimentale_premierVoisinAmeliorantSwap_HeurConst(50);

		// TWO-OPT

		analyse_experimentale_meilleurVoisinAmeliorantTwoOpt_Alea(50);
		analyse_experimentale_meilleurVoisinAmeliorantTwoOpt_HeurConst(50);

		analyse_experimentale_premierVoisinAmeliorantTwoOpt_Alea(50);
		analyse_experimentale_premierVoisinAmeliorantTwoOpt_HeurConst(50);

	}

	// ---------------------------------------
	// SWAP
	// ---------------------------------------

	public static void analyse_experimentale_meilleurVoisinAmeliorantSwap_Alea(int n) throws IOException {
		String filename = "meilleurVoisinAmeliorantSwap_Alea" + ".txt";
		File f = new File(path + filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		long startTime = System.nanoTime();
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				// le chemin optimal
				Integer[] chemin = HillClimbing.meilleurVoisinAmeliorantSwap(Glouton.villesAleatoire(100), matrice);

				// son cout
				int cout = Glouton.valChemin(chemin, matrice);
				float rapport = (float) cout / best;
				writer.write(Float.toString(rapport) + "\n");
			}
			j++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("meilleurVoisinAmeliorantSwap_Alea " + duration/1000000);
		writer.close();

	}

	public static void analyse_experimentale_meilleurVoisinAmeliorantSwap_HeurConst(int n) throws IOException {

		String filename = "meilleurVoisinAmeliorantSwap_HeurConst" + ".txt";
		File f = new File(path + filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		long startTime = System.nanoTime();
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				// le chemin optimal
				List<Integer> cheminDepart = Glouton.cheminOptimal(1,
						new ArrayList<>(Arrays.asList(Glouton.villesAleatoire(100))), matrice);

				Integer[] chemin = HillClimbing
						.meilleurVoisinAmeliorantSwap(cheminDepart.toArray(new Integer[cheminDepart.size()]), matrice);

				// son cout
				int cout = Glouton.valChemin(chemin, matrice);
				float rapport = (float) cout / best;
				writer.write(Float.toString(rapport) + "\n");
			}
			j++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("meilleurVoisinAmeliorantSwap_HeurConst " + duration/1000000);
		writer.close();

	}

	public static void analyse_experimentale_premierVoisinAmeliorantSwap_Alea(int n) throws IOException {
		String filename = "premierVoisinAmeliorantSwap_Alea" + ".txt";
		File f = new File(path + filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		long startTime = System.nanoTime();
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				// le chemin optimal
				Integer[] chemin = HillClimbing.premierVoisinAmeliorantSwap(Glouton.villesAleatoire(100), matrice);

				// son cout
				int cout = Glouton.valChemin(chemin, matrice);
				float rapport = (float) cout / best;
				writer.write(Float.toString(rapport) + "\n");
			}
			j++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("premierVoisinAmeliorantSwap_Alea " + duration/1000000);
		writer.close();

	}

	public static void analyse_experimentale_premierVoisinAmeliorantSwap_HeurConst(int n) throws IOException {

		String filename = "premierVoisinAmeliorantSwap_HeurConst" + ".txt";
		File f = new File(path + filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		long startTime = System.nanoTime();
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				// le chemin optimal
				List<Integer> cheminDepart = Glouton.cheminOptimal(1,
						new ArrayList<>(Arrays.asList(Glouton.villesAleatoire(100))), matrice);

				Integer[] chemin = HillClimbing
						.premierVoisinAmeliorantSwap(cheminDepart.toArray(new Integer[cheminDepart.size()]), matrice);
				// son cout
				int cout = Glouton.valChemin(chemin, matrice);
				float rapport = (float) cout / best;
				writer.write(Float.toString(rapport) + "\n");
			}
			j++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("premierVoisinAmeliorantSwap_HeurConst " + duration/1000000);
		writer.close();
	}

	// ---------------------------------------
	// TWO-OPT
	// ---------------------------------------

	public static void analyse_experimentale_meilleurVoisinAmeliorantTwoOpt_Alea(int n) throws IOException {
		String filename = "meilleurVoisinAmeliorantTwoOpt_Alea.txt";
		File f = new File(path + filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		long startTime = System.nanoTime();
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				// le chemin optimal
				Integer[] chemin = HillClimbing.meilleurVoisinAmeliorantTwoOpt(Glouton.villesAleatoire(100), matrice);
				int cout = Glouton.valChemin(chemin, matrice);
				float rapport = (float) cout / best;
				writer.write(Float.toString(rapport) + "\n");
			}
			j++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("meilleurVoisinAmeliorantTwoOpt_Alea " + duration/1000000);
		writer.close();
	}

	public static void analyse_experimentale_meilleurVoisinAmeliorantTwoOpt_HeurConst(int n) throws IOException {
		String filename = "meilleurVoisinAmeliorantTwoOpt_HeurConst.txt";
		File f = new File(path + filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		long startTime = System.nanoTime();
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				// le chemin optimal
				List<Integer> cheminDepart = Glouton.cheminOptimal(1,
						new ArrayList<>(Arrays.asList(Glouton.villesAleatoire(100))), matrice);

				Integer[] chemin = HillClimbing.meilleurVoisinAmeliorantTwoOpt(
						cheminDepart.toArray(new Integer[cheminDepart.size()]), matrice);
				int cout = Glouton.valChemin(chemin, matrice);
				float rapport = (float) cout / best;
				writer.write(Float.toString(rapport) + "\n");
			}
			j++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("meilleurVoisinAmeliorantTwoOpt_HeurConst " + duration/1000000);
		writer.close();
	}

	public static void analyse_experimentale_premierVoisinAmeliorantTwoOpt_Alea(int n) throws IOException {
		String filename = "premierVoisinAmeliorantTwoOpt_Alea.txt";
		File f = new File(path + filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		long startTime = System.nanoTime();
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				// le chemin optimal
				Integer[] chemin = HillClimbing.premierVoisinAmeliorantTwoOpt(Glouton.villesAleatoire(100), matrice);
				int cout = Glouton.valChemin(chemin, matrice);
				float rapport = (float) cout / best;
				writer.write(Float.toString(rapport) + "\n");
			}
			j++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("premierVoisinAmeliorantTwoOpt_Alea " + duration/1000000);
		writer.close();
	}

	public static void analyse_experimentale_premierVoisinAmeliorantTwoOpt_HeurConst(int n) throws IOException {
		String filename = "premierVoisinAmeliorantTwoOpt_HeurConst.txt";
		File f = new File(path + filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		long startTime = System.nanoTime();
		for (Matrice matrice : Glouton.readAllFiles()) {
			String instance = instances[j];
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				// le chemin optimal
				List<Integer> cheminDepart = Glouton.cheminOptimal(1,
						new ArrayList<>(Arrays.asList(Glouton.villesAleatoire(100))), matrice);

				Integer[] chemin = HillClimbing
						.premierVoisinAmeliorantTwoOpt(cheminDepart.toArray(new Integer[cheminDepart.size()]), matrice);
				int cout = Glouton.valChemin(chemin, matrice);
				float rapport = (float) cout / best;
				writer.write(Float.toString(rapport) + "\n");
			}
			j++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("premierVoisinAmeliorantTwoOpt_HeurConst " + duration/1000000);
		writer.close();
	}

}
