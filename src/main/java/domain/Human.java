package domain;

import java.util.Set;

public class Human implements Creature{
    private Set<Knowledge> humanKnowledge;
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
}
