package cpsc233.project.cpsc233javafxfinal;

import java.io.*;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomFileReader extends Reader {

    //Constructor
    public CustomFileReader(File file) {
    }

    // Method to convert an array of strings into a CSV format
    private static String convertCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }

    // Method to save player and team data to a file
    public static  void saveFile(File file, ArrayList<Team> teams) throws FileNotFoundException {
        ArrayList<String[]> values = new ArrayList<String[]>();
        for (Team teamlist : teams) {
            for (Player players : teamlist.getPlayers()) {
                ArrayList<Double> points = players.getPoints().getStats();
                ArrayList<Double> assists = players.getAssists().getStats();
                ArrayList<Double> steals = players.getSteals().getStats();
                ArrayList<Double> blocks = players.getBlocks().getStats();
                ArrayList<Double> rebounds = players.getRebounds().getStats();
                ArrayList<String> pointsstring = new ArrayList<>();
                ArrayList<String> assistsstring = new ArrayList<>();
                ArrayList<String> stealsstring = new ArrayList<>();
                ArrayList<String> blocksstring = new ArrayList<>();
                ArrayList<String> reboundsstring = new ArrayList<>();

                for(Double gamepoints: points){
                    pointsstring.add(Double.toString(gamepoints));
                }for(Double gameassists: assists){
                    assistsstring.add(Double.toString(gameassists));
                }
                for(Double gamesteals: steals){
                    stealsstring.add(Double.toString(gamesteals));
                }for(Double gameblocks: blocks){
                    blocksstring.add(Double.toString(gameblocks));
                }for(Double gamerebounds: rebounds){
                    reboundsstring.add(Double.toString(gamerebounds));
                }
                String pointstoadd = String.join(";", pointsstring);
                String assiststoadd = String.join(";", assistsstring);
                String stealstoadd = String.join(";", stealsstring);
                String blockstoadd = String.join(";", blocksstring);
                String reboundstoadd = String.join(";", reboundsstring);
                values.add(new String[]
                        {teamlist.getName(), players.getName(), players.jerseytoString(), players.getPosition().toString(), pointstoadd,
                                assiststoadd, stealstoadd, blockstoadd, reboundstoadd, players.getGamecountString(players.getGamecount())});
            }
        }
        // Write the values to the file
        try (PrintWriter printwriter = new PrintWriter(file)) {
            values.stream()
                    .map(data -> convertCSV(data))
                    .forEach(printwriter::println);
            //I had no clue how to do this, so I used the idea from this link
            //https://www.baeldung.com/java-csv
            //changed some stuff but the crux of the datasaving came from this concept
        }
    }

    // Method to load player and team data from a file
    public static ArrayList<Team> loadDataFromFile(File file) throws FileNotFoundException {
        String line;
        ArrayList<Team> teamlist = new ArrayList<Team>();
        ArrayList<Player> players = new ArrayList<Player>();

        // Read data from the file and populate the ArrayLists
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {

                String[] values = line.split(",");
                int value = 1;
                for(Team teamsinlistforcheck: teamlist) {
                    if (teamsinlistforcheck.getName().equals(values[0])) {
                        value = 0;
                    }
                }if(value == 1) {
                    Team team = new Team(players, values[0]);
                    teamlist.add(team);

                    // Add player data to the respective team
                }for (Team teams : teamlist) {
                    ArrayList<Double> points = new ArrayList<>();
                    ArrayList<Double> assists = new ArrayList<>();
                    ArrayList<Double> steals = new ArrayList<>();
                    ArrayList<Double> blocks = new ArrayList<>();
                    ArrayList<Double> rebounds = new ArrayList<>();
                    if (teams.getName().equals(values[0])) {
                        String strpoints = values[4];
                        String strassists = values[5];
                        String strsteals = values[6];
                        String strblocks = values[7];
                        String strrebounds = values[8];
                        String[] strallpoints = strpoints.split(";");
                        String[] strallassists = strassists.split(";");
                        String[] strallsteals = strsteals.split(";");
                        String[] strallblocks = strblocks.split(";");
                        String[] strallrebounds = strrebounds.split(";");
                        for(String point: strallpoints){
                            points.add(Double.valueOf(point));
                        }for(String assist: strallassists){
                            assists.add(Double.valueOf(assist));
                        }for(String steal: strallsteals){
                            steals.add(Double.valueOf(steal));
                        }for(String block: strallblocks){
                            blocks.add(Double.valueOf(block));
                        }for(String rebound: strallrebounds){
                            rebounds.add(Double.valueOf(rebound));
                        }
                        int gamesplayed = Integer.parseInt(values[9]);

                        // Create Stats objects for player statistics
                        Stats pointsforplayer = new Stats(points, StatsType.POINTS);
                        Stats assistsforplayer = new Stats(assists, StatsType.ASSISTS);
                        Stats stealsforplayer = new Stats(steals, StatsType.STEALS);
                        Stats blocksforplayer = new Stats(blocks, StatsType.BLOCKS);
                        Stats reboundsforplayer = new Stats(rebounds, StatsType.REBOUNDS);
                        Positions position = Positions.valueOf(values[3]);

                        // Create Player object and add it to the team
                        Player player = new Player(Integer.parseInt(values[2]), values[1], position, pointsforplayer, assistsforplayer, blocksforplayer,  reboundsforplayer, stealsforplayer, gamesplayed);
                        teams.addPlayer(player);
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return teamlist;
    }



    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
