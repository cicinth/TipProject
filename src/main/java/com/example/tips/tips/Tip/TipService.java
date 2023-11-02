package com.example.tips.tips.Tip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipService {

    public Double computeTip(TipRequest tipRequest){
        if(tipRequest.tipPercentage() > 50 || tipRequest.tipPercentage() < 0){
            throw  new IllegalArgumentException("Not allowed percentage");
        }

        if(tipRequest.value() <= 0){
            throw new IllegalArgumentException("Value cannot be zero or negative");
        }

        Double value = (tipRequest.value() / 100) * tipRequest.tipPercentage();


        return tipRequest.value() + value;


    }
}
