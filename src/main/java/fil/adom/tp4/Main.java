package fil.adom.tp4;

import java.io.IOException;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		//Filtre f = new FiltreOnLine();
		Filtre f2 = new FiltreOffLine();
		//f.load(500);
		f2.load(500);
	}

}
