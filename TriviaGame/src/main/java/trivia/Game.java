package trivia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// REFACTOR ME
// TODO
/*
- Il doit y avoir au moins 2 joueurs pour démarrer la partie
- La partie ne doit pas commencer tant que tous les joueurs n'ont pas été ajoutés.
  En d'autres termes, de nouveaux joueurs ne peuvent pas rejoindre après le début du jeu

[DIFFICILE]
- Après une réponse incorrecte, un joueur ne va en prison que s'il échoue à répondre à une
  deuxième question dans la même catégorie. Autrement dit, il/elle se voit offrir une
  « seconde chance » dans la même catégorie.
- Charger les questions à partir de 4 fichiers de propriétés : rock.properties, sports.properties...
- Une série (streak) est une séquence consécutive de réponses correctes pour un joueur donné.
  Après avoir donné 3 réponses correctes consécutives, un joueur gagne 2 points pour chaque
  réponse correcte suivante. Lorsqu'un joueur donne une réponse incorrecte : (a) s'il/elle était
  en série, la série se termine OU (b) s'il n'y avait pas de série, le joueur va en prison.
  (En d'autres termes, avec une série active, un joueur ne va pas en prison, mais perd sa série).
  De plus, la partie doit être remportée avec un double de points.
*/
public class Game implements IGame {
    List<Player> players = new ArrayList<>();
    public final int MAX_PLAYERS = 6;

    private final HashMap<Category, LinkedList<String>> questions;

    int currentPlayerId = 0;
    private Player currentPlayer;

    public Game() {
        questions = new HashMap<>();

        Category[] categories = Category.values();
        for (Category category : categories) {
            LinkedList<String> questionList = new LinkedList<>();

            for (int j = 0; j < 50; j++) {
                questionList.add(createQuestion(category, j));
            }

            questions.put(category, questionList);
        }
    }

    public String createQuestion(Category category, int index) {
        return category.toString() + " Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        // Check max player count
        if (howManyPlayers() < MAX_PLAYERS) {
            // Check if player already exists
            for (Player player : players) {
                if (player.toString().equals(playerName)) {
                    System.out.println(playerName + " is already played");
                    return false;
                }
            }

            Player player = new Player(playerName);
            players.add(player);

            System.out.println(playerName + " was added");
            System.out.println("They are player number " + players.size());

            if (currentPlayer == null) {
                currentPlayer = players.getFirst();
            }
            return true;
        }

        System.out.println("Too many players (" + howManyPlayers() + " / " + MAX_PLAYERS + ")");
        return false;
    }

    public int howManyPlayers() {
        return players.size();
    }

    private void advanceCurrentPlayer(int steps) {
        currentPlayer.advance(steps);
        System.out.println(currentPlayer + "'s new location is " + currentPlayer.getPosition());
    }

    public void roll(int roll) {
        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.isInPenalty()) { //Tries to get out of jail
            boolean isGettingOutOfPenaltyBox = roll % 2 != 0;
            if (isGettingOutOfPenaltyBox) {
                currentPlayer.escapePenaltyBox();
                System.out.println(players.get(currentPlayerId) + " is getting out of the penalty box");
            } else {
                System.out.println(players.get(currentPlayerId) + " is not getting out of the penalty box");
            }
        }

        if (!currentPlayer.isInPenalty()) { //Can play
            advanceCurrentPlayer(roll);
            System.out.println("The category is " + getCurrentCategory());
            askQuestion();
        }
    }

    private void askQuestion() {
        Category currentCategory = getCurrentCategory();
        System.out.println(questions.get(currentCategory).removeFirst());
    }

    private Category getCurrentCategory() {
        int currentPosition = currentPlayer.getPosition() - 1;
        int categoryId = currentPosition % Category.values().length;

        return Category.values()[categoryId];
    }

    public boolean handleCorrectAnswer() {
        if (!currentPlayer.isInPenalty()) {
            System.out.println("Answer was correct!!!!");
            currentPlayer.addCoin();
            System.out.println(currentPlayer + " now has " + currentPlayer.getCoins() + " Gold Coins.");
        }

        boolean winner = didPlayerWin();
        nextPlayer();
        return !winner;
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer + " was sent to the penalty box");
        currentPlayer.enterPenaltyBox();

        nextPlayer();
        return true;
    }

    public void nextPlayer() {
        currentPlayerId++;

        if (currentPlayerId == players.size()) {
            currentPlayerId = 0;
        }

        currentPlayer = players.get(currentPlayerId);
    }

    private boolean didPlayerWin() {
        return currentPlayer.getCoins() >= 6;
    }
}
