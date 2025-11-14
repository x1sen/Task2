package com.example.text_task.reader;

import com.example.text_task.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextReader {
    private static final Logger logger = LogManager.getLogger();

    public String readFile(String filePath) throws TextException {
        try {
            String content = Files.readString(Paths.get(filePath));
            logger.info("Successfully read file: {}", filePath);
            return content;
        } catch (IOException e) {
            logger.error("Failed to read file: {}", filePath, e);
            throw new TextException("Error reading file: " + filePath, e);
        }
    }
}
