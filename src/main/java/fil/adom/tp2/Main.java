package fil.adom.tp2;

import java.io.IOException;

import fil.adom.tp1.Glouton;
import fil.adom.tp1.Matrice;

public class Main {

	public static void main(String[] args) throws IOException {
		HillClimbing h = new HillClimbing();
		Matrice m = new Matrice("A");
		m.load();
		h.setMatrice(m);
		
		// initialisation aleatoire
		h.initAlea();

		//h.intiHeuristic();
		
		Integer[] chemin = HillClimbing.meilleurVoisinAmeliorantTwoOpt(h.getListVilles(), h.getMatrice());
		System.out.println("Le cout de ce chemin : "+ Glouton.valChemin(chemin, h.getMatrice()));
		HillClimbing.premierVoisinAmeliorantTwoOpt(h.getListVilles(), h.getMatrice());
		HillClimbing.meilleurVoisinAmeliorantSwap(h.getListVilles(), h.getMatrice());
		HillClimbing.premierVoisinAmeliorantSwap(h.getListVilles(), h.getMatrice());

	}
}
