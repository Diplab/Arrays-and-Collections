package util;

import java.util.Arrays;

/**
 * @author timmy00274672
 * 
 */
public class CountingGenerator {
    public static class BooleanGenerator implements Generator<Boolean> {
	private Boolean value = false;

	@Override
	public Boolean next() {
	    value = !value;
	    return value;
	}
    }

    public static class CharGenerator implements Generator<Character> {
	private static char[] chars = ("abcdefghijklmnopqrstuvwxyz"
		+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

	private int cur = -1;

	@Override
	public Character next() {
	    cur++;
	    return chars[cur];
	}
    }

    public static class StringGenerator implements Generator<String> {
	private int length;
	private CharGenerator charGenerator = new CharGenerator();

	public StringGenerator() {
	    this(7);
	}

	public StringGenerator(int length) {
	    super();
	    this.length = length;
	}

	public int getLength() {
	    return length;
	}

	public void setLength(int length) {
	    this.length = length;
	}

	@Override
	public String next() {
	    char returnCharArray[] = new char[length];
	    for (int i = 0; i < returnCharArray.length; i++) {
		returnCharArray[i] = charGenerator.next();
	    }
	    return new String(returnCharArray);
	}
    }

    public static void main(String[] args) throws InstantiationException,
	    IllegalAccessException {
	testGenerators(CountingGenerator.class);

    }

    public static void testGenerators(Class<CountingGenerator> class1) throws InstantiationException,
	    IllegalAccessException {
	for (Class<?> innerClass : class1.getClasses()) {
	    if (!Arrays.asList(innerClass.getInterfaces()).contains(
		    Generator.class))
		continue;

	    Generator<?> generator = (Generator<?>) innerClass.newInstance();
	    for (int i = 0; i < 3; i++) {
		System.out.println(String.format("%s \t", generator.next()));
	    }
	}
    }

}
