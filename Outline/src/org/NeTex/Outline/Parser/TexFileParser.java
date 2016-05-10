package org.NeTex.Outline.Parser;

/**
 *
 * @author Jeremy
 */

import java.io.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;
import org.NeTex.Outline.Window.ElementNode;
import org.NeTex.*;
import org.latex.filetype.TexDataObject;
import org.openide.filesystems.FileObject;
        
public class TexFileParser {
    private TexFile doc;
    private BufferedReader br;
    private int lineCounter = 0;
    private final ElementBean rootBean;
    private ArrayList<ElementBean> beginEndElements = new ArrayList<>();
    private String filename = "";
    
    Stack<ElementBean> stack = new Stack<>();
    
    
    public TexFileParser(FileObject file) throws IOException {
        String filename = file.getPath();
        if( filename != null ){
            this.filename = filename;
        }else{
            throw new IOException("File does not exist.");
        }
        // push root bean to stack
        rootBean = new ElementBean();
        stack.push(rootBean);
    }

    // parses the tex file to build the tree structure
    public ElementNode beginParsing() {
       
        try( BufferedReader br = 
            new BufferedReader(new FileReader(this.filename))){

            String line = br.readLine();
            // keep reading until end of file
            while(!line.equals("\\end{document}") ){
                ++lineCounter;
                if( ParserUtilities.partFound(line) ){
                    parseLine(line);
                }
                line = br.readLine();
            }           
        }catch( IOException e ){
            e.printStackTrace();
            return new ElementNode(this.rootBean);
            
        }catch(NullPointerException n){
            n.printStackTrace();
            return new ElementNode(this.rootBean);
        }
        return new ElementNode(this.rootBean);
    }

    // Gets the next non-empty line in the document
    private boolean parseLine(String line) throws IOException, NullPointerException {
        
        ElementBean element = null;
        // if an element type has been found, create new node and/or finish old node
        String[] elements = ParserUtilities.parseElementsFromLine(line);
        if( elements == null ) return false;
        
        for( String elem : elements ){
            element = createElement(elem);
            addElementToStack(element);
        }
        return true;
    }
    
    private boolean beginFoundElement(String line){
        return false;
    }
    
    
    private ElementBean createElement(String line) throws IllegalArgumentException, NullPointerException {
        String name = ParserUtilities.parseName(line);
        String value = ParserUtilities.parseType(line);
        ElementType type;
        try{
            type = ParserUtilities.getEnumValue(value);
        }catch( IllegalArgumentException e ){
            e.printStackTrace();
            type = ElementType.DESCRIPTION;
        }
        
        ElementBean elem = new ElementBean(type, name, lineCounter, true);
        
        if( elem == null ){
            throw new IllegalArgumentException("Invalid paramaters here... " + type + name + lineCounter + "false" );
        }
        return elem;
    }
    
    // pop elements from the stack until an element with higher level is found
    private void addElementToStack(ElementBean element){
        try{
            ElementBean currNode = stack.peek();
            while( advanceStack(element.getLevel()) ){
                advanceStack(element.getLevel());
            }
            
            // remaining node at top of stack should be the parent element
            currNode = stack.peek();
            currNode.addChild(element);
            stack.push(element);
        }catch( EmptyStackException e ){
            e.printStackTrace();
        }
    }
    
    // remove previous nodes that are higher level than the new node being added
    private boolean advanceStack( int elementLevel ) throws EmptyStackException {
        ElementBean currNode = stack.peek();
        int currLevel = ElementType.getLevel(currNode.getType());
        
        if( currLevel >= elementLevel ){
            stack.pop().setComplete(this.lineCounter);
            return true;
        }
        return false;
    }
    
    
     //get document class type to name tex tree
    private String getDocClass(BufferedReader br) throws IOException {
        String line = "";
        do{
            ++lineCounter;
            line = br.readLine();
        }while(!line.contains("\\documentclass{"));
        // parse name between { }
        return ParserUtilities.parseName(line);
    }

    // get end of preamble (and beginning of doc)
    private void toBeginOfDoc( BufferedReader br ) throws IOException {
        
    }
}


