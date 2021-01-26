package fil.adom.tp3;

import java.util.Arrays;

public class Chemin implements Comparable{
	
	private Integer[] chemin;
	private int cout;
	private boolean visited = false;
	private int generation;
	
	public Chemin() {
		
	}
	
	public Chemin(Integer[] chemin) {
		super();
		this.chemin = chemin;
	}

	public Integer[] getChemin() {
		return chemin;
	}

	public void setChemin(Integer[] chemin) {
		this.chemin = chemin;
	}

	public int getCout() {
		return cout;
	}

	public void setCout(int cout) {
		this.cout = cout;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chemin other = (Chemin) obj;
		if (!Arrays.equals(chemin, other.chemin))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object obj) {
		Chemin other = (Chemin) obj;
		//return other.cout - this.getCout();
		return this.getCout()- other.cout;
	}

	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}
	
	
	
	
	

}
