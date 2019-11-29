package fil.adom.tp4;

import java.util.Arrays;

/***
 * Classe representant un objet a 2 objectifs
 *
 */
public class MultiObjectiveObject {

	private int critere1;
	private int critere2;
	private boolean domine = false;
	private boolean visited = false;
	private Integer[] chemin;

	public MultiObjectiveObject() {

	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultiObjectiveObject other = (MultiObjectiveObject) obj;
		if (!Arrays.equals(chemin, other.chemin))
			return false;
		return true;
	}



	public MultiObjectiveObject(int critere1, int critere2) {
		super();
		this.critere1 = critere1;
		this.critere2 = critere2;
	}

	public int getCritere1() {
		return critere1;
	}

	public void setCritere1(int critere1) {
		this.critere1 = critere1;
	}

	public int getCritere2() {
		return critere2;
	}

	public void setCritere2(int critere2) {
		this.critere2 = critere2;
	}

	public String toString() {
		return this.critere1 + "	" + this.critere2;
	}

	public boolean isDomine() {
		return domine;
	}

	public void setDomine(boolean domine) {
		this.domine = domine;
	}
	
	public boolean domine(MultiObjectiveObject o) {
		return (o.getCritere1() > getCritere1() && o.getCritere2() > getCritere2());
	}

	public Integer[] getChemin() {
		return chemin;
	}

	public void setChemin(Integer[] chemin) {
		this.chemin = chemin;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

}
