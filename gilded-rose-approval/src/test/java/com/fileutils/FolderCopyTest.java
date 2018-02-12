package com.fileutils;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class FolderCopyTest {
    @Test
    public void approvalCopySrcFolder() throws Exception {
        Path outDir = Files.createTempDirectory("src");
        FolderCopy.copyFrom(Paths.get("."), outDir);
        Approvals.verifyEachFileInDirectory(outDir.toFile(), f -> f.getName().endsWith(".xml"));
    }
}