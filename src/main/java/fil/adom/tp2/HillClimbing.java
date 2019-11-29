package fil.adom.tp2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fil.adom.tp1.Glouton;
import fil.adom.tp1.Matrice;

/**
 * 
 * @author LELA Malik & TERBAH Aghiles
 * 
 *         Question 2 : Développer deux types de voisinage : le swap (échanger
 *         la position de deux villes) et le two-opt (supprimer deux arrêtes et
 *         reconnecter les deux sous-tours obtenus). Quelle est la taille du
 *         voisinage (le nombre de solutions voisines par solution) pour chacun
 *         des ces opérateurs ? Comparer les résultats obtenus par votre
 *         algorithme de hill-climbing en fonction du voisinage choisi.
 * 
 *         Question 3 : Développer deux types d’initialisation : démarrer d’une
 *         solution aléatoire, ou démarrer d’une solution obtenue à l’aide d’une
 *         heuristique constructive telle que développée dans le TP1. Comparer
 *         les résultats obtenus par votre algorithme de hill-climbing en
 *         fonction du choix de la solution initiale.
 * 
 *         Question 4 : Développer deux types de stratégie de mouvement :
 *         meilleur voisin améliorant, ou premier voisin améliorant. Comparer
 *         les résultats obtenus par votre algorithme de hill- climbing en
 *         fonction du choix de la stratégie du mouvement.
 *
 */
public class HillClimbing {

	// private static String location =
	// "/home/m2miage/terbah/Documents/ADOM/sources";
	private static String location = "/home/m2miage/lela/Documents/S3/ADOM/TPs/Workspace_Projet_Adom/adom-projet/Ressources/InstancesRandomTSP";

	private Integer[] listVilles;
	private Integer[] villes;
	private Matrice matrice;

	public HillClimbing() throws IOException {
		this.matrice = new Matrice("A");
		this.matrice.load();
		villes = new Integer[] { 1, 2, 3, 4, 5, 6 };
	}

	public static void main(String[] args) throws IOException {
		HillClimbing h = new HillClimbing();
		h.initAlea();

		/*
		meilleurVoisinAmeliorantTwoOpt(h.listVilles, h.matrice);
		premierVoisinAmeliorantTwoOpt(h.listVilles, h.matrice);
		meilleurVoisinAmeliorantSwap(h.listVilles, h.matrice);
		premierVoisinAmeliorantSwap(h.listVilles, h.matrice);
		*/

		  int gain = gainSwap(1, 4, h.villes, h.matrice);
		  System.out.println("gain " + gain);
		  int couAct = Glouton.valChemin(h.villes, h.matrice);
		  System.out.println("cout init " + couAct);
		  swap(1, 4, h.villes);
		  int cx = couAct - gain;
		  System.out.println("cout après gain " + cx); 
		  int reel = Glouton.valChemin(h.villes, h.matrice);
		  System.out.println("reel" + reel);
		 

	}

	// ------------------------------------------------------------
	// QUESTION 4 - SWAP
	// ------------------------------------------------------------

	/**
	 * Fonction retournant le chemin avec le meilleur cout d'après l'algo swap
	 * (question 4)
	 * 
	 * @param villes
	 * @param matrice
	 * @return meilleur chemin grace au swap
	 */
	public static Integer[] meilleurVoisinAmeliorantSwap2(Integer[] villes, Matrice matrice) {
		Integer[] opt = new Integer[villes.length]; // nouvelle liste de villes optimisée
		opt = villes.clone(); // on copie les listes de villes
		int coutAct = Glouton.valChemin(villes, matrice);
		int best = coutAct;
		// int i = 0;
		int changes = 0;
		for (int i = 0; i < villes.length - 1; i++) {
			for (int j = i + 1; j < villes.length; j++) {
				int vi = villes[i];
				int vj = villes[j];
				villes[i] = vj;
				villes[j] = vi;
				coutAct = Glouton.valChemin(villes, matrice);
				if (coutAct < best) {
					changes++;
					best = coutAct;
					opt = villes.clone();
				}
				villes[j] = vj;
				villes[i] = vi;
			}
			if (changes != 0)
				break;
		}
		if (changes == 0) {
			System.out.println("meilleurVoisinAmeliorantSwap : " + best);
			return opt;
		} else {
			return meilleurVoisinAmeliorantSwap2(opt, matrice);
		}
	}

	public static Integer[] meilleurVoisinAmeliorantSwap(Integer[] villes, Matrice matrice) {
		Integer[] opt = new Integer[villes.length]; // nouvelle liste de villes optimisée
		opt = villes.clone(); // on copie les listes de villes
		int coutinit = Glouton.valChemin(villes, matrice);
		int best = coutinit;
		System.out.println("debut : " + coutinit);
		// int i = 0;
		int changes = 0;
		int bestGain = 0;
		for (int i = 0; i < villes.length - 1; i++) {
			for (int j = i + 1; j < villes.length; j++) {
				int gain = gainSwap(i, j, villes, matrice);
				System.out.println("gain : " + gain);
				System.out.println("best gain : " + bestGain);
				if (gain > bestGain) {
					swap(i, j, villes);
					int cx = coutinit - gain;
					System.out.println("coutact = " + cx);
					changes++;
					best = cx;
					bestGain = gain;
					opt = villes.clone();
					System.out.println("opt = " + Glouton.valChemin(opt, matrice));
					swap(i, j, villes);
				}
			}
			if (changes != 0)
				break;
		}
		if (changes == 0) {
			System.out.println("MeilleurVoisinAmeliorantSwap : " + best);
			return opt;
		} else {
			System.out.println("meilleurVoisinAmeliorantSwap : " + best);
			return opt;
			// return meilleurVoisinAmeliorantSwap(opt, matrice);
		}
	}

	public static void swap(int i, int j, Integer[] villes) {
		int vi = villes[i];
		int vj = villes[j];
		villes[i] = vj;
		villes[j] = vi;
	}

	public static int gainSwap(int i, int j, Integer[] chemin, Matrice matrice) {
		int ix = i - 1;
		int jp = j + 1;
		if (i == 0)
			ix = chemin.length - 1;
		if (j == chemin.length - 1)
			jp = 0;
		/*
		 * System.out.println("i = " +i + " j ="+j+ " jp="+jp + " ix =" + ix);
		 * System.out.println("chemin[j] = " + chemin[j]);
		 * System.out.println("chemin[jp] = " +chemin[jp]);
		 */
		int c1 = 0;
		int c2 = 0;

		if (i == 0 && j == chemin.length - 1) {
			/*
			 * ch[i, i+1] + ch [j-1, j] - ch[j , i+1] + ch [j-1, i];
			 */
			c1 = (matrice.getMatrice()[chemin[i] - 1][chemin[i + 1] - 1]
					+ matrice.getMatrice()[chemin[j - 1] - 1][chemin[j] - 1]);
			c2 = (matrice.getMatrice()[chemin[j] - 1][chemin[i + 1] - 1]
					+ matrice.getMatrice()[chemin[j - 1] - 1][chemin[i] - 1]);
		} else {
			//c1 = matrice.getMatrice()[chemin[i-1]]
			c1 = (matrice.getMatrice()[chemin[ix] - 1][chemin[i] - 1]
					+ matrice.getMatrice()[chemin[j] - 1][chemin[jp] - 1]);
			c2 = (matrice.getMatrice()[chemin[ix] - 1][chemin[j] - 1]
					+ matrice.getMatrice()[chemin[i] - 1][chemin[jp] - 1]);
		}
		int cout = c1 - c2;
		// System.out.println("cout = " + cout);
		return cout;
	}

	/**
	 * Fonction retournant le premier voisin ameliorant avec la methode du
	 * swap(question 4)
	 * 
	 * @param villes
	 * @param matrice
	 * @return premier voisin améliorant avec le swap
	 */
	public static Integer[] premierVoisinAmeliorantSwap(Integer[] villes, Matrice matrice) {
		Integer[] opt = new Integer[villes.length];
		opt = villes.clone();
		int init = Glouton.valChemin(villes, matrice);
		int coutAct = 0;
		for (int i = 0; i < villes.length - 1; i++) {
			for (int j = i + 1; j < villes.length; j++) {
				opt[j] = villes[i];
				opt[i] = villes[j];
				coutAct = Glouton.valChemin(opt, matrice);
				if (coutAct < init) {
					return premierVoisinAmeliorantSwap(opt, matrice);
				} else {
					opt[j] = villes[j];
					opt[i] = villes[i];
				}
			}
		}
		System.out.println("-premierVoisinAmeliorantSwap- Cout Optimal = " + coutAct);
		return villes;
	}

	// ------------------------------------------------------------
	// QUESTION 4 - TWO-OPT
	// ------------------------------------------------------------

	/***
	 * Fonction calculant le gain entre la solution de départ (avant application du
	 * two opt) et la nouvelle solution (apres application du two opt)
	 * 
	 * @param i       indice du premier sommet de la premiere arrête
	 * @param j       indice du dernier sommet de la derniere arrête
	 * @param villes
	 * @param matrice
	 * @return la différence de cout entre la solution initiale et la nouvelle
	 *         combinaison
	 */
	public static int gain(int i, int j, Integer[] villes, Matrice matrice) {
		int c1 = (matrice.getMatrice()[villes[i] - 1][villes[i + 1] - 1]
				+ matrice.getMatrice()[villes[j] - 1][villes[j - 1] - 1]);
		int c2 = (matrice.getMatrice()[villes[i + 1] - 1][villes[j] - 1]
				+ matrice.getMatrice()[villes[j - 1] - 1][villes[i] - 1]);
		int cout = c1 - c2;
		return cout;
	}

	/**
	 * Fonction qui fait une seule fois le twoOpt pour des arretes dont les indices
	 * de debut et de fin sont donnés en paramètre
	 * 
	 * @param tour
	 * @param i
	 * @param j
	 * @return la nouvelle combinaison avec l'algorithme du two-opt
	 */
	public static Integer[] oneTourTwoOpt(Integer[] tour, int i, int j) {
		Integer[] opt = new Integer[tour.length];
		opt = tour.clone();
		int d = Math.abs(j - i);
		d = (d + 1) / 2;
		for (int k = 1; k < d; k++) {
			opt[i + k] = tour[j - k];
			opt[j - k] = tour[i + k];
		}
		// System.out.println(tableToString(opt));
		return opt;
	}

	/**
	 * Fonction retournant le chemin avec le meilleur cout d'après l'algo two-opt
	 * (question 4)
	 * 
	 * @param villes
	 * @param matrice
	 * @return meilleur chemin grace au two-opt
	 */
	public static Integer[] meilleurVoisinAmeliorantTwoOpt(Integer[] villes, Matrice matrice) {
		Integer[] opt = new Integer[villes.length]; // nouvelle liste de villes optimisée
		int coutSolutionInitiale = Glouton.valChemin(villes, matrice);
		// System.out.println("coutSolutionInitiale = " + coutSolutionInitiale);
		int best = 0;
		int changes = 0;
		for (int i = 0; i < villes.length - 2; i++) {
			for (int j = i + 3; j < villes.length; j++) {
				int gain = gain(i, j, villes, matrice);
				if (gain > best) {
					opt = oneTourTwoOpt(villes, i, j);
					// System.out.println("gain = " + gain);
					changes++;
					best = gain;
				}
			}
			if (changes != 0)
				break;
		}
		if (changes == 0) {
			System.out.println("cout optimal = " + (coutSolutionInitiale - best));
			return villes;
		} else
			return meilleurVoisinAmeliorantTwoOpt(opt, matrice);
	}

	/**
	 * Fonction retournant le chemin avec le meilleur cout d'après l'algo two-opt
	 * (question 4)
	 * 
	 * @param villes
	 * @param matrice
	 * @return meilleur chemin grace au two-opt
	 */
	public static Integer[] premierVoisinAmeliorantTwoOpt(Integer[] villes, Matrice matrice) {
		int coutSolutionInitiale = Glouton.valChemin(villes, matrice);
		// System.out.println("coutSolutionInitiale = " + coutSolutionInitiale);
		Integer[] nouvelleCombinaison;

		for (int i = 0; i < villes.length - 2; i++) {
			for (int j = i + 3; j < villes.length; j++) {
				int gain = gain(i, j, villes, matrice);
				if (gain > 0) {
					nouvelleCombinaison = oneTourTwoOpt(villes, i, j);
					return premierVoisinAmeliorantTwoOpt(nouvelleCombinaison, matrice);
				}
			}
		}
		System.out.println("cout optimal = " + coutSolutionInitiale);
		return villes;
	}

	// ------------------------------------------------------------

	/**
	 * Mélange aléatoirement 100 entiers correspondant des villes dans notre liste
	 * de villes.
	 */
	public void initAlea() {
		this.listVilles = Glouton.villesAleatoire(100);
	}

	/**
	 * Modifie la liste de villes pour faire en sorte que le chemin soit optimal
	 * (selon l'algorithme l’heuristique constructive du plus proche voisin).
	 */
	public void intiHeuristic() {
		List<Integer> list = Glouton.cheminOptimal(listVilles[0], new ArrayList<>(Arrays.asList(listVilles)), matrice);
		this.listVilles = list.toArray(new Integer[list.size()]);
	}

	/**
	 * Affiche un tableau d'entiers
	 * 
	 * @param table
	 * @return
	 */
	public static String tableToString(Integer[] table) {
		String res = "";
		for (Integer i : table) {
			res += i + " - ";
		}
		return res;
	}

}
