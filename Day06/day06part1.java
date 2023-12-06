package Day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day06part1 {
    public static void main(String[] args) {
        String filePath = "./Day06/day06.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String[] time = br.readLine().split(":")[1].trim().split("\\s+");
                String[] distance = br.readLine().split(":")[1].trim().split("\\s+");
                int raceTime;
                int raceDistRecord;
                int curDist;
                int raceWins = 0;
                int totalWins = 1;

                for(int i=0; i<time.length; i++){
                    raceTime = Integer.parseInt(time[i]);
                    raceDistRecord = Integer.parseInt(distance[i]);
                    for (int hold = 0; hold < raceTime; hold++) {
                        curDist = (raceTime - hold) * hold; 
                        if(curDist > raceDistRecord){
                            raceWins++;
                        }
                    }
                    totalWins *= raceWins;
                    raceWins = 0;
                }

                System.out.println("Part 1: " + totalWins);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
