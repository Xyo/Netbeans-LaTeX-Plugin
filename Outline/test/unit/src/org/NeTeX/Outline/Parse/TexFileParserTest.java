/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTeX.Outline.Parse;

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
    
    @BeforeClass
    public static void setUpClass() {
        String file = "C:\\Users\\Jeremy\\Documents\\example.tex";
        TexFile doc = new TexFile(file);
        try{
            TexFileParser parser = new TexFileParser(doc);
            parser.beginParse();
        }catch( FileNotFoundException e ){
            e.printStackTrace();
        }
       
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
    public void testAddNewNode() {
        System.out.println("addNewNode");
        String type = "";
        String name = "";
        int start = 0;
        boolean complete = false;
        TexFileParser instance = null;
        ElementNode expResult = null;
        ElementNode result = instance.addNewNode(type, name, start, complete);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

    /**
     * Test of getDocClass method, of class TexFileParser.
     */
    @Test
    public void testGetDocClass() throws Exception {
        System.out.println("getDocClass");
        BufferedReader br = null;
        TexFileParser instance = null;
        String expResult = "";
        String result = instance.getDocClass(br);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toBeginOfDoc method, of class TexFileParser.
     */
    @Test
    public void testToBeginOfDoc() throws Exception {
        System.out.println("toBeginOfDoc");
        BufferedReader br = null;
        TexFileParser instance = null;
        instance.toBeginOfDoc(br);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseLine method, of class TexFileParser.
     */
    @Test
    public void testParseLine() throws Exception {
        System.out.println("parseLine");
        BufferedReader br = null;
        TexFileParser instance = null;
        boolean expResult = false;
        boolean result = instance.parseLine(br);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
