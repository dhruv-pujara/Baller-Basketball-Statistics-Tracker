package cpsc233.project.cpsc233javafxfinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Evaluations class provides methods to evaluate player performance and statistics for teams.
 * This class includes methods to calculate means, evaluate player performances based on standard deviation
 */
public class Evaluations{
    /**
     * Calculates the mean value for two stats representing an offensive evaluation
     * @param stats1 specified stat from points for Player
     * @param stats2 specified stat from assists for Player
     * @param gamesplayed How many games played by Player
     * @return mean from previous games
     * only for offensive team for allstar
     */
    private static Double meansfoeval(Stats stats1, Stats stats2, int gamesplayed,int priorgamecount) {
        Double statsfor1 = stats1.getMean(gamesplayed, priorgamecount);
        Double statsfor2 = stats2.getMean(gamesplayed,priorgamecount);
        Double mean = (statsfor1 + statsfor2) / 2;
        return mean;
    }

    /**
     * Calculates the mean value for three stats representing a defensive evaluation.
     * @param stats1 specified stat from steals for Player
     * @param stats2 specified stat from blocks for Player
     * @param stats3 specified stat from rebounds for Player
     * @param gamesplayed How many games played by Player
     * @return mean from previous games in specified stat
     * only used in allstar roster
     */
    private static Double meansforeval(Stats stats1, Stats stats2, Stats stats3, int gamesplayed, int priorgamecount) {
        Double statsfor1 = stats1.getMean(gamesplayed, priorgamecount);
        Double statsfor2 = stats2.getMean(gamesplayed, priorgamecount);
        Double statsfor3 = stats3.getMean(gamesplayed, priorgamecount);
        Double mean = (statsfor1 + statsfor2 + statsfor3) / 3;
        return mean;
    }

    /**
     * Evaluates the performance of a player based on standard deviation
     * @param stats takes specified stat from player
     * @param gamesplayed How many games played by player
     * @return evaluation of last game's performance
     */
    private static double evaluationsOfStats(Stats stats, int gamesplayed, int priorgamecount) {// Evaluates performance of player based on standard deviation
        Integer evaluation = 0;
        Integer lastvalueposition = lastgameIndex(stats);
        Double lastvalue = stats.getStats().get(lastvalueposition);

        Double SDComparison = stats.getStandardDev(gamesplayed, priorgamecount);
        if (SDComparison == 0) {
            return evaluation;
        }
        Double mean = stats.getMean(gamesplayed, priorgamecount);
        Double SDpositive = mean + SDComparison;
        Double SDnegative = mean - SDComparison;
        if (SDnegative < lastvalue && lastvalue < SDpositive) {
            evaluation = 1;
            return evaluation;
        } else if (SDnegative - SDComparison < lastvalue && lastvalue <= SDnegative) {
            evaluation = -1;
            return evaluation;
        } else if (lastvalue <= SDnegative - SDComparison) {
            evaluation = -2;
            return evaluation;
        } else if (SDpositive + SDComparison > lastvalue && lastvalue >= SDpositive) {
            evaluation = 2;
            return evaluation;
        } else {
            evaluation = 3;
            return evaluation;
        }
    }

    /**
     * CURRENTLY 1 = POINTS, 2 = ASSISTS, 3 = STEALS, 4 = BLOCKS, 5 = REBOUNDS, change if neccessary
     *
     * @param pointseval Evaluation of points for the player
     * @param assistseval Evaluation of assists for the player
     * @param stealseval Evaluation of steals for the player
     * @param blockseval Evaluation of blocks for the player
     * @param reboundseval Evaluation of rebounds for the player
     * @return Evaluation summary based on overall performances
     */
    public static String strevaluation(Double pointseval, Double assistseval, Double stealseval, Double blockseval, Double reboundseval, int gameplayed){
        //Prints evaluation
        Double summation = pointseval + assistseval + stealseval + blockseval + reboundseval;
        if (gameplayed <= 2) {
            String response = "There is not enough data to provide an analysis of this players game.";
            return response;
        }
        Double[] values = new Double[]{pointseval, assistseval};
        for (Double stats : values) {
            if (stats == 3) {
                String response = "This player statistically had an excellent game compared to their previous results!";
                return response;
            }
        }
        for (Double stats : values) {
            if (stats == -2) {
                String response = "This player statistically had a terrible game compared to their previous results!";
                return response;
            }
        }
        if (summation < 0) {
            String response = "This player statistically had a below average game compared to their previous results.";
            return response;
        } else if (summation >= 5) {
            String response = "This player statistically had an above average game compared to their previous results!";
            return response;
        } else {
            String response = "This player statistically had an average game compared to their previous results.";
            return response;
        }
    }

    /**
     * used in lastGamestats()
     * @param stats takes specified stat from player
     * @return index from the last game,
     */
    private static Integer lastgameIndex(Stats stats){
        //Returns the index of stats of last game
        Integer lastvalueposition = 0;

        for(Double items: stats.getStats()){
            lastvalueposition += 1;
        }
        lastvalueposition -= 1;
        return lastvalueposition;
    }

    /**
     * used in fullPrintedEvaluationAndLastGameStats()
     * @param stats Takes specified stat player
     * @return specified stat from players last game
     */

    /**
     * Prints the evaluation of the last game along with the statistics of the player's performance.
     * @param points Takes Stats type "points" from player
     * @param assists Takes Stats type "assists" from player
     * @param steals Takes Stats type "steals" from player
     * @param blocks Takes Stats type "blocks" from player
     * @param rebounds Takes Stats type "rebounds" from player
     * @param gamesplayed Takes int gamePlayed from player
     */
    public static String fullPrintedEvaluationAndLastGameStats(Stats points, Stats assists, Stats steals, Stats blocks, Stats rebounds, int gamesplayed, int priorgamecount){
        //Printed evaluation of last game
        String eval;
        Double pointseval = evaluationsOfStats(points, gamesplayed, priorgamecount);
        Double assistseval = evaluationsOfStats(assists, gamesplayed, priorgamecount);
        Double stealsseval = evaluationsOfStats(steals, gamesplayed, priorgamecount);
        Double blockseval = evaluationsOfStats(blocks, gamesplayed, priorgamecount);
        Double reboundseval = evaluationsOfStats(rebounds, gamesplayed, priorgamecount);
        String evaluation = strevaluation(pointseval, assistseval,  stealsseval, blockseval, reboundseval, gamesplayed);
        eval = "Points: " + points.lastGamestats(gamesplayed, priorgamecount) + " " + "\n" +
        "Assists: " + assists.lastGamestats(gamesplayed, priorgamecount) + " " + "\n" +
        "Steals: "+ steals.lastGamestats(gamesplayed, priorgamecount) + " " + "\n" +
        "Blocks: "+ blocks.lastGamestats(gamesplayed, priorgamecount) + " " + "\n" +
        "Rebounds: " + rebounds.lastGamestats(gamesplayed, priorgamecount) + " " + "\n" + "\n" +
         "Evaluation: \n" + evaluation;
        return eval;
    }

    /**
     * Finds the top offensive and defensive players for a given position.
     * @param teamlist Takes the arraylist of teams
     * @param position Take a position - add more - DEV
     */
    public static String topOffTopDefForPosition(ArrayList<Team> teamlist, Positions position){
        String eval;
        HashMap<Double, Player> TopPlayersOff = new HashMap<>();
        HashMap<Double, Player> TopPlayersDef = new HashMap<>();
        ArrayList<Double> OffStorage = new ArrayList<>();
        ArrayList<Double> DefStorage = new ArrayList<>();
        for(Team teams: teamlist) {
            for (Player players : teams.getPlayers()) {
                if (players.getPosition().equals(position)) {
                    Stats points = players.getPoints();
                    Stats assists = players.getAssists();
                    Stats steals = players.getSteals();
                    Stats blocks = players.getBlocks();
                    Stats rebounds = players.getRebounds();
                    Double meanoff = meansfoeval(points, assists, players.getGamecount(), players.getPriotgamecount());
                    Double meandef = meansforeval(rebounds, steals, blocks, players.getGamecount(), players.getPriotgamecount());
                    TopPlayersOff.put(meanoff, players);
                    TopPlayersDef.put(meandef, players);
                    OffStorage.add(meanoff);
                    DefStorage.add(meandef);
                }
            }
        }
            if (OffStorage.isEmpty()) {
                eval = "There is no player with this position currently";
                return eval;
            } else {
                Collections.sort(OffStorage);
                Collections.sort(DefStorage);
                Player TopOff = TopPlayersOff.get(OffStorage.getLast());
                Player TopDef = TopPlayersDef.get(DefStorage.getLast());
                //JJ                                                            here                                                                                                                              here
                eval = "Offensive All Star: " + TopOff.getName() + " with a " + Math.round(OffStorage.getLast() * 100) / 100.0 + " average between points and assists" + "\n" + "\n" + "Defensive All Star: " + TopDef.getName() + " with a " + Math.round(DefStorage.getLast() * 100)/100.0 + " average between steals, rebounds and blocks";
                return eval;
            }
}

    /**
     *  Finds the top player in a specific stats category.
     * @param teamlist Arraylist of teams
     * @param statsType Type of Stats category to be evaluated
     */
    public static String TopPlayerStat(ArrayList<Team> teamlist, StatsType statsType){
        String top = "";
    HashMap<Double, Player> TopPlayersStats = new HashMap<>();
    ArrayList<Stats> statsforcomparison = new ArrayList<Stats>();
    ArrayList<Double> Statsstorage = new ArrayList<>();
    for(Team teams: teamlist) {
        for (Player players : teams.getPlayers()) {
            Stats points = players.getPoints();
            statsforcomparison.add(points);
            Stats assists = players.getAssists();
            statsforcomparison.add(assists);
            Stats steals = players.getSteals();
            statsforcomparison.add(steals);
            Stats blocks = players.getBlocks();
            statsforcomparison.add(blocks);
            Stats rebounds = players.getRebounds();
            statsforcomparison.add(rebounds);
            for (Stats statsfound : statsforcomparison) {
                if (statsfound.getType() == statsType) {
                    Double mean = statsfound.getMean(players.getGamecount(), players.getPriotgamecount());
                    TopPlayersStats.put(mean, players);
                    Statsstorage.add(mean);

                }
            }statsforcomparison.clear();
        }
    }
        if (Statsstorage.isEmpty()) {
            return null;
        } else {
            Collections.sort(Statsstorage);
            Player Topplayer = TopPlayersStats.get(Statsstorage.getLast());
            top = "Best player in " + statsType + ": \n" + Topplayer.getName() + " with " + Statsstorage.getLast();
        }
        return top;
}
}
