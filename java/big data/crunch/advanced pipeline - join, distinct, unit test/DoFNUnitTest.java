package stubs;

import static org.junit.Assert.assertEquals;

import org.apache.crunch.Pair;
import org.apache.crunch.impl.mem.emit.InMemoryEmitter;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.junit.Test;

import model.PoliceCall;

public class DoFNUnitTest {

	@Test
	public void testPoliceCostParseDoFn() {
		InMemoryEmitter<Pair<Integer, Double>> emitter = new InMemoryEmitter<Pair<Integer, Double>>();
		new PoliceCostParseDoFN().process("5,11.24", emitter);
		assertEquals(ImmutableList.of(new Pair<Integer, Double>(5, 11.24)), emitter.getOutput());
	}
	
	@Test
	public void testPolicePriorityParseDoFn() {
		InMemoryEmitter<Pair<Integer, PoliceCall>> emitter = new InMemoryEmitter<Pair<Integer, PoliceCall>>();
		PoliceCall testCall = new PoliceCall(3, "SUSPV", "RP", "RS", 173011L, 182946L, 182950L, 183107L, "OK");
		PoliceCall testCall2 = new PoliceCall(3, "SUSPV", "RP", "RS", 173011L, 182946L, 182950L, 183107L, "BAD");
		new PolicePriorityParseDoFN().process(testCall, emitter);
		assertEquals(ImmutableList.of(new Pair<Integer, PoliceCall>(3, testCall)), emitter.getOutput());
	}
	
	@Test
	public void testPoliceJurisdictionAndDispatchAreaParseDoFn() {
		InMemoryEmitter<Pair<String, String>> emitter = new InMemoryEmitter<Pair<String, String>>();
		PoliceCall testCall = new PoliceCall(3, "SUSPV", "RP", "RS", 173011L, 182946L, 182950L, 183107L, "OK");
		new PoliceJurisdictionAndDispatchAreaParseDoFN().process(testCall, emitter);
		assertEquals(ImmutableList.of(new Pair<String, String>("RP", "RS")), emitter.getOutput());
	}
}
