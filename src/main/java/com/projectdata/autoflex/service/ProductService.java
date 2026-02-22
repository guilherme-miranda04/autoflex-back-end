package com.projectdata.autoflex.service;

import com.projectdata.autoflex.dto.*;
import com.projectdata.autoflex.entity.Product;
import com.projectdata.autoflex.entity.ProductRawMaterial;
import com.projectdata.autoflex.entity.RawMaterial;
import com.projectdata.autoflex.repository.ProductRepository;
import com.projectdata.autoflex.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private RawMaterialRepository rawMaterialRepository;

    public ProductService(ProductRepository productRepository, RawMaterialRepository rawMaterialRepository) {
        this.productRepository = productRepository;
        this.rawMaterialRepository = rawMaterialRepository;
    }

    public Product createProduct(CreateProductDTO createProductDTO) {

        var entity = new Product(
                createProductDTO.code(),
                createProductDTO.name(),
                createProductDTO.price(),
                createProductDTO.rawMaterials()
        );

        // For security, I prefer to give an uuid to user.
        var saved = productRepository.save(entity);

        return saved;
    }

    public Optional<Product> getProductByID(String productID) {
        var id = UUID.fromString(productID);

        return productRepository.findById(id);
    }

//    public List<Product> listProducts() {
//        return productRepository.findAll();
//    }

    public List<ProductResponseDTO> listProducts() {
        return productRepository.findAll().stream().map(product -> {

            // Converte a lista de associações para o DTO simples
            var materials = product.getRawMaterials().stream().map(prm ->
                    new ProductMaterialResponseDTO(
                            prm.getRawMaterial().getId(),
                            prm.getRawMaterial().getName(),
                            prm.getRawMaterial().getCode(),
                            prm.getQuantityNeeded()
                    )
            ).toList();

            // Retorna o DTO do produto completo e limpo
            return new ProductResponseDTO(
                    product.getId(),
                    product.getCode(),
                    product.getName(),
                    product.getPrice(),
                    materials
            );
        }).toList();
    }

    public void deleteProductByID(String productID) {
        var id = UUID.fromString(productID);

        var productExists = productRepository.existsById(id);

        if (productExists) {
            productRepository.deleteById(id);
        }
    }

    public ProductResponseDTO updateProduct(String productID, UpdateProductDTO updateProductDTO) {
        var id = UUID.fromString(productID);

        var productEntity = productRepository.findById(id);

        if (productEntity.isPresent()) {
            var product = productEntity.get();

            if (updateProductDTO.name() != null) {
                product.setName(updateProductDTO.name());
            }

            if (updateProductDTO.code() != null) {
                product.setCode(updateProductDTO.code());
            }

            if (updateProductDTO.price() != null) {
                product.setPrice(updateProductDTO.price());
            }

            Product updatedEntity = productRepository.save(product);

            return new ProductResponseDTO(
                    updatedEntity.getId(),
                    updatedEntity.getCode(),
                    updatedEntity.getName(),
                    updatedEntity.getPrice(),
                    updatedEntity.getRawMaterials().stream()
                            .map(prm -> new ProductMaterialResponseDTO(
                                    prm.getRawMaterial().getId(),
                                    prm.getRawMaterial().getName(),
                                    prm.getRawMaterial().getCode(),
                                    prm.getQuantityNeeded()
                            )).toList()
            );
        }

        return null;
    }

    public List<ProductionSuggestionDTO> calculateProductionSuggestion() {

        var products = productRepository.findAll();
        products.sort((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()));

        Map<UUID, BigDecimal> realStock = rawMaterialRepository.findAll().stream()
                .collect(Collectors.toMap(RawMaterial::getId, RawMaterial::getStockQuantity));

        List<ProductionSuggestionDTO> suggestions = new ArrayList<>();

        for (Product product : products) {
            if (product.getRawMaterials().isEmpty()) continue;

            Map<UUID, BigDecimal> simulationStock = new HashMap<>(realStock);

            int canProduce = 0;
            boolean hasStock = true;

            while (hasStock) {
                for (ProductRawMaterial prm : product.getRawMaterials()) {
                    BigDecimal needed = prm.getQuantityNeeded();
                    BigDecimal available = simulationStock.getOrDefault(prm.getRawMaterial().getId(), BigDecimal.ZERO);

                    if (available.compareTo(needed) < 0) {
                        hasStock = false;
                        break;
                    }
                }

                if (hasStock) {
                    canProduce++;

                    for (ProductRawMaterial prm : product.getRawMaterials()) {
                        UUID RawMaterialId = prm.getRawMaterial().getId();
                        simulationStock.put(RawMaterialId, simulationStock.get(RawMaterialId).subtract(prm.getQuantityNeeded()));
                    }
                }
            }

            if (canProduce > 0) {
                BigDecimal totalValue = product.getPrice().multiply(new BigDecimal(canProduce));
                suggestions.add(new ProductionSuggestionDTO(
                        product.getId(), product.getName(), product.getPrice(), new BigDecimal(canProduce), totalValue
                ));
            }
        }

        return suggestions;
    }
}
