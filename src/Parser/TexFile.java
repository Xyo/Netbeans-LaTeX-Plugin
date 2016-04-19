package Parser;

/**
 *
 * @author Jeremy
 * 
 * Represents some basic info about the document
 */
public class TexFile {
    private int numLines;
    private String filename;
    
    TexFile( String filename ){
        this.filename = filename;
    }

    public boolean setNumLines( int num ){
        if( num < 1 ){
            return false;
        }else{
            this.numLines = num;
            return true;
        }
    }
    
    public String getFilePath(){
        return filename;
    }
    
    public int numOfLines(){
        return numLines;
    }
}
