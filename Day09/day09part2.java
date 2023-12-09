import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class day09part2 {
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
                Deque<Integer> firsts = new ArrayDeque<Integer>();

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

                    firsts.addFirst(oldList.get(0));

                    oldList.clear();
                    for(int ni : newList){
                        oldList.add(ni);
                    }

                    firstItem = oldList.get(0);
                    result = new HashSet<Integer>(oldList).size() == 1;
                    result = result && (firstItem == 0);
                    newList.clear();
                }

                for (Integer fs : firsts) {
                    sum = fs - sum;
                }
                fullSum+=sum;
                sum = 0;
            }

            System.out.println("Part 2: " + fullSum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
