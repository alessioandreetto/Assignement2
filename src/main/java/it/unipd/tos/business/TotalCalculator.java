////////////////////////////////////////////////////////////////////
// Alessio Andreetto 1193550
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.business.TotalBill;
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
        
        if (tot<10) {
        tot +=0.50;
        }
       

        return tot;

      
    }

public List < TotalBill > FreeBills(List < TotalBill > bills) {

    List < TotalBill > noPay = new ArrayList < TotalBill > ();

    for (TotalBill i: bills) {
        if (i.getUser().getAge() < 18 && (!noPay.contains(i)) && noPay.size() < 10 && i.getTime() >= 64800 && i.getTime() <= 68400) {
            Random rand = new Random();
            if (rand.nextBoolean()) {
                noPay.add(i);
            }
        }
    }
    for (TotalBill i: noPay) {
        i.changePrice(0.00);
    }
    return noPay;
}

}