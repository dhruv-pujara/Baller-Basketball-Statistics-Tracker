package cpsc233.project.cpsc233javafxfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class addGameController {

    public void initData(Team team) {
        this.team = team;
//        this.player = player;
//        teamLabel.setText("Team: " + team.getName());
//        playerLabel.setText("Player: " + player.getName());
    }

    private MainInterfaceController mainInterfaceController;

    private Team team;
    private Player player;


    @FXML
    private Label addGameErrortext;
    @FXML
    private TextField points;
    @FXML
    private TextField assists;
    @FXML
    private TextField steals;
    @FXML
    private TextField blocks;
    @FXML
    private TextField rebounds;
    @FXML
    private Label teamLabel;
    @FXML
    private Label playerLabel;

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setMainInterfaceController(MainInterfaceController mainInterfaceController) {
        this.mainInterfaceController = mainInterfaceController;
//        this.player = player;
    }

    public void switchToMainInterface(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void saveGameStats(ActionEvent event) {

        if (team == null) {
            System.err.println("Team is not initialized");
            return;
        }
        for (Player player : team.getPlayers()) {
            try {
                double pointsStored = Double.parseDouble(points.getText());
                double assistsStored = Double.parseDouble(assists.getText());
                double stealsStored = Double.parseDouble(steals.getText());
                double blocksStored = Double.parseDouble(blocks.getText());
                double reboundsStored = Double.parseDouble(rebounds.getText());

                if (pointsStored < 0 || assistsStored < 0 || stealsStored < 0 || blocksStored < 0 || reboundsStored < 0) {
                    addGameErrortext.setText("All Stats must be positive");
                    return;
                }

                player.getPoints().setStats(player.getPoints().getStats(), pointsStored);
                player.getAssists().setStats(player.getAssists().getStats(), assistsStored);
                player.getSteals().setStats(player.getSteals().getStats(), stealsStored);
                player.getBlocks().setStats(player.getBlocks().getStats(), blocksStored);
                player.getRebounds().setStats(player.getRebounds().getStats(), reboundsStored);

                player.addGamecount(player.getGamecount());

                System.out.println("Updated stats for " + player.getName() + ":");
                System.out.println("Points: " + player.getPoints().getStats());
                System.out.println("Assists: " + player.getAssists().getStats());
                System.out.println("Steals: " + player.getSteals().getStats());
                System.out.println("Blocks: " + player.getBlocks().getStats());
                System.out.println("Rebounds: " + player.getRebounds().getStats());

                player.addGamecount(player.getGamecount());
            } catch (NumberFormatException e) {
                addGameErrortext.setText("Invalid input format.");
                return;
            }
        }

    }
}
