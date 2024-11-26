package estebangmz666.controller;

import estebangmz666.model.Contact;
import estebangmz666.service.ContactService;
import estebangmz666.util.ViewLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;

public class ContactsDashboardController {

    @FXML
    private Button btnBack;

    @FXML
    private TableView<Contact> tableContacts;

    @FXML
    private TableColumn<Contact, String> colId;

    @FXML
    private TableColumn<Contact, String> colName;

    @FXML
    private TableColumn<Contact, String> colCellphone;

    @FXML
    private TableColumn<Contact, String> colCompany;

    @FXML
    private TableColumn<Contact, String> colEmail;

    @FXML
    private TableColumn<Contact, String> colPronoun;

    @FXML
    private TableColumn<Contact, String> colNotes;

    private final ViewLoader viewLoader = new ViewLoader();

    @FXML
    public void initialize() {
        if (tableContacts == null) {
            return;
        }
        configureTableColumns();
        loadContacts();
        addDoubleClickEvent();
    }

    @FXML
    void btnBackClicked(ActionEvent event) {
        viewLoader.loadView(event, "/view/MainDashboard.fxml");
    }

    private void configureTableColumns() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCellphone.setCellValueFactory(new PropertyValueFactory<>("cellphone"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPronoun.setCellValueFactory(new PropertyValueFactory<>("pronoun"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
    }

    private void loadContacts() {
        try {
            List<Contact> contacts = ContactService.getAllContacts();
            ObservableList<Contact> observableContacts = FXCollections.observableArrayList(contacts);
            tableContacts.setItems(observableContacts);
        } catch (Exception e) {
            System.err.println("Error al cargar los contactos: " + e.getMessage());
        }
    }
    

    private void addDoubleClickEvent() {
        tableContacts.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                Contact selectedContact = tableContacts.getSelectionModel().getSelectedItem();
                if (selectedContact != null) {
                    openContactManagement(selectedContact);
                }
            }
        });
    }

    @FXML
    private void openContactManagement(Contact contact) {
        try {
            ContactManagementController controller = new ContactManagementController(contact);
            Stage parentStage = (Stage) btnBack.getScene().getWindow();
            viewLoader.loadViewWithController("/view/ContactManagement.fxml", controller, parentStage);
        } catch (Exception e) {
            System.err.println("Error al abrir la ventana de gestión de contactos: " + e.getMessage());
        }
    }    
}