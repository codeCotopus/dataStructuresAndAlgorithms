package adventofcode.aoc2022.day11;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class Day11Test {
    @Test
    public void part1Sample() {
        assertEquals(10605,part1(setSample()));
    }
    @Test
    public void part1Input() {
       part1(setInput());
    }

    private static int part1(List<Monkey> monkeys) {

        for (int i = 0; i < 20; i++) {
            log.info("ROUND {}",i+1);
            int m= 0;
            for (Monkey monkey : monkeys) {
                log.info("Monkey {}",m++);
                monkey.prioritize();
                monkey.relax();
                monkey.pass();
            }
        }

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for (Monkey monkey: monkeys) {
            int passes = monkey.getTotalInspectedItems();
            if (passes > max1){
                max2 = max1;
                max1 = passes;
            }else if (passes > max2){
                max2 = passes;
            }

            log.info("Moneky passes {}", passes);


        }
        int monkeyBusiness = max1 * max2;
        log.info("Monkey business {}",monkeyBusiness);
        return monkeyBusiness;
    }

    private static List<Monkey> setInput() {
        List<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            monkeys.add(new Monkey());
        }

        monkeys.get(0).addItem(74);
        monkeys.get(0).addItem(64);
        monkeys.get(0).addItem(74);
        monkeys.get(0).addItem(63);
        monkeys.get(0).addItem(53);
        monkeys.get(0).setPrioritization(i -> i * 7);
        monkeys.get(0).setEvaluation(i -> i % 5);
        monkeys.get(0).setMonkey1(monkeys.get(1));
        monkeys.get(0).setMonkey2(monkeys.get(6));

        monkeys.get(1).addItem(66);
        monkeys.get(1).addItem(99);
        monkeys.get(1).addItem(95);
        monkeys.get(1).addItem(62);
        monkeys.get(1).setPrioritization(i -> i * i);
        monkeys.get(1).setEvaluation(i -> i % 17);
        monkeys.get(1).setMonkey1(monkeys.get(2));
        monkeys.get(1).setMonkey2(monkeys.get(5));

        monkeys.get(2).addItem(59);
        monkeys.get(2).addItem(81);
        monkeys.get(2).setPrioritization(i -> i +8);
        monkeys.get(2).setEvaluation(i -> i % 7);
        monkeys.get(2).setMonkey1(monkeys.get(4));
        monkeys.get(2).setMonkey2(monkeys.get(3));

        monkeys.get(3).addItem(50);
        monkeys.get(3).addItem(67);
        monkeys.get(3).addItem(63);
        monkeys.get(3).addItem(57);
        monkeys.get(3).addItem(63);
        monkeys.get(3).addItem(83);
        monkeys.get(3).addItem(97);
        monkeys.get(3).setPrioritization(i -> i + 4);
        monkeys.get(3).setEvaluation(i -> i % 13);
        monkeys.get(3).setMonkey1(monkeys.get(0));
        monkeys.get(3).setMonkey2(monkeys.get(7));

        monkeys.get(4).addItem(61);
        monkeys.get(4).addItem(94);
        monkeys.get(4).addItem(85);
        monkeys.get(4).addItem(52);
        monkeys.get(4).addItem(81);
        monkeys.get(4).addItem(90);
        monkeys.get(4).addItem(94);
        monkeys.get(4).addItem(70);
        monkeys.get(4).setPrioritization(i -> i + 3);
        monkeys.get(4).setEvaluation(i -> i % 19);
        monkeys.get(4).setMonkey1(monkeys.get(7));
        monkeys.get(4).setMonkey2(monkeys.get(3));

        monkeys.get(5).addItem(69);
        monkeys.get(5).setPrioritization(i -> i + 5);
        monkeys.get(5).setEvaluation(i -> i % 3);
        monkeys.get(5).setMonkey1(monkeys.get(4));
        monkeys.get(5).setMonkey2(monkeys.get(2));

        monkeys.get(6).addItem(54);
        monkeys.get(6).addItem(55);
        monkeys.get(6).addItem(58);
        monkeys.get(6).setPrioritization(i -> i +7);
        monkeys.get(6).setEvaluation(i -> i % 11);
        monkeys.get(6).setMonkey1(monkeys.get(1));
        monkeys.get(6).setMonkey2(monkeys.get(5));

        monkeys.get(7).addItem(79);
        monkeys.get(7).addItem(51);
        monkeys.get(7).addItem(83);
        monkeys.get(7).addItem(88);
        monkeys.get(7).addItem(93);
        monkeys.get(7).addItem(76);
        monkeys.get(7).setPrioritization(i -> i * 3);
        monkeys.get(7).setEvaluation(i -> i % 2);
        monkeys.get(7).setMonkey1(monkeys.get(0));
        monkeys.get(7).setMonkey2(monkeys.get(6));

        return monkeys;
    }
    private static List<Monkey> setSample() {
        List<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            monkeys.add(new Monkey());
        }

        monkeys.get(0).addItem(79);
        monkeys.get(0).addItem(98);
        monkeys.get(0).setPrioritization(i -> i * 19);
        monkeys.get(0).setEvaluation(i -> i % 23);
        monkeys.get(0).setMonkey1(monkeys.get(2));
        monkeys.get(0).setMonkey2(monkeys.get(3));

        monkeys.get(1).addItem(54);
        monkeys.get(1).addItem(65);
        monkeys.get(1).addItem(75);
        monkeys.get(1).addItem(74);
        monkeys.get(1).setPrioritization(i -> i + 6);
        monkeys.get(1).setEvaluation(i -> i % 19);
        monkeys.get(1).setMonkey1(monkeys.get(2));
        monkeys.get(1).setMonkey2(monkeys.get(0));

        monkeys.get(2).addItem(79);
        monkeys.get(2).addItem(60);
        monkeys.get(2).addItem(97);
        monkeys.get(2).setPrioritization(i -> i * i);
        monkeys.get(2).setEvaluation(i -> i % 13);
        monkeys.get(2).setMonkey1(monkeys.get(1));
        monkeys.get(2).setMonkey2(monkeys.get(3));

        monkeys.get(3).addItem(74);
        monkeys.get(3).setPrioritization(i -> i + 3);
        monkeys.get(3).setEvaluation(i -> i % 17);
        monkeys.get(3).setMonkey1(monkeys.get(0));
        monkeys.get(3).setMonkey2(monkeys.get(1));
        return monkeys;
    }

    @Test
    public void canGetMonkeyItemFIFO() {
        Monkey monkey = new Monkey();
        monkey.addItem(79);
        monkey.addItem(98);
        assertEquals(2, monkey.getItems().size());
        assertEquals(79, monkey.getNextItem());
        assertEquals(98, monkey.getNextItem());
        assertEquals(0, monkey.getItems().size());
    }

    @Test
    public void monkeyChangePriority() {
        Monkey monkey = new Monkey();
        monkey.addItem(79);
        monkey.addItem(50);
        monkey.setPrioritization(i -> i + 2);
        monkey.prioritize();
        assertEquals(81, monkey.getNextItem());
        assertEquals(52, monkey.getNextItem());
    }

    @Test
    void monkeyCanSeeDeWorrying() {
        Monkey monkey = new Monkey();
        monkey.addItem(1862);
        monkey.addItem(1501);
        monkey.relax();
        assertEquals(620, monkey.getNextItem());
        assertEquals(500, monkey.getNextItem());
    }

    @Test
    void monkeyCanEvaluate() {
        Monkey monkey = new Monkey();
        monkey.addItem(3);
        monkey.addItem(4);
        monkey.setEvaluation(x -> x % 3);
        assertTrue(monkey.evaluate());
        monkey.getNextItem();
        assertFalse(monkey.evaluate());
    }

    @Test
    void monkeyCanPass() {
        Monkey monkey = new Monkey();
        Monkey monkey1 = new Monkey();
        Monkey monkey2 = new Monkey();
        monkey.setPrioritization(x -> x);
        monkey.addItem(3);
        monkey.addItem(4);
        monkey.setEvaluation(x -> x % 3);
        monkey.setMonkey1(monkey1);
        monkey.setMonkey2(monkey2);
        monkey.prioritize();
        monkey.pass();
        monkey.pass();
        assertEquals(0, monkey.getItems().size());
        assertEquals(3, monkey1.getNextItem());
        assertEquals(4, monkey2.getNextItem());
        assertEquals(2, monkey.getTotalInspectedItems());

    }
}

