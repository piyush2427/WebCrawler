package com.webCrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.webCrawler.dao.WebCrawlDAO;
import com.webCrawler.entity.WebCrawls;

@Service
public class WebCrawlServiceImpl implements WebCrawlService {

	@Autowired
    private WebCrawlDAO webCrawlDAO;
	
	@Override
	@Transactional
	public void addCrawls(WebCrawls crawls) {
		webCrawlDAO.addCrawls(crawls);
		
	}

	
}
