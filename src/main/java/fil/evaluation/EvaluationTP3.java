package fil.evaluation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fil.adom.tp1.Glouton;
import fil.adom.tp1.Matrice;
import fil.adom.tp2.HillClimbing;
import fil.adom.tp3.Chemin;
import fil.adom.tp3.Evolutionnaire;
import fil.adom.tp3.Population;

public class EvaluationTP3 {

	private static String path = "C:\\Users\\aghil\\Documents\\M2MIAGE\\ADOM\\adom-projet\\Ressources\\fichiersGeneres\\tp3\\";
	private static String[] instances = { "A", "B", "C", "D", "E", "F" };
	private static int[] bests = { 10659, 9234, 9529, 9108, 8899, 8989 };
	
	public static void main(String[] args) throws IOException {
		algo(2, 20, 1.15, true, true, 100);
		algo(2, 20, 1.15, false, true, 100);
		algo(1, 20, 1.1, false, false, 100);
		algo(2, 20, 1.1, true, false, 100);
	}

	public static void algo(int n, int gen, double rapport, boolean alea, boolean naif, int taillePopInit) throws IOException {
		Evolutionnaire e = new Evolutionnaire();
		String filename = "";
		if (naif) filename += "naif";
		else filename += "memetique";
		if (alea) filename += "Alea.txt";
		else filename += "Heuristic.txt";
		File f = new File(path + filename);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		int j = 0;
		long startTime = System.nanoTime();
		for (Matrice matrice : Glouton.readAllFiles()) {
			Population p = new Population();
			p.setMatrice(matrice);
			if (alea) p.initPopulationAlea(taillePopInit);
			else p.initHeuristic(taillePopInit);
			e.setPopulation(p);
			e.load();
			int best = bests[j];
			for (int i = 1; i <= n; i++) {
				System.out.println(matrice.getInstance() + "	" + filename);
				if (naif) e.parcoursAll(gen, rapport, true);
				else e.parcoursAll(gen, rapport, false);
				for (Chemin c : e.getPopulation().getChemins()) {
					float r = (float) c.getCout() / best;
					writer.write(Float.toString(r) + "\n");
				}
			}
			j++;
		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println(filename + " " + duration / 1000000);
		writer.close();
	}

}
