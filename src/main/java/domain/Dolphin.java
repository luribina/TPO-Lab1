package domain;

import java.util.Set;

public class Dolphin implements Creature {
    private Set<Knowledge> dolphinKnowledge;
    private final Habitat habitat = Habitat.WATER;
    @Override
    public boolean addKnowledge(Knowledge knowledge) {
        if (!knowledge.isOwnershipPossibleBy(this)) {
            return false;
        }
        dolphinKnowledge.add(knowledge);
        return true;
    }

    @Override
    public Set<Knowledge> getKnowledge() {
        return dolphinKnowledge;
    }

    @Override
    public Habitat getHabitat() {
        return habitat;
    }
}
