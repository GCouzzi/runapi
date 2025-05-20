package br.gsalles.runapi.rdto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String acessToken;
    private String tokenType = "Bearer ";

    public AuthResponseDTO(String acessToken) {
        this.acessToken = acessToken;
    }
}
