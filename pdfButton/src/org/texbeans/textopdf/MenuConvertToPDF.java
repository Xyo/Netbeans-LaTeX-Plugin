/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.texbeans.textopdf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.latex.filetype.TexDataObject;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

@ActionID(
        category = "File",
        id = "org.texbeans.textopdf.MenuConvertToPDF"
)
@ActionRegistration(
        displayName = "#CTL_MenuConvertToPDF"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 0),
    @ActionReference(path = "Loaders/text/x-tex/Actions", position = 0)
})
@Messages("CTL_MenuConvertToPDF=Convert to PDF")
public final class MenuConvertToPDF implements ActionListener {

    private final DataObject context;

    public MenuConvertToPDF(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
       TexDataObject tobj = Utilities.actionsGlobalContext().lookup(TexDataObject.class);                      
                String msg = FileUtil.toFile(tobj.getPrimaryFile()).getAbsolutePath();
                NotifyDescriptor nd = new NotifyDescriptor.Message(msg);
                DialogDisplayer.getDefault().notify(nd);
 
            
    }
}
