package aquarium;

import aquarium.inhabitants.*;
import aquarium.obstacles.Stone;
import aquarium.obstacles.Weed;

import java.util.ArrayList;
import java.util.Collections;

public class Aquarium {
    public final Cell[][] CellsArr;
    public final ArrayList<Weed> WeedArr;
    public final ArrayList<FishWeedEnjoyer> FishWeedEnjoyerArr;
    public final ArrayList<FishPredator> FishPredatorArr;
    public final AquariumSetUp settings;
    public Aquarium(final String settings_path) throws Exception{
        this.settings = new AquariumSetUp(settings_path);
        this.CellsArr = new Cell[settings.height][settings.width];
        this.FishWeedEnjoyerArr = new ArrayList<>(settings.num_of_fishWeedEnjoyer);
        this.FishPredatorArr = new ArrayList<>(settings.num_of_fishPredator);
        this.WeedArr = new ArrayList<>(settings.num_of_Weed);
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = 0; i < settings.width; i++) {
            for (int j = 0; j < settings.height; j++) {
                cells.add(new Cell(i, j, null, null, null, null));
            }
        }
        Collections.shuffle(cells);
        int pos = 0;
        for (int i = 0; i < settings.num_of_Stones; i++) {
            cells.get(pos).stone = new Stone(this, cells.get(pos));
            pos += 1;
        }
        for (int i = 0; i < settings.num_of_Weed; i++) {
            WeedArr.add(new Weed(this, cells.get(pos)));
            cells.get(pos).weed = WeedArr.get(i);
            pos += 1;
        }
        for (int i = 0; i < settings.num_of_fishWeedEnjoyer; i++) {
            FishWeedEnjoyerArr.add(new FishWeedEnjoyer(this, cells.get(pos)));
            cells.get(pos).fishWeedEnjoyer = FishWeedEnjoyerArr.get(i);
            pos += 1;
        }
        for (int i = 0; i < settings.num_of_fishPredator; i++) {
            FishPredatorArr.add(new FishPredator(this, cells.get(pos)));
            cells.get(pos).fishPredator = FishPredatorArr.get(i);
            pos += 1;
        }
        for (Cell cell: cells) {
            CellsArr[cell.Y][cell.X] = cell;
        }
    }

    public void printCurrentState() {
        for (Cell[] line: CellsArr) {
            System.out.print("|");
            for (Cell cell: line) {
                System.out.format("%-10s|", cell);
            }
            System.out.println();
        }
    }


    public void makeIteration() {
        int size = WeedArr.size();
        for (int i = 0; i < size; i++) {
            WeedArr.get(i).move();
        }
        size = FishPredatorArr.size();
        for (int i = 0; i < size; i++) {
            FishPredatorArr.get(i).move();
        }
        size = FishWeedEnjoyerArr.size();
        for (int i = 0; i < size; i++) {
            FishWeedEnjoyerArr.get(i).move();
        }
    }
}
