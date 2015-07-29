/*******************************************************************************
 * Copyright (c) 2012-2015 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.ide.extension.machine.client.actions;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.eclipse.che.api.analytics.client.logger.AnalyticsEventLogger;
import org.eclipse.che.ide.api.action.AbstractPerspectiveAction;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.extension.machine.client.MachineLocalizationConstant;
import org.eclipse.che.ide.extension.machine.client.MachineResources;
import org.eclipse.che.ide.extension.machine.client.command.CommandConfiguration;
import org.eclipse.che.ide.extension.machine.client.machine.MachineManager;
import org.eclipse.che.ide.ui.dialogs.DialogFactory;

import java.util.Collections;

import static org.eclipse.che.ide.workspace.perspectives.project.ProjectPerspective.PROJECT_PERSPECTIVE_ID;

/**
 * Action to execute command which is selected in drop-down list.
 *
 * @author Artem Zatsarynnyy
 */
@Singleton
public class ExecuteSelectedCommandAction extends AbstractPerspectiveAction {
    private final AppContext                  appContext;
    private final DialogFactory               dialogFactory;
    private final SelectCommandComboBoxAction selectCommandAction;
    private final MachineLocalizationConstant localizationConstant;
    private final MachineManager              machineManager;
    private final AnalyticsEventLogger        eventLogger;

    @Inject
    public ExecuteSelectedCommandAction(MachineLocalizationConstant localizationConstant,
                                        MachineResources resources,
                                        AppContext appContext,
                                        DialogFactory dialogFactory,
                                        SelectCommandComboBoxAction selectCommandAction,
                                        MachineManager machineManager,
                                        AnalyticsEventLogger eventLogger) {
        super(Collections.singletonList(PROJECT_PERSPECTIVE_ID),
              localizationConstant.executeSelectedCommandControlTitle(),
              localizationConstant.executeSelectedCommandControlDescription(),
              null,
              resources.execute());
        this.localizationConstant = localizationConstant;
        this.appContext = appContext;
        this.dialogFactory = dialogFactory;
        this.selectCommandAction = selectCommandAction;
        this.machineManager = machineManager;
        this.eventLogger = eventLogger;
    }

    @Override
    public void updateInPerspective(ActionEvent e) {
        e.getPresentation().setVisible(appContext.getCurrentProject() != null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        eventLogger.log(this);

        if (machineManager.getDeveloperMachineId() != null) {
            final CommandConfiguration commandConfiguration = selectCommandAction.getSelectedCommand();
            if (commandConfiguration != null) {
                machineManager.execute(commandConfiguration);
            }
        } else {
            dialogFactory.createMessageDialog("", localizationConstant.noDevMachine(), null).show();
        }
    }
}