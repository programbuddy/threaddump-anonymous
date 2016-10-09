package com.programbuddy.threaddump.anonymous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.UUID.randomUUID;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) throws IOException {
        String outputMap = args[0];

        String java = "(([\\p{L}_$][\\p{L}\\p{N}_$]*\\.)*[\\p{L}_$][\\p{L}\\p{N}_$]*)";
        String[] regex = {"a " + java, "at " + java, "\\(" + java + "(\\:[0-9]+)\\)"};
        List<Pattern> patterns = Arrays.asList(regex).stream().map(x -> Pattern.compile(x)).collect(toList());

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Stream<String> lines = in.lines();

        List<Tuple<String, Optional<Tuple<String, String>>>> replaced =
                lines.map(x ->
                        new Tuple<String, Optional<Tuple<String, String>>>(x,
                                patterns.stream()
                                        .map(y -> y.matcher(x))
                                        .flatMap(y -> getStrings(y).stream())
                                        .map(y -> new Tuple<String, String>(randomUUID().toString(), y)).findFirst()))
                        .collect(toList());

        Path pathOutPath = Paths.get(outputMap);
        if (!Files.exists(pathOutPath)) {
            Files.createFile(pathOutPath);
        }
        Files.write(pathOutPath, replaced.stream()
                .filter(x -> x.getEl2().isPresent())
                .map(x -> x.getEl2().get().getEl1() + "=" + x.getEl2().get().getEl2())
                .collect(toList()));
        for (Tuple<String, Optional<Tuple<String, String>>> tuple : replaced) {
            System.out.println(tuple.getEl2().isPresent() ? tuple.getEl1().replace(tuple.getEl2().get().getEl2(), tuple.getEl2().get().getEl1()) : tuple.getEl1());
        }
    }

    private static ArrayList<String> getStrings(Matcher m) {
        ArrayList<String> matches = new ArrayList<>();
        while (m.find()) {
            matches.add(m.group(1));
        }
        return matches;
    }
}
