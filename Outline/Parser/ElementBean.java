package org.NeTex.Outline.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Jeremy
 */
public class ElementBean {
    
    private ElementType type;
    private final String name;  
    private final Integer start;
    private Integer end;
    private boolean needsEndTag = false;
    private boolean requiresEnding = false;
    private List<ElementBean> children = new ArrayList<>();
    
    public ElementBean(ElementType type, String name, int start, boolean needsEndTag ) {
        this.type = type;
        this.name = name;
        this.start = (Integer)start;
        this.needsEndTag = needsEndTag;
    }
    
    public void addChild(ElementBean child){
        children.add(child);
    }

    public List<ElementBean> getChildren(){
        return children;
    }

    @Override
    public String toString(){
        return name + ": " + start + " - " + end + "\t(" + needsEndTag + ")";
    }


    // Getters and Setters
    public boolean isNeedsEndTag() {
        return needsEndTag;
    }

    public void setComplete(int endingLineNumber) {
        needsEndTag = true;
        end = (Integer)endingLineNumber;
    }
    
    
    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public int getStart() {
        return start;
    }
    
    public int getEnd(){
        return end;
    }


    public void setEnd(int end) {
        this.end = end;
    }
    
    public boolean requiresEnd(){
        return this.requiresEnding;
    }
    
    public void setEndingRequired(boolean needs){
        this.requiresEnding = needs;
    }
    
    public String getName(){
        return name;
    }
    
    public int getLevel(){
        return ElementType.getLevel(getType());
    }
    
}

