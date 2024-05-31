package com.example.resizeimage2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AllChangeToolsTest {

    private AllChangeTools allChangeTools;

    @BeforeEach
    void setUp() {
        allChangeTools = new AllChangeTools();
    }

    @Test
    void changeLines() throws IOException {
        String input = "## 안녕하세요\n"
                + "\n"
                + "![image](https://github.com/muyaaho/http-basic/assets/76798969/e37dd643-9cb8-4f5d-8427-d7ce36202992)\n"
                + "\n"
                + "\n"
                + "\n"
                + "당신은 굿이에요\n"
                + "\n"
                + "![image](https://github.com/muyaaho/http-basic/assets/76798969/a85df2cf-95e4-4ae1-8a09-ea83b0d9c9b1)";
        int inputSize = 80;
        String answer = "## 안녕하세요\n"
                + "\n"
                + "<img src=https://github.com/muyaaho/http-basic/assets/76798969/e37dd643-9cb8-4f5d-8427-d7ce36202992 width=\"80%\" height=\"80%\"/><br>\n"
                + "\n"
                + "\n"
                + "\n"
                + "당신은 굿이에요\n"
                + "\n"
                + "<img src=https://github.com/muyaaho/http-basic/assets/76798969/a85df2cf-95e4-4ae1-8a09-ea83b0d9c9b1 width=\"80%\" height=\"80%\"/><br>";

        assertEquals(answer, allChangeTools.changeLines(input, inputSize));

    }
}