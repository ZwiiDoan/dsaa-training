package per.duyd.training.dsaa.baseball;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Test;

class BaseballEliminationTest {

  @Test
  void shouldReadInputDataCorrectly() {
    List<String> expectedTeams = Arrays.asList("Atlanta", "Philadelphia", "New_York", "Montreal");

    BaseballElimination baseballElimination = new BaseballElimination("baseball/teams4.txt");

    assertEquals(4, baseballElimination.numberOfTeams());
    baseballElimination.teams()
        .forEach(actualTeam -> assertTrue(expectedTeams.contains(actualTeam)));

    assertEquals(6, baseballElimination.against("Atlanta", "New_York"));
    assertEquals(78, baseballElimination.losses("New_York"));
    assertEquals(80, baseballElimination.wins("Philadelphia"));
    assertEquals(3, baseballElimination.remaining("Montreal"));

    assertTrue(baseballElimination.isEliminated("Montreal"));
  }

  @Test
  void shouldGetEliminatedTeam() {
    List<String> expectedCoEs =
        Arrays.asList("New_York", "Baltimore", "Boston", "Toronto");

    BaseballElimination baseballElimination = new BaseballElimination("baseball/teams5.txt");

    assertTrue(baseballElimination.isEliminated("Detroit"));

    AtomicInteger actualSize = new AtomicInteger();
    baseballElimination.certificateOfElimination("Detroit").forEach(actualCoE -> {
      actualSize.getAndIncrement();
      assertTrue(expectedCoEs.contains(actualCoE));
    });

    assertEquals(actualSize.get(), expectedCoEs.size());
  }

  @Test
  void shouldGetTrivialEliminatedTeam() {
    List<String> expectedCoEs = Collections.singletonList("Gryffindor");

    BaseballElimination baseballElimination = new BaseballElimination("baseball/teams4b.txt");

    assertTrue(baseballElimination.isEliminated("Hufflepuff"));

    AtomicInteger actualSize = new AtomicInteger();
    baseballElimination.certificateOfElimination("Hufflepuff").forEach(actualCoE -> {
      actualSize.getAndIncrement();
      assertTrue(expectedCoEs.contains(actualCoE));
    });

    assertEquals(actualSize.get(), expectedCoEs.size());
  }

  @Test
  void shouldReturnNullCoE() {
    BaseballElimination baseballElimination = new BaseballElimination("baseball/teams5.txt");

    assertFalse(baseballElimination.isEliminated("Baltimore"));
    assertNull(baseballElimination.certificateOfElimination("Baltimore"));
  }
}