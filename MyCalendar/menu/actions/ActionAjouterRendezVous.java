package menu.actions;

import evenement.type.EventRendezVous;
import evenement.valueObject.DureeEvenement;
import evenement.valueObject.ProprietaireEvenement;
import evenement.valueObject.TitreEvenement;
import menu.MenuAction;

import java.time.LocalDateTime;

import static menu.MainMenu.*;

public class ActionAjouterRendezVous extends MenuAction {

    @Override
    public void lancer() {
        questionsGeneriques(scanner);

        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());

        EventRendezVous eventRendezVous = new EventRendezVous(new TitreEvenement(titre), new ProprietaireEvenement(utilisateur),
                LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), new DureeEvenement(duree));

        calendar.ajouterEvent(eventRendezVous);
        System.out.println("Événement ajouté.");
    }
}
