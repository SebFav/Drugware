// auteurs: Maud El-Hachem, Sébastien Favron et Pierre-Olivier Berthe-Thérien
// 2015
package drugware_v20;

import java.util.List;

public interface FichiersInterface {

	public void lireClients(List<Client> lesClients);

	public void lireMedicaments(List<Medicament> lesMedicaments);

	public void lirePrescriptions(List<Client> lesClients);
}
