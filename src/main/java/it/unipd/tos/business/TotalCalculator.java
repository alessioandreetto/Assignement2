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
         int numGel=0;
         MenuItem GelatoMenoCostoso=null;      


        if (items == null) {
            throw new TakeAwayBillException("Lista vuota");
        }

        if (items.contains(null)) {
            throw new TakeAwayBillException("Elemento vuoto nella lista");
        }

        for(MenuItem i: items) {
            tot +=i.getPrice();
            if (i.getItemType() == MenuItem.type.Gelato) {
                numGel++;

                if ((GelatoMenoCostoso == null) || (GelatoMenoCostoso.getPrice() > i.getPrice())) {
                    GelatoMenoCostoso = i;
                }
            }

            } 
            if (numGel > 5) {
                tot -= GelatoMenoCostoso.getPrice() * 0.5;
            }
        return tot;     
    }
}