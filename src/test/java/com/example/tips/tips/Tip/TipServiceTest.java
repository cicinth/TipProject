package com.example.tips.tips.Tip;

public class TipServiceTest {

    private TipService tipService = new TipService();

    public static void main(String[] args){
        TipServiceTest  test = new TipServiceTest();
        test.runTests();
    }

    public void runTests(){
        boolean test1Passed = givenOneValidValue_andValidPercentage_whenCalculate_thenReturnValidValue();
        boolean test2Passed = givenOneValidValue_andHighPercentage_whenCalculate_thenReturnNotAllowedPercentage();
        boolean test3Passed = givenOneValidValue_andLowPercentage_whenCalculate_thenReturnNotAllowedPercentage();
        boolean test4Passed = shouldReturnInvalidValue_whenValueIsNegative();
        boolean test5Passed = shouldReturnValueWithoutTip_whenTipIsZero();
        System.out.println("Test 1 (Valid tip Calculation) Passed: " + test1Passed);
        System.out.println("Test 2 (Invalid higher percentage) Passed: " + test2Passed);
        System.out.println("Test 3 (Invalid low percentage) Passed: " + test3Passed);
        System.out.println("Test 4 (Negative value) Passed: " + test4Passed);
        System.out.println("Test 5 (Tip is zero) Passed: " + test5Passed);
    }

    public boolean givenOneValidValue_andValidPercentage_whenCalculate_thenReturnValidValue(){
        TipRequest tipRequest = new TipRequest(100.0, 15.0);
        Double value = tipService.computeTip(tipRequest);
        return value == 115.0;
    }

    public boolean givenOneValidValue_andHighPercentage_whenCalculate_thenReturnNotAllowedPercentage(){
        try{
            TipRequest tipRequest = new TipRequest(100.0, 60.0);
             tipService.computeTip(tipRequest);
             return false;
        } catch (IllegalArgumentException e){
            return e.getMessage().equals("Not allowed percentage");
        }
    }

    public boolean givenOneValidValue_andLowPercentage_whenCalculate_thenReturnNotAllowedPercentage(){
        try{
            TipRequest tipRequest = new TipRequest(100.0, -1.0);
            tipService.computeTip(tipRequest);
            return false;
        } catch (IllegalArgumentException e){
            return e.getMessage().equals("Not allowed percentage");
        }
    }

    public boolean shouldReturnInvalidValue_whenValueIsNegative(){
        try{
            TipRequest tipRequest = new TipRequest(-50.0, 10.0);
            tipService.computeTip(tipRequest);
            return false;
        }catch (IllegalArgumentException e){
            return e.getMessage().equals("Value cannot be zero or negative");
        }
    }

    public boolean shouldReturnValueWithoutTip_whenTipIsZero(){
        TipRequest tipRequest = new TipRequest(100.0, 0.0);
        Double value = tipService.computeTip(tipRequest);
        return value == 100.0;
    }


}
