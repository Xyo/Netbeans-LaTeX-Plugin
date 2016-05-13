/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Compilation;

import java.util.Collection;
import org.latex.filetype.TexDataObject;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.filesystems.FileAttributeEvent;
import org.openide.filesystems.FileChangeListener;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileRenameEvent;
import org.openide.loaders.DataObject;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.nodes.Node;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.NeTex.Compilation//CompilationWindow//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "CompilationWindowTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "bottomSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "org.NeTex.Compilation.CompilationWindowTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_CompilationWindowAction",
        preferredID = "CompilationWindowTopComponent"
)
@Messages({
    "CTL_CompilationWindowAction=CompilationWindow",
    "CTL_CompilationWindowTopComponent=CompilationWindow Window",
    "HINT_CompilationWindowTopComponent=This is a CompilationWindow window"
})
public final class CompilationWindowTopComponent extends TopComponent implements LookupListener {
   
    
    private Lookup.Result<TexDataObject> result;
    private FileObject file;
    
    public CompilationWindowTopComponent() {
        initComponents();
        setName(Bundle.CTL_CompilationWindowTopComponent());
        setToolTipText(Bundle.HINT_CompilationWindowTopComponent());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();

        jTextField1.setText(org.openide.util.NbBundle.getMessage(CompilationWindowTopComponent.class, "CompilationWindowTopComponent.jTextField1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 186, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
   
    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    
    public void addErrorsToWindow(String errors){
        this.jTextField1.setText(errors);
    }
    
     @Override
    public void componentOpened() {
        result = Utilities.actionsGlobalContext().lookupResult(TexDataObject.class);
        result.addLookupListener(this);
    }
    
    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<TexDataObject> source = (Lookup.Result) le.getSource();

        Collection instances = source.allInstances();
        if (instances.isEmpty()) return;
        Object obj = instances.iterator().next();

        if (obj instanceof DataObject){
            try{
                TexDataObject tobj = Utilities.actionsGlobalContext().lookup(TexDataObject.class);
                this.file = tobj.getPrimaryFile();
                FileObject folder = this.file.getParent();
                addFileListener(folder);
            }catch(Exception e){
                //folder not found
            }
          
        }
        
    }
    
    public void addFileListener(FileObject folder){
        folder.addFileChangeListener(new FileChangeListener(){
              @Override
              public void fileAttributeChanged(FileAttributeEvent e){
                  
              }
              @Override
              public void fileChanged(FileEvent fe){
                  
              }
              @Override
              public void fileDataCreated(FileEvent fe){
                    // if a new file is created, get name of new file and begin parse
                    try{
                        FileObject newFile = fe.getFile();
                        String filename = newFile.getPath();
                        int index = filename.indexOf(".");
                        if( filename.substring(index).equals(".log")){
                            ReadLogFile logReader = new ReadLogFile(filename);
                            String errors = logReader.readFile();
                            addErrorsToWindow(errors);
                        }
                    }catch(Exception e){
                        // failed.
                        return;
                    }
              }

              @Override
              public void fileFolderCreated(FileEvent fe) {
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }

              @Override
              public void fileDeleted(FileEvent fe) {
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }

              @Override
              public void fileRenamed(FileRenameEvent fre) {
                  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
          });
    }
}
