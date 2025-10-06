package com.example.quoteapi.controller;

import com.example.quoteapi.model.Quote;
import com.example.quoteapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuoteController {
    private final QuoteService service;

    @Autowired
    public QuoteController(QuoteService service) {
        this.service = service;
    }

    @GetMapping("/quote")
    public Map<String, Object> getQuotes() {
        List<Quote> quotes = service.getQuotes();
        Map<String, Object> response = new HashMap<>();
        response.put("quotes", quotes);
        response.put("apiHits", service.getApiCounter());
        return response;
    }

    @PostMapping("/admin/quote")
    public Quote addQuote(@RequestBody Map<String, String> body) {
        String text = body.get("text");
        return service.addQuote(text);
    }

    @DeleteMapping("/admin/quote/{id}")
    public Map<String, String> deleteQuote(@PathVariable int id) {
        boolean deleted = service.deleteQuote(id);
        if (deleted) {
            return Map.of("message", "Quote deleted successfully");
        } else {
            return Map.of("error", "Quote not found");
        }
    }
}

