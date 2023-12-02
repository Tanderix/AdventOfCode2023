package Day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class day02part1 {
    public static void main(String[] args) {
        String filePath = "./Day02/day02.txt";
        Map<String, Integer> bag = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14
        );

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                String[] sets;
                String[] games;
                String[] info;
                int id = 1;
                int IDsum = 0;
                boolean possible = true;

                while ((line = br.readLine()) != null) {
                    sets = line.split(":")[1].trim().split(";");
                    for (String set : sets) {
                        games = set.trim().split(",");
                        for (String game : games) {
                            info = game.trim().split(" ");
                            if(bag.get(info[1]) < Integer.parseInt(info[0])){
                                possible = false;
                            }
                        }
                    }
                    if(possible){
                        IDsum+=id;
                    }else{
                        possible = true;
                    }
                    id++;
                }

                System.out.println("Part 1: " + IDsum);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
