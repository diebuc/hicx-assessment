package com.diebuc.hicxsimplefileparser.config;

import com.diebuc.hicxsimplefileparser.fileparser.FileParser;
import com.diebuc.hicxsimplefileparser.fileparser.TextFileParser;
import com.diebuc.hicxsimplefileparser.directorywatcher.DirectoryWatcher;
import com.diebuc.hicxsimplefileparser.directorywatcher.DirectoryWatcherImpl;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectoryWatcherTestsConfig {

    @Bean
    DirectoryWatcher directoryWatcher(){
        return new DirectoryWatcherImpl();
    }

    @Bean
    FileParser textFileParser(){
        return new TextFileParser(new TokenCounterImpl());
    };


}
