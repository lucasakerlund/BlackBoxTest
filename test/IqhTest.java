
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class IqhTest {

    //countConsonants method
    @Test
    void testCountConsonantsWhenLowercase(){
        int result = Iqh.countConsonants("hej");
        assertEquals(2, result);
    }

    @Test
    void testCountConsonantsWhenUppercase(){
        int result = Iqh.countConsonants("HEJ");
        assertEquals(2, result);
    }

    @Test
    void testCountConsonantsWithSpaces(){
        int result = Iqh.countConsonants("H E j");
        assertEquals(2, result);
    }

    @Test
    void testCountConsonantsWithNumbers(){
        int result = Iqh.countConsonants("1234567890");
        assertEquals(0, result);
    }

    @Test
    void testCountConsonantsWithSymbols(){
        int result = Iqh.countConsonants("%¤#!)(^*");
        assertEquals(0, result);
    }

    private void checkValidConsonants(String sequence, int expectedPerChar, String failMessage){
        List<Character> faultList = new ArrayList<>();
        for(char c : sequence.toCharArray()){
            int result = Iqh.countConsonants(String.valueOf(c));
            if(expectedPerChar != result){
                faultList.add(c);
            }
        }
        if(!faultList.isEmpty()){
            fail(String.format(failMessage, faultList));
        }
    }

    @Test
    void testIfAllConsonantsAreValidWithLowercase(){
        checkValidConsonants("bcdfghjklmnpqrstvwxz", 1, "Didnt count %s as a consonant.");
    }

    @Test
    void testIfAllConsonantsAreValidWithUppercase(){
        checkValidConsonants("BCDFGHJKLMNPQRSTVWXZ", 1, "Didnt count %s as a consonant.");
    }

    @Test
    void testIfVocalsAreConsonantsWithLowercase(){
        checkValidConsonants("aoueiy", 0, "Counted %s as consonants.");
    }

    @Test
    void testIfVocalsAreConsonantsWithUppercase(){
        checkValidConsonants("AOUEIY", 0, "Counted %s as consonants.");
    }

    @Test
    void testConsonantsIfEmpty(){
        int result = Iqh.countConsonants("");
        assertEquals(0, result);
    }

    @Test
    void testConsonantsIfNull(){
        int result = Iqh.countConsonants(null);
        assertEquals(0, result);
    }
    //
    //countVowels method
    @Test
    void testCountVowelsWhenLowercase(){
        int result = Iqh.countVowels("hej");
        assertEquals(1, result);
    }

    @Test
    void testCountVowelsWhenUppercase(){
        int result = Iqh.countVowels("HEJ");
        assertEquals(1, result);
    }

    @Test
    void testCountVowelsWithSpaces(){
        int result = Iqh.countVowels("H E j");
        assertEquals(1, result);
    }

    @Test
    void testCountVowelsWithNumbers(){
        int result = Iqh.countVowels("1234567890");
        assertEquals(0, result);
    }

    @Test
    void testCountVowelsWithSymbols(){
        int result = Iqh.countVowels("%¤#!)(^*");
        assertEquals(0, result);
    }

    private void checkValidVowels(String sequence, int expectedPerChar, String failMessage){
        List<Character> faultList = new ArrayList<>();
        for(char c : sequence.toCharArray()){
            int result = Iqh.countVowels(String.valueOf(c));
            if(expectedPerChar != result){
                faultList.add(c);
            }
        }
        if(!faultList.isEmpty()){
            fail(String.format(failMessage, faultList));
        }
    }

    @Test
    void testIfAllVowelsAreValidWithLowercase(){
        checkValidVowels("aoueiy", 1, "Didnt count %s as a vowel."); //Kolla med Bill om ÅÄÖ
    }

    @Test
    void testIfAllVowelsAreValidWithUppercase(){
        checkValidVowels("AOUEIY", 1, "Didnt count %s as a vowel."); //Kolla med Bill om ÅÄÖ
    }

    @Test
    void testIfConsonantsAreVocalsWithLowercase(){
        checkValidVowels("bcdfghjklmnpqrstvwxz", 0, "Counted %s as vowels.");
    }

    @Test
    void testIfConsonantsAreVocalsWithUppercase(){
        checkValidVowels("BCDFGHJKLMNPQRSTVWXZ", 0, "Counted %s as vowels.");
    }

    @Test
    void testVowelsIfEmpty(){
        int result = Iqh.countVowels("");
        assertEquals(0, result);
    }

    @Test
    void testVowelsIfNull(){
        int result = Iqh.countVowels(null);
        assertEquals(0, result);
    }
    //
    //invert method
    @Test
    void testInvertWithLowercase(){
        String result = Iqh.invert("hej");
        assertEquals("jeh", result);
    }

    @Test
    void testInvertWithUppercase(){
        String result = Iqh.invert("HEJ");
        assertEquals("JEH", result);
    }

    @Test
    void testInvertWithSpaces(){
        String result = Iqh.invert("H e j");
        assertEquals("j e H", result);
    }

    /*
    * Att testa nummer såhär på en string är 99% overkill, eftersom en programmerare hade nog behövt medvetet göra ett sånt här fel
    * om man inte hade lagt alla "tillåtna" symboler i en array.
    * */
    @Test
    void testInvertWithNumbers(){
        String result = Iqh.invert("0123456789");
        assertEquals("9876543210", result);
    }

    @Test
    void testInvertIfEmpty(){
        String result = Iqh.invert("");
        assertEquals("", result);
    }

    @Test
    void testInvertIfNull(){
        String result = Iqh.invert(null);
        assertEquals("", result);
    }
    //
    //lowerHalf method
    @Test
    void testLowerHalfIfNotEven(){
        String result = Iqh.lowerHalf("hej");
        assertEquals(null, result);
    }

    @Test
    void testLowerHalfIfEven(){
        String result = Iqh.lowerHalf("heje");
        assertEquals("he", result);
    }

    @Test
    void testLowerHalfIfNotEvenWithSpaces(){
        String result = Iqh.lowerHalf("he je");
        assertEquals(null, result);
    }

    @Test
    void testLowerHalfIfEvenWithSpaces(){
        String result = Iqh.lowerHalf("he j");
        assertEquals("he", result);
    }

    @Test
    void testLowerHalfIfNotEvenWithOnlySpaces(){
        String result = Iqh.lowerHalf("   "); //Kolla med Bill om det är okej att göra såna här "onödiga tester"
        assertEquals(null, result);
    }

    @Test
    void testLowerHalfIfEvenWithOnlySpaces(){
        String result = Iqh.lowerHalf("  ");
        assertEquals(" ", result);
    }

    @Test
    void testLowerHalfIfEmpty(){
        String result = Iqh.lowerHalf("");
        assertEquals("", result);
    }

    @Test
    void testLowerHalfIfNull(){
        String result = Iqh.lowerHalf(null);
        assertEquals("", result);
    }
    //
    //readString method
    private File createFile(String path, String content){
        File file = new File(path);
        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath(), false);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Test
    void testReadString(){
        File file = createFile("test/files/test.txt", "1231231231asd asd \nad d");

        String s = Iqh.readString("sfqfaa");
        System.out.println(s);
    }
    //
    //removeVowels method

    //
    //scatterString method
    private boolean hasSameContent(String str1, String str2){
        char[] str1Chars = str1.toCharArray();
        Arrays.sort(str1Chars);
        str1 = String.valueOf(str1Chars);

        char[] str2Chars = str2.toCharArray();
        Arrays.sort(str2Chars);
        str2 = String.valueOf(str2Chars);
        System.out.println("input: " + str1 + " \nresult:" + str2);
        return str1.equals(str2);
    }

    @Test
    void testScatterStringIfRandomizing(){
        String input = "randomizing";
        for(int i = 0; i<10; i++){
            String result = Iqh.scatterString(input);
            if(!input.equals(result)){
                return;
            }
        }
        fail("Didnt randomize while scattering 10 times.");
    }

    @Test
    void testScatterStringWhenLowercase(){
        String input = "asd";
        String result = Iqh.scatterString(input);
        assertTrue(hasSameContent(input, result));
    }

    @Test
    void testScatterStringWhenUppercase(){
        String input = "ASD";
        String result = Iqh.scatterString(input);
        assertTrue(hasSameContent(input, result));
    }

    @Test
    void testScatterStringWithNumbers(){
        String input = "SAdefws#";
        String result = Iqh.scatterString(input);
        assertTrue(hasSameContent(input, result));
    }

    @Test
    void test(){
        String input = "aaaa";
        String result = Iqh.scatterString(input);
        assertTrue(hasSameContent(input, result));
    }
    //


}