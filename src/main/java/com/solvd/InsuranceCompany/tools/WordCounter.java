package com.solvd.InsuranceCompany.tools;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class WordCounter {

    private static final String INPUT_PATH = "src/main/resources/insurance.txt";
    private static final String OUTPUT_PATH = "src/main/resources/output.txt";
    private static final String TARGET_WORD = "Request";

    public static void runAnalysis(String inputPath, String outputPath, String targetWord) {
        List<String> lines;
        try {
            lines = FileUtils.readLines(new File(inputPath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading the input file: " + e.getMessage(), e);
        }

        String normalizedTargetWord = targetWord.toLowerCase();

        int totalOccurrences = lines.stream()
                .map(String::toLowerCase)
                .mapToInt(line -> StringUtils.countMatches(line, normalizedTargetWord))
                .sum();

        String resultLine = "Word: " + targetWord + " | Count: " + totalOccurrences + " | Status: Processed\n";

        try {
            FileUtils.writeStringToFile(new File(outputPath), resultLine, StandardCharsets.UTF_8, true);
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the output file: " + e.getMessage(), e);
        }
    }
}