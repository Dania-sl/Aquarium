package aquarium.obstacles;

import aquarium.Aquarium;
import aquarium.AquariumObject;
import aquarium.Cell;

public abstract class AbstractObstacle extends AquariumObject {
    protected final Cell cell;
    public AbstractObstacle(final Aquarium aquarium, final Cell cell) {
        super(aquarium);
        this.cell = cell;
    }
}
