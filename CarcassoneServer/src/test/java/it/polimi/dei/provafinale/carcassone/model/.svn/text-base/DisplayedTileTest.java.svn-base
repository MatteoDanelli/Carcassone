package it.polimi.dei.provafinale.carcassone.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DisplayedTileTest {

	private DisplayedTile tile1;
	private DisplayedTile tile2;
	private DisplayedTile tile3;
	private DisplayedTile tile3WithFlag;
	
	@Before
	public void setUp() throws Exception {
		tile1 = new DisplayedTile("N=N S=C W=S E=S NS=0 NE=0 NW=0 WE=1 SE=0 SW=0");
		tile2 = new DisplayedTile("N=S S=S+G W=S E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0");
		tile3 = new DisplayedTile("N=C S=S W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0");
		tile3WithFlag = new DisplayedTile("N=C+B S=S W=C E=S NS=0 NE=0 NW=1 WE=0 SE=1 SW=0");
	}

	@Test
	public void testToInternalRepresentation() {
		//tile1
		String aux[] = tile1.toInternalRepresentation();
		assertEquals("Error on internal representation", "             ", aux[0]);
		assertEquals("Error on internal representation", "             ", aux[1]);
		assertEquals("Error on internal representation", "S1         S1", aux[2]);
		assertEquals("Error on internal representation", "             ", aux[3]);
		assertEquals("Error on internal representation", "      C1     ", aux[4]);
		//tile2
		aux = tile2.toInternalRepresentation();
		assertEquals("Error on internal representation", "      S1     ", aux[0]);
		assertEquals("Error on internal representation", "             ", aux[1]);
		assertEquals("Error on internal representation", "S1         S2", aux[2]);
		assertEquals("Error on internal representation", "             ", aux[3]);
		assertEquals("Error on internal representation", "      S2(G)  ", aux[4]);
		//tile3
		aux = tile3WithFlag.toInternalRepresentation();
		assertEquals("Error on internal representation", "      C1(B)  ", aux[0]);
		assertEquals("Error on internal representation", "             ", aux[1]);
		assertEquals("Error on internal representation", "C1         S1", aux[2]);
		assertEquals("Error on internal representation", "             ", aux[3]);
		assertEquals("Error on internal representation", "      S1     ", aux[4]);
	}

	@Test
	public void testToTextualRepresentation() {
		//tile1
		String s1 = "+#############+\n#             #\n#             #\n#S1         S1#\n#             #\n#      C1     #\n+#############+\n";
		assertEquals("Error in textual representation", s1, tile1.toTextualRepresentation());
		
		//tile2
		String s2 = "+#############+\n#      S1     #\n#             #\n#S1         S2#\n#             #\n#      S2(G)  #\n+#############+\n";
		assertEquals("Error in textual representation", s2,tile2.toTextualRepresentation());
		
		//tile3
		String s3 = "+#############+\n#      C1     #\n#             #\n#C1         S1#\n#             #\n#      S1     #\n+#############+\n";
		assertEquals("Error in textual representation", s3,tile3.toTextualRepresentation());
	}

}
