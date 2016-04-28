/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTeX.Outline.Parse;

import java.util.Objects;

/**
 *
 * @author Jeremy
 */
public class ElementBean implements Comparable {
    public enum ElementType{
        PART, CHAPTER, SECTION, SUBSECTION, SUBSUBSECTION, PARAGRAPH, 
        SUBPARAGRAPH, DESCRIPTION, FIGURE, LIST, TABLE
    }
    
    private ElementType type;
    private final String name;
    private final Integer start;
    private Integer end;
    private boolean needsEndTag = false;
    private boolean requiresEnding = false;
    
    
    ElementBean(ElementType type, String name, int start, boolean needsEndTag ) {
        this.type = type;
        this.name = name;
        this.start = (Integer)start;
        this.needsEndTag = needsEndTag;
    }
    
    
    @Override
    public int compareTo(Object other) {
        if( !(other instanceof ElementBean) ) return 0;
        return this.start.compareTo( ((ElementBean)other).getStart() );
    }
    
    @Override
    public boolean equals(Object seg) {
        if( seg == null || !(seg instanceof ElementNode) ) return false;
        
        return this.start == ((ElementBean)seg).getStart();
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


    public Integer getEnd() {
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
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.start);
        hash = 41 * hash + Objects.hashCode(this.end);
        hash = 41 * hash + (this.needsEndTag ? 1 : 0);
        hash = 41 * hash + (this.requiresEnding ? 1 : 0);
        return hash;
    }
}


