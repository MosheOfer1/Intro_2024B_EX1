package main.java;

public class logic {

    public static boolean[][] board;
    public static int solutionsCounter =0;

    //the recursive function for putting 8 queens on the chess board, in a way that no one can eat anyone else
    public static void queens(int raw) {
        //the exit condition, if it has reached to the last raw on the board
        if (raw==8){
            solutionsCounter++;
            //printBoard();
            return;
        }
        //if you have reached to not an empty raw, check for contradictions,
        //and if all good send to the next raw, else return.
        for (int i = 0; i < 8; i++) {
            if (board[i][raw]) {
                if (checkBoard(i, raw)) {
                    queens(raw + 1);
                } else {
                    return;
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            if (checkBoard(i,raw) && !board[i][raw]) {
                board[i][raw] = true;
                queens(raw + 1);
                board[i][raw] = false;
            }
        }
    }

    //true if no one can eat anyone else.
    //else return false.
    public static boolean checkBoard(int line, int raw) {
        //raw
        for (int i = 0; i < 8; i++) {
            if (board[line][i] && i!=raw){
                return false;
            }
        }

        //column
        for (int i = 0; i < 8; i++) {
            if (board[i][raw] && i!=line){
                return false;
            }
        }

        //left diagonal
//        int y = line - 1;
//        int x = raw - 1;
//        while (x >= 0 && y >= 0) {
//            if (board[y][x]){
//                return false;
//            }
//            x--;
//            y--;
//        }
//        y = line + 1;
//        x = raw + 1;
//        while (x < 8 && y < 8){
//            if (board[y][x]){
//                return false;
//            }
//            x++;
//            y++;
//        }
//
//        //right diagonal
//        y = line - 1;
//        x = raw + 1;
//        while (x < 8 && y >= 0) {
//            if (board[y][x]){
//                return false;
//            }
//            x++;
//            y--;
//        }
//        y = line + 1;
//        x = raw - 1;
//        while (x >= 0 && y < 8) {
//            if (board[y][x]){
//                return false;
//            }
//            x--;
//            y++;
//        }

        return checkingDiagonals(line,raw);
    }

    public static boolean checkingDiagonals(int line, int raw){
        int d = 7 - Math.max(line,raw);
        int x = raw + d;
        int y = line + d;
        while (x>=0 && y>=0){
            if (board[y][x] && x!=raw){
                return false;
            }
            x--;
            y--;
        }

        x = raw - Math.min(raw,7-line);
        y = line + Math.min(raw,7-line);
        while (x<=7 && y>=0){
            if (board[y][x] && x!=raw){
                return false;
            }
            x++;
            y--;
        }

        return true;
    }

    private static void printBoard() {
        System.out.println("########");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j])
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println();
        }
    }

    public static int countSolutions() {
        solutionsCounter = 0;
        board=new boolean[8][8];
        for (int i = 0; i < 8; i++) {
            int s = main.queen[i].getInSquare();
            if (s != -1){
                board[s/8][s%8]=true;
            }
        }
        queens(0);
        return solutionsCounter;
    }
}
