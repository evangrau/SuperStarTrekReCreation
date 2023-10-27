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
public class TestShip {

    @Test
    public void test1() {
        Ship ship = new Ship(1,1,1,1);

        assertTrue(ship.getXQuad() == 1 && ship.getYQuad() == 1 && ship.getXSect() == 1 && ship.getYSect() == 1);
    }
}