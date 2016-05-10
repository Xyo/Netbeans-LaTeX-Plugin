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
        ROOT, TITLE, PART, CHAPTER, SECTION, SUBSECTION, SUBSUBSECTION, PARAGRAPH, 
        SUBPARAGRAPH, DESCRIPTION, FIGURE, LIST, TABLE;
        
        public static int getLevel(ElementType type){
            switch(type){
                case ROOT:
                    return -9001;
                case PART:
                    return 0;
                case CHAPTER:
                    return 1;
                case SECTION:
                    return 2;
                case SUBSECTION:
                    return 3;
                case SUBSUBSECTION:
                    return 4;
                case PARAGRAPH:
                    return 5;
                case SUBPARAGRAPH:
                    return 6;
                default:
                    return 7;
            }
        }
    }
    
