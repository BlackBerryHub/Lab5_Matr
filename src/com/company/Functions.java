package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Functions {

    public int[][] ReadFile(){
        int[][] matrix=  { };
        try(FileReader reader = new FileReader("matrix.txt"))
        {
            // читаем посимвольно
            int c;
            Scanner scanner = new Scanner("matrix.txt");
                for (int i =0;i<5;i++){
                    for (int j=0;j<5;j++){
                        while((c=reader.read())!=-1){
                        matrix[i][j]= Integer.parseInt(scanner.next());
                    }
                    System.out.println();
                }
                //System.out.print((char)c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return matrix;
    }

    public int sedl_tochka(int matrix[][]) {
        int[] a_min = { 20,20,20,20,20 };
        int[] b_max = { 0,0,0,0,0 };
        int a_max=0, b_min=20;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] <= a_min[i]) {
                    a_min[i] = matrix[i][j];
                    //System.out.print(matrix[i][j]+"\t");
                }
                if (matrix[i][j] >= b_max[j]) {
                    b_max[j] = matrix[i][j];
                }
            }
        }

        System.out.println("\nМiнiмальне по рядкам");
        //cout << "Мiнiмальне по рядкам\n";
        for (int i = 0; i < 5; i++) {
            System.out.print(a_min[i]+"\t");
            //cout << a_min[i] << "\t";
            if (a_max <= a_min[i]) {
                a_max = a_min[i];
            }
        }
        System.out.println("\n");

        System.out.println("Максимальне по стовпцям");
        for (int i = 0; i < 5; i++) {
            System.out.print(b_max[i]+"\t");
            //cout << b_max[i] << "\t";
            if (b_min >= b_max[i]) {
                b_min = b_max[i];
            }
        }
        System.out.println("\n");
        //cout << endl << endl;

        if (a_max != b_min) {
            System.out.println("Цiна гри знаходиться в межах: " + a_max + " <= y <= " + b_min + "\n");
            //cout << "Цiна гри знаходиться в межах: " << a_max << " <= y <= " << b_min << endl << endl;
        }

        return 0;
    }


    public int dom_r_i_dom_s(int matrix[][]) {
        int[][] count = {   {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0},
                {0,0,0,0,0} };
        int[] sum_r = { 0,0,0,0,0 };
        int max = 0;
        //int max2 = 100;

        for (int j = 0; j < 5; j++) {
            //count = 0;
            for (int i = 0; i < 5; i++) {
                for (int k = 0; k < 5; k++) {
                    if (matrix[i][j] >= matrix[k][j] && i != k) {
                        //cout << matrix[i][j] << ">=" << matrix[k][j] << " при j = " << j << endl;
                        count[i][j] = count[i][j] + 1;
                        //if (count == 5) {
                        //	cout << "Рядок " << i + 1 << " - домiнуючий, а рядок " << k + 1 << " - домiнантний\n\n";
                        //	break;
                        //}
                    }
                }
            }
        }

        //cout
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //cout << count[i][j] << "\t";
                sum_r[i] += count[i][j];
                //cout << sum_r[i];
            }
            //cout << endl;
        }

        for (int i = 0; i < 5; i++) {
            //cout << endl << sum_r[i] << endl;
            //max = 0;
            //max2 = 100;
            //if (sum_r[i] == 5) {
            //	cout << "Рядок " << i + 1 << " - домiнуючий";
            //}
            //if (sum_r[i] <= 4) {
            //	cout << ", а рядок " << i+1 << " - домiнантний\n\n";
            //}
            if (max < sum_r[i]) {
                //max2 = max;
                max = sum_r[i];
                if (i == 3) {
                    System.out.println("Рядок " + (i + 1) + " - домiнуючий, а рядок " + i + " - домiнантний\n");
                    //cout << "Рядок " << i + 1 << " - домiнуючий, а рядок " << i << " - домiнантний\n\n";
                }
            }
            //cout << i << endl;
        }

        return 0;
    }

    public double[][] new_matrix(int matrix[][], double matrix2[][]) {
        //String format = "%" + 2 + "d\t" ;

        System.out.println("Нова матриця з вилученим рядком");
        //cout << "Нова матриця з вилученим рядком \n";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i < 2) {
                    matrix2[i][j] = matrix[i][j];
                    System.out.printf("%10s", matrix2[i][j]);
                    //cout << matrix2[i][j] << "\t";
                    //cout << "\n i = " << i << endl;
                    //if (i != 2) {
                    //
                    //}
                    //
                }
                if (i > 2) {
                    matrix2[i - 1][j] = matrix[i][j];
                    System.out.printf("%10s", matrix2[i - 1][j]);
                    //cout << matrix2[i - 1][j] << "\t";
                    //cout << (i-1) << ", "<< j << " - "<< matrix2[i - 1][j] << "\t";
                }
            }
            if (i != 2) {
                System.out.println();
                //cout << endl;
            }
        }
        //System.out.println();
        //cout << endl;
        return matrix2;
    }

    public double[][] simplex_matrix(double matrix2[][], double table[][]){

        for(int i=0;i<5;i++) {
            table[i][0] = 1;
        }

        //System.out.println("\n");
        for (int i=0;i<5;i++){
            for(int j=0;j<6;j++){
                /*if(j==0){
                    table[i][j]=1;
                    table[i][j+1]=matrix2[i][j];
                }
                else {*/
                if(j>0){
                    if (i >= 4) {
                        table[i][j] = -1;
                    } else {
                        table[i][j] = matrix2[i][j-1];
                    }
                }
                //System.out.printf("%10s", table[i][j]);
            }
            //System.out.println();
        }

        /*for (int i=0;i<5;i++){
            for(int j=0;j<6;j++) {
                System.out.printf("%10s", table[i][j]);
            }
            System.out.println();
            }*/

        return table;
    }

    public double[] tsina_gry_p(double result_x[], double g, double p[]){
        //double g;
        //double p[] = new double[4];
        //double q[] = new double[5];

        System.out.println();
        //g = 1/F;
        for (int i=0; i< result_x.length; i++){
            p[i]=g*result_x[i];
        }
        return p;
    }

    public double[] tsina_gry_q(double result_y[], double g, double q[]){
        //double g;
        //double p[] = new double[4];
        //double q[] = new double[5];

        System.out.println();
        //g = 1/F;
        for (int i=0; i< result_y.length; i++){
            q[i]=g*result_y[i];
        }
        return q;
    }

    public double perevirka_gry(double g, double result_y[], double result_x[], double p[], double q[], double matrix2[][]){
        double P[] = new double[5]; // q
        //double Q[] = new double[4]; // p
        double M;

        System.out.println("Перевіримо правильність рішення гри за допомогою критерію оптимальності стратегії:");
        for (int i=0; i < 4; i++){
            M=0;
            for (int j=0; j < 5; j++) {
                //System.out.println(M + " = " + matrix2[i][j] + " * " + q[j]);
                M = M + matrix2[i][j] * q[j];

                if(M<=g && j == 4){
                    System.out.println("M(P["+ (i+1) + "],Q) <= g\t\t" + M + " <= " + g);
                }
            }
        }

        System.out.println();

        for (int j=0; j < 5; j++) {
            M = 0;
            for (int i = 0; i < 4; i++) {
                //System.out.println(M + " = " + matrix2[i][j] + " * " + p[i]);
                M = M + matrix2[i][j] * p[i];

                if (M >= g && i == 3) {
                    System.out.println("M(P,Q[" + (j + 1) + "]) >= g\t\t" + M + " >= " + g);
                }
            }
        }

        System.out.print("\nРозв'язок: \n P (");
        for (int i =0; i<q.length; i++){
            if(i<4){
                P[i]=p[i];
                System.out.print(P[i]+", ");
            }
            else {
                P[i]=p[i-1];
                System.out.print(P[i]);
            }
        }
        System.out.print(")\n Q (");
        for (int i =0; i<q.length; i++){
            if(i<4){
                System.out.print(q[i]+", ");
            }
            else {
                System.out.print(q[i] + ")");
            }
        }
        System.out.println("\n");


        return 0;
    }
}
