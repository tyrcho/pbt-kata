package com.fileutils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderCopy {
    static void copyFrom(Path from, Path to) throws IOException {
        Files
                .list(from)
                .forEach(file -> {
                    try {
                        Path target = Paths.get(to.toString(), file.getFileName().toString());
                        Files.copy(file, target);
                        if (file.toFile().isDirectory()) {
                            Path sourceDir = Paths.get(from.toString(), file.getFileName().toString());
                            copyFrom(sourceDir, target);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
