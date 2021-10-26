package com.diebuc.hicxsimplefileparser;

import com.diebuc.hicxsimplefileparser.directorywatcher.DirectoryWatcher;
import com.diebuc.hicxsimplefileparser.fileparser.FileParser;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;
import com.diebuc.hicxsimplefileparser.tokenparser.DotParser;
import com.diebuc.hicxsimplefileparser.tokenparser.TokenParser;
import com.diebuc.hicxsimplefileparser.tokenparser.WordParser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class HicxSimpleFileParserApplication implements CommandLineRunner {
    private static final Logger LOG =
            LoggerFactory.getLogger(HicxSimpleFileParserApplication.class);
    public static final String THE_MOST_FREQUEST_WORD_IS = "The Most frequest word is ";
    @Autowired
    DirectoryWatcher directoryWatcher;
    @Autowired
    @Qualifier("textFileParser")
    FileParser textFileParser;
    @Autowired
    @Qualifier("pdfFileParser")
    FileParser pdfFileParser;
    @Autowired
    @Qualifier("xlsFileParser")
    FileParser xlsFileParser;
    @Override
    public void run(String... args) throws Exception {
        LOG.info("EXECUTING : Simple File Parser");
        if (args.length == 0) {
            System.out.println("Missing directory parameter");
            return;
        }
        String directoryPath = args[0].toString();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            System.out.println("Directory doesn't exists");
            return;
        }
        List<FileParser> parsers = new ArrayList<>() {{
            add(textFileParser);
            add(pdfFileParser);
            add(xlsFileParser);
        }};
        List<TokenParser> tokenParsers = new ArrayList<>() {{
            add(new DotParser());
            add(new WordParser());
        }};
        directoryWatcher.startWatching(directoryPath, fileName -> {
            for (FileParser parser : parsers) {
                try {
                    if (parser.canParseFileTypeByFileName(fileName)) {
                        TokenStatsResult result = parser.parseFile(fileName, tokenParsers);
                        System.out.println(String.format("Stats-Counters of file [ %s ]", fileName));
                        System.out.println("======================================================");
                        result.getAllStatistics().forEach(stat -> {
                            if(stat.compareTo(DotParser.TOKEN_SYMBOL)!=0 && stat.compareTo("words")!=0)
                                System.out.println(String.format("%s [%s]: [%s] occurrences", "", stat, result.getStatistic(stat).toString()));
                            else
                                System.out.println(String.format("%s [%s]: [%s] occurrences", THE_MOST_FREQUEST_WORD_IS, stat, result.getStatistic(stat).toString()));
                        });
                    }
                } catch (UnsupportedOperationException e) {
                    //log.error(e.getMessage(), e);
                    System.out.println("Unsupported file format for parsing: " + fileName);
                }
            }
        }, true);
        return;
    }

    public static void main(String[] args) {
        SpringApplication.run(HicxSimpleFileParserApplication.class, args);
    }


}


