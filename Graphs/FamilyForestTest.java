package Graphs;

import org.junit.Test;

import static org.junit.Assert.*;

public class FamilyForestTest {
    @Test
    public void Test0() {
        FamilyForest f = new FamilyForest();
        String[] people = new String[]{"Ricardo", "Sean", "Maya", "Ishaan", "Chia-Lin"};

        for (String s : people) {
            f.make_family(s);
            // each person should now be their own family representative
            assertEquals(f.find(s), s);
        }

        String rep = f.union("Sean", "Ishaan");
        assertTrue(rep != null);
        assertTrue(rep.equals("Sean") || rep.equals("Ishaan"));

        f.union("Maya", "Ishaan");
        f.union("Ricardo", "Chia-Lin");

        // families: {"Sean", "Ishaan", "Maya"}, {"Ricardo", "Chia-Lin"}

        assertEquals(f.find("Ishaan"), f.find("Sean"));
        assertEquals(f.find("Maya"), f.find("Sean"));

        assertEquals(f.find("Ricardo"), f.find("Chia-Lin"));
        assertNotEquals(f.find("Sean"), f.find("Chia-Lin"));
    }
}