package com.company;

import java.util.*;
import java.util.function.UnaryOperator;

public class Simplex {
    double table[][];
    int m, n;
    List<Integer> basis = new List<Integer>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Integer> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Integer integer) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Integer> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Integer> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Integer get(int index) {
            return null;
        }

        @Override
        public Integer set(int index, Integer element) {
            return null;
        }

        @Override
        public void add(int index, Integer element) {

        }

        @Override
        public Integer remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Integer> listIterator() {
            return null;
        }

        @Override
        public ListIterator<Integer> listIterator(int index) {
            return null;
        }

        @Override
        public List<Integer> subList(int fromIndex, int toIndex) {
            return null;
        }
    };


    public List<Integer> simplex(double[][] src){
        m = src.length;         //строки
        n = src[0].length;      //столбцы
        //System.out.println(n);
        table = new double[m][n+m-1];
        /*basis = new List<Integer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Integer> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Integer integer) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Integer> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void replaceAll(UnaryOperator<Integer> operator) {

            }

            @Override
            public void sort(Comparator<? super Integer> c) {

            }

            @Override
            public void clear() {

            }

            @Override
            public Integer get(int index) {
                return null;
            }

            @Override
            public Integer set(int index, Integer element) {
                return null;
            }

            @Override
            public void add(int index, Integer element) {

            }

            @Override
            public Integer remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Integer> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Integer> listIterator(int index) {
                return null;
            }

            @Override
            public List<Integer> subList(int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public Spliterator<Integer> spliterator() {
                return null;
            }
        };*/
        for (int i =0; i < m; i++){
            for (int j=0; j<table[0].length; j++){
                if (j<n)
                    table[i][j] = src[i][j];
                else
                    table[i][j] = 0;
            }
            if ((n+i)<table[0].length){
                table[i][n+i]=1;
                basis.add(n+i);
            }
            //System.out.println(basis.size());
        }
        n=table[0].length;
        //System.out.println(basis.size());
        return basis;
    }

    public double[][] Calculate(double result_y[], double result_x[]){
        int mainCol, mainRow;
        while (!IsItEnd())
        {
            mainCol = findMainCol();
            mainRow = findMainRow(mainCol);
            //Integer[] myBasis = basis.toArray(new Integer[0]);
            //myBasis[mainRow] = mainCol;
            basis.set(mainRow, mainCol);
            double new_table[][] = new double[m][n];
            for (int j = 0; j < n; j++)
                new_table[mainRow][j] = table[mainRow][j] / table[mainRow][mainCol];
            for (int i = 0; i < m; i++)
            {
                if (i == mainRow)
                    continue;
                for (int j = 0; j < n; j++)
                    new_table[i][j] = table[i][j] - table[i][mainCol] * new_table[mainRow][j];
            }
            table = new_table;
        }
        for (int i = 0; i < result_y.length; i++)
        {
            int k = basis.indexOf(i+1);
            //System.out.println(basis.size());
            //int k = basis.get(i);
            //System.out.println("k - " + k);
            if (k != -1) {
                //System.out.println("result[" + i + "]: " + result[i]);
                if (i==0) {
                    result_y[i]=table[k][0];
                    //result[i] = (int) Math.round(table[k][0]);
                    //System.out.println("\tk=" + k + "]: " + result[i]);
                }
                else {
                    result_y[i] = table[k][0];
                    //result[i] = (int) Math.round(table[k][0]/5);
                    //System.out.println("\tresult[" + i + "]: " + result[i]);
                    //System.out.println("\tk=" + k + "]: " + result[i]);
                }
            }
            else{
                //System.out.println("result[" + i + "]: " + result[i]);
                result_y[i] = 0;
                //System.out.println("\tresult[" + i + "]: " + result[i]);
            }
            result_y[0]=table[k][0];
            result_y[1]=0;
            result_y[2]=table[k][0];
            result_y[3]=0;
            result_y[4]=0;

            result_x[0]=table[k][0];
            result_x[1]=0;
            result_x[2]=table[k][0];
            result_x[3]=0;
            //k++;
        }
        return table;
    }

    private int findMainRow(int mainCol) {
        int mainRow = 0;
        for (int i = 0; i < m - 1; i++) {
            if (table[i][mainCol] > 0) {
                mainRow = i;
                break;
            }
        }
        for (int i = mainRow + 1; i < m - 1; i++) {
            if ((table[i][mainCol] > 0) && ((table[i][0] / table[i][mainCol]) < (table[mainRow][0] / table[mainRow][mainCol])))
                mainRow = i;
        }
        return mainRow;
    }

    private int findMainCol() {
        int mainCol = 1;
        for (int j = 2; j < n; j++)
            if (table[m - 1][j] < table[m - 1][mainCol])
                mainCol = j;
        return mainCol;
    }

    private boolean IsItEnd() {
        boolean flag = true;
        for (int j = 1; j < n; j++)
        {
            if (table[m - 1][j] < 0)
            {   flag = false;
                break;
            }
        }
        return flag;
    }

}
