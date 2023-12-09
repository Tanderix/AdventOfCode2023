import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class day09part1 {
    public static void main(String[] args) {
        String filePath = "./Day09/day09.txt";
        String line;
        int firstItem;
        int sum = 0;
        int fullSum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                ArrayList<Integer> oldList = new ArrayList<>();
                ArrayList<Integer> newList = new ArrayList<>();
                Stack<Integer> penultimates = new Stack<>();

                String[] lineSplit = line.split(" ");
                for(String s : lineSplit){
                    oldList.add(Integer.parseInt(s));
                }

                firstItem = oldList.get(0);
                boolean result = new HashSet<Integer>(oldList).size() == 1;
                result = result && (firstItem == 0);
                while(!result){
                    for (int i = 0; i < oldList.size()-1; i++) {
                        newList.add(oldList.get(i+1) - oldList.get(i)); 
                    }

                    penultimates.push(oldList.get(oldList.size()-1));

                    oldList.clear();
                    for(int ni : newList){
                        oldList.add(ni);
                    }

                    firstItem = oldList.get(0);
                    result = new HashSet<Integer>(oldList).size() == 1;
                    result = result && (firstItem == 0);
                    newList.clear();
                }
                for (Integer pen : penultimates) {
                    sum += pen;
                }
                fullSum+=sum;
                sum = 0;
            }

            System.out.println("Part 1: " + fullSum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
