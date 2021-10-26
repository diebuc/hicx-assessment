package com.diebuc.hicxsimplefileparser.fileparser;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;
import com.diebuc.hicxsimplefileparser.tokenparser.TokenParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PdfFileParser extends FileParserImpl implements FileParser  {

	public static final String FILE_EXTENSION = ".pdf";

	TokenCounter tokenCounter;

	public PdfFileParser(TokenCounter tokenCounter) {
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
