package ihm;

import javax.swing.AbstractAction;

import routage.Appareil;
import routage.Artere;
import routage.Reseau;

public class ActionCheckAjoutConnexion extends AbstractAction {
	private Reseau reseau;
	private Fenetre fenetre;

	public ActionCheckAjoutConnexion(Reseau reseau, Fenetre fenetre) {
		super();
		this.reseau = reseau;
		this.fenetre = fenetre;
	}

	private boolean pasMemeAppareil() {
		String appareilIdUn;
		String appareilIdDeux;
		try {
			appareilIdUn = (String) fenetre.comboBoxArtereA.getSelectedItem();
			appareilIdDeux = (String) fenetre.comboBoxArtereB.getSelectedItem();
			if (appareilIdUn.equals(appareilIdDeux)) {
				return false;
			}
		} catch (Exception e) {
			System.out.println("catch");

			return false;
		}
		return true;
	}

	private boolean pasMemeConnexion() {
		String appareilIdUn;
		String appareilIdDeux;
		appareilIdUn = (String) fenetre.comboBoxArtereA.getSelectedItem();
		appareilIdDeux = (String) fenetre.comboBoxArtereB.getSelectedItem();
		
		for (Artere e : reseau.getArteres()) {
			if (e.getId().equals(appareilIdUn + appareilIdDeux) || e.getId().equals(appareilIdDeux + appareilIdUn)) {
				return false;
			}
		}
		return true;
	}

	private boolean pasDoubleConnexionOrdinnateur() {
		String appareilIdUn;
		String appareilIdDeux;
		appareilIdUn = (String) fenetre.comboBoxArtereA.getSelectedItem();
		appareilIdDeux = (String) fenetre.comboBoxArtereB.getSelectedItem();
		if (reseau.getAppareilById(appareilIdUn).getType().equals("Ordinateur") && reseau.getAppareilById(appareilIdDeux).getType().equals("Ordinateur")) {
			return false;
		}
		return true;
	}
	
	private boolean pasDeuxiemeConnexionOrdinateur() {
		Appareil appareilUn;
		Appareil appareilDeux;
		appareilUn = reseau.getAppareilById(((String)fenetre.comboBoxArtereA.getSelectedItem()));
		appareilDeux = reseau.getAppareilById(((String)fenetre.comboBoxArtereB.getSelectedItem()));
		if(appareilUn.getType().equals("Ordinateur")) {
			for(Artere e : reseau.getArteres()) {
				if( e.getAppareilUn().getId().equals(appareilUn.getId()) || e.getAppareilDeux().getId().equals(appareilUn.getId())) {
					return false;
				}
			}
		}
		
		if(appareilDeux.getType().equals("Ordinateur")) {
			for(Artere e : reseau.getArteres()) {
				if( e.getAppareilUn().getId().equals(appareilDeux.getId()) || e.getAppareilDeux().getId().equals(appareilDeux.getId())) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean connexionOk() {
		try {
			int poid = Integer.parseInt(fenetre.poid.getText());
			String appareilIdUn;
			String appareilIdDeux;
			appareilIdUn = (String) fenetre.comboBoxArtereA.getSelectedItem();
			appareilIdDeux = (String) fenetre.comboBoxArtereB.getSelectedItem();
		} catch (NumberFormatException ex) {
			return false;
		}
		return pasMemeAppareil() && pasMemeConnexion() && pasDoubleConnexionOrdinnateur() && pasDeuxiemeConnexionOrdinateur();
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
			
		try {
			fenetre.actionAjouterConnexion.setEnabled(connexionOk());

		}catch(Exception f) {
			fenetre.actionAjouterConnexion.setEnabled(false);
		}
	}
}
