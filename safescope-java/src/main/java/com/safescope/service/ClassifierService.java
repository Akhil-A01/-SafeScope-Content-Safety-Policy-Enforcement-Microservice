package com.safescope.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.safescope.model.ClassificationResult;

@Service
public class ClassifierService {

    public ClassificationResult classifyUrl(String url) {
        String u = url == null ? "" : url.toLowerCase(Locale.ROOT);

        // simple heuristics-based checks as an initial Java port
        if (u.contains("phish") || u.contains("malware") || u.contains("ransom")) {
            return new ClassificationResult("malicious", 0.99);
        }

        try {
            URI uri = new URI(u.startsWith("http") ? u : "http://" + u);
            String host = uri.getHost();
            if (host != null && (host.endsWith(".ru") || host.endsWith(".cn") || host.contains("suspicious"))) {
                return new ClassificationResult("suspicious", 0.8);
            }
        } catch (URISyntaxException ignored) {
        }

        if (u.contains("login") && u.contains("verify")) {
            return new ClassificationResult("suspicious", 0.7);
        }

        return new ClassificationResult("safe", 0.1);
    }

    public ClassificationResult classifyText(String text) {
        String t = text == null ? "" : text.toLowerCase(Locale.ROOT);
        if (t.contains("credit card") || t.contains("ssn") || t.contains("password")) {
            return new ClassificationResult("malicious", 0.95);
        }
        if (t.contains("click here") || t.contains("free") || t.contains("winner")) {
            return new ClassificationResult("suspicious", 0.6);
        }
        return new ClassificationResult("safe", 0.05);
    }
}
