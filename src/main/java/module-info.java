module cpsc233.project.cpsc233javafxfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.xml;


    opens cpsc233.project.cpsc233javafxfinal to javafx.fxml;
    exports cpsc233.project.cpsc233javafxfinal;
}