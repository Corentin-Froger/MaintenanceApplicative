package evenement.types;

import evenement.Event;
import evenement.valueObjects.DureeEvenement;
import evenement.valueObjects.ProprietaireEvenement;
import evenement.valueObjects.TitreEvenement;
import evenement.valueObjects.TypeEvenement;
import evenement.valueObjects.reunion.LieuEvenement;
import evenement.valueObjects.reunion.ParticipantsEvenement;

import java.time.LocalDateTime;

public class EventReunion extends Event {
    public LieuEvenement lieu; // utilisé seulement pour REUNION
    public ParticipantsEvenement participants; // séparés par virgules (pour REUNION uniquement)

    public EventReunion(TitreEvenement title, ProprietaireEvenement proprietaire, LocalDateTime dateDebut,
                        DureeEvenement dureeMinutes, LieuEvenement lieu, ParticipantsEvenement participants) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.type = TypeEvenement.REUNION;
        this.lieu = lieu;
        this.participants = participants;
    }

    public String description() {
        return "Réunion : " + title + " à " + lieu + " avec " + participants;
    }
}
