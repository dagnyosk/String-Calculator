package is.ru.stringcalculator;

public class Calculator{
	public static int add(String text){
		if(text.equals(""))
			return 0;
		else{
			if(text.contains(",")){
				String numbers[] = text.split(",|n");
				return sum(numbers);
			}
			return 1;
		}
		
	}
	
	private static int toInt(String number){
		return Integer.parseInt(number);
	}
	
	private static int sum(String [] numbers){
		int total = 0;
		String negNumbers = "";
			for(String number : numbers){
				int i = toInt(number);
				if (i < 0){
					negNumbers += number + ",";
				}
				else {
					if ( i < 1000) {
						total += i;
					}
				}
			}
			if (negNumbers.isEmpty()) 
				return total;
			else {
				throw new IllegalArgumentException ("Negatives not allowed: " + negNumbers.substring(0, negNumbers.length() -1));
			}
	}
	
}