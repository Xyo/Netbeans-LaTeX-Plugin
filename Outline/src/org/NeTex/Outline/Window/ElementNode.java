package org.NeTex.Outline.Window;

import java.awt.Image;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.NeTex.Outline.Parser.ElementBean;
import org.NeTex.Outline.Parser.ElementType;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.*;

/**
 *
 * @author Jeremy
 */
public class ElementNode extends AbstractNode {
    private ElementType type;
    private ElementBean element;
    
    public ElementNode(ElementBean element){
       super(Children.create(new ElementNodeChildFactory(element), true ));
       setDisplayName(element.getName());
       this.type = element.getType();
    }
    

    public ElementNode(){
        super(Children.create(new ElementNodeChildFactory(), true));
        setDisplayName("Root");
        this.type = ElementType.ROOT;
    }
    
    public int getLineNumber(){
        return element.getStart();
    }
    
    @Override
    public Image getIcon(int type){
        return ImageUtilities.loadImage("C:\\Users\\Jeremy\\Documents\\NetBeansProjects\\NeTex\\nodeImage.png");
    }
 
//    @Override
//    public Action[] getActions(boolean popup){
//        return new Action[]{ new GoToLine() };
//    }
//    
//    private class GoToLine extends AbstractAction{
//        
//        public GoToLine(){
//            putValue(NAME, "Go to beginning line");
//        }
//        
//        @Override
//        public void actionPerformed(ActionEvent e){
//            ElementBean element = getLookup().lookup(ElementBean.class);
//            // TODO: get editor's go-to line action
//        }
//        
//        
//    }
}
