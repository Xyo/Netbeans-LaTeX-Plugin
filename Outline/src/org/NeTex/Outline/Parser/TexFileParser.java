package org.NeTex.Outline.Parser;

/**
 *
 * @author Jeremy
 */

import org.NeTex.Outline.Window.ElementNodeChildFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Stack;
import org.NeTex.Outline.Window.ElementNode;

public class TexFileParser {
    private TexFile doc;
    private BufferedReader br;
    private int lineCounter = 0;
    private ElementBean rootBean;
    private ArrayList<ElementBean> addedBeans = new ArrayList<>();
    
    Stack<ElementBean> stack = new Stack<>();
    
    
    public TexFileParser( TexFile doc ) throws FileNotFoundException {
        if( doc == null ) throw new FileNotFoundException( "The file is missing" );
        this.doc = doc;
        // push root bean to stack
        rootBean = new ElementBean();
        stack.push(rootBean);
    }
    
    // parses the tex file to build the tree structure
    public ElementNode beginParse() {
        
        try( BufferedReader br = 
                new BufferedReader(new FileReader("C:\\Users\\Jeremy\\Documents\\NetBeansProjects\\NeTex\\sample.tex")   )){

            String line = br.readLine();
            // keep reading until end of file
            while( line != null ){
                ++lineCounter;
                if( ParserUtilities.partFound(line) ){
                    parseLine(line);
                }
                line = br.readLine();
            }
           ElementNode newRoot = new ElementNode(this.rootBean);
           return newRoot;
        }catch( IOException e ){
            e.printStackTrace();
        }catch(StringIndexOutOfBoundsException s){
            s.printStackTrace();
        }
        return new ElementNode(new ElementBean(ElementType.ROOT, "New root", 0, false));
    }
    

    // Gets the next non-empty line in the document
    public boolean parseLine(String line) throws IOException {
        
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
    
    
    public ElementBean createElement(String line) throws IllegalArgumentException {
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
    public void addElementToStack(ElementBean element){
        if(!stack.isEmpty()){
            ElementBean currNode = stack.peek();
            currNode.addChild(element);
            stack.push(element);
            // remove previous nodes that are higher level than the new node being added
//            while( currNode.getLevel() >= element.getLevel() ){
//                currNode = stack.peek();
//                if( currNode.getType() != ElementType.ROOT ) currNode.setComplete(this.lineCounter);
//                stack.pop();
//            }
            // remaining node at top of stack should be the parent element
            
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


