package com.bcncgroup.pricingservice.shared.domain.exceptions;

public class PriceNotFoundException extends RuntimeException{
    public PriceNotFoundException(String message){super(message);}
}
