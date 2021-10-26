
package com.diebuc.hicxsimplefileparser;

import com.diebuc.hicxsimplefileparser.config.DotParserTextConfig;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;
import com.diebuc.hicxsimplefileparser.tokenparser.TokenParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Scanner;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {DotParserTextConfig.class})
public class DotParserTests {

    @Autowired
    @Qualifier("dotParser")
    TokenParser dotParser;

    @Autowired
    TokenCounter tokenCounter;

    @Test
    void testWhenExistsDotsTheCountThemThenCorrect() {
        String textContent = "aaa. aaa. bbb bbb. ccc ccc tt. ttt m. mmm aaa ccc. aaa p q";
        TokenStatsResult statsResult = new TokenStatsResult();
        Scanner scanner = new Scanner(textContent);
        dotParser.parse(scanner, tokenCounter, statsResult, "[\\ |\\.\\,\\;\\:\\r\\n]");
        List<String> tokens = statsResult.getAllStatistics();
        Assert.notNull(statsResult, "results can't be null");
        Assert.notNull(tokens, "processed token list can't be null");
        Assert.isTrue(statsResult.getStatistic("dots").compareTo(6L) == 0, "token occurrence mismatch");
    }

}
