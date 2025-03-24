package menu.actions;

import evenement.type.EventPeriodique;
import evenement.valueObject.DureeEvenement;
import evenement.valueObject.ProprietaireEvenement;
import evenement.valueObject.TitreEvenement;
import evenement.valueObject.periodique.FrequenceEvenement;
import menu.MenuAction;

import java.time.LocalDateTime;

import static menu.MainMenu.*;

public class ActionAjouterEvenementPeriodique extends MenuAction {

    @Override
    public void lancer() {
        questionsGeneriques(scanner);

        System.out.print("Frequence (en jours) : ");
        int frequence = Integer.parseInt(scanner.nextLine());

        EventPeriodique eventPeriodique = new EventPeriodique(new TitreEvenement(titre), new ProprietaireEvenement(utilisateur),
                LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), new DureeEvenement(0),
                new FrequenceEvenement(frequence));

        calendar.ajouterEvent(eventPeriodique);
        System.out.println("Événement ajouté.");
    }
}
