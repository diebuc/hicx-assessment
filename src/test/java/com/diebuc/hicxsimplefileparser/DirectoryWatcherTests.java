package com.diebuc.hicxsimplefileparser;


import com.diebuc.hicxsimplefileparser.config.DirectoryWatcherTestsConfig;
import com.diebuc.hicxsimplefileparser.directorywatcher.DirectoryWatcher;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = { DirectoryWatcherTestsConfig.class })
public class DirectoryWatcherTests
{

	public static final String FILEWATCHER_TEST_TXT = "filewatcher_test.txt";

	@Autowired
	DirectoryWatcher directoryWatcher;

	@Value("${directorywatcher.processed.directory:processed}")
	private String processedDirPath;

	@AfterAll
	public static void clean(){
		deleteTestFiles();
	}

	@BeforeAll
	public static void setup(){
		deleteTestFiles();
	}

	@Test
	public void testCanStartWatchingADirectory()
    {
		//delayed file creation
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		final Runnable createNewFileTask = new Runnable() {
			@Override
			public void run() {
				try {
					createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					fail();
				}
			}
		};
		scheduler.schedule(createNewFileTask, 5, TimeUnit.SECONDS);

		final Runnable stopDirectoryWatcher = new Runnable() {
			@Override
			public void run() {
				directoryWatcher.stopWatching();
			}
		};
		scheduler.schedule(stopDirectoryWatcher, 10, TimeUnit.SECONDS);

		// current dir
		String directoryPath =  System.getProperty("user.dir");
		directoryWatcher.startWatching(directoryPath, fileName  -> {
			Assert.isTrue(1==1, "new file detected!");
		}, false);

    }

	private void createNewFile()
		throws IOException {
		String str = "aaa aaa aaa bbb";
		BufferedWriter writer = new BufferedWriter(new FileWriter(FILEWATCHER_TEST_TXT));
		writer.write(str);
		writer.close();
	}

	private static void deleteTestFiles() {
		try {
			if(Files.exists(Paths.get(System.getProperty("user.dir"),FILEWATCHER_TEST_TXT)))
				Files.delete(Paths.get(System.getProperty("user.dir"),FILEWATCHER_TEST_TXT));
			if(Files.exists(Paths.get(System.getProperty("user.dir"), "processed", FILEWATCHER_TEST_TXT)))
				Files.delete(Paths.get(System.getProperty("user.dir"), "processed", FILEWATCHER_TEST_TXT));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
