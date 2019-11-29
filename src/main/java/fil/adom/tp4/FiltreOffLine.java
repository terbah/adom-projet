package fil.adom.tp4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fil.adom.tp1.Glouton;

/***
 * Classe representant un filtre off Line
 *
 */
public class FiltreOffLine extends Filtre{
	
	private int nbComp = 0;
	
	public FiltreOffLine() {
		super();
	}
	
	
	public void loadList(int n) throws IOException {
		this.instanceinit.add(listInstance(n, matricesAB));
		this.instanceinit.add(listInstance(n, matricesCD));
		this.instanceinit.add(listInstance(n, matricesEF));
	}
	
	public void filtreList() throws IOException {
		for (List<MultiObjectiveObject> o : instanceinit) {
			nbComp+=(o.size());
			supprimerDomines(o);
		}
	}
	
	/***
	 * 
	 * @param nbInstances nombre d'instances à traiter
	 * @param ms Le couple d'instances concerné
	 * @return la liste filtree par le methode offLine
	 * @throws IOException
	 */
	public List<MultiObjectiveObject> listInstance(int nbInstances, TwoMatrice ms ) throws IOException{
		List<MultiObjectiveObject> NonDomines = initInstance(nbInstances, ms);
		filtreOffLineByFlag(NonDomines);
		//supprimerDomines(NonDomines);
		return NonDomines;
	}

	
	
	/***
	 * 
	 * @param nbInstances nombre de solutions à generer 
	 * @param ms matrices à partir desquelles les solutions seront generees
	 * @return une liste de taille nbInstances contenant les solutions à 2 objectifs generees 
	 * @throws IOException
	 */
	public static List<MultiObjectiveObject> initInstance(int nbInstances, TwoMatrice ms ) throws IOException{
		List<MultiObjectiveObject> list = new ArrayList<>();
		for (int i=0; i< nbInstances; i++) {
			list.add(createObject(Glouton.villesAleatoire(100), ms));
		}
		return list;
	}


	/***
	 * Parcours toutes la liste et marque les elements domines.
	 * @param ls liste a filtrer
	 */
	public static void filtreOffLineByFlag(List<MultiObjectiveObject> ls) {
		int i = 0;
		while (i < ls.size()) {
				int j = i;
				while (j < ls.size() && !ls.get(i).isDomine()) {
					//nbComp++;
					if (ls.get(j).domine(ls.get(i))) {
						ls.get(i).setDomine(true);
					}else if (ls.get(i).domine(ls.get(j))) {
						ls.get(j).setDomine(true);
					}
					j++;
				}
			i++;
		}
	}
	
	public static void main(String[]args) throws IOException {	
		
		/*
		String s = "0	0";
		for (int i = 1; i<=100; i++) {
		FiltreOffLine f = new FiltreOffLine();
		f.load(i*100);
		s+=("\n" + i*100 + "	" + f.getNbComp()/3);
		}
		stringToFile(s, PATH, "compOffLine.txt");
		*/
		
		
		FiltreOffLine f = new FiltreOffLine();
		f.load(500);
		createFileListMultiObjectiveObject(f.getInstanceinit().get(0), PATH, "offAllAB.txt");
		createFileListMultiObjectiveObject(f.getInstanceinit().get(1), PATH, "offAllCD.txt");
		createFileListMultiObjectiveObject(f.getInstanceinit().get(2), PATH, "offAllEF.txt");
		f.filtreList();
		createFileListMultiObjectiveObject(f.getInstanceinit().get(0), PATH, "offFiltreAB.txt");
		createFileListMultiObjectiveObject(f.getInstanceinit().get(1), PATH, "offFiltreCD.txt");
		createFileListMultiObjectiveObject(f.getInstanceinit().get(2), PATH, "offFiltreEF.txt");
		
		
	}


	public int getNbComp() {
		return nbComp;
	}


	public void setNbComp(int nbComp) {
		nbComp = nbComp;
	}
	
	
	

}
