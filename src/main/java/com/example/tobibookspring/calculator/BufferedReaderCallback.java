package com.example.tobibookspring.calculator;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderCallback {
    Integer doSomethingWithLine(String line, Integer value) throws IOException;
}
