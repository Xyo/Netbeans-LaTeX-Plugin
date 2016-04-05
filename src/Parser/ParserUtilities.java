package Parser;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jeremy
 */
public class ParserUtilities {
    
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
    
    public String foundPattern(String ex){
        //String patterns = "^[/]\\w+[{][\\w|\\s]+[}]";
        String patterns = "\\{}";
        Pattern pat = Pattern.compile(patterns);
        Matcher match = pat.matcher(ex);

        String value ="";
        String result = "";
        
        if( match.find( ) ) {         
            System.out.println("Match found");
            StringTokenizer tokenizer = new StringTokenizer(match.group(0), "{}");
            value = tokenizer.nextToken();
            
            if( partFound(value) ){
                result = tokenizer.nextToken();
            } else {
                result = "";
            }
        }
        System.out.println("Result from tokenizer: " + result);
        return result;
    }
}
