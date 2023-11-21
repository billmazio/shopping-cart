package seminars.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import seminars.project.service.ISeminarCartService;
import seminars.project.entity.Seminar;

import seminars.project.service.SeminarServiceImpl;



import java.util.List;

@Controller
@RequestMapping("/cart")
public class SeminarCartController {
    private final SeminarServiceImpl seminarServiceImpl;
    private final ISeminarCartService seminarCartService;


    @Autowired
    public SeminarCartController(SeminarServiceImpl seminarServiceImpl, ISeminarCartService seminarCartService) {
        this.seminarServiceImpl = seminarServiceImpl;
        this.seminarCartService = seminarCartService;

    }


    // Display the cart with seminars.
    @GetMapping(value = {"", "/"})
    public String seminarCart(Model model) {
        model.addAttribute("cart", seminarCartService.getCart());
        return "cart";
    }
    // Add a seminar to the cart.
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id, RedirectAttributes redirect) {
        List<Seminar> cart = seminarCartService.getCart();
        Seminar seminar = seminarServiceImpl.findSeminarById(id).get();
        if (seminar != null) {
            cart.add(seminar);
        }
        seminarCartService.getSession().setAttribute("cart", cart);
        redirect.addFlashAttribute("successMessage", "Added seminar successfully!");
        return "redirect:/cart";
    }

    // Remove a seminar from the cart.
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long id, RedirectAttributes redirect) {
        Seminar seminar = seminarServiceImpl.findSeminarById(id).get();
        if (seminar != null) {
            seminarCartService.deleteSeminarWithId(id);
        }
        redirect.addFlashAttribute("successMessage", "Removed seminar successfully!");
        return "redirect:/cart";
    }
    // Remove all seminars from the cart.
    @GetMapping("/remove/all")
    public String removeAllFromCart() {
        List<Seminar> cart = seminarCartService.getCart();
        cart.removeAll(cart);
        return "redirect:/cart";

    }

}