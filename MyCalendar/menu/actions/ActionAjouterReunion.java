package menu.actions;

import evenement.type.EventReunion;
import evenement.valueObject.DureeEvenement;
import evenement.valueObject.ProprietaireEvenement;
import evenement.valueObject.TitreEvenement;
import evenement.valueObject.reunion.LieuEvenement;
import evenement.valueObject.reunion.ParticipantsEvenement;
import menu.MenuAction;

import java.time.LocalDateTime;

import static menu.MainMenu.*;

public class ActionAjouterReunion extends MenuAction {

    @Override
    public void lancer() {
        questionsGeneriques(scanner);

        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(scanner.nextLine());

        System.out.println("Lieu :");
        String lieu = scanner.nextLine();

        StringBuilder participants = new StringBuilder(utilisateur.nom());
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
}
