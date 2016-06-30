package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.IfeedRepository;
import com.example.model.Feed;
import com.example.repository.FeedRepository;
import com.mysema.query.types.Predicate;


@SpringBootApplication
//@RestController
@EntityScan(basePackages = {"com.example.model"})
@EnableJpaRepositories(basePackages = {"com.example.repository"})
@EnableTransactionManagement
@ImportResource("classpath:/spring/tfeeds-db-endpoint.xml")
@EnableScheduling

public class TfeedsDbEndpointApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context= 
				SpringApplication.run(TfeedsDbEndpointApplication.class, args);
	}
	
	private FeedRepository feedRepository;
	 
    @Autowired
    public void setProductRepository(FeedRepository feedRepository) {
        this.feedRepository = feedRepository;
    }
	
	@Autowired
	IfeedRepository feedRepo;
	

//	@RequestMapping("/push")
//	public @ResponseBody List<Feed> getFeeds() throws ExecutionException, InterruptedException {
//		
//		Map<Object,Object> feeds = feedRepo.findAllFeeds();
//		
//		//final List<Feed> list = objectMapper.convertValue(feeds, Feed.class);
//		
//		
//		List<Feed> feedList = new ArrayList<Feed>();
//		
//		for(Map.Entry<Object, Object> entry: feeds.entrySet()){
//			
//			feedList.add((Feed) entry.getValue());
//		}
//		
//		feedRepository.save(feedList);
//		
//		System.out.println(feedRepository.findAll().iterator().next().getIndicator());
//
//		return feedList;
//	}

}
