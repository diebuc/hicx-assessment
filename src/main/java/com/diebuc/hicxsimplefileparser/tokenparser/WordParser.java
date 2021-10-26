package com.diebuc.hicxsimplefileparser.tokenparser;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;

import java.util.Map;
import java.util.Scanner;

public class WordParser implements TokenParser{
    @Override
    public Long parse(Scanner scanner, TokenCounter tokenCounter, TokenStatsResult result, String patternOfDelimiters) {
        Integer a;
        while (scanner.hasNext()) {
            scanner.useDelimiter(patternOfDelimiters);
            String token = scanner.next();
            tokenCounter.countToken(token);
        }
        Map<String, Long>  maximunOccurence = tokenCounter.getMaximunOccurence();
        if(maximunOccurence.size()==1 ){
            for (String stat : maximunOccurence.keySet())
                if(stat.compareToIgnoreCase("NONE")!=0)
                    result.setStatistic(stat, maximunOccurence.get(stat) );
        }
        result.setStatistic("words", tokenCounter.getCountWords());
        return tokenCounter.getCountWords();
    }
}
