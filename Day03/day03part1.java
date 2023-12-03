package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day03part1 {
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
                    num = "";
                    for (int j = 0; j < row.length(); j++) {
                        if(Character.isDigit(row.charAt(j))){
                            num += row.charAt(j);
                            if(checkAdjacent(schema, i, j)){
                                found = true;
                            }
                        }else{
                            if(found){
                                sum += Integer.parseInt(num);
                                found = false;
                            }
                            num = "";
                        }
                    }
                }

                System.out.println("Part 1: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkAdjacent(String[] myschema, int i, int j){
        //Check up
        if(i-1 >= 0 && !Character.isDigit(myschema[i-1].charAt(j)) && myschema[i-1].charAt(j) != '.'){
            return true;
        }

        //Check down
        if(i+1 < myschema.length && !Character.isDigit(myschema[i+1].charAt(j)) && myschema[i+1].charAt(j) != '.'){
            return true;
        }        

        //Check left
        if(j-1 >= 0 && !Character.isDigit(myschema[i].charAt(j-1)) && myschema[i].charAt(j-1) != '.'){
            return true;
        }

        //Check right
        if(j+1 < myschema[i].length() && !Character.isDigit(myschema[i].charAt(j+1)) && myschema[i].charAt(j+1) != '.'){
            return true;
        }

        //Check top left
        if(j-1 >= 0 && i-1 >= 0 && !Character.isDigit(myschema[i-1].charAt(j-1)) && myschema[i-1].charAt(j-1) != '.'){
            return true;
        }

        //Check top right
        if(i-1 >= 0 && j+1 < myschema[i].length() && !Character.isDigit(myschema[i-1].charAt(j+1)) && myschema[i-1].charAt(j+1) != '.'){
            return true;
        }

        //Check bottom left
        if(i+1 < myschema.length && j-1 >= 0 && !Character.isDigit(myschema[i+1].charAt(j-1)) && myschema[i+1].charAt(j-1) != '.'){
            return true;
        }

        //Check bottom right
        if(i+1 < myschema.length && j+1 < myschema[i].length() && !Character.isDigit(myschema[i+1].charAt(j+1)) && myschema[i+1].charAt(j+1) != '.'){
            return true;
        }

        return false; 
    }
}