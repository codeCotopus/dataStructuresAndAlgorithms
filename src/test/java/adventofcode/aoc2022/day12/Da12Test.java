package adventofcode.aoc2022.day12;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class Da12Test {


    char[][] sampleMap = Day12Input.INPUT.lines().map(String::toCharArray).toArray(char[][]::new);

    @Test
    public void part1sample() {
        char[][] sampleMap = Day12Input.INPUT.lines().map(String::toCharArray).toArray(char[][]::new);
        int sX = 0;
        int sY = 0;

        int eX = 0;
        int eY = 0;
        for (int i = 0; i < sampleMap.length; i++) {
            for (int j = 0; j < sampleMap[i].length; j++) {
                if (sampleMap[i][j] == 'S') {
                    sX = i;
                    sY = j;
                }
                if (sampleMap[i][j] == 'E') { //2,5 for sample
                    eX = i;
                    eY = j;
                    sampleMap[i][j] ='z';
                }
            }
        }

        List <PathNode>nodes = new ArrayList();
        PathNode start = new PathNode(sX, sY, 'a');
        PathNode end = new PathNode(eX, eY, 'z');
        start.setMap(sampleMap);
        nodes.add(start);


        while (!nodes.isEmpty()){
            PathNode node = nodes.remove(0);
            log.info("Node x {}, Y {}",node.getX(),node.getY());

            nodes.addAll(node.getPossibleMoves());


            if (node.equals(end)){
                log.info("FOUND {}{} Length {}",node.getX(),node.getY(),node.getVisitedNodes().size()-1);
                log.info("PATH:");
                node.getVisitedNodes().forEach(n ->
                        log.info("FOUND {} {}",n.getX(),n.getY())
                        );
                break;
            }
        }

    }

    @Test
    public void pathNodeKnowsWhereItIs() {
        PathNode node = new PathNode(3, 4, 1);
        assertEquals(3, node.getX());
        assertEquals(4, node.getY());
    }

    @Test
    public void nodeHasVisitedItself() {
        PathNode node = new PathNode(3, 4, 1);
        assertTrue(node.getVisitedNodes().contains(node));
    }

    @Test
    public void nodeKnowsVisitedNodes() {
        PathNode node = new PathNode(3, 4, 1);
        PathNode node2 = new PathNode(3, 5, 1, node);
        assertTrue(node.getVisitedNodes().contains(node));
        assertTrue(node2.getVisitedNodes().contains(node));
        assertTrue(node2.getVisitedNodes().contains(node2));
    }

    @Test
    public void nodeHasHeight() {
        PathNode node = new PathNode(3, 4, 'a');
        assertEquals('a', node.getHeight());
    }

    @Test
    public void nodeCanGoToValidNodes() {// Valid node is lower or 1 higher
        PathNode prevNode = new PathNode(0, 0, 'a');
        PathNode node = new PathNode(0, 1, 'a',prevNode);
        node.setMap(sampleMap);
        List<PathNode> possibleMOves = node.getPossibleMoves();
        assertEquals(2,possibleMOves.size());
    }

}

