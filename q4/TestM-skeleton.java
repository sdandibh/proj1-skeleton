import org.junit.*;
import static org.junit.Assert.*;

import java.io.PrintStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

public class TestM {

    /* add your test code here */
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    // Test set 1: Node coverage but not edge coverage
    @Test
    public void testNodeCoverage() {
        // TR: 1-2-3-5-8-9-11
        // This test covers all nodes but misses some edges
        M obj = new M();
        obj.m("a", 0);
        assertEquals("a\n", outContent.toString());
    }

    // Test set 2: Edge coverage but not edge-pair coverage
    @Test
    public void testEdgeCoverage1() {
        // TR: 1-3-4-8-10-11
        M obj = new M();
        obj.m("", 1);
        assertEquals("zero\n", outContent.toString());
    }

    @Test
    public void testEdgeCoverage2() {
        // TR: 1-2-3-5-8-9-11
        M obj = new M();
        obj.m("a", 0);
        assertEquals("a\n", outContent.toString());
    }

    @Test
    public void testEdgeCoverage3() {
        // TR: 1-3-6-8-9-11
        M obj = new M();
        obj.m("aa", 1);
        assertEquals("a\n", outContent.toString());
    }

    @Test
    public void testEdgeCoverage4() {
        // TR: 1-3-7-8-9-11
        M obj = new M();
        obj.m("aaa", 1);
        assertEquals("b\n", outContent.toString());
    }

    // Test set 3: Edge-pair coverage but not prime path coverage
    @Test
    public void testEdgePairCoverage1() {
        // TR: 1-3-4-8-10-11
        M obj = new M();
        obj.m("", 1);
        assertEquals("zero\n", outContent.toString());
    }

    @Test
    public void testEdgePairCoverage2() {
        // TR: 1-2-3-5-8-9-11
        M obj = new M();
        obj.m("a", 0);
        assertEquals("a\n", outContent.toString());
    }

    @Test
    public void testEdgePairCoverage3() {
        // TR: 1-3-6-8-9-11
        M obj = new M();
        obj.m("aa", 1);
        assertEquals("a\n", outContent.toString());
    }

    @Test
    public void testEdgePairCoverage4() {
        // TR: 1-3-7-8-9-11
        M obj = new M();
        obj.m("aaa", 1);
        assertEquals("b\n", outContent.toString());
    }

    // Test set 4: Prime path coverage
    // Note: Prime path coverage is not achievable due to infeasible paths
    @Test
    public void testPrimePathCoverage1() {
        // TR: 1-3-4-8-10-11
        M obj = new M();
        obj.m("", 1);
        assertEquals("zero\n", outContent.toString());
    }

    @Test
    public void testPrimePathCoverage2() {
        // TR: 1-2-3-5-8-9-11
        M obj = new M();
        obj.m("a", 0);
        assertEquals("a\n", outContent.toString());
    }

    @Test
    public void testPrimePathCoverage3() {
        // TR: 1-3-6-8-9-11
        M obj = new M();
        obj.m("aa", 1);
        assertEquals("a\n", outContent.toString());
    }

    @Test
    public void testPrimePathCoverage4() {
        // TR: 1-3-7-8-9-11
        M obj = new M();
        obj.m("aaa", 1);
        assertEquals("b\n", outContent.toString());
    }

}

class M {
  public static void main(String [] argv){
    M obj = new M();
    if (argv.length > 0)
      obj.m(argv[0], argv.length);
  }

  public void m(String arg, int i) {
    int q = 1;
    A o = null;
    Impossible nothing = new Impossible();
    if (i == 0)
      q = 4;
    q++;
    switch (arg.length()) {
      case 0: q /= 2; break;
      case 1: o = new A(); new B(); q = 25; break;
      case 2: o = new A(); q = q * 100;
      default: o = new B(); break; 
    }
    if (arg.length() > 0) {
      o.m();
    } else {
      System.out.println("zero");
    }
    nothing.happened();
  }
}

class A {
  public void m() {
    System.out.println("a");
  }
}

class B extends A {
  public void m() {
    System.out.println("b");
  }
}

class Impossible{
  public void happened() {
    // "2b||!2b?", whatever the answer nothing happens here
  }
}

