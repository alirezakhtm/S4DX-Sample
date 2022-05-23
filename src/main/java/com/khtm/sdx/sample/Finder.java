package com.khtm.sdx.sample;

import com.khtm.sdx.sample.exception.StringArrayNullOrEmptyException;
import com.khtm.sdx.sample.exception.StringSearchNullOrEmptyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class designed to search specific characters in string array
 *
 * @author Alireza Khatami Doost [alireza.khtm@gmail.com]
 * */
public class Finder {

    private final String[] input;
    private String wantedString;
    private List<String> patterns = new ArrayList<>();
    private static final String ALL_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ";


    public Finder(String[] input){
        if(input == null || input.length == 0) throw new StringArrayNullOrEmptyException();
        this.input = input;
    }

    /**
     * This function search specific characters that belong to input string
     * Caution: Uppercase letters are not equal to lowercase letters
     *
     * @param s is wanted string that we want to search all its characters in string array
     * @return all values that contain characters of input
     * */
    public String[] find(String s){
        if(s == null || s.equals("")) throw new StringSearchNullOrEmptyException();
        this.setWantedString(s);
        ArrayList<String> arrayList = Arrays.stream(this.input)
                .filter(this::findCharacter)
                .collect(Collectors.toCollection(ArrayList::new));
        String[] ans = new String[arrayList.size()];
        ans = arrayList.toArray(ans);
        return ans;
    }

    /**
     * This function able us that we can set value wantedString parameter and set null for pattern of regex
     *
     * @param s new value of wantedString
     * */
    private void setWantedString(String s) {
        this.patterns.clear();
        this.wantedString = s;
    }

    /**
     * find characters with created patterns of regex
     *
     * @param s is string value that may contain specific characters
     * @return true if input contain specific characters, false if input doesn't contain specific characters
     * */
    private boolean findCharacter(String s) {
        this.createRegexPattern();
        boolean ans = true;
        int patternLength = patterns.size();
        int count = 1;
        for(String pattern : patterns){
            ans = ans && ((count++ < patternLength) == Pattern.compile(pattern).matcher(s).find());
        }
        return ans;
    }

    /**
     * this function generate patterns for regex
     * */
    private void createRegexPattern(){
        if(this.patterns.size() > 0) return; // to avoid to make patterns more times.
        String otherChr = ALL_ALPHABET;
        String[] characters = this.wantedString.split("|");
        for(String ch : characters){
            this.patterns.add(ch+"+");
            otherChr = otherChr.replace(ch, "");
        }
        this.patterns.add(String.format("[%s]+", otherChr));
    }

}
