package teachers.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teachers.project.entity.Seminar;
import teachers.project.service.SeminarCartService;
import teachers.project.service.SeminarService;


import java.util.List;

@Controller
@RequestMapping("/cart")
public class SeminarCartController {
    private final SeminarService seminarService;
    private final SeminarCartService seminarCartService;

    public SeminarCartController(SeminarService seminarService, SeminarCartService seminarCartService) {
        this.seminarService = seminarService;
        this.seminarCartService = seminarCartService;
    }
    //showing cart model
    @GetMapping(value = {"", "/"})
    public String seminarCart(Model model) {
        model.addAttribute("cart", seminarCartService.getCart());
        return "cart";
    }
    //adding the cart
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id, RedirectAttributes redirect) {
        List<Seminar> cart = seminarCartService.getCart();
        Seminar seminar = seminarService.findSeminarById(id).get();
        if (seminar != null) {
            cart.add(seminar);
        }
        seminarCartService.getSession().setAttribute("cart", cart);
        redirect.addFlashAttribute("successMessage", "Added seminar successfully!");
        return "redirect:/cart";
    }

    //removing the cart
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long id, RedirectAttributes redirect) {
        Seminar seminar = seminarService.findSeminarById(id).get();
        if (seminar != null) {
            seminarCartService.deleteSeminarWithId(id);
        }
        redirect.addFlashAttribute("successMessage", "Removed seminar successfully!");
        return "redirect:/cart";
    }
    //remove all the carts
    @GetMapping("/remove/all")
    public String removeAllFromCart() {
        List<Seminar> cart = seminarCartService.getCart();
        cart.removeAll(cart);
        return "redirect:/cart";

    }

}