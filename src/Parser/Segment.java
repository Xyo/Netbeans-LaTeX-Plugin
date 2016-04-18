package Parser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Jeremy
 * 
 * A segment of the document that will be an individual unit in the tree
 */


public abstract class Segment implements Comparable{
    private String name;
    private Integer begin = 0;
    private Integer end = 0;
    private boolean correct;
    
    Segment(Integer lineStart){
        this.begin = lineStart;
    }
    
    public int getLineStart() {
        return begin;
    }

    public int getLineEnd() {
        return end;
    }
    
    public boolean isCorrect() {
        return correct;
    }
    
    public void setCorrect(){
        if( end > 0 ){
            correct = true;
        }
    }
    
    public void setIncorrect(){
        correct = false;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public int compareTo(Object seg) {
        return this.begin.compareTo( ((Segment)seg).getLineStart() );
    }
    
    @Override
    public boolean equals(Object seg) {
        if( seg == null || !(seg instanceof Segment) ) return false;
        
        return this.begin == ((Segment)seg).getLineStart();
    }
}
