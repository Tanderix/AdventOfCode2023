package Day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import Day11.ShortestPathInMatrix.Point;

public class day11part1 {
    public static int SIZE = 140;
    public static void main(String[] args) {
        String filePath = "./Day11/day11.txt";
        String line;
        int[] rows = new int[SIZE];
        int[] cols = new int[SIZE];
        int index = 0;
        String[] initMatrix = new String[SIZE];
        ArrayList<Point> positions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //Fill inizial matrix
            ArrayList<Integer> res;
            while((line = br.readLine()) != null){
                initMatrix[index] = line;
                res = checkUsed(line);
                if(res.size() > 0){
                    rows[index] = 1;
                    for(int c : res){
                        cols[c] = 1;
                    }
                }
                index++;
            }

            //Build new matrix
            int[][] newMatrix = new int[SIZE+offset(rows)][SIZE+offset(cols)];
            int curNum = 1;
            int i = 0;
            int newi = 0;
            while(i<SIZE){
                int newj = 0;
                int j = 0;
                while(j<SIZE){
                    if(initMatrix[i].charAt(j) == '#'){
                        newMatrix[newi][newj] = curNum;
                        curNum++;
                    }else{
                        newMatrix[newi][newj] = 0;
                    }
                    if(cols[j] == 0){
                        newj++;
                        newMatrix[newi][newj] = 0;
                    }
                    newj++;
                    j++;
                }
                if(rows[i] == 0){
                    int[] newrow = emptyRow(SIZE+offset(cols));
                    newMatrix[newi] = newrow;
                    newi++;
                    newMatrix[newi] = newrow;
                }
                newi++;
                i++;
            }

            //Fill positions
            for (int j = 0; j < newMatrix.length; j++) {
                for (int k = 0; k < newMatrix[i].length; k++) {
                    if(newMatrix[j][k] != 0){
                        positions.add(new Point(newMatrix[j][k], j, k));
                    }
                }
            }

            ArrayList<HashSet<Integer>> sets = new ArrayList<>();
            int sum = 0;
            int comb = 0;
            for (Point p1 : positions) {
                for(Point p2: positions){
                    HashSet<Integer> combs = new HashSet<>();
                    combs.add(p1.num);
                    combs.add(p2.num);
                    if(!sets.contains(combs)){
                        int path = ShortestPathInMatrix.shortestPath(newMatrix, p1, p2);
                        sum += path;
                        sets.add(combs);
                    }
                    comb++;
                }
            }

            System.out.println("Part 1: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Integer> checkUsed(String s){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != '.'){
                res.add(i);
            }
        }
        return res;
    }

    public static int offset(int[] arr){
        int res = 0;
        for (int i : arr) {
            if(i == 0){
                res++;
            }
        }
        return res;
    }

    public static int[] emptyRow(int length){
        int[] newrow = new int[length];
        for (int i = 0; i < length; i++) {
            newrow[i] = 0;
        }

        return newrow;
    }
}

class ShortestPathInMatrix {

    static class Point {
        int num = -1;
        int x;
        int y;
        
        public Point(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    
    static int[] row = {-1, 0, 0, 1};
    static int[] col = {0, -1, 1, 0};

    public static boolean isValid(int[][] matrix, boolean[][] visited, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        return (row >= 0) && (row < rows) && (col >= 0) && (col < cols) && !visited[row][col];
    }

    public static int shortestPath(int[][] matrix, Point source, Point destination) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[][] visited = new boolean[rows][cols];
        visited[source.x][source.y] = true;

        Queue<Point> queue = new LinkedList<>();
        queue.add(source);

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Point current = queue.poll();

                if (current.x == destination.x && current.y == destination.y) {
                    return distance; // Found destination
                }

                for (int j = 0; j < 4; j++) {
                    int newRow = current.x + row[j];
                    int newCol = current.y + col[j];

                    if (isValid(matrix, visited, newRow, newCol)) {
                        visited[newRow][newCol] = true;
                        queue.add(new Point(-1, newRow, newCol));
                    }
                }
            }
            distance++;
        }

        return -1; // No path found
    }
}