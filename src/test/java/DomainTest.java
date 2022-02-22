import domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DomainTest {
    private final List<Knowledge> knowledges = Arrays.asList(
            new HumanKnowledge("foo"),
            new DolphinKnowledge("foo"),
            new CommonKnowledge("foo"));

    @Test
    void humanHabitatTest() {
        Creature human = new Human();
        assertEquals(human.getHabitat(), Habitat.LAND, "Human habitat must be LAND. Habitat: " + human.getHabitat());
    }

    @Test
    void dolphinHabitatTest() {
        Creature dolphin = new Dolphin();
        assertEquals(dolphin.getHabitat(), Habitat.WATER, "Dolphin habitat must be WATER. Habitat: " + dolphin.getHabitat());
    }

    @Test
    void knowledgeGetDescriptionTest() {
        for (Knowledge k : knowledges) {
            assertEquals(k.getDescription(), "foo");
        }
    }

    @Test
    void humanKnowledgeTest() {
        Knowledge humanKnowledge = new HumanKnowledge("foo");
        assertTrue(humanKnowledge.isOwnershipPossibleBy(new Human()));
        assertFalse(humanKnowledge.isOwnershipPossibleBy(new Dolphin()));
    }

    @Test
    void dolphinKnowledgeTest() {
        Knowledge dolphinKnowledge = new DolphinKnowledge("foo");
        assertFalse(dolphinKnowledge.isOwnershipPossibleBy(new Human()));
        assertTrue(dolphinKnowledge.isOwnershipPossibleBy(new Dolphin()));
    }

    @Test
    void commonKnowledgeTest() {
        Knowledge commonKnowledge = new CommonKnowledge("foo");
        assertTrue(commonKnowledge.isOwnershipPossibleBy(new Human()));
        assertTrue(commonKnowledge.isOwnershipPossibleBy(new Dolphin()));
    }

    private static Stream<Arguments> provideCreatures() {
        return Stream.of(
                Arguments.of(new Human()),
                Arguments.of(new Dolphin())
        );
    }

    @ParameterizedTest
    @MethodSource("provideCreatures")
    public void creatureAddKnowledgeTest(Creature creature) {
        for (Knowledge k : knowledges) {
            if (k.isOwnershipPossibleBy(creature)) {
                assertTrue(creature.addKnowledge(k));
                assertTrue(creature.getKnowledge().contains(k));
            } else {
                assertFalse(creature.addKnowledge(k));
                assertFalse(creature.getKnowledge().contains(k));
            }
        }
    }
}
