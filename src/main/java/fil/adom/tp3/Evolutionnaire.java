package fil.adom.tp3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import fil.adom.tp1.Glouton;
import fil.adom.tp1.Matrice;
import fil.adom.tp2.HillClimbing;

public class Evolutionnaire {

	private Population population;

	public void load() {
		this.population.load();
	}

	public Evolutionnaire() {
	}

	public Evolutionnaire(Population population) {
		super();
		this.population = population;
	}

	public Population getPopulation() {
		return population;
	}

	public void setPopulation(Population population) {
		this.population = population;
	}

	/**
	 * 
	 * @param o les parents
	 * @param i, l'indice de la génération
	 * @param naif l'algorithme est-il naif 
	 */
	public void parcoursEvolMutation(OrderBasedCrossover o, int i, boolean naif) {
		o.load();
		o.getParent1().setVisited(true);
		o.getParent2().setVisited(true);
		Chemin e = o.getEnfant();
		e.setGeneration(i);
		if (naif)
			mutation(e);
		else
			e.setChemin(HillClimbing.meilleurVoisinAmeliorantTwoOpt(e.getChemin(), population.getMatrice()));
		e.setCout(Glouton.valChemin(e.getChemin(), population.getMatrice()));
		if (!population.getChemins().contains(e))
			population.getChemins().add(e);
	}

	/***
	 * 
	 * @param nbGeneration nombre de générations souhaité 
	 * @param rapport rapport de suppresion
	 * @param naif true si l'algorithme est naif
	 */
	public void parcoursAll(int nbGeneration, double rapport, boolean naif) {
		int i = 1;
		while (i <= nbGeneration) {
			List<OrderBasedCrossover> list = getParents();
			if (list.size() == 0)
				return;
			for (OrderBasedCrossover o : list) {
				parcoursEvolMutation(o, i, naif);
			}
			Collections.sort(population.getChemins());
			eliminerMauvais(rapport);
			i++;
		}
		int best = population.getChemins().get(0).getCout();
		population.getChemins().removeIf(o -> (o.getCout() > best * 1.05));
	}

	/***
	 * 
	 * @return une liste de parents couplés de qualité 
	 */
	public List<OrderBasedCrossover> getParents() {
		List<OrderBasedCrossover> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			for (int j = i + 1; j < population.getChemins().size() / 2; j++) {
				OrderBasedCrossover o = new OrderBasedCrossover(population.getChemins().get(i),
						population.getChemins().get(j));
				list.add(o);
			}

		}
		return list;
	}

	/**
	 * Elimines tous les chemins dont le cout est supérieur à rapport*cout du meilleur
	 * @param rapport
	 */
	public void eliminerMauvais(double rapport) {
		int best = population.getChemins().get(0).getCout();
		population.getChemins().removeIf(o -> (o.getCout() > best * rapport));
	}

	/**
	 * Opère une mutation de type swap au chemin passé en paramètre
	 * @param chemin
	 */
	public void mutation(Chemin chemin) {
		Random r = new Random();
		int i = 0;
		int j = 0;
		while (i == j) {
			i = r.nextInt(chemin.getChemin().length);
			j = r.nextInt(chemin.getChemin().length);
		}
		int vali = chemin.getChemin()[i];
		int valj = chemin.getChemin()[j];
		chemin.getChemin()[i] = valj;
		chemin.getChemin()[j] = vali;
	}
}
