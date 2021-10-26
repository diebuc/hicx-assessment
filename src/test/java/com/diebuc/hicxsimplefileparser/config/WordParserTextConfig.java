package com.diebuc.hicxsimplefileparser.config;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounterImpl;
import com.diebuc.hicxsimplefileparser.tokenparser.TokenParser;
import com.diebuc.hicxsimplefileparser.tokenparser.WordParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WordParserTextConfig {

    @Bean
    public TokenParser wordParser() {
        return new WordParser();
    }

    @Bean
    public TokenCounter tokenCounter(){
        return new TokenCounterImpl();
    }

}
