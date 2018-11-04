package pl.mrumlova.autodrom.dto;

import javax.validation.constraints.Email;

public class FormDto {

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
