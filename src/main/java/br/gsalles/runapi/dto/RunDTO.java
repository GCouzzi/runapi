package br.gsalles.runapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RunDTO {

    @NotBlank
    @Size(min = 2, max = 100)
    private String startLocationName;

    @NotBlank
    @Size(min = 2, max = 100)
    private String endLocationName;

    @NotBlank
    @Email
    @Size(min = 5, max = 50)
    private String userEmail;

}
