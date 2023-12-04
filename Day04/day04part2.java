import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class day04part2 {
    public static void main(String[] args) {
        String filePath = "./Day04/day04.txt";
        HashSet<String> winningNums = new HashSet<>();
        HashMap<Integer, Integer> wins = new HashMap<>();
        HashMap<Integer, Integer> cardsMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                int card;
                String input;
                String[] winning;
                String[] myNums;
                int cardSum = 0;
                int numCards = 0;

                //Parse lines
                while ((line = br.readLine()) != null) {
                    numCards++;
                    //Split card number from data
                    card = Integer.parseInt(line.split(":")[0].trim().split("\\s+")[1]);
                    input = line.split(":")[1];
                    winning = input.split("\\|")[0].trim().split("\\s+");
                    myNums = input.split("\\|")[1].trim().split("\\s+");
                    for (String w : winning) {
                        winningNums.add(w.trim());
                    }

                    for(String n : myNums){
                        if(winningNums.contains(n)){
                            if(!wins.containsKey(card)){
                                wins.put(card, 1);
                            }else{
                                wins.put(card, wins.get(card)+1);
                            }
                        }
                    }
                    winningNums.clear();
                }
                cardSum += numCards;
                for (int index = 0; index < numCards; index++) {
                    cardsMap.put(index+1, 1);
                }

                for (int i = 1; i <= numCards; i++) {
                    int n = cardsMap.get(i);
                    Integer winsPerCard = wins.get(i);
                    if(winsPerCard==null){ winsPerCard = 0;}
                    for(int k = 0; k<n; k++){
                        for(int j = i+1; j<=i+winsPerCard; j++){
                            cardsMap.put(j, cardsMap.get(j)+1);
                        }
                        cardSum += winsPerCard;
                    }
                }
                System.out.println("Part 2: " + cardSum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
