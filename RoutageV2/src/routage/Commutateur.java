package routage;

import java.util.ArrayList;

public class Commutateur extends Appareil {

	public Commutateur(String id) {
		this.id = id;
		super.tabArtere = new ArrayList<>();
		super.type = "Commutateur";
	}
}