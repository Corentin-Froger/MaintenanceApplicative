import evenement.Event;
import evenement.types.EventPeriodique;
import evenement.types.EventRendezVous;
import evenement.types.EventReunion;
import evenement.valueObjects.DureeEvenement;
import evenement.valueObjects.ProprietaireEvenement;
import evenement.valueObjects.TitreEvenement;
import evenement.valueObjects.TypeEvenement;
import evenement.valueObjects.periodique.FrequenceEvenement;
import evenement.valueObjects.reunion.LieuEvenement;
import evenement.valueObjects.reunion.ParticipantsEvenement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static evenement.valueObjects.TypeEvenement.PERIODIQUE;

public class CalendarManager {
    public List<Event> events;

    public CalendarManager() {
        this.events = new ArrayList<>();
    }

    public void ajouterEvent(Event event) {
        events.add(event);
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new ArrayList<>();

        for (Event e : events) {
            if (e.type.equals(PERIODIQUE)) {
                LocalDateTime temp = e.dateDebut;

                while (temp.isBefore(fin)) {
                    if (!temp.isBefore(debut)) {
                        result.add(e);
                        break;
                    }
                    EventPeriodique e1 = (EventPeriodique) e; // TODO c'est nul les cast
                    temp = temp.plusDays(e1.frequenceJours.jours());
                }
            } else if (!e.dateDebut.isBefore(debut) && !e.dateDebut.isAfter(fin)) {
                result.add(e);
            }
        }
        return result;
    }

    // TODO enlever ?
    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.plusMinutes(e1.dureeMinutes.duree());
        LocalDateTime fin2 = e2.dateDebut.plusMinutes(e2.dureeMinutes.duree());

        if (e1.type.equals(PERIODIQUE) || e2.type.equals(PERIODIQUE)) {
            return false; // Simplification abusive
        }

        return e1.dateDebut.isBefore(fin2) && fin1.isAfter(e2.dateDebut);
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }
}
