package br.gsalles.runapi.controller;

import br.gsalles.runapi.dto.RunDTO;
import br.gsalles.runapi.mapper.RunMapper;
import br.gsalles.runapi.rdto.RunResponseDTO;
import br.gsalles.runapi.service.RunService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/runs")
@RequiredArgsConstructor
public class RunController {

    private final RunService runService;
    private final RunMapper runMapper;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RunResponseDTO> create(@RequestBody @Valid RunDTO runDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(runMapper.runToRunResponseDTO(runService.create(runDTO)));
    }

    @GetMapping(path = "end/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RunResponseDTO> end(@PathVariable Long id) {
        return ResponseEntity.ok(runMapper.runToRunResponseDTO(runService.end(id)));
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<RunResponseDTO>> findAll() {
        return ResponseEntity.ok(runMapper.listRunToListRunResponseDTO(runService.findAll()));
    }

    @GetMapping(path = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RunResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(runMapper.runToRunResponseDTO(runService.findById(id)));
    }

    @GetMapping(path = "user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<RunResponseDTO>> findByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(runMapper.listRunToListRunResponseDTO(runService.findByUserId(id)));
    }

    @GetMapping(path = "distance/greater", produces = {MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<RunResponseDTO>> findByDistanceGreaterThan(@RequestParam Double distance) {
        return ResponseEntity.ok(runMapper.listRunToListRunResponseDTO(runService.findByDistanceGreaterThan(distance)));
    }

    @GetMapping(path ="distance/less", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<RunResponseDTO>> findByDistanceLessThan(@RequestParam Double distance) {
        return ResponseEntity.ok(runMapper.listRunToListRunResponseDTO(runService.findByDistanceLessThan(distance)));
    }

    @GetMapping(path = "date/between", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<RunResponseDTO>> findByStartDateBetween(@RequestParam LocalDateTime start,
                                                                       @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(runMapper.listRunToListRunResponseDTO(runService.findByStartDateBetween(start, end)));
    }

    @GetMapping(path = "user/{id}/distance/greater",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<RunResponseDTO>> findByUserIdAndDistanceGreaterThan(@PathVariable Long id,
                                                                                   @RequestParam Double distance) {
        return ResponseEntity.ok(
                runMapper.listRunToListRunResponseDTO(runService.findByUserIdAndDistanceGreaterThan(id, distance))
        );
    }

    @GetMapping(path = "user/{id}/distance/less",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<RunResponseDTO>> findByUserIdAndDistanceLessThan(@PathVariable Long id,
                                                                                @RequestParam Double distance) {
        return ResponseEntity.ok(
                runMapper.listRunToListRunResponseDTO(runService.findByUserIdAndDistanceLessThan(id, distance))
        );
    }

    @GetMapping(path = "user/{id}/date/between",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<RunResponseDTO>> findByUserIdAndStartDateBetween(@PathVariable Long id,
                                                                                @RequestParam LocalDateTime start,
                                                                                @RequestParam LocalDateTime end) {
        return ResponseEntity.ok(
                runMapper.listRunToListRunResponseDTO(runService.findByUserIdAndStartDateBetween(id, start, end))
        );
    }
}
