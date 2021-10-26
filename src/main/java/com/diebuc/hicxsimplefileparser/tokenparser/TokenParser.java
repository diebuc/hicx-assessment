package com.diebuc.hicxsimplefileparser.tokenparser;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;

import java.util.Scanner;

public interface TokenParser {

    public Long parse(Scanner scanner, TokenCounter tokenCounter, TokenStatsResult result, String patternOfDelimiters);

}
