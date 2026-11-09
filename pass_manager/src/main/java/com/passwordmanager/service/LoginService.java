package com.passwordmanager.service;

import com.passwordmanager.dto.CreatePasswordEntryRequest;
import com.passwordmanager.dto.LoginRequest;
import org.springframework.stereotype.Service;
import com.passwordmanager.model.PasswordEntry;
import com.passwordmanager.repository.PasswordEntryRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.passwordmanager.dto.UpdatePasswordEntryRequest;

import java.util.List;

@Service
public class LoginService {
    private final PasswordEntryRepository passwordEntryRepository;
    public LoginService(PasswordEntryRepository passwordEntryRepository){
        this.passwordEntryRepository = passwordEntryRepository;
    }
    public LoginRequest createLogin(LoginRequest request){
        return request;
    }

    public PasswordEntry createTestEntry(){
        PasswordEntry entry = new PasswordEntry("git", "git", "g8cx", "biwerg", "gg");
        return entry;
    }

    public PasswordEntry createPasswordEntry(CreatePasswordEntryRequest request){
        PasswordEntry entry = new PasswordEntry(
                request.getTitle(),
                request.getWebsite(),
                request.getUsername(),
                request.getEncryptedPassword(),
                request.getNotes()
        );
        return passwordEntryRepository.save(entry);
    }

    public List<PasswordEntry> getAllEntries(){
        return passwordEntryRepository.findAll();
    }

    public PasswordEntry getEntryById(Long id) {
        return passwordEntryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PasswordEntry updateEntry(Long id, UpdatePasswordEntryRequest request) {
        PasswordEntry entry = getEntryById(id);

        entry.setTitle(request.getTitle());
        entry.setWebsite(request.getWebsite());
        entry.setUsername(request.getUsername());
        entry.setEncryptedPassword(request.getEncryptedPassword());
        entry.setNotes(request.getNotes());

        return passwordEntryRepository.save(entry);
    }

    public void deleteEntry(Long id){
        PasswordEntry entry = getEntryById(id);
        passwordEntryRepository.delete(entry);
    }
}
