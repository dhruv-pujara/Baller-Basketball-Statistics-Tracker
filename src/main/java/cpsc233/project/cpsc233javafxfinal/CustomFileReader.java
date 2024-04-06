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
    private String convertCSV(String[] data) {
        return Stream.of(data)
                .collect(Collectors.joining(","));
    }

    // Method to save player and team data to a file
    public void saveFile(File file, ArrayList<Team> teams) throws FileNotFoundException {
        ArrayList<String[]> values = new ArrayList<String[]>();
        for (Team teamlist : teams) {
            for (Player players : teamlist.getPlayers()) {
                values.add(new String[]
                        {teamlist.getName(), players.getName(), players.jerseytoString(), players.getPosition().toString(), players.getPoints().getMean(players.getGamecount(), players.getPriotgamecount()).toString(),
                                players.getAssists().getMean(players.getGamecount(),players.getPriotgamecount()).toString(), players.getSteals().getMean(players.getGamecount(),players.getPriotgamecount()).toString()
                                , players.getBlocks().getMean(players.getGamecount(),players.getPriotgamecount()).toString(), players.getRebounds().getMean(players.getGamecount(),players.getPriotgamecount()).toString(), players.getGamecountString(players.getGamecount()), players.getGamecountString(players.getGamecount())});
            }
        }
        // Write the values to the file
        try (PrintWriter printwriter = new PrintWriter(file)) {
            values.stream()
                    .map(this::convertCSV)
                    .forEach(printwriter::println);
            //I had no clue how to do this, so I used the idea from this link
            //https://www.baeldung.com/java-csv
            //changed some stuff but the crux of the datasaving came from this concept
        }
    }

    // Method to load player and team data from a file
    public ArrayList<Team> loadDataFromFile(File file) throws FileNotFoundException {
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
                    if (teams.getName().equals(values[0])) {
                        Double pointvalues = Double.valueOf(values[4]);
                        Double assistsvalues = Double.valueOf(values[5]);
                        Double stealsvalues = Double.valueOf(values[6]);
                        Double blocksvalues = Double.valueOf(values[7]);
                        Double reboundsvalues = Double.valueOf(values[8]);
                        int gamesplayed = Integer.parseInt(values[9]);
                        ArrayList<Double> points = new ArrayList<>();
                        ArrayList<Double> assists = new ArrayList<>();
                        ArrayList<Double> steals = new ArrayList<>();
                        ArrayList<Double> blocks = new ArrayList<>();
                        ArrayList<Double> rebounds = new ArrayList<>();

                        points.add(pointvalues);
                        assists.add(assistsvalues);
                        steals.add(stealsvalues);
                        blocks.add(blocksvalues);
                        rebounds.add(reboundsvalues);

                        // Create Stats objects for player statistics
                        Stats pointsforplayer = new Stats(points, StatsType.POINTS);
                        Stats assistsforplayer = new Stats(assists, StatsType.ASSISTS);
                        Stats stealsforplayer = new Stats(steals, StatsType.STEALS);
                        Stats blocksforplayer = new Stats(blocks, StatsType.BLOCKS);
                        Stats reboundsforplayer = new Stats(rebounds, StatsType.REBOUNDS);
                        Positions position = Positions.valueOf(values[3]);

                        // Create Player object and add it to the team
                        Player player = new Player(Integer.parseInt(values[2]), values[1], position, pointsforplayer, assistsforplayer, blocksforplayer, stealsforplayer, reboundsforplayer, gamesplayed, gamesplayed);
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
