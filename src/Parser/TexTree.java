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
    private DefaultMutableTreeNode lastNode;
    
    TexTree( String name ){
        root = new DefaultMutableTreeNode(name);
        model = new DefaultTreeModel(root);
        tree = new JTree(model);
        // last node added at beginning will be root
        lastNode = root;
    }


    public boolean addNewSection( Segment element ){

            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(element);
            if( lastNode.getParent() == root ){

            }else{

            }
            lastNode = lastNode.getParent();
            lastNode.getParent().add(newNode);
            lastNode = newNode;

            lastNode = new DefaultMutableTreeNode(element);


        System.out.println(lastNode.getPath());
    }

    public boolean addToChild( String element ){
        if( lastNode != null ){
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(element);
            lastNode.add(newNode);
            lastNode = newNode;
        }else{
            lastNode = new DefaultMutableTreeNode(element);
        }
        System.out.println(lastNode.getPath());
        return true;
    }

}
