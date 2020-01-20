package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    public PokemonTypeRepository pokemonTypeRepository;

    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Override
    public PokemonType getPokemonType(int id) {
        PokemonType pokemonType = pokemonTypeRepository.findPokemonTypeById(id);
        return pokemonType;
    }

    @Override
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeRepository.findAllPokemonType();
    }

    @Override
    public PokemonType getPokemonTypeByName(String name) {
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }

    @Override
    public List<PokemonType> getPokemonTypeByTypes(List<String> types) {
        return pokemonTypeRepository.findPokemonTypeByTypeList(types);
    }
}
