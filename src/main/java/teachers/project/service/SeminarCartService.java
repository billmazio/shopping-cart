package teachers.project.service;

import org.springframework.stereotype.Service;
import teachers.project.entity.Seminar;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeminarCartService {

    private HttpSession session;

    public SeminarCartService( HttpSession session) {
        this.session = session;
    }

    public HttpSession getSession() {
        return session;
    }

    //creation seminar-cartMarket
    public List<Seminar> getCart() {
        List<Seminar> cart = (List<Seminar>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }
    //creation of price
     public BigDecimal totalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        List<Seminar> cart = getCart();
        for (Seminar s : cart) {
            totalPrice = totalPrice.add(s.getPrice());
        }
        return  totalPrice;
     }

    //removed automatically after adding
    public void emptyCart() {
        List<Seminar> cart = getCart();
        cart.removeAll(cart);
    }
    //remove from cart
    public void deleteSeminarWithId(Long seminarId) {
        List<Seminar> cart = getCart();
        for (int i = 0; i <= cart.size(); i++) {
            if (cart.get(i).getId() == seminarId) {
                cart.remove(cart.get(i));
            }
        }


    }

}
