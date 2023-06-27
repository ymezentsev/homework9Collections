import java.util.StringJoiner;

public class MyStack<T> {
    public static final int SIZE = 10;
    private Object[] data;
    private int size;

    public MyStack() {
        this.data = new Object[SIZE];
    }

    public void push(T value) {
        if (size == data.length) {
            resizeArray();
        }
        data[size++] = value;
    }

    private void resizeArray() {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public void clear() {
        this.data = new Object[SIZE];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        return (T) data[size - 1];
    }

    public T pop() {
        T element = (T) data[size - 1];
        data[size - 1] = null;
        this.size--;
        return element;
    }

    public T remove(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        T element = (T) data[index];

        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        this.size--;
        return element;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        for (int i = 0; i < size; i++) {
            stringJoiner.add(data[i].toString());
        }

        return stringJoiner.toString();
    }
}
