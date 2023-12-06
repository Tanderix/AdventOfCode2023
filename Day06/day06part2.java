package Day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day06part2 {
    public static void main(String[] args) {
        String filePath = "./Day06/day06.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String[] time = br.readLine().split(":")[1].trim().split("\\s+");
                String[] distance = br.readLine().split(":")[1].trim().split("\\s+");

                String numBuilder = "";
                for (int i = 0; i < time.length; i++) {
                    numBuilder+= time[i];
                }
                long raceTime = Long.parseLong(numBuilder);

                numBuilder = "";
                for (int i = 0; i < time.length; i++) {
                    numBuilder+= distance[i];
                }
                long raceDistRecord = Long.parseLong(numBuilder);

                long curDist;
                int raceWins = 0;

                for (int hold = 0; hold < raceTime; hold++) {
                    curDist = (raceTime - hold) * hold; 
                    //System.out.println(curDist + ", record: " + raceDistRecord);
                    if(curDist > raceDistRecord){
                        raceWins++;
                    }
                }

                System.out.println("Part 2: " + raceWins);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
