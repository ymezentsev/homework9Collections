import java.util.StringJoiner;

public class MyLinkedList<T> {

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private int size;
    private Node<T> first;
    private Node<T> last;

    public void add(T value) {
        Node<T> newNode = new Node<>((T) value);

        if (size == 0) {
            first = last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void clear() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (isIndexWrong(index)) {
            return null;
        }

        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    private boolean isIndexWrong(int index) {
        return index < 0 || index >= size();
    }

    public T remove(int index) {
        if (isIndexWrong(index)) {
            return null;
        }

        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        T value = node.value;
        if (node != last) {
            node.next.prev = node.prev;
        } else {
            node.prev.next = null;
            last = node.prev;
        }

        if (node != first) {
            node.prev.next = node.next;
        } else {
            node.next.prev = null;
            first = node.next;
        }

        size--;
        return value;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        Node<T> node = first;
        for (int i = 0; i < size; i++) {
            stringJoiner.add(node.value.toString());
            node = node.next;
        }

        return stringJoiner.toString();
    }
}
