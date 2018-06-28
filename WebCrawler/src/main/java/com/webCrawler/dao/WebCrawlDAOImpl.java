package com.webCrawler.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.webCrawler.entity.WebCrawls;

@Repository
public class WebCrawlDAOImpl implements WebCrawlDAO {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public void addCrawls(WebCrawls crawls) {
		Query query = new Query(Criteria.where("recordURL").is(crawls.getRecordURL()));
		WebCrawls webCrawl = this.mongoOperations.findOne(query, WebCrawls.class);
		if (webCrawl == null)
			this.mongoOperations.save(crawls);
	}

}
