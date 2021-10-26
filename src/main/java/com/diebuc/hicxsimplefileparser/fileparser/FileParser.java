package com.diebuc.hicxsimplefileparser.fileparser;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;
import com.diebuc.hicxsimplefileparser.tokenparser.TokenParser;

import java.util.List;

public interface FileParser {
    TokenStatsResult parseFile(String fileName, List<TokenParser> tokenParsers);
    Boolean canParseFileTypeByFileName(String fileName);
}
