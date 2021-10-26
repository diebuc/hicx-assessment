package com.diebuc.hicxsimplefileparser.fileparser;

import com.diebuc.hicxsimplefileparser.tokencounter.TokenCounter;
import com.diebuc.hicxsimplefileparser.tokencounter.TokenStatsResult;
import com.diebuc.hicxsimplefileparser.tokenparser.TokenParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Slf4j
@Component
public class TextFileParser extends FileParserImpl implements FileParser {

	public static final String FILE_EXTENSION = ".txt";

	List<TokenParser> tokenParsers;

	public TextFileParser(TokenCounter tokenCounter) {
		super(tokenCounter);
	}

	public TokenStatsResult parseFile(String fileName, List<TokenParser> tokenParsers) {
		TokenStatsResult result = new TokenStatsResult();
		tokenCounter.cleanCounters();;
		Scanner scanner;
		try {
			for (TokenParser tokenParser: tokenParsers ) {
				File file = new File(fileName);
				scanner = new Scanner(file);
				tokenParser.parse(scanner,tokenCounter,result, "[\\ |\\.\\,\\;\\:\\r\\n]");
				scanner.close();
			}

		} catch (FileNotFoundException e) {
			log.error(e.getMessage(),e);
		}
		return result;
	}

	@Override
	public Boolean canParseFileTypeByFileName(String fileName) {
		return fileName.toLowerCase(Locale.ROOT).endsWith(FILE_EXTENSION);
	}
}
