/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Outline.UI;

import java.awt.Event;
import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Jeremy
 */
public class ElementEventChildFactory extends ChildFactory<Event> {

    @Override
    protected boolean createKeys(List<Event> list) {
        return false;
    }
    
    @Override
    protected Node createNodeForKey(Event key){
        Node newNode = new AbstractNode( 
                Children.create(new ElementEventChildFactory(), true), 
                Lookups.singleton(key)  );
        newNode.setDisplayName(key.toString());
        return newNode;
    }
}
