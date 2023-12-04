import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class day04part1 {
    public static void main(String[] args) {
        String filePath = "./Day04/day04.txt";
        HashSet<String> winningNums = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                String input;
                String[] winning;
                String[] myNums;
                int pointSum = 0;

                //Parse lines
                while ((line = br.readLine()) != null) {
                    int curPoints = 0;
                    //Split card number from data
                    input = line.split(":")[1];
                    winning = input.split("\\|")[0].trim().split("\\s+");
                    myNums = input.split("\\|")[1].trim().split("\\s+");
                    for (String w : winning) {
                        winningNums.add(w.trim());
                    }

                    for(String n : myNums){
                        if(winningNums.contains(n)){
                            if(curPoints == 0){
                                curPoints = 1;
                            }else{
                                curPoints *= 2;
                            }
                        }
                    }
                    pointSum += curPoints;
                    curPoints = 0;
                    winningNums.clear();
                }

                System.out.println("Part 1: " + pointSum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
