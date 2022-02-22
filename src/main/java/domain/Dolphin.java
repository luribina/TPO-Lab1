package domain;

import java.util.HashSet;
import java.util.Set;

public class Dolphin implements Creature {
    private final Set<Knowledge> dolphinKnowledge = new HashSet<>();
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

    @Override
    public String toString() {
        return "Dolphin{" +
                "dolphinKnowledge=" + dolphinKnowledge +
                ", habitat=" + habitat +
                '}';
    }
}
