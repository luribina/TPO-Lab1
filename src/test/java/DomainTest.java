import domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Test
    void knowledgeOwnershipNullTest() {
        for (Knowledge k : knowledges) {
            assertFalse(k.isOwnershipPossibleBy(null));
        }
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

    @Test
    void multipleKnowledgeSameDescriptionTest() {
        final Set<Knowledge> knowledges = new HashSet<>(Arrays.asList(
                new CommonKnowledge("foo"),
                new CommonKnowledge("foo"),

                new DolphinKnowledge("foo"),
                new DolphinKnowledge("foo"),

                new HumanKnowledge("foo"),
                new HumanKnowledge("foo")
        ));

        assertEquals(3, knowledges.size());

        for (Knowledge k : knowledges) {
            assertEquals("foo", k.getDescription());
        }
    }

    @Test
    void multipleHumanDifferentKnowledgeDescriptionTest() {
        Creature h1 = new Human();
        Creature h2 = new Human();

        Knowledge hk1 = new HumanKnowledge("hk1");
        Knowledge hk2 = new HumanKnowledge("hk2");

        Knowledge ck1 = new CommonKnowledge("ck1");
        Knowledge ck2 = new CommonKnowledge("ck2");

        Knowledge dk1 = new DolphinKnowledge("dk1");

        h1.addKnowledge(hk1);
        h1.addKnowledge(hk2);
        h1.addKnowledge(ck1);
        h1.addKnowledge(dk1);

        h2.addKnowledge(hk1);
        h2.addKnowledge(ck2);
        h2.addKnowledge(dk1);

//        assertEquals(new HashSet<>(Arrays.asList(hk1, hk2, ck1)), h1.getKnowledge());
        assertEquals(new HashSet<>(Arrays.asList(hk1, ck2)), h2.getKnowledge());
    }

    @Test
    void sameHumanSameKnowledgeRepeatDescriptionTest() {
        for (Knowledge k : knowledges) {
            Creature h1 = new Human();

            for (int i = 0; i < 7; ++i) {
                h1.addKnowledge(k);

                if (k.isOwnershipPossibleBy(h1)) {
                    assertEquals(1, h1.getKnowledge().size());
                    assertTrue(h1.getKnowledge().contains(k));
                } else {
                    assertEquals(0, h1.getKnowledge().size());
                    assertFalse(h1.getKnowledge().contains(k));
                }
            }
        }
    }
}
