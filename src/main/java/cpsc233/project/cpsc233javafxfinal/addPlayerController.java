package cpsc233.project.cpsc233javafxfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addPlayerController implements Initializable {

    private MainInterfaceController mainInterfaceController;
    @FXML
    private TextField playerName;
    Team team;

    @FXML
    private TextField playerNumber;
    @FXML
    private Text IntroText;
    @FXML
    private Text erroraddPlayertext;

    @FXML
    void addtoTeam(MouseEvent event) {
        try {
            System.out.println(team.toString());
            ArrayList<Double> stats = new ArrayList<>();
            int gamecount = 0;
            int prior = 0;
            Stats points = new Stats(stats, StatsType.POINTS);
            Stats assists = new Stats(stats, StatsType.ASSISTS);
            Stats steals = new Stats(stats, StatsType.STEALS);
            Stats blocks = new Stats(stats, StatsType.BLOCKS);
            Stats rebounds = new Stats(stats, StatsType.REBOUNDS);
            Player player = new Player(Integer.parseInt(playerNumber.getText()), playerName.getText(), positions.getValue(), points, assists, blocks, rebounds, steals, gamecount, prior);
            team.addPlayer(player);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
            Parent root = fxmlLoader.load();
            MainInterfaceController controller = fxmlLoader.getController();
            controller.setTeam(team);
            Stage stage = (Stage) erroraddPlayertext.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            erroraddPlayertext.setText("Invalid input format.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private ComboBox<Positions> positions;

    @FXML
    void mainMenu(MouseEvent event) {


    }

    @FXML
    void switchToMainInterface(ActionEvent event) {

    }

    public void initData(Team team) {
        this.team = team;
        IntroText.setText("Adding a player to team " + team.getName());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        positions.getItems().addAll(Positions.ShootingGuard, Positions.Centre, Positions.PointGuard, Positions.PowerForward, Positions.SmallForward);
    }

}
