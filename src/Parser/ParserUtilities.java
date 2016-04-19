package Parser;

/**
 * Created by Jeremy on 4/18/16.
 */
public class ParserUtilities {

    public static boolean partFound( String line ){
        if( line.contains("\\part")
                || line.contains("\\chapter")
                || line.contains("\\section")
                || line.contains("\\subsection")
                || line.contains("\\subsubsection")
                || line.contains("\\paragraph")
                || line.contains("\\subparagraph")
                || line.contains("\\backmatter")
                || line.contains("\\frontmatter")
                || line.contains("\\appendix")
                || line.contains("\\backmatter")
                || line.contains("\\appendix")
                || line.contains("\\closing")
                || line.contains("\\glossary{")
                || line.contains("\\end{")
                || line.contains("\\bibliography{")
                || beginFound(line)     ){

            return true;
        }
        return false;
    }

    public static boolean beginFound( String line ){
        if( line.contains("\\begin{description}")
                || line.contains("\\begin{document}")
                || line.contains("\\begin{figure")
                || line.contains("begin{list}")
                || line.contains("begin{table")
                || line.contains("begin{titlepage") ){
            return true;
        }
        return false;
    }

    public static boolean isEndingElement( String element ){
        if( element.contains("\\end")) return true;
        return false;
    }

    public static boolean isStartingElement( String element ){
        if( element.contains("\\begin") ) return true;
        return false;
    }

    public static String parseName(String line){
        int start = line.indexOf("{") + 1;
        int end = line.indexOf("}") + 1;

        return line.substring( start, end );
    }


}
