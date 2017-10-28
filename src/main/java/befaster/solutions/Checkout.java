package befaster.solutions;

import java.util.HashMap;
import java.util.Map;

public class Checkout {
	
	private static final Map<Character, Integer> PRICES = new HashMap<>();
	static {
		PRICES.put('A', 50);
		PRICES.put('B', 30);
		PRICES.put('C', 20);
		PRICES.put('D', 15);
		PRICES.put('E', 40);
		PRICES.put('F', 10);
		PRICES.put('G', 20);
		PRICES.put('H', 10);
		PRICES.put('I', 35);
		PRICES.put('J', 60);
		PRICES.put('K', 80);
		PRICES.put('L', 90);
		PRICES.put('M', 15);
		PRICES.put('N', 40);
		PRICES.put('O', 10);
		PRICES.put('P', 50);
		PRICES.put('Q', 30);
		PRICES.put('R', 50);
		PRICES.put('S', 30);
		PRICES.put('T', 20);
		PRICES.put('U', 40);
		PRICES.put('V', 50);
		PRICES.put('W', 20);
		PRICES.put('X', 90);
		PRICES.put('Y', 10);
		PRICES.put('Z', 50);
	}
	
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
		
		char[] skus = basket.toCharArray();
			
		
		// Check all items
		for(char sku : skus)
		{
			if(!PRICES.containsKey(sku))
				throw new IllegalBasketException();
		}
		
		// Count all items
		Counter counter = new Counter();
		for(char sku : skus)
		{
			counter.increment(sku);
		}
		
		// Add the appropriate values 
		int totalPrice = 0;
		
		// Free items offers
		totalPrice += applyGetOneFreeOffer(2, 'E', 'B', counter);
		totalPrice += applyGetOneFreeOffer(2, 'F', 'F', counter);
		totalPrice += applyGetOneFreeOffer(3, 'N', 'M', counter);
		totalPrice += applyGetOneFreeOffer(3, 'R', 'Q', counter);
		totalPrice += applyGetOneFreeOffer(3, 'U', 'U', counter);
	
		// Multi price offers
		totalPrice += applyMultiPriceOffer(2, 'K', 150, counter);
		totalPrice += applyMultiPriceOffer(5, 'P', 200, counter);
		totalPrice += applyMultiPriceOffer(3, 'Q', 80, counter);
		
		totalPrice += applyMultiPriceOffer(3, 'V', 130, counter);
		totalPrice += applyMultiPriceOffer(2, 'V', 90, counter);
		
		totalPrice += applyMultiPriceOffer(10, 'H', 80, counter);
		totalPrice += applyMultiPriceOffer(5, 'H', 45, counter);
		
		totalPrice += applyMultiPriceOffer(5, 'A', 200, counter);
		totalPrice += applyMultiPriceOffer(3, 'A', 130, counter);
		
		totalPrice += applyMultiPriceOffer(2, 'B', 45, counter);
		
		
		// single items
		for(Character sku : PRICES.keySet())
		{
			totalPrice += counter.get(sku) * PRICES.get(sku);
		}
		

		return totalPrice;
		
	}


	private static int applyMultiPriceOffer(int targetQuantity, Character targetItem, int discountedValue, Counter counter) {
		int offerValue = 0;
		while(counter.get(targetItem) >= targetQuantity)
		{
			offerValue += discountedValue;
			counter.substract(targetItem, targetQuantity);
		}
		return offerValue;
	}


	private static int applyGetOneFreeOffer(int targetQuantity, Character targetItem, Character freeItem, Counter counter) 
	{
		int offerValue = 0;
		while(counter.get(targetItem) >= targetQuantity)
		{
			offerValue += targetQuantity * PRICES.get(targetItem);
			counter.substract(targetItem, targetQuantity);
			if(counter.get(freeItem) > 0)
			{
				counter.substract(freeItem, 1);
			}
		}
		return offerValue;
	}
    
	
	private static class Counter {
		private final Map<Character, Integer> innerMap;
		
		public Counter() 
		{
			innerMap = new HashMap<>();
		}
		
		
		
		public Integer increment(Character sku)
		{
			return add(sku, 1);
		}
		
		public Integer substract(Character sku, Integer subValue)
		{
			return add(sku, -subValue);
		}
		
		public Integer add(Character sku, Integer addValue)
		{			
			Integer nextValue = get(sku) + addValue;
			innerMap.put(sku, nextValue);
			return nextValue;
		}
		
		
		public Integer get(Object key)
		{
			return innerMap.getOrDefault(key, 0);
		}
	
	}
	
	private static class IllegalBasketException extends Exception {
		
	}
}