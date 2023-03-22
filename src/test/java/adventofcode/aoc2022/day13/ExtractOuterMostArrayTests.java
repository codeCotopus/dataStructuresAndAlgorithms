package adventofcode.aoc2022.day13;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ExtractOuterMostArrayTests {

    @ParameterizedTest
    @CsvSource(value = {"[]:[]",
            "[4]:4",
            "[4]:[4]",
            "[[4],[2]]:[4,2]",
            "[[]]:[[]]",
            "[[],[[]]]:[[],[[]]]",
            "[[4],[]]:[4,[]]",
            "[[4],[3],[]]:[4,3,[]]",
            "[[4],[]]:[[4],[]]",
            "[[[4],[2]],[]]:[[4,2],[]]",

    }, delimiter = ':')
    void tranformTest(String expected, String value) {
        assertEquals(expected, transform(value));
    }


    // [1
    // 1
    // 1]
    //n[[1]
    //[1]
    //[[1
    //1]]n
    //[]
    //[[[]]]


    private String transform(String s) {
        if (!s.contains("[") && !s.contains(",")) {
            return "[" + s + "]";
        } else if (s.contains("[") && !s.contains(",") && s.contains("]")) {
            return s;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                for (int j = 1; j < s.length() - i; j++) {
//                    if (s.charAt(i + j) == '[') {
//
//
//
//                        log.info("ejre");
//                    }
                    //if (s.charAt(i + j) == ']') { // List does not contain sublist. Easy case.
                        String composedList = "[";
                        String withoutParenthesis = s.substring(1, s.length() - 1);
                        String[] split = withoutParenthesis.split(",");
                        for (String element : split) {
                            composedList += transform(element) + ",";
                        }
                        composedList = composedList.substring(0, composedList.length() - 1); //Remove ,
                        composedList += "]";
                        return composedList;
                 //   }
                }
            }
        }

        return "[]";
    }
}

