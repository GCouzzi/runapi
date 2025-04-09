package br.gsalles.runapi.rdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RunResponseDTO {

    private Long id;

    private String startLocationName;

    private String endLocationName;

    private Double distance;

    private String userEmail;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
