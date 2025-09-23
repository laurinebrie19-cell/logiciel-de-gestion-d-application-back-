
package services;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.UserRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.UserResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Role;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.RoleRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.UserRepository;

import com.stelotechConsulting.gestionMutuel.utilisateur.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Préparation des données
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setFirstName("Jean");
        user.setLastName("Dupont");
        user.setValid(true);

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<UserResponseDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("Jean", result.get(0).getFirstName());
    }

    @Test
    void testGetUserByIdValidUser() {
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setFirstName("Marie");
        user.setValid(true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDTO result = userService.getUserById(1L);

        assertEquals("Marie", result.getFirstName());
    }

    @Test
    void testCreateUserWithDefaultStatus() {
        UserRequestDTO request = new UserRequestDTO();
        request.setFirstName("Lucas");
        request.setLastName("Martin");
        request.setEmail("lucas@example.com");
        request.setPassword("password");
        request.setRoleIds(Arrays.asList(1L));

        Role role = new Role();
        role.setId(1L);
        role.setRoleName("Utilisateur");

        when(roleRepository.findAllById(request.getRoleIds())).thenReturn(Collections.singletonList(role));
        when(userRepository.save(any(Utilisateur.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserResponseDTO result = userService.createUser(request);

        assertEquals("Lucas", result.getFirstName());
        assertTrue(result.getRoles().contains("Utilisateur"));
    }

    @Test
    void testUpdateUser() {
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setFirstName("OldName");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(Utilisateur.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserRequestDTO request = new UserRequestDTO();
        request.setFirstName("NewName");

        UserResponseDTO result = userService.updateUser(1L, request);

        assertEquals("NewName", result.getFirstName());
    }

    @Test
    void testDeleteUser() {
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setValid(true);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(Utilisateur.class))).thenAnswer(invocation -> invocation.getArgument(0));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).save(user);
        assertFalse(user.isValid());
    }

    @Test
    void testGetUtilisateursActifs() {
        Utilisateur user = new Utilisateur();
        user.setId(1L);
        user.setFirstName("Léa");
        user.setStatus("Actif");
        user.setValid(true);

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<UserResponseDTO> result = userService.getUtilisateursActifs();

        assertEquals(1, result.size());
        assertEquals("Léa", result.get(0).getFirstName());
    }
}
