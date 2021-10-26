package com.diebuc.hicxsimplefileparser.tokenparser;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class DotParser implements TokenParser {
    public static final String TOKEN_SYMBOL = ".";

    public Long parse(Scanner scanner, TokenCounter tokenCounter, TokenStatsResult result, String patternOfDelimiters){
        Long dotCount = scanner.findAll("\\.").count();
        tokenCounter.countToken(TOKEN_SYMBOL,dotCount);
        result.setStatistic("dots", tokenCounter.getCount(TOKEN_SYMBOL));
        return dotCount;
    }
}
