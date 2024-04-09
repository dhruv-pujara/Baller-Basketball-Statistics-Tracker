package cpsc233.project.cpsc233javafxfinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addPlayerController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        positions.getItems().addAll(Positions.ShootingGuard, Positions.Centre, Positions.PointGuard, Positions.PowerForward, Positions.SmallForward);
    }
    public void initData(Team team) {
        this.team = team;
        IntroText.setText("Adding a player to Team " + team.getName() + ":");
        erroraddPlayertext.setTextFill(Color.BLACK);
    }
    @FXML
    void switchToMainInterface(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    //Variables and constants
    Team team;

    //FXML ID inserts
    @FXML
    private TextField playerName;
    @FXML
    private TextField playerNumber;
    @FXML
    private Text IntroText;
    @FXML
    private Label erroraddPlayertext;
    @FXML
    private ComboBox<Positions> positions;

    @FXML
    void addtoTeam(MouseEvent event) {
        try {
            if (positions.getValue() == null) {
                erroraddPlayertext.setText("Position not selected.");
                erroraddPlayertext.setTextFill(Color.RED);
            } else if(playerName.getText().trim().isEmpty()){
                erroraddPlayertext.setText("PLayer name cannot be empty.");
                erroraddPlayertext.setTextFill(Color.RED);
            } else {
                ArrayList<Double> pointsstats = new ArrayList<>();
                ArrayList<Double> assistsstats = new ArrayList<>();
                ArrayList<Double> stealssstats = new ArrayList<>();
                ArrayList<Double> blocksstats = new ArrayList<>();
                ArrayList<Double> reboundsstats = new ArrayList<>();
                int gamecount = 0;
                int prior = 0;
                Stats points = new Stats(pointsstats, StatsType.POINTS);
                Stats assists = new Stats(assistsstats, StatsType.ASSISTS);
                Stats steals = new Stats(stealssstats, StatsType.STEALS);
                Stats blocks = new Stats(blocksstats, StatsType.BLOCKS);
                Stats rebounds = new Stats(reboundsstats, StatsType.REBOUNDS);
                Player player = new Player(Integer.parseInt(playerNumber.getText()), playerName.getText(), positions.getValue(), points, assists, blocks, rebounds, steals, gamecount, prior);
                team.addPlayer(player);
                erroraddPlayertext.setText("Player " + playerName.getText() +" has been added!");
                erroraddPlayertext.setTextFill(Color.BLACK);
                playerName.setText(null);
                playerNumber.setText(null);
                positions.setValue(null);
            }
        } catch (NumberFormatException e) {
            erroraddPlayertext.setText("Invalid Input - Please input number");
            erroraddPlayertext.setTextFill(Color.RED);

        } catch (Exception e) {
             unaccountedError(e);
        }
    }


    public void unaccountedError (Exception e){
        //Here just in case something else happens
        //Should never be triggered, but you never know!
        Alert leave = new Alert(Alert.AlertType.ERROR);
        leave.setTitle("An Error Has Occurred :" + e);
        leave.setHeaderText("""
                An unexpected error has occurred. The program will now close.
                """);
        leave.showAndWait();
        leave.close();
        System.exit(1);
    }
}
