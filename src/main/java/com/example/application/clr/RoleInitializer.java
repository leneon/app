package com.example.application.clr;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.application.entities.ERole;
import com.example.application.entities.Role;
import com.example.application.repositories.RoleRepository;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Itérer sur les valeurs de l'énumération
        for (ERole roleEnum : ERole.values()) {
            if (roleRepository.findByName(roleEnum) == null) {
                Role role = new Role();
                role.setName(roleEnum);
                roleRepository.save(role);
                System.out.println("Role '" + roleEnum + "' ajouté.");
            }
        }
   

    }
}
