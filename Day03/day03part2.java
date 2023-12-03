package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class day03part2 {
        public static void main(String[] args) {
        String filePath = "./Day03/day03.txt";
        int SIZE = 140;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                String[] schema = new String[SIZE];
                int index = 0;
                int sum = 0;
                String num = "";
                boolean found = false;
                boolean gear = false;
                int gearRatio = 0;
                int[] result;
                int curI = -1;
                int curJ = -1;
                boolean toAdd = false;
                boolean first = false;
                boolean second = false;
                String mykey = "";
                HashMap<String, MyValue> gears = new HashMap<>();

                while ((line = br.readLine()) != null) {
                    schema[index] = line;
                    index++; 
                }

                //Search number
                for(int i = 0; i < schema.length; i++){
                    String row = schema[i];
                    if(found){
                        sum += Integer.parseInt(num);
                        found = false;
                    }
                    if(gear){
                        int curNum = gears.get(mykey).value;
                        if(curNum == 0){
                            gears.put(mykey, new MyValue(Integer.parseInt(num), false));
                        }else{
                            gears.put(mykey, new MyValue(Integer.parseInt(num) * curNum, true));
                        }
                        gear = false;
                    }
                    num = "";
                    for (int j = 0; j < row.length(); j++) {
                        if(Character.isDigit(row.charAt(j))){
                            num += row.charAt(j);
                            result = checkAdjacent(schema, i, j);
                            if(result[1] == 1){
                                mykey = result[2] + " " + result[3];
                                if(gears.get(mykey) == null){
                                    gears.put(mykey, new MyValue(0, false));
                                }
                                gear = true;
                            }
                        }else{
                            if(gear){
                                int curNum = gears.get(mykey).value;
                                if(curNum == 0){
                                    gears.put(mykey, new MyValue(Integer.parseInt(num), false));
                                }else{
                                    gears.put(mykey, new MyValue(Integer.parseInt(num) * curNum, true));
                                }
                                gear = false;
                            }
                            num = "";
                        }
                    }
                }
                for (Map.Entry<String, MyValue> g : gears.entrySet()) {
                    MyValue aux = g.getValue();
                    if(aux.full){
                        sum += g.getValue().value;
                    }
                }

                System.out.println("Part 2: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] checkAdjacent(String[] myschema, int i, int j){
        int[] res = {0, 0, -1, -1};

        //Check up
        if(i-1 >= 0 && !Character.isDigit(myschema[i-1].charAt(j)) && myschema[i-1].charAt(j) != '.'){
            res[0] = 1;
            if(myschema[i-1].charAt(j) == '*'){
                res[1] = 1;
                res[2] = i-1;
                res[3] = j;
            }
            return res;
        }

        //Check down
        if(i+1 < myschema.length && !Character.isDigit(myschema[i+1].charAt(j)) && myschema[i+1].charAt(j) != '.'){
            res[0] = 1;
            if(myschema[i+1].charAt(j) == '*'){
                res[1] = 1;
                res[2] = i+1;
                res[3] = j;
            }
            return res;
        }        

        //Check left
        if(j-1 >= 0 && !Character.isDigit(myschema[i].charAt(j-1)) && myschema[i].charAt(j-1) != '.'){
            res[0] = 1;
            if(myschema[i].charAt(j-1) == '*'){
                res[1] = 1;
                res[2] = i;
                res[3] = j-1;
            }
            return res;
        }

        //Check right
        if(j+1 < myschema[i].length() && !Character.isDigit(myschema[i].charAt(j+1)) && myschema[i].charAt(j+1) != '.'){
            res[0] = 1;
            if(myschema[i].charAt(j+1) == '*'){
                res[1] = 1;
                res[2] = i;
                res[3] = j+1;
            }
            return res;
        }

        //Check top left
        if(j-1 >= 0 && i-1 >= 0 && !Character.isDigit(myschema[i-1].charAt(j-1)) && myschema[i-1].charAt(j-1) != '.'){
            res[0] = 1;
            if(myschema[i-1].charAt(j-1) == '*'){
                res[1] = 1;
                res[2] = i-1;
                res[3] = j-1;
            }
            return res;
        }

        //Check top right
        if(i-1 >= 0 && j+1 < myschema[i].length() && !Character.isDigit(myschema[i-1].charAt(j+1)) && myschema[i-1].charAt(j+1) != '.'){
            res[0] = 1;
            if(myschema[i-1].charAt(j+1) == '*'){
                res[1] = 1;
                res[2] = i-1;
                res[3] = j+1;
            }
            return res;
        }

        //Check bottom left
        if(i+1 < myschema.length && j-1 >= 0 && !Character.isDigit(myschema[i+1].charAt(j-1)) && myschema[i+1].charAt(j-1) != '.'){
            res[0] = 1;
            if(myschema[i+1].charAt(j-1) == '*'){
                res[1] = 1;
                res[2] = i+1;
                res[3] = j-1;
            }
            return res;
        }

        //Check bottom right
        if(i+1 < myschema.length && j+1 < myschema[i].length() && !Character.isDigit(myschema[i+1].charAt(j+1)) && myschema[i+1].charAt(j+1) != '.'){
            res[0] = 1;
            if(myschema[i+1].charAt(j+1) == '*'){
                res[1] = 1;
                res[2] = i+1;
                res[3] = j+1;
            }
            return res;
        }
        return res; 
    }
    
}

class MyValue{
    public int value = -1;
    public boolean full = false;

    public MyValue(int v, boolean f){
        this.value = v;
        this.full = f;
    }
}