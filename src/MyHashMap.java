import java.util.StringJoiner;

public class MyHashMap<K, T> {

    private class Node {
        K key;
        T value;
        Node prev;
        Node next;

        public Node(K key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private Node first;
    private Node last;

    public void put(Object key, Object value) {
        Node newNode = new Node((K) key, (T) value);

        if (size == 0) {
            first = last = newNode;
        } else {
            Node node = first;
            for (int i = 0; i < size; i++) {
                if(node.key.equals(newNode.key)){
                    return;
                }
                node = node.next;
            }

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

    public Object get(Object key) {
        Node node = first;
        for (int i = 0; i < size; i++) {
            if(node.key.equals(key)){
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public Object remove(Object key) {
        boolean isFound = false;

        Node node = first;
        for (int i = 0; i < size; i++) {
            if(node.key.equals(key)){
                isFound = true;
                break;
            }
            node = node.next;
        }

        if(!isFound){
            return null;
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

        Node node = first;
        for (int i = 0; i < size; i++) {
            stringJoiner.add("{" + node.key.toString() + "=" + node.value.toString() + "}");
            node = node.next;
        }

        return stringJoiner.toString();
    }
}
