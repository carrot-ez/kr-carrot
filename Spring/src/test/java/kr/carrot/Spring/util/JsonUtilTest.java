package kr.carrot.Spring.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    @Test
    public void JSON읽기() {
        try {
            List list = JsonUtil.readJson(List.class, "gameTypes.json");
            System.out.println(list.get(0));

        }
        catch(IOException ie) {
            System.out.println(ie.getMessage());
        }

    }
}