/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTex.Outline.Window;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import javax.swing.JComponent;
import org.NeTex.Outline.Parser.TexFileParser;
import org.latex.filetype.TexDataObject;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.*;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.view.BeanTreeView;
import org.openide.filesystems.FileAttributeEvent;
import org.openide.filesystems.FileChangeAdapter;
import org.openide.filesystems.FileEvent;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

/**
 * 
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.NeTex.Outline.UI//NavigationWindow//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "NavigationWindowTopComponent",
        iconBase = "org/NeTex/Outline/UI/outlineIcon.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "navigator", openAtStartup = true)
@ActionID(category = "Window", id = "org.NeTex.Outline.UI.NavigationWindowTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_NavigationWindowAction",
        preferredID = "NavigationWindowTopComponent"
)
@Messages({
    "CTL_NavigationWindowAction=NavigationWindow",
    "CTL_NavigationWindowTopComponent=NavigationWindow Window",
    "HINT_NavigationWindowTopComponent=This is a NavigationWindow window"
})
public final class NavigationWindowTopComponent extends TopComponent 
        implements NavigatorPanel, ExplorerManager.Provider {
    
    private TexDataObject project;
    private final ExplorerManager manager = new ExplorerManager();
    private ElementNode root;
    private FileObject file;
    
    public NavigationWindowTopComponent() {
        initComponents();
        setName(Bundle.CTL_NavigationWindowTopComponent());
        setToolTipText(Bundle.HINT_NavigationWindowTopComponent());
        
        this.root = new ElementNode();
        manager.setRootContext(root);
        getProjectFile();
        setDisplayName("Example");
    }
    
    public void getProjectFile(){
        Lookup lookup = Utilities.actionsGlobalContext(); 
        Project project = lookup.lookup(Project.class);
        if( project != null ){
            this.file = project.getProjectDirectory();
        }
        
        
        // add a file change listener to the folder, otherwise there will be two updates for every change
        // (One for the file, and one for the folder)
        FileObject folder = file.getParent();
        folder.addFileChangeListener(new FileChangeAdapter() {
            
            @Override
            public void fileChanged(FileEvent ev) {
                reconstructOutline();
            }
            
            @Override
            public void fileAttributeChanged(FileAttributeEvent ev) {
                System.out.println(ev.getName() + ": " + ev.getOldValue() + " -> " + ev.getNewValue());
            }
        });
    }
   
    
    // try to get default main project
    public boolean getMainProjectFile(){
        
        Project mainProject = OpenProjects.getDefault().getMainProject();
        if( mainProject != null ){
            FileObject projectDir = mainProject.getProjectDirectory();
            String projectLocation = projectDir.getPath();
            this.file = FileUtil.toFileObject(new File(projectLocation));
            System.out.println("Project location: " + projectLocation);
            return true;
        }else{
            this.file = FileUtil.toFileObject(new File("C:\\Users\\Jeremy\\Documents\\NetBeansProjects\\NeTex\\sample.tex"));
            return false;
        }
    }
    
    public void reconstructOutline(){
        getProjectFile();
        try{
            
            TexFileParser parser = new TexFileParser(this.file);
            
            ElementNode newRoot = parser.beginParsing();
            if( newRoot != null ){
                this.root = newRoot;
            }
            manager.setRootContext(newRoot);
            this.repaint();
       }catch(Exception e){
           // can't do much
           this.root.setDisplayName("Parsing Failed.");
           // add error message box notifying user
       }
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new BeanTreeView();

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(NavigationWindowTopComponent.class, "NavigationWindowTopComponent.jButton1.text")); // NOI18N
        jButton1.setToolTipText(org.openide.util.NbBundle.getMessage(NavigationWindowTopComponent.class, "NavigationWindowTopComponent.jButton1.toolTipText")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(119, 119, 119)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reconstructOutline();
    }//GEN-LAST:event_jButton1ActionPerformed
// TODO: listen for 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
   
    // trigger for file changing
    public void fileChanged(FileEvent ev) {
        System.out.println("Contents of " + ev.getFile() + " changed.");
    }
    
    
    @Override
    public void componentOpened() {
    }
    

    @Override
    public void componentClosed() {
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

    @Override
    public String getDisplayHint() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JComponent getComponent() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void panelActivated(Lookup lkp) {
      
    }

    @Override
    public void panelDeactivated() {
        
    }

    // I WOULD LIKE TO SPEAK TO YOUR MANAGER  
    @Override
    public ExplorerManager getExplorerManager() {
       return manager;
    }
      

}
