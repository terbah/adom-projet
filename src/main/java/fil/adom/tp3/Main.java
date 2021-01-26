package fil.adom.tp3;

import fil.adom.tp1.Matrice;

public class Main {
	
	public static void main(String[] args) {
		Evolutionnaire e = new Evolutionnaire();
		Population p = new Population();
		p.setMatrice(new Matrice("A"));
		e.setPopulation(p);
		e.load();
		e.getPopulation().initPopulationAlea(100);
		e.parcoursAll(20, 1.1, false);
		for (Chemin c : e.getPopulation().getChemins()) {
			System.out.println(c.getCout() + " " + c.getGeneration());
		}

	}
}
