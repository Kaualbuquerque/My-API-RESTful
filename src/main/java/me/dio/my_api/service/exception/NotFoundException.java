package me.dio.my_api.service.exception;

public class NotFoundException extends BusinessException{
    
    public NotFoundException(){
        super("Resource not found!");
    }
}
