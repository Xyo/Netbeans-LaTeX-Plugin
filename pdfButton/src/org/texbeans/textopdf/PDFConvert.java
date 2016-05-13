/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.texbeans.textopdf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.latex.filetype.TexDataObject;
import org.openide.*;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

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

        //String message = FileUtil.toFile(tobj.getPrimaryFile()).getAbsolutePath();
        //NotifyDescriptor nd = new NotifyDescriptor.Message(message);
        //DialogDisplayer.getDefault().notify(nd);
        /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
         */
        String msg = "The file has been saved by name ";
        String filename = "";
        //try {
        //    String wd = System.getProperty("user.dir");
        //    JFileChooser fc = new JFileChooser(wd);
        //    int rc = fc.showDialog(null, "Select Latex File");
        //    if (rc == JFileChooser.APPROVE_OPTION) {
        //        File file = fc.getSelectedFile();
        filename = FileUtil.toFile(tobj.getPrimaryFile()).getAbsolutePath();
        //    }else {
        //        return;
        //    }
        //}
        //catch (IOException exc) {
        //    exc.printStackTrace ();
        //}
          //C:.../.../document/netbeansprojects/latextemplate/texfile -> 
        String temp = filename.substring(0, filename.length() - 3);

        temp = temp.substring(0,temp.lastIndexOf('\\')+1);
        
                
        String args[] = {"pdflatex", filename, "-output-directory="+temp};
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(args);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        filename = filename.substring(0, filename.length() - 3);
        filename += "pdf";
        //String temp = filename.substring(1, filename.length() - 3);
//        temp += "pdf";
//
//        //NotifyDescriptor d = new NotifyDescriptor.Message(temp, NotifyDescriptor.INFORMATION_MESSAGE);
//        //DialogDisplayer.getDefault().notify(d);
//        String initialFilename = temp;
//
//        args = new String[4];
//        args[0] = "move";
//        args[1] = "/y";
//        args[2] = initialFilename;
//        args[3] = filename; //
//        try {
//            Runtime runtime = Runtime.getRuntime();
//            runtime.exec(args);
//        } catch (IOException exc) {
//            exc.printStackTrace();
//        } 

        try {
            Runtime r1 = Runtime.getRuntime();
            r1.exec("pdfopen --file=" + filename);
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        NotifyDescriptor d1 = new NotifyDescriptor.Message(msg + filename, NotifyDescriptor.INFORMATION_MESSAGE);
        DialogDisplayer.getDefault().notify(d1);
        TopComponent tc;

    }

    public String getName() {
        return null;
    }

    //@Override
    //protected String iconResource() {
    //    return "org/orgtest/test/icon.png";
    //}
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    //@Override
    //protected boolean asynchronous() {
    //    return false;
    //}
}
