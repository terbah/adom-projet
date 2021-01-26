package fil.adom.tp2;

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

	private Integer[] listVilles;
	private Matrice matrice;

	public HillClimbing() throws IOException {
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
	public static Integer[] meilleurVoisinAmeliorantSwap(Integer[] villes, Matrice matrice) {
		Integer[] opt = new Integer[villes.length]; // nouvelle liste de villes optimisée
		opt = villes.clone(); // on copie les listes de villes
		int coutinit = Glouton.valChemin(villes, matrice);
		int best = coutinit;
		int changes = 0;
		int bestGain = 0;
		for (int i = 0; i < villes.length - 1; i++) {
			for (int j = i + 1; j < villes.length; j++) {
				int gain = gainSwap(i, j, villes, matrice);
				if (gain > bestGain) {
					swap(i, j, villes);
					int cx = coutinit - gain;
					changes++;
					best = cx;
					bestGain = gain;
					opt = villes.clone();
					swap(i, j, villes);
				}
			}
			if (changes != 0)
				break;
		}
		if (changes == 0) {
			return opt;
		} else {
			return meilleurVoisinAmeliorantSwap(opt, matrice);
		}
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
		for (int i = 0; i < villes.length - 1; i++) {
			for (int j = i + 1; j < villes.length; j++) {
				int gain = gainSwap(i, j, villes, matrice);
				if (gain > 0){
					swap(i, j, villes);
					return premierVoisinAmeliorantSwap(villes, matrice);
				}
			}
		}
		return villes;
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
		int c1 = 0;
		int c2 = 0;
		if (j == i+1) {
			c1 = (matrice.getMatrice()[chemin[ix] - 1][chemin[i] - 1]+
					+ matrice.getMatrice()[chemin[j] - 1][chemin[jp] - 1] );
			c2 = (matrice.getMatrice()[chemin[ix] - 1][chemin[j] - 1]
					+ matrice.getMatrice()[chemin[i] - 1][chemin[jp] - 1]);
		}

		else if ((i == 0 && j==chemin.length - 1)) {
			c1 = (matrice.getMatrice()[chemin[i] - 1][chemin[i + 1] - 1]
					+ matrice.getMatrice()[chemin[j - 1] - 1][chemin[j] - 1]);
			c2 = (matrice.getMatrice()[chemin[j] - 1][chemin[i + 1] - 1]
					+ matrice.getMatrice()[chemin[j - 1] - 1][chemin[i] - 1]);
		} else {
			c1 = (matrice.getMatrice()[chemin[ix] - 1][chemin[i] - 1]
					+ matrice.getMatrice()[chemin[i] - 1][chemin[i+1] - 1]
					+ matrice.getMatrice()[chemin[j-1] - 1][chemin[j] - 1]
					+ matrice.getMatrice()[chemin[j] - 1][chemin[jp] - 1] );
			
			c2 = (matrice.getMatrice()[chemin[ix] - 1][chemin[j] - 1]
					+ matrice.getMatrice()[chemin[j] - 1][chemin[i+1] - 1]
					+ matrice.getMatrice()[chemin[j-1] - 1][chemin[i] - 1]
			+ matrice.getMatrice()[chemin[i] - 1][chemin[jp] - 1]);

		}
		int cout = c1 - c2;
		return cout;
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
		Integer[] opt = new Integer[villes.length]; 
		int best = 0;
		int changes = 0;
		for (int i = 0; i < villes.length - 2; i++) {
			for (int j = i + 3; j < villes.length; j++) {
				int gain = gain(i, j, villes, matrice);
				if (gain > best) {
					opt = oneTourTwoOpt(villes, i, j);
					changes++;
					best = gain;
				}
			}
			if (changes != 0)
				break;
		}
		if (changes == 0) {
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
		return villes;
	}

	// ------------------------------------------------------------

	public Integer[] getListVilles() {
		return listVilles;
	}


	public void setListVilles(Integer[] listVilles) {
		this.listVilles = listVilles;
	}


	public Matrice getMatrice() {
		return matrice;
	}


	public void setMatrice(Matrice matrice) {
		this.matrice = matrice;
	}


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
