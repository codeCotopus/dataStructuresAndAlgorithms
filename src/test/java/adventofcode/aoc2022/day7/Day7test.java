package adventofcode.aoc2022.day7;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
public class Day7test {
    @Test
    public void sample() {
        solve2(Day7Input.INPUT);
    }



    private static int solve2(String input) {

        HashMap<String, Integer> sizes = fillFolderStructure(input);

        int total = 0;


        int freeSpace = 70000000 - sizes.get("/");
        int requiredSpace = 30000000 - freeSpace;

        int min =70000000;

        for (String folder :sizes.keySet()) {
            Integer integer = sizes.get(folder);

            if(integer >=requiredSpace ){
                if(integer<min)
                    min=integer;
            }
        }

        log.info("what is the rquired space: {}",requiredSpace);
        log.info("min space: {}",min);

        //log.info("File {}", sizes);
        //log.info("Total {}", total);
        return total;
    }


    private static int solve1(String input) {
        HashMap<String, Integer> sizes = fillFolderStructure(input);

        int total = 0;
        for (String folder :sizes.keySet()) {
            if(sizes.get(folder) <=100000 )
                total+=sizes.get(folder);
        }

        log.info("File {}", sizes);
        log.info("Total {}", total);
        return total;
    }

    private static HashMap<String, Integer> fillFolderStructure(String input) {
        String[] terminal = input.split("\n");
        HashMap<String, Integer> sizes = new HashMap<>();

        List<String> path = new ArrayList<>();
        // path.add("/");

        for (String line : terminal) {


            if (line.charAt(0) == '$') {
                log.info("Command to execute {}",line);
               // String currentDirectory = path.get(path.size() - 1);
                if (line.substring(2, 4).equals("ls")) {
                    // Listing directories
                //    log.info("listing {} directory", currentDirectory);
                }
                if (line.substring(2, 4).equals("cd")) {

                    log.info("moved to {} directory", line.substring(5, line.length()));

                    String o = (path.size()==0?"":path.toString()) + line.substring(5, line.length());
                    if (!sizes.containsKey(o)){
                        log.info("registering {} directory with 0 size", o);
                        sizes.put(o,0);
                    }

                    if (line.substring(5,6).equals("/")){
                        path = new ArrayList<>();
                        path.add("/");
                        log.info("Current path : {}",path);
                        continue;
                    }
                    if (line.substring(5,line.length()).equals("..")){
                        path.remove(path.size()-1);
                        log.info("Current path : {}",path);
                        continue;
                    }
                    path.add(path.toString()+line.substring(5, line.length()));
                    log.info("Current path : {}",path);
                }

                //This is a command. (which means we stop listing the results of directory
                continue;
            }

            if (line.charAt(0) == 'd') {
                // This is a directory. Need to drill down
                continue;
            }

            Pattern pattern = Pattern.compile("^(\\d+).*");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            Integer size = Integer.valueOf(matcher.group(1));
            log.info("adding file of size {}",size);
            for (String folder :path) {
                Integer previous = sizes.get(folder) ;
                sizes.put(folder,size+previous);
            }
            log.info("current sizes {}",sizes);



        }
        return sizes;
    }
}
