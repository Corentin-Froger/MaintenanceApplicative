package evenement;

import evenement.valueObjects.DureeEvenement;
import evenement.valueObjects.ProprietaireEvenement;
import evenement.valueObjects.TitreEvenement;
import evenement.valueObjects.TypeEvenement;

import java.time.LocalDateTime;

public abstract class Event {
    public TypeEvenement type;
    public TitreEvenement title;
    public ProprietaireEvenement proprietaire;
    public LocalDateTime dateDebut;
    public DureeEvenement dureeMinutes;

    public Event(TitreEvenement title, ProprietaireEvenement proprietaire, LocalDateTime dateDebut, DureeEvenement dureeMinutes) {
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
    }

    public abstract String description();
}
