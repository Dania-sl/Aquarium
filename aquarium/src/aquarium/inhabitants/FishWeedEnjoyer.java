package aquarium.inhabitants;

import aquarium.Aquarium;
import aquarium.Cell;

import java.util.ArrayList;
import java.util.Random;

public class FishWeedEnjoyer extends Fish {
    public FishWeedEnjoyer(final Aquarium aquarium, final Cell cell) {
        super(aquarium, cell);
    }

    @Override
    protected boolean eat(final Cell currentCell) {
        if (currentCell.weed != null) {
            cur_Hunger = Math.max(0, cur_Hunger - currentCell.weed.current_HungerBonus);
            currentCell.weed = null;
            return true;
        }
        return false;
    }

    @Override
    public Cell FindDaWay() {
        ArrayList<Cell> possibleCells = new ArrayList<>();
        for (int i = Math.max(this.Y - 1, 0); i <= Math.min(this.Y + 1, aquarium.CellsArr.length - 1); i++) {
            for (int j = Math.max(this.X - 1, 0); j <= Math.min(this.X + 1, aquarium.CellsArr[0].length - 1); j++) {
                if (aquarium.CellsArr[i][j].fishWeedEnjoyer == null) {
                    if (aquarium.CellsArr[i][j].isEmpty()) {
                        possibleCells.add(aquarium.CellsArr[i][j]);
                    } else if (aquarium.CellsArr[i][j].weed != null) {
                        return aquarium.CellsArr[i][j];
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
    protected void moveSelf(final Cell to) {
        aquarium.CellsArr[this.Y][this.X].fishWeedEnjoyer = null;
        aquarium.CellsArr[to.Y][to.X].fishWeedEnjoyer = this;
        moveToCell(aquarium.CellsArr[to.Y][to.X]);
    }

    @Override
    protected void killSelfImpl() {
        aquarium.CellsArr[this.Y][this.X].fishWeedEnjoyer = null;
    }

    @Override
    protected void spawnChildrenImpl(final Cell cell) {
        aquarium.FishWeedEnjoyerArr.add(new FishWeedEnjoyer(aquarium, cell));
        cell.fishWeedEnjoyer = aquarium.FishWeedEnjoyerArr.get(aquarium.FishWeedEnjoyerArr.size() - 1);
    }

    @Override
    protected boolean makePregnantImpl(Cell cell) {
        if (isMale && !madeChildren &&
                cell.fishWeedEnjoyer != null && !cell.fishWeedEnjoyer.madeChildren &&
                !cell.fishWeedEnjoyer.isMale &&
                aquarium.settings.fish_matureStartAge <= cell.fishWeedEnjoyer.cur_Age &&
                cell.fishWeedEnjoyer.cur_Age <= aquarium.settings.fish_oldStartAge &&
                !cell.fishWeedEnjoyer.isPregnant) {
            madeChildren = true;
            cell.fishWeedEnjoyer.madeChildren = true;
            cell.fishWeedEnjoyer.isPregnant = true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "H" + fishInfo();
    }
}
