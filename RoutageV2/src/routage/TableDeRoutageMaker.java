package routage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class TableDeRoutageMaker {

	private Reseau reseau;
	private Appareil appareilA, appCourant, target;
	private ArrayList<Appareil> nonVisite, visite, voisins;
	private Appareil voisinEncours;

	public TableDeRoutageMaker(Reseau reseau, Appareil appareilA) {
		this.reseau = reseau;
		this.appareilA = appareilA;
		this.nonVisite = new ArrayList<>();
		this.visite = new ArrayList<>();
		this.voisins = new ArrayList<>();
		for (Appareil e : reseau.getCommutateurs()) {
			if (e.estVoisin(appareilA) && e != this.appareilA)
				this.voisins.add(e);
		}

	}

	public Route getCheminPlusCourt() {
		preparerGraph();

		while (!(this.nonVisite.isEmpty())) {
			checkVoisinnage();
			deplacer();

		}
		Route resultat = getRoute();
		return resultat;
	}

	private void checkVoisinnage() {

		for (Artere e : appCourant.getTabArtere()) {
			Appareil voisin;

			if (this.appCourant == e.getAppareilUn()) {
				voisin = e.getAppareilDeux();
			} else {
				voisin = e.getAppareilUn();
			}
			int distance = e.getPoid() + appCourant.getDistance();
			if (voisin.getDistance() > distance && voisin != this.appareilA) {
				voisin.setDistance(distance);
				voisin.setPrecedent(appCourant);
			}
		}

		this.nonVisite.remove(appCourant);
		this.visite.add(appCourant);
	}

	private void deplacer() {
		if (!(this.nonVisite.isEmpty())) {
			Appareil courant = this.nonVisite.get(0);
			for (Appareil e : this.nonVisite) {
				if (e.getDistance() < courant.getDistance()) {
					courant = e;
				}
			}
			this.appCourant = courant;
		}
	}

	private void preparerGraph() {
		for (Appareil e : reseau.getAppareils()) {
			e.setPrecedent(null);
			e.setDistance(Integer.MAX_VALUE);
			this.nonVisite.add(e);
		}
		this.nonVisite.remove(appareilA);
		this.visite.clear();
		this.appareilA.setDistance(0);
		this.appCourant.setDistance(0);
	}

	private Route getRoute() {
		Route route = new Route();
		this.appCourant = this.target;
		Stack<Appareil> etapes = new Stack<>();

		route.setPoids(this.appCourant.getDistance());
		while (this.appCourant != voisinEncours) {
			etapes.push(appCourant);
			this.appCourant = this.appCourant.getPrecedent();
		}
		etapes.push(appCourant);
		;
		while (!(etapes.isEmpty())) {
			route.addAppareil(etapes.pop());
		}
		return route;
	}

	//Cette méthode est largement optimisable
	//Il suffit de faire algorithme de Dijkstra une fois par voisin
	//et de suivre précedent depuis chaque commutateur
	
	private ArrayList<ArrayList<Route>> getTable() {
		ArrayList<ArrayList<Route>> routesPourTable = new ArrayList<>(); //contient les routes de chaque voisins pour chaque commutateur

		for (Commutateur e : reseau.getCommutateurs()) {
			if (e != this.appareilA) { 												//Pour tout les commutateur différente de celui dont on fait la table
				ArrayList<Route> routesPourUnCommutateur = new ArrayList<>();

				for (Appareil v : this.voisins) {									//pour chaque voisin de A 
					this.appCourant = v;
					int poids = 0;
					this.voisinEncours = v;
					this.target = e;

					if (e.estVoisin(this.appareilA) && v == e) {					//Si le voisin est le commutateur pour lequel on calcul les chemins
						poids += e.getArtereCommune(appareilA).getPoid();
						Route route = new Route(e, poids);
						routesPourUnCommutateur.add(route);

					} else {

						Route route = getCheminPlusCourt();							//algorithme de Dijkstra
						poids += v.getArtereCommune(this.appareilA).getPoid();
						route.majPoids(poids);
						routesPourUnCommutateur.add(route);
					}
				}
				routesPourTable.add(routesPourUnCommutateur); 
			}
		}

		return routesPourTable;
	}

	public String getTableString() {
		ArrayList<ArrayList<Route>> table = getTable();
		String retour = "";

		for (ArrayList<Route> e : table) {
			Collections.sort(e);
		}

		for (ArrayList<Route> e : table) {
			retour += e.get(0).getLastAppId() + ": "; //le dernier appareil d'une route est l'appareil correspondant à la ligne de la table
			for (Route f : e) {						  //Il y a une route par voisin
				retour += f.getFirtAppId() + " ";     //Le premier id d'une route est le voisin de l'appareil dont on fait la table
			}
			retour += "\n";
		}
		return retour;
	}

}
