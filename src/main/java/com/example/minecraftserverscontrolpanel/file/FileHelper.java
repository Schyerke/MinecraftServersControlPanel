package com.example.minecraftserverscontrolpanel.file;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public enum FileHelper {
    INSTANCE;

    private final String FILENAME = "servers.csv";

    public void saveServer(String serverName, String serverPath) {
        serverName = serverName.toLowerCase();
        if (checkDoubleServer(serverName)) {
            return;
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILENAME, true))) {
            String[] data = {serverName, serverPath};
            writer.writeNext(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CsvServerRecord> findAll() {
        try (CSVReader reader = new CSVReader(new FileReader(FILENAME))) {
            return reader.readAll().stream()
                    .map(data -> new CsvServerRecord(data[0], data[1]))
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public CsvServerRecord findServer(String serverName) {
        serverName = serverName.toLowerCase();
        CsvServerRecord serverRecord = null;
        try (CSVReader reader = new CSVReader(new FileReader(FILENAME))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals(serverName)) {
                    serverRecord = new CsvServerRecord(nextLine[0], nextLine[1]);
                    break;
                }
            }
        } catch (FileNotFoundException ignored) {
            System.err.println("File not found");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return serverRecord;
    }

    public void deleteServer(String serverName) {
        serverName = serverName.toLowerCase();
        try (CSVReader reader = new CSVReader(new FileReader(FILENAME))) {
            List<String[]> allElements = reader.readAll();
            for (String[] element : allElements) {
                if (element[0].equals(serverName)) {
                    allElements.remove(element);
                }
            }
            FileWriter sw = new FileWriter(FILENAME);
            CSVWriter writer = new CSVWriter(sw);
            writer.writeAll(allElements);
        } catch (FileNotFoundException ignored) {
            System.err.println("File not found");
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private boolean checkDoubleServer(String serverName) {
        try (CSVReader reader = new CSVReader(new FileReader(FILENAME))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals(serverName)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return false;
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createBatFile(CsvServerRecord serverRecord) {
        String parentDirectory = serverRecord.serverPath().substring(0, serverRecord.serverPath().lastIndexOf("/"));
        List<String> lines = List.of(
                "cd /d " + parentDirectory,
                "java -jar " + serverRecord.serverPath()
        );
        Path file = Paths.get(PathHelper.INSTANCE.getBatFilePath(serverRecord.serverName()));
        try{
            Files.write(file, lines, StandardCharsets.UTF_8);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}