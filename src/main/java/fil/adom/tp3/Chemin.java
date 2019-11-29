package fil.adom.tp3;

import java.util.Arrays;

public class Chemin implements Comparable{
	
	private Integer[] chemin;
	private int cout;
	private boolean visited = false;
	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(chemin);
		return result;
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
		return this.getCout()- other.cout;
	}
	
	
	
	
	

}
