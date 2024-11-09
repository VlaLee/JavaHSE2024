package Interfaces;

import java.util.HashMap;

public interface FrequencyDictionary {

    void fillDict() throws Exception;
    HashMap<Character, Integer> getDict();
}
