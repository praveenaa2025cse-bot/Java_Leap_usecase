package com.example.sewer.controller;

import com.example.sewer.entity.Contact;
import com.example.sewer.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/contacts")
public class ContactController {
    @Autowired
    private ContactRepository repository;
    @GetMapping
    public List<Contact> getAll() { return repository.findAll(); }
    @PostMapping
    public Contact create(@RequestBody Contact entity) { return repository.save(entity); }
}
