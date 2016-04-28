/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.NeTeX.Window;

import javax.swing.ActionMap;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.NeTeX.Outline//Outline//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "OutlineTopComponent",
        iconBase = "org/NeTeX/Outline/outlineIcon.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "leftSlidingSide", openAtStartup = true)
@ActionID(category = "Window", id = "org.NeTeX.Outline.OutlineTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_OutlineAction",
        preferredID = "OutlineTopComponent"
)
@Messages({
    "CTL_OutlineAction=Outline",
    "CTL_OutlineTopComponent=Outline Window",
    "HINT_OutlineTopComponent=This is a Outline window"
})
public final class OutlineTopComponent extends TopComponent implements ExplorerManager.Provider {
    private final ExplorerManager manager = new ExplorerManager();
    
    public OutlineTopComponent() {
        initComponents();
        setName(Bundle.CTL_OutlineTopComponent());
        setToolTipText(Bundle.HINT_OutlineTopComponent());
        manager.setRootContext( new AbstractNode(Children.create(new ElementEventChildFactory(), true)) );
        ActionMap map = this.getActionMap();
        associateLookup (ExplorerUtils.createLookup (manager, map));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

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
    
    public ExplorerManager getExplorerManager(){
        return this.manager;
    }
    
    // switch all listeners on when component is shown
    protected void componentActivated() {
        ExplorerUtils.activateActions(manager, true);
    }
    // switch all listeners off when component is hidden
    protected void componentDeactivated() {
        ExplorerUtils.activateActions(manager, false);
    }
}
