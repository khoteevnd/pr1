package khoteev;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MyHashTable<K,V> implements Table<K,V> {
	private int iTs;
	public Node<K,V>[] hashTable;
	private int size;
	private float fullness;
	private float LOADS_FACTOR = 0.75f;

	@SuppressWarnings("unchecked")
	MyHashTable(int initialTableSize) {
		iTs = initialTableSize;
		hashTable = new Node[iTs];
		fullness = hashTable.length * LOADS_FACTOR;
	}

	@SuppressWarnings("unchecked")
	MyHashTable(int initialTableSize, float loads_factor) {
		iTs = initialTableSize;
		LOADS_FACTOR = loads_factor;
		hashTable = new Node[iTs];
		fullness = hashTable.length * LOADS_FACTOR;
	}

	public Node<K,V>[] getHashTable(){
		return hashTable;
	}
	public int length() {
		return hashTable.length;
	}

	@Override
	public String toString(){
		String str = "";
		str = str + "size = " + this.size() + "\n";
		str = str + "lenght = " + this.length() + "\n";
		str = str + "fullness = " + this.fullness + "\n";
		str = str + "hashTable = " + this.hashTable + "\n";
		if(hashTable.length > 0) {
			Node<K,V>[] t = this.getHashTable();
			System.out.println("T = " + t.toString());
			for(int i=0; i < t.length; i++){
				if(t[i] != null)
					str = str + t[i].toString() + "\n";
			}
		}
		else {return "hashTable is null";}
		return str;
	}

	//Додавання елемента в тмассив елементів hashTable
	@Override
	public boolean insert(final K key, final V value) {
		// Якщо Таблиця заповнена, в залежності від значення LOAD_FACTOR, то подвоємо таблицю і 
		if(size + 1 >= fullness) {
			fullness *= 2;
			sizeHashTableDoubling();
		}
		// Створюємо новий елмент ключ=значення
		Node<K,V> newNode = new Node<>(key, value);
		// Обраховуємо позицію елемента в масиві hashTable, виходячи з хеша елемента і розміру таблиці
		int index = newNode.hash();

		// Якщо значення в масиві елментів hashTable дорівнює null, що означає що елемент не заповненний
		if(hashTable[index] == null) {
			//то додаємо створенний елемент в масив hashTable, за відповідною позицією
			hashTable[index] = new Node<>(null, null);
			hashTable[index].getNodes().add(newNode);
			// Збільшуємо значення кількості елементів в Хеш-таблиці
			size++;
			// Виходимо с вункції
			return true;
			//return simpleAdd(index, newNode);
		}
		// Якщо елемент не нулювий, то за цією позицією вже було додавання елемента, та створено список елементів
		// забераємо цей список для перевірки
		List<Node<K,V>> nodeList = hashTable[index].getNodes();
		
		// Робемо перевірку, якщо в списку вже є елемент з таким ключем, але значення різні, то перезаписуємо значення
		// Та перевіряєм на колізію елементів в списку
		final K newNodeK = newNode.getKey();
		final V newNodeV = newNode.getValue();
		int newNodeH = newNode.hashCode();
		
		for(Node<K,V> node: nodeList){
			
			boolean keyExistButValueNew;
			
			int nodeFromListH = node.hashCode(); 
			boolean eqH = (newNodeH == nodeFromListH)? true: false;
			
			final K nodeFromListK = node.getKey();
			boolean eqK = newNodeK.equals(nodeFromListK);

			final V nodeFromListV = node.getValue();
			boolean eqV = newNodeV.equals(nodeFromListV);

			// Якщо ключи співпадають а значення різні
			if(eqK && !eqV){
				// Записуємо нове значення
				node.setValue(value);
				keyExistButValueNew = true;
			} else {
				keyExistButValueNew = false;
			}

			//Перевірка на колізію
			boolean collisionFix;
			// Якщо хеш-коди елементів різні, та елюч різні, та значення різні, то в нас колізія
			if(!eqH && !eqK && !eqV){
				// Додаємо новий елемент в наш взятий лист з Хеш-таблиці по індексу
				nodeList.add(newNode);
				// Збільшуємо значення кількості елементів в Хеш-таблиці
				size++;
				collisionFix = true;
			} else {
				collisionFix = false;
			}
			
			// Якщо при перевірці було перезапис значення в елементі, або колізія завершаємо робуту функції та повертаємо істину
			if(keyExistButValueNew || collisionFix){ 
				return true;
			}
		}

		return false;
	}

	public void printHT() {
		System.out.println("----start----");
		for(Node<K,V> e: getHashTable()){
			if(e == null){
				System.out.println(e);
			}
			else {
				if(!e.nodes.isEmpty())
					System.out.println("not null");
			}	
		}
		System.out.println("----end----");
	}
	// private boolean simpleAdd(int index, Node<K,V> newNode) {
	// 	hashTable[index] = new Node<>(null, null);
	// 	hashTable[index].getNodes().add(newNode);
	// 	size++;
	// 	return true;
	// }

	// private boolean keyExistButValueNew( final Node<K,V> nodeFromList, final Node<K,V> newNode, final V value) {
	// 	K newNodeK = newNode.getKey();
	// 	K nodeFromListK = nodeFromList.getKey();
	// 	boolean eqK = newNodeK.equals(nodeFromListK);

	// 	V newNodeV = newNode.getValue();
	// 	V nodeFromListV = nodeFromList.getValue();
	// 	boolean eqV = newNodeV.equals(nodeFromListV);

	// 	// Якщо ключи співпадають а значені різні
	// 	if(eqK && !eqV){
	// 		// Записуємо нове значення
	// 		nodeFromList.setValue(value);
	// 		return true;
	// 	}
	// 	return false;
	// }

	// private boolean collisionFix( final Node<K,V> nodeFromList, final Node<K,V> newNode, List<Node<K,V>> nodes) {
	// 	int newNodeH = newNode.hashCode();
	// 	int nodeFromListH = nodeFromList.hashCode(); 
	// 	boolean eqH = (newNodeH == nodeFromListH)? true: false;

	// 	K newNodeK = newNode.getKey();
	// 	K nodeFromListK = nodeFromList.getKey();
	// 	boolean eqK = newNodeK.equals(nodeFromListK);

	// 	V newNodeV = newNode.getValue();
	// 	V nodeFromListV = nodeFromList.getValue();
	// 	boolean eqV = newNodeV.equals(nodeFromListV);
	// 	if(
	// 		!eqH && !eqK && !eqV
	// 		// newNode.hashCode() == nodeFromList.hashCode() && 
	// 		// !Objects.equals(newNode.k, nodeFromList.k) && 
	// 		// !Objects.equals(newNode.v, nodeFromList.v)
	// 	){
	// 		nodes.add(newNode);
	// 		size++;
	// 		return true;
	// 	}
	// 	return false;
	// }

	@SuppressWarnings("unchecked")
	// Подвоєння таблиці та переасподілу елементів
	private void sizeHashTableDoubling(){
		Node<K,V>[] oldHashTable = hashTable;
		hashTable = new Node[oldHashTable.length * 2];
		size = 0;
		for(Node<K,V> node : oldHashTable){
			if(node != null){
				for(Node<K,V> n: node.getNodes()){
					insert(n.k, n.v);
				}
			}
		}
	}

	@Override
	public boolean delete(final K key){
		int index = hash(key);
		if(hashTable[index] == null) return false;
		if(hashTable[index].getNodes().size() == 1){
			hashTable[index] = null;
			return true;
		}

		List<Node<K,V>> nodeList = hashTable[index].getNodes();
		for(Node<K,V> node: nodeList){
			if(key.equals(node.getKey())){
				nodeList.remove(node);
				return true;
			}
		}

		return false;
	}

	@Override
	public V get(final K key){
		int index = hash(key);
		if(index < hashTable.length && hashTable[index] != null){


			if(hashTable[index].getNodes().size() == 1){
				return hashTable[index].getNodes().get(0).getValue();
			}

			List<Node<K,V>> nodeList = hashTable[index].getNodes();
			for(Node<K,V> node: nodeList){
				if(key.equals(node.getKey())){
					return node.getValue();
				}
			}
		}
		return null;
	}

	//Повертає скільки єлементів в нашому масиві hashTable
	@Override
	public int size(){
		return size;
	}

	public int hash(final K key){
		int hashCode = 31;
		hashCode = hashCode * 17 + key.hashCode();
		return hashCode % hashTable.length;
	}


	@Override
	public Iterator<V> iterator(){
		return new Iterator<V>() {
			int counterArray = 0;
			int valuesCounter = 0;
			Iterator<Node<K,V>>	subIterator = null;

			@Override
			public boolean hasNext(){
				if(valuesCounter == size)
					return false;
				
				if(subIterator == null || subIterator.hasNext()){
					if(moveToNextCell()){
						subIterator = hashTable[counterArray].getNodes().iterator();
					} else {
						return false;
					}
				}

				return subIterator.hasNext();
			}

			private boolean moveToNextCell(){
				counterArray++;
				while(counterArray < hashTable.length && hashTable[counterArray] == null){
					counterArray++;
				}
				return counterArray < hashTable.length && hashTable[counterArray] != null;
			}

			@Override
			public V next(){
				valuesCounter++;
				return subIterator.next().getValue();
			}
		};
	}






	private class Node<K,V> {
		private List<Node<K,V>> nodes;
		private int hash;
		private K k;
		private V v;

		private Node(){
			k = null; 
			v = null;
			nodes = new LinkedList<Node<K,V>>();
		}

		private Node(K key,V value){
			k = key; 
			v = value;
			nodes = new LinkedList<Node<K,V>>();
			if(key != null)
				hashCode();
		}

		private List<Node<K,V>> getNodes() { return nodes; }
		
		private int hash() { 
			return hashCode() % hashTable.length;
		}
		
		private K getKey() { return k; }
		
		private V getValue() { return v; }
		
		private void setValue(V value) { v = value; }
		
		@Override
		public int hashCode() {
			int hashCode = 31;
			hashCode = hashCode * 17 + k.hashCode();
			return hashCode;
		}
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj) {
				return true;
			}
			if(obj instanceof Node) {
				@SuppressWarnings({ "rawtypes", "unchecked" })
				final Node<K,V> node = (Node) obj;
				return (
					Objects.equals(k, node.getKey()) &&
					Objects.equals(v, node.getValue()) ||
					Objects.equals(hash, node.hash)
					
				);
			}
			return false;
		}
		// @Override
		// public String toString(){
		// // 	String str = "";

		// // 	if(nodes.size() > 0){
		// // 		nodes.forEach((node) -> {
		// // 			node.toString();
		// // 			node.toString();
		// // 		});
		// // 	}
		// // 	else {
		// // 		if(this.k == null) {
		// // 			str = str + "key = " + "null" + " ";
		// // 		}
		// // 		else {
		// // 			str = str + this.k.toString();
		// // 		}
	
		// // 		if(this.v == null) {
		// // 			str = str + "value = " + "null" + "\n";
		// // 		}
		// // 		else {
		// // 			str = str + this.v.toString();
		// // 		}
		// // 	}
			
		// // 	return str;
		// }
	}


}
