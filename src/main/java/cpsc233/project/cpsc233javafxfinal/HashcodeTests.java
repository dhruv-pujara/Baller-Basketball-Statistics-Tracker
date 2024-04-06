package cpsc233.project.cpsc233javafxfinal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HashcodeTests {
    public static void main(String[] args) {

        //Creating Stats objects for each players statistics
        //Jackson's Stats
        Stats points1 = new Stats(new ArrayList<>(List.of(3.0)), StatsType.POINTS);
        Stats assists1 = new Stats(new ArrayList<>(List.of(12.0)), StatsType.ASSISTS);
        Stats blocks1 = new Stats(new ArrayList<>(List.of(9.0)), StatsType.BLOCKS);
        Stats rebounds1 = new Stats(new ArrayList<>(List.of(8.0)), StatsType.REBOUNDS);
        Stats steals1 = new Stats(new ArrayList<>(List.of(10.0)), StatsType.STEALS);
        //Billy's Stats
        Stats points2 = new Stats(new ArrayList<>(List.of(4.0)), StatsType.POINTS);
        Stats assists2 = new Stats(new ArrayList<>(List.of(18.0)), StatsType.ASSISTS);
        Stats blocks2 = new Stats(new ArrayList<>(List.of(12.0)), StatsType.BLOCKS);
        Stats rebounds2 = new Stats(new ArrayList<>(List.of(17.0)), StatsType.REBOUNDS);
        Stats steals2 = new Stats(new ArrayList<>(List.of(11.0)), StatsType.STEALS);

        //AJ's Stats
        Stats points3 = new Stats(new ArrayList<>(List.of(15.0)), StatsType.POINTS);
        Stats assists3 = new Stats(new ArrayList<>(List.of(11.0)), StatsType.ASSISTS);
        Stats blocks3 = new Stats(new ArrayList<>(List.of(7.0)), StatsType.BLOCKS);
        Stats rebounds3 = new Stats(new ArrayList<>(List.of(12.0)), StatsType.REBOUNDS);
        Stats steals3 = new Stats(new ArrayList<>(List.of(8.0)), StatsType.STEALS);

        //Shelly's Stats
        Stats points4 = new Stats(new ArrayList<>(List.of(13.0)), StatsType.POINTS);
        Stats assists4 = new Stats(new ArrayList<>(List.of(19.0)), StatsType.ASSISTS);
        Stats blocks4 = new Stats(new ArrayList<>(List.of(22.0)), StatsType.BLOCKS);
        Stats rebounds4 = new Stats(new ArrayList<>(List.of(14.0)), StatsType.REBOUNDS);
        Stats steals4 = new Stats(new ArrayList<>(List.of(6.0)), StatsType.STEALS);

        //Jordan's Stats
        Stats points5 = new Stats(new ArrayList<>(List.of(19.0)), StatsType.POINTS);
        Stats assists5 = new Stats(new ArrayList<>(List.of(11.0)), StatsType.ASSISTS);
        Stats blocks5 = new Stats(new ArrayList<>(List.of(4.0)), StatsType.BLOCKS);
        Stats rebounds5 = new Stats(new ArrayList<>(List.of(19.0)), StatsType.REBOUNDS);
        Stats steals5 = new Stats(new ArrayList<>(List.of(15.0)), StatsType.STEALS);

        //Add the player stats to an array list
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player(4, "Jackson", Positions.Centre, points1, assists1, blocks1, rebounds1, steals1, 2,1));
        players.add(new Player(8, "Billy", Positions.SmallForward, points2, assists2, blocks2, rebounds2, steals2, 4,2));
        players.add(new Player(2, "AJ", Positions.ShootingGuard, points3, assists3, blocks3, rebounds3, steals3, 7,2));
        players.add(new Player(12, "Shelly", Positions.PowerForward, points4, assists4, blocks4, rebounds4, steals4, 2,0));
        players.add(new Player(19, "Jordan", Positions.PointGuard, points5, assists5, blocks5, rebounds5, steals5, 6,4));

        System.out.println("INITIAL LIST:");
        for (Player player : players) {
            System.out.println(player.toString());
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        players.remove(new Player(12, "Shelly", Positions.PowerForward, points4, assists4, blocks4, rebounds4, steals4, 2, 0));
        System.out.println("UPDATED LIST:");
        for (Player player : players) {
            System.out.println(player.toString());
            System.out.println();
        }

    }

}