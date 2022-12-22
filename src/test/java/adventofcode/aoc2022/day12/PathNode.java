package adventofcode.aoc2022.day12;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
@Slf4j
public class PathNode {
    private int x;
    private int y;
    private int height;

    final private List<PathNode> visitedNodes;
    private char[][] map;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathNode pathNode = (PathNode) o;
        return x == pathNode.x && y == pathNode.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public PathNode(int x, int y, int height) {
        this.x = x;
        this.y = y;

        if (height=='E')
            this.height = 'z';
        else
            this.height = height;

        visitedNodes = new ArrayList<>();
        visitedNodes.add(this);
    }

    public PathNode(int x, int y, int height, PathNode node) {
        this(x, y, height);
        this.visitedNodes.addAll(node.visitedNodes);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public List<PathNode> getVisitedNodes() {


        return visitedNodes;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setMap(char[][] sampleMap) {
        this.map = sampleMap;
    }

    public List<PathNode> getPossibleMoves() {
        List<PathNode> possibleMoves = new ArrayList<>();
        if (x > 0)
            if (!visitedNodes.contains(new PathNode(x - 1, y, map[x - 1][y])))
                if (map[x - 1][y] - height <= 1) {
                    PathNode pathNode = new PathNode(x - 1, y, map[x - 1][y], this);
                    pathNode.setMap(map);
                    possibleMoves.add(pathNode);
                    //log.info("X{},Y{},height:{} , Added X:{},Y{},Height:{}",x,y,(char)height,x-1,y,map[x - 1][y]);
                }
        if (x + 1 < map.length)
            if (!visitedNodes.contains(new PathNode(x + 1, y, map[x + 1][y])))
                if (map[x + 1][y] - height <= 1) {
                    PathNode pathNode = new PathNode(x + 1, y, map[x + 1][y], this);
                    pathNode.setMap(map);
                    possibleMoves.add(pathNode);
                   // log.info("X{},Y{},height:{} , Added X:{},Y{},Height:{}",x,y,(char)height,x+1,y,map[x + 1][y]);
                }
        if (y > 0)
            if (!visitedNodes.contains(new PathNode(x, y - 1, map[x][y - 1])))
                if (map[x][y - 1] - height <= 1) {
                    PathNode pathNode = new PathNode(x, y - 1, map[x][y - 1], this);
                    pathNode.setMap(map);
                    possibleMoves.add(pathNode);
                    //log.info("X{},Y{},height:{} , Added X:{},Y{},Height:{}",x,y,(char)height,x,y-1,map[x][y-1]);

                }
        if (y + 1 < map[0].length)
            if (!visitedNodes.contains(new PathNode(x, y + 1, map[x][y + 1])))
                if (map[x][y + 1] - height <= 1) {
                    PathNode pathNode = new PathNode(x, y + 1, map[x][y + 1], this);
                    pathNode.setMap(map);
                    possibleMoves.add(pathNode);
                    //log.info("X{},Y{},height:{} , Added X:{},Y{},Height:{}",x,y,(char)height,x,y+1,map[x][y+1]);
                }


        return possibleMoves;
    }
}
