package domain;

import java.util.HashSet;
import java.util.Set;

public class Human implements Creature{
    private final Set<Knowledge> humanKnowledge = new HashSet<>();
    private final Habitat habitat = Habitat.LAND;
    @Override
    public boolean addKnowledge(Knowledge knowledge) {
        if (!knowledge.isOwnershipPossibleBy(this)) {
            return false;
        }
        humanKnowledge.add(knowledge);
        return true;
    }

    @Override
    public Set<Knowledge> getKnowledge() {
        return humanKnowledge;
    }

    @Override
    public Habitat getHabitat() {
        return habitat;
    }

    @Override
    public String toString() {
        return "Human{" +
                "humanKnowledge=" + humanKnowledge +
                ", habitat=" + habitat +
                '}';
    }
}
