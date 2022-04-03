package com.endava.proiectfinalandreea.service;

import com.endava.proiectfinalandreea.entity.SupplierEntity;
import com.endava.proiectfinalandreea.exeption.SupplierNotFoundException;
import com.endava.proiectfinalandreea.mapper.SupplierMapper;
import com.endava.proiectfinalandreea.model.SupplierDto;
import com.endava.proiectfinalandreea.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public List<SupplierDto> getSuppliers() {
        return supplierMapper.mapListEntityToListDto(supplierRepository.findAll());
    }

    public SupplierDto getSupplier(Integer supplierId) {
        return supplierMapper.mapEntityToDto(supplierRepository.findById(supplierId).orElseThrow(() -> new SupplierNotFoundException("No such id for a supplier  "+supplierId)));
    }
    public SupplierDto createSupplier(SupplierDto client) {

        SupplierEntity supplierEntity = supplierMapper.mapDtoToEntity(client);
        SupplierEntity savedEntity = supplierRepository.save(supplierEntity);

        return supplierMapper.mapEntityToDto(savedEntity);
    }



}
