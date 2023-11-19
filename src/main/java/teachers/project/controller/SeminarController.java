package teachers.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import teachers.project.entity.Seminar;
import teachers.project.service.SeminarServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/seminar")
public class SeminarController {

    private final SeminarServiceImpl seminarServiceImpl;
    @Autowired
    public SeminarController(SeminarServiceImpl seminarServiceImpl) {
        this.seminarServiceImpl = seminarServiceImpl;
    }

    // Display all seminars with optional pagination.
    @GetMapping(value = { "", "/" })
    public String getAllSeminars(Model model, @RequestParam("page") Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size) {
        return page(null,model,page,size);
    }
    // Display all seminars with optional pagination.
    @GetMapping("/search")
    public String searchSeminars(@RequestParam("term") String term, Model model,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {
        if (term.isBlank()) {
            return "redirect:/seminar";
        }
        return page(term, model, page, size);
    }
    // Helper method for pagination.
    private String page(@RequestParam("term") String term, Model model, @RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Seminar> seminarPage;

        if (term == null) {
            seminarPage = seminarServiceImpl.findPaginated(PageRequest.of(currentPage - 1, pageSize), null);
        } else {
            seminarPage = seminarServiceImpl.findPaginated(PageRequest.of(currentPage - 1, pageSize), term);
        }
        model.addAttribute("seminarPage", seminarPage);

        int totalPages = seminarPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "list";
    }
    // Display a form to add a new seminar.
    @GetMapping("/add")
    public String addSeminar(Model model) {
        model.addAttribute("seminar", new Seminar());
        return "form";
    }

    //Save a seminar
    @PostMapping("/save")
    public String saveSeminar(@Valid Seminar seminar, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "form";
        }
        seminarServiceImpl.save(seminar);
        redirect.addFlashAttribute("successMessage", "Saved seminar successfully!");
        return "redirect:/seminar";
    }

    // Display a form to edit an existing seminar.
    @GetMapping("/edit/{id}")
    public String editSeminar(@PathVariable("id") Long id, Model model) {
        model.addAttribute("seminar", seminarServiceImpl.findSeminarById(id));
        return "form";
    }

    //Delete a seminar
    @GetMapping("/delete/{id}")
    public String deleteSeminar(@PathVariable Long id, RedirectAttributes redirect) {
        seminarServiceImpl.delete(id);
        redirect.addFlashAttribute("successMessage", "Deleted seminar successfully!");
        return "redirect:/seminar";
    }
}
