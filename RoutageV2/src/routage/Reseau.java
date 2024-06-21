package routage;

import java.util.ArrayList;
import java.util.Stack;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

public class Reseau {
	private ArrayList<Appareil> appareils;
	private ArrayList<Commutateur> commutateurs;
	private ArrayList<Artere> arteres;
	private int nbElem;
	private SingleGraph graph;
	private char[] alphabet = new char[26];
	public static final int NB_MAX_APPAREIL = 26;

	public Reseau() {
		this.appareils = new ArrayList<>();
		this.arteres = new ArrayList<>();
		this.commutateurs = new ArrayList<>();
		this.graph = new SingleGraph("reseaux");
		System.setProperty("org.graphstream.ui", "swing");
		graph.setAttribute("ui.antialias");
		graph.setAttribute("ui.quality");
		for (int i = 0; i < 26; i++) {
			alphabet[i] = (char) ('A' + i);
		}
		nbElem = 0;
	}

	public boolean reseauConnexe() {
		for(Appareil e : this.appareils) {
			if((e.getTabArtere().isEmpty())) {
				return false;
			}
		}
		
		for(Commutateur c : commutateurs) { //évite en partie d'avoir des commutateur point de passage obligatoire
			if(c.getTabArtere().size() <2 ) {
				return false;
			}
		}
		
		return true && depthSearch() == appareils.size();
	}

	public ArrayList<Appareil> getAppareils() {
		return this.appareils;
	}

	public ArrayList<Artere> getArteres() {
		return this.arteres;
	}

	public SingleGraph getGraph() {
		return this.graph;
	}

	public Appareil getAppareilById(String id) {
		for (Appareil e : appareils) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		return null;
	}

	public int getNbelem() {
		return this.nbElem;
	}

	public String getLastId() {
		return appareils.get(appareils.size() - 1).getId(); 
	}

	public ArrayList<Commutateur> getCommutateurs() {
		return this.commutateurs;
	}

	public void addCommutateur() {
		Commutateur commutateur = new Commutateur(getNextId());
		this.appareils.add(commutateur);
		this.commutateurs.add(commutateur);
		Node node = this.graph.addNode(commutateur.getId());
		node.setAttribute("ui.label", commutateur.getId());
		nbElem++;

	}

	public void addOrdinateur() {
		Ordinateur ordinateur = new Ordinateur(getNextId());
		this.appareils.add(ordinateur);
		Node node = this.graph.addNode(ordinateur.getId());
		node.setAttribute("ui.label", ordinateur.getId());
		node.setAttribute("ui.class", "marked");
		nbElem++;

	}

	public void addArtere(String idAppareilUn, String idAppareilDeux, int poid) {

		Appareil appareilUn = getAppareilById(idAppareilUn);
		Appareil appareilDeux = getAppareilById(idAppareilDeux);

		Artere artere = new Artere(poid, appareilUn, appareilDeux);
		this.arteres.add(artere);
		Edge edge = graph.addEdge(artere.getId(), idAppareilUn, idAppareilDeux);
		edge.setAttribute("ui.label", poid);
		appareilUn.addArtere(artere);
		appareilDeux.addArtere(artere);
		this.arteres.add(artere);
	}

	public String getNextId() {
		return "" + alphabet[nbElem];
	}

	public void dessinner() {
		graph.display();
	}
	
	private Appareil checkVoisinnage(Appareil appCourant, ArrayList<Appareil> element) { //retourne un voisin non visite de l'appareil courant
		for (Artere e : appCourant.getTabArtere()) {
			Appareil voisin;

			if (appCourant == e.getAppareilUn()) {
				voisin = e.getAppareilDeux();
			} else {
				voisin = e.getAppareilUn();
			}
			if (!(element.contains(voisin))) {
				return voisin;
			}
		}
		return appCourant;
	}
	
	private int depthSearch() {
		ArrayList<Appareil> element = new ArrayList<>();
		Stack<Appareil> route = new Stack<>();				//pour backtrace
		Appareil courant = this.appareils.get(0);
		element.add(courant);
		Appareil ancienCourant = null;
		while(courant !=ancienCourant) {
			Appareil nouveauCourant = checkVoisinnage(courant, element);
			ancienCourant = courant;
			if(nouveauCourant != courant) {
				route.push(courant);
				courant = nouveauCourant;
				element.add(courant);
			}
			else {
				if(!(route.isEmpty())) {
					courant = route.pop();
				}
			}
			
		}
		return element.size();
	}

}
