import java.util.Arrays;
import java.util.Optional;

public class Outcast {

  private final WordNet wordnet;

  // constructor takes a WordNet object
  public Outcast(WordNet wordnet) {
    Optional.ofNullable(wordnet).orElseThrow(IllegalArgumentException::new);
    this.wordnet = wordnet;
  }

  // given an array of WordNet nouns, return an outcast
  public String outcast(String[] nouns) {
    Optional.ofNullable(nouns)
        .map(it -> it.length >= 2 ? it : null)
        .orElseThrow(IllegalArgumentException::new);

    String outcast = nouns[0];
    int maxDistance = 0;

    for (String noun : nouns) {
      int totalDistance = Arrays.stream(nouns).filter(otherNoun -> !otherNoun.equals(noun))
          .mapToInt(otherNoun -> wordnet.distance(noun, otherNoun)).sum();

      if (totalDistance > maxDistance) {
        maxDistance = totalDistance;
        outcast = noun;
      }
    }

    return outcast;
  }

  // see test client below
  public static void main(String[] args) {
  }
}
