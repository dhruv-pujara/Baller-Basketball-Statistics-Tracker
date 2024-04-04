module cpsc233.project.cpsc233javafxfinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens cpsc233.project.cpsc233javafxfinal to javafx.fxml;
    exports cpsc233.project.cpsc233javafxfinal;
}