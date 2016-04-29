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
public enum ElementType{
        ROOT, PART, CHAPTER, SECTION, SUBSECTION, SUBSUBSECTION, PARAGRAPH, 
        SUBPARAGRAPH, DESCRIPTION, FIGURE, LIST, TABLE;
        
        public static int getLevel(ElementType type){
            switch(type){
                case ROOT:
                    return -9000;
                case PART:
                    return -1;
                case CHAPTER:
                    return 0;
                case SECTION:
                    return 1;
                case PARAGRAPH:
                    return 2;
                default:
                    return 6;
            }
        }
    }
    
