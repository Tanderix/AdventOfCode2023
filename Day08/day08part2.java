package Day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class day08part2 {
    public static void main(String[] args) {
        String filePath = "./Day08/day08.txt";
        String line;
        HashMap<String, MyDirs> dirMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String directions = br.readLine();
            br.readLine();
            boolean first = true;

            //Build map
            while ((line = br.readLine()) != null) {
                String node = line.split(" = ")[0].trim();
                String lr = line.split(" = ")[1].replaceAll("\\(|\\)", "").trim();
                if(first){
                    first = false;
                }
                dirMap.put(node, new MyDirs(lr.split(", ")[0], lr.split(", ")[1]));
            }

            ArrayList<String> curNodes = new ArrayList<>();
            ArrayList<Integer> cycleSteps = new ArrayList<>();
            
            for(String k : dirMap.keySet()){
                if(k.charAt(2) == 'A'){
                    curNodes.add(k);
                    cycleSteps.add(-1);
                }
            }
            
            boolean finished = false;
            int ended = 0; 
            int jumps = 0;
            int index = 0;
            while(!finished){
                for (String curNode : curNodes) {
                    if(curNode.endsWith("Z")){
                        if(cycleSteps.get(curNodes.indexOf((curNode))) == -1){
                            cycleSteps.set(curNodes.indexOf(curNode), jumps);
                        }
                        ended++;
                    }
                }
                boolean fine = true;
                for(int i=0; i<cycleSteps.size(); i++){
                    if(cycleSteps.get(i) == -1 ){
                        fine = false;
                        break;
                    }
                }

                if(fine){
                    break;
                }

                if(ended == curNodes.size()){
                    finished = true;
                    break;
                }else{
                    ended = 0;
                }

                //Restart LR input
                if(index == directions.length()){
                    index = 0;
                }

                for (int i = 0; i < curNodes.size(); i++) {
                    String curNode = curNodes.get(i);
                    if(directions.charAt(index) == 'L'){
                        curNodes.set(i, dirMap.get(curNode).left);
                    }else if(directions.charAt(index) == 'R'){
                        curNodes.set(i, dirMap.get(curNode).right);
                    }else{
                        System.out.println("Errore!");
                    }
                }
                jumps++;
                index++;
            }
                
            System.out.println("Part 2: " + lcm(cycleSteps));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long gcd(long a, long b) { 
        if (a == 0) 
            return b;  
        return gcd(b % a, a);  
    } 

    public static long lcm(ArrayList<Integer> list){
        long lcm = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            lcm =  (lcm / gcd(lcm, list.get(i))) * list.get(i); 
        }
        return lcm;
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
