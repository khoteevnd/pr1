package khoteev;

//import java.util.LinkedList;

public class Node<K,V> {
	K k;
	V v;
	int hash;
	public Node(K key,V value) {
		k = key;
		v = value;
	}
}
