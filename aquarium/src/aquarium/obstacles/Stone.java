package aquarium.obstacles;

import aquarium.Aquarium;
import aquarium.AquariumSetUp;
import aquarium.Cell;

public class Stone extends AbstractObstacle{
    public Stone(final Aquarium aquarium, final Cell cell) {
        super(aquarium, cell);
    }

    @Override
    public void move() {}

    @Override
    public String toString() {
        return "X";
    }
}
