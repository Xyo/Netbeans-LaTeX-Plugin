/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Outline.Parser;

/**
 *
 * @author Jeremy
 */
public class ElementBeanFactory {
     public ElementBean createNode(String value, String name, int start, boolean needsEndTag){
        ElementType type = ParserUtilities.getEnumValue(value);
        ElementBean element = new ElementBean(type, name, start, needsEndTag);
        return element;
    }
}
