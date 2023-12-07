import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class day07part1 implements Comparator<MyRank>{
    public static HashMap<Character, Integer> strength = new HashMap<>();
    public static void main(String[] args) {
        String filePath = "./Day07/day07.txt";
        String line;
        HashMap<String, Integer> handBid = new HashMap<>();
        HashMap<Character, Integer> cards = new HashMap<>();
        ArrayList<ArrayList<Integer>> types = new ArrayList<>();
            types.add(new ArrayList<>(Arrays.asList(5)));
            types.add(new ArrayList<>(Arrays.asList(4,1)));
            types.add(new ArrayList<>(Arrays.asList(3,2)));
            types.add(new ArrayList<>(Arrays.asList(3,1,1)));
            types.add(new ArrayList<>(Arrays.asList(2,2,1)));
            types.add(new ArrayList<>(Arrays.asList(2,1,1,1)));
            types.add(new ArrayList<>(Arrays.asList(1,1,1,1,1)));

        HashMap<ArrayList<Integer>, Integer> ranks = new HashMap<>();
            ranks.put(new ArrayList<>(Arrays.asList(5)), 7);
            ranks.put(new ArrayList<>(Arrays.asList(4,1)), 6);
            ranks.put(new ArrayList<>(Arrays.asList(3,2)), 5);
            ranks.put(new ArrayList<>(Arrays.asList(3,1,1)), 4);
            ranks.put(new ArrayList<>(Arrays.asList(2,2,1)), 3);
            ranks.put(new ArrayList<>(Arrays.asList(2,1,1,1)), 2);
            ranks.put(new ArrayList<>(Arrays.asList(1,1,1,1,1)), 1);

            strength.put('A', 14);
            strength.put('K', 13);
            strength.put('Q', 12);
            strength.put('J', 11);
            strength.put('T', 10);
            strength.put('9', 9);
            strength.put('8', 8);
            strength.put('7', 7);
            strength.put('6', 6);
            strength.put('5', 5);
            strength.put('4', 4);
            strength.put('3', 3);
            strength.put('2', 2);
           
        ArrayList<MyRank> handRank = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                cards = new HashMap<>();
                //Part 1: parsing
                String hand = line.split(" ")[0];
                int bid = Integer.parseInt(line.split(" ")[1]);
                handBid.put(hand, bid);

                //Part 2: type checking
                for(int i=0; i < hand.length(); i++){
                    if(cards.get(hand.charAt(i)) == null){
                        cards.put(hand.charAt(i), 1);
                    }else{
                        cards.put(hand.charAt(i), cards.get(hand.charAt(i))+1);
                    }
                }

                //Part 3: sorting
                ArrayList<Integer> cardSet = new ArrayList<>(cards.values());
                Collections.sort(cardSet);
                Collections.reverse(cardSet);

                for(ArrayList<Integer> type : types){
                    if(cardSet.equals(type)){
                        handRank.add(new MyRank(hand, ranks.get(cardSet)));
                    }
                }
            }


            Collections.sort(handRank, (r1, r2) ->{ if(r1.rank < r2.rank){
                                                        return -1;
                                                    }else if(r1.rank > r2.rank){
                                                        return 1;
                                                    }else{
                                                        int i = 0;
                                                        while(i<r1.hand.length()){
                                                            if(strength.get(r1.hand.charAt(i)) < strength.get(r2.hand.charAt(i))){
                                                                return -1;
                                                            }else if(strength.get(r1.hand.charAt(i)) > strength.get(r2.hand.charAt(i))){
                                                                return 1;
                                                            }
                                                            i++;
                                                        }
                                                        return 0;
                                                    }
                                                }
                            );

            int sum = 0;
            for(int i=0; i<handRank.size(); i++){
                MyRank e = handRank.get(i);
                int bid = handBid.get(e.hand);
                sum += bid * (i+1);
            }
            System.out.println("Part 1: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int compare(MyRank r1, MyRank r2) {
        if(r1.rank < r1.rank){
            return -1;
        }else if(r1.rank > r2.rank){
            return 1;
        }else{
            if(strength.get(r1.hand.charAt(0)) < strength.get(r2.hand.charAt(0))){
                return -1;
            }else if(strength.get(r1.hand.charAt(0)) > strength.get(r2.hand.charAt(0))){
                return 1;
            }else{
                return 0;
            }
        }
    }
}

class MyRank{
    String hand;
    int rank;

    MyRank(String h, int r){
        this.hand = h;
        this.rank = r;
    }
}

