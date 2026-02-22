package com.projectdata.autoflex.controller;

import com.projectdata.autoflex.dto.AddRawMaterialDTO;
import com.projectdata.autoflex.dto.UpdateRawMaterialQuantityDTO;
import com.projectdata.autoflex.entity.ProductRawMaterial;
import com.projectdata.autoflex.service.ProductRawMaterialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductRawMaterialController {
    private ProductRawMaterialService productRawMaterialService;

    public ProductRawMaterialController(ProductRawMaterialService productRawMaterialService) {
        this.productRawMaterialService = productRawMaterialService;
    }

    @PostMapping("/{productID}/materials")
    public ResponseEntity<ProductRawMaterial> addRawMaterial(
            @PathVariable("productID") String productID,
            @RequestBody AddRawMaterialDTO addRawMaterialDTO
    ) {
        productRawMaterialService.addRawMaterialToProduct(productID, addRawMaterialDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productID}/materials/{rawMaterialID}")
    public ResponseEntity<Void> updateQuantity(
            @PathVariable("productID") String productID,
            @PathVariable("rawMaterialID") String rawMaterialID,
            @RequestBody UpdateRawMaterialQuantityDTO updateRawMaterialQuantityDTO
    ) {
        productRawMaterialService.updateRawMaterialQuantity(productID, rawMaterialID, updateRawMaterialQuantityDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productID}/materials/{rawMaterialID}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable("productID") String productID, @PathVariable("rawMaterialID") String rawMaterialID) {
        productRawMaterialService.deleteRawMaterialFromProduct(productID, rawMaterialID);
        return ResponseEntity.noContent().build();
    }
}
