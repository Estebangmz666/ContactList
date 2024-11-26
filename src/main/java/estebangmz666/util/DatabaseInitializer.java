package estebangmz666.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final String DB_URL = PropertiesLoader.getPathFromProperties("db.url");
    
    public static void initializeDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement()) {

            String createContactsTable = """
                CREATE TABLE IF NOT EXISTS Contacts (
                    id TEXT PRIMARY KEY,
                    name TEXT NOT NULL,
                    lastname TEXT,
                    cellphone TEXT NOT NULL,
                    company TEXT,
                    email TEXT,
                    pronoun TEXT
                );
            """;

            String createNotesTable = """
                CREATE TABLE IF NOT EXISTS Notes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    contact_id TEXT NOT NULL,
                    note TEXT NOT NULL,
                    FOREIGN KEY (contact_id) REFERENCES Contacts (id)
                );
            """;

            statement.execute(createContactsTable);
            System.out.println("Tabla 'Contacts' creada o ya existe.");
            statement.execute(createNotesTable);
            System.out.println("Tabla 'Notes' creada o ya existe.");
        } catch (Exception e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }   
    }
}