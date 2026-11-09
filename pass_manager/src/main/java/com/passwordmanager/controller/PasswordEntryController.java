package com.passwordmanager.controller;

import com.passwordmanager.model.PasswordEntry;
import com.passwordmanager.service.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import com.passwordmanager.dto.UpdatePasswordEntryRequest;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.passwordmanager.dto.CreatePasswordEntryRequest;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/entries")
public class PasswordEntryController {
    private final LoginService loginService;

    public PasswordEntryController(LoginService loginService){
        this.loginService = loginService;
    }

    @GetMapping
    public List<PasswordEntry> getAllEntries() {
        return loginService.getAllEntries();
    }

    @GetMapping("/{id}")
    public PasswordEntry getEntryById(@PathVariable Long id) {
        return loginService.getEntryById(id);
    }

    @PutMapping("/{id}")
    public PasswordEntry updateEntry(
            @PathVariable Long id,
            @RequestBody UpdatePasswordEntryRequest request) {
        return loginService.updateEntry(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable Long id){
        loginService.deleteEntry(id);
    }

    @PostMapping
    public PasswordEntry createPasswordEntry(@Valid @RequestBody CreatePasswordEntryRequest request){
        return loginService.createPasswordEntry(request);
    }

}
