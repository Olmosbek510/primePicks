package uz.primepicks.primepicks.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.primepicks.primepicks.entity.Role;
import uz.primepicks.primepicks.entity.User;
import uz.primepicks.primepicks.entity.enums.RoleName;
import uz.primepicks.primepicks.repo.RoleRepo;
import uz.primepicks.primepicks.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AutoRun implements CommandLineRunner {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
    }

    public void genDate() {
        List<Role> roles = new ArrayList<>(List.of(
                Role.builder()
                        .name(RoleName.ROLE_ADMIN)
                        .build(),
                Role.builder()
                        .name(RoleName.ROLE_USER)
                        .build(),
                Role.builder()
                        .name(RoleName.ROLE_SALES_MANAGER)
                        .build()
        ));
        roleRepo.saveAll(roles);
        Role role = roleRepo.findByName(RoleName.ROLE_ADMIN);
        userRepo.save(User.builder()
                .firstName("Olmosbek")
                .lastName("Urazboev")
                .username("diamond")
                .password(passwordEncoder.encode("123"))
                .roles(List.of(role))
                .build());
    }
}
