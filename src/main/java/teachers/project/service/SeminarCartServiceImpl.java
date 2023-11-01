package teachers.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teachers.project.entity.Seminar;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeminarCartServiceImpl implements ISeminarCartService {

    private final HttpSession session;
   @Autowired
    public SeminarCartServiceImpl( HttpSession session) {
        this.session = session;
    }

    public HttpSession getSession() {
        return session;
    }

    @Override
    public List<Seminar> getCart() {
        List<Seminar> cart = (List<Seminar>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        return cart;
    }

    @Override
    public BigDecimal totalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        List<Seminar> cart = getCart();
        for (Seminar s : cart) {
            totalPrice = totalPrice.add(s.getPrice());
        }
        return  totalPrice;
    }

    @Override
    public void emptyCart() {
        List<Seminar> cart = getCart();
        cart.clear();
    }

    @Override
    public void deleteSeminarWithId(Long seminarId) {
        List<Seminar> cart = getCart();
        cart.removeIf(seminar -> seminar.getId().equals(seminarId));
    }
}
