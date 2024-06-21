package routage;

import java.util.ArrayList;


public abstract class Appareil implements Comparable<Appareil> {

	protected String id; 
	protected ArrayList<Artere> tabArtere;  //Contient toutes les arteres connecté à l'appareil
	protected String type;					//Ordinateur ou commutateur
	protected Appareil precedent;			//algorithme de Dijkstra
	protected int distance = 100000;		//algorithme de Dijkstra

	public int getDistance() {
		return this.distance;
	}

	public Appareil getPrecedent() {
		return this.precedent;
	}

	public String getId() {
		return this.id;
	}

	public ArrayList<Artere> getTabArtere() {
		return this.tabArtere;
	}

	public void addArtere(Artere artere) {
		this.tabArtere.add(artere);
	}

	public String getType() {
		return this.type;
	}

	public boolean estVoisin(Appareil commutateur) {
		for (Artere e : this.tabArtere) {
			if (e.getAppareilUn() == commutateur || e.getAppareilDeux() == commutateur) {
				return true;
			}
		}
		return false;
	}

	public Artere getArtereCommune(Appareil appareil) {
		for (Artere e : this.tabArtere) {
			if (e.getAppareilDeux() == appareil || e.getAppareilUn() == appareil) {
				return e;
			}
		}
		return null;
	}

	public void setPrecedent(Appareil precedent) {
		this.precedent = precedent;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String toString() {
		return this.id;
	}

	@Override
	public int compareTo(Appareil o) {
		return Integer.compare(this.distance, o.distance);
	}

}
