package domain;

public class CommonKnowledge implements Knowledge {

    private final String description;

    public CommonKnowledge(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isOwnershipPossibleBy(Creature creature) {
        return true;
    }
}
