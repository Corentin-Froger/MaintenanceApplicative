package menu;

import evenement.CalendarManager;
import evenement.Event;
import menu.actions.*;
import utilisateur.Utilisateur;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final String ART = """
              _____         _                   _                __  __
             / ____|       | |                 | |              |  \\/  |
            | |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___   _ __
            | |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\ | '__|
            | |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | | | (_| || | | || (_| || (_| || __/ | |
             \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___| |_|
                                                                                               __/ |
                                                                                              |___/""";

    private static final List<MenuAction> listeActions = Arrays.asList(
            new ActionConnexion(),
            new ActionCreerCompte(),
            new ActionVoirEvenement(),
            new ActionAjouterRendezVous(),
            new ActionAjouterReunion(),
            new ActionAjouterEvenementPeriodique(),
            new ActionDeconnexion()
    );

    // Pour se rappeler des choix
    public static Utilisateur utilisateur = null;
    public static Scanner scanner = new Scanner(System.in);

    public static Utilisateur[] utilisateurs = new Utilisateur[99];
    public static String[] motsDePasses = new String[99];
    public static int nbUtilisateurs = 0;

    public static CalendarManager calendar = new CalendarManager();

    public static boolean continuer = true;
    public static String titre;
    public static int annee;
    public static int moisRdv;
    public static int jourRdv;
    public static int heure;
    public static int minute;

    public static void main(String[] args) {
        while (true) {
            if (utilisateur == null) {
                System.out.println(ART);
                System.out.println("1 - Se connecter");
                System.out.println("2 - Créer un compte");
                System.out.println("Choix : ");

                String choix = scanner.nextLine();

                if (Integer.parseInt(choix) >= 1 && Integer.parseInt(choix) <= 2) {
                    listeActions.get(Integer.parseInt(choix) - 1).lancer();
                }
            }

            while (continuer && utilisateur != null) {
                System.out.println("\nBonjour, " + utilisateur.nom());
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                System.out.println("1 - Voir les événements");
                System.out.println("2 - Ajouter un rendez-vous perso");
                System.out.println("3 - Ajouter une réunion");
                System.out.println("4 - Ajouter un évènement périodique");
                System.out.println("5 - Se déconnecter");
                System.out.print("Votre choix : ");

                String choix = scanner.nextLine();

                if (Integer.parseInt(choix) >= 1 && Integer.parseInt(choix) <= 5) {
                    listeActions.get(Integer.parseInt(choix) + 1).lancer();
                }
            }
        }
    }

    public static void questionsGeneriques(Scanner scanner) {
        System.out.print("Titre de l'événement : ");
        titre = scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        annee = Integer.parseInt(scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        moisRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        jourRdv = Integer.parseInt(scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        heure = Integer.parseInt(scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        minute = Integer.parseInt(scanner.nextLine());
    }

    public static void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");

            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}
