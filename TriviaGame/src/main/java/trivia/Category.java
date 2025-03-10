package trivia;

public enum Category {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock");

    private final String stringValue;
    Category(final String name) {
        stringValue = name;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
