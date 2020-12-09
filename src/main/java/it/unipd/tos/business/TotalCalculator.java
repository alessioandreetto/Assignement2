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
        double total = 0.0;        


        if (items == null) {
            throw new TakeAwayBillException("Lista vuota");
        }

        if (items.contains(null)) {
            throw new TakeAwayBillException("Elemento vuoto nella lista");
        }

        //costo
        for (MenuItem i: items) { 
            total += i.getPrice();           
        }
        return total;     
    }
}