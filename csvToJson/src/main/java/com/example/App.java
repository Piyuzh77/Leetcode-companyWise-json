package com.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Stream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class App {

    public static void main(String[] args) {
        String inputDirectory = "LeetCode-Questions-CompanyWise-master";
        String outputFilePath = "output.json";

        try {
            Map<String, Problems> data = processCsvDirectory(Paths.get(inputDirectory));
            saveToJson(data.values(), Paths.get(outputFilePath));
            System.out.println("JSON saved at: " + outputFilePath);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Map<String, Problems> processCsvDirectory(Path directory) throws IOException {
        Map<String, Problems> map = new HashMap<>();
        try (Stream<Path> paths = Files.walk(directory)) {
            paths.filter(Files::isRegularFile).forEach(file -> {
                String filename = file.getFileName().toString();
                Pattern pattern = Pattern.compile("([\\w-]+)_.*\\.csv$"); 
                Matcher matcher = pattern.matcher(filename);

                if (matcher.matches()) {
                    String companyName = matcher.group(1).toLowerCase();
                    try {
                        processCsvFile(file, companyName, map);
                    } catch (Exception e) {
                        System.err.println("Error processing file: " + file.toString());
                        e.printStackTrace();
                    }
                }
            });
        }
        return map;
    }

    private static void processCsvFile(Path file, String companyName, Map<String, Problems> map)
            throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(Files.newBufferedReader(file))) {
            String[] headers = reader.readNext();
            if (headers == null) throw new EmptyFileException("Empty CSV file: " + file);

            List<String> requiredHeaders = Arrays.asList("ID", "Title", "Difficulty", "Leetcode Question Link");
            for (String header : requiredHeaders) {
                if (!Arrays.asList(headers).contains(header)) {
                    throw new IllegalArgumentException("Missing required header: " + header);
                }
            }

            String[] row;
            while ((row = reader.readNext()) != null) {
                String probId = row[getIndex(headers, "ID")];
                String title = row[getIndex(headers, "Title")];
                String difficulty = row[getIndex(headers, "Difficulty")];
                String url = row[getIndex(headers, "Leetcode Question Link")];

                map.computeIfAbsent(probId, id -> new Problems(id, title, difficulty, url))
                   .getCompanies().add(companyName);
            }
        }
    }

    private static int getIndex(String[] row, String entityName) {
        for (int i = 0; i < row.length; i++) {
            if (row[i].equalsIgnoreCase(entityName)) return i;
        }
        return -1;
    }

    private static void saveToJson(Collection<Problems> problems, Path outputPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, problems);
        }
    }

    public static class EmptyFileException extends RuntimeException {
        public EmptyFileException(String message) {
            super(message);
        }
    }
}
