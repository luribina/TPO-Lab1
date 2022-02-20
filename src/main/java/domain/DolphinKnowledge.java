package domain;

public class DolphinKnowledge implements Knowledge {
    private final String description;

    public DolphinKnowledge(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isOwnershipPossibleBy(Creature creature) {
        return creature instanceof Dolphin;
    }
}
