// auteurs: Maud El-Hachem, Sébastien Favron et Pierre-Olivier Berthe-Thérien
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
		String[] nomMarque = {"ésoméprazole", "clopidogrel", "prégabaline", "toxine Clostridium  botulique de type A"};
		String[][] usages = {{"traiter les symptômes de RGO", "soigner les brûlures d'estomac", "traiter les ulcères d'estomac"},
				{"prévenir les crises cardiaques", "les accidents vasculaires cérébraux et certains autres problèmes circulatoires chez les personnes atteintes d'athérosclérose"},
				{"soulager la douleur neuropathique associée à la neuropathie diabétique périphérique", "soulager la douleur associée à la fibromyalgie; prise en charge de la douleur neuropathique centrale"},
				{"blépharospasme;strabisme", "dystonie cervicale", "hyperhidrose de l'aisselle;hyperactivité de la vessie"}};
		double[][] dosesPossibles = {{20, 40}, {75, 300}, {25, 50, 75, 150, 225, 300}, {50, 100, 200}};
		String[] unite = {"mg", "mg", "mg", "unités Allergan"}; 
		String[][] interactions = {{"clopidogrel", "dabigatran", "ifosfamide"},
				{"fluvoxamine", "ibuprofène", "naproxène", "gemfibrozil"},
				{"clonidine", "cyclobenzaprine", "alcool", "diphénhydramine", "buprénorphine"},
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
