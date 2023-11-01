package teachers.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teachers.project.entity.Seminar;
import teachers.project.entity.Student;
import teachers.project.service.EmailService;
import teachers.project.service.ISeminarCartService;
import teachers.project.service.IStudentService;


import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final IStudentService studentService;
    private final EmailService emailService;
    private final ISeminarCartService seminarCartService;
    @Autowired
    public CheckoutController(IStudentService studentService, EmailService emailService, ISeminarCartService seminarCartService) {
        this.studentService = studentService;
        this.emailService = emailService;
        this.seminarCartService = seminarCartService;
    }
    //filling in details
    @GetMapping(value = {"","/"})
    public String checkout(Model model) {
        List<Seminar> cart = seminarCartService.getCart();
        if (cart.isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("student", new Student());
        model.addAttribute("seminarsInCart",cart);
        model.addAttribute("totalPrice",seminarCartService.totalPrice().toString());
        return "checkout";
    }
    //store the vest
    @PostMapping("/placeVest")
    public String placeVest(@Valid Student student, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "/checkout";
        }
        //showing completing vest
        studentService.createVest(student,seminarCartService.getCart());
        emailService.sendEmail(student.getEmail(),"roomSeminar -Vest Confirmation","Your vest has been confirmed");
        //method void emptyCart
        seminarCartService.emptyCart();
        redirect.addFlashAttribute("successMessage", "The vest is confirmed, check your email.");
        return "redirect:/cart";
    }

}




