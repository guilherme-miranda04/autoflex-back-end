package com.projectdata.autoflex.controller;

import com.projectdata.autoflex.dto.*;
import com.projectdata.autoflex.entity.Product;
import com.projectdata.autoflex.entity.ProductRawMaterial;
import com.projectdata.autoflex.service.ProductRawMaterialService;
import com.projectdata.autoflex.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private ProductRawMaterialService productRawMaterialService;
    private ProductService productService;

    public ProductController(ProductRawMaterialService productRawMaterialService, ProductService productService) {
        this.productRawMaterialService = productRawMaterialService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDTO createProductDTO) {
        try {
            var createdProduct = productService.createProduct(createProductDTO);

//            return ResponseEntity.created(URI.create("/product/" + updatedProduct.getId().toString())).build();

            return ResponseEntity.ok(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{productID}")
    public ResponseEntity<Product> getProduct(@PathVariable("productID") String productID) {
        var product = productService.getProductByID(productID);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> listProducts() {
        var products = productService.listProducts();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/production-suggestion")
    public ResponseEntity<List<ProductionSuggestionDTO>> productionSuggestion() {
        var productionData = productService.calculateProductionSuggestion();

        return ResponseEntity.ok(productionData);
    }

    @PutMapping("/{productID}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable("productID") String productID, @RequestBody UpdateProductDTO updateProductDTO) {
        var updatedProduct = productService.updateProduct(productID, updateProductDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<Void> deleteProductByID(@PathVariable("productID") String productID) {
        productService.deleteProductByID(productID);

        return ResponseEntity.noContent().build();
    }

    // Raw in Product:

    @PostMapping("/{productID}/raw-material")
    public ResponseEntity<ProductRawMaterial> addRawMaterial(
            @PathVariable("productID") String productID,
            @RequestBody AddRawMaterialDTO addRawMaterialDTO
    ) {
        productRawMaterialService.addRawMaterialToProduct(productID, addRawMaterialDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productID}/raw-material/{rawMaterialID}")
    public ResponseEntity<Void> updateQuantity(
            @PathVariable("productID") String productID,
            @PathVariable("rawMaterialID") String rawMaterialID,
            @RequestBody UpdateRawMaterialQuantityDTO updateRawMaterialQuantityDTO
    ) {
        productRawMaterialService.updateRawMaterialQuantity(productID, rawMaterialID, updateRawMaterialQuantityDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productID}/raw-material/{rawMaterialID}")
    public ResponseEntity<Void> deleteRawMaterial(@PathVariable("productID") String productID, @PathVariable("rawMaterialID") String rawMaterialID) {
        productRawMaterialService.deleteRawMaterialFromProduct(productID, rawMaterialID);
        return ResponseEntity.noContent().build();
    }
}
