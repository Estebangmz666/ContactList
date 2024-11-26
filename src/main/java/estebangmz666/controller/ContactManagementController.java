package estebangmz666.controller;

import estebangmz666.model.Contact;
import estebangmz666.service.ContactService;
import estebangmz666.util.Validator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ContactManagementController {

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfCellphone;

    @FXML
    private TextField tfCompany;

    @FXML
    private TextField tfEmail;

    @FXML
    private VBox vbNotes;

    @FXML
    private Label lbMessage;

    private Contact contact;
    private final List<TextField> noteFields = new ArrayList<>();

    public ContactManagementController(Contact contact) {
        this.contact = contact;
    }

    @FXML
    public void initialize() {
        if (contact != null) {
            tfName.setText(contact.getName());
            tfLastName.setText(contact.getLastName());
            tfCellphone.setText(contact.getCellphone());
            tfCompany.setText(contact.getCompany());
            tfEmail.setText(contact.getEmail());

            if (contact.getNotes() != null) {
                for (String note : contact.getNotes()) {
                    addNoteField(note);
                }
            }
        }
    }

    @FXML
    void addNoteField(ActionEvent event) {
        addNoteField("");
    }

    void addNoteField(String note) {
        HBox noteBox = new HBox(5);
        TextField newNoteField = new TextField(note);
        newNoteField.setPromptText("Escribe una nota...");
        Button btnDelete = new Button("X");
        btnDelete.setOnAction(e -> {
            vbNotes.getChildren().remove(noteBox);
            noteFields.remove(newNoteField);
        });
        noteBox.getChildren().addAll(newNoteField, btnDelete);
        vbNotes.getChildren().add(noteBox);
        noteFields.add(newNoteField);
    }

    @FXML
    void saveContact(ActionEvent event) {
        try {
            if (tfName.getText().isEmpty() || tfCellphone.getText().isEmpty()) {
                lbMessage.setText("Por favor, completa los campos obligatorios (*).");
                return;
            }
            if (tfEmail.getText() != null && !tfEmail.getText().isEmpty() && !Validator.validateEmail(tfEmail.getText())) {
                lbMessage.setText("El correo electrónico no es válido.");
                return;
            }

            contact.setName(tfName.getText());
            contact.setLastName(tfLastName.getText());
            contact.setCellphone(tfCellphone.getText());
            contact.setCompany(tfCompany.getText());
            contact.setEmail(tfEmail.getText());

            ArrayList<String> notes = new ArrayList<>();
            for (TextField noteField : noteFields) {
                if (!noteField.getText().isEmpty()) {
                    notes.add(noteField.getText());
                }
            }
            contact.setNotes(notes);

            if (ContactService.updateContact(contact)) {
                lbMessage.setText("Contacto actualizado correctamente.");
                tfName.getScene().getWindow().hide();
            } else {
                lbMessage.setText("Error al actualizar el contacto.");
            }
        } catch (Exception e) {
            lbMessage.setText("Error inesperado al guardar el contacto.");
            e.printStackTrace();
        }
    }

    @FXML
    void cancel(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Hay cambios sin guardar. ¿Deseas salir?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                tfName.getScene().getWindow().hide();
            }
        });
    }
}