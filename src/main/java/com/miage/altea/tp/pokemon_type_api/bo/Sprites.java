package com.miage.altea.tp.pokemon_type_api.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sprites {

    private String back_default;
    private String front_default;

}
