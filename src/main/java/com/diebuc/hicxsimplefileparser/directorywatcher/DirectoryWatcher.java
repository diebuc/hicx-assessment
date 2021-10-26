package com.diebuc.hicxsimplefileparser.directorywatcher;

import java.util.function.Consumer;

public interface DirectoryWatcher {
    void startWatching(String directoryPath, Consumer<String> callbackNewFiledCrated, Boolean processExistingFiles);

    void stopWatching();
}
