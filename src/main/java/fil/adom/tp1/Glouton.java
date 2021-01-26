package fil.adom.tp1;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author LELA Malik, TERBAH Aghiles
 * 
 *         Classe contenant la fonction d'évaluation et la fonction de
 *         génération d'une solution aléatoire.
 * 
 *         Question 2.2 : Développer une fonction d’évaluation, capable de
 *         calculer la valeur à l’objectif f(π) d’une solution (permutation) π
 *         passée en paramètre.
 * 
 *         Question 2.3 : Générer une solution aléatoire (une permutation), et
 *         l’évaluer. Quelle est sa qualité par rapport `a la meilleure solution
 *         connue ?
 * 
 *         Question 3.1 : Développer l’heuristique constructive du plus proche
 *         voisin pour le TSP, en veillant à bien ranger dans des fichiers les
 *         solutions trouvées par vos programmes. Quel est la qualité de cette
 *         heuristique par rapport à une solution aléatoire ? Le choix de la
 *         ville initiale a-t-il un impact sur les résultats obtenus ?
 * 
 */
public class Glouton {

	private static String[] instances = {"A", "B", "C", "D", "E", "F"};
	
	public static void main(String[] args) throws IOException {
		test();
	}
	
	
	/**
	 * Fonction servant de main
	 * @throws IOException
	 */
	public static void test() throws IOException {
		Matrice matrice = new Matrice("A");
		matrice.load();
		Integer[] chemin = villesAleatoire(100);
		int cout = valChemin(chemin, matrice);
		int best = 10569;
		int ppv = plusProcheVoisin(1, chemin, matrice);
		System.out.println("Le cout de ce chemin aléatoire est : " + cout);

		System.out.println("Meilleure solution connue pour l'instance A : " + best);
		System.out.println("La permutation aléatoire est " + cout / best
				+ " fois moins optimale que la meilleure solution connue");
		System.out.println("Méthode du plus proche voisin");
		for (int i = 1; i < 101; i++) {
			List<Integer> voisins = new ArrayList<>(Arrays.asList(chemin));
			List<Integer> cheminop = cheminOptimal(i, voisins, matrice);
			int v = valChemin(cheminop.toArray(new Integer[cheminop.size()]), matrice);
			System.out.println("le cout en commençant par la ville " + i + " est : " + v);
		}
	}
	
	// --------------------------------------------

	/**
	 * Fonction qui lit tous les fichiers d'un repertoire et crée toutes les
	 * instances.
	 * 
	 * @throws IOException
	 */
	public static List<Matrice> readAllFiles() throws IOException {
		List<Matrice> list = new ArrayList<>();
		for (String s : instances) {
			list.add(new Matrice(s));
		}
		for (Matrice m : list) {
			m.load();
		}
		return list;
	}
	

	/**
	 * Fonction qui retourne la distance entre deux villes dans une matrice donnée
	 * @param villeA
	 * @param villeB
	 * @param matrice
	 * @return la distance entre la villeA et la villeB dans la matrice
	 */
	public static int distance(int villeA, int villeB, Matrice matrice) {
		int val = 0;
		val += matrice.getMatrice()[villeB - 1][villeA - 1];
		return val;
	}

	/**
	 * Fonction d'évaluation d'une permutation qui retounr le coup d'un chemin dans une matrice donnée
	 * 
	 * @param chemin
	 * @param matrice
	 * @return
	 */
	public static int valChemin(Integer[] chemin, Matrice matrice) {
		int val = distance(chemin[0], chemin[chemin.length - 1], matrice);
		for (int i = 0; i < (chemin.length - 1); i++) {
			int villeA = chemin[i];
			int villeB = chemin[i + 1];
			val += distance(villeA, villeB, matrice);
		}
		return val;
	}

	/**
	 * Fonction qui génère une solution aléatoire (villes rangées aléatoirement)
	 * @param dimension, taille du tableau
	 * @return une permutation aléatoire
	 */
	public static Integer[] villesAleatoire(int dimension) {
		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < dimension + 1; i++)
			list.add(i);
		Collections.shuffle(list);
		return list.toArray(new Integer[list.size()]);
	}

	/**
	 * Fonction qui retourne le cout d'une solution aléatoire.
	 * @param matrice
	 * @return le cout d'une permutation aléatoire dans la matrice
	 */
	public static int cout_chemin_villesAleatoire(Matrice matrice) {
		Integer[] chemin = villesAleatoire(100);
		int cout = valChemin(chemin, matrice);
		System.out.println("Le cout de la solution aléatoire :" + cout);
		return cout;
	}

	/**
	 * Fonction qui retourne le chemin optimal à partir : d'une ville de départ, une liste de villes et une matrice de couts
	 * @param init    la ville de départ
	 * @param voisins toutes les villes (dans notre exemple 1à100)
	 * @param matrice matrice contenant les distances entre des villes
	 * @return Fonction qui retourne le chemin optimal pour une matrice de distance
	 *         donnée (q3.1)
	 */
	public static List<Integer> cheminOptimal(Integer init, List<Integer> voisins, Matrice matrice) {
		List<Integer> cheminop = new ArrayList<Integer>();
		int sommet = init;
		if (voisins.contains(init))
			voisins.remove(init);
		cheminop.add(init);
		int t = voisins.size();
		while (t > 1) {
			// On passe en liste des voisins, tous les voisins restants en enlevant ceux par
			// lesquels on est déjà passé
			int ppv = plusProcheVoisin(sommet, voisins.toArray(new Integer[voisins.size()]), matrice);
			sommet = ppv;
			cheminop.add(ppv);
			voisins.remove(new Integer(ppv));
			t--;
		}
		return cheminop;
	}

	/**
	 * Fonction qui retourne le voisin la ville la plus proche en terme de cout 
	 * @param init,    le sommet initial
	 * @param voisins, les destinations potentielles
	 * @param matrice, la matrice des couts
	 * @return Parmi les voisins retourne le voisin le plus proche du sommet initial
	 *         donné
	 */
	public static int plusProcheVoisin(Integer init, Integer[] voisins, Matrice matrice) {
		int min = voisins[0];
		for (Integer i : voisins) {
			if ((i != init) && matrice.getMatrice()[init - 1][i - 1] < matrice.getMatrice()[init - 1][min - 1])
				min = i;
		}
		return min;
	}
	
	public static Integer[] cheminAleatoireOptimal(Matrice matrice) {
		Integer[] ch = Glouton.villesAleatoire(100);
		List<Integer> list = Glouton.cheminOptimal(ch[0], new ArrayList<>(Arrays.asList(ch)), matrice);
		ch = list.toArray(new Integer[list.size()]);
		return ch;
	}

}
