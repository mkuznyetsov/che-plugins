/*******************************************************************************
 * Copyright (c) 2012-2016 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.ide.ext.java.client;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import org.eclipse.che.ide.api.action.ActionManager;
import org.eclipse.che.ide.api.action.DefaultActionGroup;
import org.eclipse.che.ide.api.constraints.Anchor;
import org.eclipse.che.ide.api.constraints.Constraints;
import org.eclipse.che.ide.api.extension.Extension;
import org.eclipse.che.ide.api.filetypes.FileType;
import org.eclipse.che.ide.api.filetypes.FileTypeRegistry;
import org.eclipse.che.ide.api.icon.Icon;
import org.eclipse.che.ide.api.icon.IconRegistry;
import org.eclipse.che.ide.api.keybinding.KeyBindingAgent;
import org.eclipse.che.ide.api.keybinding.KeyBuilder;
import org.eclipse.che.ide.ext.java.client.action.FindUsagesAction;
import org.eclipse.che.ide.ext.java.client.action.NewJavaSourceFileAction;
import org.eclipse.che.ide.ext.java.client.action.NewPackageAction;
import org.eclipse.che.ide.ext.java.client.action.OpenDeclarationAction;
import org.eclipse.che.ide.ext.java.client.action.OpenImplementationAction;
import org.eclipse.che.ide.ext.java.client.action.QuickDocumentationAction;
import org.eclipse.che.ide.ext.java.client.action.FileStructureAction;
import org.eclipse.che.ide.ext.java.client.refactoring.move.MoveAction;
import org.eclipse.che.ide.ext.java.client.refactoring.rename.RenameRefactoringAction;
import org.eclipse.che.ide.ext.java.shared.Constants;
import org.eclipse.che.ide.util.browser.UserAgent;
import org.eclipse.che.ide.util.input.KeyCodeMap;

import static org.eclipse.che.ide.api.action.IdeActions.GROUP_ASSISTANT;
import static org.eclipse.che.ide.api.action.IdeActions.GROUP_FILE_NEW;

/** @author Evgen Vidolob */
@Extension(title = "Java", version = "3.0.0")
public class JavaExtension {

    private final String GROUP_ASSISTANT_REFACTORING  = "assistantRefactoringGroup";

    @Inject
    public JavaExtension(FileTypeRegistry fileTypeRegistry,
                         @Named("JavaFileType") FileType javaFile,
                         @Named("JavaClassFileType") FileType classFile,
                         @Named("JspFileType") FileType jspFile) {
        JavaResources.INSTANCE.css().ensureInjected();

        fileTypeRegistry.registerFileType(javaFile);
        fileTypeRegistry.registerFileType(jspFile);
        fileTypeRegistry.registerFileType(classFile);
    }

    /** For test use only. */
    public JavaExtension() {
    }

    @Inject
    private void prepareActions(JavaLocalizationConstant localizationConstant,
                                NewPackageAction newPackageAction,
                                KeyBindingAgent keyBinding,
                                NewJavaSourceFileAction newJavaSourceFileAction,
                                ActionManager actionManager,
                                MoveAction moveAction,
                                FileStructureAction fileStructureAction,
                                RenameRefactoringAction renameRefactoringAction,
                                QuickDocumentationAction quickDocumentationAction,
                                OpenDeclarationAction openDeclarationAction,
                                OpenImplementationAction openImplementationAction,
                                FindUsagesAction findUsagesAction) {

        DefaultActionGroup newGroup = (DefaultActionGroup)actionManager.getAction(GROUP_FILE_NEW);

        actionManager.registerAction("newJavaClass", newJavaSourceFileAction);
        newGroup.add(newJavaSourceFileAction, Constraints.FIRST);

        actionManager.registerAction("newJavaPackage", newPackageAction);
        newGroup.add(newPackageAction, new Constraints(Anchor.AFTER, "newJavaClass"));

        DefaultActionGroup refactorGroup = (DefaultActionGroup)actionManager.getAction(GROUP_ASSISTANT_REFACTORING);
        if (refactorGroup == null) {
            refactorGroup = new DefaultActionGroup("Refactoring", true, actionManager);
            actionManager.registerAction(GROUP_ASSISTANT_REFACTORING, refactorGroup);
        }

        DefaultActionGroup assistantGroup = (DefaultActionGroup)actionManager.getAction(GROUP_ASSISTANT);
        refactorGroup.addSeparator();
        refactorGroup.add(moveAction);
        refactorGroup.add(renameRefactoringAction);
        assistantGroup.add(refactorGroup, new Constraints(Anchor.BEFORE, "updateDependency"));

        actionManager.registerAction("showQuickDoc", quickDocumentationAction);
        actionManager.registerAction("openJavaDeclaration", openDeclarationAction);
        actionManager.registerAction("openImplementation", openImplementationAction);
        actionManager.registerAction("javaRenameRefactoring", renameRefactoringAction);
        actionManager.registerAction("javaMoveRefactoring", moveAction);
        actionManager.registerAction("javaFindUsages", findUsagesAction);
        actionManager.registerAction("javaClassStructure", fileStructureAction);

        assistantGroup.add(quickDocumentationAction, new Constraints(Anchor.BEFORE, GROUP_ASSISTANT_REFACTORING));
        assistantGroup.add(openDeclarationAction, new Constraints(Anchor.BEFORE, GROUP_ASSISTANT_REFACTORING));
        assistantGroup.add(openImplementationAction, new Constraints(Anchor.BEFORE, GROUP_ASSISTANT_REFACTORING));
        assistantGroup.add(fileStructureAction, new Constraints(Anchor.BEFORE, GROUP_ASSISTANT_REFACTORING));
        assistantGroup.add(findUsagesAction, new Constraints(Anchor.BEFORE, GROUP_ASSISTANT_REFACTORING));

        if (UserAgent.isMac()) {
            keyBinding.getGlobal().addKey(new KeyBuilder().alt().control().charCode('b').build(), "openImplementation");
            keyBinding.getGlobal().addKey(new KeyBuilder().control().charCode('j').build(), "showQuickDoc");
            keyBinding.getGlobal().addKey(new KeyBuilder().control().charCode(KeyCodeMap.F12).build(), "javaClassStructure");
        } else {
            keyBinding.getGlobal().addKey(new KeyBuilder().alt().action().charCode('b').build(), "openImplementation");
            keyBinding.getGlobal().addKey(new KeyBuilder().action().charCode('q').build(), "showQuickDoc");
            keyBinding.getGlobal().addKey(new KeyBuilder().action().charCode(KeyCodeMap.F12).build(), "javaClassStructure");
        }
        keyBinding.getGlobal().addKey(new KeyBuilder().none().charCode(KeyCodeMap.F4).build(), "openJavaDeclaration");
        keyBinding.getGlobal().addKey(new KeyBuilder().shift().charCode(KeyCodeMap.F6).build(), "javaRenameRefactoring");
        keyBinding.getGlobal().addKey(new KeyBuilder().charCode(KeyCodeMap.F6).build(), "javaMoveRefactoring");
        keyBinding.getGlobal().addKey(new KeyBuilder().alt().charCode(KeyCodeMap.F7).build(), "javaFindUsages");
    }

    @Inject
    private void registerIcons(IconRegistry iconRegistry, JavaResources resources) {
        // icon for category in Wizard
        iconRegistry.registerIcon(new Icon(Constants.JAVA_CATEGORY + ".samples.category.icon", resources.javaCategoryIcon()));
    }

}
