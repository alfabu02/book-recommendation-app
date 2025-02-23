package uz.alfabu.bookrecommendationapp.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.alfabu.bookrecommendationapp.entity.PermissionEnum;
import uz.alfabu.bookrecommendationapp.entity.Role;
import uz.alfabu.bookrecommendationapp.repository.RoleRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlAuto;

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (ddlAuto.equals("create")) {
            Role role = new Role();
            role.setName("USER_ROLE");
            role.setPermissions(List.of(PermissionEnum.GET_ONE_USER));
            roleRepository.save(role);
        }
    }
}
