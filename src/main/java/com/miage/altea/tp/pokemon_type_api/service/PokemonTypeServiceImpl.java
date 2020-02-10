package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PokemonTypeServiceImpl implements PokemonTypeService {

    protected PokemonTypeRepository pokemonTypeRepository;

    private TranslationRepository translationRepository;

    @Override
    public PokemonType getPokemonType(int id) {
        PokemonType pokemonType = pokemonTypeRepository.findPokemonTypeById(id);
        return insertTranslationIntoPokemonType(pokemonType);
    }

    private PokemonType insertTranslationIntoPokemonType(PokemonType pokemonType) {
        String name = this.translationRepository.getPokemonName(pokemonType.getId(), LocaleContextHolder.getLocale());
        pokemonType.setName(name);
        return pokemonType;
    }

    @Override
    public List<PokemonType> getAllPokemonTypes() {
        List<PokemonType> pokemonTypes = pokemonTypeRepository.findAllPokemonType();
        return pokemonTypes.stream()
                .peek(pokemonType ->
                        pokemonType.setName(this.translationRepository.getPokemonName(pokemonType.getId(), LocaleContextHolder.getLocale()))
                )
                .sorted(Comparator.comparingInt(PokemonType::getId))
                .collect(Collectors.toList());
    }

    @Override
    public PokemonType getPokemonTypeByName(String name) {
        return this.insertTranslationIntoPokemonType(pokemonTypeRepository.findPokemonTypeByName(name));
    }

    @Override
    public List<PokemonType> getPokemonTypeByTypes(List<String> types) {
        List<PokemonType> pokemonTypes = pokemonTypeRepository.findPokemonTypeByTypeList(types);
        pokemonTypes.stream().peek(pokemonType ->
                pokemonType.setName(this.translationRepository.getPokemonName(pokemonType.getId(), LocaleContextHolder.getLocale()))
        );
        return pokemonTypes;
    }

    @Override
    @Autowired
    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Override
    @Autowired
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }
}
