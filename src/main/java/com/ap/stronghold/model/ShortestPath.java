package com.ap.stronghold.model;

import java.util.*;

public class ShortestPath {
    private static char[][] map2dToChar2d(Map[][] gameMap, int dX, int dY) {
        int rows = Game.getY();
        int columns = Game.getX();
        char[][] matrix = new char[rows][columns];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if(x == dX && y == dY){
                    matrix[x][y] = 'D';
                }else if(gameMap[x][y].getMapType().isCanBePassed()){
                    matrix[x][y] = '1';
                }else{
                    matrix[x][y] = '0';
                }
            }
        }
        return matrix;
    }
    public static ArrayList<Map> getPath(Map[][] mapMatrix, int dx1, int dy1, int dx2, int dy2) {
        char[][] matrix = map2dToChar2d(mapMatrix, dx2, dy2);
        Queue<Node2> queue = new LinkedList<>();

        Node2 source = new Node2(dx1, dy1, null);
        int numOfRows = matrix.length;
        int numOfColumns = matrix[0].length;

        queue.add(source);

        while (!queue.isEmpty()) {
            Node2 poped = queue.poll();
            System.out.println("x: " + poped.x + " y: " + poped.y);
            if (matrix[poped.x][poped.y] == 'D') {
                ArrayList<Map> maps = new ArrayList<>();
                Node2 p = poped;
                while (p != null) {
                    maps.add(Game.getGameMapXY(p.x, p.y));
                    p = p.perv;
                }
                Collections.reverse(maps);
                return maps;
            } else {
                matrix[poped.x][poped.y] = '0';

                List<Node2> neighbourList = addNeighbours(poped, matrix, numOfRows, numOfColumns);
                queue.addAll(neighbourList);
            }
        }
        return null;
    }

    private static List<Node2> addNeighbours(Node2 poped, char[][] matrix, final int numOfRows, final int numOfColumns) {

        List<Node2> list = new LinkedList<Node2>();

        if ((poped.x - 1 >= 0 && poped.x - 1 < numOfRows) && matrix[poped.x - 1][poped.y] != '0') {
            list.add(new Node2(poped.x - 1, poped.y, poped));
        }
        if ((poped.x + 1 >= 0 && poped.x + 1 < numOfRows) && matrix[poped.x + 1][poped.y] != '0') {
            list.add(new Node2(poped.x + 1, poped.y, poped));
        }
        if ((poped.y - 1 >= 0 && poped.y - 1 < numOfColumns) && matrix[poped.x][poped.y - 1] != '0') {
            list.add(new Node2(poped.x, poped.y - 1, poped));
        }
        if ((poped.y + 1 >= 0 && poped.y + 1 < numOfColumns) && matrix[poped.x][poped.y + 1] != '0') {
            list.add(new Node2(poped.x, poped.y + 1, poped));
        }
        if ((poped.x - 1 >= 0 && poped.x - 1 < numOfRows) && (poped.y - 1 >= 0 && poped.y - 1 < numOfColumns) && matrix[poped.x - 1][poped.y - 1] != '0') {
            list.add(new Node2(poped.x - 1, poped.y - 1, poped));
        }
        if ((poped.x - 1 >= 0 && poped.x - 1 < numOfRows) && (poped.y + 1 >= 0 && poped.y + 1 < numOfColumns) && matrix[poped.x - 1][poped.y + 1] != '0') {
            list.add(new Node2(poped.x - 1, poped.y + 1, poped));
        }
        if ((poped.x + 1 >= 0 && poped.x + 1 < numOfRows) && (poped.y - 1 >= 0 && poped.y - 1 < numOfColumns) && matrix[poped.x + 1][poped.y - 1] != '0') {
            list.add(new Node2(poped.x + 1, poped.y - 1, poped));
        }
        if ((poped.x + 1 >= 0 && poped.x + 1 < numOfRows) && (poped.y + 1 >= 0 && poped.y + 1 < numOfColumns) && matrix[poped.x + 1][poped.y + 1] != '0') {
            list.add(new Node2(poped.x + 1, poped.y + 1, poped));
        }
        return list;
    }
}

class Node2 {
    int x;
    int y;
    Node2 perv;

    Node2(int x, int y, Node2 perv) {
        this.x = x;
        this.y = y;
        this.perv = perv;
    }
}