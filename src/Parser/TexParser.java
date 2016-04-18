package Parser;

/**
 *
 * @author Jeremy
 */
import java.util.ArrayList; 

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.io.*;
import javax.swing.tree.DefaultMutableTreeNode;


public class TexParser {
    private TexFile doc;
    private TexTree tree;
    
    TexParser( TexFile doc ) throws FileNotFoundException {
        if( doc == null ) throw new FileNotFoundException( "TexFile not created yet" );
        this.doc = doc;
    }
    
 
    // parses the tex file to build the tree structure
    public void beginParse() {
        tree = new TexTree(doc.getFilePath());
        
        try( BufferedReader br = 
                new BufferedReader( new FileReader(doc.getFilePath()) ) ){
            String line = br.readLine();
            // TODO: parsing on value to just get identifier
            // TODO: adding ending value
            if( partFound(line) ){
                tree.addNode(line);
                // if the line begins a section and ends it immediately, then the next node
                // will be the beginning of a new section
                if( containsEndValue(line) ){
                    
                }
            }
        }catch( IOException e ){}  
    }
    
    
    public static boolean partFound( String value ){
        String found = "";
        if( value.equals("\\part") 
                 || value.equals("\\chapter") 
                 || value.equals("\\section") 
                 || value.equals("\\subsection") 
                 || value.equals("\\subsubsection") 
                 || value.equals("\\paragraph") 
                 || value.equals("\\subparagraph")  
                 || value.equals("\\backmatter")
                 || value.equals("\\frontmatter") 
                 || value.equals("\\appendix") 
                 || value.equals("\\backmatter")
                 || value.equals("\\begin{)")    
                 || value.equals("\\end{")       ){
            
            return true;
        }
        return false;    
    }
    
    public static boolean containsEndValue( String line ){
        // TODO: more cases of section ending
        if( line.contains("}") ){
            return true;
        }else{
            return false;
        }
    }
    
}

