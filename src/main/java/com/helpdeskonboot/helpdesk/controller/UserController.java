package com.helpdeskonboot.helpdesk.controller;


import com.helpdeskonboot.helpdesk.dto.UserDTO;
import com.helpdeskonboot.helpdesk.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.getUserFromDBWithData(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getDTOById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getDTOById(id));
    }
}
