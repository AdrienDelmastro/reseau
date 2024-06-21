package ihm;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Dialog extends JFrame {

	private ActionFermer actionFermer;
	private String appareilA, appareilB, content;

	public Dialog(Fenetre fenetre, String appareilA, String appareilB, String content) {
		super("Route la plus courte");
		this.appareilA = appareilA;
		this.appareilB = appareilB;
		this.content = content;
		setLocationRelativeTo(fenetre);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		setSize(400, 100);
		setVisible(true);

	}

	public Dialog(Fenetre fenetre, String appareilA, String content) {
		super("Table de routage");
		this.appareilA = appareilA;
		this.content = content;
		setLocationRelativeTo(fenetre);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initRoutage();
		pack();
		setVisible(true);

	}

	private void init() {
		initActions();
		initComposants();
	}

	private void initRoutage() {
		initActions();
		initComposantsRoutage();
	}

	private void initActions() {
		this.actionFermer = new ActionFermer(this);
	}

	private void initComposants() {
		JPanel contenuPanel = new JPanel();
		contenuPanel.setLayout(new BoxLayout(contenuPanel, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panelBouton = new JPanel();

		JLabel labelTitre = new JLabel("Route la plus courte entre " + this.appareilA + " et " + this.appareilB);
		panel.add(labelTitre);

		JLabel labelContent = new JLabel(content);
		panel.add(labelContent);

		JButton boutonOk = new JButton(actionFermer);
		panelBouton.add(boutonOk);

		contenuPanel.add(panel);
		contenuPanel.add(panelBouton);
		add(contenuPanel);

	}

	private void initComposantsRoutage() {
		JPanel contenuPanel = new JPanel();
		contenuPanel.setLayout(new BoxLayout(contenuPanel, BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panelBouton = new JPanel();

		JLabel labelTitre = new JLabel("Table de routage de " + this.appareilA);
		panel.add(labelTitre);

		JTextArea labelContent = new JTextArea(content);
		labelContent.setEditable(false);
		panel.add(labelContent);

		JButton boutonOk = new JButton(actionFermer);
		panelBouton.add(boutonOk);

		contenuPanel.add(panel);
		contenuPanel.add(panelBouton);
		add(contenuPanel);

	}
}
