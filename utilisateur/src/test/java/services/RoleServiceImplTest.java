package services;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.RoleResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Role;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.PermissionRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.RoleRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.RoleService;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    void getAllRoles_ShouldReturnAllRolesExceptId1(){

        // 1. Créer des rôles de test
        Role role = new Role(1L, "SuperAdmin");
        Role role1 = new Role(2L, "ROLE_USER");
        Role role2 = new Role(3L, "ROLE_MANAGER");

        // 2. Configurer le repository mocké pour renvoyer ces rôles
        when(roleRepository.findAll()).thenReturn(List.of(role, role1, role2));

        when(modelMapper.map(any(), any()))
                .thenReturn(RoleResponseDTO.builder()
                .id(4L)
                .roleName("ROLE_ADMIN")
                .build());

        // 3. Appel de la methode
        List<RoleResponseDTO> result = roleService.getAllRoles();

        // 4. Vérifier qu'on a bien 2 rôles (car on exclut celui avec ID=1)
        assertThat(result).hasSize(2);

        // 5. Vérifier que le rôle avec ID=1 n'est pas présent
        assertThat(result).extracting("id").doesNotContain(1L);

        // 7. Vérifier qu'on a bien appelé le repository
        verify(roleRepository).findAll();
    }
}
