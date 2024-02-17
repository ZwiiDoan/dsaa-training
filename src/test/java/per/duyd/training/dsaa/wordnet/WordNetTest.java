package per.duyd.training.dsaa.wordnet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordNetTest {
  private WordNet wordnet;

  @BeforeEach
  public void setUp() {
    wordnet = new WordNet("wordnet/test_synsets.txt", "wordnet/test_hypernyms.txt");
  }

  // Constructor, nouns, isNoun tests (likely remain unchanged) ...

  @Test
  public void testDistance_closelyRelated() {
    assertEquals(2, wordnet.distance("dog", "cat"));
  }

  @Test
  public void testDistance_distantlyRelated() {
    assertEquals(3, wordnet.distance("dog", "plant"));
  }

  @Test
  public void testDistance_sameNoun() {
    assertEquals(0, wordnet.distance("vertebrate", "vertebrate"));
  }

  @Test
  public void testDistance_unrelatedNouns() {
    assertEquals(-1, wordnet.distance("bird", "physical_entity"));
  }

  @Test
  public void testSap_basicCase() {
    assertEquals("mammal", wordnet.sap("mammal", "dog"));
  }

  @Test
  public void testSap_moreDistantRelationship() {
    assertEquals("organism", wordnet.sap("bird", "plant"));
  }

  @Test
  public void testSap_noCommonAncestor() {
    assertNull(wordnet.sap("physical_entity", "mammal")); // Assuming 'fish' is not in your data
  }
}

