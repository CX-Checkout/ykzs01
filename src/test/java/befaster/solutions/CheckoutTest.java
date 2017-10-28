package befaster.solutions;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CheckoutTest {

    @Test
    public void compute_sum() {
        assertThat(Sum.sum(1, 1), equalTo(2));
    }
    
    @Test
    public void empty_basket_has_a_value_of_0()
    {
    	assertEquals(Checkout.checkout(""), 0);
    }
    
    @Test
    public void single_item()
    {
    	assertEquals(Checkout.checkout("A"), 50);
    	assertEquals(Checkout.checkout("B"), 30);
    	assertEquals(Checkout.checkout("C"), 20);
    	assertEquals(Checkout.checkout("D"), 15);
    }
    
    @Test
    public void basket_of_single_items()
    {
    	assertEquals(Checkout.checkout("ABCD"), 50 + 30 + 20 + 15);
    }
    
    @Test
    public void illegal_basket()
    {
    	assertEquals(Checkout.checkout("aa"), -1);
    }   
    
    @Test
    public void multi_price_offer() throws Exception
    {
    	assertEquals(Checkout.checkout("AAA"), 130);
    	assertEquals(Checkout.checkout("BB"), 45);
    }
    
    @Test
    public void round_acceptance_test() throws Exception
    {
    	assertEquals(Checkout.checkout("AAABBABCD"), 130 + 45 + 50 + 30 + 20 + 15);
    	assertEquals(Checkout.checkout("BB"), 45);
    }
}