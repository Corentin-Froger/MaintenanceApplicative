package menu.actions;

import menu.MenuAction;
import utilisateur.Utilisateur;

import static menu.MainMenu.*;

public class ActionCreerCompte extends MenuAction {

    @Override
    public void lancer() {
        System.out.print("Nom d'utilisateur: ");
        utilisateur = new Utilisateur(scanner.nextLine());
        System.out.print("Mot de passe: ");
        String motDePasse = scanner.nextLine();
        System.out.print("Répéter mot de passe: ");

        if (scanner.nextLine().equals(motDePasse)) {
            utilisateurs[nbUtilisateurs] = utilisateur;
            motsDePasses[nbUtilisateurs] = motDePasse;
            nbUtilisateurs++;
        } else {
            System.out.println("Les mots de passes ne correspondent pas...");
            utilisateur = null;
        }
    }
}
