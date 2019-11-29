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

public class ApprocheScalaire {
	
	List<TwoMatrice> matrices = new ArrayList<>();



	public ApprocheScalaire() throws IOException {
		this.matrices.add(new TwoMatrice("A", "B"));
		this.matrices.add(new TwoMatrice("C", "D"));
		this.matrices.add(new TwoMatrice("E", "F"));
	}
	
	public void load() throws IOException {
		for (TwoMatrice m : matrices) {
			m.load();
		}
	}
	



	/***
	 * 
	 * @param l le couple de paramètres lambda
	 * @param o un objet multiobjectif
	 * @return le cout de l'objet multiobjectif pour les 2 lambdas 
	 */
	public static int sommePonderee(Lambda l, MultiObjectiveObject o) {
		return somme(l, o.getCritere1(), o.getCritere2());
	}
	
	public static int somme(Lambda l, int c1, int c2) {
		return l.getLambda1()*c1+l.getLambda2()*c2;
	}
	
	/***
	 * 
	 * @param n nombre de couple de poids 
	 * @return une liste de couple de poids
	 */
	public List<Lambda> listOfLambdaCouple(int n){
		List<Lambda> lambdas = new ArrayList<Lambda>();
		for (int i=0; i<=n; i++) {
			lambdas.add(new Lambda(i, n-i));
		}
		return lambdas;
	}
	
	
	public static int gain(Integer[] c1, Integer[] c2, TwoMatrice ms, Lambda l) throws IOException {
		int s1 = sommePonderee(l, ParserMultiCriteres.fonctionEvaluation(c1, ms));
		int s2 = sommePonderee(l, ParserMultiCriteres.fonctionEvaluation(c2, ms));
		return (s1 - s2)/100;
	}
	
	
	public static MultiObjectiveObject premierVoisinAmeliorantTwoOpt(MultiObjectiveObject villes, TwoMatrice ms, Lambda l) throws IOException {
		Integer[] nouvelleCombinaison;
		for (int i = 0; i < villes.getChemin().length - 2; i++) {
			for (int j = i + 3; j < villes.getChemin().length; j++) {
				nouvelleCombinaison = HillClimbing.oneTourTwoOpt(villes.getChemin(), i, j);
				int gain = gain(villes.getChemin(),nouvelleCombinaison, ms, l);
				if (gain > 0) {
					return premierVoisinAmeliorantTwoOpt(ParserMultiCriteres.fonctionEvaluation(nouvelleCombinaison, ms), ms, l);
				}
			}
		}
		return villes;
	}
	
	
	
	/**
	 * 
	 * @param n, taille de la liste
	 * @return une liste contenant n objets MultiObjectiveObject
	 * @throws IOException
	 */
	public List<MultiObjectiveObject> listBests(List<Lambda> lambdas, MultiObjectiveObject o, TwoMatrice ms) throws IOException{
		int i = 0;
		List<MultiObjectiveObject> list = new ArrayList<>();
		while (i<lambdas.size()) {
			MultiObjectiveObject m = premierVoisinAmeliorantTwoOpt(o, ms, lambdas.get(i));
			list.add(m);
			i++;
		}
		return list;	
	}
	
	

	public List<TwoMatrice> getMatrices() {
		return matrices;
	}

	public void setMatrices(List<TwoMatrice> matrices) {
		this.matrices = matrices;
	}
	
	

	public static void test(int nbExectuion, int tailleLambda, String path, String fileName, String instance) throws IOException {
		ApprocheScalaire as;
		as = new ApprocheScalaire();
		as.load();
		List<MultiObjectiveObject> ls = new ArrayList<>();
		List<Lambda> lambdas = as.listOfLambdaCouple(tailleLambda);
		TwoMatrice m = null;
		switch(instance){
		case "AB":
			m = as.getMatrices().get(0);
			break;
		case "CD":
			m = as.getMatrices().get(1);
			break;
		case "EF":
			m = as.getMatrices().get(2);
			break;	
		}
		for (int i= 0; i<nbExectuion; i++) {
				List<MultiObjectiveObject> nouveau = as.listBests(lambdas, ParserMultiCriteres.objetAleatoire(m), m);				
				for (MultiObjectiveObject x : nouveau){
					   if (!ls.contains(x))
					      ls.add(x);
					}
			
		}
		FiltreOffLine.filtreOffLineByFlag(ls);
		Filtre.supprimerDomines(ls);
		System.out.println("on filtre" +"\n");
		Filtre.createFileListMultiObjectiveObject(ls,  path, fileName);
		System.out.println("fin");
	}
	

	
	public static void main(String[]args) throws IOException {
		String path = "/home/m2miage/terbah/Documents/ADOM/adom-projet/Ressources/fichiersGeneres/tp5/";
		// 10 * 100 vecteurs
		test(10, 100, path, "frontScalaireAB10X100.txt", "AB");
		test(10, 100, path, "frontScalaireCD10X100.txt", "CD");
		test(10, 100, path, "frontScalaireEF10X100.txt", "EF");
		
		test(2, 500, path, "frontScalaireAB2X500.txt", "AB");
		test(2, 500, path, "frontScalaireCD2X500.txt", "CD");
		test(2, 500, path, "frontScalaireEF2X500.txt", "EF");
		
		


	

	}




}
