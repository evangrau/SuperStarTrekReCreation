import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;

// import org.junit.runner.Description;
// import org.junit.rules.TestRule;
// import org.junit.rules.TestWatcher;
// import org.junit.FixMethodOrder;
// import org.junit.runners.MethodSorters;

import java.util.ArrayList;

/**
 * Unit tests
 */
// @FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSST {
    SST game = new SST();

    @Test
    public void test1() {
        assertEquals(game.getQuadSize(), 8);
        assertEquals(game.getSectSize(), 10);
    }
}