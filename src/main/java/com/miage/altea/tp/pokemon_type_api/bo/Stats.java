package com.miage.altea.tp.pokemon_type_api.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stats {

    private Integer speed;
    private Integer defense;
    private Integer attack;
    private Integer hp;

}
