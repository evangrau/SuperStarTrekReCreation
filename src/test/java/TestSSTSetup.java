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
public class TestSSTSetup {

    SSTSetup s = new SSTSetup();
    SSTSetup.Klingon k = s.new Klingon(1, 1, 1, 1);

    @Test
    public void test1() {
        assertTrue(k.hp == 200 && k.energy == 400);
    }

    @Test
    public void test2() {
        assertTrue(k.xquad == 1 && k.yquad == 1 && k.xsec == 1 && k.ysec == 1);
    }
}