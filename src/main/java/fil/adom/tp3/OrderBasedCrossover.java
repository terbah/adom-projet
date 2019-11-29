package fil.adom.tp3;

import java.util.Arrays;
import java.util.Random;

public class OrderBasedCrossover {
	
	private Chemin parent1;
	private Chemin parent2;
	private boolean isVisited = false;
	
	private Chemin enfant;
	
	
	
	
	
	public OrderBasedCrossover(Chemin enfant) {
		super();
		this.enfant = enfant;
	}
	public OrderBasedCrossover(Chemin parent1, Chemin parent2) {
		super();
		this.parent1 = parent1;
		this.parent2 = parent2;
	}
	public OrderBasedCrossover(Chemin parent1, Chemin parent2, Chemin enfant) {
		super();
		this.parent1 = parent1;
		this.parent2 = parent2;
		this.enfant = enfant;
	}

	public void load() {
		int i = this.parent1.getChemin().length/2;
        Random r = new Random();
        int n = r.nextInt(i);
        for (int j=n; j<=n+i; j++) {
        	this.enfant.getChemin()[j] = this.parent1.getChemin()[j];
        }
        for (int indice = 0; indice < n; indice++) {
        	this.enfant.getChemin()[indice] = this.parent2.getChemin()[indice];
        }
        for (int indice2 = n+i; indice2<this.parent2.getChemin().length; indice2++) {
        	this.enfant.getChemin()[indice2] = this.parent2.getChemin()[indice2];
        }
        
	}
	public boolean isVisited() {
		return isVisited;
	}
	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	public Chemin getParent1() {
		return parent1;
	}
	public void setParent1(Chemin parent1) {
		this.parent1 = parent1;
	}
	public Chemin getParent2() {
		return parent2;
	}
	public void setParent2(Chemin parent2) {
		this.parent2 = parent2;
	}
	public Chemin getEnfant() {
		return enfant;
	}
	public void setEnfant(Chemin enfant) {
		this.enfant = enfant;
	}


	
	
	
	
	

}
