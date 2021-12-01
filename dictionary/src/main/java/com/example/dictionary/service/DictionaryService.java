package com.example.dictionary.service;

import com.example.dictionary.model.Entry;
import com.example.dictionary.reference.DictionaryReference;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {

    private static final String INVALID_REFERENCE = "Invalid Reference";

    public Entry getWord(String word) {

        Entry entry = new Entry(word, DictionaryReference.getDictionary()
                                                         .get(word));

        return entry;
    }
}
