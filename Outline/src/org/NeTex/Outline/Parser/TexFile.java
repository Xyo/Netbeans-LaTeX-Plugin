/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Outline.Parser;


/**
 *
 * @author Jeremy
 * 
 * Represents some basic info about the document
 */
public class TexFile {
    private int numOfLines;
    private final String filename;
    
    public TexFile(String filename ){
        this.filename = filename;
    }

    public boolean setNumLines( int num ){
        if( num < 1 ){
            return false;
        }else{
            this.numOfLines = num;
            return true;
        }
    }
    
    public String getFilePath(){
        return filename;
    }
    
    public int numOfLines(){
        return numOfLines;
    }
}

