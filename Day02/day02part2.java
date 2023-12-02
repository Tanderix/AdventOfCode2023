package Day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class day02part2 {
    public static void main(String[] args) {
        String filePath = "./Day02/day02.txt";

        Map<String, Integer> min = new HashMap<>();
        
        min.put("red", Integer.MIN_VALUE);
        min.put("green", Integer.MIN_VALUE);
        min.put("blue", Integer.MIN_VALUE);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                String[] sets;
                String[] games;
                String[] info;
                int powerSum = 0;

                while ((line = br.readLine()) != null) {
                    sets = line.split(":")[1].trim().split(";");
                    for (String set : sets) {
                        games = set.trim().split(",");
                        for (String game : games) {
                            info = game.trim().split(" ");
                            if(min.get(info[1]) < Integer.parseInt(info[0])){
                                min.put(info[1], Integer.parseInt(info[0]));
                            }
                        }
                    }
                    powerSum += (min.get("red") * min.get("green") * min.get("blue"));
                    min.replaceAll((key, value) -> 0);
                }

                System.out.println("Part 2: " + powerSum);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
}
