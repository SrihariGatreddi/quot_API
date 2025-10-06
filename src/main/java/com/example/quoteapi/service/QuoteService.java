package com.example.quoteapi.service;

import com.example.quoteapi.model.Quote;
import com.example.quoteapi.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuoteService {
    private final QuoteRepository repository;
    private int apiCounter = 0;

    @Autowired
    public QuoteService(QuoteRepository repository) {
        this.repository = repository;
    }

    public List<Quote> getQuotes() {
        List<Quote> allQuotes = new ArrayList<>(repository.getAllQuotes());
        Collections.shuffle(allQuotes);
        List<Quote> selected = allQuotes.subList(0, Math.min(5, allQuotes.size()));
        for (Quote quote : selected) {
            quote.incrementCounter();
        }
        apiCounter++;
        return selected;
    }

    public int getApiCounter() {
        return apiCounter;
    }

    public Quote addQuote(String text) {
        return repository.addQuote(text);
    }

    public boolean deleteQuote(int id) {
        return repository.deleteQuote(id);
    }
}

