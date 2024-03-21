package mainPackage;

import static mainPackage.Main.bord_size;

public class Logic {

    public static int solutionsCounter = 0;
    public static boolean[][] bord;


    // A recursive function for putting 8 queens on the chess board, in a way that no one can eat anyone else
    public static void placeQueensRecursive(int raw, boolean[][] bord) {
        // Exit condition, if it has reached to the last raw on the board
        if (raw == bord_size){
            solutionsCounter++;
            //printBoard();
            return;
        }

        // If you have reached to not an empty raw, check for contradictions,
        // and if all good send to the next raw, else return.
        for (int i = 0; i < bord_size; i++) {
            if (bord[i][raw]) {
                if (checkBoard(i, raw, bord)) {
                    placeQueensRecursive(raw + 1, bord);
                } else {
                    return;
                }
            }
        }

        for (int i = 0; i < bord_size; i++) {
            if (checkBoard(i,raw, bord) && !bord[i][raw]) {
                bord[i][raw] = true;
                placeQueensRecursive(raw + 1, bord);
                bord[i][raw] = false;
            }
        }
    }

    // Return true if no one can eat anyone else
    public static boolean checkBoard(int line, int raw, boolean[][] bord) {
        //raw
        for (int i = 0; i < bord_size; i++) {
            if (bord[line][i] && i!=raw){
                return false;
            }
        }

        //column
        for (int i = 0; i < bord_size; i++) {
            if (bord[i][raw] && i!=line){
                return false;
            }
        }

        return checkDiagonals(line, raw, bord);
    }

    public static boolean checkDiagonals(int column, int raw, boolean[][] bord){
        int d = bord_size - 1 - Math.max(column,raw);
        int x = raw + d;
        int y = column + d;
        while (x >= 0 && y >= 0){
            if (bord[y][x] && x!=raw){
                return false;
            }
            x--;
            y--;
        }

        x = raw - Math.min(raw,bord_size - 1 - column);
        y = column + Math.min(raw,bord_size - 1 - column);
        while (x <= bord_size - 1 && y >= 0){
            if (bord[y][x] && x!=raw){
                return false;
            }
            x++;
            y--;
        }

        return true;
    }

    public static void printBoard(boolean[][] bord) {
        System.out.println("########");
        for (int i = 0; i < bord_size; i++) {
            for (int j = 0; j < bord_size; j++) {
                if (bord[i][j])
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println();
        }
    }

    public static int countSolutions(Queen[] queens) {
        solutionsCounter = 0;
        bord = new boolean[bord_size][bord_size];
        for (int i = 0; i < bord_size; i++) {
            int s = queens[i].getInSquare();
            if (s != -1){
                bord[s / bord_size][s % bord_size] = true;
            }
        }
        placeQueensRecursive(0, bord);
        return solutionsCounter;
    }
}
