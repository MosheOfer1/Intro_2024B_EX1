package mainPackage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;
import static mainPackage.Logic.*;
import static mainPackage.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class LogicTest {
    @ParameterizedTest
    @MethodSource("provideTestBoards")
    public void testPrintBoard(boolean[][] board, String expectedOutput) {
        // Arrange
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        // Act
        printBoard(board);

        // Assert
        assertEquals(expectedOutput, outputStreamCaptor.toString().replace("\r",""));
    }

    private static Stream<Object[]> provideTestBoards() {
        return Stream.of(
                new Object[]{new boolean[][]{
                        {true, true, true, false, false, false, true, true},
                        {false, false, false, true, true, false, false, false},
                        {true, true, true, false, false, true, true, true},
                        {false, false, false, false, false, false, false, false},
                        {true, true, true, false, false, true, true, true},
                        {false, false, false, true, true, false, false, false},
                        {true, true, true, false, false, true, true, true},
                        {false, false, false, false, false, false, false, false}},
                        """
########
11100011
00011000
11100111
00000000
11100111
00011000
11100111
00000000
"""
                },
                new Object[]{new boolean[][]{
                        {false, false, false, false, false, false, false, false},
                        {false, true, true, true, true, true, true, false},
                        {false, false, false, false, false, false, false, false},
                        {false, true, true, true, true, true, true, false},
                        {false, false, false, false, false, false, false, false},
                        {false, true, true, true, true, true, true, false},
                        {false, false, false, false, false, false, false, false},
                        {false, true, true, true, true, true, true, false}},
                        """
########
00000000
01111110
00000000
01111110
00000000
01111110
00000000
01111110
"""}
        );
    }


    @Test
    public void testCheckBoardNoConflicts() {
        boolean[][] board = {
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false}
        };
        assertTrue(checkBoard(0, 0, board));
    }

    @Test
    public void testCheckBoardWithConflicts() {
        boolean[][] board = {
                {false, false, false, false, false, false, false, false},
                {false, false, true, false, true, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false}
        };
        assertFalse(checkBoard(2, 3, board)); // Conflict in row and column
        assertFalse(checkDiagonals(2, 1, board));

    }

    @Test
    public void testCheckDiagonalsConflicts() {
        boolean[][] board = {
                {false, false, false, false, false, false, false, false},
                {false, false, true, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, true, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false},
                {false, false, false, false, false, false, false, false}
        };
        assertFalse(checkDiagonals(2, 1, board));
        assertFalse(checkDiagonals(4, 3, board));
    }



    @Test
    void testCountSolutions() {
        Queen[] queens = new Queen[bord_size];
        for (int i = 0; i < 8; i++)
            queens[i] = new Queen(i);
        assertEquals(92, countSolutions(queens));
    }
    @Test
    void testCountSolutionsWithQueenInASquare1() {
        Queen[] queens = new Queen[bord_size];
        for (int i = 0; i < 8; i++)
            queens[i] = new Queen(i);
        queens[0].setInSquare(0);
        assertEquals(4, countSolutions(queens));
    }
    @Test
    void testCountSolutionsWithQueenInASquare2() {
        Queen[] queens = new Queen[bord_size];
        for (int i = 0; i < 8; i++)
            queens[i] = new Queen(i);
        queens[0].setInSquare(54);
        assertEquals(16, countSolutions(queens));
    }
}