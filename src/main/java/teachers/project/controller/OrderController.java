package teachers.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import teachers.project.entity.Seminar;
import teachers.project.entity.Student;
import teachers.project.entity.StudentSeminar;
import teachers.project.service.IStudentService;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;




@Controller
@RequestMapping("/orders")
public class OrderController {

    private final IStudentService studentService;
    @Autowired
    public OrderController(IStudentService studentService) {
        this.studentService = studentService;
    }

    // Display all orders with optional pagination.
    @GetMapping(value = { "", "/" })
    public String getAllVests(Model model, @RequestParam("page")Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
       return page(null , model ,page , size);
    }

    // Search for orders based on a search term.
    @GetMapping("/search")
    public String searchVests(@RequestParam("term") String term, Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
       if (term.isBlank()) {
           return "redirect:/orders";
       }
       return page (term,model,page,size);
    }
    // Display details of a specific order.
    @GetMapping("/{id}")
    public String showSpecificVest(@PathVariable("id") Long id, Model model) {
        List<StudentSeminar> studentSeminars = studentService.findOrdersByStudentId(id);

        Student student = null;
        List<Seminar> seminars = null;
        for (StudentSeminar s : studentSeminars) {
            student = s.getStudent();
            seminars= s.getSeminars();
        }
        model.addAttribute("student",student);
        model.addAttribute("seminars",seminars);
        return "order";
    }
    // Helper method for pagination.
    private String page(@RequestParam("term") String term, Model model,
                        @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

       Page<StudentSeminar> orderPage;

       if (term == null) {
           orderPage = studentService.findPaginated(PageRequest.of(currentPage -1,pageSize),null);

       }else {
           orderPage = studentService.findPaginated(PageRequest.of(currentPage -1,pageSize),term);
       }
       model.addAttribute("orderPage",orderPage);

       int totalPages = orderPage.getTotalPages();
       if (totalPages > 0) {
           List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
           model.addAttribute("pageNumbers", pageNumbers);
       }
       return "orders";
    }
}

