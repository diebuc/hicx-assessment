package com.diebuc.hicxsimplefileparser.fileparser;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;
import com.diebuc.hicxsimplefileparser.tokenparser.TokenParser;

import java.util.List;

public abstract class FileParserImpl implements FileParser {

    public static final String FILE_EXTENSION = "NONE";
    protected TokenCounter tokenCounter;
    protected List<TokenParser> tokenParsers;

    protected FileParserImpl(TokenCounter tokenCounter){
        this.tokenCounter = tokenCounter;
    }

    @Override
    public abstract  TokenStatsResult parseFile(String fileName, List<TokenParser> tokenParsers);

    @Override
    public abstract Boolean canParseFileTypeByFileName(String fileName);
}
