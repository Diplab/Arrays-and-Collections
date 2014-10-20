package container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class StringAddress {
    private String address;

    public StringAddress(String address) {
	super();
	this.address = address;
    }

    @Override
    public String toString() {
	return super.toString() + " " + address;
    }
}

/**
 * show Collections.nCopies (immutable), and Collections.fill (replace elements
 * in List)
 * 
 * @author timmy00274672
 * @see Collections
 * @see List
 */
public class FillingList {

    public static void main(String[] args) {
	// Returns an immutable list consisting of n copies of the specified
	// object.
	List<StringAddress> addresses =
		Collections.nCopies(3, new StringAddress("hello"));
	System.out.println(addresses);
	try {
	    addresses.remove(0);
	} catch (UnsupportedOperationException exception) {
	    exception.printStackTrace();
	}

	addresses = new ArrayList<>(addresses);
	Collections.fill(addresses, new StringAddress("world"));
	System.out.println(addresses);
    }

}
