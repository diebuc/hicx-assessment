package com.diebuc.hicxsimplefileparser.tokencounter;

import java.util.Map;

public interface TokenCounter {

	public void cleanCounters();

	void countToken(String token);

	void countToken(String token, Long count);

	Long getCount(String token);

	Long getCountWords();
	Map<String,Long> getMaximunOccurence();

}