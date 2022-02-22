package domain;

import java.util.Set;

public interface Creature {
    boolean addKnowledge(Knowledge knowledge);
    Set<Knowledge> getKnowledge();
    Habitat getHabitat();
}
