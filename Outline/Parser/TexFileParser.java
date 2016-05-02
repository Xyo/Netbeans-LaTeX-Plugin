package org.NeTex.Outline.Parser;

/**
 *
 * @author Jeremy
 */

import org.NeTex.Outline.UI.ElementNodeChildFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class TexFileParser {
    private TexFile doc;
    private BufferedReader br;
    private int lineCounter = 0;
    private ArrayList<ElementBean> addedBeans = new ArrayList<>();
    
    Stack<ElementBean> stack = new Stack<>();
    
    public static void main(String[] args){
        
    }
    
    
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
        }catch( IOException e ){
            e.printStackTrace();
        }  
    }
    

    // Gets the next non-empty line in the document
    public boolean parseLine(String line) throws IOException {
        
        ElementBean element = null;
        // if an element type has been found, create new node and/or finish old node
        String[] elements = ParserUtilities.parseElementsFromLine(line);
        for( String elem : elements ){
            element = createElement(line);
            addElementToStack(element);
        }
       
        return false;
    }
    
    
    public ElementBean createElement(String line) throws IllegalArgumentException {
        String name = ParserUtilities.parseName(line);
        String value = ParserUtilities.parseType(line);
        ElementType type = ParserUtilities.getEnumValue(value);
        
        ElementBean elem = new ElementBean(type, name, lineCounter, true);
        
        if( elem == null ){
            throw new IllegalArgumentException("Invalid paramaters here... " + type + name + lineCounter + "false" );
        }
        return elem;
    }
    
    // pop elements from the stack until an element with higher level is found
    public void addElementToStack(ElementBean element){
        if(!stack.isEmpty()){
            ElementBean currNode = stack.peek();
            // remove previous nodes that are higher level than the new node being added
            while( currNode.getLevel() <= element.getLevel() ){
                currNode = stack.peek();
                currNode.setComplete(this.lineCounter);
                stack.pop();
            }
            // remaining node at top of stack should be the parent element
            currNode.addChild(element);
            stack.push(element);
        }
    }
    
    
     //get document class type to name tex tree
    public String getDocClass(BufferedReader br) throws IOException {
        String line = "";
        do{
            ++lineCounter;
            line = br.readLine();
        }while(!line.contains("\\documentclass{"));
        // parse name between { }
        return ParserUtilities.parseName(line);
    }

    // get end of preamble (and beginning of doc)
    public void toBeginOfDoc( BufferedReader br ) throws IOException {
        
    }
}


