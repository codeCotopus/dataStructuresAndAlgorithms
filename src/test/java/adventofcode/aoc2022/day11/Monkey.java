package adventofcode.aoc2022.day11;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongUnaryOperator;
import java.util.stream.IntStream;

@Slf4j
public class Monkey {


    private List<Long> items = new ArrayList<>();
    private LongUnaryOperator prioritization;
    private LongUnaryOperator evaluation ;
    private Monkey monkey1;
    private Monkey monkey2;

    private  int inspections=0;


    public void addItem(long item) {
        items.add(item);
    }

    public List<Long> getItems() {
        return items;
    }

    public long getNextItem() {
        return items.remove(0);
    }


    public void setPrioritization(LongUnaryOperator f) {
        this.prioritization = f;
    }

    public void prioritize() {
        log.info("Monkey has {} items",items.size());
        inspections+=items.size();
        IntStream.range(0, items.size())
                .forEach(i -> {
                    log.info("item {} becoming {}",items.get(i),prioritization.applyAsLong(items.get(i)));
                    items.set(i, prioritization.applyAsLong(items.get(i)));

                });
    }


    public void relax() {
        IntStream.range(0, items.size())
                .forEach(i -> {
                    log.info("relaxing {} to {}",items.get(i),items.get(i)/3);
                    items.set(i, items.get(i)/3);
                });
    }

    public void setEvaluation(LongUnaryOperator predicate) {
        this.evaluation = predicate;
    }

    public boolean evaluate() {
        return evaluation.applyAsLong(items.get(0)) == 0;
    }

    public void setMonkey1(Monkey monkey) {
        this.monkey1 = monkey;
    }
    public void setMonkey2(Monkey monkey) {
        this.monkey2 = monkey;
    }


    public void pass() {
        while(!items.isEmpty()){
            log.info("evaluating and passing {}",items.get(0));
            (evaluate()?monkey1:monkey2)
                    .addItem(this.getNextItem());
        }

    }

    public int getTotalInspectedItems() {
        return inspections;
    }
}
