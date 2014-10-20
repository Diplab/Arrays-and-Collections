package container;

import java.util.LinkedHashSet;
import java.util.Set;

import util.CollectionData;
import util.Generator;
import util.RandomGenerators;

public class CollectionDataTest {
    public static void main(String[] args) {
	CollectionData<String> list =
		CollectionData.list(new Generator<String>() {
		    String[] foundationStrings = "strang guy stands here."
			    .split(" ");
		    private int index = 0;

		    @Override
		    public String next() {
			if (index == foundationStrings.length - 1)
			    return null;
			return foundationStrings[index++];
		    }
		}, 15);
	Set<String> set = new LinkedHashSet<>(list);
	System.out.println(set);
	
	list = CollectionData.list(new RandomGenerators.StringGenerator(3), 5);
	set = new LinkedHashSet<>(list);
	System.out.println(set);
    }
}
