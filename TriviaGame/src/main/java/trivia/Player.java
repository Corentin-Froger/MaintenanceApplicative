package trivia;

public class Player {
    private final String name;
    private int coins;
    private int position;
    private boolean inPenalty;

    public Player(String n) {
        name = n;
        coins = 0;
        position = 1;
        inPenalty = false;
    }

    public boolean isInPenalty() {
        return inPenalty;
    }

    public int getPosition() {
        return position;
    }

    public int getCoins() {
        return coins;
    }

    public void advance(int steps) {
        position += steps;
        if (position > 12) {
            position -= 12;
        }
    }

    public void addCoin() {
        coins += 1;
    }

    public void escapePenaltyBox() {
        inPenalty = false;
    }

    public void enterPenaltyBox() {
        inPenalty = true;
    }

    @Override
    public String toString() {
        return name;
    }
}
