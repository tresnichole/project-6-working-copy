package com.example.dictionary.service;

import com.example.dictionary.model.Entry;
import com.example.dictionary.reference.DictionaryReference;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DictionaryService {

    private static final String INVALID_REFERENCE = "Invalid Reference";

    public Entry getWord(String word) {

        Entry entry = new Entry(word, DictionaryReference.getDictionary()
                                                         .get(word));
        return entry;
    }

    public List<Entry> getWordsStartingWith(String value) {

        return DictionaryReference.getDictionary()
                                  .entrySet()
                                  .stream()
                                  .filter(entry -> entry.getKey()
                                                        .contains(value))
                                  .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                                  .map(entry -> new Entry(entry.getKey(), entry.getValue()))
                                  .collect(Collectors.toList());
    }

    public List<Entry> getWordsThatContainConsecutiveDoubleletters() {

        return DictionaryReference.getDictionary()
                .entrySet()
                .stream()
                .filter( entry -> {

                    String word = entry.getKey();
                    boolean duplicateConsecutiveLetters = false;
                    for(int x = 1; x < word.length(); x++) {
                        if(word.charAt(x) == word.charAt(x - 1)) {
                            duplicateConsecutiveLetters = true;
                            break;
                        }
                    }
                    return duplicateConsecutiveLetters;

                })
                .sorted(Map.Entry.comparingByKey(Comparator.naturalOrder()))
                .map(entry -> new Entry(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());

    }

}
