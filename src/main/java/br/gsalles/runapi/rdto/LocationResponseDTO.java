package br.gsalles.runapi.rdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationResponseDTO {

    private Long id;

    private String name;

    private Double latitude;

    private Double longitude;
}
