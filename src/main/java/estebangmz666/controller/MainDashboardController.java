package estebangmz666.controller;

import estebangmz666.util.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class MainDashboardController {

    @FXML
    private Hyperlink hlExit;

    @FXML
    private Button btnRegisterContacts;

    @FXML
    private Button btnSeeContacts;

    private ViewLoader viewLoader = new ViewLoader();

    @FXML
    void hlExitClicked(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnRegisterContactsClicked(ActionEvent event) {
        try {
            viewLoader.loadView(event, "/view/ContactRegister.fxml");
        } catch (Exception e) {
            System.err.println("Error al cargar la vista de registro de contactos: " + e.getMessage());
        }
    }

    @FXML
    void btnSeeContactsClicked(ActionEvent event) {
        try {
            viewLoader.loadView(event, "/view/ContactsDashboard.fxml");
        } catch (Exception e) {
            System.err.println("Error al cargar la vista del dashboard de contactos: " + e.getMessage());
        }
    }
}