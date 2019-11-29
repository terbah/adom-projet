package fil.adom.tp4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fil.adom.tp1.Glouton;

/***
 * Classe representant un filtre on Line
 *
 */
public class FiltreOnLine extends Filtre{
	
	private int nbComp = 0;
	
	private List<MultiObjectiveObject> ensembleSolAB = new ArrayList<>();
	private List<MultiObjectiveObject> ensembleSolCD = new ArrayList<>();
	private List<MultiObjectiveObject> ensembleSolEF = new ArrayList<>();


	public FiltreOnLine() {
		super();
	}


	public void loadList(int nb) throws IOException {
		instanceinit.add(listInstance(nb, matricesAB));
		instanceinit.add(listInstance(nb, matricesCD));
		instanceinit.add(listInstance(nb, matricesEF));
	}
	

	/***
	 * 
	 * @param nbInstances nombre d'instances à traiter
	 * @param ms Le couple d'instances concerné
	 * @return les solutions non domines parmi les nbInstances
	 * @throws IOException
	 */
	public List<MultiObjectiveObject> listInstance(int nbInstances, TwoMatrice ms) throws IOException {
		List<MultiObjectiveObject> list = new ArrayList<>();
		list.add(createObject(Glouton.villesAleatoire(100), ms));
		for (int in = 0; in < nbInstances; in++) {
			MultiObjectiveObject ob = createObject(Glouton.villesAleatoire(100), ms);
			switch(ms.getMatriceA().getInstance()+ms.getMatriceB().getInstance()){
			case "AB":
				ensembleSolAB.add(ob);
				break;
			case "CD":
				ensembleSolCD.add(ob);
				break;
			case "EF":
				ensembleSolEF.add(ob);
				break;	
			}
			testEntree(ob, list);
			supprimerDomines(list);
		}
		//System.out.println("nb obj non d : " + list.size());
		return list;
	}
	
	

	public List<MultiObjectiveObject> getEnsembleSolAB() {
		return ensembleSolAB;
	}


	public void setEnsembleSolAB(List<MultiObjectiveObject> ensembleSolAB) {
		this.ensembleSolAB = ensembleSolAB;
	}


	public List<MultiObjectiveObject> getEnsembleSolCD() {
		return ensembleSolCD;
	}


	public void setEnsembleSolCD(List<MultiObjectiveObject> ensembleSolCD) {
		this.ensembleSolCD = ensembleSolCD;
	}


	public List<MultiObjectiveObject> getEnsembleSolEF() {
		return ensembleSolEF;
	}


	public void setEnsembleSolEF(List<MultiObjectiveObject> ensembleSolEF) {
		this.ensembleSolEF = ensembleSolEF;
	}


	/***
	 * Compare l'objet ob avec chaque element de la liste pendant le parcours.
	 * 			-> L'objet est ajouté à la liste s'il n'est dominé par aucun element de la liste.
	 * @param ob objet à comparer avec le reste de la liste
	 * @param list liste d'objets à 2 objects
	 */
	public void testEntree(MultiObjectiveObject ob, List<MultiObjectiveObject> list) {
		int i = 0;
		boolean b = true;
		while (i<list.size() && b) {
			nbComp++;
			if (ob.domine(list.get(i))) {
				list.get(i).setDomine(true);
			}
			else if (list.get(i).domine(ob)) {
				b = false;
				ob.setDomine(true);
			}
			i++;
		}
		if (!ob.isDomine()) list.add(ob);
		nbComp+=(list.size());
	}


	public int getNbComp() {
		return nbComp;
	}


	public void setNbComp(int nbComp) {
		this.nbComp = nbComp;
	}
	
	public static void main(String[]args) throws IOException {
		/*
		String s = "0	0";
		for (int i = 1; i<=100; i++) {
		FiltreOnLine f = new FiltreOnLine();
		f.load(i*100);
		s+=("\n" + i*100 + "	" + f.getNbComp()/3);
		}
		stringToFile(s, PATH, "compOnLine.txt");
		*/
		
		
		FiltreOnLine f = new FiltreOnLine();
		f.load(500);
		createFileListMultiObjectiveObject(f.getInstanceinit().get(0), PATH, "onFiltreAB.txt");
		createFileListMultiObjectiveObject(f.getInstanceinit().get(1), PATH, "onFiltreCD.txt");
		createFileListMultiObjectiveObject(f.getInstanceinit().get(2), PATH, "onFiltreEF.txt");
		
		//
		createFileListMultiObjectiveObject(f.getEnsembleSolAB(), PATH, "onAllAB.txt");
		createFileListMultiObjectiveObject(f.getEnsembleSolCD(), PATH, "onAllCD.txt");
		createFileListMultiObjectiveObject(f.getEnsembleSolEF(), PATH, "onAllEF.txt");
		
	}

	
}
