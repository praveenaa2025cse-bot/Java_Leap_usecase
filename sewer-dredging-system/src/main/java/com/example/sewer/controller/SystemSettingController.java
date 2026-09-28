package com.example.sewer.controller;

import com.example.sewer.entity.SystemSetting;
import com.example.sewer.repository.SystemSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sewer/settings")
public class SystemSettingController {
    @Autowired
    private SystemSettingRepository repository;
    @GetMapping
    public List<SystemSetting> getAll() { return repository.findAll(); }
    @PostMapping
    public SystemSetting create(@RequestBody SystemSetting entity) { return repository.save(entity); }
}
