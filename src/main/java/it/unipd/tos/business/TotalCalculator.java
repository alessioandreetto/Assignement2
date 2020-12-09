////////////////////////////////////////////////////////////////////
// Alessio Andreetto 1193550
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import it.unipd.tos.business.exception.TakeAwayBillException;

public class TotalCalculator implements TakeAwayBill {
    public double getOrderPrice(List < MenuItem > items, User user) throws TakeAwayBillException {
        double tot = 0.0;   
        int numGel= 0;
        MenuItem GelatoMenoCostoso=null;
        double BudGel = 0;
       
        
       

        if (items == null) {
            throw new TakeAwayBillException("lista vuota");
        }

        if (items.contains(null)) {
            throw new TakeAwayBillException("elemento vuoto");
        }
        
        if (items.size() > 30) {
             throw new TakeAwayBillException("30 elememti");
        }

        //costo
        for(MenuItem i: items) {
        tot +=i.getPrice();
        if (i.getItemType() == MenuItem.type.Gelato) {
             numGel++;
             BudGel += i.getPrice();
            if ((GelatoMenoCostoso == null) || (GelatoMenoCostoso.getPrice() > i.getPrice())) {
                GelatoMenoCostoso = i;
            }
        }
            if (i.getItemType() == MenuItem.type.Budino) {
            BudGel += i.getPrice();
            }
            
        
        
        } 
        if (numGel > 5) {
            tot -= GelatoMenoCostoso.getPrice() * 0.5;
        }
        if (BudGel > 50) {
        tot -= tot * 0.1;
        }
       

        return tot;

      
    }



}