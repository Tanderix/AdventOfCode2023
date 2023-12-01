import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class day01part2{
    public static void main(String[] args) {
        String filePath = "./day01.txt";
        Map<String, Integer> nums = Map.of(
            "zero", 0,
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9 
        );

        /*for(Map.Entry<String, Integer> entry : nums.entrySet()){
                System.out.println(entry.getKey() + "---" + entry.getValue());
        }*/

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int sum = 0;
            int firstindex = Integer.MAX_VALUE;
            int lastindex = Integer.MIN_VALUE;
            int numindex = 0;
            String tempfirst = "";
            String templast = "";
            int finalfirst;
            int finallast;

            while ((line = br.readLine()) != null) {
                System.out.println(line);

                for(Map.Entry<String, Integer> entry : nums.entrySet()){
                    if(line.indexOf(entry.getKey()) != -1 && line.indexOf(entry.getKey()) < firstindex){
                        firstindex = line.indexOf(entry.getKey());
                        tempfirst = entry.getValue().toString();
                    }                    
                }

                for(Map.Entry<String, Integer> entry : nums.entrySet()){
                    if(line.lastIndexOf(entry.getKey()) != -1 && line.lastIndexOf(entry.getKey()) > lastindex){
                        lastindex = line.lastIndexOf(entry.getKey());
                        templast = entry.getValue().toString();
                    }                    
                }

                
                System.out.println("\tIndex first word:" + firstindex + " : " + tempfirst);
                System.out.println("\tIndex last word:" + lastindex + " : " + templast);
                

                numindex = 0;
                while(numindex < line.length()-1 && !Character.isDigit(line.charAt(numindex))){
                    numindex++;
                }
                if(numindex < firstindex){
                    firstindex = numindex;
                    tempfirst = "" + line.charAt(numindex);
                }
                
                numindex = line.length()-1;
                while(numindex > -1 && !Character.isDigit(line.charAt(numindex))){
                    numindex--;
                }
                if(numindex > lastindex){
                    lastindex = numindex;
                    templast = "" + line.charAt(numindex);
                }

                finalfirst = firstindex;
                finallast = lastindex;

                firstindex = Integer.MAX_VALUE;
                lastindex = Integer.MIN_VALUE;
                System.out.println("\tFinal first:" + firstindex + " : " + tempfirst);
                System.out.println("\tFinal last:" + lastindex + " : " + templast);

                sum += Integer.parseInt(tempfirst+templast);
            }

            System.out.println("Partial sum: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}