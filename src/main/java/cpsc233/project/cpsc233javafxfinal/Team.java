package cpsc233.project.cpsc233javafxfinal;

import java.io.*;
import java.util.*;

public class Team {
    private String name;
    private ArrayList<Player> players;
    public Team(ArrayList<Player> players, String name){
        this.players = new ArrayList<>();
        this.name = name;
    }
    public void addPlayer(Player player){
        players.add(player);
    }

    @Override
    public String toString(){
        String value = name;
        return name;
    }

    public ArrayList<Player> getPlayers() {
         return players;
        }

    public String getName() {
        return name;
    }
}
