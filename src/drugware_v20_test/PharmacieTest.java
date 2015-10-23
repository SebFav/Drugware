// auteurs: Maud El-Hachem, Sébastien Favron et Pierre-Olivier Berthe-Thérien
// 2015
package drugware_v20_test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import drugware_v20.Client;
import drugware_v20.FichiersInterface;
import drugware_v20.Medicament;
import drugware_v20.Pharmacie;
import drugware_v20.Prescription;

public class PharmacieTest {

	private FichiersInterface mock;
	private Pharmacie pharmacie;
	private List<Client> listeClient;
	private List<Prescription> listePrescription;
	private List<Medicament> listeMedicament;

	@Before
	public void setUp() throws Exception {
		mock = EasyMock.createMock(FichiersInterface.class);

		pharmacie = new Pharmacie() {
			@Override
			protected FichiersInterface creerFichiers() {
				return mock;
			}
		};
		listeClient = new ArrayList<>();
		listePrescription = new ArrayList<>();
		listeMedicament = new ArrayList<>();
		// Ajouter 1 client à la pharmacie
		listeClient.add(new Client("1234", "Guindon", "Simon"));
		pharmacie.setLesClients(listeClient);
		// Ajouter 1 médicament au cient
		Medicament medicament = new Medicament();
		medicament.setNomMarque("Alcolo");
		medicament.setNomMolecule("alcool");
		listeMedicament.add(medicament);
		pharmacie.setLesMedicaments(listeMedicament);
		// Ajouter 1 prescription au client
		Prescription prescription = new Prescription("Troll", 20.20, 10);
		listePrescription.add(prescription);
		pharmacie.getLesClients().get(0).setPrescriptions(listePrescription);
	}

	@After
	public void tearDown() throws Exception {
		pharmacie = null;
		assertNull(pharmacie);
	}

	@Test
	public void testPharmacie() {
		System.out
				.println("@before - PharmacieTest - Pharmacie - testPharmacie: tester si l'objet pharmacie est créer");
		assertNotNull(pharmacie);
		System.out
				.println("@after - PharmacieTest - Pharmacie - testPharmacie");
	}

	@Test
	public void testGetLesClients() {
		System.out
				.println("@before - PharmacieTest - getLesClients - testGetLesClients: tester l'obtention des clients");
		assertEquals(listeClient, pharmacie.getLesClients());
		System.out
				.println("@after - PharmacieTest - getLesClients - testGetLesClients");
	}

	@Test
	public void testSetLesClients() {
		System.out
				.println("@before - PharmacieTest - setLesClients - testSetLesClients: tester l'application des clients");
		ArrayList<Client> temp = new ArrayList<>();
		temp.add(new Client("5678", "Landry", "Francois"));
		pharmacie.setLesClients(temp);
		assertEquals(temp, pharmacie.getLesClients());
		System.out
				.println("@after - PharmacieTest - setLesClients - testSetLesClients");
	}

	@Test
	public void testGetLesMedicaments() {
		System.out
				.println("@before - PharmacieTest - getLesMedicaments - testGetLesMedicaments: tester l'obtention des médicaments");

		assertEquals(listeMedicament, pharmacie.getLesMedicaments());
		System.out
				.println("@after - PharmacieTest - getLesMedicaments - testGetLesMedicaments");
	}

	@Test
	public void testSetLesMedicaments() {
		System.out
				.println("@before - PharmacieTest - setLesMedicaments - testSetLesMedicaments: tester l'application des médicaments");
		ArrayList<Medicament> temp = new ArrayList<>();
		Medicament medicament = new Medicament();
		medicament.setNomMarque("Medoc");
		temp.add(medicament);
		pharmacie.setLesMedicaments(temp);
		assertEquals(temp, pharmacie.getLesMedicaments());
		System.out
				.println("@after - PharmacieTest - setLesMedicaments - testSetLesMedicaments");
	}

	@Test
	public void testLireClients() {
		System.out
				.println("@before - PharmacieTest - lireClients - testLireClients: tester si la liste des clients est bien obtenue");
		mock.lireClients(listeClient);
		EasyMock.expectLastCall();
		EasyMock.replay(mock);
		pharmacie.lireClients();
		assertEquals(listeClient, pharmacie.getLesClients());
		System.out
				.println("@after - PharmacieTest - lireClients - testLireClients");
	}

	@Test
	public void testLireMedicaments() {
		System.out
				.println("@before - PharmacieTest - lireMedicaments - testLireMedicaments: tester si la liste des médicaments est bien obtenue");
		mock.lireMedicaments(listeMedicament);
		EasyMock.expectLastCall();
		EasyMock.replay(mock);
		pharmacie.lireMedicaments();
		assertEquals(listeMedicament, pharmacie.getLesMedicaments());
		System.out
				.println("@after - PharmacieTest - lireMedicaments - testLireMedicaments");
	}

	@Test
	public void testLirePrescriptions() {
		System.out
				.println("@before - PharmacieTest - lirePrescriptions - testLirePrescriptions: tester si la liste des prescriptions est bien obtenue");
		listeClient.clear();
		pharmacie.lireClients();
		mock.lirePrescriptions(listeClient);
		EasyMock.expectLastCall();
		EasyMock.replay(mock);
		pharmacie.lirePrescriptions();
		assertEquals(listeClient.get(0).getPrescriptions(),
				pharmacie.getPrescriptionsClient("ELHM12345678"));
		assertEquals(listeClient.get(1).getPrescriptions(),
				pharmacie.getPrescriptionsClient("DUFO12345678"));
		assertEquals(listeClient.get(1).getPrescriptions(),
				pharmacie.getPrescriptionsClient("DUFO12345678"));
		assertEquals(listeClient.get(2).getPrescriptions(),
				pharmacie.getPrescriptionsClient("PELA12345678"));
		System.out
				.println("@after - PharmacieTest - lirePrescriptions - testLirePrescriptions");
	}

	@Test
	public void testSiClientExiste() {
		System.out
				.println("@before - PharmacieTest - siClientExiste - testSiClientExiste: tester si client existe ou non");
		assertTrue(pharmacie.siClientExiste("1234"));
		assertFalse(pharmacie.siClientExiste("2345"));
		System.out
				.println("@after - PharmacieTest - siClientExiste - testSiClientExiste");
	}

	@Test
	public void testAjouterClient() {
		System.out
				.println("@before - PharmacieTest - ajouterClient - testAjouterClient: tester l'ajout d'un client");
		pharmacie.ajouterClient("9012", "Berthe", "Pierre");
		assertEquals("9012", pharmacie.getLesClients().get(1).getNAM());
		System.out
				.println("@after - PharmacieTest - ajouterClient - testAjouterClient");
	}

	@Test
	public void testGetPrescriptionsClient() {
		System.out
				.println("@before - PharmacieTest - getPrescriptionsClient - testGetPrescriptionsClient: tester l'obtention de la prescription d'un client");
		assertEquals(listePrescription,
				pharmacie.getPrescriptionsClient("1234"));
		assertEquals(null, pharmacie.getPrescriptionsClient("5678"));
		System.out
				.println("@after - PharmacieTest - getPrescriptionsClient - testGetPrescriptionsClient");
	}

	@Test
	public void testServirPrescription() {
		System.out
				.println("@before - PharmacieTest - servirPrescription - testServirPrescription: tester la modification d'une prescription");
		listePrescription.add(new Prescription("Sleep", 2, 1));
		pharmacie.getLesClients().get(0).setPrescriptions(listePrescription);
		assertTrue(pharmacie.servirPrescription("1234", "Sleep"));
		assertFalse(pharmacie.servirPrescription("1234", "Sleep"));
		assertFalse(pharmacie.servirPrescription("1234", "Test"));
		assertFalse(pharmacie.servirPrescription("5678", "Troll"));
		System.out
				.println("@after - PharmacieTest - servirPrescription - testServirPrescription");
	}

	@Test
	public void testTrouverInteraction() {
		System.out
				.println("@before - PharmacieTest - trouverInteraction - testTrouverInteraction: tester si la comparaison de deux médicaments est valide");
		Medicament medicament1 = new Medicament();
		Medicament medicament2 = new Medicament();
		Medicament medicament3 = new Medicament();
		medicament1.setNomMolecule("frite");
		medicament2.setNomMolecule("sel");
		medicament3.setNomMolecule("vinaigre");
		String[] interactionMed1 = { "sel", "sall" };
		String[] interactionMed2 = {};
		String[] interactionMed3 = { "well", "frite" };
		medicament1.setInteractions(interactionMed1);
		medicament2.setInteractions(interactionMed2);
		medicament3.setInteractions(interactionMed3);
		listeMedicament.add(medicament1);
		listeMedicament.add(medicament2);
		listeMedicament.add(medicament3);
		pharmacie.setLesMedicaments(listeMedicament);
		assertTrue(pharmacie.trouverInteraction("frite", "sel"));
		assertTrue(pharmacie.trouverInteraction("frite", "vinaigre"));
		assertFalse(pharmacie.trouverInteraction("sel", "vinaigre"));
		System.out
				.println("@after - PharmacieTest - trouverInteraction - testTrouverInteraction");
	}

	@Test
	public void testCreerPrescription() {
		System.out
				.println("@before - PharmacieTest - creerPrescription - testCreerPrescription: tester la création d'une prescription");
		ArrayList<Medicament> temp = new ArrayList<>();
		Medicament medicament = new Medicament();
		medicament.setNomMarque("Medoc");
		temp.add(medicament);
		pharmacie.setLesMedicaments(temp);
		listePrescription.add(new Prescription("Sleep", 2, 1));
		pharmacie.getLesClients().get(0).setPrescriptions(listePrescription);
		assertTrue(pharmacie.creerPrescription("1234", "Sleep", 2, 2));
		assertTrue(pharmacie.creerPrescription("1234", "Medoc", 2, 2));
		assertFalse(pharmacie.creerPrescription("8888", "Sleep", 2, 2));
		assertFalse(pharmacie.creerPrescription("1234", "BAD", 2, 2));
		System.out
				.println("@after - PharmacieTest - creerPrescription - testCreerPrescription");
	}
}
