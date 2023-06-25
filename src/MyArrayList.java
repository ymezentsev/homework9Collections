import java.util.StringJoiner;

public class MyArrayList <T> {
    public static final int SIZE = 10;
    private Object[] data;
    private int index;

    public MyArrayList() {
        this.data = new Object[SIZE];
    }

    public void add(Object value) {
        if (index == data.length) {
            resizeArray();
        }
        data[index++] = value;
    }

    private void resizeArray() {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public void clear() {
        this.data = new Object[SIZE];
        this.index = 0;
    }

    public int size() {
        return index;
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public Object remove(int index) {
        if(index < 0 || index >= size()){
            return null;
        }
        Object element = data[index];

        for (int i = index; i < size(); i++) {
            data[i] = data[i+1];
        }
        this.index--;
        return element;
    }

    public Object get(int index) {
        if(index < 0 || index >= size()){
            return null;
        }
        return data[index];
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");

        for (int i = 0; i < index; i++) {
            stringJoiner.add(data[i].toString());
        }

        return stringJoiner.toString();
    }
}
