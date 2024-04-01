package com.ua.project.Autobase.services;

import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Component
public class TxtFileReader {
    private String fileName;

    public List<String> readFile() throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(this.fileName))) {
            return stream.collect(Collectors.toList());
        }
    }
}
