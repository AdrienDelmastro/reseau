package ihm;

import java.util.ArrayList;

import javax.swing.AbstractAction;

import routage.Reseau;
import routage.Route;
import routage.RoutePlusCourteChercheur;
import routage.TableDeRoutageMaker;

public class ActionTrouverTable extends AbstractAction{
	private Reseau reseau;
	private Fenetre fenetre;
	private String appareilIdA;
	
	public ActionTrouverTable(Reseau reseau, Fenetre fenetre) {
		super("Générer table");
		this.reseau = reseau;
		this.fenetre = fenetre;
		this.setEnabled(false);

	}

	  
	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
		TableDeRoutageMaker chercheur = new TableDeRoutageMaker(this.reseau, this.reseau.getAppareilById((String)this.fenetre.comboBoxTable.getSelectedItem()));
		this.appareilIdA = (String)this.fenetre.comboBoxTable.getSelectedItem();

		Dialog dialog = new Dialog(this.fenetre, this.appareilIdA, chercheur.getTableString());
		System.out.println(chercheur.getTableString());
	}
}
