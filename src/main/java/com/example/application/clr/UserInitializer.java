package com.example.application.clr;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.application.entities.ERole;
import com.example.application.entities.Role;
import com.example.application.entities.Structure;
import com.example.application.entities.User;
import com.example.application.repositories.RoleRepository;
import com.example.application.repositories.StructureRepository;
import com.example.application.repositories.UserRepository;

import java.util.Optional;
import java.util.Set;

@Component
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final StructureRepository structureRepository;

    public UserInitializer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder,  StructureRepository structureRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.structureRepository = structureRepository;
    }

    @Override
public void run(String... args) throws Exception {
    for (ERole roleEnum : ERole.values()) {
        // Vérifier si le rôle existe, sinon le créer
        Optional<Role> roleOptional = roleRepository.findByName(roleEnum);
        Role role;
        if (roleOptional.isEmpty()) { // Utiliser isEmpty() pour vérifier l'absence
            role = new Role();
            role.setName(roleEnum);
            roleRepository.save(role);
        } else {
            role = roleOptional.get();
        }

        // Vérifier si un utilisateur pour ce rôle existe, sinon le créer
        String username = roleEnum.name().toLowerCase().replace("role_", "");
        if (userRepository.findByUsername(username).isEmpty()) { // Assurez-vous que findByUsername renvoie un Optional
            User user = new User();
            user.setUsername(username);
            user.setEmail(username + "@gmail.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setStatus(true);
            user.setRoles(Set.of(role));
            userRepository.save(user);
            System.out.println("Utilisateur '" + username + "' avec le rôle '" + roleEnum + "' créé.");
        }
        
    }

    structureRepository.save(new Structure("LONATO")); 
}

}
