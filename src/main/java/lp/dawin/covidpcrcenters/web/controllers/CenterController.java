package lp.dawin.covidpcrcenters.web.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lp.dawin.covidpcrcenters.services.CenterService;
import lp.dawin.covidpcrcenters.services.dto.DepartmentDTO;
import lp.dawin.covidpcrcenters.services.dto.SaveCenterDTO;
import lp.dawin.covidpcrcenters.web.model.BindingErrorsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "Covid PCR centers")
@RequestMapping("/api")
public class CenterController {

    static Logger logger = LoggerFactory.getLogger(CenterController.class);

    private CenterService centerService;

    @Autowired
    public CenterController(final CenterService centerService) {
        this.centerService = centerService;
    }

    @GetMapping("/departments")
    @ResponseBody
    @ApiOperation(value = "List all departments")
    List<DepartmentDTO> getAllDepartments() {
        logger.debug("Retrieving all departments");
        return this.centerService.getAllDepartments();
    }

    @GetMapping(value = "/departments/{code}")
    @ResponseBody
    @ApiOperation(value = "Get a single department by its code")
    ResponseEntity<DepartmentDTO> getDepartment(@PathVariable final String code) {
        logger.debug("Retrieving all departments");
        final Optional<DepartmentDTO> dto = this.centerService.getDepartmentByCode(code);
        return ResponseEntity.of(dto);
    }


    //
    // TODO Exercice 2 :  Liste des centres de dépistage d’un département
    // ..
    // @ApiOperation(value = "Get all centers of a department")
    // List<CenterDTO> getCenters( ...
    // ..


    //
    // TODO Exercice 3 :   Affichage d’un centre de dépistage d’un département à partir de son identifiant
    // ..
    // @ApiOperation(value = "Get a single center of a department by its identifier")
    // ResponseEntity<CenterDTO> getCenter(...
    // ..


    // TODO Exercice 4 :  Ajout d’un centre de dépistage
    @PostMapping("/departments/{code}/centers")
    @ApiOperation(value = "Add a new center to a department")
    ResponseEntity<Long> addCenter(@PathVariable final String code, @Valid @RequestBody SaveCenterDTO center, BindingResult bindingResult) {
        logger.debug("Creating a center, add it to department {}", code);

        // TODO Check department existence

        // In case of validation failure, return 400 - BAD REQUEST, give information in headers
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (center == null)) {
            BindingErrorsResponse errors = new BindingErrorsResponse();
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return ResponseEntity.badRequest().headers(headers).build();
        }

        // TODO Create center
        long id = 0;

        // Return identifier of created center
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).body(id);
    }

    //
    // TODO Exercice 5 :  Mise à jour d’un centre de dépistage
    // @PutMapping( ...
    // @ApiOperation(value = "Update a center of a department")
    // ResponseEntity<?> updateCenter(...
    // [...]
    // return ResponseEntity.noContent().build();


    //
    // TODO Exercice 6 :   Suppression d’un centre de dépistage d’un département à partir de son identifiant
    // ..
    //  @ApiOperation(value = "Delete a center of a department")
    // ResponseEntity<?> deleteCenter(@PathVariable final String code, @PathVariable final long centerId) {
    // ..


}
