package com.projectdata.autoflex.service;

import com.projectdata.autoflex.entity.Product;
import com.projectdata.autoflex.entity.ProductRawMaterial;
import com.projectdata.autoflex.entity.RawMaterial;
import com.projectdata.autoflex.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void calculateProductionSuggestionTest() {
        RawMaterial iron = new RawMaterial();
        iron.setStockQuantity(new BigDecimal("10"));

        RawMaterial plastic = new RawMaterial();
        plastic.setStockQuantity(new BigDecimal("9"));

        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Produto A");
        product.setPrice(new BigDecimal("100"));

        ProductRawMaterial prm1 = new ProductRawMaterial();
        prm1.setProduct(product);
        prm1.setRawMaterial(iron);
        prm1.setQuantityNeeded(new BigDecimal("2"));

        ProductRawMaterial prm2 = new ProductRawMaterial();
        prm2.setProduct(product);
        prm2.setRawMaterial(plastic);
        prm2.setQuantityNeeded(new BigDecimal("3"));

        product.setRawMaterials(Set.of(prm1, prm2));

        when(productRepository.findAll()).thenReturn(List.of(product));

        var result = productService.calculateProductionSuggestion();
        System.out.println(result);
        
        assertEquals(1, result.size());
        assertEquals(new BigDecimal("3"), result.get(0).quantityPossible());
        assertEquals(new BigDecimal("300"), result.get(0).totalValue());
    }
}
