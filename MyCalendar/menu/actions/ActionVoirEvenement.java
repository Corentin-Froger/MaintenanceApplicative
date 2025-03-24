package menu.actions;

import menu.MenuAction;
import menu.actions.voirEvenement.ActionVoirEvenementJour;
import menu.actions.voirEvenement.ActionVoirEvenementMois;
import menu.actions.voirEvenement.ActionVoirEvenementSemaine;
import menu.actions.voirEvenement.ActionVoirTousEvenement;

import java.util.Arrays;
import java.util.List;

import static menu.MainMenu.scanner;

public class ActionVoirEvenement extends MenuAction {

    private static final List<MenuAction> listeActions = Arrays.asList(
            new ActionVoirTousEvenement(),
            new ActionVoirEvenementMois(),
            new ActionVoirEvenementSemaine(),
            new ActionVoirEvenementJour()
    );

    @Override
    public void lancer() {
        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");

        String choix = scanner.nextLine();

        if (Integer.parseInt(choix) >= 1 && Integer.parseInt(choix) <= 5) {
            listeActions.get(Integer.parseInt(choix) - 1).lancer();
        }
    }
}
