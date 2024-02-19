/*
Варіант = 1

Завдання першого рівня
Клас (геометрична фігура) та його методи = Трикутник: координати вершин, конструктор, методи обчислення площі, периметра, виведення об’єкта
Метод хешування = Ділення
Ключ = Периметр

Завдання другого рівня
Метод вирішення колізій = Роздільне зв'язування

Завдання третього рівня
Крігтерій видалення елементів = Елементи зі значенням плоші, меншої від заданої

*/

package khoteev;
import khoteev.Triangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class pr1 {
	public static void ss(Object Object){
		System.out.println(Object);
	}
	public static void ss(int i){
		System.out.println(i);
	}
	public static void ss(double i){
		System.out.println(i);
	}
	// private static int hash(double key){
		
	// }
	public static void main(String[] args) {

		int count = 15;
		MyHashTable<String,String> strings = new MyHashTable<>(count);
		for (int i = 0; i < count; i++){
			strings.insert(""+i,"ccc"+i);
		}

		//strings.printHT();

		ss(strings.length());
		ss(strings.size());
		for (int i = 0; i < count; i++){
			ss(strings.get(""+i));
		}
		// MyHashTable<Float, Triangle> table = new MyHashTable<>(count);
		// for (int i = 0; i < count; i++){
		// 	Triangle t = new Triangle(0f, 20f);
		// 	table.insert(t.getPerimetr(), t);
		// }

		// table.printHT();

		// for (Triangle t : table) {
		// 	System.out.println(t);
		// }
	}
}