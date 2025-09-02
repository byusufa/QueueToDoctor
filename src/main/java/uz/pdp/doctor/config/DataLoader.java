package uz.pdp.doctor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.doctor.entity.Role;
import uz.pdp.doctor.entity.User;
import uz.pdp.doctor.enums.RoleName;
import uz.pdp.doctor.repo.RoleRepository;
import uz.pdp.doctor.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlauto;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
        if (ddlauto.equals("create")) {

            Role role1 = Role.builder()
                    .roleName(RoleName.ADMIN)
                    .build();
            Role role2 = Role.builder()
                    .roleName(RoleName.USER)
                    .build();
            Role role3 = Role.builder()
                    .roleName(RoleName.DOCTOR)
                    .build();
            Role role4 = Role.builder()
                    .roleName(RoleName.SUPER_ADMIN)
                    .build();

            List<Role> roles = roleRepository.saveAll(new ArrayList<>(List.of(role1, role2, role3, role4)));

            User user1 = User.builder()
                    .roles(new ArrayList<>(List.of(role1)))
                    .firstName("Eshmat")
                    .lastName("Toshmatov")
                    .username("a")
                    .password(passwordEncoder.encode("1"))
                    .build();
            User user2 = User.builder()
                    .roles(new ArrayList<>(List.of(role2)))
                    .firstName("Hikmat")
                    .lastName("Nusratov")
                    .username("b")
                    .password(passwordEncoder.encode("2"))
                    .build();
            User user3 = User.builder()
                    .roles(new ArrayList<>(List.of(role3)))
                    .firstName("Ali")
                    .lastName("Valiyev")
                    .username("c")
                    .password(passwordEncoder.encode("3"))
                    .build();
            userRepository.saveAll(new ArrayList<>(List.of(user1, user2, user3)));
        }
    }
}
