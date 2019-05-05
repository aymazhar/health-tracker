package ca.ubc.cs.cpsc210.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class Invalidinputswindow {

    @FXML
    Button ok;

    public void okButton() {
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }
}
