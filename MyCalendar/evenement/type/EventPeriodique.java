package evenement.type;

import evenement.Event;
import evenement.valueObject.DureeEvenement;
import evenement.valueObject.ProprietaireEvenement;
import evenement.valueObject.TitreEvenement;
import evenement.valueObject.TypeEvenement;
import evenement.valueObject.periodique.FrequenceEvenement;

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
