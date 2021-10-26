package com.diebuc.hicxsimplefileparser.config;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounterImpl;
import com.diebuc.hicxsimplefileparser.tokenparser.DotParser;
import com.diebuc.hicxsimplefileparser.tokenparser.TokenParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotParserTextConfig {

    @Bean
    public TokenParser dotParser() {
        return new DotParser();
    }

    @Bean
    public TokenCounter tokenCounter(){
        return new TokenCounterImpl();
    }

}
