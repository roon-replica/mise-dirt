package pratice.roon.misedirt.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SignUpDTO {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String matchingPassword;

    public boolean isPasswordMatched() {
        return password.equals(matchingPassword);
    }
}
