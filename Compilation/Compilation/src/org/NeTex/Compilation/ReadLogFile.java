/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Compilation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Jeremy
 */
public class ReadLogFile {
    private String filename;
    
    
    ReadLogFile(String filename){
        this.filename = filename;
    }
    
    
    public String readFile(){
        StringBuilder sb = new StringBuilder();
        try( BufferedReader br = 
            new BufferedReader(new FileReader(this.filename))){
            
            String line = br.readLine();
            // keep reading until end of file
            while(true){
                line = br.readLine();
                sb.append(line);
            }           
        }catch( IOException e ){
            e.printStackTrace();
            return null;
            
        }catch(NullPointerException n){
            // end of file
        }
        return sb.toString();
    }
}
