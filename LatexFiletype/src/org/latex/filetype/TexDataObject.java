/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.latex.filetype;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport.ReadOnly;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

@Messages({
    "LBL_Tex_LOADER=Files of Tex"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_Tex_LOADER",
        mimeType = "text/x-tex",
        extension = {"tex", "TEX", "Tex"}
)
@DataObject.Registration(
        mimeType = "text/x-tex",
        iconBase = "org/latex/filetype/File_Text.png",
        displayName = "#LBL_Tex_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/x-tex/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    ),
    @ActionReference(
            path = "Loaders/text/x-tex/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    ),
    @ActionReference(
            path = "Loaders/text/x-tex/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    ),
    @ActionReference(
            path = "Loaders/text/x-tex/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    ),
    @ActionReference(
            path = "Loaders/text/x-tex/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    ),
    @ActionReference(
            path = "Loaders/text/x-tex/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    ),
    @ActionReference(
            path = "Loaders/text/x-tex/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    ),
    @ActionReference(
            path = "Loaders/text/x-tex/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    ),
    @ActionReference(
            path = "Loaders/text/x-tex/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class TexDataObject extends MultiDataObject {

    public TexDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/x-tex", true);
    }

    @Override
    protected int associateLookup() {
        return 1;
    }

    @MultiViewElement.Registration(
            displayName = "#LBL_Tex_EDITOR",
            iconBase = "org/latex/filetype/File_Text.png",
            mimeType = "text/x-tex",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "Tex",
            position = 1000
    )
    @Messages("LBL_Tex_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }
}
/*
    private static class TexChildFactory extends ChildFactory<String> {//creates children for texnodes

        private final TexDataObject dataobj;

        public TexChildFactory(TexDataObject dataobj) {
            this.dataobj = dataobj;
        }

        @Override
        protected boolean createKeys(List list) {
            FileObject fileobj = dataobj.getPrimaryFile();
            try {
                List<String> dataobjContent = fileobj.asLines();
                list.addAll(dataobjContent);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
            return true;
        }

        @Override
        protected Node createNodeForKey(String key) {
            Node childNode = new AbstractNode(Children.LEAF);
            childNode.setDisplayName(key);
            return childNode;
        }
    }

    class TexNode extends DataNode {//Tex Node, used for properties like line count

        public TexNode(TexDataObject aThis, Children kids, Lookup lookup) {
            super(aThis, kids, lookup);
        }

        @Override
        protected Sheet createSheet() {
            Sheet sheet = super.createSheet();
            Sheet.Set set = Sheet.createPropertiesSet();
            sheet.put(set);
            set.put(new LineCountProperty(this));
            return sheet;
        }

        private class LineCountProperty extends ReadOnly<Integer> {

            private final TexNode node;

            public LineCountProperty(TexNode node) {
                super("lineCount", Integer.class, "Line Count", "Number of Lines");
                this.node = node;
            }

            @Override
            public Integer getValue() throws IllegalAccessException, InvocationTargetException {
                int lineCount = 0;
                DataObject texDataobj = node.getDataObject();
                FileObject texlines = texDataobj.getPrimaryFile();
                try {
                    lineCount = texlines.asLines().size();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
                return lineCount;
            }
        }
    }

        @Override
    protected Node createNodeDelegate() {//parses the file
        return new TexNode(
                this,
                Children.create(new TexChildFactory(this), true),
                getLookup());
    }
}
*/