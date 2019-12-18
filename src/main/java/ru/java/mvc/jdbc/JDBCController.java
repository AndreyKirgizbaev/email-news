package ru.java.mvc.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.java.mvc.bean.News;
import ru.java.mvc.hazelcast.HazelcastService;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class JDBCController {

    @Autowired
    JDBCService jdbcService;

    @Autowired
    HazelcastService hazelcastService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView selectAllNews() {
        List<News> news =  jdbcService.queryAllNews();
        return new ModelAndView("index", "resultObject", news);
    }

    @RequestMapping(value = "/jdbcInsertNews", method = RequestMethod.POST)
    public ModelAndView jdbcInsertNews(@ModelAttribute("news") News news) {
        news.setNewsDate(new Timestamp(System.currentTimeMillis()));
        jdbcService.insertNews(news);
        hazelcastService.insertNewsInHazelcast(news);
        return selectAllNews();
    }
}
