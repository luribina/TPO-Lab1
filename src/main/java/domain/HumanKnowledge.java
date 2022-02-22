package domain;

public class HumanKnowledge implements Knowledge {
    private final String description;

    public HumanKnowledge(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isOwnershipPossibleBy(Creature creature) {
        return creature instanceof Human;
    }
}
