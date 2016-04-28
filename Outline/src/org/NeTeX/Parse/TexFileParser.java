/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTeX.Parse;

/**
 *
 * @author Jeremy
 */

import java.io.*;
import java.util.ArrayList;

public class TexFileParser {
    private TexFile doc;
    private BufferedReader br;
    private int lineCounter = 0;
    private ArrayList<ElementNode> addedNodes = new ArrayList<>();
    private ElementNodeFactory nodeFactory = new ElementNodeFactory();
    
    TexFileParser( TexFile doc ) throws FileNotFoundException {
        if( doc == null ) throw new FileNotFoundException( "The file is missing" );
        this.doc = doc;
    }
    
    public ElementNode addNewNode(String type, String name, int start, boolean complete) throws IllegalArgumentException {
        ElementNode newNode = nodeFactory.createNode(type, name, start, complete);
        if( newNode == null ) throw new IllegalArgumentException("Invalid paramaters here... " + type + name + start + complete );
        return newNode;
    }
 
    // parses the tex file to build the tree structure
    public void beginParse() {
        
        try( BufferedReader br = 
                new BufferedReader(new FileReader(doc.getFilePath()))   ){

            String documentClass = getDocClass(br);
            
            parseLine(br);
        }catch( IOException e ){}  
    }
    


    //get document class type to name tex tree
    public String getDocClass(BufferedReader br) throws IOException {
        String line = "";
        do{
            ++lineCounter;
            br.readLine();
        }while(!line.contains("\\documentclass{"));
        // parse name between { }
        return ParserUtilities.parseName(line);
    }

    // get end of preamble (and beginning of doc)
    public void toBeginOfDoc( BufferedReader br ) throws IOException {
        
    }
    
    // Gets the next non-empty line in the document
    public boolean parseLine( BufferedReader br ) throws IOException {
        
        ElementNode newNode = null;
        String line = br.readLine();
        
        // if it's a blank line, check if the last node added still needs to be completed
        if( line.isEmpty() ) handleEmptyLine(line);
        
        String name = "";
        String type = "";
        // if an element type has been found, create new node and/or finish old node
        if( ParserUtilities.partFound(line) ){
            ElementNode parent = lastIncompleteNode();
            name = ParserUtilities.parseName(line);
            type = ParserUtilities.parseType(line);
            
            newNode = addNewNode(name, type, lineCounter, false);
            if( parent != null ){
                parent.add(newNode);
            }
                
        }
        return false;
    }
    
    
    private boolean handleEmptyLine(String line){
        String name = "";
        String type = "";
        // check if node is not /begin{} type, and so is ended with a blank line
        // ((!ParserUtilities.beginFound(line))
        ElementNode lastNode = lastIncompleteNode();
        if( lastNode != null ){
            lastNode.setComplete(lineCounter);
        }
        return true;
    }
    
    private ElementNode lastNodeAdded(){
        return this.addedNodes.get( addedNodes.size()-1 );
    }
    
    // gets the most recently added incomplete node
    private ElementNode lastIncompleteNode(){
        for( int i = addedNodes.size()-1; i>=0;  i-- ){
            if( !addedNodes.get(i).isComplete() ) return addedNodes.get(i);
        }
        return null;
    }
    
    private ElementNode addedNodeByName(String name){
        for( ElementNode node : this.addedNodes ){
            if( node.getName().equals(name) ) return node;
        }
        return null;
    }
}


