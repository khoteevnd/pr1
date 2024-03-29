package khoteev;

import java.util.Dictionary;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

/**
 * Класс Хеш-таблица
 * Содержит массив листов строк
 * Размер этого массива
 * Константы p и x, необходимые для реализации хеш-функции
 * p в виде BigInteger
 */
public class TriangleHashTable {

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

	Node<K,V>[] table;
	private int size;
	public TriangleHashTable(int size) {
		this.size = size;
	}
	public boolean put(Triangle triangle){
		return false;
		//Node node = new Node<K,V>(hash(triangle.getPerimetr()), triangle.,);
	}

}