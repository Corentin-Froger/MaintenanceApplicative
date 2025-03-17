package evenement.type;

import evenement.Event;
import evenement.valueObject.DureeEvenement;
import evenement.valueObject.ProprietaireEvenement;
import evenement.valueObject.TitreEvenement;
import evenement.valueObject.TypeEvenement;

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
