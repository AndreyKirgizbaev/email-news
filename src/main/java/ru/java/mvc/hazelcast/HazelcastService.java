package ru.java.mvc.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Service;
import ru.java.mvc.bean.News;

import javax.annotation.PostConstruct;
import java.util.Queue;

@Service
public class HazelcastService {

    private HazelcastInstance instance;

    @PostConstruct
    public void init() {
        Config cfg = new Config();
        instance = Hazelcast.newHazelcastInstance(cfg);
    }

    public void insertNewsInHazelcast(News news)  {
        Queue<News> queueNews = instance.getQueue("news");
        queueNews.offer(news);
    }
}
