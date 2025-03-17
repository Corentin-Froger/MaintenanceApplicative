import ValueObjects.DureeEvenement;
import ValueObjects.ProprietaireEvenement;
import ValueObjects.TitreEvenement;
import ValueObjects.TypeEvenement;

import java.time.LocalDateTime;

public class Event {
    public TypeEvenement type; // "RDV_PERSONNEL", "REUNION", "PERIODIQUE"
    public TitreEvenement title;
    public ProprietaireEvenement proprietaire;
    public LocalDateTime dateDebut;
    public DureeEvenement dureeMinutes;
    public String lieu; // utilisé seulement pour REUNION
    public String participants; // séparés par virgules (pour REUNION uniquement)
    public int frequenceJours; // uniquement pour PERIODIQUE

    public Event(TypeEvenement type, TitreEvenement title, ProprietaireEvenement proprietaire, LocalDateTime dateDebut,
                 DureeEvenement dureeMinutes, String lieu, String participants, int frequenceJours) {
        this.type = type;
        this.title = title;
        this.proprietaire = proprietaire;
        this.dateDebut = dateDebut;
        this.dureeMinutes = dureeMinutes;
        this.lieu = lieu;
        this.participants = participants;
        this.frequenceJours = frequenceJours;
    }

    public String description() {
        return switch (type) {
            case RDV_PERSONNEL -> "RDV : " + title + " à " + dateDebut.toString();
            case REUNION -> "Réunion : " + title + " à " + lieu + " avec " + participants;
            case PERIODIQUE -> "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
        };
    }
}
