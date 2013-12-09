package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DisplayedMapTest {

	private DisplayedMap testMap;
	private Position p00, p10, p20, p11, p21, p0_1;
	private DisplayedTile testDisplayedTile1, testDisplayedTile3, testDisplayedTile4;
	
	@Before
	public void setUp() throws Exception {
		testMap = new DisplayedMap();
		p00 = new Position (0,0); 
		p10 = new Position (1,0); 
		p20 = new Position (2,0); 
		p0_1 = new Position (0,-1); 
		p11 = new Position (1,1); 
		p21 = new Position (2, 1); 
		testDisplayedTile1 = new DisplayedTile ("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
		testDisplayedTile3 = new DisplayedTile("N=C S=C W=N E=N NS=0 NE=0 NW=0 WE=0 SE=0 SW=0");
		testDisplayedTile4 = new DisplayedTile("N=C S=S W=C+R E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0");
	}

	@Test
	public final void testToTextualRepresentation() {
		testMap.putTileInPosition(p00, testDisplayedTile1);
		testMap.putTileInPosition(p0_1, testDisplayedTile1);
		testMap.putTileInPosition(p10, testDisplayedTile4);
		testMap.putTileInPosition(p20, testDisplayedTile4);
		testMap.putTileInPosition(p11, testDisplayedTile3);
		testMap.putTileInPosition(p21, testDisplayedTile3);
		String expectedRepresentation = "                            +.............+.............+                           \n" +
										"                            .             .             .                           \n" +
										"                            .             .             .                           \n" +
										"                            . (1,2)       . (2,2)       .                           \n" +
										"                            .             .             .                           \n" +
										"                            .             .             .                           \n" +
										"              +.............+#############+#############+.............+             \n" +
										"              .             #      C1     .      C1     #             .             \n" +
										"              .             #             .             #             .             \n" +
										"              . (0,1)       #             .             # (3,1)       .             \n" +
										"              .             #             .             #             .             \n" +
										"              .             #      C2     .      C2     #             .             \n" +
										"+.............+#############+.............+.............+.............+             \n" +
										".             #             .      C1     .      C1     #             .             \n" +
										".             #             .             .             #             .             \n" +
										". (-1,0)      #S1         S1.C1(R)      S1.C1(R)      S1# (3,0)       .             \n" +
										".             #             .             .             #             .             \n" +
										".             #      C1     .      S1     .      S1     #             .             \n" +
										"+.............+.............+#############+#############+.............+             \n" +
										".             #             #             .             .                           \n" +
										".             #             #             .             .                           \n" +
										". (-1,-1)     #S1         S1# (1,-1)      . (2,-1)      .                           \n" +
										".             #             #             .             .                           \n" +
										".             #      C1     #             .             .                           \n" +
										"+.............+#############+.............+.............+                           \n" +
										"              .             .                                                       \n" +
										"              .             .                                                       \n" +
										"              . (0,-2)      .                                                       \n" +
										"              .             .                                                       \n" +
										"              .             .                                                       \n" +
										"              +.............+                                                       ";
		assertEquals(expectedRepresentation, testMap.toTextualRepresentation());
	}

}
