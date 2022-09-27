import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

    StringCalculator calculator = new StringCalculator();

    // Крок 1: підтримка нуля
    @Test
    public void emptyStringReturnsZero(){
        assertEquals(calculator.add(""), 0);
    }
    // Крок 1: підтримка одного числа
    @Test
    public void oneNumberGivenReturnsNumber(){
        assertEquals(calculator.add("617"), 617);
    }
    // Крок 1: підтримка пари чисел, розділених комою
    @Test
    public void twoNumbersGivenReturnsSum(){
        assertEquals(calculator.add("2,7"), 9);
    }

    // Крок 2: підтримка довільної кількості чисел, розділених комою
    @Test
    public void moreThanTwoNumbersGivenReturnsSum(){
        assertEquals(calculator.add("2,7,1,5,4"), 19);
    }

    // Крок 3: підтримка роздільника - символ нової строки
    @Test
    public void newLineDelimiterReturnsSun(){
        assertEquals(calculator.add("2\n7\n4"), 13);
    }
    @Test(expected = IllegalArgumentException.class)
    public void incorrectDelimitersInInput(){
        calculator.add("1\n");
    }

    // Крок 4: підтримка роздільників від користувача
    @Test
    public void supportUserDelimiters(){
        assertEquals(calculator.add("//;\n1;2\n5"),8);
    }
    
    // Крок 5: виклик калькулятора з від'ємними числами
    @Test(expected = IllegalArgumentException.class)
    public void negativeNumbersThrowsException(){
        calculator.add("-2,7,-4\n3,8,13");
    }

    // Крок 6: ігноруємо числа більше 1000
    @Test
    public void ignoreNumbersGreaterThanOneThousand(){
        assertEquals(calculator.add("1000,999\n1001"), 1999);
    }

    // Крок 7: роздільник довільної довжини
    @Test
    public void longDelimiter(){
        assertEquals(calculator.add("//[*&]\n19*&2*&31"), 52);
    }

    // Крок 8: декілька роздільників довільної довжини
    @Test
    public void longMultipleDelimiters(){
        assertEquals(calculator.add( "//[*][%%][***]\n1*2%%3***4"), 10);
    }    
}
