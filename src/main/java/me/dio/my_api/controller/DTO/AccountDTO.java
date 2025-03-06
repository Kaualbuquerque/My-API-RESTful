package me.dio.my_api.controller.DTO;

import java.math.BigDecimal;

import me.dio.my_api.domain.model.Account;

public record AccountDTO(Long id, String number, String agency, BigDecimal balance, BigDecimal limit) {

    public AccountDTO(Account model) {
        this(model.getId(), model.getNumber(), model.getAgency(), model.getBalance(), model.getLimit());
    }

    public Account toModel() {
        Account model = new Account();

        model.setId(id);
        model.setNumber(number);
        model.setAgency(agency);
        model.setBalance(balance);
        model.setLimit(limit);
        
        return model;
    }
}
