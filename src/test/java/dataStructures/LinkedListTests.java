package dataStructures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LinkedListTests {
    private static final String test = "Test";
    private static final String test2 = "Test2";
    private static final String test3 = "Test3";
    private DoubleLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new DoubleLinkedList<>();
    }

    @Test
    public void WHEN_ListIsCreated_EmptyList() {
        listLengthIs(0);
    }

    @Test
    public void IfRemovingFromInvalidIndex_ThrowException() {
        exceptionWhenGettingIndex(1);
        exceptionWhenGettingIndex(0);
        exceptionWhenGettingIndex(-1);
    }


    @Test
    public void WhenPushingElLements_CorrectLengthIsReturned() {
        list.push(test);
        listLengthIs(1);
        list.push(test);
        listLengthIs(2);
    }

    @Test
    public void CanPopHead() {
        list.push(test);
        assertEquals(test, list.pop());
        listLengthIs(0);
    }

    @Test
    public void IfTwoPushes_TwoCorrectPops() {
        list.push(test);
        list.push(test2);
        assertEquals(test, list.pop());
        assertEquals(test2, list.pop());
    }

    @Test
    public void CanGetSpecificElement() {
        initializeListWithElements();

        listLengthIs(3);
        assertEquals(test2, list.get(1));
        listLengthIs(2);
        assertEquals(test3, list.get(1));
        listLengthIs(1);
        assertEquals(test, list.get(0));
        listLengthIs(0);
    }

    @Test
    //@UseReporter(VisualStudioCodeReporter.class)
    public void WhenGettingTailByIndex_TailIsUpdated() {
        initializeListWithElements();

        listLengthIs(3);
        assertEquals(test3, list.get(2));

        listLengthIs(2);
        assertEquals(test2, list.get(1));
        listLengthIs(1);
        exceptionWhenGettingIndex(1);
        list.push("Test4");
        assertEquals("Test4", list.get(1));
        listLengthIs(1);
        exceptionWhenGettingIndex(1);
        assertEquals(test, list.get(0));
        listLengthIs(0);
        exceptionWhenGettingIndex(0);
        listLengthIs(0);
    }

    @Test
    public void WhenPoppingHead_HeadIsUpdated() {
        assertHeadIsUpdatedWhen(() -> list.pop());
    }

    @Test
    public void WhenGettingHeadByIndex_HeadIsUpdated() {
        assertHeadIsUpdatedWhen(() -> list.get(0));
    }

    private void assertHeadIsUpdatedWhen(Runnable runnable) {
        initializeListWithElements();

        runnable.run();
        exceptionWhenGettingIndex(2);
        assertEquals(test3, list.get(1));
        assertEquals(test2, list.get(0));
    }

    private void initializeListWithElements() {
        list.push(test);
        list.push(test2);
        list.push(test3);
    }


    private void exceptionWhenGettingIndex(int i) {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(i));
    }

    private void listLengthIs(int expected) {
        assertEquals(expected, list.length());
    }
}
