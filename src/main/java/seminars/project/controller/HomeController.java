package seminars.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import seminars.project.entity.Seminar;
import seminars.project.service.SeminarServiceImpl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//HomePage
@Controller
public class HomeController {

    // Default number of seminars displayed per page
    private static final int pageSizeDefault = 6;

    private final SeminarServiceImpl seminarServiceImpl;

    @Autowired
    public HomeController(SeminarServiceImpl seminarServiceImpl) {
        this.seminarServiceImpl = seminarServiceImpl;
    }



    // Display the home page with a list of seminars and optional pagination.
    @GetMapping(value = { "", "/" })
    public String listSeminars(Model model, @RequestParam("page")Optional<Integer> page,
                               @RequestParam("size")Optional<Integer> size) {
        return page(null, model, page, size);
    }
    // Search for seminars based on a search term.
    @GetMapping("/search")
    public String searchSeminars(@RequestParam("term")String term, Model model,
                                 @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        if (term.isBlank()) {
            return "redirect:/";
        }
        return page(term, model, page, size);
    }


    // Main trunk of pagination and control with if and else statements.
    private String page(@RequestParam("term") String term, Model model,@RequestParam("page") Optional<Integer> page,
                        @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(pageSizeDefault);

      Page<Seminar> seminarPage;

        if (term == null) {
            seminarPage = seminarServiceImpl.findPaginated(PageRequest.of(currentPage -1,pageSize),null);

        }else {
            seminarPage = seminarServiceImpl.findPaginated(PageRequest.of(currentPage -1,pageSize),term);
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
