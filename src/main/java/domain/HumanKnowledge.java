package domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanKnowledge that = (HumanKnowledge) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
