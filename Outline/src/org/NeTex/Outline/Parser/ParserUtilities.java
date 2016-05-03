package org.NeTex.Outline.Parser;

/**
 * Created by Jeremy on 4/18/16.
 */

public class ParserUtilities {

    public static boolean partFound( String line ){
        if( line.contains("\\part")
                || line.contains("\\chapter")
                || line.contains("\\section") ){
                return true; 
        }
        return false;
    }
//                || line.contains("\\subsection")
//                || line.contains("\\subsubsection")
//                || line.contains("\\paragraph")
//                || line.contains("\\subparagraph")
//                || line.contains("\\backmatter")
//                || line.contains("\\frontmatter")
//                || line.contains("\\appendix")
//                || line.contains("\\backmatter")
//                || line.contains("\\appendix")
//                || line.contains("\\closing")
//                || line.contains("\\glossary{")
//                || line.contains("\\end{")
//                || line.contains("\\bibliography{")
//                || beginFound(line)     
                

            

    // ******** For tex file parser ********
    
    //            if( ParserUtilities.isEndingElement(line) ){
//                ElementNode correspondingNode = addedNodeByName(name);
//                correspondingNode.setComplete(lineCounter);
//            }
//            
//            if( ParserUtilities.beginFound(line) ){
//                newNode = factory.createNode(name, type, lineCounter, emptyLineFound);
//            }else if( ParserUtilities.isEndingElement(line) ){
//
//            }

//    public static boolean beginFound( String line ){
//        if( line.contains("\\begin{description}")
//                || line.contains("\\begin{document}")
//                || line.contains("\\begin{figure")
//                || line.contains("begin{list}")
//                || line.contains("begin{table")
//                || line.contains("begin{titlepage") ){
//            return true;
//        }
//        return false;
//    }
    
//    public static boolean isEndingElement( String element ){
//        if( element.contains("\\end")) return true;
//        return false;
//    }
//
//    public static boolean isBeginningElement( String element ){
//        if( element.contains("\\begin") ) return true;
//        return false;
//    }

    public static String parseName(String line){
        
        int start = line.indexOf("{") + 1;
        int end = line.indexOf("}");
        if( start < 0 || end < 0 ) return "";
        
        if( (start+1) == end ) return parseType(line);
        return line.substring( start, end );
    }
    
    public static String parseType(String line) throws IllegalArgumentException {
//        if( beginFound(line) ) return parseName(line);
        if( !line.contains("\\") ) return "";
        
        int start = line.indexOf("\\") + 1;
        
        int end = -1;
        if( line.contains("{") ){
            end = line.indexOf("{");
        }else{
            end = line.length();
        }
        return line.substring( start, end );
    }
    
    public static String[] parseElementsFromLine(String line){
        int index = line.indexOf("\\");
        int nextIndex = line.lastIndexOf("\\");
        
        if( index < 0 ) return null;
        
        if( index != nextIndex ){
            String[] parts = line.split("\\\\");
            return parts;
        }else{
            String[] part = {line.substring(index)};
            return part;
        }
    }

    public static ElementType getEnumValue(String value){
        String type = value.toUpperCase();
        switch(type){
            case("PART"):
                return ElementType.PART;
            case("CHAPTER"):
                return ElementType.CHAPTER;
            case("SECTION"):
                return ElementType.SECTION;
//            case("SUBSECTION"):
//                return ElementBean.ElementType.SUBSECTION;
//            case("SUBSUBSECTION"):
//                return ElementBean.ElementType.SUBSUBSECTION;
//            case("PARAGRAPH"):
//                return ElementBean.ElementType.PARAGRAPH;
//            case("SUBPARAGRAPH"):
//                return ElementBean.ElementType.SUBPARAGRAPH;
//            case("FIGURE"):
//                return ElementBean.ElementType.FIGURE;
//            case("LIST"):
//                return ElementBean.ElementType.LIST;
//            case("TABLE"):
//                return ElementBean.ElementType.TABLE;
                
                // TODO: fix illegal arguments
            default:
                return ElementType.TABLE;
        }
        //throw new IllegalArgumentException(value + " is not a supported type");
    }
}
