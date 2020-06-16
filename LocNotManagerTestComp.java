import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.Rule;

public class LocNotManagerTestComp {

	@SuppressWarnings("deprecation")
	@Rule
	public Timeout globalTimeout = new Timeout(1000);

	@Test
	public void testRetrieve() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			bst.insert(12, 12);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			bst.insert(100, 100);
			bst.update(55);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testEmpty() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			bst.insert(100, 100);
			boolean f = bst.empty();
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testFull() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			bst.insert(100, 100);
			boolean f = bst.full();
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testClear() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			bst.insert(100, 100);
			bst.clear();
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testFind() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			bst.insert(100, 100);
			boolean f = bst.find(33);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testNbKeyComp() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			bst.insert(100, 100);
			int n = bst.nbKeyComp(100);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testInsert() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			boolean f = bst.insert(100, 100);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testRemove() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			bst.insert(100, 100);
			boolean f = bst.remove(20);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAll() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			bst.insert(10, 10);
			List<Pair<Integer, Integer>> l = bst.getAll();
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetRange() {
		try {
			Map<Integer, Integer> bst = new BST<Integer, Integer>();
			List<Pair<Integer, Integer>> l = bst.getRange(10, 2);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testAddNot() {
		try {
			Map<Double, Map<Double, LocNot>> nots = new BST<Double, Map<Double, LocNot>>();
			LocNotManager.addNot(nots, new LocNot("Buy water", 24.75365, 46.62900, 0, 0));
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testDelNot() {
		try {
			Map<Double, Map<Double, LocNot>> nots = new BST<Double, Map<Double, LocNot>>();
			LocNotManager.addNot(nots, new LocNot("Buy water", 24.75365, 46.62900, 0, 0));
			LocNotManager.delNot(nots, 24.72294, 46.61838);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		try {
			Map<Double, Map<Double, LocNot>> nots = new BST<Double, Map<Double, LocNot>>();
			LocNotManager.addNot(nots, new LocNot("Buy water", 24.75365, 46.62900, 0, 0));
			LocNotManager.save("output.txt", nots);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testLoad() {
		try {
			Map<Double, Map<Double, LocNot>> nots = LocNotManager.load("input.txt");
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAllNots() {
		try {
			Map<Double, Map<Double, LocNot>> nots = new BST<Double, Map<Double, LocNot>>();
			LocNotManager.addNot(nots, new LocNot("Buy water", 24.75365, 46.62900, 0, 0));
			List<LocNot> l = LocNotManager.getAllNots(nots);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNotsAt() {
		try {
			BST<Double, Map<Double, LocNot>> nots = new BST<Double, Map<Double, LocNot>>();
			LocNotManager.addNot(nots, new LocNot("Buy bottled water", 24.75367, 46.62902, 0, 0));
			List<LocNot> l = LocNotManager.getNotsAt(nots, 24.72330, 46.63650, 100000);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testGetActiveNotsAt() {
		try {
			BST<Double, Map<Double, LocNot>> nots = new BST<Double, Map<Double, LocNot>>();
			LocNotManager.addNot(nots, new LocNot("Buy bottled water", 24.75367, 46.62902, 0, 0));
			List<LocNot> l = LocNotManager.getActiveNotsAt(nots, 24.72330, 46.63650, 100000);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testLocNotManagerPerform() {
		try {
			BST<Double, Map<Double, LocNot>> nots = new BST<Double, Map<Double, LocNot>>();
			LocNotManager.perform(nots, 24.72330, 46.63650, 100000);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testIndex() {
		try {
			Map<Double, Map<Double, LocNot>> nots = new BST<Double, Map<Double, LocNot>>();
			LocNotManager.addNot(nots, new LocNot("Buy bottled water", 24.75367, 46.62902, 0, 0));
			Map<String, List<LocNot>> ind = LocNotManager.index(nots);
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteNots() {
		try {
			Map<Double, Map<Double, LocNot>> nots = new BST<Double, Map<Double, LocNot>>();
			LocNotManager.addNot(nots, new LocNot("Buy bottled water", 24.75367, 46.62902, 0, 0));
			LocNotManager.delNots(nots, "Buy");
		} catch (Exception e) {
			fail("Your program threw an exception.");
			e.printStackTrace();
		}
	}
}
