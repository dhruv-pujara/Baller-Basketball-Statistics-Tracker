package cpsc233.project.cpsc233javafxfinal;

import java.util.*;

/**
 * Represents statistical data for a player, such as points, assists, rebounds, etc.
 *
 */
public class Stats {
    private final StatsType type;
    private ArrayList<Double> stats;

    /**
     *  Constructor to initialize a Stats object
     * @param stats The list of values representing the statistics over multiple games
     * @param type The type of statistic (eg. points, assists, rebounds)
     */
    public Stats(ArrayList<Double> stats, StatsType type) {
        this.stats = stats;
        this.type = type;
    }
    public String toString(){
        String value = "";
        for(Double stats: getStats()){
            value = value + "    " + stats;
        }
        return value;
    }


    /**
     * Gets the list of statistical values.
     * @return The list of statistical values
     */
    public ArrayList<Double> getStats() {
        return stats;
    }


    /**
     * Sets the list of statistical values and adds a new value to it.
     * @param stats The list of statistical values to be set
     * @param value The new value to be added to the list
     */
    public void setStats(ArrayList<Double> stats, Double value) {
        this.stats = stats;
        this.stats.add(value);
    }

    /**
     * Gets the type of statistic.
     * @return The type of statistic (eg. points, assists, rebounds)
     */
    public StatsType getType() {
        return type;
    }

    /**
     * Calculates the mean value of the statistic.
     * @param gamesplayed The number of games played
     * @return The mean value of the statistic
     */
    public Double getMean(int gamesplayed) {
        Double summation = 0.0;
        int divisor = 0;
        if(getStats().isEmpty()) {
            return 0.0;
        }else{
            for (Double values : getStats()) {
                summation = summation + values;
                divisor += 1;
            }
            Double mean = summation / divisor;
            return mean;
        }
    }

    /**
     * Calculates the standard deviation of the statistic.
     * @param gamesplayed The number of games played
     * @return The standard deviation of the statistic
     */
    public double getStandardDev(int gamesplayed) {
        double summation = 0;
        Integer divisor = 0;
        for (Double values : getStats()) {
            divisor += 1;
        }
        if (divisor < 3) {
            return 0;
        } else {
            Double mean = getMean(gamesplayed);
            for (Double values : getStats()) {
                summation = summation + Math.pow((values - mean), 2);
            }
            Double standardDev = Math.sqrt(summation / (divisor - 1));
            return standardDev;
        }
    }
    public Double lastGamestats(int gamesplayed){
        return getStats().getLast();
    }
}
