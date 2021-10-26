package com.diebuc.hicxsimplefileparser.directorywatcher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.function.Consumer;

@Slf4j
@Component
public class DirectoryWatcherImpl implements DirectoryWatcher {
    String directoryPath;
    Path path;

    @Value("${directorywatcher.processed.directory:processed}")
    private String processedDirPath;

    private WatchService watchService;
    private WatchKey watchKey;

    public DirectoryWatcherImpl() {

    }

    @Override
    public void startWatching(String directoryPath, Consumer<String> parseFileConsumer,  Boolean processExistingFiles ) {
        this.directoryPath = directoryPath;
        try {
            File processedDir = new File( Paths.get(directoryPath, processedDirPath).toString());
            if(!processedDir.exists())
                processedDir.mkdir();
            watchService = FileSystems.getDefault().newWatchService();
            path = Paths.get(directoryPath);
            path.register(watchService,StandardWatchEventKinds.ENTRY_CREATE);
            if(processExistingFiles)
                processExistingFilesInDirectory(parseFileConsumer, path);

            log.debug("Wating for files");
            while ((watchKey = watchService.take()) != null) {
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    log.info("Event:" + event.kind() + ". File: " + event.context() + ".");
                    String fileName = event.context().toString();
                    processNewFileCopiedInDirectory(parseFileConsumer, fileName);
                }
                watchKey.reset();
            }
        } catch (IOException e) {
			log.error(e.getMessage(),e);
        } catch (InterruptedException e) {
			log.error(e.getMessage(),e);
        } catch (ClosedWatchServiceException e) {
            log.error(e.getMessage(),e);
        }
    }

    @Override
    public void stopWatching() {
        try {
            watchKey.cancel();
            watchService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: refactor duplicated code
    private void processNewFileCopiedInDirectory(Consumer<String> parseFileConsumer, String fileName) throws IOException {
        String sourceFilePath = Paths.get(path.toString(), fileName).toString();
        String processedFilePath = Paths.get(Paths.get(path.toString()).toString(),this.processedDirPath , fileName).toString();
        parseFileConsumer.accept(sourceFilePath);
        Files.move(
                Paths.get(sourceFilePath),
                Paths.get(processedFilePath),
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    private void processExistingFilesInDirectory(Consumer<String> parseFileConsumer, Path directoryPath) throws IOException {
        Files.list(directoryPath).forEach(file->{
            File fileForCheck = new File(file.toString());
            if(!fileForCheck.isFile()) return;
            String sourceFilePath = Paths.get(directoryPath.toString(),file.getFileName().toString()).toString();
            String processedFilePath = Paths.get(Paths.get(directoryPath.toString()).toString(),this.processedDirPath ,file.getFileName().toString()).toString();
            parseFileConsumer.accept(sourceFilePath);
            try {
                Files.move(
                        Paths.get(sourceFilePath),
                        Paths.get(processedFilePath),
                        StandardCopyOption.REPLACE_EXISTING
                );
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        });
    }

}
