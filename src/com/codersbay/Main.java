package com.codersbay;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        boolean[][] habitat = new boolean[30][30];
        Scanner sc = new Scanner(System.in);
        System.out.println("How often you want to repeat it ?");
        int repeat = sc.nextInt();

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
        nextGen(habitat, repeat);
    }

    public static void nextGen(boolean[][] grid, int repeat) {
        boolean future[][] = new boolean[grid.length][grid.length];

        for (int k = 0; k < repeat; k++) {
            for (int l = 1; l < grid.length - 1; l++) {
                for (int m = 1; m < grid.length - 1; m++) {
                    int aliveNeighbours = 0;
                    for (int i = -1; i <= 1; i++)
                        for (int j = -1; j <= 1; j++)
                            aliveNeighbours += grid[l + i][m + j] ? 1 : 0;

                    aliveNeighbours -= grid[l][m] ? 1 : 0;

                    if ((grid[l][m]) && (aliveNeighbours < 2))
                        future[l][m] = false;

                    else if ((grid[l][m]) && (aliveNeighbours > 3))
                        future[l][m] = false;

                    else if ((!grid[l][m]) && (aliveNeighbours == 3))
                        future[l][m] = true;

                    else
                        future[l][m] = grid[l][m];
                }
            }

            System.out.printf("Round %s", k + 1);
            System.out.println();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    if (!future[i][j])
                        System.out.print("-");
                    else
                        System.out.print("#");
                }
                System.out.println();
            }
        }
    }
}
