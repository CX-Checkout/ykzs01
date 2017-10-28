package befaster.solutions;

import java.util.HashMap;
import java.util.Map;

public class Checkout {
	
	public static int checkout(String basket) {
		try {
			return computeBasketValue(basket);
		}
		catch(IllegalBasketException e)
		{
			return -1;
		}
	}


	private static int computeBasketValue(String basket) throws IllegalBasketException {
		
		Map<Character, Integer> prices = new HashMap<>();
		prices.put('A', 50);
		prices.put('B', 30);
		prices.put('C', 20);
		prices.put('D', 15);
		prices.put('E', 40);
		prices.put('F', 10);


		char[] skus = basket.toCharArray();
			
		
		// Check all items
		for(char sku : skus)
		{
			if(!prices.containsKey(sku))
				throw new IllegalBasketException();
		}
		
		// Count all items
		int countA = 0;
		int countB = 0;
		int countC = 0;
		int countD = 0;
		int countE = 0;
		int countF = 0;
		for(char sku : skus)
		{
			if(sku == 'A')
			{
				countA++;
			}
			if(sku == 'B')
			{
				countB++;
			}
			if(sku == 'C')
			{
				countC++;
			}
			if(sku == 'D')
			{
				countD++;
			}
			if(sku == 'E')
			{
				countE++;
			}
			if(sku == 'F')
			{
				countF++;
			}
		}
		
		
		// Add the appropriate values 
		int totalPrice = 0;
		
		while(countE >= 2)
		{
			totalPrice += 2 * prices.get('E');
			if(countB > 0)
			{
				countB -= 1;
			}
			countE -= 2;
		}
		
		while(countF >= 2)
		{
			totalPrice += 2 * prices.get('F');
			countF -= 2;
			if(countF > 0)
			{
				countF -= 1;
			}
		}
		
		while(countA >= 5)
		{
			totalPrice += 200;
			countA -= 5;
		}
		while(countA >= 3)
		{
			totalPrice += 130;
			countA -= 3;
		}
		while(countB >= 2)
		{
			totalPrice += 45;
			countB -= 2;
		}
		
		totalPrice += countA * prices.get('A');
		totalPrice += countB * prices.get('B');
		totalPrice += countC * prices.get('C');
		totalPrice += countD * prices.get('D');
		totalPrice += countE * prices.get('E');
		totalPrice += countF * prices.get('F');
		

		return totalPrice;
		
	}
    
	
	private static class IllegalBasketException extends Exception {
		
	}
}