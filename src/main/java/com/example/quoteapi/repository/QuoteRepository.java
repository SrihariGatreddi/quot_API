package com.example.quoteapi.repository;

import com.example.quoteapi.model.Quote;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class QuoteRepository {
    private final List<Quote> quotes = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public QuoteRepository() {
        // Add some initial quotes
        addQuote("The only limit to our realization of tomorrow is our doubts of today.");
        addQuote("Success is not final, failure is not fatal: It is the courage to continue that counts.");
        addQuote("Life is what happens when you're busy making other plans.");
        addQuote("You miss 100% of the shots you don't take.");
        addQuote("The best way to get started is to quit talking and begin doing.");
    }

    public List<Quote> getAllQuotes() {
        return quotes;
    }

    public Quote addQuote(String text) {
        Quote quote = new Quote(idGenerator.getAndIncrement(), text);
        quotes.add(quote);
        return quote;
    }

    public boolean deleteQuote(int id) {
        return quotes.removeIf(q -> q.getId() == id);
    }

    public Optional<Quote> getQuoteById(int id) {
        return quotes.stream().filter(q -> q.getId() == id).findFirst();
    }
}

