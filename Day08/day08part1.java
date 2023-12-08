package Day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class day08part1 {
public static void main(String[] args) {
        String filePath = "./Day08/day08.txt";
        String line;
        HashMap<String, MyDirs> dirMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String directions = br.readLine();
            br.readLine();
            boolean first = true;
            String curNode = "AAA";

            //Build map
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String node = line.split(" = ")[0].trim();
                String lr = line.split(" = ")[1].replaceAll("\\(|\\)", "").trim();
                if(first){
                    first = false;
                }
                dirMap.put(node, new MyDirs(lr.split(", ")[0], lr.split(", ")[1]));
            }

            boolean finished = false;
            int jumps = 0;
            int index = 0;
            while(!finished){
                System.out.println(curNode);
                if(curNode.equals("ZZZ")){
                    finished = true;
                    break;
                }

                if(index == directions.length()){
                    index = 0;
                }

                if(directions.charAt(index) == 'L'){
                    curNode = dirMap.get(curNode).left;
                }else if(directions.charAt(index) == 'R'){
                    curNode = dirMap.get(curNode).right;
                }else{
                    System.out.println("Errore!");
                }
                jumps++;
                index++;
            }

            System.out.println("Part 1: " + jumps);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class MyDirs{
    String left;
    String right;

    public MyDirs(String l, String r){
        this.left = l;
        this.right = r;
    }
}
