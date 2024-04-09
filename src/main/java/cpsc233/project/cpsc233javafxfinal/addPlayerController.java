package cpsc233.project.cpsc233javafxfinal;

public class addPlayerController {

    private MainInterfaceController mainInterfaceController;
    private Team team;
    private Player player;

    public void initData(Team team, Player player) {
        this.team = team;
        this.player = player;
    }

    public void setMainInterfaceController(MainInterfaceController mainInterfaceController) {
        this.mainInterfaceController = mainInterfaceController;
    }

}
