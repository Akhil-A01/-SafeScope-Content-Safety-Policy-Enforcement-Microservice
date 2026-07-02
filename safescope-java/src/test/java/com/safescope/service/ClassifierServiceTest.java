package com.safescope.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.safescope.model.ClassificationResult;

public class ClassifierServiceTest {

    @Test
    public void testUrlMalicious() {
        ClassifierService s = new ClassifierService();
        ClassificationResult r = s.classifyUrl("http://badsite.example/malware");
        assertEquals("malicious", r.getLabel());
        assertTrue(r.getScore() > 0.8);
    }

    @Test
    public void testTextSensitive() {
        ClassifierService s = new ClassifierService();
        ClassificationResult r = s.classifyText("Please send your credit card number");
        assertEquals("malicious", r.getLabel());
    }
}
