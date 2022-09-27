import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    
    private final String delimiter = ",|\n";
    private final String userCustomDelimiter = "//";

    public int add(String number) {
        if (isEmpty(number)){
            return 0;
        } 
        if (number.length()==1) {
            return inputToInt(number);
        } 
        else{
        if (number.startsWith(userCustomDelimiter)){
            String userDelimiters = Delimiter.parseDelimiters(number);
            String rawNumbers = Delimiter.parseNumbers(number,userDelimiters);
            
            if ((rawNumbers.indexOf("-")!=-1)){
                negativesWithCustomDelimiters(rawNumbers);
            }
            if (rawNumbers.split(userDelimiters).length==1) {
                int result = inputToInt(rawNumbers);
                if (result<0){
                    negativesWithCustomDelimiters(rawNumbers);
                } else if (result > 1000) {
                    return 0;
                } else {
                    return result;
                }
            } 
            checkCorrectness(number, userDelimiters, rawNumbers);
            String[] numbers = rawNumbers.split(userDelimiters);

            int sum = sumOfNumbers(numbers,2);
            return sum;
        }
        else{
        containsErrors(number, ",", "\n");
        String[] numbers = number.split(delimiter);
            int sum = sumOfNumbers(numbers,1);
            return sum;
            }
        }
    }
      
    private void containsErrors(String input, String del1, String del2) throws IllegalArgumentException {
        try {
            String error1 = new String(del1+del2);
            String error2 = new String(del2+del1);
            int error1Count = input.indexOf(error1);
            int error2Count = input.indexOf(error2);
            
            if ((error1Count!=-1)||(error2Count!=-1)){
                throw new IllegalArgumentException();
                }
            else if (input.endsWith(del1)||input.endsWith(del2)){
                throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Incorrect delimiter");  
            }
        
        }

    private int inputToInt(String input){
        return Integer.parseInt(input);
    }

    private boolean isEmpty(String input){
        return input.isEmpty();
    }

    private void negativeNumbers(String[] numbers) throws IllegalArgumentException {
        int negativeNumbersCount = 0;
        String negatives = "Negative numbers: ";
        try {
            for(String number : numbers){
                if (inputToInt(number)<0){
                    negativeNumbersCount++;
                    if(negativeNumbersCount==1){
                        negatives+=number;
                    }
                    else {
                        negatives+=(", "+number);
                    }
                }
            }
            if (negativeNumbersCount!=0){
                throw new IllegalArgumentException();
            }
            
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("\nNegative numbers is not allowed\n"+negatives);  
        }
        
    }
    
    private int sumOfNumbers(String[] numbers,int var){
        if (var==1){
            negativeNumbers(numbers);}
        int sum=0;
        for(int i=0; i<numbers.length;){
            if(("".equals(numbers[i])&&var==2)||inputToInt(numbers[i])>1000) 
                i++;
            else {    
                sum +=inputToInt(numbers[i]);
                i++;
                }
            }
        return sum;
    }

    private void checkCorrectness(String input, String delimiters, String numbers) throws IllegalArgumentException {
        try {
            String reg = "^[0-9]{1,4}(("+delimiters+"){1}[0-9]{1,4})+";
            Pattern pattern = Pattern.compile(reg);
            Matcher m = pattern.matcher(numbers);
            
            if (!m.matches()){
                throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Incorrect usage of delimiters");  
            }
        }
    
        private void negativesWithCustomDelimiters(String rawNumbers) throws IllegalArgumentException {
            String negatives_num = "Negative numbers: ";
            int count = 0;
            try {
                Pattern pattern = Pattern.compile("-[0-9]{1,4}");
                Matcher m = pattern.matcher(rawNumbers);
                int start,end=0;
                while (m.find()) {
                    start = m.start(); 
                    end = m.end();
                    if (count!=0){
                        negatives_num+= ", ";
                    }
                    negatives_num += rawNumbers.substring(start, end);
                    count++;
                }
                if (count!=0){
                    throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Negative numbers is not allowed"+"\n"+negatives_num);  
                }
        }

}
