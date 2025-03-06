package me.dio.my_api.controller.DTO;

import java.math.BigDecimal;

import me.dio.my_api.domain.model.Card;

public record CardDTO(Long id, String number, BigDecimal limit) {
    
    public CardDTO(Card model){
        this(model.getId(), model.getNumber(), model.getLimit());
    }

    public Card toModel(){
        Card model = new Card();

        model.setId(id);
        model.setNumber(number);
        model.setLimit(limit);

        return model;
    }
}
