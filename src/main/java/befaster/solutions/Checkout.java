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

		int totalPrice = 0;
		char[] skus = basket.toCharArray();
			
		int countA = 0;
		int countB = 0;
		for(char sku : skus)
		{
			
			if(!prices.containsKey(sku))
				throw new IllegalBasketException();
			
			if(sku == 'A')
			{
				countA++;
			}
			
			if(sku == 'B')
			{
				countB++;
			}
			

			
			if(countA == 3)
			{
				countA = 0;
				int valueOfA = 50;
				totalPrice += 130;
				totalPrice -= 2*valueOfA;
			}
			else if(countB == 2)
			{
				countB = 0;
				int valueOfB = 30;
				totalPrice += 45;
				totalPrice -= 1*valueOfB;
			}
			else
			{				
				int itemValue = prices.get(sku);
				totalPrice += itemValue; 
			}

		}
		return totalPrice;
		
	}
    
	
	private static class IllegalBasketException extends Exception {
		
	}
}
