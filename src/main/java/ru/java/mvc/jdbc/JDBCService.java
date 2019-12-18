package ru.java.mvc.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.java.mvc.bean.News;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class JDBCService {

    @Autowired
    DataSource dataSource; //look to application-context.xml bean id='dataSource' definition

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<News> queryAllNews() {
        final String QUERY_SQL = "SELECT * FROM NEWS ORDER BY IDNEWS";
        List<News> newsList = this.jdbcTemplate.query(QUERY_SQL, new RowMapper<News>() {
            public News mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                News news = new News();
                news.setIdNews(resultSet.getInt("IDNEWS"));
                news.setNewsAuthor(resultSet.getString("NEWSAUTHOR"));
                news.setNewsHeadline(resultSet.getString("NEWSHEADLINE"));
                news.setNewsMain(resultSet.getString("NEWSMAIN"));
                news.setNewsDate(resultSet.getTimestamp("NEWSDATE"));
                return news;
            }
        });
        return newsList;
    }

    public void insertNews(News news)  {
        final String INSERT_SQL = "INSERT INTO NEWS (NEWSAUTHOR, NEWSHEADLINE, NEWSMAIN, NEWSDATE) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(INSERT_SQL, news.getNewsAuthor(), news.getNewsHeadline(), news.getNewsMain(), news.getNewsDate());
    }
}
