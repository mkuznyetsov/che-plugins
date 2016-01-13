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
package org.eclipse.che.ide.ext.svn.client.add;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;

import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.notification.StatusNotification;
import org.eclipse.che.ide.api.parts.WorkspaceAgent;
import org.eclipse.che.ide.ext.svn.client.SubversionClientService;
import org.eclipse.che.ide.ext.svn.client.SubversionExtensionLocalizationConstants;
import org.eclipse.che.ide.ext.svn.client.common.RawOutputPresenter;
import org.eclipse.che.ide.ext.svn.client.common.SubversionActionPresenter;
import org.eclipse.che.ide.ext.svn.shared.CLIOutputResponse;
import org.eclipse.che.ide.part.explorer.project.ProjectExplorerPresenter;
import org.eclipse.che.ide.rest.AsyncRequestCallback;
import org.eclipse.che.ide.rest.DtoUnmarshallerFactory;

import java.util.List;

/**
 * Handler for the {@link org.eclipse.che.ide.ext.svn.client.action.AddAction} action.
 */
@Singleton
public class AddPresenter extends SubversionActionPresenter {

    private final DtoUnmarshallerFactory                   dtoUnmarshallerFactory;
    private final NotificationManager                      notificationManager;
    private final SubversionClientService                  service;
    private final SubversionExtensionLocalizationConstants constants;

    private StatusNotification notification;

    /**
     * Constructor.
     */
    @Inject
    protected AddPresenter(final AppContext appContext,
                           final DtoUnmarshallerFactory dtoUnmarshallerFactory,
                           final EventBus eventBus,
                           final NotificationManager notificationManager,
                           final RawOutputPresenter console,
                           final SubversionExtensionLocalizationConstants constants,
                           final SubversionClientService service,
                           final WorkspaceAgent workspaceAgent,
                           final ProjectExplorerPresenter projectExplorerPart) {
        super(appContext, eventBus, console, workspaceAgent, projectExplorerPart);

        this.service = service;
        this.dtoUnmarshallerFactory = dtoUnmarshallerFactory;
        this.notificationManager = notificationManager;
        this.constants = constants;
    }

    public void showAdd() {
        final String projectPath = getCurrentProjectPath();
        if (projectPath == null) {
            return;
        }

        final List<String> selectedPaths = getSelectedPaths();
        notification = notificationManager.notify(constants.addStarted(), StatusNotification.Status.PROGRESS, true);

        service.add(projectPath, selectedPaths, null, false, true, false, false,
                    new AsyncRequestCallback<CLIOutputResponse>(dtoUnmarshallerFactory.newUnmarshaller(CLIOutputResponse.class)) {
                        @Override
                        protected void onSuccess(final CLIOutputResponse response) {

                            printResponse(response.getCommand(), response.getOutput(), response.getErrOutput());

                            if (response.getErrOutput() == null || response.getErrOutput().size() == 0) {
                                notification.setTitle(constants.addSuccessful());
                                notification.setStatus(StatusNotification.Status.SUCCESS);
                            } else {
                                notification.setTitle(constants.addWarning());
                                notification.setStatus(StatusNotification.Status.FAIL);
                            }
                        }

                        @Override
                        protected void onFailure(final Throwable exception) {
                            notification.setTitle(constants.addFailed());
                            notification.setContent(exception.getMessage());
                            notification.setStatus(StatusNotification.Status.FAIL);
                        }
                    });
    }

}
