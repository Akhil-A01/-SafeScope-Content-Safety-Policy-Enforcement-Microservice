package com.safescope.controller;

import com.safescope.model.ClassificationResult;
import com.safescope.service.ClassifierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClassifierController {

    private final ClassifierService service;

    public ClassifierController(ClassifierService service) {
        this.service = service;
    }

    @PostMapping("/classify")
    public ResponseEntity<ClassificationResult> classify(@RequestBody Map<String, String> body) {
        if (body.containsKey("url")) {
            ClassificationResult r = service.classifyUrl(body.get("url"));
            return ResponseEntity.ok(r);
        }
        if (body.containsKey("text")) {
            ClassificationResult r = service.classifyText(body.get("text"));
            return ResponseEntity.ok(r);
        }
        return ResponseEntity.badRequest().build();
    }
}
