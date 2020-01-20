package com.miage.altea.tp.pokemon_type_api.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();

            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);
        PokemonType pokemonTypebis = pokemons.stream().filter(pokemonType -> pokemonType.getId() == id).findFirst().orElseThrow(FileNotFoundException::new);
        return pokemonTypebis;
    }

    @SneakyThrows
    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        return pokemons.stream().filter(pokemonType -> pokemonType.getName().equals(name)).findFirst().orElseThrow(FileNotFoundException::new);
    }

    @Override
    public List<PokemonType> findAllPokemonType()  {
        return pokemons;
    }

    @Override
    public List<PokemonType> findPokemonTypeByTypeList(List<String> types) {
        return pokemons.stream().filter(pokemon -> pokemon.getTypes().containsAll(types)).collect(Collectors.toList());
    }
}
