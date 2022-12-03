package dataStructures;

import lombok.AllArgsConstructor;

public class DoubleLinkedList<T> {


    private DoubleLInkedListNode <T> head;
    private DoubleLInkedListNode <T> tail;
    private int length = 0;

    public int length() {
        return length;
    }
    public T get(int i) {
        if (invalidIndex(i)) throw new IndexOutOfBoundsException();
        if (isFirstElement(i)) return this.pop();

        else{
            DoubleLInkedListNode<T> current = iterateTillNode(i, head);
            reassignPrevious(current);
            reassignNext(current);
            reasignTail(current);
            length--;
            return current.value;
        }
    }

    private DoubleLinkedList<T>.DoubleLInkedListNode<T> iterateTillNode(int i, DoubleLInkedListNode<T> current) {
        for (int j = 0; j < i; j++) {
            current = current.next;
        }
        return current;
    }

    private void reassignPrevious(DoubleLInkedListNode<T> current) {
        if (current.previous != null) current.previous.next= current.next;
    }

    private void reassignNext(DoubleLInkedListNode<T> current) {
        if (current.next != null) current.next.previous = current.previous;
    }

    private void reasignTail(DoubleLInkedListNode<T> current) {
        if (tail == current)
            tail= current.previous;
    }

    private static boolean isFirstElement(int i) {
        return i == 0;
    }

    private boolean invalidIndex(int i) {
        return i >= length || i < 0;
    }

    public void push(T test) {
        DoubleLInkedListNode<T> futureTail = new DoubleLInkedListNode<>(tail, test,null);
        if (head == null){
            head = futureTail;
        }
        if (tail != null) {
            tail.next = futureTail;
        }
        tail = futureTail;
        length++;
    }

    public T pop() {
        T value = head.value;
        head  = head.next;
        length--;
        return value;
    }

    @AllArgsConstructor
    private class DoubleLInkedListNode<S> {
        private DoubleLInkedListNode previous;
        private S value;
        private DoubleLInkedListNode next;
    }
}
