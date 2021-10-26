package com.diebuc.hicxsimplefileparser.fileparser;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;
import com.diebuc.hicxsimplefileparser.tokenparser.TokenParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;

import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class XlsFileParser extends FileParserImpl implements FileParser {

	public static final String FILE_EXTENSION = ".xls";
	
	public XlsFileParser(TokenCounter tokenCounter) {
		super(tokenCounter);
	}

	@Override
	public TokenStatsResult parseFile(String fileName, List<TokenParser> tokenParsers) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Boolean canParseFileTypeByFileName(String fileName) {
		return false;
	}

}
