package ihm;

import javax.swing.AbstractAction;

import routage.Reseau;
import routage.Route;
import routage.RoutePlusCourteChercheur;

public class ActionTrouverRoute extends AbstractAction {
	private Reseau reseau;
	private Fenetre fenetre;
	private String appareilIdA, appareilIdB;

	public ActionTrouverRoute(Reseau reseau, Fenetre fenetre) {
		super("Rechercher une route");
		this.reseau = reseau;
		this.fenetre = fenetre;
		this.setEnabled(false);
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		this.appareilIdA = (String) this.fenetre.comboBoxCheminA.getSelectedItem();
		this.appareilIdB = (String) this.fenetre.comboBoxCheminB.getSelectedItem();
		RoutePlusCourteChercheur chercheur = new RoutePlusCourteChercheur(this.reseau,
				this.reseau.getAppareilById((String) this.fenetre.comboBoxCheminA.getSelectedItem()),
				this.reseau.getAppareilById((String) this.fenetre.comboBoxCheminB.getSelectedItem()));
		Route route = chercheur.getCheminPlusCourt();
		Dialog dialog = new Dialog(this.fenetre, this.appareilIdA, this.appareilIdB, route.toString());
	}
}
