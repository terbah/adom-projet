package fil.adom.tp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Matrice {

	//public static String location = "/home/m2miage/terbah/Documents/ADOM/adom-projet/Ressources/InstancesRandomTSP/";
	public static String location = "C:\\Users\\aghil\\Documents\\M2MIAGE\\ADOM\\adom-projet\\Ressources\\InstancesRandomTSP\\";
	//public static String location = "D:\\Malik\\ECOLE\\Lille1\\M2 (2019-2020)\\S3\\ADOM\\TPs\\Workspace_Projet_Adom\\adom-projet\\Ressources\\InstancesRandomTSP\\";
	
	private int[][] matrice;
	private String instance;
	
	public Matrice(String instance) {
		this.instance = instance;
	}
	
	public void load() throws IOException {
		File f = new File(location + "random" + instance + "100.tsp");
		setMatrice(f);
	}
	
	public void setMatrice(File f) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(f));
		this.matrice = new int[100][100];
		for (int i = 1; i < 101; i++) {
			for (int j = i; j < 101; j++) {
				String cout = in.readLine();
				// System.out.println("cout " + i + " à " + j + " = " + cout);
				matrice[i - 1][j - 1] = Integer.parseInt(cout);
				matrice[j - 1][i - 1] = Integer.parseInt(cout);
			}
		}
	}
	
	public int[][] getMatrice() {
		return this.matrice;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

}
