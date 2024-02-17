package per.duyd.training.dsaa.wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class WordNet {
  private final HashSet<String> nounSet;
  private final HashMap<Integer, List<String>> synsetMap;
  private final Digraph digraph;
  private final SAP sap;

  // constructor takes the name of the two input files
  public WordNet(String synsets, String hypernyms) {
    nounSet = new HashSet<>();
    synsetMap = new HashMap<>();
    int V = 0;

    In in = null;
    try {
      in = new In(synsets);
      String line;
      while ((line = in.readLine()) != null) {
        String[] values = line.split(",");
        int synsetId = Integer.parseInt(values[0]);
        List<String> nouns = Arrays.asList(values[1].split(" "));
        synsetMap.put(synsetId, nouns);
        nounSet.addAll(nouns);
        V++;
      }
    } finally {
      if (in != null) {
        in.close();
      }
    }

    digraph = new Digraph(V);

    try {
      in = new In(hypernyms);
      String line;
      while ((line = in.readLine()) != null) {
        String[] values = line.split(",");
        int synsetId = Integer.parseInt(values[0]);
        for (int i = 1; i < values.length; i++) {
          digraph.addEdge(synsetId, Integer.parseInt(values[i]));
        }
      }
    } finally {
      in.close();
    }

    sap = new SAP(digraph);
  }

  // returns all WordNet nouns
  public Iterable<String> nouns() {
    return nounSet;
  }

  // is the word a WordNet noun?
  public boolean isNoun(String word) {
    validateNoun(word);
    return nounSet.contains(word);
  }

  // distance between nounA and nounB (defined below)
  public int distance(String nounA, String nounB) {
    validateNouns(nounA, nounB);

    List<Integer> synsetIdsA = getSynsetIds(nounA);
    List<Integer> synsetIdsB = getSynsetIds(nounB);

    return sap.length(synsetIdsA, synsetIdsB);
  }

  private void validateNoun(String noun) {
    Optional.ofNullable(noun).orElseThrow(IllegalArgumentException::new);
  }

  private void validateNouns(String nounA, String nounB) {
    validateNoun(nounA);
    validateNoun(nounB);

    if (!isNoun(nounA) || !isNoun(nounB)) {
      throw new IllegalArgumentException();
    }
  }

  private List<Integer> getSynsetIds(String nounA) {
    return synsetMap.entrySet().stream()
        .filter(entry -> entry.getValue().contains(nounA))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());
  }

  // a synset (second field of test_synsets.txt) that is the common ancestor of nounA and nounB
  // in a shortest ancestral path (defined below)
  public String sap(String nounA, String nounB) {
    validateNouns(nounA, nounB);

    List<Integer> synsetIdsA = getSynsetIds(nounA);
    List<Integer> synsetIdsB = getSynsetIds(nounB);

    int ancestor = sap.ancestor(synsetIdsA, synsetIdsB);

    return ancestor == -1 ? null : String.join(" ", synsetMap.get(ancestor));
  }

  // do unit testing of this class
  public static void main(String[] args) {

  }
}