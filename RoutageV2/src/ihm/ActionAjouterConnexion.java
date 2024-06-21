package ihm;

import javax.swing.AbstractAction;

import routage.Reseau;

public class ActionAjouterConnexion extends AbstractAction {
	private Reseau reseau;
	private Fenetre fenetre;

	public ActionAjouterConnexion(Reseau reseau, Fenetre fenetre) {
		super("Ajouter une connexion");
		this.reseau = reseau;
		this.fenetre = fenetre;
		setEnabled(false);
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {

		reseau.addArtere((String) fenetre.comboBoxArtereA.getSelectedItem(),
				(String) fenetre.comboBoxArtereB.getSelectedItem(), Integer.parseInt(fenetre.poid.getText()));
		fenetre.poid.setText("");
		fenetre.comboBoxArtereA.setSelectedIndex(0);
		fenetre.comboBoxArtereB.setSelectedIndex(0);
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
