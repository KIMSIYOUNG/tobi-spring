package com.example.tobibookspring.calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public <T> T lineReadTemplate(String path, LineCallBack<T> callback, T initVal) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            T res = initVal;
            String line = null;
            while ((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }
            return res;
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public Integer calcSum(String path) {
        return lineReadTemplate(path, (line, value) -> value + Integer.parseInt(line), 0);
    }

    public int calcMultiply(String path) {
        return lineReadTemplate(path, (line, value) -> value * Integer.parseInt(line), 1);
    }
}
