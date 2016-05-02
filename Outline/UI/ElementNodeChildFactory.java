/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Outline.UI;

import java.util.Arrays;
import java.util.List;
import org.NeTex.Outline.Parser.ElementBean;
import org.NeTex.Outline.Parser.ElementType;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;
import org.NeTex.Outline.Parser.ElementBean;

/**
 *
 * @author Jeremy
 */
public class ElementNodeChildFactory extends ChildFactory<ElementBean>{
    ElementBean element;
    
    ElementNodeChildFactory(ElementBean element){
        this.element = element;
    }
    
    @Override
    protected boolean createKeys(List<ElementBean> list) {
        List<ElementBean> children = element.getChildren();
        if( children == null ){
            return false;
        }else{
            list.addAll(children);
            return true;
        }
    }
    
    @Override
    protected Node createNodeForKey(ElementBean key){
        return new ElementNode(key);
    }
    
    
}
