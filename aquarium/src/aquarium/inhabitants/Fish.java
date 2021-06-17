package aquarium.inhabitants;

import aquarium.Aquarium;
import aquarium.AquariumObject;
import aquarium.Cell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class Fish extends AquariumObject {
    protected int X;
    protected int Y;
    protected int cur_Age = 0;
    protected int cur_Hunger = 0;
    private boolean isAlive = true;
    private int currentPregnancyStage = 0;
    protected boolean isPregnant = false;
    protected final boolean isMale;
    protected boolean madeChildren = false;
    protected Fish(final Aquarium aquarium, Cell cell) {
        super(aquarium);
        this.X = cell.X;
        this.Y = cell.Y;
        this.isMale = new Random().nextBoolean();
    }

    public void moveToCell(final Cell cell) {
        this.X = cell.X;
        this.Y = cell.Y;
    }

    protected abstract Cell FindDaWay();

    protected abstract boolean eat(final Cell currentCell);
    protected abstract void moveSelf(final Cell to);

    private void killSelf() {
        this.isAlive = false;
        killSelfImpl();
    }
    protected abstract void killSelfImpl();

    @Override
    public void move() {
        if (!isAlive) {
            return;
        }
        if (!eat(aquarium.CellsArr[this.Y][this.X])) {
            moveSelf(FindDaWay());
        }
        handleAgeAndHunger();
        handlePregnancy();
    }

    private void handleAgeAndHunger() {
        cur_Age += 1;
        cur_Hunger += 1;
        if (cur_Age == aquarium.settings.fish_maxAge || cur_Hunger == aquarium.settings.fish_maxHunger) {
            killSelf();
        }
    }
    private void handlePregnancy() {
        if (isMale) {
            for (int i = Math.max(0, this.Y - 1); i <= Math.min(aquarium.settings.height - 1, this.Y + 1); i++) {
                for (int j = Math.max(0, this.X - 1); j <= Math.min(aquarium.settings.height - 1, this.X + 1); j++) {
                    if (!(i == this.Y && j == this.X) && makePregnantImpl(aquarium.CellsArr[i][j])) {
                        return;
                    }
                }
            }
            return;
        }
        if (this.currentPregnancyStage == aquarium.settings.fish_pregnancyLength && isPregnant) {
            spawnChildren();
        }
        if (this.isPregnant) {
            currentPregnancyStage += 1;
        }
    }
    abstract protected boolean makePregnantImpl(final Cell cell);

    private void spawnChildren() {
        for (Cell cell: findEmptyCells(aquarium.settings.fish_amountOfChildren)) {
            spawnChildrenImpl(cell);
        }
        isPregnant = false;
    }

    protected abstract void spawnChildrenImpl(final Cell cell);

    private ArrayList<Cell> findEmptyCells(int limit) {
        int cnt = 0;
        ArrayList<Cell> res = new ArrayList<>(limit);
        for (Cell[] line: aquarium.CellsArr) {
            for (Cell cell: line) {
                if (cell.isEmpty()) {
                    res.add(cell);
                    cnt += 1;
                    if (cnt == limit) {
                        Collections.shuffle(res);
                        return res;
                    }
                }
            }
        }
        Collections.shuffle(res);
        return res;
    }

    protected String fishInfo() {
        StringBuilder sb = new StringBuilder();

        if (isMale) {
            sb.append("M");
        }
        else {
            sb.append("F");
        }
        if (cur_Age >= aquarium.settings.fish_oldStartAge) {
            sb.append("3");
        } else if (cur_Age >= aquarium.settings.fish_matureStartAge) {
            sb.append("2");
        } else {
            sb.append("1");
        }
        if (!isMale) {
            if (isPregnant) {
                sb.append("+");
            } else {
                sb.append("-");
            }
        }
        return sb.toString();
    }
}
