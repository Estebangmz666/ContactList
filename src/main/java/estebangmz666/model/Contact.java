package estebangmz666.model;

import java.util.ArrayList;
import java.util.UUID;

public class Contact {
    private UUID id;
    private String name;
    private String lastName;
    private String cellphone;
    private String company;
    private ArrayList<String> notes;
    private String email;
    private Pronoun pronoun;

    public Contact(UUID id, String name, String lastName, String cellphone, String company, ArrayList<String> notes, String email, Pronoun pronoun) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.cellphone = cellphone;
        this.company = company;
        this.notes = (notes != null) ? notes : new ArrayList<>();
        this.email = email;
        this.pronoun = pronoun;
    }

    public Contact(UUID id, String name, String lastName, String cellphone, String company, String email, Pronoun pronoun) {
        this(id, name, lastName, cellphone, company, new ArrayList<>(), email, pronoun);
    }
    

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("Formato de correo electrónico no válido");
        }
        this.email = email;
    }
    
    public Pronoun getPronoun() {
        return pronoun;
    }

    public void setPronoun(Pronoun pronoun) {
        this.pronoun = pronoun;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((cellphone == null) ? 0 : cellphone.hashCode());
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((pronoun == null) ? 0 : pronoun.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contact other = (Contact) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (cellphone == null) {
            if (other.cellphone != null)
                return false;
        } else if (!cellphone.equals(other.cellphone))
            return false;
        if (company == null) {
            if (other.company != null)
                return false;
        } else if (!company.equals(other.company))
            return false;
        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (pronoun != other.pronoun)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Contact [id=" + id + ", name=" + name + ", lastName=" + lastName + ", cellphone=" + cellphone
                + ", company=" + company + ", notes=" + notes + ", email=" + email + ", pronoun=" + pronoun + "]";
    }

}