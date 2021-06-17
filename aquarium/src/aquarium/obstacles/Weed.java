package aquarium.obstacles;

import aquarium.Aquarium;
import aquarium.Cell;

public class Weed extends AbstractObstacle{
    public int current_HungerBonus = 0;
    public Weed(final Aquarium aquarium, final Cell cell) {
        super(aquarium, cell);
    }

    @Override
    public void move() {
        current_HungerBonus = (current_HungerBonus + 1) % aquarium.settings.weed_maxHungerBonus;
    }

    @Override
    public String toString() {
        return "G";
    }
}
