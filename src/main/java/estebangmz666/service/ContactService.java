package estebangmz666.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import estebangmz666.dao.ContactDAO;
import estebangmz666.dto.ContactDTO;
import estebangmz666.exception.ServiceException;
import estebangmz666.model.Contact;
import estebangmz666.util.Validator;

public class ContactService {

    public static boolean registerContact(ContactDTO contactDTO) {
        if (!Validator.validateInputs(contactDTO.getName(), contactDTO.getCellphone())) {
            throw new IllegalArgumentException("Los campos obligatorios son inválidos.");
        }
        if (contactDTO.getEmail() != null && !Validator.validateEmail(contactDTO.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }
    
        Contact contact = new Contact(
            UUID.randomUUID(),
            contactDTO.getName(),
            contactDTO.getLastName(),
            contactDTO.getCellphone(),
            contactDTO.getCompany(),
            null,
            contactDTO.getEmail(),
            contactDTO.getPronoun()
        );
        try {
            return ContactDAO.insertContact(contact);
        } catch (Exception e) {
            throw new ServiceException("Error al registrar el contacto.", e);
        }
    }

    public static List<Contact> getAllContacts() {
        return ContactDAO.getAllContacts();
    }

    public static boolean updateContact(Contact contact) {
        if (!Validator.validateInputs(contact.getName(), contact.getCellphone())) {
            throw new IllegalArgumentException("Los campos obligatorios son inválidos.");
        }
        if (contact.getEmail() != null && !Validator.validateEmail(contact.getEmail())) {
            throw new IllegalArgumentException("El correo electrónico no es válido.");
        }
        boolean contactUpdated = ContactDAO.updateContact(contact);
        boolean notesUpdated = ContactDAO.saveNotes(contact.getId(), contact.getNotes());
        return contactUpdated && notesUpdated;
    }

    public static List<Contact> getAllContactsWithNotes() {
        List<Contact> contacts = ContactDAO.getAllContacts();
        for (Contact contact : contacts) {
            List<String> notes = ContactDAO.getNotesByContactId(contact.getId());
            contact.setNotes(new ArrayList<>(notes));
        }
        return contacts;
    }

    public static ContactDTO toDTO(Contact contact) {
        return new ContactDTO(
            contact.getName(),
            contact.getLastName(),
            contact.getCellphone(),
            contact.getCompany(),
            contact.getEmail(),
            contact.getPronoun()
        );
    }    
}