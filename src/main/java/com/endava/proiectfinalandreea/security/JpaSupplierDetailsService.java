//package com.endava.proiectfinalandreea.security;
//
//import com.endava.proiectfinalandreea.entity.SupplierEntity;
//import com.endava.proiectfinalandreea.model.SecuritySupplier;
//import com.endava.proiectfinalandreea.repository.SupplierRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class JpaSupplierDetailsService implements UserDetailsService {
//
//    private final SupplierRepository supplierRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<SupplierEntity> dbUser = supplierRepository.findUserByUserName(username);
//        SupplierEntity user = dbUser.orElseThrow(() -> new UsernameNotFoundException("User was not found in the database"));
//        return new SecuritySupplier(user);
//    }
//}
