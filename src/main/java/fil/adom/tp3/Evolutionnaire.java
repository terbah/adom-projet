package fil.adom.tp3;

import java.util.List;
import java.util.Random;

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

	
	
	public void parcoursEvolMutation(Chemin p1, Chemin p2) {
		p1.setVisited(true);
		p2.setVisited(true);
		OrderBasedCrossover o = new OrderBasedCrossover(p1, p2);
		o.load();
		Chemin e = o.getEnfant();
		swap(e.getChemin());
		population.getChemins().add(e);
	}
	
	
	public void parcoursEvolAll() {
		
	}






	public void swap(Integer[]chemin) {
		Random r = new Random();
		int i = 0;
		int j = 0;
		while(i == j) {
			i = r.nextInt(chemin.length);
			j = r.nextInt(chemin.length);
		}
		int vali = chemin[i];
		int valj = chemin[j];
		chemin[i] = valj;
		chemin[j] = vali;

	}
	
	
}
