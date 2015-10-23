// auteurs: Maud El-Hachem, Sébastien Favron et Pierre-Olivier Berthe-Thérien
// 2015
package drugware_v20;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu menuFic;
	private JMenu menuClients;
	private JMenu menuPresc;
	private JMenu menuMedic;

	private JMenuItem itemFic1;
	private JMenuItem itemFic2;
	private JMenuItem itemFic3;

	private JMenuItem itemClients1;
	private JMenuItem itemClients2;

	private JMenuItem itemPresc1;
	private JMenuItem itemPresc2;
	private JMenuItem itemPresc3;

	private JMenuItem itemMedic1;
	private JMenuItem itemMedic2;

	private JTextField field1;
	private JTextField field2;
	private JTextField field3;
	private JTextField field4;

	private Pharmacie pharma;

	public Fenetre() {
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		menuBar = new JMenuBar();
		menuFic = new JMenu("Fichier");
		menuClients = new JMenu("Clients");
		menuPresc = new JMenu("Prescriptions");
		menuMedic = new JMenu("Médicaments");

		itemFic1 = new JMenuItem("Charger les fichiers");
		itemFic2 = new JMenuItem("Mettre à jour les fichiers");
		itemFic3 = new JMenuItem("Quitter");

		itemClients1 = new JMenuItem("Inscrire un nouveau client");
		itemClients2 = new JMenuItem("Afficher tous les clients");

		itemPresc1 = new JMenuItem("Afficher les prescriptions d'un client");
		itemPresc2 = new JMenuItem("Servir une prescription");
		itemPresc3 = new JMenuItem("Nouvelle prescription");

		itemMedic1 = new JMenuItem("Afficher tous les médicaments");
		itemMedic2 = new JMenuItem("Afficher si interaction");

		field1 = new JTextField();
		field2 = new JTextField();
		field3 = new JTextField();
		field4 = new JTextField();

		pharma = new Pharmacie();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				quitter();
			}
		});
		setVisible(true);

	}

	public void initMenus() {

		// Menu fichier
		itemFic1.addActionListener(new BoutonFic1Listener());
		this.menuFic.add(itemFic1);
		itemFic2.addActionListener(new BoutonFic2Listener());
		this.menuFic.add(itemFic2);

		// Ajout d'un séparateur
		this.menuFic.addSeparator();
		// si quitter
		itemFic3.addActionListener(new BoutonFic3Listener());
		this.menuFic.add(itemFic3);

		// Menu Clients
		itemClients1.addActionListener(new BoutonClient1Listener());
		this.menuClients.add(itemClients1);
		itemClients2.addActionListener(new BoutonClient2Listener());
		this.menuClients.add(itemClients2);

		// Menu Prescriptions
		itemPresc1.addActionListener(new BoutonPresc1Listener());
		this.menuPresc.add(itemPresc1);
		itemPresc2.addActionListener(new BoutonPresc2Listener());
		this.menuPresc.add(itemPresc2);
		itemPresc3.addActionListener(new BoutonPresc3Listener());
		this.menuPresc.add(itemPresc3);

		// Menu Médicaments
		itemMedic1.addActionListener(new BoutonMedic1Listener());
		this.menuMedic.add(itemMedic1);
		itemMedic2.addActionListener(new BoutonMedic2Listener());
		this.menuMedic.add(itemMedic2);

		this.menuBar.add(menuFic);
		this.menuBar.add(menuClients);
		this.menuBar.add(menuPresc);
		this.menuBar.add(menuMedic);
		this.setJMenuBar(menuBar);
		this.setVisible(true);

	}

	public class BoutonFic1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pharma.lireClients();
			pharma.lirePrescriptions();
			pharma.lireMedicaments();
		}
	}

	public class BoutonFic2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			pharma.ecrireClients();
			pharma.ecrirePrescriptions();
		}
	}

	public class BoutonFic3Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			quitter();
		}
	}

	public class BoutonClient1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String nom, prenom;
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Numéro d'assurance maladie", JOptionPane.QUESTION_MESSAGE);
			if (NAM != null && NAM.length() > 0)
				if (pharma.siClientExiste(NAM))
					JOptionPane.showMessageDialog(null,
							"Ce numéro d'assurance maladie existe déjà",
							"Problème", JOptionPane.INFORMATION_MESSAGE);
				else {
					nom = JOptionPane.showInputDialog(null, "Entre le nom",
							"Nom", JOptionPane.QUESTION_MESSAGE);
					prenom = JOptionPane.showInputDialog(null,
							"Entre le prenom", "Prénom",
							JOptionPane.QUESTION_MESSAGE);
					if (nom != null && nom.length() > 0 && prenom != null
							&& prenom.length() > 0)
						pharma.ajouterClient(NAM, nom, prenom);
				}
		}
	}

	public class BoutonClient2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFrame nouvelle = new JFrame("Clients");
			int nbClients = pharma.getLesClients().size();
			JPanel panneau = new JPanel();
			panneau.setLayout(new GridLayout(nbClients, 1));
			panneau.setBackground(Color.white);
			for (Iterator<Client> it = pharma.getLesClients().iterator(); it
					.hasNext();) {
				Client courant = it.next();
				JLabel label = new JLabel();
				label.setText("<html><p style='font-size:14px'>"
						+ courant.afficherClient() + "</p></html>");
				panneau.add(label);
			}
			nouvelle.setContentPane(panneau);
			nouvelle.setSize(400, nbClients * 100);
			nouvelle.setVisible(true);

		}
	}

	public class BoutonPresc1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String NAM = JOptionPane.showInputDialog(null,
					"Entre le numéro d'assurance maladie",
					"Numéro d'assurance maladie", JOptionPane.QUESTION_MESSAGE);
			if (NAM != null && NAM.length() > 0) {
				List<Prescription> liste = pharma.getPrescriptionsClient(NAM);
				if (liste != null) {
					JFrame nouvelle = new JFrame("Prescription du client");
					int nbPresc = liste.size();
					JPanel panneau = new JPanel();
					panneau.setLayout(new GridLayout(nbPresc, 1));
					panneau.setBackground(Color.white);
					for (Iterator<Prescription> it = liste.iterator(); it
							.hasNext();) {
						Prescription courant = it.next();
						JLabel label = new JLabel();
						label.setText("<html><p style='font-size:14px'>"
								+ courant.afficherPrescription()
								+ "</p></html>");
						panneau.add(label);
					}
					nouvelle.add(panneau);
					nouvelle.setSize(400, nbPresc * 100);
					nouvelle.setVisible(true);
				}

			}
		}
	}

	public class BoutonPresc2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Object[] fields = { "Numéro d'assurance maladie", field1,
					"Nom du médicament", field2 };
			int option = JOptionPane.showConfirmDialog(null, fields,
					"Prescription", JOptionPane.OK_CANCEL_OPTION);
			String NAM = field1.getText();
			String medicament = field2.getText();
			if (option == JOptionPane.OK_OPTION) {
				if (pharma.servirPrescription(NAM, medicament)) {
					JOptionPane.showMessageDialog(null, "Prescription servie!",
							"Prescription", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Il n'est pas possible de servir la prescription",
							"Prescription", JOptionPane.INFORMATION_MESSAGE);
				}

			} else if (option == JOptionPane.CANCEL_OPTION
					|| option == JOptionPane.CLOSED_OPTION) {

			}
		}
	}

	public class BoutonPresc3Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Object[] fields = { "Numéro d'assurance maladie", field1,
					"Nom du médicament", field2, "Dose", field3,
					"Nombre de renouvellements", field4 };
			int option = JOptionPane.showConfirmDialog(null, fields,
					"Prescription", JOptionPane.OK_CANCEL_OPTION);
			String NAM = field1.getText();
			String medicament = field2.getText();
			double dose = Double.parseDouble(field3.getText());
			int renouv = Integer.parseInt(field4.getText());
			if (option == JOptionPane.OK_OPTION) {
				if (pharma.creerPrescription(NAM, medicament, dose, renouv)) {
					JOptionPane.showMessageDialog(null, "Prescription créée!",
							"Prescription", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Il n'est pas possible de créer la prescription",
							"Prescription", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (option == JOptionPane.CANCEL_OPTION
					|| option == JOptionPane.CLOSED_OPTION) {

			}
		}
	}

	public class BoutonMedic1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFrame nouvelle = new JFrame("Médicaments");
			int nbMedic = pharma.getLesMedicaments().size();
			JPanel panneau = new JPanel();
			panneau.setLayout(new GridLayout(nbMedic, 1));
			panneau.setBackground(Color.white);
			for (Iterator<Medicament> it = pharma.getLesMedicaments()
					.iterator(); it.hasNext();) {
				Medicament courant = it.next();
				JLabel label = new JLabel();
				label.setText("<html><p style='font-size:14px'>"
						+ courant.getNomMolecule() + " "
						+ courant.getNomMarque() + "</p></html>");
				panneau.add(label);
			}
			nouvelle.add(panneau);
			nouvelle.setSize(400, nbMedic * 100);
			nouvelle.setVisible(true);

		}
	}

	public class BoutonMedic2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Object[] fields = { "Nom de la molécule ou du médicament no 1",
					field1, "Nom de la molécule ou du médicament no 2", field2 };
			int option = JOptionPane.showConfirmDialog(null, fields,
					"Interactions", JOptionPane.OK_CANCEL_OPTION);
			String medicament1 = field1.getText();
			String medicament2 = field2.getText();
			if (option == JOptionPane.OK_OPTION) {
				if (pharma.trouverInteraction(medicament1, medicament2)) {
					JOptionPane.showMessageDialog(null,
							"Interaction trouvée! Faites attention!",
							"Interactions", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null,
							"Il n'est pas possible de créer la prescription",
							"Prescription", JOptionPane.INFORMATION_MESSAGE);
				}
			} else if (option == JOptionPane.CANCEL_OPTION
					|| option == JOptionPane.CLOSED_OPTION) {

			} else {
				JOptionPane.showMessageDialog(null,
						"Aucune interaction trouvée!", "Interactions",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	private void quitter() {
		int confirm = JOptionPane.showOptionDialog(null,
				"Voulez-vous vraiment vous quitter?",
				"Confirmation de fermeture", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == JOptionPane.YES_OPTION) {
			System.exit(0);
		} else {

		}
	}

}
