package util;

import java.util.Random;

/**
 * 
 * @author timmy00274672
 * 
 */
public class RandomGenerators {

    private static Random random = new Random();

    public static Random getRandom() {
        return random;
    }

    public static void setRandom(Random random) {
        RandomGenerators.random = random;
    }

    public static class BooleanGenerator implements Generator<Boolean> {

	@Override
	public Boolean next() {
	    return random.nextBoolean();
	}

    }

    public static class CharGenerator implements Generator<Character> {
	private static char[] chars = ("abcdefghijklmnopqrstuvwxyz"
		+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

	@Override
	public Character next() {
	    return chars[random.nextInt(chars.length)];
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

    /**
     * @param args
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void main(String[] args) throws InstantiationException,
	    IllegalAccessException {
	CountingGenerator.testGenerators(RandomGenerators.class);

    }

}
