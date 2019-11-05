import junit.framework.TestCase;

/**
 * The ShoppingCartTest is an example JUnit TestCase
 * as described in the JUnit Primer.
 *
 * @author Mike Clark, http://clarkware.com
 */
 
public class ShoppingCartTest extends TestCase {
    
    private ShoppingCart cart;
    private Product book1;
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp() {
	
        cart = new ShoppingCart();
	
        book1 = new Product("Pragmatic Unit Testing", 29.95);
	
        cart.addItem(book1);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown() {
        // release objects under test here, as necessary
    }
    
    /**
     * Tests emptying the cart.
     */
    public void testEmpty() {
	
        cart.empty();
    
        assertEquals(0, cart.getItemCount());
    }

    /**
     * Tests adding an item to the cart.
     */
    public void testAddItem() {
	
        Product book2 = new Product("Pragmatic Project Automation", 29.95);
        cart.addItem(book2);

        assertEquals(2, cart.getItemCount());
	
        double expectedBalance = book1.getPrice() + book2.getPrice();	
        assertEquals(expectedBalance, cart.getBalance(), 0.0);
    }
        
    /**
     * Tests removing an item from the cart.
     *
     * @throws ProductNotFoundException If the product was not in the cart.
     */
    public void testRemoveItem() throws ProductNotFoundException {
	
        cart.removeItem(book1);

        assertEquals(0, cart.getItemCount());
    }
    
    /**
     * Tests removing an unknown item from the cart.
     *
     * This test is successful if the 
     * ProductNotFoundException is raised.
     */
    public void testRemoveItemNotInCart() {
	
        try {
	    
            Product book3 = new Product("Pragmatic Version Control", 29.95);
            cart.removeItem(book3);
	    
            fail("Should raise a ProductNotFoundException");
	    
        } catch(ProductNotFoundException expected) {
            // successful test
        }
    }
}
