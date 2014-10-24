package util.map;

import util.CountingMapData;

public class SlowMapTest extends AbstractMapTest {
    
    @Override
    public void setUp() throws Exception {
	super.setUp();
	map = new SlowMap<>();
	emptyMap = new SlowMap<>();
	map.putAll(new CountingMapData(25));
    }
}
