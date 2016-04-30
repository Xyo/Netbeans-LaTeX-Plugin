package org.NeTex.Outline.Parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jeremy
 */
public class TexFileParserTest {
    
    public TexFileParserTest() {
    }
    
    public static void main(String[] args){
        setUpClass();
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }   
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addNewNode method, of class TexFileParser.
     */
    @Test
    public void testAddNewBean() {
        System.out.println("addNewNode");
        String type = "";
        String name = "";
        int start = 0;
        boolean complete = false;
        
        TexFileParser instance = null;
        try{
            instance = new TexFileParser( new TexFile("C:\\\\Users\\\\Jeremy\\\\Documents\\\\example.tex") );
        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        String nonsense = "//part{PartTitle}blahblah//section{SectionTitle}";
        ElementBean expResult = null;
        ElementBean result = instance.createNewBean("//chapter{ChapterTitle}");
        System.out.println(result.toString());
        assertNotEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of beginParse method, of class TexFileParser.
     */
    @Test
    public void testBeginParse() {
        System.out.println("beginParse");
        TexFileParser instance = null;
        instance.beginParse();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

//    /**
//     * Test of getDocClass method, of class TexFileParser.
//     */
//    @Test
//    public void testGetDocClass() throws Exception {
//        System.out.println("getDocClass");
//        BufferedReader br = null;
//        TexFileParser instance = null;
//        String expResult = "";
//        String result = instance.getDocClass(br);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toBeginOfDoc method, of class TexFileParser.
//     */
//    @Test
//    public void testToBeginOfDoc() throws Exception {
//        System.out.println("toBeginOfDoc");
//        BufferedReader br = null;
//        TexFileParser instance = null;
//        instance.toBeginOfDoc(br);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of parseLine method, of class TexFileParser.
     */
    @Test
    public void testParseLine() throws Exception {
        System.out.println("parseLine");
        BufferedReader br = null;
        TexFileParser instance = null;
        boolean expResult = false;
        boolean result = instance.parseLine("//part{PartTitle}blahblah//section{SectionTitle}");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}