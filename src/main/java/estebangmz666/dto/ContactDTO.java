package estebangmz666.dto;

import estebangmz666.model.Pronoun;

public class ContactDTO {
    private String name;
    private String lastName;
    private String cellphone;
    private String company;
    private String email;
    private Pronoun pronoun;

    public ContactDTO(String name, String lastName, String cellphone, String company, String email, Pronoun pronoun) {
        this.name = name;
        this.lastName = lastName;
        this.cellphone = cellphone;
        this.company = company;
        this.email = email;
        this.pronoun = pronoun;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getCompany() {
        return company;
    }

    public String getEmail() {
        return email;
    }

    public Pronoun getPronoun() {
        return pronoun;
    }    
}