package com.khtm.sdx.sample;


import com.khtm.sdx.sample.exception.StringArrayNullOrEmptyException;
import com.khtm.sdx.sample.exception.StringSearchNullOrEmptyException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class FinderTest {

    /**
     * Simple test with limited String array.
     * */
    @Test
    public void firstTest(){

        String[] thisIsAStringArray = new String[7];
        thisIsAStringArray[0] = "asd";
        thisIsAStringArray[1] = "asdd";
        thisIsAStringArray[2] = "fre";
        thisIsAStringArray[3] = "glk";
        thisIsAStringArray[4] = "lkm";
        thisIsAStringArray[5] = "asddff";
        thisIsAStringArray[6] = "asddaas";

        String[] desiredAnswer = new String[]{"asd","asdd","asddaas"};

        Finder finder = new Finder(thisIsAStringArray);
        String[] ans = finder.find("sad");

        Assert.assertArrayEquals(desiredAnswer, ans);
    }

    /**
     * Simple test with limited String array.
     * */
    @Test
    public void secondTest(){

        String[] thisIsAStringArray = new String[4];
        thisIsAStringArray[0] = "mmzt";
        thisIsAStringArray[1] = "zttz";
        thisIsAStringArray[2] = "ttzt";
        thisIsAStringArray[3] = "mttm";

        String[] desiredAnswer = new String[]{"mmzt"};

        Finder finder = new Finder(thisIsAStringArray);
        String[] ans = finder.find("mzt");

        Assert.assertArrayEquals(desiredAnswer, ans);
    }

    /**
     * test for StringArrayNullOrEmptyException
     * */
    @Test
    public void StringArrayNullOrEmptyTest(){
        String[] thisIsAStringArray = null;

        Exception exception =  Assert.assertThrows(StringArrayNullOrEmptyException.class, () -> {
            new Finder(thisIsAStringArray);
        });

        String expectedMessage = "String array is null or empty.";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * test for StringSearchNullOrEmptyException
     * */
    @Test
    public void StringSearchNullOrEmptyTest(){
        String[] thisIsAStringArray = new String[4];
        thisIsAStringArray[0] = "mmzt";
        thisIsAStringArray[1] = "zttz";
        thisIsAStringArray[2] = "ttzt";
        thisIsAStringArray[3] = "mttm";

        Exception exception = Assert.assertThrows(StringSearchNullOrEmptyException.class, () -> {
            Finder finder = new Finder(thisIsAStringArray);
            String[] ans = finder.find("");
        });

        String expectedMessage = "String search is null or empty.";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));

    }


    /**
     * In this test case, 60k records generated and used as input
     * according to requirement to measure performance this test case implemented.
     * */
    @Test
    public void performaceTest(){
        String[] input = new String[60000];
        for (int i = 0; i < 60000 - 4; i++) {
            input[i] = this.generateRandomString();
        }
        // add expected value to our input data
        // according to generateRandomString function, we sure that m, z and t characters don't exist in generated string
        input[59996] = "mmzt";
        input[59997] = "zttz";
        input[59998] = "ttzt";
        input[59999] = "mttm";

        String[] expectedAnswer01 = new String[]{"zttz", "ttzt"};
        String[] expectedAnswer02 = new String[]{"mttm"};
        String[] expectedAnswer03 = new String[]{"mmzt"};

        Finder finder = new Finder(input);

        String[] answer01 = finder.find("tz");
        String[] answer02 = finder.find("mt");
        String[] answer03 = finder.find("mzt");

        Assert.assertArrayEquals(expectedAnswer01, answer01);
        Assert.assertArrayEquals(expectedAnswer02, answer02);
        Assert.assertArrayEquals(expectedAnswer03, answer03);
    }

    /**
     * this function generate random String for Unit test.
     * */
    private String generateRandomString(){
        // this pattern contains all character except m, z and t, because I wanna create specific expected answer for performance test.
        String pattern = "abcdefghijklnopqrsuvwxy";
        String[] characters = pattern.split("|");
        StringBuilder wordStringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int intRandom = random.nextInt(22);
            wordStringBuilder.append(characters[intRandom]);
        }
        return wordStringBuilder.toString();
    }

}
