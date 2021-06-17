package aquarium;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AquariumSetUp {
    public int width;
    public int height;
    public int num_of_Stones;
    public int num_of_Weed;
    public int weed_maxHungerBonus;
    public int num_of_fishWeedEnjoyer;
    public int num_of_fishPredator;
    public int fish_maxAge;
    public int fish_maxHunger;
    public int fish_pregnancyLength;
    public int fish_matureStartAge;
    public int fish_amountOfChildren;
    public int fish_oldStartAge;
    public AquariumSetUp(String settings_file_path) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(settings_file_path));
            String line = reader.readLine();

            while (line != null) {
                String[] splitLine = line.split("=");
                String property = splitLine[0];
                String value = splitLine[1];
                switch (property) {
                    case "width" -> this.width = Integer.parseInt(value);
                    case "height" -> this.height = Integer.parseInt(value);
                    case "num_of_stones" -> this.num_of_Stones = Integer.parseInt(value);
                    case "num_of_weed" -> this.num_of_Weed = Integer.parseInt(value);
                    case "num_of_fishWeedEnjoyer" -> this.num_of_fishWeedEnjoyer = Integer.parseInt(value);
                    case "num_of_fishPredator" -> this.num_of_fishPredator = Integer.parseInt(value);
                    case "fish_maxAge" -> this.fish_maxAge = Integer.parseInt(value);
                    case "fish_maxHunger" -> this.fish_maxHunger = Integer.parseInt(value);
                    case "weed_maxHungerBonus" -> this.weed_maxHungerBonus = Integer.parseInt(value);
                    case "fish_pregnancyLength" -> this.fish_pregnancyLength = Integer.parseInt(value);
                    case "fish_matureStartAge" -> this.fish_matureStartAge = Integer.parseInt(value);
                    case "fish_amountOfChildren" -> this.fish_amountOfChildren = Integer.parseInt(value);
                    case "fish_oldStartAge" -> this.fish_oldStartAge = Integer.parseInt(value);
                    default -> throw new Exception("unknown settings parameter: " + property);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
