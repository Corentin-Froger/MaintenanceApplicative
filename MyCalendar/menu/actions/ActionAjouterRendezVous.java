package menu.actions;

import menu.MenuAction;
import utilisateur.Utilisateur;

import static menu.MainMenu.*;

public class ActionAjouterRendezVous extends MenuAction {

    @Override
    public void lancer() {
        System.out.print("Nom d'utilisateur: ");
        utilisateur = new Utilisateur(scanner.nextLine());
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();

        if (utilisateur.isAdmin()) {
            if (!Utilisateur.isAdminPasswordValid(utilisateur, motDePasse)) {
                utilisateur = null;
            }
        } else { // Utilisateur lambda
            for (int i = 0; i < nbUtilisateurs; i++) {
                assert utilisateurs[i] != null;

                if (utilisateurs[i].equals(utilisateur) && motsDePasses[i].equals(motDePasse)) {
                    utilisateur = utilisateurs[i];
                }
            }
        }
    }
}
