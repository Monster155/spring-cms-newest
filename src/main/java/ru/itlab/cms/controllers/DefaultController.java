package ru.itlab.cms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.itlab.cms.models.Article;
import ru.itlab.cms.services.ArticleServiceImpl;
import ru.itlab.cms.services.TagServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class DefaultController {

    @Autowired
    private ArticleServiceImpl articleService;
    @Autowired
    private TagServiceImpl tagService;

    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

//    @Autowired
//    private LoggingAspect loggingAspect;

    private MessageSourceAccessor msa;
    private String code;

    @Autowired
    private void setMessageSourceAccessor(MessageSource ms) {
        this.msa = new MessageSourceAccessor(ms);
    }

    /*@RequestMapping("/")
    private String index(ModelMap map) {

        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tagService.getAll().get(0));
        articleService.addArticle(Article.builder().author("D").name("F").text("<h1>Hi</h1>").tags(tags).build());
        tags.clear();
        tags.add(tagService.getAll().get(2));
        articleService.addArticle(Article.builder().author("D").name("S").text("<h1>Hello</h1>").tags(tags).build());
        tags.clear();
        tags.add(tagService.getAll().get(1));
        tags.add(tagService.getAll().get(3));
        articleService.addArticle(Article.builder().author("D").name("T").text("<h1>Good morning</h1>").tags(tags).build());

//        1,action
//        2,adventure
//        3,simulator
//        4,shooter

        StringBuilder sb = new StringBuilder();
        articleService.getAll().forEach(a -> {
            sb.append(a.toString()).append("\n");
        });
        map.put("text", sb.toString());
        return "index";
    }*/

    @RequestMapping(value = "/")
    public String index(@RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                        @RequestParam(name = "pageLimit", defaultValue = "5") Integer pageLimit,
                        ModelMap map) {
        List<Article> articles = articleService.getAll();
        if (articles.size() == 0) return "view";
        pageNum--; // it gets first page like 1 instead of 0 (url needs 1)
        if (pageNum < 0) pageNum = 0;
        if (pageLimit < 1) pageLimit = 1;
        int pagesCount = (articles.size() + pageLimit - 1) / pageLimit;
        map.put("pagesCount", pagesCount);
        map.put("limit", pageLimit);
        if (pageNum >= pagesCount) System.out.println(); // write log
        else if ((pageNum + 1) * pageLimit >= articles.size())
            map.put("allPages", articles.subList(pageNum * pageLimit, articles.size()));
        else map.put("allPages", articles.subList(pageNum * pageLimit, (pageNum + 1) * pageLimit));
        return "view";
    }

    @RequestMapping(value = "/home")
    public String home(ModelMap map) {
        map.put("code", "This is home page");
        return "code";
    }

    @RequestMapping(value = "/404")
    public String errorPage() {
        return "status:404";
    }

    @RequestMapping(value = "/twitch_access")
    public String twitchAccessGet(@RequestParam String code,
                                  ModelMap map) {
        map.put("code", code);
        return "code";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String pageGet(@ModelAttribute("article") Article article,
                          ModelMap map) {
        System.out.println(article);
        putDataFromArticle(map, articleService.getText(article));
        return "page";
    }

    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public String editorGet(ModelMap map) {
        map.addAttribute("article", new Article());
        return "editor";
    }

    @RequestMapping(value = "/editor", method = RequestMethod.POST)
    public String editorPost(RedirectAttributes redirectAttributes,
                             @Valid @ModelAttribute("article") Article article,
                             BindingResult result,
                             ModelMap modelMap) {
        System.out.println("Check");
        System.out.println(article);
        if (!result.hasErrors()) {
            if (articleService.addArticle(article)) {
                redirectAttributes.addFlashAttribute("message", msa.getMessage("article.true"));
                return "redirect:" + MvcUriComponentsBuilder.fromMappingName("DC#index").build();
            } else {
                modelMap.put("duplicateArticle", msa.getMessage("article.duplicate"));
            }
        }
        modelMap.put("message", msa.getMessage("article.false"));
        return "editor";
    }

    @GetMapping("/validationCheck")
    @ResponseBody
    public Result validationCheck(@ModelAttribute Article article) {
        return checkArticle(article);
    }

    private Result checkArticle(Article article) {
        Result result = new Result();
        if (article.getName().length() < 3)
            result.addError(new Error("Article name", "Article name is too short"));
        else if (article.getName().length() > 30)
            result.addError(new Error("Article name", "Article name is too long"));
        if (article.getAuthor().length() < 3)
            result.addError(new Error("Author name", "Author name is too short"));
        else if (article.getAuthor().length() > 30)
            result.addError(new Error("Author name", "Author name is too long"));

        result.setStatus(result.getErrors().isEmpty());
        return result;
    }

    @RequestMapping("/change")
    public String change(@RequestParam String locale, HttpServletRequest req) {
        String referer = req.getHeader("Referer");
        String[] localeData = locale.split("-");
        localeResolver.setLocale(request, response, new Locale(localeData[0], localeData[1]));
        return "redirect:" + referer;//MvcUriComponentsBuilder.fromMappingName("DC#loginGet").build();
    }

    private void putDataFromArticle(ModelMap map, Article article) {
        map.put("name", article.getName());
        map.put("text", article.getText());
        map.put("date", new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date(article.getDate().getTime())));
        map.put("author", article.getAuthor());
    }

    private class Result {
        private boolean status;
        private ArrayList<Error> errors;

        public Result() {
            errors = new ArrayList<>();
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public ArrayList<Error> getErrors() {
            return errors;
        }

        public void addError(Error error) {
            this.errors.add(error);
        }
    }

    private class Error {
        private String name;
        private String message;

        public Error(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }
    }
}
