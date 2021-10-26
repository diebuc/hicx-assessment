package com.diebuc.hicxsimplefileparser.tokencounter;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Component
public class TokenCounterImpl implements TokenCounter {

	Map<String, Long> tokens;
	
	public TokenCounterImpl() {
		tokens = new HashMap<String, Long>();
	}

	@Override
	public void cleanCounters() {
		tokens.clear();
	}

	@Override
	public void countToken(String token) {
		Long count = tokens.get(token);
		if(token.compareTo("")==0)
			return;
		if (count != null) {
			count++;
		}
		else
			count = 1L;
		tokens.put(token, count);
	}

	@Override
	public void countToken(String token, Long count){
		Long curentCount = tokens.get(token);
		if (curentCount != null) {
			curentCount+=count;
		}
		else
			curentCount = count;
		tokens.put(token, curentCount);
	}


	@Override
	public Long getCount(String token)
	{
		return tokens.get(token);
	}

	@Override
	public Long getCountWords()
	{
		Long sum=0L;
		for (Entry<String, Long> token :
				tokens.entrySet()) {
			if (token.getKey().matches("^[a-zA-Z0-9]+$")) {
				sum += token.getValue();
			}
		}
		return sum;
	}

	@Override
	public Map<String,Long> getMaximunOccurence()
	{
		Map<String,Long> maximunOcurrenceTuple = new HashMap<>();
		Long max = 1L;
		String word = "NONE";

		for (Entry<String, Long> token :
			tokens.entrySet()) {
			if (token.getValue() > max) {
				if(token.getKey().matches(  "^[a-zA-Z0-9]+$"))
				{
					max =  token.getValue();
					word = token.getKey() ;
				}
			}
		}
		maximunOcurrenceTuple.put(word,max);
		return maximunOcurrenceTuple;
	}


}
