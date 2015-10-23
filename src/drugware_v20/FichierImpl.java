// auteurs: Maud El-Hachem, S�bastien Favron et Pierre-Olivier Berthe-Th�rien
// 2015
package drugware_v20;

import java.util.ArrayList;
import java.util.List;

public class FichierImpl implements FichiersInterface{

	@Override
	public void lireClients(List<Client> lesClients) {
		lesClients.add(new Client("ELHM12345678", "ElHachem", "Maud"));
		lesClients.add(new Client("DUFO12345678", "Dufort", "JeanRene"));
		lesClients.add(new Client("PELA12345678", "Peladeau", "PK"));
		lesClients.add(new Client("BRUW12345678", "Wayne", "Bruce"));
		lesClients.add(new Client("DOLA12345678", "Dolan", "Xavier"));
	}

	@Override
	public void lireMedicaments(List<Medicament> lesMedicaments) {
		
		String[] nomMolecule = {"Nexium", "Plavix", "Lyrica", "Botox"};
		String[] nomMarque = {"�som�prazole", "clopidogrel", "pr�gabaline", "toxine Clostridium  botulique de type A"};
		String[][] usages = {{"traiter les sympt�mes de RGO", "soigner les br�lures d'estomac", "traiter les ulc�res d'estomac"},
				{"pr�venir les crises cardiaques", "les accidents vasculaires c�r�braux et certains autres probl�mes circulatoires chez les personnes atteintes d'ath�roscl�rose"},
				{"soulager la douleur neuropathique associ�e � la neuropathie diab�tique p�riph�rique", "soulager la douleur associ�e � la fibromyalgie; prise en charge de la douleur neuropathique centrale"},
				{"bl�pharospasme;strabisme", "dystonie cervicale", "hyperhidrose de l'aisselle;hyperactivit� de la vessie"}};
		double[][] dosesPossibles = {{20, 40}, {75, 300}, {25, 50, 75, 150, 225, 300}, {50, 100, 200}};
		String[] unite = {"mg", "mg", "mg", "unit�s Allergan"}; 
		String[][] interactions = {{"clopidogrel", "dabigatran", "ifosfamide"},
				{"fluvoxamine", "ibuprof�ne", "naprox�ne", "gemfibrozil"},
				{"clonidine", "cyclobenzaprine", "alcool", "diph�nhydramine", "bupr�norphine"},
				{"aclidinium", "atropine", "belladone", "benztropine"}};
		
		int nombresMed = 6;
		
		Medicament med = new Medicament();
		String[] tempUsage = null;
		double[] tempDoses = null;
		String[] tempInteractions = null;
		
		for(int i = 0; i < nombresMed; i++){
			med = null;
			med.setNomMolecule(nomMolecule[0]);
			med.setNomMarque(nomMarque[0]);
			for(int u = 0; u < usages[i].length; u++){
				tempUsage[u] = usages[i][u];
			}
			med.setUsages(tempUsage);
			for(int d = 0; d < dosesPossibles[i].length; d++){
				tempDoses[d] = dosesPossibles[i][d];
			}
			med.setDosesPossibles(tempDoses);
			med.setUnite(unite[i]);
			for(int d = 0; d < interactions[i].length; d++){
				tempInteractions[d] = interactions[i][d];
			}
			med.setInteractions(tempInteractions);
			lesMedicaments.add(med);
		}
	}

	@Override
	public void lirePrescriptions(List<Client> lesClients) {
		lireClients(lesClients);
		
		List<Prescription> prescription = new ArrayList<>();
		prescription.add(new Prescription("Botox", 50.0, 0));
		lesClients.get(0).setPrescriptions(prescription);
		
		prescription.clear();
		prescription.add(new Prescription("Plavix", 300.0, 3));
		prescription.add(new Prescription("Nexium", 40.0, 2));
		lesClients.get(1).setPrescriptions(prescription);
		
		prescription.clear();
		prescription.add(new Prescription("Nexium", 20.0, 3));
		lesClients.get(2).setPrescriptions(prescription);
	}

}
