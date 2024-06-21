package routage;

import java.util.ArrayList;

public class Route implements Comparable<Route> {
	private ArrayList<Appareil> chemin;
	private int poids;

	public Route() {
		this.chemin = new ArrayList<>();
		this.poids = 0;
	}

	public Route(Appareil app, int poids) {
		this.chemin = new ArrayList<>();
		this.poids = poids;
		this.chemin.add(app);
	}

	public Route(ArrayList<Appareil> chemin, int poids) {
		this.chemin = chemin;
		this.poids = poids;
	}

	public int getPoids() {
		return this.poids;
	}

	public ArrayList<Appareil> getChemin() {
		return this.chemin;
	}

	public String getLastAppId() {
		return this.chemin.get(chemin.size() - 1).toString();
	}

	public String getFirtAppId() {
		return this.chemin.get(0).toString();
	}

	public void majPoids(int valeure) {
		this.poids += valeure;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public void addAppareil(Appareil appareil) {
		this.chemin.add(appareil);
	}

	public void removeAppareil(Appareil appareil) {
		this.chemin.remove(appareil);
	}

	public void setChemin(ArrayList<Appareil> chemin) {
		this.chemin = chemin;
	}

	public String toString() {
		String str = "";
		for (Appareil e : this.chemin) {
			str += e.getId() + " ";
		}
		return str + " Poids: " + this.poids;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this || o == null || !(o instanceof Route)) {
			return false;
		}
		Route route = (Route) o;
		return this.chemin.equals(route.chemin);
	}

	@Override
	public int compareTo(Route o) {
		return Integer.compare(this.poids, o.poids);
	}

}
