package estebangmz666.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import estebangmz666.exception.DatabaseException;
import estebangmz666.model.Contact;
import estebangmz666.model.Pronoun;
import estebangmz666.util.DatabaseConnection;

public class ContactDAO {

    public static boolean insertContact(Contact contact) {
        String sql = "INSERT INTO Contacts (id, name, lastname, cellphone, company, email, pronoun) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, contact.getId().toString());
            pstmt.setString(2, contact.getName());
            pstmt.setString(3, contact.getLastName());
            pstmt.setString(4, contact.getCellphone());
            pstmt.setString(5, contact.getCompany());
            pstmt.setString(6, contact.getEmail());
            pstmt.setString(7, contact.getPronoun() != null ? contact.getPronoun().name() : null);
    
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Error al insertar el contacto en la base de datos.", e);
        }
    }

    public static List<Contact> getAllContacts() {
        String sql = "SELECT * FROM Contacts";
        List<Contact> contacts = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
    
            while (rs.next()) {
                Contact contact = new Contact(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("name"),
                    rs.getString("lastname"),
                    rs.getString("cellphone"),
                    rs.getString("company"),
                    null,
                    rs.getString("email"),
                    rs.getString("pronoun") != null ? Pronoun.valueOf(rs.getString("pronoun")) : null
                );
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener contactos de la base de datos.", e);
        }

        return contacts;
    }
    

    public static boolean updateContact(Contact contact) {
        String sql = "UPDATE Contacts SET name = ?, lastname = ?, cellphone = ?, company = ?, email = ?, pronoun = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, contact.getName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getCellphone());
            pstmt.setString(4, contact.getCompany());
            pstmt.setString(5, contact.getEmail());
            pstmt.setString(6, contact.getPronoun() != null ? contact.getPronoun().name() : null);
            pstmt.setString(7, contact.getId().toString());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveNotes(UUID contactId, List<String> notes) {
        String deleteSql = "DELETE FROM Notes WHERE contact_id = ?";
        String insertSql = "INSERT INTO Notes (contact_id, note) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
                PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {                
                deleteStmt.setString(1, contactId.toString());
                deleteStmt.executeUpdate();
                for (String note : notes) {
                    insertStmt.setString(1, contactId.toString());
                    insertStmt.setString(2, note);
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch();
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw new DatabaseException("Error al guardar las notas del contacto.", e);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al manejar la transacción para guardar notas.", e);
        }
    }

    public static List<String> getNotesByContactId(UUID contactId) {
        String sql = "SELECT note FROM Notes WHERE contact_id = ?";
        List<String> notes = new ArrayList<>();
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                 
            pstmt.setString(1, contactId.toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    notes.add(rs.getString("note"));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error al obtener las notas del contacto.", e);
        }
    
        return notes;
    }
}