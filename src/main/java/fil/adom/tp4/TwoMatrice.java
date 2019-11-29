package fil.adom.tp4;

import java.io.IOException;

import fil.adom.tp1.Matrice;

/***
 * Classe representant 2 matrices associees.
 *
 */
public class TwoMatrice {

	private Matrice matriceA;
	private Matrice matriceB;
	
	public TwoMatrice(String a, String b) {
		this.matriceA = new Matrice(a);
		this.matriceB = new Matrice(b);
	}
	
	public void load() throws IOException {
		this.matriceA.load();
		this.matriceB.load();
	}

	public Matrice getMatriceA() {
		return matriceA;
	}

	public void setMatriceA(Matrice matriceA) {
		this.matriceA = matriceA;
	}

	public Matrice getMatriceB() {
		return matriceB;
	}

	public void setMatriceB(Matrice matriceB) {
		this.matriceB = matriceB;
	}
	
	

}
