package fil.adom.tp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fil.adom.tp1.Glouton;
import fil.adom.tp1.Matrice;

public class Population {

	private List<Chemin> chemins = new ArrayList<>();
	private Matrice matrice;


	public Population(List<Chemin> chemins) {
		super();
		this.chemins = chemins;
	}
	
	public void load() {
		try {
			this.matrice.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Population() {
		super();
	}

	public void initPopulationAlea(int n) {
		for (int i = 0; i < n; i++) {
			Integer[] ch = Glouton.villesAleatoire(100);
			Chemin chemin = new Chemin(ch);
			chemin.setCout(Glouton.valChemin(ch, matrice));
			chemin.setGeneration(0);
			if (!chemins.contains(chemin))
				chemins.add(chemin);
		}
	}

	public void initHeuristic(int n) {
		for (int i = 0; i < n; i++) {
			Integer[] ch = Glouton.villesAleatoire(100);
			List<Integer> list = Glouton.cheminOptimal(ch[0], new ArrayList<>(Arrays.asList(ch)), matrice);
			ch = list.toArray(new Integer[list.size()]);
			Chemin chemin = new Chemin(ch);
			chemin.setCout(Glouton.valChemin(ch, matrice));
			chemin.setGeneration(0);
			if (!chemins.contains(chemin))
				chemins.add(chemin);
		}
	}

	public List<Chemin> getChemins() {
		return chemins;
	}

	public void setChemins(List<Chemin> chemins) {
		this.chemins = chemins;
	}

	public Matrice getMatrice() {
		return matrice;
	}

	public void setMatrice(Matrice matrice) {
		this.matrice = matrice;
	}
}
