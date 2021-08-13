package stubs;

import static org.junit.Assert.assertEquals;

import org.apache.crunch.Pair;
import org.apache.crunch.impl.mem.emit.InMemoryEmitter;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.junit.Test;

public class DoFNUnitTest {

	@Test
	public void testPoliceCostParseDoFn() {
		InMemoryEmitter<Pair<Integer, Double>> emitter = new InMemoryEmitter<Pair<Integer, Double>>();
		new PoliceCostParseDoFN().process("5,11.24", emitter);
		assertEquals(ImmutableList.of(new Pair<Integer, Double>(5, 11.24)), emitter.getOutput());
	}
}
