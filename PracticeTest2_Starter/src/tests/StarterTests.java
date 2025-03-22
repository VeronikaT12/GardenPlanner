package test;

import model.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class GardenTest {

	@Test
	public void testPlotToStringToggle() {
		Plot p = new Plot("Tomatoes", 5, 6);
		assertEquals("A plot of 30 square feet (5' by 6') for Tomatoes", p.toString());
		p.toggleUnit();
		assertEquals("A plot of 2.79 square meters (1.52 m by 1.83 m) for Tomatoes", p.toString());
		p.toggleUnit();
		assertEquals("A plot of 30 square feet (5' by 6') for Tomatoes", p.toString());
	}

	@Test
	public void testPlotEquality() {
		Plot a = new Plot("Carrots", 4, 5);
		Plot b = new Plot("Carrots", 10, 2);
		Plot c = new Plot("Lettuce", 4, 5);
		assertEquals(a, b);
		assertNotEquals(a, c);
	}

	@Test
	public void testSectionAddAndReject() {
		GardenSection section = new GardenSection(100);
		try {
			section.addPlot("Basil", 5, 5);
			section.addPlot("Lettuce", 3, 5);
			section.addPlot("Onion", 6, 8);
			assertEquals("Garden section used: 88 sq ft (12 sq ft left): [Basil: 25 sq ft (5' by 5'), Lettuce: 15 sq ft (3' by 5'), Onion: 48 sq ft (6' by 8')]", section.toString());
		} catch (OvercrowdedGardenSpaceException e) {
			fail("Unexpected exception");
		}

		try {
			section.addPlot("Pumpkin", 4, 4);
			fail("Expected OvercrowdedGardenSpaceException not thrown");
		} catch (OvercrowdedGardenSpaceException e) {
			assertEquals("Garden section used: 88 sq ft (12 sq ft left): [Basil: 25 sq ft (5' by 5'), Lettuce: 15 sq ft (3' by 5'), Onion: 48 sq ft (6' by 8')]", section.toString());
		}
	}

	@Test
	public void testGardenPlanToStringAndCopy() {
		GardenPlan plan = new GardenPlan(5);
		GardenSection s1 = new GardenSection(100);
		GardenSection s2 = new GardenSection(100);
		try {
			s1.addPlot("Rose", 5, 5);
			s2.addPlot("Lavender", 6, 6);
		} catch (OvercrowdedGardenSpaceException e) {
			fail();
		}
		plan.addSection(s1);
		plan.addSection(s2);
		assertEquals("40.0% of garden planned (2 of 5 sections)", plan.toString());

		GardenPlan plan2 = new GardenPlan(plan);
		GardenSection[] sections = plan2.getSections();
		assertEquals(2, sections.length);
		assertNotSame(sections[0], s1);
		assertEquals(sections[0], s1);
	}
}