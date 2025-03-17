package evenement.type;

import evenement.Event;
import evenement.valueObject.DureeEvenement;
import evenement.valueObject.ProprietaireEvenement;
import evenement.valueObject.TitreEvenement;
import evenement.valueObject.TypeEvenement;
import evenement.valueObject.reunion.LieuEvenement;
import evenement.valueObject.reunion.ParticipantsEvenement;

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
