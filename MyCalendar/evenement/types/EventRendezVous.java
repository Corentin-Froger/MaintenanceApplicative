package evenement.types;

import evenement.Event;
import evenement.valueObjects.DureeEvenement;
import evenement.valueObjects.ProprietaireEvenement;
import evenement.valueObjects.TitreEvenement;
import evenement.valueObjects.TypeEvenement;

import java.time.LocalDateTime;

public class EventRendezVous extends Event {
    public EventRendezVous(TitreEvenement title, ProprietaireEvenement proprietaire, LocalDateTime dateDebut, DureeEvenement dureeMinutes) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.type = TypeEvenement.RDV_PERSONNEL;
    }

    public String description() {
        return "RDV : " + title + " à " + dateDebut.toString();
    }
}
