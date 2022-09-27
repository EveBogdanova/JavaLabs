
import java.util.regex.*;
public class Delimiter {
    public static String parseDelimiters(String input){
        String delimiters = ",|\\n";

        String oneDelimeter = input.substring(2, input.indexOf("\n")); 
        if (oneDelimeter.length()==1){
            delimiters += "|" + oneDelimeter;
            delimiters = delimiters.replace("*", "\\*"); 
            delimiters = delimiters.replace("+", "\\+"); 
            return delimiters;
        }
        
        Pattern pattern = Pattern.compile("^\\/\\/\\[{1,1}.*]{1,1}\\n");
        Matcher m = pattern.matcher(input);
        int start=0,end=0;
        while (m.find()) {
            start = m.start();
            end = m.end();
        }
        String rawDelimiters = input.substring(start+2, end-1);
        String[] delimitersCollection = rawDelimiters.split("\\[");
        for (int i=1;i<delimitersCollection.length;i++){
            String delimiter = delimitersCollection[i];   
            delimiters += "|" + delimiter.substring(0, delimiter.length()-1);
        } 
        delimiters = delimiters.replace("*", "\\*"); 
        delimiters = delimiters.replace("+", "\\+"); 
        return delimiters;
            }

        
        public static String parseNumbers(String input, String delimeters){
            
            if ((delimeters.length() == 6)||((delimeters.length() == 7) && input.indexOf("*")!=-1)){
                String number = input.substring(4,input.length());
                return number;
            }
            Pattern pattern = Pattern.compile("^\\/\\/\\[{1,1}.*]{1,1}\\n");
            Matcher m = pattern.matcher(input);
            int end=0;
            while (m.find()) {
                end = m.end();
            }
            String numbers = input.substring(end,input.length());

            return numbers;
        }
        }

        


