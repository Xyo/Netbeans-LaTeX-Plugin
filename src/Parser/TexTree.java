/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Jeremy
 */
public class TexTree {
    private DefaultTreeModel model;
    private JTree tree;
    
    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode lastNode = null;
    
    TexTree( String name ){
        root = new DefaultMutableTreeNode(name);
        model = new DefaultTreeModel(root);
        tree = new JTree(model);
    }
    
    /*  @line = data to add to node
        @endSection:
            true = next node will start a new section
            false = next node will be added to lastNode
    */
    public void addNode( String line, boolean beginSection ){
        if( lastNode != null ){
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(line);
            lastNode.add(newNode);
            lastNode = newNode; 
            return;
        }else{
            lastNode = new DefaultMutableTreeNode(line);
        }
    }
    
}
