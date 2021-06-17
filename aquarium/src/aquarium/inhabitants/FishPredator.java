package aquarium.inhabitants;

import aquarium.Aquarium;
import aquarium.Cell;

import java.util.ArrayList;
import java.util.Random;

public class FishPredator extends Fish {
    public FishPredator(final Aquarium aquarium, final Cell cell) {
        super(aquarium, cell);
    }

    @Override
    protected boolean eat(final Cell currentCell) {
        if (currentCell.fishWeedEnjoyer != null) {
            cur_Hunger = Math.max(0, cur_Hunger - currentCell.fishWeedEnjoyer.cur_Hunger/3);
            currentCell.fishWeedEnjoyer = null;
            return true;
        }
        return false;
    }

    @Override
    public Cell FindDaWay() {
        ArrayList<Cell> possibleCells = new ArrayList<>();
        for (int i = Math.max(this.Y - 1, 0); i <= Math.min(this.Y + 1, aquarium.CellsArr.length - 1); i++) {
            for (int j = Math.max(this.X - 1, 0); j <= Math.min(this.X + 1, aquarium.CellsArr[0].length - 1); j++)  {
                if (aquarium.CellsArr[i][j].fishPredator == null) {
                    if (aquarium.CellsArr[i][j].fishWeedEnjoyer != null) {
                        return aquarium.CellsArr[i][j];
                    }
                    if (aquarium.CellsArr[i][j].isEmpty() || aquarium.CellsArr[i][j].weed != null) {
                        possibleCells.add(aquarium.CellsArr[i][j]);
                    }
                }
            }
        }
        if (possibleCells.size() == 0) {
            return aquarium.CellsArr[this.Y][this.X];
        }
        return possibleCells.get(new Random().nextInt(possibleCells.size()));
    }

    @Override
    protected void moveSelf(Cell to) {
        aquarium.CellsArr[this.Y][this.X].fishPredator = null;
        aquarium.CellsArr[to.Y][to.X].fishPredator = this;
        moveToCell(aquarium.CellsArr[to.Y][to.X]);
    }

    @Override
    protected void killSelfImpl() {
        aquarium.CellsArr[this.Y][this.X].fishPredator = null;
    }

    @Override
    protected void spawnChildrenImpl(final Cell cell) {
        aquarium.FishPredatorArr.add(new FishPredator(aquarium, cell));
        cell.fishPredator = aquarium.FishPredatorArr.get(aquarium.FishPredatorArr.size() - 1);
    }

    @Override
    protected boolean makePregnantImpl(Cell cell) {
        if (isMale && !madeChildren &&
                cell.fishPredator != null && !cell.fishPredator.madeChildren &&
                !cell.fishPredator.isMale &&
                aquarium.settings.fish_matureStartAge <= cell.fishPredator.cur_Age &&
                cell.fishPredator.cur_Age <= aquarium.settings.fish_oldStartAge &&
                !cell.fishPredator.isPregnant) {
            madeChildren = true;
            cell.fishPredator.madeChildren = true;
            cell.fishPredator.isPregnant = true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "P" + fishInfo();
    }
}
