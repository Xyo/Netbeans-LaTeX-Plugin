/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.texbeans.textopdf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;
import javax.tools.FileObject;
import org.latex.filetype.TexDataObject;
import org.openide.*;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

@ActionID(
        category = "Bugtracking",
        id = "org.texbeans.textopdf.PDFConvert"
)
@ActionRegistration(
        iconBase = "org/texbeans/textopdf/logo.png",
        displayName = "#CTL_PDFConvert"
)
@ActionReference(path = "Toolbars/Build", position = 250)
@Messages("CTL_PDFConvert=Latex to PDF")
public final class PDFConvert implements ActionListener {


    TexDataObject context;

    public PDFConvert(DataObject context) {
        this.context = (TexDataObject) context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        TexDataObject tobj = Utilities.actionsGlobalContext().lookup(TexDataObject.class);                      
                String msg = FileUtil.toFile(tobj.getPrimaryFile()).getAbsolutePath();
                NotifyDescriptor nd = new NotifyDescriptor.Message(msg);
                DialogDisplayer.getDefault().notify(nd);
 
            

        }
    
}
