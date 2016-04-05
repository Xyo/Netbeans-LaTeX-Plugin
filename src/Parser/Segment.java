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
    private Integer begin = 0;
    private Integer end = 0;
    private boolean correct;
    private Set<Segment> children;
    
    Segment(Integer lineStart){
        this.begin = lineStart;
        children = new TreeSet<>();
    }
    
    // @param Returns an arraylist of the segment's child segments
    public List<Segment> getChildren(){
        List<Segment> children = new ArrayList<>();
        if( this.children.isEmpty() ){
            return null;
        }else{
            for( Segment seg : this.children ){
                children.add(seg);
            }
        }
        return children;
    }
    
    public int getLineStart() {
        return begin;
    }

    public int getLineEnd() {
        return end;
    }
    
    public boolean isComplete() {
        return correct;
    }
    
    public void setComplete(){
        if( end > 0 ){
            correct = true;
        }
    }
    
    public void setIncomplete(){
        correct = false;
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
