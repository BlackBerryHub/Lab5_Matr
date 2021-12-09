package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        double matrix2[][]= new double[4][5];
        double table[][] = new double[5][6];

        double result_y []= new double[5];
        double result_x []=new double[4];
        double table_result[][];
        double g = 0, F = 0;
        double p[] = new double[4];
        double q[] = new double[5];

        Scanner sc = new Scanner(new BufferedReader(new FileReader("matrix.txt")));
        int rows = 5;
        int columns = 5;
        int [][] matrix = new int[rows][columns];
        while(sc.hasNextLine()) {
            for (int i=0; i<matrix.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    matrix[i][j] = Integer.parseInt(line[j]);
                }
            }
        }

        Functions f =new Functions();

        f.sedl_tochka(matrix);
        f.dom_r_i_dom_s(matrix);
        f.new_matrix(matrix, matrix2);
        f.simplex_matrix(matrix2, table);

        Simplex S = new Simplex();
        S.simplex(table);
        table_result = S.Calculate(result_y,result_x);

        System.out.println("\nРезультат - симплекс таблиця:");
        for (int i = 0; i < table_result.length; i++) {
            for (int j = 0; j < table_result[0].length; j++){
                String res = String.format("%.2f", table_result[i][j]);
                System.out.print(res + "\t\t");
            }
            System.out.println();
        }

        System.out.println("\nОптимальний план:");
        for (int i = 0; i < result_y.length; i++) {
            if (i< result_x.length) {
                System.out.print("y" + (i + 1) + " = ");
                System.out.printf("%.2f", result_y[i]);
                System.out.print("\t\t x" + (i + 1) + " = ");
                System.out.printf("%.2f", result_x[i]);
                F+=result_x[i];
                System.out.println();
            }
            else {
                System.out.print("y" + (i + 1) + " = ");
                System.out.printf("%.2f", result_y[i]);
            }
        }

        //System.out.println("\nFx="+F);
        g=1/F;
        f.tsina_gry_p(result_x, g, p);
        f.tsina_gry_q(result_y, g, q);
        f.perevirka_gry(g, result_y, result_x, p, q, matrix2);
    }


}
