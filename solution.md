Build
Compile:
#!/bin/bash
javac src/test/resources/junit-4.12.jar -d classes src/test/java/is/ru/stingcalculator/calculator.java
Clean:
#!/bin/bash
rm -r classes/*
Compile tests:
#!/bin/bash
javac -cp "src/test/resources/junit-4.12.jar:/classes" src/test/java/is/ru/stringcalculator/*.java -d classesUnit Test:
Unit test:
#!/bin/bash
java -cp "classes/:src/test/resources/junit-4.12.jar:src/test/resources/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore is.ru.stringcalculator.CalculatorTest
Build and Test:
#!/bin/bash
./bin/clean.sh
./bin/compile.sh
./bin/compile_tests.sh
./bin/unit_test.sh

TDD
Test Empty
CalculatorTest.java
@Test
public void testEmptyString() {
	assertEquals(0, Calculator.add(""));
}

Calculator.java
public static int add(String test){
	return 0;
}

Test One Number
CalculatorTest.java
@Test
public void testOneNumber(){
	assertEquals(1, Calculator.add("1"));
}

Calculator.java
public static int add(String text){
	if(text.equals("")){
		return 0;
   	}
    	else
		return 1;
}

Test Two Numbers
CalculatorTest.java
@Test
public void testTwoStrings(){
	assertEquals(3, Calculator.add("1,2"));
}

Calculator.java
if(text.equals("")){
	return 0;
}
else{
	if(text.contains(",")){
		String[] numbers = text.split(",");
		return toInt(numbers[0]) + toInt(numbers[1]);
	}
	return 1;
}

Breyting
private static int toInt(String number){
	return Integer.parseInt(number);
}

Test Multiple Numbers
CalculatorTest.java
@Test
public void testMultipleNumbers(){
	assertEquals(6, Calculator.add("1,2,3"));
}

Calculator.java
else{
	if(text.contains(",")){
		String numbers[] = text.split(",");
		int total = 0;
		for(String number : numbers){
			total += toInt(number);
		}
		return total;
	}
	return 1;
}

Breyting
private int sum(String [] numbers){
	int total = 0;
	for(String number : numbers){
		total += toInt(number);
	}
	return total;
}

New Lines
CalculatorTest.java
@Test
public void testTwoLinesMultipleNumbers(){
	assertEquals(6, Calculator.add(“1n2,3”));
}

Calculator.java
else{
	if(text.contains(",")){
		String numbers[] = text.split(",|n");
		return sum(numbers);
	}
	return 1;
}

Throw exceptions when negative
CalculatorTest.java
@Rule
public ExpectedException thrown = ExpectedException.none();
@Test
public void testThrowExceptionsNegativeMultiple(){
	thrown.expect(IllegalArgumentException.class);
	thrown.expectMessage("Negatives not allowed: -4,-5");
	Calculator.add("2,-4,3,-5");
}

Calculator.java
private static int sum(String [] numbers){
	int total = 0;
	String negNumbers = "";
		for(String number : numbers){
			int i = toInt(number);
			if (i < 0)
				negNumbers += number + ",";
		}
		if (negNumbers.isEmpty()) 
			return total;
		else {
			throw new IllegalArgumentException ("Negatives not allowed: " + negNumbers.substring(0, negNumbers.length() -1));
		}
}


Numbers bigger than 1000
CalculatorTest.java
@Test
public void testNumbersBigger(){
	assertEquals(2, Calculator.add("1001,2"));
}

Calculator.java
private static int sum(String [] numbers){
	int total = 0;
		for(String number : numbers){
			if (toInt(number) < 1000) {
				total += toInt(number);
			}
		}
		return total;
}
