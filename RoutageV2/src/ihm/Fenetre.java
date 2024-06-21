package ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import routage.Reseau;

public class Fenetre extends JFrame {

	public Reseau reseau;
	public ActionAjouterCommutateur actionAjouterCommutateur;
	public ActionAjouterOrdinateur actionAjouterOrdinateur;
	public ActionAjouterConnexion actionAjouterConnexion;
	public ActionCheckAjoutConnexion actionCheckAjoutConnexion;
	public ActionTrouverRoute actionTrouverRoute;
	public ActionTrouverTable actionTrouverTable;

	public JComboBox<String> comboBoxArtereA;
	public JComboBox<String> comboBoxArtereB;
	public JComboBox<String> comboBoxCheminA;
	public JComboBox<String> comboBoxCheminB;
	public JComboBox<String> comboBoxTable;

	public JTextField poid;

	public Fenetre() {
		super("Routage");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		reseau = new Reseau();
		reseau.getGraph().setAttribute("ui.stylesheet", "url(style.txt)");
		comboBoxArtereA = new JComboBox<String>();
		comboBoxArtereB = new JComboBox<String>();
		comboBoxCheminA = new JComboBox<String>();
		comboBoxCheminB = new JComboBox<String>();
		comboBoxTable = new JComboBox<String>();

		init();
		comboBoxArtereA.setAction(actionCheckAjoutConnexion);
		comboBoxArtereB.setAction(actionCheckAjoutConnexion);
		pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void init() {
		initActions();
		initComposants();
	}

	private void initActions() {
		this.actionAjouterCommutateur = new ActionAjouterCommutateur(reseau, this);
		this.actionAjouterOrdinateur = new ActionAjouterOrdinateur(reseau, this);
		this.actionAjouterCommutateur.setActionAutre(actionAjouterOrdinateur);
		this.actionAjouterOrdinateur.setActionAutre(actionAjouterCommutateur);

		this.actionAjouterConnexion = new ActionAjouterConnexion(reseau, this);
		this.actionCheckAjoutConnexion = new ActionCheckAjoutConnexion(reseau, this);

		this.actionTrouverRoute = new ActionTrouverRoute(reseau, this);
		this.actionTrouverTable = new ActionTrouverTable(reseau, this);

	}

	private void initComposants() {
		PanelGraph panelMid = new PanelGraph(reseau.getGraph());
		add(panelMid, BorderLayout.CENTER);

		JPanel panelEast = new JPanel();
		panelEast.setLayout(new GridBagLayout());

		JPanel contentEastUn = new JPanel();
		contentEastUn.setLayout(new GridLayout(0, 1, 0, 6));

		JPanel contentEastDeux = new JPanel();
		contentEastDeux.setLayout(new GridLayout(0, 1, 0, 6));

		JPanel contentEastTrois = new JPanel();
		contentEastTrois.setLayout(new GridLayout(0, 1, 0, 6));

		JPanel contentEastQuatre = new JPanel();
		contentEastQuatre.setLayout(new GridLayout(0, 1, 0, 6));

		JPanel contentEastCinq = new JPanel();
		contentEastCinq.setLayout(new GridLayout(0, 1, 0, 6));

		GridBagConstraints contraintes = new GridBagConstraints();

		JLabel labelInteraction = new JLabel("Interactions");
		labelInteraction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		contentEastUn.add(labelInteraction);

		JLabel labelAjout = new JLabel("Ajouter un appareil");
		labelAjout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		contentEastDeux.add(labelAjout);
		JButton ajouterCommutateur = new JButton(actionAjouterCommutateur);
		contentEastDeux.add(ajouterCommutateur);
		JButton ajouterOrdinateur = new JButton(actionAjouterOrdinateur);
		contentEastDeux.add(ajouterOrdinateur);

		JLabel labelArtere = new JLabel("Ajouter une connexion");
		labelArtere.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		contentEastTrois.add(labelArtere);
		contentEastTrois.add(comboBoxArtereA);
		contentEastTrois.add(comboBoxArtereB);

		JPanel test = new JPanel();
		test.setLayout(new GridBagLayout());

		JLabel labelPoid = new JLabel("Poids");
		poid = new JTextField("", 7);
		poid.setAction(actionCheckAjoutConnexion);

		poid.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				actionCheckAjoutConnexion.actionPerformed(null);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				actionCheckAjoutConnexion.actionPerformed(null);

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				actionCheckAjoutConnexion.actionPerformed(null);
			}
		});

		contraintes.weightx = 1;
		test.add(labelPoid, contraintes);
		contraintes.weightx = 1;
		test.add(poid, contraintes);
		contentEastTrois.add(test);

		JButton ajouteConnexion = new JButton(actionAjouterConnexion);
		contentEastTrois.add(ajouteConnexion);

		JLabel labelRoute = new JLabel("Trouver une route");
		labelRoute.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		contentEastQuatre.add(labelRoute);
		contentEastQuatre.add(comboBoxCheminA);
		contentEastQuatre.add(comboBoxCheminB);

		JButton trouverRoute = new JButton(actionTrouverRoute);
		contentEastQuatre.add(trouverRoute);

		JLabel labelTable = new JLabel("Génrer Table");
		labelTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		contentEastCinq.add(labelTable);
		contentEastCinq.add(comboBoxTable);
		JButton trouverTable = new JButton(actionTrouverTable);
		contentEastCinq.add(trouverTable);

		contraintes.gridy = 0;
		contraintes.weighty = 1;
		contraintes.anchor = GridBagConstraints.NORTH;
		panelEast.add(contentEastUn, contraintes);

		contraintes.gridy = 1;
		contraintes.weighty = 2;
		panelEast.add(contentEastDeux, contraintes);

		contraintes.gridy = 2;
		contraintes.weighty = 2;
		panelEast.add(contentEastTrois, contraintes);

		contraintes.gridy = 3;
		contraintes.weighty = 2;
		panelEast.add(contentEastQuatre, contraintes);

		contraintes.gridy = 4;
		contraintes.weighty = 10;
		panelEast.add(contentEastCinq, contraintes);

		add(panelEast, BorderLayout.EAST);

	}

	public void ajouerItemList(String id) {
		comboBoxArtereA.addItem(id);
		comboBoxArtereB.addItem(id);
	}

	public void ajouterOrdiList(String id) {
		comboBoxCheminA.addItem(id);
		comboBoxCheminB.addItem(id);
	}

	public void ajouterCommutateurTable(String id) {
		this.comboBoxTable.addItem(id);
	}
}
