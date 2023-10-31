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

    Ship ship = new Ship(1,1,1,1);

    @Test
    public void test1() {
        assertTrue(ship.getXQuad() == 1 && ship.getYQuad() == 1 && ship.getXSect() == 1 && ship.getYSect() == 1);
    }

    @Test
    public void test2() {
        ship.move(1,3,5,3);
        assertTrue(ship.getXQuad() == 2 && ship.getYQuad() == 4 && ship.getXSect() == 6 && ship.getYSect() == 4);
    }
}