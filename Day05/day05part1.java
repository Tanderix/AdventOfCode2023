import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day05part1 {
    public static HashMap<Long, MyKey> seed_to_soil = new HashMap<>();
    public static HashMap<Long, MyKey> soil_to_fertilizer = new HashMap<>();
    public static HashMap<Long, MyKey> fertilizer_to_water = new HashMap<>();
    public static HashMap<Long, MyKey> water_to_light = new HashMap<>();
    public static HashMap<Long, MyKey> light_to_temperature = new HashMap<>();
    public static HashMap<Long, MyKey> temperature_to_humidity = new HashMap<>();
    public static HashMap<Long, MyKey> humidity_to_location = new HashMap<>();
    public static ArrayList<HashMap<Long, MyKey>> mapList = new ArrayList<>();
    public static void main(String[] args) {
        String filePath = "./Day05/day05.txt";
        String[] seeds = {};

        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                long min = Long.MAX_VALUE;
                //First line -- Seeds
                line = br.readLine();
                while((!line.isEmpty()) && (line != null)){
                    //System.out.println(line);
                    seeds = line.split(":")[1].trim().split(" ");
                    line = br.readLine();
                }

                fillMap(seed_to_soil, "seed-", br);
                fillMap(soil_to_fertilizer, "soil-", br);
                fillMap(fertilizer_to_water, "fert", br);
                fillMap(water_to_light, "water", br);
                fillMap(light_to_temperature, "light", br);
                fillMap(temperature_to_humidity, "temp", br);
                fillMap(humidity_to_location, "hum", br);

                mapList.add(seed_to_soil);
                mapList.add(soil_to_fertilizer);
                mapList.add(fertilizer_to_water);
                mapList.add(water_to_light);
                mapList.add(light_to_temperature);
                mapList.add(temperature_to_humidity);
                mapList.add(humidity_to_location);

                for (String seed : seeds) {
                    long seedNum = Long.parseLong(seed);
                    long curLoc = seedNum;
                    for (HashMap<Long, MyKey> curMap : mapList) {
                        for (Map.Entry<Long, MyKey> entry : curMap.entrySet()) {
                            long source = entry.getKey();
                            long destination = entry.getValue().dest;
                            long step = entry.getValue().step;

                            if(curLoc >= source && curLoc <= source + step){
                                long toAdd = curLoc - source;
                                curLoc = destination + toAdd;
                                break;
                            }
                        }
                        //System.out.println("Partial step: " + curLoc);
                    }
                    if(curLoc < min){ min = curLoc;}
                    //System.out.println("Location: " + curLoc);
                }

                /*printMap("seed_to_soil", seed_to_soil);
                printMap("soil-to-fertilizer", soil_to_fertilizer);
                printMap("fertilizer-to-water", fertilizer_to_water);
                printMap("water-to-light", water_to_light);
                printMap("light-to-temperature", light_to_temperature);
                printMap("temperature-to-humidity", temperature_to_humidity);
                printMap("humidity-to-location", humidity_to_location);*/

                System.out.println("Part 1: " + min);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fillMap(HashMap<Long, MyKey> m, String prefix, BufferedReader br){
        try{
            String line = br.readLine();
            while((line != null) && (!line.isEmpty())){
                if(line.startsWith(prefix)){ line = br.readLine(); continue; };
                String[] data = line.split(" ");
                MyKey mk = new MyKey(-1, -1);
                mk.dest = Long.parseLong(data[0]);
                mk.step = Long.parseLong(data[2]);
                m.put(Long.parseLong(data[1]), mk);
                line = br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void printMap(String name, HashMap<Long, MyKey> m){
        System.out.println("\n" + name + " - ");
        for (Map.Entry<Long, MyKey> entry : m.entrySet()) {
            System.out.println("Src: " + entry.getKey() + ", Dst: " + entry.getValue().dest + ", Stp: " + entry.getValue().step);
        }
    }
}

class MyKey{
    public long dest;
    public long step;

    public MyKey(long d, long s){
        this.dest = d;
        this.step = s;
    }
}

