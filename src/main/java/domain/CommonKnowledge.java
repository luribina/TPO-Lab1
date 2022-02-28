package domain;

import java.util.Objects;

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
        return creature != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonKnowledge that = (CommonKnowledge) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }
}
