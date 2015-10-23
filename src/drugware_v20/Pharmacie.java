// auteurs: Maud El-Hachem, Sébastien Favron et Pierre-Olivier Berthe-Thérien
// 2015
package drugware_v20;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

public class Pharmacie {
	private List<Client> lesClients;
	private List<Medicament> lesMedicaments;

	protected FichiersInterface creerFichiers() {
		return new FichierImpl();
	}

	public Pharmacie() {
		this.lesMedicaments = new ArrayList<>();
		this.lesClients = new ArrayList<>();
	}

	/**
	 * @return the lesClients
	 */
	public List<Client> getLesClients() {
		return lesClients;
	}

	/**
	 * @param lesClients
	 *            the lesClients to set
	 */
	public void setLesClients(List<Client> lesClients) {
		this.lesClients = lesClients;
	}

	/**
	 * @return the lesMedicaments
	 */
	public List<Medicament> getLesMedicaments() {
		return lesMedicaments;
	}

	/**
	 * @param lesMedicaments
	 *            the lesMedicaments to set
	 */
	public void setLesMedicaments(List<Medicament> lesMedicaments) {
		this.lesMedicaments = lesMedicaments;
	}

	public void lireClients() {
		Fichiers fichier = new Fichiers();
		fichier.lireClients(lesClients);
	}

	public void lireMedicaments() {
		Fichiers fichier = new Fichiers();
		fichier.lireMedicaments(lesMedicaments);
	}

	public void lirePrescriptions() {
		Fichiers fichier = new Fichiers();
		fichier.lirePrescriptions(lesClients);
	}

	public boolean siClientExiste(String NAM) {
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				return true;
			}
		}
		return false;
	}

	public void ajouterClient(String NAM, String nom, String prenom) {
		this.lesClients.add(new Client(NAM, nom, prenom));
	}

	public List<Prescription> getPrescriptionsClient(String NAM) {
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				return itClient.getPrescriptions();
			}
		}
		return null;
	}

	public boolean servirPrescription(String NAM, String medicament) {
		boolean delivree = false;
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				for (Iterator<Prescription> it2 = itClient.getPrescriptions()
						.iterator(); it2.hasNext();) {
					Prescription courante = it2.next();
					if (courante.getMedicamentAPrendre().equalsIgnoreCase(
							medicament))
						if (courante.getRenouvellements() >= 1) {
							courante.setRenouvellements(courante
									.getRenouvellements() - 1);
							delivree = true;
							/*
							 * for(Iterator<Medicament> it3 =
							 * getLesMedicaments().iterator(); it3.hasNext();){
							 * Medicament courant = it3.next();
							 * if(courant.getNomMarque
							 * ().equalsIgnoreCase(medicament)){ double[] doses
							 * = courant.getDosesPossibles(); List<Double>
							 * dosesrecom = new ArrayList<Double>(); for(int i =
							 * 0; i <= doses.length; i++){ if(doses[i] <=
							 * courante.getDose()){ dosesrecom.add(doses[i]); }
							 * } JOptionPane.showMessageDialog(null, dosesrecom,
							 * "Doses recommandées",
							 * JOptionPane.INFORMATION_MESSAGE); } }
							 */

						}
				}
			}
		}
		return delivree;
	}

	public boolean trouverInteraction(String medicament1, String medicament2) {
		for (Iterator<Medicament> it = lesMedicaments.iterator(); it.hasNext();) {
			Medicament courant = it.next();
			if (courant.getNomMolecule().equalsIgnoreCase(medicament1))
				for (int i = 0; i < courant.getInteractions().length; i++)
					if (courant.getInteractions()[i]
							.equalsIgnoreCase(medicament2))
						return true;
			if (courant.getNomMolecule().equalsIgnoreCase(medicament2))
				for (int i = 0; i < courant.getInteractions().length; i++)
					if (courant.getInteractions()[i]
							.equalsIgnoreCase(medicament1))
						return true;
		}
		return false;
	}

	public void ecrireClients() {
		Fichiers fichier = new Fichiers();
		fichier.ecrireClients(lesClients);
	}

	public void ecrirePrescriptions() {
		Fichiers fichier = new Fichiers();
		fichier.ecrirePrescriptions(lesClients);
	}

	public boolean creerPrescription(String NAM, String medicament,
			double dose, int renouv) {
		boolean creee = false;
		for (Iterator<Client> it = lesClients.iterator(); it.hasNext();) {
			Client itClient = it.next();
			if (itClient.getNAM().equals(NAM)) {
				for (Iterator<Prescription> it2 = itClient.getPrescriptions()
						.iterator(); it2.hasNext();) {
					Prescription courante = it2.next();
					if (courante.getMedicamentAPrendre().equalsIgnoreCase(
							medicament)) {
						courante.setDose(dose);
						courante.setRenouvellements(renouv);
						creee = true;
					} else if (!it2.hasNext() && !creee) {
						for (Iterator<Medicament> it3 = getLesMedicaments()
								.iterator(); it3.hasNext();) {
							Medicament courant = it3.next();
							if (courant.getNomMarque().equalsIgnoreCase(
									medicament)) {

								List<Prescription> prescription = new ArrayList<>();
								prescription.add(new Prescription(medicament,
										dose, renouv));
								itClient.setPrescriptions(prescription);
								creee = true;
							}
						}

					}
				}
			}
		}
		return creee;
	}
}
