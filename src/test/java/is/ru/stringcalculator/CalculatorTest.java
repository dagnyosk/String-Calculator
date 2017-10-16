package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class CalculatorTest{
	@Test
	public void testEmptyString(){
		assertEquals(0, Calculator.add(""));
	}
	
	@Test
	public void testOneNumber(){
		assertEquals(1, Calculator.add("1"));
	}
	
	@Test
	public void testTwoNumbers(){
		assertEquals(3, Calculator.add("1,2"));
	}
	
	@Test
	public void testMultipleNumbers(){
		assertEquals(6, Calculator.add("1,2,3"));
	}
	@Test
	public void testTwoLinesMultipleNumbers(){
		assertEquals(6, Calculator.add("1n2,3"));
	}
	@Test
	public void testNumbersBigger(){
		assertEquals(2, Calculator.add("1001,2"));
	}
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testThrowExceptionsNegativeMultiple(){
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("Negatives not allowed: -4,-5");
		Calculator.add("2,-4,3,-5");
	}

}
