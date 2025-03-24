package menu.actions.voirEvenement;

import menu.MenuAction;

import static menu.MainMenu.calendar;

public class ActionVoirTousEvenement extends MenuAction {
    @Override
    public void lancer() {
        calendar.afficherEvenements();
    }
}
