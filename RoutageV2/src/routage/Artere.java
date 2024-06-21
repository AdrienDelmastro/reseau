package routage;

public class Artere {
	private final String id;
	private final int poid;
	private final Appareil appareilUn;
	private final Appareil appareilDeux;

	public Artere(int poid, Appareil appareilUn, Appareil appareilDeux) {
		this.id = appareilUn.getId() + appareilDeux.getId();
		this.poid = poid;
		this.appareilUn = appareilUn;
		this.appareilDeux = appareilDeux;
	}

	public String getId() {
		return this.id;
	}

	public int getPoid() {
		return this.poid;
	}

	public Appareil getAppareilUn() {
		return this.appareilUn;
	}

	public Appareil getAppareilDeux() {
		return this.appareilDeux;
	}

	public String toString() {
		return this.id;
	}
}
