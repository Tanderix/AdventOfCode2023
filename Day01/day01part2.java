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

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int sum = 0;
            int firstindex = Integer.MAX_VALUE;
            int lastindex = Integer.MIN_VALUE;
            int numindex = 0;
            String tempfirst = "";
            String templast = "";

            while ((line = br.readLine()) != null) {

                for(Map.Entry<String, Integer> entry : nums.entrySet()){
                    if(line.indexOf(entry.getKey()) != -1 && line.indexOf(entry.getKey()) < firstindex){
                        firstindex = line.indexOf(entry.getKey());
                        tempfirst = entry.getValue().toString();
                    }
                    
                    if(line.lastIndexOf(entry.getKey()) != -1 && line.lastIndexOf(entry.getKey()) > lastindex){
                        lastindex = line.lastIndexOf(entry.getKey());
                        templast = entry.getValue().toString();
                    } 
                }

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

                firstindex = Integer.MAX_VALUE;
                lastindex = Integer.MIN_VALUE;

                sum += Integer.parseInt(tempfirst+templast);
            }

            System.out.println("Part 2: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}