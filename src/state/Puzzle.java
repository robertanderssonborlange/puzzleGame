/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * The class describe a n-puzzle. The class has methods for showing the puzzle
 * mixing the tiles of the puzzle and move a tile. The class implements the
 * methods that are declared in the interface ITraverse
 *
 * @author Please, enter your name instead of this sentence if you do any changes.
 */
public class Puzzle extends State {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final Random randomGenerator = new Random();
    private final int[][] board;
    private int rowAndColumnSize = 4;
    private int spaceRow = 0, spaceColumn = 0, row = 0, col = 0, numberOfTiles = 0;


    /**
     * The constructor initiate a puzzle that has number of tiles.
     * The number of tiles must be 3, 8 or 15. If any other value is given
     * the number of tiles will be set to 8.
     *
     * @param numberOfTiles the number of tiles that the puzzle shall have
     */
    public Puzzle(int numberOfTiles) {
        if (numberOfTiles == 3) {
            rowAndColumnSize = 2;
        } else if (numberOfTiles == 15) {
            rowAndColumnSize = 4;
        } else if (numberOfTiles == 8) {
            rowAndColumnSize = 3;
        } else {
            System.err.println("Wrong number");
        }

        board = new int[rowAndColumnSize][];

        for (int i = 0; i < rowAndColumnSize; i++) {
            board[i] = new int[rowAndColumnSize];
        }

        int tileNumber = 1;
        for (int i = 0; i < rowAndColumnSize; i++) {
            for (int j = 0; j < rowAndColumnSize; j++) {
                board[i][j] = tileNumber++;
            }
        }

        spaceRow = rowAndColumnSize - 1;
        spaceColumn = rowAndColumnSize - 1;
        board[spaceRow][spaceColumn] = 0;
        this.numberOfTiles = numberOfTiles;
    }

    /**
     * The constructor initiate a puzzle with the content that are read in
     * via the scanner.
     *
     * @param scanner The scanner has to be connected to source that contains
     *                the state of a puzzle.
     */
    public Puzzle(Scanner scanner) {
        int numberOfTiles = scanner.nextInt();

        if (numberOfTiles == 3) {
            rowAndColumnSize = 2;
        } else if (numberOfTiles == 15) {
            rowAndColumnSize = 4;
        } else if (numberOfTiles == 8) {
            rowAndColumnSize = 3;
        } else {
            System.err.println("Wrong number");
        }

        board = new int[rowAndColumnSize][];

        for (int i = 0; i < rowAndColumnSize; i++) {
            board[i] = new int[rowAndColumnSize];
        }

        for (int i = 0; i < rowAndColumnSize; i++) {
            for (int j = 0; j < rowAndColumnSize; j++) {
                int tileNumber = scanner.nextInt();
                board[i][j] = tileNumber;

                if (tileNumber == 0) {
                    spaceRow = i;
                    spaceColumn = j;
                }
            }
        }
    }

    /**
     * The constructor initiate a puzzle to be a copy of the original.
     *
     * @param original The original to the new puzzle.
     */
    private Puzzle(Puzzle original) {
        this.rowAndColumnSize = original.rowAndColumnSize;

        board = new int[rowAndColumnSize][];

        for (int i = 0; i < rowAndColumnSize; i++) {
            board[i] = new int[rowAndColumnSize];
        }

        for (int i = 0; i < rowAndColumnSize; i++) {
            for (int j = 0; j < rowAndColumnSize; j++) {
                board[i][j] = original.board[i][j];
            }
        }

        this.spaceRow = original.spaceRow;
        this.spaceColumn = original.spaceColumn;
        this.parent = original.parent;
        this.depth = original.depth;
        this.numberOfTiles = original.numberOfTiles;
    }




    /**
     * The method return true if two puzzle object have equal boards, otherwise false.
     *
     * @param o The object that this object shall be compered with
     * @return true if this object is equal to anObject, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Puzzle puzzle1 = (Puzzle) o;

     //   for (int i=0;i<rowAndColumnSize;i++) {
       //     for (int j=0;j<rowAndColumnSize;j++){
         //       if (this.board[i][j] != puzzle1.board[i][j]) return false;
       //     }
    //    }

       if (!Arrays.deepEquals(this.board, puzzle1.board)) {
           return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    /**
     * The method print out the board of tiles
     */
    @Override
    public void show() {
        System.out.println("Depth = " + depth + " Heuristic value = " + heuristicValue);

        for (int i = 0; i < rowAndColumnSize; i++) {
            for (int j = 0; j < rowAndColumnSize; j++) {
                if (board[i][j] == 0)
                    System.out.print("  _");
                else if (board[i][j] <= 9)
                    System.out.print("  " + board[i][j]);
                else
                    System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * The method makes it possible for a person to move around tiles
     */
    public void solveByHuman() {
        String prompt = "Move the space, L left, U up, R right, D down, Q quit: ";
        System.out.print(prompt);
        String direction = keyboard.next();

        while (!direction.equalsIgnoreCase("q")) {
            moveSpace(direction);
            show();
            System.out.print(prompt);
            direction = keyboard.next();
        }
    }
    /**
     * Searches for the empty brick
     */
    private void loop() {
        row = 0;
        col = 0;

        for (int i = 0; i < rowAndColumnSize; i++) {
            for (int j = 0; j < rowAndColumnSize; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                }
            }
        }
    }

    /**
     * The method move the space on step in the direction that is sent to the
     * method. The parameter direction can have for different values;
     * L moves the space one step left if it is possible.
     * U moves the space one step up if it is possible.
     * R moves the space one step right if it is possible.
     * D moves the space one step down if it is possible.
     *
     * @param direction the direction to move
     */
    private void moveSpace(String direction) {
        if (direction.equalsIgnoreCase("U")) {

            loop();

            if (row != 0) {
                swap(row, col, row - 1, col);
            } else {
                System.err.println("Not possible to move up");
            }

        } else if (direction.equalsIgnoreCase("D")) {
            loop();
            if (row != rowAndColumnSize - 1) {
                swap(row, col, row + 1, col);
            } else {
                System.err.println("Not possible to move down");
            }

        } else if (direction.equalsIgnoreCase("L")) {
            loop();
            if (col != 0) {
                swap(row, col, row, col - 1);
            } else {
                System.err.println("Not possible to move left");
            }
        } else if (direction.equalsIgnoreCase("R")) {
            loop();
            if (col != rowAndColumnSize - 1) {
                swap(row, col, row, col + 1);
            } else {
                System.err.println("Not possible to move right");
            }
        }

    }

    /**
     * The method swap the value of board[i][j] with the value of board[k][l].
     *
     * @param i row index for one element of the board.
     * @param j column index for one element of the board.
     * @param k row index for another element of the board.
     * @param l column index for another element of the board.
     */
    private void swap(int i, int j, int k, int l) {
        int number = board[i][j];
        board[i][j] = board[k][l];
        board[k][l] = number;
    }

    /**
     * The method shuffle the tiles of the puzzle.
     */
    public void autoMix() {

        System.out.println("Mixed");
        boolean check = false;

        for (int i = 0; i < numberOfTiles; i++) {

            do {
                int random = randomGenerator.nextInt(4);


                if (random == 0) {
                    loop();
                    if (col != 0) {
                        swap(row, col, row, col - 1);
                        check = true;
                    }
                } else if (random == 1) {
                    loop();
                    if (row != 0) {
                        swap(row, col, row - 1, col);
                        check = true;
                    }
                } else if (random == 2) {
                    loop();
                    if (col != rowAndColumnSize - 1) {
                        swap(row, col, row, col + 1);
                        check = true;
                    }
                } else {
                    loop();
                    if (row != rowAndColumnSize - 1) {
                        swap(row, col, row + 1, col);
                        check = true;
                    }
                } //if-else

            } while (!check); //loop for possible moves.
        }//for

    }

    /**
     * The method create the children and return the references to them in a
     * array with 4 element. Some of the elements can have the value null if
     * it not was possible to create all children.
     *
     * @return An array with children, some of the elements can be null.
     */
    @Override
    public IState[] createChildren() {
        Puzzle[] children = new Puzzle[4];

        //left
        loop();
        if (col != 0) {
            Puzzle puzzle = new Puzzle(this);
            puzzle.swap(row, col, row, col - 1);
            children[0] = puzzle;
        } else {
            //  System.err.println("Not possible to move left");
            children[0] = null;
        }

        //up
        loop();
        if (row != 0) {
            Puzzle puzzle = new Puzzle(this);
            puzzle.swap(row, col, row - 1, col);
            children[1] = puzzle;
        } else {
            // System.err.println("Not possible to move up");
            children[1] = null;
        }

        //right
        loop();
        if (col != rowAndColumnSize - 1) {
            Puzzle puzzle = new Puzzle(this);
            puzzle.swap(row, col, row, col + 1);
            children[2] = puzzle;
        } else {
            //   System.err.println("Not possible to move right");
            children[2] = null;
        }

        //down
        loop();
        if (row != rowAndColumnSize - 1) {
            Puzzle puzzle = new Puzzle(this);
            puzzle.swap(row, col, row + 1, col);
            children[3] = puzzle;
        } else {
            //  System.err.println("Not possible to move down");
            children[3] = null;
        }

        for (Puzzle child : children) {
            if (child != null) {
                child.parent = this;
                child.depth = this.depth + 1;
            }
        }

        return children;
    }


    /**
     * calculate a heuristic value and assign it to this object.
     *
     * @param goal The goal state that is searched.
     */
    @Override
    public void calculateHeuristicValue(IState goal) {

        Puzzle puzzle = (Puzzle) goal;

        int missingTiles = 0; //tiles out of place

        for (int i = 0; i < rowAndColumnSize;i++) {
            for (int j = 0;j<rowAndColumnSize;j++) {
                if (board[i][j] != puzzle.board[i][j]){
                    missingTiles++;
                }
            }
        }

        heuristicValue = depth + missingTiles;

    }
}
