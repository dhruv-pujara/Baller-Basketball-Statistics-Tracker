package cpsc233.project.cpsc233javafxfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class addGameController {

    public void initData(Team team, Player player) {
        this.team = team;
        this.player = player;
        teamText.setText("Adding game for team " + team.getName());
        playerText.setText("Player: " + player.getName());
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
    private Text teamText;
    @FXML
    private Text playerText;


    public void setTeamAndPlayer(Team team, Player player) {

        this.team = team;
        this.player = player;
    }

    public void setMainInterfaceController(MainInterfaceController mainInterfaceController) {
        this.mainInterfaceController = mainInterfaceController;
    }

    public void switchToMainInterface(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void saveGameStats(ActionEvent event) {
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

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
                Parent root = fxmlLoader.load();
                MainInterfaceController controller = fxmlLoader.getController();
                controller.setTeam(team);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            } catch (NumberFormatException e) {
                addGameErrortext.setText("Invalid input format.");
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }


}
