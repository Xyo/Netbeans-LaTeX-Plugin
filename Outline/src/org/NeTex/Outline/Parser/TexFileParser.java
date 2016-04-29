/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Outline.Parser;

/**
 *
 * @author Jeremy
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class TexFileParser {
    private TexFile doc;
    private BufferedReader br;
    private int lineCounter = 0;
    private ElementChildFactory elementFactory = new ElementChildFactory();
    
    Stack<ElementNode> stack = new Stack<>();

           
    TexFileParser( TexFile doc ) throws FileNotFoundException {
        if( doc == null ) throw new FileNotFoundException( "The file is missing" );
        this.doc = doc;
    }
    

 
    // parses the tex file to build the tree structure
    public void beginParse() {
        
        try( BufferedReader br = 
                new BufferedReader(new FileReader(doc.getFilePath()))   ){

            String line = "";
            while( (line = br.readLine()).isEmpty() || !ParserUtilities.partFound(line) ){
                lineCounter++;
                line = br.readLine();
            }
            parseLine(line);
        }catch( IOException e ){}  
    }
    
    // Gets the next non-empty line in the document
    public boolean parseLine(String line) throws IOException {
        
        ElementNode newNode = null;
        // if an element type has been found, create new node and/or finish old node
        String[] elements = ParserUtilities.parseElementsFromLine(line);
        for( String elem : elements ){
            newNode = createNewNode(line);
            addNodeToStack(newNode);
        }
       
        return false;
    }
    
    public ElementBean createNewBean(String line) throws IllegalArgumentException {
        String name = ParserUtilities.parseName(line);
        String type = ParserUtilities.parseType(line);
        ElementNode newNode = nodeFactory.create(type, name, lineCounter, false);
        if( newNode == null ) throw new IllegalArgumentException("Invalid paramaters here... " + type + name + lineCounter + "false" );
        return newNode;
    }
    
    public void addNodeToStack(ElementNode newNode){
         if(!stack.isEmpty()){
            ElementNode currNode = stack.peek();
            // remove previous nodes that are higher level than the new node being added
            while( currNode.getLevel() >= newNode.getLevel() ){
                stack.peek();
                currNode.setComplete(this.lineCounter);
                stack.pop();
            }
            // next node in the stack should be the parent element of the new node
            
        }
    }
    
    
    private ElementNode lastNodeAdded(){
        return this.addedNodes.get( addedNodes.size()-1 );
    }
    
    // gets the most recently added incomplete node
    private ElementNode lastIncompleteNode(){
        for( int i = addedNodes.size()-1; i>=0;  i-- ){
            if( !((ElementNode)addedNodes.get(i)).isComplete() ) 
                return addedNodes.get(i);
        }
        return null;
    }
    
    private ElementNode addedNodeByName(String name){
        for( ElementNode node : this.addedNodes ){
            if( node.getName().equals(name) ) return node;
        }
        return null;
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
}


