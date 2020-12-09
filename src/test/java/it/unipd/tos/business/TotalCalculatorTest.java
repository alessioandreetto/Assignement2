package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.business.exception.TakeAwayBillException;

public class TotalCalculatorTest {
	
	TotalCalculator calculator;
	User user;
	double totale;
	List<MenuItem> list;
	private static final double Diff = 1e-3;
	
	@Before
	public void setup() {
		calculator = new TotalCalculator();
		totale = 0.00;
		list = new ArrayList<MenuItem>();
		user = new User("Alessio","Andreetto",21);
	}
	
		@Test(expected = TakeAwayBillException.class) 
	    public void listNulltTest() throws TakeAwayBillException {
	        list = null;
	        totale = calculator.getOrderPrice(list,user);
	    }
		
	@Test
	public void sumTest() throws TakeAwayBillException {

        list.add(new MenuItem(MenuItem.type.Gelato, "CoppaNafta", 5.00));
        list.add(new MenuItem(MenuItem.type.Gelato, "Biancaneve", 6.00));
        list.add(new MenuItem(MenuItem.type.Budino, "Pinguino", 3.50));
        list.add(new MenuItem(MenuItem.type.Bevanda, "Fanta", 2.50));

        totale = calculator.getOrderPrice(list,user);

        assertEquals(17.00,totale,Diff);
    }
	
			@Test(expected = TakeAwayBillException.class) 
		    public void nullInListTest() throws TakeAwayBillException {
				list.add(null);
		        totale = calculator.getOrderPrice(list,user);
		    }
			
			@Test
			public void discount50Test() throws TakeAwayBillException {
				list.add(new MenuItem(MenuItem.type.Gelato,"Pinguino",6.50));
				list.add(new MenuItem(MenuItem.type.Gelato,"BananaSplit",6.50));
				list.add(new MenuItem(MenuItem.type.Gelato,"Biancaneve",6.50));
				list.add(new MenuItem(MenuItem.type.Gelato,"Cioccolato",5.00));
				list.add(new MenuItem(MenuItem.type.Gelato,"Banana",3.00));
				list.add(new MenuItem(MenuItem.type.Gelato,"FiorDiLatte",5.00));
				list.add(new MenuItem(MenuItem.type.Gelato,"Fragola",5.00));
				totale = calculator.getOrderPrice(list, user);
				assertEquals(36.00,totale,Diff);
			}
			
			@Test
			public void discount10Test() throws TakeAwayBillException {
				list.add(new MenuItem(MenuItem.type.Budino,"Cameo",30.00));
				list.add(new MenuItem(MenuItem.type.Budino,"Biancaneve",30.00));
				list.add(new MenuItem(MenuItem.type.Gelato,"Fragola",35.00));
				list.add(new MenuItem(MenuItem.type.Bevanda,"Pepsi",5.00));
				totale = calculator.getOrderPrice(list, user);
				assertEquals(90.00,totale,Diff);
			}
			
			@Test(expected = TakeAwayBillException.class) 
			public void max30Test() throws TakeAwayBillException {
				for(int i=0; i<32; i++) {
				list.add(new MenuItem(MenuItem.type.Gelato,"Biancaneve",6.00));
				}
				totale = calculator.getOrderPrice(list,user);
			}
			
			@Test
			public void plus050Test() throws TakeAwayBillException {
				list.add(new MenuItem(MenuItem.type.Budino,"CoppaNafta",4.00));
				totale = calculator.getOrderPrice(list, user);
				assertEquals(4.50,totale,Diff);
			}
			
			
			
			
			
			
			
			
			
			
}