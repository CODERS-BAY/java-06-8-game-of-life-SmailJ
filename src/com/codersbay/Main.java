/*

Wont work, wont fix

 */

package com.codersbay;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*public class Main {
    public static boolean [][]calculateNextGeneration (boolean[][] currentGeneration){
        boolean[][] nextGeneration = Arrays.copyOf(currentGeneration, currentGeneration.length);
        for (int i = 1; i <= currentGeneration.length-1; i++) {
            for (int j = 1; j <= currentGeneration[0].length -2; j++) {
                int numberOfNeighbours = calculateNeighbours(currentGeneration, i, j);
                boolean livesInNextGen = nextCellLives(currentGeneration[i][j], numberOfNeighbours);
                nextGeneration[i][j] = livesInNextGen;
            }
        }
        return  nextGeneration;
    }

    public static int calculateNeighbours(boolean[][] field, int row, int column) {
        if (row == 0 || column == 0 || row == field.length - 1 || column == field[0].length - 1) {
            return -1;
        }

        boolean amIAlive = field[row][column];
        int neighbourCount = amIAlive ? -1 : 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (field[i][j] && (i != row || j != column)) {
                    neighbourCount++;
                }
            }
        }

        return neighbourCount;
    }

    public static boolean nextCellLives(boolean currentIsAlive, int nOfLivingNeighbours) {
        return staysAlive(currentIsAlive, nOfLivingNeighbours) ||
                isResurrected(currentIsAlive, nOfLivingNeighbours);
    }

    private static boolean staysAlive(boolean currentIsAlive, int nOfLivingNeighbours) {
        return currentIsAlive && (nOfLivingNeighbours == 2 || nOfLivingNeighbours == 3);
    }

    private static boolean isResurrected(boolean currentIsAlive, int nOfLivingNeighbours) {
        return !currentIsAlive && nOfLivingNeighbours == 3;
    }
}*/


public class Main {
    public static void main(String[] args) {
        boolean[][] habitat = new boolean[30][30];
        Scanner sc = new Scanner(System.in);
        System.out.println("How often you want to repeat it ?");
        int repeat = sc.nextInt();

        isAlive(habitat);
        nextGen(habitat, repeat);
    }

    public static void isAlive(boolean[][] habitat) {
        Random random = new Random();
        for (int i = 0; i < habitat.length; i++) {
            for (int j = 0; j < habitat.length; j++) {
                habitat[i][j] = random.nextBoolean();
                if (!habitat[i][j]) {
                    System.out.print("-");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }

    public static void nextGen(boolean[][] habitat, int repeat) {

        for (int k = 0; k < repeat; k++) {
            boolean[][] future = new boolean[habitat.length][habitat.length];
            for (int l = 1; l < habitat.length - 1; l++) {
                for (int m = 1; m < habitat.length - 1; m++) {
                    int aliveNeighbours = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            aliveNeighbours += habitat[l + i][m + j] ? 1 : 0;
                        }
                    }
                    aliveNeighbours -= habitat[l][m] ? 1 : 0;

                    if (isAlive(habitat, l, m) && (aliveNeighbours < 2)) {
                        future[l][m] = false;
                    } else if (isAlive(habitat, l, m) && (aliveNeighbours > 3)) {
                        future[l][m] = false;
                    } else if (!isAlive(habitat, l, m) && (aliveNeighbours == 3)) {
                        future[l][m] = true;
                    } else {
                        future[l][m] = habitat[l][m];
                    }
                }
            }

            System.out.printf("Round %s", k + 1);
            System.out.println();
            for (int i = 0; i < habitat.length; i++) {
                for (int j = 0; j < habitat.length; j++) {
                    if (!future[i][j])
                        System.out.print("-");
                    else
                        System.out.print("#");
                }
                System.out.println();
            }
            habitat = future;
        }
    }

    public static boolean isAlive(boolean habitat[][], int row, int col) {
        return habitat[row][col];
    }
}
