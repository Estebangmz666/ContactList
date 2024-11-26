package estebangmz666.controller;

import estebangmz666.dto.ContactDTO;
import estebangmz666.model.Pronoun;
import estebangmz666.service.ContactService;
import estebangmz666.util.Validator;
import estebangmz666.util.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.logging.Logger;

public class ContactRegisterController {

    private static final Logger logger = Logger.getLogger(ContactRegisterController.class.getName());

    @FXML
    private Button btnRegisterContact;

    @FXML
    private ComboBox<Pronoun> cbPronoun;

    @FXML
    private Hyperlink hlExit;

    @FXML
    private Label lbMessage;

    @FXML
    private TextField tfCellphone;

    @FXML
    private TextField tfCompany;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfName;

    private final ViewLoader viewLoader = new ViewLoader();

    @FXML
    public void initialize() {
        cbPronoun.getItems().addAll(Pronoun.values());
        cbPronoun.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Pronoun item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.toString());
            }
        });

        cbPronoun.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Pronoun item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.toString());
            }
        });
    }
    
    @FXML
    void btnRegisterContactClicked(ActionEvent event) {
        try {
            String nameText = tfName.getText();
            String lastNameText = tfLastName.getText() != null ? tfLastName.getText() : "";
            String cellphoneText = tfCellphone.getText();
            String companyText = tfCompany.getText() != null ? tfCompany.getText() : "";
            String emailText = tfEmail.getText() != null ? tfEmail.getText() : "";
            Pronoun pronoun = cbPronoun.getValue();

            if (!Validator.validateInputs(nameText, cellphoneText)) {
                lbMessage.setText("Por favor, completa los campos obligatorios (*).");
                return;
            }

            ContactDTO contactDTO = new ContactDTO(nameText, lastNameText, cellphoneText, companyText, emailText, pronoun);

            if (ContactService.registerContact(contactDTO)) {
                lbMessage.setText("Contacto registrado exitosamente!");
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(1500);
                        clearForm();
                        viewLoader.loadView(event, "/view/MainDashboard.fxml");
                    } catch (Exception e) {
                        logger.severe("Error al navegar al dashboard principal: " + e.getMessage());
                    }
                });
            } else {
                lbMessage.setText("Hubo un error al registrar el contacto.");
            }
        } catch (IllegalArgumentException e) {
            lbMessage.setText("Datos inválidos: " + e.getMessage());
            logger.warning("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            lbMessage.setText("Error inesperado. Por favor, inténtalo de nuevo.");
            logger.severe("Error al registrar el contacto: " + e.getMessage());
        }
    }

    @FXML
    void hlExitClicked(ActionEvent event) {
        viewLoader.loadView(event, "/view/MainDashboard.fxml");
    }

    private void clearForm() {
        tfCellphone.clear();
        tfCompany.clear();
        tfEmail.clear();
        tfLastName.clear();
        tfName.clear();
        cbPronoun.setValue(null);
    }
}