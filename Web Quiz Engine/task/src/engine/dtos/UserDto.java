package engine.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {

    @NotBlank
    @Pattern(regexp = "[\\w.]+@[\\w.]+\\.\\w+")
    private String email;

    @NotBlank
    @Size(min = 5)
    private String password;

    public UserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
