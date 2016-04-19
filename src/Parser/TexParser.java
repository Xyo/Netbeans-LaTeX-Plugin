package Parser;

/**
 *
 * @author Jeremy
 */

import java.io.*;



public class TexParser {
    private TexFile doc;
    private TexTree tree;
    private int lineCounter = 0;

    TexParser( TexFile doc ) throws FileNotFoundException {
        if( doc == null ) throw new FileNotFoundException( "TexFile not created yet" );
        this.doc = doc;
    }
    
 
    // parses the tex file to build the tree structure
    public void beginParse() {


        try( BufferedReader br = 
                new BufferedReader(new FileReader(doc.getFilePath()))   ){

            String documentClass = getDocClass(br);
            tree = new TexTree(documentClass);

            // todo: get ending line now, or set ending line when next section is added to tree?
            // check if the line contains an important element
            if( ParserUtilities.partFound(line) ){
                String name = ParserUtilities.parseName(line);
                Segment seg = new Segment( name, lineCounter);

                if( ParserUtilities.isStartingElement(line) ){
                    tree.addNewSection(seg);
                }else if( ParserUtilities.isEndingElement(line) ){
                    tree.addToChild(seg);
                }
            }
        }catch( IOException e ){}  
    }
    
    public int getEndingLine( BufferedReader br ){

    }


    //get document class type to name tex tree
    public String getDocClass(BufferedReader br) throws IOException {
        String line = "";
        do{
            ++lineCounter;
            br.readLine();
        }while(!line.contains("\\documentclass{"));
        // parse name between { }
        return ParserUtilities.parseName(line);
    }

    // get end of preamble (and beginning of doc)
    public void toBeginOfDoc( BufferedReader br ) throws IOException {
        String line = "";
        do{
            ++lineCounter;
            br.readLine();
        }while(!line.contains("\\documentclass{"));
    }
    
}

