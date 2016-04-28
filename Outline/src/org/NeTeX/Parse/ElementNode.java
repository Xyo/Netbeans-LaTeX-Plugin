/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTeX.Outline.Parse;
import javax.swing.tree.DefaultMutableTreeNode;
/**
 *
 * @author Jeremy
 */
public class ElementNode extends DefaultMutableTreeNode {
    private ElementBean element;
    
    ElementNode(ElementBean bean){
        this.element = bean;
    }
    
    public String getName(){
        return this.element.getName();
    }
    
    public String getDescription(){
        return this.element.toString();
    }
    
    public boolean isComplete(){
        return element.isNeedsEndTag();
    }
    
    public void setComplete(int lineNumber){
        element.setComplete(lineNumber);
    }
    
    public int getStartLine(){
        return element.getStart();
    }
    
    public int getEndLine(){
        return element.getEnd();
    }
    
    
    public void setEndLine(int end){
        element.setEnd(end);
    }
}


