package evenement.types;

import evenement.Event;
import evenement.valueObjects.DureeEvenement;
import evenement.valueObjects.ProprietaireEvenement;
import evenement.valueObjects.TitreEvenement;
import evenement.valueObjects.TypeEvenement;
import evenement.valueObjects.periodique.FrequenceEvenement;

import java.time.LocalDateTime;

public class EventPeriodique extends Event {
    public FrequenceEvenement frequenceJours; // uniquement pour PERIODIQUE

    public EventPeriodique(TitreEvenement title, ProprietaireEvenement proprietaire, LocalDateTime dateDebut,
                           DureeEvenement dureeMinutes, FrequenceEvenement frequenceJours) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.type = TypeEvenement.PERIODIQUE;
        this.frequenceJours = frequenceJours;
    }

    public String description() {
        return "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
    }
}
