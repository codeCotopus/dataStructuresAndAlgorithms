package adventofcode.aoc2022.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompositeThingy {
    Integer element;
    List<CompositeThingy> otherComposites = new ArrayList<>();

    String startsWithNumberRegexExpression = "^(\\d+)[,\\]].*$";
    Pattern pattern = Pattern.compile(startsWithNumberRegexExpression);

    public CompositeThingy(String s) {


        int totalArrays =0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='['){
                totalArrays++;
            }
        }



        
        
        
        Matcher matcher = pattern.matcher(s);
        boolean startsWithElement = matcher.find();
        if (startsWithElement) {
            element = Integer.parseInt(matcher.group(1));
        }

    }
}
