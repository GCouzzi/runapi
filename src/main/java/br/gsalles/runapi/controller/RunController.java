package br.gsalles.runapi.controller;

import br.gsalles.runapi.dto.RunDTO;
import br.gsalles.runapi.mapper.PageMapper;
import br.gsalles.runapi.mapper.RunMapper;
import br.gsalles.runapi.rdto.RunResponseDTO;
import br.gsalles.runapi.service.RunService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    private final PageMapper pageMapper;

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
    public ResponseEntity<Page<RunResponseDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "fullname") String sort,
                                                        @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(pageMapper.pageRunToPageRunResponseDTO(runService.findAll(page, size, sort, direction)));
    }

    @GetMapping(path = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<RunResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(runMapper.runToRunResponseDTO(runService.findById(id)));
    }

    @GetMapping(path = "user/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RunResponseDTO>> findByUserId(@PathVariable Long id,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size,
                                                             @RequestParam(defaultValue = "fullname") String sort,
                                                             @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(pageMapper.pageRunToPageRunResponseDTO(runService.findByUserId(id, page, size, sort, direction)));
    }

    @GetMapping(path = "distance/greater", produces = {MediaType.APPLICATION_JSON_VALUE
            , MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RunResponseDTO>> findByDistanceGreaterThan(@RequestParam Double distance,
                                                                          @RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size,
                                                                          @RequestParam(defaultValue = "fullname") String sort,
                                                                          @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(pageMapper.pageRunToPageRunResponseDTO(runService.findByDistanceGreaterThan(distance, page, size, sort, direction)));
    }

    @GetMapping(path ="distance/less", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RunResponseDTO>> findByDistanceLessThan(@RequestParam Double distance,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size,
                                                                       @RequestParam(defaultValue = "fullname") String sort,
                                                                       @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(pageMapper.pageRunToPageRunResponseDTO(runService.findByDistanceLessThan(distance, page, size, sort, direction)));
    }

    @GetMapping(path = "date/between", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RunResponseDTO>> findByStartDateBetween(@RequestParam LocalDateTime start,
                                                                       @RequestParam LocalDateTime end,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size,
                                                                       @RequestParam(defaultValue = "fullname") String sort,
                                                                       @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(pageMapper.pageRunToPageRunResponseDTO(runService.findByStartDateBetween(start, end, page, size, sort, direction)));
    }

    @GetMapping(path = "user/{id}/distance/greater",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RunResponseDTO>> findByUserIdAndDistanceGreaterThan(@PathVariable Long id,
                                                                                   @RequestParam Double distance,
                                                                                   @RequestParam(defaultValue = "0") int page,
                                                                                   @RequestParam(defaultValue = "10") int size,
                                                                                   @RequestParam(defaultValue = "fullname") String sort,
                                                                                   @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(
                pageMapper.pageRunToPageRunResponseDTO(runService.findByUserIdAndDistanceGreaterThan(id, distance, page, size, sort, direction))
        );
    }

    @GetMapping(path = "user/{id}/distance/less",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RunResponseDTO>> findByUserIdAndDistanceLessThan(@PathVariable Long id,
                                                                                @RequestParam Double distance,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size,
                                                                                @RequestParam(defaultValue = "fullname") String sort,
                                                                                @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(
                pageMapper.pageRunToPageRunResponseDTO(runService.findByUserIdAndDistanceLessThan(id, distance, page, size, sort, direction))
        );
    }

    @GetMapping(path = "user/{id}/date/between",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RunResponseDTO>> findByUserIdAndStartDateBetween(@PathVariable Long id,
                                                                                @RequestParam LocalDateTime start,
                                                                                @RequestParam LocalDateTime end,
                                                                                @RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size,
                                                                                @RequestParam(defaultValue = "fullname") String sort,
                                                                                @RequestParam(defaultValue = "asc") String direction) {
        return ResponseEntity.ok(
                pageMapper.pageRunToPageRunResponseDTO(runService.findByUserIdAndStartDateBetween(id, start, end, page, size, sort, direction))
        );
    }
}
