package cpsc233.project.cpsc233javafxfinal;

import java.util.*;

/**
 * This class represents a player with various statistics
 *
 */
public class Player {
    protected Player player;
    private final int jersey;
    private String name = null;
    private Positions position = null;
    private Stats points;
    private Stats assists;
    private Stats rebounds;
    private Stats blocks;
    private Stats steals;
    private int gamecount;
    private int priotgamecount;

    /**
     * Constructor
     * @param jersey Int Jersey - jersey number
     * @param name String name - will also tbe the name of Player Type
     * @param position Position place - position player is on the court
     * @param points StatType points - how many points a player has
     * @param assists StatsType assists - how many assists a player has
     * @param blocks StatsType blocks -  how many blocks a player has
     * @param rebounds StatsType rebounds - how many rebounds a player has
     * @param steals StatsType steals - how many steals a player has
     *
     */
    // Constructor
    public Player(int jersey, String name, Positions position, Stats points, Stats assists, Stats blocks, Stats rebounds, Stats steals, int gamecount) {
        this.jersey = jersey;
        this.name = name;
        this.position = position;
        this.points = points;
        this.assists = assists;
        this.steals = steals;
        this.rebounds = rebounds;
        this.blocks = blocks;
        this.gamecount = gamecount;
    }

//    public Player(int jersey, String jackson, String centre, int i, int i1, int i2, int i3, int i4, int gamecount) {
//    }

    /**
     * Converts the jersey number to a string.
     * @return String representation of the jersey number
     */
    public String jerseytoString(){
        String jerseyString = Integer.toString(jersey);
        return jerseyString;
    }

    /**
     * Sets the rebound statistics of the player
     * @param rebounds Stats representing rebounds made by the player
     */
    public void setRebounds(Stats rebounds) {
        this.rebounds = rebounds;
    }

    /**
     * Sets the steals statistics of the player
     * @param steals Stats representing steals made by the player
     */
    public void setSteals(Stats steals) {
        this.steals = steals;
    }

    /**
     * Sets the points statistics of the player
     * @param points Stats representing steals made by the player
     */
    public void setPoints(Stats points) {
        this.points = points;
    }

    /**
     * Sets the player object
     * @param player Player object
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     *
     * @return
     */
    public Stats getRebounds() {
        return rebounds;
    }

    /**
     *
     * @param blocks
     */
    public void setBlocks(Stats blocks) {
        this.blocks = blocks;
    }

    /**
     *
     * @return
     */
    public Stats getAssists() {
        return assists;
    }

    /**
     *
     * @param assists
     */
    public void setAssists(Stats assists) {
        this.assists = assists;
    }

    /**
     *
     * @return
     */
    public Stats getSteals() {
        return steals;
    }

    /**
     *
     * @return
     */
    public Stats getPoints() {
        return points;
    }

    /**
     *
     * @return
     */
    public Stats getBlocks() {
        return blocks;
    }

    /**
     *
     * @return
     */
    public int getJersey() {
        return jersey;
    }

    /**
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param statistics
     * @return
     */
    public Double meansfoeval(Stats statistics){
        return null;
    }

    /**
     *
     * @return
     */
    public Positions getPosition() {
        return position;
    }

    /**
     *
     * @return
     */
    public int getGamecount() {
        return gamecount;
    }

    /**
     *
     * @param gamecount
     */
    public void addGamecount(int gamecount) {
        this.gamecount = gamecount + 1;
    }

    /**
     *
     * @param gamecount
     */
    public void setGamecount(int gamecount) {
        this.gamecount = gamecount;
    }

    /**
     *
     * @param gamecount
     * @return
     */
    public String getGamecountString(int gamecount) {
        String gamecounted = Integer.toString(gamecount);
        return gamecounted;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString(){
        String output = String.format(getName() + "'s player profile:\n" + "Jersey Number: " + getJersey() + "\n" +
                "Position: " + getPosition() + "\n" + "Games Played: " + getGamecount() + "\n" + "Average Points: " + getPoints().getMean(getGamecount()) + "\n"
                 + "Average Assists: " + getAssists().getMean(getGamecount()) + "\n" + "Average Steals: " + getSteals().getMean(getGamecount()) + "\n" +
                "Average Blocks: " + getBlocks().getMean(getGamecount()) + "\n" + "Average Rebounds: " + getRebounds().getMean(getGamecount()));
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player1 = (Player) o;
        return jersey == player1.jersey && gamecount == player1.gamecount && Objects.equals(player, player1.player) && Objects.equals(name, player1.name) && position == player1.position && Objects.equals(points, player1.points) && Objects.equals(assists, player1.assists) && Objects.equals(rebounds, player1.rebounds) && Objects.equals(blocks, player1.blocks) && Objects.equals(steals, player1.steals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, jersey, name, position, points, assists, rebounds, blocks, steals, gamecount);
    }

}

