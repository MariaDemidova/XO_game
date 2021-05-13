package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static final char EMPTY_CELL = '*';
    public static final char CELL1 = 'X';
    public static final char CELL2 = 'O';
    public static final char SIZE = 3;
    public static char[][] field = new char[SIZE][SIZE];
    public static char cell;
    public static Random random = new Random();

    public static void main(String[] args) {
        whatPlay();
    }

    static void whatPlay() {
        System.out.println("С кем хотите поиграть?\n1 - игра для двух игроков человеческого происхождения.\n2 - игра человека против машины Зинки.");
        int answer = scanner.nextInt();
        while (true) {
            if (answer == 1) {
                multiPlay();
                break;
            }
            if (answer == 2) {
                playWithMaсhine();
                break;
            } else whatPlay();
        }
    }

    private static void playWithMaсhine() {
        initField();
        showField(field);
        int playerNumber = 1;
        do {
            makeStep(playerNumber, CELL1);
            if (isWin(CELL1)) {
                System.out.println("победил человек");
                break;
            }
            if (isFieldFull(field)) {
                System.out.println("Человек умен, как и машина. Ничья");
                break;
            }
            stepMaсhine();
            if (isWin(CELL2)) {
                System.out.println("Победила Зинка.");
                break;
            }
            if (isFieldFull(field)) {
                System.out.println("Никто не победил");
                break;
            }
        } while (true);
    }

    private static void stepMaсhine() {
        int x = 0;
        int y = 0;
        int check = 0;
        int checkEmptyCell = 0;
        int count = 0;
        for (int i = 0; i < SIZE; i++) { //проверка строки, чтоб заблокировать
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == CELL1) count++;
                if (field[i][j] == EMPTY_CELL) checkEmptyCell++;
            }
            if (count == SIZE - 1 && checkEmptyCell == 1) {
                y = i;
                check = 1;
                do {
                    x = random.nextInt(SIZE);
                } while (!isEmpty(y, x));
            }
            count = 0;
            checkEmptyCell = 0;
        }
        for (int i = 0; i < SIZE; i++) { //проверка столбца, чтоб заблокировать
            for (int j = 0; j < SIZE; j++) {
                if (field[j][i] == CELL1) count++;
                if (field[j][i] == EMPTY_CELL) checkEmptyCell++;
            }
            if (count == SIZE - 1 && checkEmptyCell == 1) {
                x = i;
                check = 1;
                do {
                    y = random.nextInt(SIZE);
                } while (!isEmpty(y, x));
            }
            count = 0;
            checkEmptyCell = 0;
        }
        for (int i = 0; i < SIZE; i++) { //проверка главной диаг, чтоб заблокировать
            if (field[i][i] == CELL1) count++;
            if (field[i][i] == EMPTY_CELL) checkEmptyCell++;
        }
        if (count == SIZE - 1 && checkEmptyCell == 1) {
            check = 1;
            for (int i = 0; i < SIZE; i++) {
                if (field[i][i] == EMPTY_CELL) {
                    x = i;
                    y = i;
                }
            }
        }
        count = 0;
        checkEmptyCell = 0;
        for (int i = 0; i < SIZE; i++) { //проверка 2 диаг, чтоб заблокировать
            if (field[i][SIZE - 1 - i] == CELL1) count++;
            if (field[i][SIZE - 1 - i] == EMPTY_CELL) checkEmptyCell++;
        }
        if (count == SIZE - 1 && checkEmptyCell == 1) {
            check = 1;
            for (int i = 0; i < SIZE; i++) {
                if (field[i][SIZE - 1 - i] == EMPTY_CELL) {
                    y = i;
                    x = SIZE - 1 - i;
                }
            }
        }
        count = 0;
        checkEmptyCell = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) { //проверка строки, чтоб победить
                if (field[i][j] == CELL2) count++;
                if (field[i][j] == EMPTY_CELL) checkEmptyCell++;
            }
            if (count == SIZE - 1 && checkEmptyCell == 1) {
                y = i;
                check = 1;
                do {
                    x = random.nextInt(SIZE);
                } while (!isEmpty(y, x));
            }
            count = 0;
            checkEmptyCell = 0;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) { //проверка столбца, чтоб победить
                if (field[j][i] == CELL2) count++;
                if (field[j][i] == EMPTY_CELL) checkEmptyCell++;
            }
            if (count == SIZE - 1 && checkEmptyCell == 1) {
                x = i;
                check = 1;
                do {
                    y = random.nextInt(SIZE);
                } while (!isEmpty(y, x));
            }
            count = 0;
            checkEmptyCell = 0;
        }
        for (int i = 0; i < SIZE; i++) { //проверка главной диаг, чтоб победить
            if (field[i][i] == CELL2) count++;
            if (field[i][i] == EMPTY_CELL) checkEmptyCell++;
        }
        if (count == SIZE - 1 && checkEmptyCell == 1) {
            check = 1;
            for (int i = 0; i < SIZE; i++) {
                if (field[i][i] == EMPTY_CELL) {
                    x = i;
                    y = i;
                }
            }
        }
        count = 0;
        checkEmptyCell = 0;
        for (int i = 0; i < SIZE; i++) { //проверка 2 диаг, чтоб победитьы
            if (field[i][SIZE - 1 - i] == CELL2) count++;
            if (field[i][SIZE - 1 - i] == EMPTY_CELL) checkEmptyCell++;
        }
        if (count == SIZE - 1 && checkEmptyCell == 1) {
            check = 1;
            for (int i = 0; i < SIZE; i++) {
                if (field[i][SIZE - 1 - i] == EMPTY_CELL) {
                    y = i;
                    x = SIZE - 1 - i;
                }
            }
        }
        if (check == 0) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isEmpty(y, x));
        }
        System.out.println("Зинаида сходила в точку " + (x + 1) + ", " + (y + 1));
        field[y][x] = CELL2;
        showField(field);
    }

    static void multiPlay() {
        initField();
        showField(field);
        int playerNumber = 1;
        do {
            if (playerNumber == 1) cell = CELL1;
            else cell = CELL2;
            makeStep(playerNumber, cell);
            if (isFieldFull(field)) {
                System.out.println("Человеки одинаково хороши");
                break;
            }
            if (isWin(cell)) {
                System.out.println("победил игрок" + playerNumber);
                break;
            }
            playerNumber = changeTurn(playerNumber);
        } while (true);
    }

    static void makeStep(int playerNumber, char cell) {
        int x = 0;
        int y = 0;
        do {
            System.out.println("Игрок " + playerNumber + ", введите координату X");
            x = scanner.nextInt() - 1;
            System.out.println("Игрок " + playerNumber + ", введите координату Y");
            y = scanner.nextInt() - 1;
            if (!isEmpty(y, x)) System.out.println("Сюда уже ходили или вы криво пишите. Напишите еще раз координаты.");
        } while (!isEmpty(y, x));

        field[y][x] = cell;
        showField(field);
    }

    static boolean isEmpty(int y, int x) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (field[y][x] == EMPTY_CELL) {
            return true;
        } else return false;
    }

    static int changeTurn(int playerNumber) {
        if (playerNumber == 1) {
            playerNumber = 2;
        } else {
            playerNumber = 1;
        }
        return playerNumber;
    }

    static void showField(char[][] field) {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < field.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
    }

    static void initField() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
    }

    static boolean isWin(char cell) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == cell) count++;
            }
            if (count == SIZE) return true;
            else count = 0;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[j][i] == cell) count++;
            }
            if (count == SIZE) return true;
            else count = 0;
        }
        for (int i = 0; i < SIZE; i++) {
            if (field[i][i] == cell) count++;
        }
        if (count == SIZE) return true;
        else count = 0;

        for (int i = 0; i < SIZE; i++) {
            if (field[i][SIZE - i - 1] == cell) count++;
        }
        if (count == SIZE) return true;
        return false;
    }

    static boolean isFieldFull(char[][] field) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[j][i] == EMPTY_CELL) return false;
            }
        }
        return true;
    }
}


