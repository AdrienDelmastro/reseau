package routage;

import java.util.ArrayList;

public class Ordinateur extends Appareil {

	boolean relie;

	public Ordinateur(String id) {
		super.id = id;
		super.tabArtere = new ArrayList<>();
		this.relie = false; //est connecté ou non
		super.type = "Ordinateur";

	}

	public boolean getRelie() {
		return relie;
	}
}
