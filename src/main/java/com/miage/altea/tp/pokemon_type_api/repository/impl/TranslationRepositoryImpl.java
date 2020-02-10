package com.miage.altea.tp.pokemon_type_api.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.bo.Translation;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

    private Map<Locale, List<Translation>> translations;

    private List<Translation> defaultTranslations;

    public TranslationRepositoryImpl() {
        try {
            var objectMapper = new ObjectMapper();

            var frenchTranslationStream = new ClassPathResource("translations-fr.json").getInputStream();
            var frenchTranslationsArray = objectMapper.readValue(frenchTranslationStream, Translation[].class);

            var englishTranslationStream = new ClassPathResource("translations-en.json").getInputStream();
            var englishTranslationsArray = objectMapper.readValue(englishTranslationStream, Translation[].class);

            this.translations = Map.of(
                    Locale.FRENCH, List.of(frenchTranslationsArray),
                    Locale.ENGLISH, List.of(englishTranslationsArray)
            );

            this.defaultTranslations = List.of(englishTranslationsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPokemonName(int id, Locale locale) {
        List<Translation> translations = this.translations.get(locale);
        if (translations == null){
            translations = this.defaultTranslations;
        }
        Translation translation = translations.stream().filter(translation1 -> translation1.getId() == id).findFirst().get();
        return translation.getName();
    }
}
