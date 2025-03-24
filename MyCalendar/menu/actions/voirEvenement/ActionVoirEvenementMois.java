package menu.actions.voirEvenement;

import menu.MenuAction;

import java.time.LocalDateTime;

import static menu.MainMenu.*;

public class ActionVoirEvenementMois extends MenuAction {
    @Override
    public void lancer() {
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeMois = Integer.parseInt(scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int mois = Integer.parseInt(scanner.nextLine());

        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
    }
}
