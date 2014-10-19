Arrays-and-Collections
=======================

Thinking in Java Ch 16 &amp; Ch 17

## Outline
- [Array](#Array)
	+ [Array的獨特之處](#Array的獨特之處)
	+ [Array的記憶體](#Array的記憶體)
	+ [多維Array](#多維Array)
	+ [Array與泛型](#Array與泛型)
	+ [建立測試資料](#建立測試資料)


## Array
	一種重要的資料結構，幾乎所有高階的程式語言都提供了Array，
	其優點在於當要儲存大量同性質的資料時是很有效率的，而由於
	在記憶體中是以連續間加以配置，這種連續的線性序列，使得它
	存取元素的速度十分的快速，但也同樣有著它的限制，Array一旦
	產生，它的大小就會固定，無法動態改變，且在編譯之前就必須
	知道切確型別。

### Array的獨特之處
	Array可以持有基本型別(Ex:int char),而這是其他的泛型容器所
	做不到的，但在Autoboxing出現之後，基本型別與object之間的轉
	換皆為自動進行，這就使得泛型容器在使用時好像可以持有基本型別
	，也同時因為Autoboxing出現的出現加上容器有著比array更多的功能
	，在很多時候我們會選擇容器而非Array

### Array的記憶體	
	![memory.jpg](photo/memory.jpg)
	兩者唯一的差別在於一個持有references 另一個持有基本型別之值
``` java

public class Reference {

	public static void main(String[] args) {
		int [] a1 = {1,2,3,4,5,6};
		int [] a2;
		Integer [] a3 = {new Integer(1),new Integer(2),3,4,5,6};
		Integer [] a4;
		a2 = a1;
		a4 = a3;
		System.out.println("Before:");
		for(int i = 0; i < 6; i++){
			System.out.println("a1["+i+"] = "+a1[i]);
		}
		
		for(int i = 0; i < 6; i++){
			a2[i] = a2[i] + 1;
		}
		
		System.out.println("After:");
		for(int i = 0; i < 6; i++){			
			System.out.println("a1["+i+"] = "+a1[i]);
		}
		System.out.println("Before:");
		for(int i = 0; i < 6; i++){			
			System.out.println("a3["+i+"] = "+a3[i]);
		}
		System.out.println("After:");
		for(int i = 0; i < 6; i++){
			a4[i] = a4[i] + 1;
			System.out.println("a3[" + i +"] = "+a3[i]);
			
		}
	}

}

```
### 多維Array
	每一維度中的資料皆以{}區分，每一組相呼應的{}後定義的便是下一
	維度的Array資料
``` java
import java.util.Arrays;

public class TwoDimensionArray {
	
	public static void main(String[] args) {
		int [][] a = {
			{1,2,3},
			{4,5,6},
			{7,8,9},
		};
		System.out.println(Arrays.deepToString(a));
	}
}
```
``` java
import java.util.Arrays;

public class ThreeDimensionArray {

	public static void main(String[] args) {
		int [][][] a =new int[5][6][7];
		
		System.out.println(a.length);
		System.out.println(a[0].length);
		System.out.println(a[0][1].length);
		System.out.println(Arrays.deepToString(a));
	}
}
```
### Array與泛型
	通常來說，array和泛型是不能混用的，我們無法使用參數化型別來形成array
	``` java
	Peel<Banana>[] = peels = new Peel<Banana>[10]; //Illegal
	```
	erasure會移去參數化型別的類別資訊，但array必須要知道所持物件的明確型別。
	
### 建立測試資料
	在測試時，如果能輕鬆地將array填滿，對於測試會相當有幫助
	
	Arrays.fill()
	java所提供的一個方法，能將單一數值或物件的reference複製到array中的每個
	位置。
``` java
package generatora;
import java.util.Arrays;

public class FillingArrays {

	public static void main(String[] args) {
		int size = 6;
		boolean[] a1 = new boolean[size];
		int[] a2 = new int[size];
		String[] a3 = new String[size];
		Arrays.fill(a1, true);
		System.out.println("a1 = " + Arrays.toString(a1));
		Arrays.fill(a2, 10);
		System.out.println("a2 = " + Arrays.toString(a2));
		Arrays.fill(a3, "Last");
		System.out.println("a3 = " + Arrays.toString(a3));
	}
}

	
```
	但這麼做只能建立單一元素的Array，為了要更有彈性會搭配Generator
	以下介紹兩種產生器

``` java
package generatora;

public class CountingGenerator {
	public static class Boolean implements Generator<java.lang.Boolean> {
		private boolean value = false;

		public java.lang.Boolean next() {
			value = !value;
			return value;
		}
	}

	public static class Byte implements Generator<java.lang.Byte> {
		private byte value = 0;

		public java.lang.Byte next() {
			return value++;
		}
	}

	static char[] chars = ("abcdefghijklmnopqrstuvwxyz"
			+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

	public static class Character implements Generator<java.lang.Character> {
		int index = -1;

		public java.lang.Character next() {
			index = (index + 1) % chars.length;
			return chars[index];
		}
	}

	public static class String implements Generator<java.lang.String> {
		private int length = 7;
		Generator<java.lang.Character> cg = new Character();

		public String() {
		}

		public String(int length) {
			this.length = length;
		}

		public java.lang.String next() {
			char[] buf = new char[length];
			for (int i = 0; i < length; i++)
				buf[i] = cg.next();
			return new java.lang.String(buf);
		}
	}

	public static class Short implements Generator<java.lang.Short> {
		private short value = 0;

		public java.lang.Short next() {
			return value++;
		}
	}

	public static class Integer implements Generator<java.lang.Integer> {
		private int value = 0;

		public java.lang.Integer next() {
			return value++;
		}
	}

	public static class Long implements Generator<java.lang.Long> {
		private long value = 0;

		public java.lang.Long next() {
			return value++;
		}
	}

	public static class Float implements Generator<java.lang.Float> {
		private float value = 0;

		public java.lang.Float next() {
			float result = value;
			value += 1.0;
			return result;
		}
	}

	public static class Double implements Generator<java.lang.Double> {
		private double value = 0.0;

		public java.lang.Double next() {
			double result = value;
			value += 1.0;
			return result;
		}
	}
}
```
	
``` java
package generatora;

import java.util.Random;

public class RandomGenerator {
	private static Random r = new Random(47);

	public static class Boolean implements Generator<java.lang.Boolean> {
		public java.lang.Boolean next() {
			return r.nextBoolean();
		}
	}

	public static class Byte implements Generator<java.lang.Byte> {
		public java.lang.Byte next() {
			return (byte) r.nextInt();
		}
	}

	public static class Character implements Generator<java.lang.Character> {
		public java.lang.Character next() {
			return CountingGenerator.chars[r
					.nextInt(CountingGenerator.chars.length)];
		}
	}

	public static class String extends CountingGenerator.String {
		// Plug in the random Character generator:
		{
			cg = new Character();
		} // Instance initializer

		public String() {
		}

		public String(int length) {
			super(length);
		}
	}

	public static class Short implements Generator<java.lang.Short> {
		public java.lang.Short next() {
			return (short) r.nextInt();
		}
	}

	public static class Integer implements Generator<java.lang.Integer> {
		private int mod = 10000;

		public Integer() {
		}

		public Integer(int modulo) {
			mod = modulo;
		}

		public java.lang.Integer next() {
			return r.nextInt(mod);
		}
	}

	public static class Long implements Generator<java.lang.Long> {
		private int mod = 10000;

		public Long() {
		}

		public Long(int modulo) {
			mod = modulo;
		}

		public java.lang.Long next() {
			return new java.lang.Long(r.nextInt(mod));
		}
	}

	public static class Float implements Generator<java.lang.Float> {
		public java.lang.Float next() {
			
			int trimmed = Math.round(r.nextFloat() * 100);
			return ((float) trimmed) / 100;
		}
	}

	public static class Double implements Generator<java.lang.Double> {
		public java.lang.Double next() {
			long trimmed = Math.round(r.nextDouble() * 100);
			return ((double) trimmed) / 100;
		}
	}
}
```
``` java
package generatora;

public class GeneratorTest {
	public static int size = 10;

	public static void test(Class<?> surroundingClass) {
		for (Class<?> type : surroundingClass.getClasses()) {
			System.out.print(type.getSimpleName() + ": ");
			try {
				Generator<?> g = (Generator<?>) type.newInstance();
				for (int i = 0; i < size; i++)
					System.out.printf(g.next() + " ");
				System.out.println();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static void main(String[] args) {

		System.out.println("Counting : ");
		test(CountingGenerator.class);

		System.out.println("\nRandom : ");
		test(RandomGenerator.class);
	}

}
```		
	要由產生器來產生array需要再另外建立一個工具
``` java
package generatora;

public class Generated {
	// Fill an existing array:
	public static <T> T[] array(T[] a, Generator<T> gen) {
		return new CollectionData<T>(gen, a.length).toArray(a);
	}

	// Create a new array:
	@SuppressWarnings("unchecked")
	public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
		T[] a = (T[]) java.lang.reflect.Array.newInstance(type, size);
		return new CollectionData<T>(gen, size).toArray(a);
	}
}
```
	
``` java
package generatora;

import java.util.Arrays;

public class GeneratedTest {

	public static void main(String[] args) {
		Integer[] a = { 9, 8, 7, 6 };
		System.out.println(Arrays.toString(a));
		a = Generated.array(a, new CountingGenerator.Integer());
		System.out.println(Arrays.toString(a));
		Integer[] b = Generated.array(Integer.class,
				new CountingGenerator.Integer(), 20);
		System.out.println(Arrays.toString(b));
	}

}
```	
	透過這些我們就能建立一些有變化的測試資料
	
### Arrays 工具
	java.util中提供的 Arrays class之中提供了許多方法用以處理array
- array的複製


	這個方法並不再Arrays中，但也是一個相當有用的方法
	語法為 arraycopy(被copy的array, 起始位置, 要貼上的array, 起始位置,長度)
``` java
package Arrays;
import java.util.Arrays;

public class Arraycopy {

	public static void main(String[] args) {
		int[] i = new int[7]; 
		int[] j = new int[10];
		Arrays.fill(i, 10);
		Arrays.fill(j, 19);
		System.out.println("i = " + Arrays.toString(i));
		System.out.println("j = " + Arrays.toString(j));
		System.arraycopy(i, 0, j, 0, i.length);
		System.out.println("j = " + Arrays.toString(j));
	}
}
```
- array的比較

	使用equals()來檢驗兩個arrays是否相等

``` java
package Arrays;
import java.util.Arrays;


public class ComparingArrays {

	public static void main(String[] args) {
		int[] a1 = new int[10];
		int[] a2 = new int[10];
		Arrays.fill(a1, 50);
		Arrays.fill(a2, 50);
		System.out.println(Arrays.equals(a1, a2));
		String[] s1 = new String[4];
		String[] s2 = new String[4];
		Arrays.fill(s1, "Yo!");
		Arrays.fill(s2, "Yo!");
		System.out.println(Arrays.equals(s1, s2));
	}
}
```
	
- array元素的比較
	

	藉由實作java.lang.Comparable interface 讓某個class具有比較
	的能力
	
``` java
package CompType;

import generatora.Generated;
import generatora.Generator;

import java.util.Arrays;
import java.util.Random;

public class CompType implements Comparable<CompType> {
	int i;
	int j;
	private static int count = 1;

	public CompType(int n1, int n2) {
		i = n1;
		j = n2;
	}

	public String toString() {
		String result = "[i = " + i + ", j = " + j + "]";
		if (count++ % 3 == 0)
			result += "\n";
		return result;
	}

	public int compareTo(CompType rv) {
		return (i < rv.i ? -1 : (i == rv.i ? 0 : 1));
	}

	private static Random r = new Random(47);

	public static Generator<CompType> generator() {
		return new Generator<CompType>() {
			public CompType next() {
				return new CompType(r.nextInt(100), r.nextInt(100));
			}
		};
	}

	public static void main(String[] args) {
		CompType[] a = Generated.array(new CompType[12], generator());
		System.out.println("before sorting:");
		System.out.println(Arrays.toString(a));
		Arrays.sort(a);
		System.out.println("after sorting:");
		System.out.println(Arrays.toString(a));
	}
}
```

- array的排序
 
	使用內建的sorting函式來進行排序(objects array需要有實作Comparator)
	Java的排序演算法，會針對你所排序的型別來進行最佳化，面對基本型別是Quicksort
	面對物件則採用Stable merge sort，所以我們不太需要擔心排序的效率問題。

``` java
package CompType;

import java.util.Arrays;
import java.util.Collections;

import generatora.Generated;
import generatora.RandomGenerator;

public class StringSorting {
	public static void main(String[] args) {
		String[] sa = Generated.array(new String[20],
				new RandomGenerator.String(5));
		System.out.println("Before sort: " + Arrays.toString(sa));
		Arrays.sort(sa);
		System.out.println("After sort: " + Arrays.toString(sa));
		Arrays.sort(sa, Collections.reverseOrder());
		System.out.println("Reverse sort: " + Arrays.toString(sa));
		Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
		System.out.println("Case-insensitive sort: " + Arrays.toString(sa));
	}
}

```

- 在已排序的array中進行搜尋

	在array排序完畢後，我們可以利用Arrays.binarySearch() 快速搜尋其中某個元素
	但千萬別用在未經排序的array上，其結果無法預測。
	
``` java
package CompType;

import generatora.Generated;
import generatora.Generator;
import generatora.RandomGenerator;
import java.util.Arrays;

class ConvertTo {

	public static int[] primitive(Integer[] in) {
		int[] result = new int[in.length];
		for (int i = 0; i < in.length; i++)
			result[i] = in[i];
		return result;
	}

}

public class ArraySearching {

	public static void main(String[] args) {
		Generator<Integer> gen = new RandomGenerator.Integer(1000);
		int[] a = ConvertTo.primitive(Generated.array(new Integer[25], gen));
		System.out.println("Unsorted array: " + Arrays.toString(a));
		Arrays.sort(a);
		System.out.println("Sorted array: " + Arrays.toString(a));
		while (true) {
			int r = gen.next();
			int location = Arrays.binarySearch(a, r);
			if (location >= 0) {
				System.out.println("Location of " + r + " is " + location
						+ ", a[" + location + "] = " + a[location]);
				break;
			}
		}
	}
}

```

