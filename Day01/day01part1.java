import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day01part1{
    public static void main(String[] args) {
        String filePath = "./day01.txt"; 

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int sum = 0;
            while ((line = br.readLine()) != null) {
                int index = 0;
                String totstring = "";

                while(!Character.isDigit(line.charAt(index))){
                    index++;
                }

                totstring+=line.charAt(index);
                index = line.length()-1;

                while(!Character.isDigit(line.charAt(index))){
                    index--;
                }
                
                totstring+=line.charAt(index);

                sum += Integer.parseInt(totstring);
                System.out.println();

            }

            System.out.println("Part 1: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}