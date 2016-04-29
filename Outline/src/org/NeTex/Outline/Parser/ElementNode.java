/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Outline.Parser;
import javax.swing.tree.DefaultMutableTreeNode;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;
/**
 *
 * @author Jeremy
 */
public class ElementNode extends AbstractNode {
    private ElementBean element = null; //TODO: fix this later

    public ElementNode(Children children) {
        super(children);
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
    
    public ElementType getType(){
        return element.getType();
    }
    
    public int getLevel(){
        return ElementType.getLevel(getType());
    }
}


