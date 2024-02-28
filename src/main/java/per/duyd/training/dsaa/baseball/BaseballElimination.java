package per.duyd.training.dsaa.baseball;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import java.util.Optional;

public class BaseballElimination {

  private final String[] teams;
  private final int[] wins;
  private final int[] losses;
  private final int[] remains;
  private final int[][] games;

  // create a baseball division from given filename in format specified below
  public BaseballElimination(String filename) {
    In in = new In(filename);
    int teamsCount = in.readInt();

    this.teams = new String[teamsCount];
    this.losses = new int[teamsCount];
    this.wins = new int[teamsCount];
    this.remains = new int[teamsCount];
    this.games = new int[teamsCount][teamsCount];

    int row = 0;
    while (!in.isEmpty()) {
      String team = in.readString();
      this.teams[row] = team;
      this.wins[row] = in.readInt();
      this.losses[row] = in.readInt();
      this.remains[row] = in.readInt();

      for (int i = 0; i < teamsCount; i++) {
        this.games[row][i] = in.readInt();
      }

      row++;
    }
  }

  // number of teams
  public int numberOfTeams() {
    return teams.length;
  }

  // all teams
  public Iterable<String> teams() {
    return Arrays.asList(this.teams);
  }

  // number of wins for given team
  public int wins(String team) {
    return Optional.ofNullable(team)
        .map(t -> getTeamIndex(team))
        .map(i -> this.wins[i])
        .orElseThrow(IllegalArgumentException::new);
  }

  // number of losses for given team
  public int losses(String team) {
    return Optional.ofNullable(team)
        .map(t -> getTeamIndex(team))
        .map(i -> this.losses[i])
        .orElseThrow(IllegalArgumentException::new);
  }

  // number of remaining games for given team
  public int remaining(String team) {
    return Optional.ofNullable(team)
        .map(t -> getTeamIndex(team))
        .map(i -> this.remains[i])
        .orElseThrow(IllegalArgumentException::new);
  }

  private Integer getTeamIndex(String team) {
    return Optional.ofNullable(team).map(t -> {
      for (int i = 0; i < this.teams.length; i++) {
        if (this.teams[i].equals(t)) {
          return i;
        }
      }
      return null;
    }).orElseThrow(IllegalArgumentException::new);
  }

  // number of remaining games between team1 and team2
  public int against(String team1, String team2) {
    int t1 = Optional.ofNullable(getTeamIndex(team1)).orElseThrow(IllegalArgumentException::new);
    int t2 = Optional.ofNullable(getTeamIndex(team2)).orElseThrow(IllegalArgumentException::new);

    return games[t1][t2];
  }

  // is given team eliminated?
  public boolean isEliminated(String team) {
    return certificateOfElimination(team) != null;
  }

  // subset R of teams that eliminates given team; null if not eliminated
  public Iterable<String> certificateOfElimination(String team) {
    int sourceIndex = getTeamIndex(team);
    int maxWins = this.wins[sourceIndex] + this.remains[sourceIndex];
    Bag<String> certOfEl = new Bag<>();

    for (int i = 0; i < this.teams.length; i++) {
      if (this.wins[i] > maxWins) {
        certOfEl.add(this.teams[i]);
      }
    }

    if (!certOfEl.isEmpty()) {
      return certOfEl;
    }

    Bag<Integer> minCut = getMinCut(sourceIndex);
    minCut.forEach(teamIndex -> certOfEl.add(this.teams[teamIndex]));

    return getAverageMinCutWins(minCut) > this.wins[sourceIndex] + this.remains[sourceIndex] ?
        certOfEl : null;
  }

  private Bag<Integer> getMinCut(int sourceIndex) {
    int sourceVertex = getTeamVertex(sourceIndex); //Use selected team as source vertex
    int gameVertices = this.teams.length * this.teams.length;
    int targetVertex = this.teams.length + gameVertices;
    int networkSize = targetVertex + 1;

    FlowNetwork flowNetwork =
        buildFlowNetwork(networkSize, sourceIndex, targetVertex, sourceVertex);

    FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, sourceVertex, targetVertex);

    return getMinCut(sourceIndex, fordFulkerson);
  }

  private double getAverageMinCutWins(Bag<Integer> minCut) {
    double totalMinCutWins = 0;
    for (int i : minCut) {
      totalMinCutWins += this.wins[i];
      for (int j : minCut) {
        if (i != j) {
          totalMinCutWins += (double) this.games[i][j] / 2;
        }
      }
    }
    return totalMinCutWins / minCut.size();
  }

  private Bag<Integer> getMinCut(int sourceIndex, FordFulkerson fordFulkerson) {
    Bag<Integer> minCut = new Bag<>();
    for (int teamIndex = 0; teamIndex < this.teams.length; teamIndex++) {
      if (teamIndex == sourceIndex) {
        continue;
      }

      int teamVertex = getTeamVertex(teamIndex);

      if (fordFulkerson.inCut(teamVertex)) {
        minCut.add(teamIndex);
      }
    }
    return minCut;
  }

  private FlowNetwork buildFlowNetwork(int networkSize, int sourceIndex, int targetVertex,
                                       int sourceVertex) {
    FlowNetwork flowNetwork = new FlowNetwork(networkSize);
    int sourceMaxWins = this.wins[sourceIndex] + this.remains[sourceIndex];

    for (int i = 0; i < this.teams.length; i++) {
      if (i == sourceIndex) {
        continue;
      }
      int teamVertex = getTeamVertex(i);

      flowNetwork.addEdge(new FlowEdge(teamVertex, targetVertex,
          Math.max(0, sourceMaxWins - this.wins[i])));

      for (int j = i + 1; j < this.teams.length; j++) {
        if (j == sourceIndex) {
          continue;
        }
        int gameVertex = getGameVertex(i, j);
        int otherTeamVertex = getTeamVertex(j);
        flowNetwork.addEdge(new FlowEdge(sourceVertex, gameVertex, this.games[i][j]));
        flowNetwork.addEdge(new FlowEdge(gameVertex, teamVertex, Double.POSITIVE_INFINITY));
        flowNetwork.addEdge(new FlowEdge(gameVertex, otherTeamVertex, Double.POSITIVE_INFINITY));
      }
    }

    return flowNetwork;
  }

  private int getGameVertex(int i, int j) {
    return i * this.teams.length + j;
  }

  private int getTeamVertex(int i) {
    return this.teams.length * this.teams.length + i;
  }

}
