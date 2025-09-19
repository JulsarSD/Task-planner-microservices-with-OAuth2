package micro.planner.users.controller;

import jakarta.ws.rs.core.Response;
import micro.planner.users.dto.UserDTO;
import micro.planner.users.keycloak.KeycloakUtils;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminController {

    private static final int CONFLICT = 409;

    private static final String USER_ROLE_NAME = "user";

    private final static String TOPIC_NAME = "java-test";

    private final KeycloakUtils keycloakUtils;

    private KafkaTemplate<Object, String> kafkaTemplate;

    public AdminController(KeycloakUtils keycloakUtils, KafkaTemplate<Object, String> kafkaTemplate) {
        this.keycloakUtils = keycloakUtils;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody UserDTO userDTO) {

        if (userDTO.getEmail() == null || userDTO.getEmail().trim().length() == 0) {
            return new ResponseEntity("missed param: email", HttpStatus.NOT_ACCEPTABLE);
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().trim().length() == 0) {
            return new ResponseEntity("missed param: password", HttpStatus.NOT_ACCEPTABLE);
        }

        if (userDTO.getUsername() == null || userDTO.getUsername().trim().length() == 0) {
            return new ResponseEntity("missed param: username", HttpStatus.NOT_ACCEPTABLE);
        }

        Response createdResponse = keycloakUtils.createKeycloakUser(userDTO);

        if (createdResponse.getStatus() == CONFLICT) {
            return new ResponseEntity("user or email already exists " + userDTO.getEmail(), HttpStatus.CONFLICT);
        }

        String userId = CreatedResponseUtil.getCreatedId(createdResponse);
        System.out.printf("User created with UserID: %s%n", userId);

        List<String> defaultRoles = new ArrayList<>();
        defaultRoles.add(USER_ROLE_NAME);

        keycloakUtils.addRoles(userId, defaultRoles);

        if (userId != null) {
            kafkaTemplate.send(TOPIC_NAME, userId);
        }

        return ResponseEntity.status(createdResponse.getStatus()).build();

    }

    @PutMapping("/update")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {

        if (userDTO.getId().isBlank()) {
            return new ResponseEntity("missed param:", HttpStatus.NOT_ACCEPTABLE);
        }

        keycloakUtils.updateKeycloakUser(userDTO);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/deletebyid")
    public ResponseEntity deleteByUserId(@RequestBody String userId) {

        keycloakUtils.deleteKeycloakUser(userId);

        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping("/id")
    public ResponseEntity<UserRepresentation> findById(@RequestBody String userId) {

        return ResponseEntity.ok(keycloakUtils.findUserById(userId));
    }


    @PostMapping("/search")
    public ResponseEntity<List<UserRepresentation>> search(@RequestBody String email) {

        return ResponseEntity.ok(keycloakUtils.searchKeycloakUsers("email:" + email));
    }

}
