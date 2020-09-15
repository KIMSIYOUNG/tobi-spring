package com.example.tobibookspring.calculator;

import java.io.IOException;

@FunctionalInterface
public interface LineCallBack<T> {
    T doSomethingWithLine(String line, T value) throws IOException;
}
