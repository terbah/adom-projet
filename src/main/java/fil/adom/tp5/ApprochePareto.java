package fil.adom.tp5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fil.adom.tp2.HillClimbing;
import fil.adom.tp4.Filtre;
import fil.adom.tp4.FiltreOffLine;
import fil.adom.tp4.MultiObjectiveObject;
import fil.adom.tp4.ParserMultiCriteres;
import fil.adom.tp4.TwoMatrice;

public class ApprochePareto {

	
	List<TwoMatrice> matrices = new ArrayList<>();



	public ApprochePareto() throws IOException {
		this.matrices.add(new TwoMatrice("A", "B"));
		this.matrices.add(new TwoMatrice("C", "D"));
		this.matrices.add(new TwoMatrice("E", "F"));
	}
	
	public void load() throws IOException {
		for (TwoMatrice m : matrices) {
			m.load();
		}
	}
	
	
	
	
	public List<TwoMatrice> getMatrices() {
		return matrices;
	}

	public void setMatrices(List<TwoMatrice> matrices) {
		this.matrices = matrices;
	}

	public static List<MultiObjectiveObject> voisinsTwoOpt(MultiObjectiveObject villes, TwoMatrice ms) throws IOException {
		List<MultiObjectiveObject> voisins = new ArrayList<>();
		for (int i = 0; i < villes.getChemin().length - 2; i++) {
			for (int j = i + 3; j < villes.getChemin().length; j++) {
					voisins.add(ParserMultiCriteres.fonctionEvaluation(HillClimbing.oneTourTwoOpt(villes.getChemin(), i, j), ms));
				}
			}
		return voisins;
	}
	
	
	public static List<MultiObjectiveObject> genererEtFiltrer(MultiObjectiveObject o, TwoMatrice ms) throws IOException {
		o.setVisited(true);
		List<MultiObjectiveObject> l = voisinsTwoOpt(o, ms);
		//System.out.println("taille de la liste : " + l.size());
		FiltreOffLine.filtreOffLineByFlag(l);
		FiltreOffLine.supprimerDomines(l);
		//System.out.println("taille de la liste après filtre : " + l.size());
		return l;
	}
	
	
	public static List<MultiObjectiveObject> pareto(List<MultiObjectiveObject> start, TwoMatrice ms) throws IOException{
		List<MultiObjectiveObject> archive = new ArrayList<>();
		archive.addAll(start);
		int i = 0;
		System.out.println("i au debut : " + i);
		for (MultiObjectiveObject o : start) {
			if (!o.isVisited()) {
				List<MultiObjectiveObject> l = genererEtFiltrer(o,ms);
				for (MultiObjectiveObject ob : l) {
					if (!archive.contains(ob)) archive.add(ob);
				}
				//archive.addAll(genererEtFiltrer(o,ms));
				i++;
			}
		}		
		System.out.println("i a la fin : " + i);
		FiltreOffLine.filtreOffLineByFlag(archive);
		FiltreOffLine.supprimerDomines(archive);
		if (i == 0)return archive;
		else return pareto(archive, ms);
	}
	
	public static void test(String path, String fileName, String instance) throws IOException {
		ApprochePareto ap = new ApprochePareto();
		ap.load();
		List<MultiObjectiveObject> os = new ArrayList<MultiObjectiveObject>();
		TwoMatrice m = null;
		switch(instance){
		case "AB":
			m = ap.getMatrices().get(0);
			break;
		case "CD":
			m = ap.getMatrices().get(1);
			break;
		case "EF":
			m = ap.getMatrices().get(2);
			break;	
		}
		os.add(ParserMultiCriteres.objetAleatoire(m));
		Filtre.createFileListMultiObjectiveObject(pareto(os, m),  path, fileName);
		System.out.println("fin");
	}
	
	
	public static void main(String[] args) throws IOException {
		/*
		ApprochePareto ap = new ApprochePareto();
		List<MultiObjectiveObject> os = new ArrayList<MultiObjectiveObject>();
		os.add(ParserMultiCriteres.objetAleatoire(ap.getMatrices().get(0)));
		for (MultiObjectiveObject o : pareto(os, ap.getMatrices().get(0))) {
			System.out.println(o);
		};
		*/
		String path = "/home/m2miage/terbah/Documents/ADOM/adom-projet/Ressources/fichiersGeneres/tp5/";
		test(path, "paretoAB.txt", "AB");
		test(path, "paretoCD.txt", "CD");
		test(path, "paretoEF.txt", "EF");

	}

	
	

}
