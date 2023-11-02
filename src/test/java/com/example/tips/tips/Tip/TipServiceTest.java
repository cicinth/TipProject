package com.example.tips.tips.Tip;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class TipServiceTest {

    @InjectMocks
    private TipService tipService;


    @Test
    public void givenOneValidValue_andValidPercentage_whenCalculate_thenReturnValidValue(){
        TipRequest tipRequest = new TipRequest(100.0, 15.0);
        Double value = tipService.computeTip(tipRequest);
        assertEquals(115.0, value, 0.1);
    }

    @Test
    public void givenOneValidValue_andHighPercentage_whenCalculate_thenReturnNotAllowedPercentage(){
        try{
            TipRequest tipRequest = new TipRequest(100.0, 60.0);
            tipService.computeTip(tipRequest);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e){
            assertEquals("Not allowed percentage", e.getMessage());
        }
    }

    @Test
    public void givenOneValidValue_andLowPercentage_whenCalculate_thenReturnNotAllowedPercentage(){
        try{
            TipRequest tipRequest = new TipRequest(100.0, -1.0);
            tipService.computeTip(tipRequest);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e){
            assertEquals("Not allowed percentage", e.getMessage());
        }
    }

    @Test
    public void shouldReturnInvalidValue_whenValueIsNegative(){
        try{
            TipRequest tipRequest = new TipRequest(-50.0, 10.0);
            tipService.computeTip(tipRequest);
            fail("Expected IllegalArgumentException");
        }catch (IllegalArgumentException e){
            assertEquals("Value cannot be zero or negative", e.getMessage());
        }
    }

    @Test
    public void shouldReturnValueWithoutTip_whenTipIsZero(){
        TipRequest tipRequest = new TipRequest(100.0, 0.0);
        Double value = tipService.computeTip(tipRequest);
        assertEquals(100.0, value, 0.1);
    }


}
