package menu.actions;

import menu.MenuAction;

import static menu.MainMenu.*;

public class ActionDeconnexion extends MenuAction {

    @Override
    public void lancer() {
        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
        continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");
        utilisateur = null;
    }
}
