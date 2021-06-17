package aquarium;

public abstract class AquariumObject {
    protected final Aquarium aquarium;
    public AquariumObject(final Aquarium aquarium) {
        this.aquarium = aquarium;
    }
    abstract public void move();
}
