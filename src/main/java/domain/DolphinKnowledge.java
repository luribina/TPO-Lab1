package domain;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DolphinKnowledge that = (DolphinKnowledge) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
