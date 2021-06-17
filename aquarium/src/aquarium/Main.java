package aquarium;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Aquarium aquarium = new Aquarium("src/Settings.txt");
        int countDays = 0;
        aquarium.printCurrentState();
        System.out.println("1) Запустить итерацию");
        System.out.println("2) Выход");
        while (true) {
            System.out.print("Выберите действие: ");
            int n = sc.nextInt();
            switch (n) {
                case 1:
                    aquarium.makeIteration();
                    countDays += 1;
                    System.out.println("День: " + countDays);
                    aquarium.printCurrentState();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Введено неверное число!");
            }
        }
    }
}
