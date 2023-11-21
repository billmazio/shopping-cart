package seminars.project.service;

import seminars.project.entity.Seminar;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

public interface ISeminarCartService {
    List<Seminar> getCart();
    BigDecimal totalPrice();
    void emptyCart();
    void deleteSeminarWithId(Long seminarId);

    HttpSession getSession();


}
