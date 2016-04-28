/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTeX.Outline.Parse;


/**
 *
 * @author Jeremy
 */
public class ElementNodeFactory {
    
    public ElementNode createNode(String value, String name, int start, boolean needsEndTag){
        ElementBean.ElementType type = ParserUtilities.getEnumValue(value);
        ElementBean element = new ElementBean(type, name, start, needsEndTag);
        return new ElementNode(element);
    }
}
