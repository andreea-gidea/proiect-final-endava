//package com.endava.proiectfinalandreea.controller;
//
//
//import com.endava.proiectfinalandreea.model.SupplierDto;
//import com.endava.proiectfinalandreea.service.SupplierService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/supplier")
//public class SupplierController {
//
//    private final SupplierService supplierService;
//
//    @GetMapping()
//    public List<SupplierDto> getSuppliers() {
//        return supplierService.getSuppliers();
//    }
//
//    @GetMapping("/{supplierId}")
//    public SupplierDto get(@PathVariable(name = "supplierId") Integer supplierId) {
//        return supplierService.getSupplier(supplierId);
//    }
//    @PostMapping()
//    public SupplierDto createSupplier(@RequestBody SupplierDto supplierDto){
//        return supplierService.createSupplier(supplierDto);
//    }
//
//    @DeleteMapping("/{supplierId}")
//    public void deletePost(@PathVariable(name = "supplierId") Integer SupplierId){
//        supplierService.deleteSupplier(SupplierId);
//    }
//
//}