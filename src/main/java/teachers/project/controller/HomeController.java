package teachers.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import teachers.project.entity.Seminar;
import teachers.project.service.SeminarService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//HomePage
@Controller
public class HomeController {

    //maximum number of seminars in every page
    private static final int pageSizeDefault = 6;

    private final SeminarService seminarService;

    @Autowired
    public HomeController(SeminarService seminarService) {
        this.seminarService = seminarService;
    }


    //list of seminars and crossing them with optional
    @GetMapping(value = { "", "/" })
    public String listSeminars(Model model, @RequestParam("page")Optional<Integer> page,
                               @RequestParam("size")Optional<Integer> size) {
        return page(null, model, page, size);
    }
    //search seminar
    @GetMapping("/search")
    public String searchSeminars(@RequestParam("term")String term, Model model,
                                 @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if (term.isBlank()) {
            return "redirect:/";
        }
        return page(term, model, page, size);
    }

    //main trunk of pagination and control with if and else
    private String page(@RequestParam("term") String term, Model model,@RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(pageSizeDefault);

      Page<Seminar> seminarPage;

        if (term == null) {
            seminarPage = seminarService.findPaginated(PageRequest.of(currentPage -1,pageSize),null);

        }else {
            seminarPage = seminarService.findPaginated(PageRequest.of(currentPage -1,pageSize),term);
        }
        model.addAttribute("seminarPage",seminarPage);

        int totalPages = seminarPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers",pageNumbers);
        }
        return "index";
    }



}
