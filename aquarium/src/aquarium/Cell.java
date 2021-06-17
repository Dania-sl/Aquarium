package aquarium;

import aquarium.inhabitants.Fish;
import aquarium.inhabitants.FishPredator;
import aquarium.inhabitants.FishWeedEnjoyer;
import aquarium.obstacles.Stone;
import aquarium.obstacles.Weed;

public class Cell {
    public final int X;
    public final int Y;
    public Stone stone;
    public Weed weed;
    public FishWeedEnjoyer fishWeedEnjoyer;
    public FishPredator fishPredator;
    //public AquariumObject aquariumObject;
    public Cell(final int X, final int Y, Stone stone, Weed weed, FishWeedEnjoyer fishE, FishPredator fishP) {
        this.X = X;
        this.Y = Y;
        this.stone = stone;
        this.weed = weed;
        this.fishWeedEnjoyer = fishE;
        this.fishPredator = fishP;
    }

    public boolean isEmpty() {
        return stone == null && weed == null && fishWeedEnjoyer == null && fishPredator == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (stone != null) {
            sb.append(stone).append(" ");
        }
        if (weed != null) {
            sb.append(weed).append(" ");
        }
        if (fishWeedEnjoyer != null) {
            sb.append(fishWeedEnjoyer).append(" ");
        }
        if (fishPredator != null) {
            sb.append(fishPredator);
        }
        return sb.toString();
    }
}
