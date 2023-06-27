import java.util.StringJoiner;

public class MyHashMap<K, T> {

    private static class Node<K, T> {
        K key;
        T value;
        Node<K, T> next;

        public Node(K key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    public static final int CAPACITY = 10;
    private Node<K, T>[] data;
    private int size;

    public MyHashMap() {
        this.data = new Node[CAPACITY];
    }

    private int getHash(K key) {
        return Math.abs(key.hashCode()) % CAPACITY;
    }

    public void put(K key, T value) {
        if (key == null)
            return;

        Node<K, T> newNode = new Node<>(key, value);
        int index = getHash(key);

        if (data[index] == null) {
            data[index] = newNode;
        } else {
            Node<K, T> currentNode = data[index];
            Node<K, T> previosNode = null;

            while (currentNode != null) {
                if (currentNode.key.equals(key)) {
                    currentNode.value = value;
                    return;
                }
                previosNode = currentNode;
                currentNode = currentNode.next;
            }
            previosNode.next = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public void clear() {
        this.data = new Node[CAPACITY];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(K key) {
        if (key == null)
            return null;

        int index = getHash(key);

        if (data[index] != null) {
            Node<K, T> currentNode = data[index];

            while (currentNode != null) {
                if (currentNode.key.equals(key)) {
                    return currentNode.value;
                }
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    public T remove(K key) {
        if (key == null)
            return null;

        int index = getHash(key);

        if (data[index] != null) {
            Node<K, T> currentNode = data[index];
            Node<K, T> previosNode = null;

            while (currentNode != null) {
                if (currentNode.key.equals(key)) {
                    if (previosNode == null) {
                        data[index] = null;
                    } else {
                        previosNode.next = currentNode.next;
                    }
                    size--;
                    return currentNode.value;
                }
                previosNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "{", "}");

        for (int i = 0; i < CAPACITY; i++) {
            if (data[i] != null) {
                Node<K, T> node = data[i];
                do {
                    stringJoiner.add(node.key.toString() + "=" + node.value.toString());
                    node = node.next;
                } while (node != null);
            }
        }
        return stringJoiner.toString();
    }
}

