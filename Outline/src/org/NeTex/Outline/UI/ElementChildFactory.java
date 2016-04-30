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
public class ElementChildFactory extends ChildFactory<ElementBean>{

    @Override
    protected boolean createKeys(List<ElementBean> list) {
        ElementBean[] beans = new ElementBean[2];
        for( int i=0; i<beans.length; i++){
            beans[0] = new ElementBean(ElementType.CHAPTER, "ChapterTitle", 5, false );
            list.addAll(Arrays.asList(beans));
        }
        return true;
    }
    
    @Override
    protected Node createNodeForKey(ElementBean key){
        Node newNode = new AbstractNode(Children.create(new ElementChildFactory(), true), Lookups.singleton(key));
        newNode.setDisplayName(key.getName());
        return newNode;
    }
    
}
