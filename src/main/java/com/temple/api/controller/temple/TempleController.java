package com.temple.api.controller.temple;

import com.temple.api.payload.temple.TempleDto;
import com.temple.api.payload.temple.TempleResponse;
import com.temple.api.service.temple.TempleService;
import com.temple.api.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/temple")
public class TempleController {

    private TempleService templeService;

    public TempleController(TempleService templeService) {
        this.templeService = templeService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TempleDto> createTemple(
            @Valid @RequestBody TempleDto templeDto
    ){
        return new ResponseEntity<>(templeService.createTemple(templeDto), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public TempleResponse getAllTemples(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return templeService.getAllTemples(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TempleDto> getById(
            @PathVariable(name = "id") long id
    ){
        return ResponseEntity.ok(templeService.getTempleById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TempleDto> updateTempleDto(
            @Valid @RequestBody TempleDto templeDto,
            @PathVariable(name = "id") long id
    ){
        TempleDto templeResponse = templeService.updateTemple(templeDto, id);
        return new ResponseEntity<>(templeResponse, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTemple(@PathVariable(name = "id") long id){
        templeService.deleteTempleById(id);
        return new ResponseEntity<>("Temple deleted successfully.", HttpStatus.OK);
    }
}
