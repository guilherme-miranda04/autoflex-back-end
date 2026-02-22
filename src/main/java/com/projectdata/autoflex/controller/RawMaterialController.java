package com.projectdata.autoflex.controller;

import com.projectdata.autoflex.dto.CreateRawMaterialDTO;
import com.projectdata.autoflex.dto.RawMaterialResponseDTO;
import com.projectdata.autoflex.dto.UpdateRawMaterialDTO;
import com.projectdata.autoflex.entity.RawMaterial;
import com.projectdata.autoflex.service.RawMaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/raw-material")
@CrossOrigin(origins = "http://localhost:5173")
public class RawMaterialController {

    private RawMaterialService rawMaterialService;

    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @PostMapping
    public ResponseEntity<RawMaterial> createRawMaterial(@RequestBody CreateRawMaterialDTO createRawMaterialDTO) {
        try {
            var rawMaterialID = rawMaterialService.createRawMaterial(createRawMaterialDTO);

            return ResponseEntity.created(URI.create("/raw-material/" + rawMaterialID.toString())).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{rawMaterialID}")
    public ResponseEntity<RawMaterial> getRawMaterial(@PathVariable("rawMaterialID") String rawMaterialID) {
        var rawMaterial = rawMaterialService.getRawMaterialByID(rawMaterialID);

        if (rawMaterial.isPresent()) {
            return ResponseEntity.ok(rawMaterial.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<RawMaterialResponseDTO>> listRawMaterials() {
        var rawMaterials = rawMaterialService.listRawMaterials();

        return ResponseEntity.ok(rawMaterials);
    }

    @PutMapping("/{rawMaterialID}")
    public ResponseEntity<RawMaterialResponseDTO> updateRawMaterial(@PathVariable("rawMaterialID") String rawMaterialID, @RequestBody UpdateRawMaterialDTO updateRawMaterialDTO) {
        var updatedRawMaterial = rawMaterialService.updateRawMaterial(rawMaterialID, updateRawMaterialDTO);
        return ResponseEntity.ok(updatedRawMaterial);
    }

    @DeleteMapping("/{rawMaterialID}")
    public ResponseEntity<Void> deleteRawMaterialByID(@PathVariable("rawMaterialID") String rawMaterialID) {
        rawMaterialService.deleteRawMaterialByID(rawMaterialID);

        return ResponseEntity.noContent().build();
    }
}
