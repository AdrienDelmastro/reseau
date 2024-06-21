package ihm;

import javax.swing.AbstractAction;

import routage.Reseau;

public class ActionAjouterOrdinateur extends AbstractAction {
	private Reseau reseau;
	private ActionAjouterCommutateur actionAutre;
	private Fenetre fenetre;

	public ActionAjouterOrdinateur(Reseau reseau, Fenetre fenetre) {
		super("Ajouter ordinateur");
		this.reseau = reseau;
		this.fenetre = fenetre;
	}

	public void setActionAutre(ActionAjouterCommutateur actionAutre) {
		this.actionAutre = actionAutre;
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		reseau.addOrdinateur();
		if (reseau.getNbelem() == Reseau.NB_MAX_APPAREIL) {
			setEnabled(false);
			actionAutre.setEnabled(false);
		}
		String id = reseau.getLastId();
		fenetre.ajouerItemList(id);
		fenetre.ajouterOrdiList(id);
		if(reseau.reseauConnexe()) {
			fenetre.actionTrouverRoute.setEnabled(true);
			fenetre.actionTrouverTable.setEnabled(true);
		}
		else {
			fenetre.actionTrouverRoute.setEnabled(false);
			fenetre.actionTrouverTable.setEnabled(false);
		}
	}
	
}
