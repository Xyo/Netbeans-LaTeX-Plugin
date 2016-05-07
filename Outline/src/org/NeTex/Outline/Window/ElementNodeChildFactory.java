/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Outline.Window;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.NeTex.Outline.Parser.ElementBean;
import org.openide.nodes.Children;

/**
 *
 * @author Jeremy
 */
public class ElementNodeChildFactory extends ChildFactory<ElementBean>{
    ElementBean element;
    
    ElementNodeChildFactory(ElementBean element){
        this.element = element;
    }
    
    ElementNodeChildFactory(){}
    
    @Override
    protected boolean createKeys(List<ElementBean> list) {
        try{
            List<ElementBean> children = element.getChildren();
            if( children == null || children.isEmpty() ){
                return false;
            }else{
                list.addAll(children);
                return true;
            }
        }catch( NullPointerException e ){
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    protected Node createNodeForKey(ElementBean key){
        ElementNode newNode = new ElementNode(key);
        newNode.setDisplayName(key.getName());
        return newNode;
    }
    
    
}
