package fil.adom.tp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import fil.adom.tp1.Glouton;
import fil.adom.tp1.Matrice;
import fil.adom.tp2.HillClimbing;

public class OrderBasedCrossover {
	
	private Chemin parent1;
	private Chemin parent2;
	private boolean isVisited = false;
	
	private Chemin enfant = new Chemin();
	
	
	
	
	
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

	public OrderBasedCrossover() {
		// TODO Auto-generated constructor stub
	}
	public void load() {
		int i = this.parent1.getChemin().length/2;
		this.enfant.setChemin(new Integer[this.parent1.getChemin().length]);
        Random r = new Random();
        List<Integer> cont = new ArrayList<>();
        int n = r.nextInt(i);
        for (int j=n; j<=n+i; j++) {
        	this.enfant.getChemin()[j] = this.parent1.getChemin()[j];
        	cont.add(this.parent1.getChemin()[j]);
        }
        for (Integer e : this.parent2.getChemin()) {
        	for (int ind = 0; ind< enfant.getChemin().length; ind++) {
        		if (enfant.getChemin()[ind] == null && !cont.contains(e)) {
        			this.enfant.getChemin()[ind] = e;
        			cont.add(e);
        		}
        	}
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderBasedCrossover other = (OrderBasedCrossover) obj;
		if (parent1 == null) {
			if (other.parent1 != null)
				return false;
		} else if (!parent1.equals(other.parent1))
			return false;
		if (parent2 == null) {
			if (other.parent2 != null)
				return false;
		} else if (!parent2.equals(other.parent2))
			return false;
		return true;
	}
	


	
	
	
	
	

}
