package com.webCrawler.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "df_webCrawls")
public class WebCrawls {

	
		public WebCrawls(String recordURL) {
		super();
		this.recordURL = recordURL;
	}

		private String recordURL;

		public String getRecordURL() {
			return recordURL;
		}

		public void setRecordURL(String recordURL) {
			this.recordURL = recordURL;
		}
		
}
