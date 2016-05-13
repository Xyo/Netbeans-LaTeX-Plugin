package org.latex.project;

import java.beans.PropertyChangeListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups; 


public class LatexProject implements Project {
    
    private final FileObject projectDir;
    private final ProjectState state;
    private Lookup lkp;
    
    LatexProject(FileObject dir, ProjectState state) {
        this.projectDir = dir;
        this.state = state;
    } 
    
    @Override 
    public FileObject getProjectDirectory() { 
        return projectDir;
    } 
    
    @Override
    public Lookup getLookup() { 
        if (lkp == null) {
            lkp = Lookups.fixed(new Object[]{
                //features
                new Info(),
            });
        }
        return lkp;
    }
    
    private final class Info implements ProjectInformation {
        @StaticResource()
        public static final String LATEX_ICON = "org/latex/project/l_black.png";
        @Override 
        public Icon getIcon() { 
            return new ImageIcon(ImageUtilities.loadImage(LATEX_ICON));
        }
        
        @Override 
        public String getName() { 
            return getProjectDirectory().getName();
        }
        
        @Override 
        public String getDisplayName() {
            return getName();
        } 
        
        @Override
        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change } 
        }
        
        @Override
        public void removePropertyChangeListener(PropertyChangeListener pcl) { 
            //do nothing, won't change 
        } 
        
        @Override
        public Project getProject() {
            return LatexProject.this;
        }
    }
}