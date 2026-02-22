package com.projectdata.autoflex.service;

import com.projectdata.autoflex.dto.CreateRawMaterialDTO;
import com.projectdata.autoflex.dto.RawMaterialProductResponseDTO;
import com.projectdata.autoflex.dto.RawMaterialResponseDTO;
import com.projectdata.autoflex.dto.UpdateRawMaterialDTO;
import com.projectdata.autoflex.entity.RawMaterial;
import com.projectdata.autoflex.repository.RawMaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RawMaterialService {

    private RawMaterialRepository rawMaterialRepository;

    public RawMaterialService(RawMaterialRepository rawMaterialRepository) {
        this.rawMaterialRepository = rawMaterialRepository;
    }

    // Create
    public UUID createRawMaterial(CreateRawMaterialDTO createRawMaterialDTO) {

        var entity = new RawMaterial(
                createRawMaterialDTO.code(),
                createRawMaterialDTO.name(),
                createRawMaterialDTO.stockQuantity(),
                createRawMaterialDTO.products()
        );

        var saved = rawMaterialRepository.save(entity);

        return saved.getId();
    }

    // Get by ID
    public Optional<RawMaterial> getRawMaterialByID(String rawMaterialID) {
        var id = UUID.fromString(rawMaterialID);

        return rawMaterialRepository.findById(id);
    }

    // Get All
//    public List<RawMaterial> listRawMaterials() {
//        return rawMaterialRepository.findAll();
//    }

    public List<RawMaterialResponseDTO> listRawMaterials() {
        return rawMaterialRepository.findAll().stream().map(rawMaterial -> {

            // Converte a lista de associações para o DTO simples
            var materials = rawMaterial.getProducts().stream().map(prm ->
                    new RawMaterialProductResponseDTO(
                            prm.getProduct().getName(),
                            prm.getProduct().getCode()
                    )
            ).toList();

            // Retorna o DTO do produto completo e limpo
            return new RawMaterialResponseDTO(
                    rawMaterial.getId(),
                    rawMaterial.getCode(),
                    rawMaterial.getName(),
                    rawMaterial.getStockQuantity(),
                    materials
            );
        }).toList();
    }

    public void deleteRawMaterialByID(String rawMaterialID) {
        var id = UUID.fromString(rawMaterialID);

        var rawMaterialExists = rawMaterialRepository.existsById(id);

        if (rawMaterialExists) {
            rawMaterialRepository.deleteById(id);
        }
    }

    public RawMaterialResponseDTO updateRawMaterial(String rawMaterialID, UpdateRawMaterialDTO updateRawMaterialDTO) {
        var id = UUID.fromString(rawMaterialID);

        var rawMaterialEntity = rawMaterialRepository.findById(id);

        if (rawMaterialEntity.isPresent()) {
            var rawMaterial = rawMaterialEntity.get();

            if (updateRawMaterialDTO.name() != null) {
                rawMaterial.setName(updateRawMaterialDTO.name());
            }

            if (updateRawMaterialDTO.code() != null) {
                rawMaterial.setCode(updateRawMaterialDTO.code());
            }

            if (updateRawMaterialDTO.code() != null) {
                rawMaterial.setStockQuantity(updateRawMaterialDTO.stockQuantity());
            }

            RawMaterial updatedEntity = rawMaterialRepository.save(rawMaterial);

            return new RawMaterialResponseDTO(
                    updatedEntity.getId(),
                    updatedEntity.getCode(),
                    updatedEntity.getName(),
                    updatedEntity.getStockQuantity(),
                    updatedEntity.getProducts().stream()
                            .map(prm -> new RawMaterialProductResponseDTO(
                                    prm.getProduct().getName(),
                                    prm.getProduct().getCode()
                            )).toList()
            );
        }

        return null;
    }
}
