import evenement.Event;
import evenement.types.EventPeriodique;
import evenement.types.EventRendezVous;
import evenement.types.EventReunion;
import evenement.valueObjects.DureeEvenement;
import evenement.valueObjects.ProprietaireEvenement;
import evenement.valueObjects.TitreEvenement;
import evenement.valueObjects.periodique.FrequenceEvenement;
import evenement.valueObjects.reunion.LieuEvenement;
import evenement.valueObjects.reunion.ParticipantsEvenement;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static final String ART = """
              _____         _                   _                __  __
             / ____|       | |                 | |              |  \\/  |
            | |       __ _ | |  ___  _ __    __| |  __ _  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __
            | |      / _` || | / _ \\| '_ \\  / _` | / _` || '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '__|
            | |____ | (_| || ||  __/| | | || (_| || (_| || |    | |  | | | (_| || | | || (_| || (_| ||  __/| |
             \\_____| \\__,_||_| \\___||_| |_| \\__,_| \\__,_||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_|
                                                                                                __/ | |___/""";

    // Pour se rappeler des choix
    private static String titre;
    private static int annee;
    private static int moisRdv;
    private static int jourRdv;
    private static int heure;
    private static int minute;

    private static void questionsGeneriques(Scanner scanner) {
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

    private static void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");

            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }

    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();
        Scanner scanner = new Scanner(System.in);
        String utilisateur = null;
        boolean continuer = true;

        String[] utilisateurs = new String[99];
        String[] motsDePasses = new String[99];
        int nbUtilisateurs = 0;

        while (true) {
            if (utilisateur == null) {
                System.out.println(ART);
                System.out.println("1 - Se connecter");
                System.out.println("2 - Créer un compte");
                System.out.println("Choix : ");

                switch (scanner.nextLine()) {
                    case "1" -> {
                        System.out.print("Nom d'utilisateur: ");
                        utilisateur = scanner.nextLine();

                        // TODO mots de passe des admins en clair...
                        if (utilisateur.equals("Roger")) {
                            String motDePasse = scanner.nextLine();
                            if (!motDePasse.equals("Chat")) {
                                utilisateur = null;
                            }
                        } else {
                            if (utilisateur.equals("Pierre")) {
                                String motDePasse = scanner.nextLine();
                                if (!motDePasse.equals("KiRouhl")) {
                                    utilisateur = null;
                                }
                            } else {
                                System.out.print("Mot de passe: ");
                                String motDePasse = scanner.nextLine();

                                for (int i = 0; i < nbUtilisateurs; i++) {
                                    assert utilisateurs[i] != null;

                                    if (utilisateurs[i].equals(utilisateur) && motsDePasses[i].equals(motDePasse)) {
                                        utilisateur = utilisateurs[i];
                                    }
                                }
                            }
                        }
                    }
                    case "2" -> {
                        System.out.print("Nom d'utilisateur: ");
                        utilisateur = scanner.nextLine();
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
            }

            while (continuer && utilisateur != null) {
                System.out.println("\nBonjour, " + utilisateur);
                System.out.println("=== Menu Gestionnaire d'Événements ===");
                System.out.println("1 - Voir les événements");
                System.out.println("2 - Ajouter un rendez-vous perso");
                System.out.println("3 - Ajouter une réunion");
                System.out.println("4 - Ajouter un évènement périodique");
                System.out.println("5 - Se déconnecter");
                System.out.print("Votre choix : ");

                String choix = scanner.nextLine();

                switch (choix) {
                    case "1" -> {
                        System.out.println("\n=== Menu de visualisation d'Événements ===");
                        System.out.println("1 - Afficher TOUS les événements");
                        System.out.println("2 - Afficher les événements d'un MOIS précis");
                        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
                        System.out.println("4 - Afficher les événements d'un JOUR précis");
                        System.out.println("5 - Retour");
                        System.out.print("Votre choix : ");

                        choix = scanner.nextLine();

                        switch (choix) {
                            case "1" -> calendar.afficherEvenements();
                            case "2" -> {
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeMois = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le mois (1-12) : ");
                                int mois = Integer.parseInt(scanner.nextLine());

                                LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
                                LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

                                afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
                            }
                            case "3" -> {
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeSemaine = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le numéro de semaine (1-52) : ");
                                int semaine = Integer.parseInt(scanner.nextLine());

                                LocalDateTime debutSemaine = LocalDateTime.now()
                                        .withYear(anneeSemaine)
                                        .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                                        .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                                        .withHour(0).withMinute(0);
                                LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

                                afficherListe(calendar.eventsDansPeriode(debutSemaine, finSemaine));
                            }
                            case "4" -> {
                                System.out.print("Entrez l'année (AAAA) : ");
                                int anneeJour = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le mois (1-12) : ");
                                int moisJour = Integer.parseInt(scanner.nextLine());
                                System.out.print("Entrez le jour (1-31) : ");
                                int jour = Integer.parseInt(scanner.nextLine());

                                LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
                                LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

                                afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
                            }
                        }
                    }
                    case "2" -> {
                        questionsGeneriques(scanner);

                        System.out.print("Durée (en minutes) : ");
                        int duree = Integer.parseInt(scanner.nextLine());

                        EventRendezVous eventRendezVous = new EventRendezVous(new TitreEvenement(titre), new ProprietaireEvenement(utilisateur),
                                LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), new DureeEvenement(duree));

                        calendar.ajouterEvent(eventRendezVous);
                        System.out.println("Événement ajouté.");
                    }
                    case "3" -> {
                        questionsGeneriques(scanner);

                        System.out.print("Durée (en minutes) : ");
                        int duree = Integer.parseInt(scanner.nextLine());

                        System.out.println("Lieu :");
                        String lieu = scanner.nextLine();

                        StringBuilder participants = new StringBuilder(utilisateur);
                        System.out.println("Ajouter un participant ? (oui / non)");

                        while (scanner.nextLine().equals("oui")) {
                            System.out.print("Participants : " + participants);
                            participants.append(", ").append(scanner.nextLine());
                        }

                        EventReunion eventReunion = new EventReunion(new TitreEvenement(titre), new ProprietaireEvenement(utilisateur),
                                LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), new DureeEvenement(duree),
                                new LieuEvenement(lieu), new ParticipantsEvenement(participants.toString()));

                        calendar.ajouterEvent(eventReunion);
                        System.out.println("Événement ajouté.");
                    }
                    case "4" -> {
                        questionsGeneriques(scanner);

                        System.out.print("Frequence (en jours) : ");
                        int frequence = Integer.parseInt(scanner.nextLine());

                        EventPeriodique eventPeriodique = new EventPeriodique(new TitreEvenement(titre), new ProprietaireEvenement(utilisateur),
                                LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), new DureeEvenement(0),
                                new FrequenceEvenement(frequence));

                        calendar.ajouterEvent(eventPeriodique);
                        System.out.println("Événement ajouté.");
                    }
                    default -> {
                        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
                        continuer = scanner.nextLine().trim().equalsIgnoreCase("oui");
                        utilisateur = null;
                    }
                }
            }
        }
    }
}
