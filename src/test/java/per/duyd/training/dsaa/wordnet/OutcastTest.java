package per.duyd.training.dsaa.wordnet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class OutcastTest {
  private final WordNet wordNet = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
  private final Outcast outcast = new Outcast(wordNet);

  @ParameterizedTest
  @CsvSource({"outcast5.txt,table", "outcast8.txt,bed", "outcast11.txt,potato"})
  void testSampleTestData(String outcastFile, String outcastNoun) {
    In in = new In("wordnet/" + outcastFile);
    String[] nouns = in.readAllStrings();
    assertEquals(outcastNoun, outcast.outcast(nouns));
  }

}