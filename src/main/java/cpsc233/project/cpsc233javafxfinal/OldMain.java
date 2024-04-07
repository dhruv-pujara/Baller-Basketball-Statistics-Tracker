package cpsc233.project.cpsc233javafxfinal;

import java.util.*;
import java.io.*;
public class OldMain {
    public static void main(String[] args) {
        ArrayList<Team> teams = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello. Welcome to Baller:The Basketball Statistic Program! Here, you can add Basketball teams, players, and view" +
                " statistics and averages.");
        int i = 0;
        while (i != 1) {
            //Creates loop for user to enter, view, and edit teams/players. Does not exit unless user prompts
            System.out.println("""
                                        
                    Please enter the corresponding number of what you would like to do:\s
                    [1] Add Team(s)
                    [2] Add Player(s) to Teams
                    [3] Add a Game
                    [4] View All Teams
                    [5] View Stats
                    [6] Load Saved File
                    [0] Save into File and Exit""");
            try {
                int direct = scanner.nextInt();
                //ADD TEAMS
                if (direct == 1) {
                    int q = 1;
                    int numTeams = -1;
                    //Ensures teams is not less than 0
                    //Cannot make 0 teams
                    while (q == 1) {
                        System.out.print("How many teams do you want to add?: ");
                        numTeams = scanner.nextInt();
                        if (numTeams <= 0) {
                            System.out.println("Invalid amount of teams. Please try again.");
                            System.out.println(" ");
                        } else {
                            q = 0;
                        }
                    }
                    scanner.nextLine();
                    //Prompts user to enter team name
                    for (int k = 0; k < numTeams; k++) {
                        System.out.print("Enter the name of team " + (k + 1) + ": ");
                        String name = scanner.nextLine();
                        int value = 0;
                        //invalid if team already exists
                        for (Team teamnames : teams) {
                            if (teamnames.getName().equals(name)) {
                                System.out.println("Invalid team, team with this name already exists.");
                                System.out.println(" ");
                                value = 1;
                                break;
                            }
                        }
                        if (value == 1) {
                            continue;
                        }
                        ArrayList<Player> players = new ArrayList<Player>();
                        Team team = new Team(players, name);
                        teams.add(team);
                    }
                    //back to MM message
                    System.out.println("Back to Main Menu.");



                    //ADD PLAYERS TO EXISTING TEAM
                } else if (direct == 2) {
                    //If arraylist teams is empty, there is nothing to add players too.
                    if (teams.isEmpty()) {
                        System.out.println("There are currently no teams made.");
                    } else {
                        int x = 1;
                        while (x == 1) {
                            int m = 0;
                            int index = 1;
                            while (m == 0) {
                                //Prints out the teams in form '[i] team name'
                                //User then inputs number to select a team
                                System.out.println("What team would you like to add a player to:");
                                int teamNum = 0;
                                for (Team teamlist : teams) {
                                    teamNum = teams.indexOf(teamlist);
                                    teamNum = teamNum + 1;
                                    System.out.println("[" + teamNum + "] " + teamlist);
                                }
                                index = scanner.nextInt();
                                scanner.nextLine();
                                //Prevents error if user enters invalid number
                                if (index <= 0 || index > teamNum) {
                                    System.out.println("Invalid Response - Please try again.");
                                    System.out.println(" ");
                                } else {
                                    m = 1;
                                }
                            }
                            Team team = teams.get(index - 1);
                            m = 0;
                            int numPlayers = 0;
                            while (m == 0) {
                                System.out.print("How many players do you want to add to team " + team + "? ");
                                numPlayers = scanner.nextInt();
                                scanner.nextLine();
                                //Cannot add 0 players
                                if (numPlayers <= 0) {
                                    System.out.println("Invalid amount of players. Please try again");
                                    System.out.println(" ");
                                } else {
                                    m = 1;
                                }
                                for (int j = 0; j < numPlayers; j++) {
                                    System.out.print("Enter the name of player " + (j + 1) + " for team " + team + ": ");
                                    String name = scanner.next();
                                    //ADD CODE FOR IF PLAYER ALREADY EXISTS HERE - DEV
                                    int q = 1;
                                    int jersey = 0;
                                    while (q == 1) {
                                        System.out.print("Enter the jersey number of player " + (j + 1) + " for team " + team + ": ");
                                        jersey = scanner.nextInt();
                                        //If user puts invalid jersey number, is reprompted
                                        if (jersey <= 0 || jersey > 99) {
                                            System.out.println("Invalid jersey number - please try again.");
                                            System.out.println(" ");
                                        } else {
                                            q = 0;
                                        }
                                    }
                                    Positions position = null;
                                    int value = 0;
                                    while (value == 0) {
                                        //User inputs number for what position player is in. If invalid, it reprompts.
                                        System.out.println("Enter the position of player " + (j + 1) + " for team " + team + ": ");
                                        System.out.println("""
                                                [1] Centre \s
                                                [2] Small Forward
                                                [3] Shooting Guard
                                                [4] Power Forward
                                                [5] Point guard""");
                                        int positionindex = scanner.nextInt();
                                        if (positionindex == 1) {
                                            position = Positions.Centre;
                                            value = 1;
                                        } else if (positionindex == 2) {
                                            position = Positions.SmallForward;
                                            value = 1;
                                        } else if (positionindex == 3) {
                                            position = Positions.ShootingGuard;
                                            value = 1;
                                        } else if (positionindex == 4) {
                                            position = Positions.PowerForward;
                                            value = 1;
                                        } else if (positionindex == 5) {
                                            position = Positions.PointGuard;
                                            value = 1;
                                        } else {
                                            System.out.println("An invalid position was selected, please try again.");
                                            System.out.println(" ");
                                        }
                                    }
                                    //created Object player (aka, the name of the player)
                                    ArrayList<Double> forpoints = new ArrayList<>();
                                    ArrayList<Double> forassists = new ArrayList<>();
                                    ArrayList<Double> forblocks = new ArrayList<>();
                                    ArrayList<Double> forsteals = new ArrayList<>();
                                    ArrayList<Double> forrebounds = new ArrayList<>();

                                    Stats points = new Stats(forpoints, StatsType.POINTS);
                                    Stats assists = new Stats(forassists, StatsType.ASSISTS);
                                    Stats blocks = new Stats(forblocks, StatsType.BLOCKS);
                                    Stats steals = new Stats(forsteals, StatsType.STEALS);
                                    Stats rebounds = new Stats(forrebounds, StatsType.REBOUNDS);
                                    Player player = new Player(jersey, name, position, points, assists, blocks, rebounds, steals, 0, 0);
                                    team.addPlayer(player);
                                }
                            }
                            x = 0;
                        }
                    }
                    //Back to MM message
                    System.out.println("Back to Main Menu.");



                    //ADD GAME
                } else if (direct == 3) {
                    //If no teams, there is no players to add a team to
                    if (teams.isEmpty()) {
                        System.out.println("There are currently no teams made.");
                    } else {
                        int k = 0;
                        int index = 1;
                        while (k == 0) {
                            //Prints out the teams in form '[i] team name'
                            //User then inputs number to select a team
                            System.out.println("What team would you like to add a game for?");
                            int teamNum = 0;
                            for (Team teamlist : teams) {
                                teamNum = teams.indexOf(teamlist);
                                teamNum = teamNum + 1;
                                System.out.println("[" + teamNum + "] " + teamlist);
                            }
                            index = scanner.nextInt();
                            scanner.nextLine();
                            //Reprompts question above if team enters invalid response
                            if (index <= 0 || index > teamNum) {
                                System.out.println("Invalid response - please try again.");
                                System.out.println(" ");
                            } else {
                                k = 1;
                            }
                        }
                        Team team = teams.get(index - 1);
                        ArrayList<Player> players = team.getPlayers();
                        if (players.isEmpty()) {
                            //If there are no players, there is nothing to add a game too
                            System.out.println("There are currently no players on this team.");
                        } else {
                            //All doubles so averages can be calculated accurately. User will input an Int, which will
                            //turn into a Double
                            Double pointstoadd = -1.0;
                            Double assiststoadd = -1.0;
                            Double stealstoadd = -1.0;
                            Double blockstoadd = -1.0;
                            Double reboundstoadd = -1.0;
                            //Iterates through players and user adds stats for them
                            for (Player player : players) {
                                player.addGamecount(player.getGamecount());
                                System.out.println("Enter stats for player " + player.getName() + ":");
                                //While loop to ensure points are more than 0. If invalid response, reprompts.
                                int l = 1;
                                while (l == 1) {
                                    System.out.print("Enter Points: ");
                                    pointstoadd = scanner.nextDouble();
                                    if (pointstoadd >= 0) {
                                        Stats points = player.getPoints();
                                        points.setStats(points.getStats(), pointstoadd);
                                        l = 0;
                                    } else {
                                        System.out.println("Invalid amount of points- please try again.");
                                        System.out.println(" ");
                                    }
                                }

                                l = 1;
                                //While loop to ensure assists are more than 0. If invalid response, reprompts.
                                while (l == 1) {
                                    System.out.print("Enter Assists: ");
                                    assiststoadd = scanner.nextDouble();
                                    if (assiststoadd >= 0) {

                                        Stats assists = player.getAssists();
                                        assists.setStats(assists.getStats(), assiststoadd);
                                        l = 0;
                                    } else {
                                        System.out.println("Invalid amount of assists- please try again.");
                                        System.out.println(" ");
                                    }
                                }
                                l = 1;
                                //While loop to ensure steals are more than 0. If invalid response, reprompts.
                                while (l == 1) {
                                    System.out.print("Enter Steals: ");
                                    stealstoadd = scanner.nextDouble();
                                    if (stealstoadd >= 0) {
                                        Stats steals = player.getSteals();
                                        steals.setStats(steals.getStats(), stealstoadd);
                                        l = 0;
                                    } else {
                                        System.out.println("Invalid amount of steals- please try again.");
                                        System.out.println(" ");
                                    }
                                }
                                l = 1;
                                //While loop to ensure blocks are more than 0. If invalid response, reprompts.
                                while (l == 1) {
                                    System.out.print("Enter Blocks: ");
                                    blockstoadd = scanner.nextDouble();
                                    if (blockstoadd >= 0) {
                                        Stats blocks = player.getBlocks();
                                        blocks.setStats(blocks.getStats(), blockstoadd);
                                        l = 0;
                                    } else {
                                        System.out.println("Invalid amount of blocks- please try again.");
                                        System.out.println(" ");
                                    }
                                }
                                l = 1;
                                //While loop to ensure rebounds are more than 0. If invalid response, reprompts.
                                while (l == 1) {
                                    System.out.print("Enter Rebounds: ");
                                    reboundstoadd = scanner.nextDouble();
                                    if (reboundstoadd >= 0) {
                                        Stats rebounds = player.getRebounds();
                                        rebounds.setStats(rebounds.getStats(), reboundstoadd);
                                        l = 0;
                                    } else {
                                        System.out.println("Invalid amount of rebounds- please try again.");
                                        System.out.println(" ");
                                    }
                                }
                                System.out.println("Evaluation of players last game:");
                                Evaluations.fullPrintedEvaluationAndLastGameStats(player.getPoints(), player.getAssists(), player.getSteals(), player.getBlocks(), player.getRebounds(), player.getGamecount(), player.getPriotgamecount());
                                System.out.println(" ");
                                //Clear scanner
                                scanner.nextLine();
                            }
                        }
                    }
                    //Back to MM message
                    System.out.println("Back to Main Menu.");



                    //VIEW ALL TEAMS
                    //VIEW PLAYERS ON TEAMS
                } else if (direct == 4) {
                    //If there are no teams, there is nothing to view
                    if (teams.isEmpty()) {
                        System.out.println("There are currently no teams made.");
                    } else {
                        int w = 0;
                        int index = 1;
                        while (w == 0) {
                            //Prints out the teams in form '[i] team name'
                            //User then inputs number to select a team
                            System.out.println("What team would you like to see the players for?");
                            int teamNum = 0;
                            for (Team teamlist : teams) {
                                teamNum = teams.indexOf(teamlist);
                                teamNum = teamNum + 1;
                                System.out.println("[" + teamNum + "] " + teamlist);
                            }
                            index = scanner.nextInt();
                            scanner.nextLine();
                            //Reprompts question above if team enters invalid response
                            if (index <= 0 || index > teamNum) {
                                System.out.println("Invalid input- please try again.");
                                System.out.println(" ");
                            } else {
                                w = 1;
                            }
                        }
                        Team team = teams.get(index - 1);
                        ArrayList<Player> players = team.getPlayers();
                        //If there is no players on a team, this tells the user
                        if (players.isEmpty()) {
                            System.out.println("There are currently no players on this team.");
                        } else {
                            //Prints all players one by one for user to see
                            System.out.println("The players on team " + team.getName() + " are as follows:");
                            for (Player playersonteam : players) {
                                System.out.println(playersonteam.getName());
                            }
                        }
                    }
                    //back to MM message
                    System.out.println("Back to Main Menu.");



                    //STATS MENU
                } else if (direct == 5) {
                    int p = 0;
                    while (p != 1) {
                        try {
                            System.out.println("""
                                                                        
                                    What statistics would you like to see?: \s
                                    [1] Top player in a specified stat
                                    [2] A players portfolio
                                    [3] What does the All-Star Roster look like?
                                    [4] What was a player's last game?
                                    [0] Back to Main Menu""");
                            int stats = scanner.nextInt();
                            //TOP PLAYER
                            if (stats == 1) {
                                        //TEST IF PLAYERS ARE ADDED OR NOT DEV
                                //NEED TEAM TEST IF TEAMS EXIST, NOT PLAYERS SHOULD BE COVERED IN THE FUNCTION
                                //NEED IF INVALID INPUT
                                        System.out.print("""
                                                                                    
                                                What Stat would you like?: \s
                                                [1] Points
                                                [2] Assists
                                                [3] Steals
                                                [4] Blocks
                                                [5] Rebounds
                                                """);
                                        int stattype = scanner.nextInt();
                                        if(teams.isEmpty()) {
                                            System.out.println("There are no teams currently added");
                                        }{
                                }
                                        if (stattype == 1) {
                                            Evaluations.TopPlayerStat(teams, StatsType.POINTS);
                                        } else if (stattype == 2) {
                                            Evaluations.TopPlayerStat(teams, StatsType.ASSISTS);
                                        } else if (stattype == 3) {
                                            Evaluations.TopPlayerStat(teams, StatsType.STEALS);
                                        } else if (stattype == 4) {
                                            Evaluations.TopPlayerStat(teams, StatsType.BLOCKS);
                                        } else if (stattype == 5) {
                                            Evaluations.TopPlayerStat(teams, StatsType.REBOUNDS);
                                        }
                                //STAT TYPES....
                                System.out.println("Returning to Statistics Menu.");
                                //FINDS A PLAYERS AVERAGE
                            } else if (stats == 2) {
                                if (teams.isEmpty()){
                                    System.out.println("There are no teams currently created.");
                                }else{
                                    int e = 0;
                                    int index = 1;
                                    while (e == 0) {
                                        System.out.println("What team is the player you want on:");
                                        int teamNum = 0;
                                        for (Team teamlist : teams) {
                                            teamNum = teams.indexOf(teamlist);
                                            teamNum = teamNum + 1;
                                            System.out.println("[" + teamNum + "] " + teamlist);
                                        }
                                        index = scanner.nextInt();
                                        //Reprompts question above if team enters invalid response
                                        if (index <= 0 || index > teamNum) {
                                            System.out.println("Invalid input- please try again.");
                                            System.out.println(" ");
                                        } else {
                                            e = 1;
                                        }
                                    }
                                    Team team = teams.get(index-1);
                                    ArrayList<Player> teamPlayers = team.getPlayers();
                                    //If there is no players on a team, this tells the user
                                    if (teamPlayers.isEmpty()) {
                                        System.out.println("There are currently no players on this team.");
                                    } else {
                                        index = 1;
                                        int playerNum = 1;
                                        //Prints all players in form [i] = playerName
                                        while (e == 1) {
                                            System.out.println("What players profile would you like to view?");
                                            for (Player player : teamPlayers) {
                                                playerNum = teamPlayers.indexOf(player);
                                                playerNum = playerNum + 1;
                                                String name = player.getName();
                                                System.out.println("[" + playerNum + "] " + name);
                                            }
                                            index = scanner.nextInt();
                                            scanner.nextLine();
                                            //Reprompts question above if team enters invalid response
                                            if (index <= 0 || index > playerNum) {
                                                System.out.println("Invalid input- please try again.");
                                                System.out.println(" ");
                                            } else {
                                                e = 0;
                                                //If the player has played no games, user is told
                                                Player returnPlayer = teamPlayers.get(index - 1);
                                                if (returnPlayer.getGamecount() == 0){
                                                    System.out.println(returnPlayer.getName() + " has played no games.");
                                                    System.out.println(" ");
                                                }else {
                                                    System.out.println(returnPlayer.toString());
                                                    System.out.println(" ");
                                                }
                                            }
                                        }
                                    }
                                }
                                System.out.println("Returning to Statistics Menu.");
                                //ALL STAR ROSTER
                            } else if (stats == 3) {
                                if (teams.isEmpty()){
                                    System.out.println("There are no teams currently created.");
                                }else {
                                        System.out.println("The current all star team for Offense and Defense " +
                                                "by position are:");
                                        System.out.println("Centre: ");
                                        Evaluations.topOffTopDefForPosition(teams, Positions.Centre);
                                        System.out.println("Small Forward: ");
                                        Evaluations.topOffTopDefForPosition(teams, Positions.SmallForward);
                                        System.out.println("Power Forward: ");
                                        Evaluations.topOffTopDefForPosition(teams, Positions.PowerForward);
                                        System.out.println("Shooting Guard: ");
                                        Evaluations.topOffTopDefForPosition(teams, Positions.ShootingGuard);
                                        System.out.println("Point Guard: ");
                                        Evaluations.topOffTopDefForPosition(teams, Positions.PointGuard);
                                    }
                                    /* PRINT FOR EACH TOP PLAYER IN POSITION
                                    TRY/CATCH FOR IF PLAYER DNE
                                }*/
                                System.out.println("Returning to Statistics Menu.");



                                //FIND PLAYERS LAST GAME
                            } else if (stats == 4) {
                                //If there are no teams, there are no players
                                if (teams.isEmpty()){
                                System.out.println("There are no teams currently created.");
                                    System.out.println(" ");
                                }else{
                                    int e = 0;
                                    int index = 1;
                                    while (e == 0) {
                                        //Prints menu in style of [i] teamName
                                        System.out.println("What team is the player you want on:");
                                        int teamNum = 0;
                                        for (Team teamlist : teams) {
                                            teamNum = teams.indexOf(teamlist);
                                            teamNum = teamNum + 1;
                                            System.out.println("[" + teamNum + "] " + teamlist);
                                        }
                                        index = scanner.nextInt();
                                        //Reprompts question above if team enters invalid response
                                        if (index <= 0 || index > teamNum) {
                                            System.out.println("Invalid input- please try again.");
                                            System.out.println(" ");
                                        } else {
                                            e = 1;
                                        }
                                    }
                                    Team team = teams.get(index-1);
                                    ArrayList<Player> teamPlayers = team.getPlayers();
                                    //If there is no players on a team, this tells the user
                                    if (teamPlayers.isEmpty()) {
                                        System.out.println("There are currently no players on this team.");
                                        System.out.println(" ");
                                        scanner.nextLine();
                                    } else {
                                        index = 1;
                                        int playerNum = 0;
                                        //Prints all players in form [i] playerName
                                        while (e == 1) {
                                            System.out.println("What player would you like to see the last game for?");
                                            for (Player player : teamPlayers) {
                                                playerNum = teamPlayers.indexOf(player);
                                                playerNum = playerNum + 1;
                                                String name = player.getName();
                                                System.out.println("[" + playerNum + "] " + name);
                                            }
                                            index = scanner.nextInt();
                                            scanner.nextLine();
                                            //Reprompts question above if team enters invalid response
                                            if (index <= 0 || index > playerNum) {
                                                System.out.println("Invalid input- please try again.");
                                                System.out.println(" ");
                                            } else {
                                                e = 0;
                                                //If the player has played no games, user is told
                                                Player returnPlayer = teamPlayers.get(index-1);
                                                if (returnPlayer.getGamecount() == 0){
                                                    System.out.println(returnPlayer.getName() + " has played no games.");
                                                    System.out.println(" ");
                                                }else{
                                                    //function from Evaluations.java is used to print last games
                                                    System.out.println("Here is " + returnPlayer.getName() + "'s last game:");
                                                    Evaluations.fullPrintedEvaluationAndLastGameStats(returnPlayer.getPoints(), returnPlayer.getAssists(), returnPlayer.getSteals(), returnPlayer.getBlocks(), returnPlayer.getRebounds(), returnPlayer.getGamecount(), returnPlayer.getPriotgamecount());
                                                    System.out.println(" ");
                                                }
                                            }
                                        }
                                    }
                                }
                                System.out.println("Returning to Statistics Menu.");


                                //RETURN TO MAIN MENU
                            } else if (stats == 0) {
                                //Returns to Main Menu - all direct == # have this so user knows where they are
                                System.out.println("Returning to Main Menu.");
                                p = 1;
                            } else {
                                //incase they enter invalid command
                                System.out.println("Invalid response - please try again.");
                            }
                        } catch (InputMismatchException e) {
                            //Catch incase input is not number
                            System.out.println("Input was not a number - please try again.");
                            System.out.println(" ");
                            scanner.next();
                        }
                    }



                //FILE LOADER
                } else if (direct == 6) {
                    //looks in directory for filename.csv
                    //If file does not exist, DO SOMETHING - DEV
                    try{
                        System.out.println("What is the name of the file you want to add?");
                        String filename = scanner.next();
                        File file = new File(filename + ".csv");
                        CustomFileReader read = new CustomFileReader(file);
                        teams = read.loadDataFromFile(file);
                        //back to MM message
                        System.out.println("Back to Main Menu.");
                    } catch (FileNotFoundException e) {
                        //This likely won't run, since CustomFileReader.java throws RuntimeException when file cannot be found
                        System.err.println(".cvs file not found- please create a file and try again.");
                        System.exit(1);
                    } catch (RuntimeException e){
                        //Prints when user tries to load a file that does not exist
                        //Not much else we can do, so the code exits.
                        System.err.println("Valid .cvs file not found- please create a file and try again.");
                        System.exit(2);
                    }



                    //SAVE FILE AND CONTROLLED EXIT
                } else if (direct == 0) {
                    int b = 0;
                    while (b == 0) {
                        //Try/Catch block incase user inputs invalid name
                        try {
                            System.out.println("What name would you like to give this file?" +
                                    " Please do not add any punctuation.");
                            String fileName = scanner.next();
                            File file = new File(fileName + ".csv");
                            CustomFileReader read = new CustomFileReader(file);
                            read.saveFile(file, teams);
                            //Lets user know file has been made
                            System.out.println(fileName + ".csv has been made!");
                            System.out.println("");
                            b = 1;
                            //Controlled program exit
                            //Since the whole program is in a while loop, when i = 1, the loop exits and the program ends
                            i = 1;
                        } catch (FileNotFoundException e) {
                            //Used to catch bad inputs - for example, if a user tries to name a file ">.csv", it will catch the error
                            //Will then reprompt user to make a different file name
                            System.out.println("Invalid file name - please try again.");
                            System.out.println(" ");
                        }
                    }
                } else {
                    //Prints when player enters invalid number for the main directory
                    System.out.println("Invalid response - please try again.");
                    System.out.println(" ");
                }
            } catch (InputMismatchException e) {
                //If at any point the user inputs a letter instead of a # this will run
                //direct == 5 has its own InputMismatchException catch, so any errors made in that
                //block will run there instead.
                System.out.println("Input was not a number- please try again.");
                System.out.println(" ");
                scanner.next();
            }
        }
        //goodbye message that always prints, unless exception is thrown.
        System.out.println("Thank you for using Baller! Have a good day.");
    }
}
