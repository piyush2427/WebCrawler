package com.webCrawler.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;

import com.webCrawler.entity.WebCrawls;
import com.webCrawler.service.WebCrawlService;
import com.webCrawler.service.WebCrawlServiceImpl;

@Controller
public class WebCrawlController {

	public static void processPage(String URL, MongoOperations mongoOperation) throws IOException{
			
			WebCrawls crawls = new WebCrawls(URL);
			Query query = new Query(Criteria.where("recordURL").is(crawls.getRecordURL()));
			WebCrawls webCrawl = mongoOperation.findOne(query, WebCrawls.class);
			if (webCrawl == null)
				mongoOperation.save(crawls);
			
			//get useful information
			Document doc = Jsoup.connect(URL).get();
 
			if(doc.text().contains("Piyush Pani") || doc.text().contains("IAS") || doc.text().contains("ICS") || doc.text().contains("IPS")){
				
				System.out.println(URL);
			}
 
			//get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for(Element link: questions){
				if(link.attr("href").contains("org") || link.attr("href").contains("com"))
					processPage(link.attr("abs:href"),mongoOperation);
			}
		}
	
	public static void main(String[] args) throws IOException {

		// For XML
		ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
		//String URl ="http://en.wikipedia.org";
		String URL ="http://www.google.in/";
		processPage(URL,mongoOperation);
		
	}
	

}
