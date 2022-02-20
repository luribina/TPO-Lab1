package domain;

public interface Knowledge {
    String getDescription();
    boolean isOwnershipPossibleBy(Creature creature);
}
