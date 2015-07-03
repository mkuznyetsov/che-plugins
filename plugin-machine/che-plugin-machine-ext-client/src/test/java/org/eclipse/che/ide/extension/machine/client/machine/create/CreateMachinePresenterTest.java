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
package org.eclipse.che.ide.extension.machine.client.machine.create;

import org.eclipse.che.api.project.gwt.client.ProjectTypeServiceClient;
import org.eclipse.che.api.project.shared.dto.ProjectDescriptor;
import org.eclipse.che.api.project.shared.dto.ProjectTypeDefinition;
import org.eclipse.che.api.promises.client.Function;
import org.eclipse.che.api.promises.client.Operation;
import org.eclipse.che.api.promises.client.Promise;
import org.eclipse.che.ide.api.app.AppContext;
import org.eclipse.che.ide.api.app.CurrentProject;
import org.eclipse.che.ide.extension.machine.client.machine.MachineManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Answers.RETURNS_DEEP_STUBS;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/** @author Artem Zatsarynny */
@RunWith(MockitoJUnitRunner.class)
public class CreateMachinePresenterTest {

    private final static String RECIPE_URL   = "http://www.host.com/recipe";
    private final static String MACHINE_NAME = "machine";

    @Mock
    private CreateMachineView        view;
    @Mock
    private MachineManager           machineManager;
    @Mock
    private AppContext               appContext;
    @Mock
    private ProjectTypeServiceClient projectTypeServiceClient;

    @InjectMocks
    private CreateMachinePresenter presenter;

    @Mock(answer = RETURNS_DEEP_STUBS)
    private ProjectDescriptor projectDescriptor;

    @Mock
    private Promise<ProjectTypeDefinition> projectTypePromise;
    @Mock
    private Promise<String>                recipeUrlPromise;

    @Captor
    private ArgumentCaptor<Function<ProjectTypeDefinition, String>> functionCaptor;
    @Captor
    private ArgumentCaptor<Operation<String>>                       recipeUrlCaptor;

    @Before
    public void setUp() {
        when(view.getRecipeURL()).thenReturn(RECIPE_URL);
        when(view.getMachineName()).thenReturn(MACHINE_NAME);
    }

    @Test
    public void shouldSetActionDelegate() throws Exception {
        verify(view).setDelegate(presenter);
    }

    @Test
    public void shouldShowView() throws Exception {
        final ProjectTypeDefinition projectTypeDefinition = mock(ProjectTypeDefinition.class);
        when(projectTypeDefinition.getDefaultRecipe()).thenReturn(RECIPE_URL);

        String projectTypeId = "p_type_id";
        when(projectDescriptor.getType()).thenReturn(projectTypeId);
        when(projectDescriptor.getRecipe()).thenReturn(null);
        final CurrentProject currentProject = mock(CurrentProject.class);
        when(currentProject.getRootProject()).thenReturn(projectDescriptor);
        when(appContext.getCurrentProject()).thenReturn(currentProject);

        when(projectTypeServiceClient.getProjectType(anyString())).thenReturn(projectTypePromise);
        when(projectTypePromise.then(any(Function.class))).thenReturn(recipeUrlPromise);

        presenter.showDialog();

        verify(view).show();
        verify(view).setCreateButtonState(eq(false));
        verify(view).setReplaceButtonState(eq(false));
        verify(view).setMachineName(eq(""));
        verify(view).setRecipeURL(eq(""));
        verify(view).setErrorHint(eq(false));

        verify(projectTypePromise).then(functionCaptor.capture());
        functionCaptor.getValue().apply(projectTypeDefinition);

        verify(recipeUrlPromise).then(recipeUrlCaptor.capture());
        recipeUrlCaptor.getValue().apply(RECIPE_URL);

        verify(view).setRecipeURL(eq(RECIPE_URL));
    }

    @Test
    public void buttonsShouldBeDisabledWhenNameIsEmpty() throws Exception {
        when(view.getMachineName()).thenReturn("");

        presenter.onNameChanged();

        verify(view).setCreateButtonState(eq(false));
        verify(view).setReplaceButtonState(eq(false));
    }

    @Test
    public void buttonsShouldBeEnabledWhenNameIsNotEmpty() throws Exception {
        presenter.onNameChanged();

        verify(view).setCreateButtonState(eq(true));
        verify(view).setReplaceButtonState(eq(true));
    }

    @Test
    public void shouldCreateMachine() throws Exception {
        presenter.onCreateClicked();

        verify(view).getRecipeURL();
        verify(view).getMachineName();
        verify(machineManager).startMachine(eq(RECIPE_URL), eq(MACHINE_NAME));
        verify(view).close();
    }

    @Test
    public void shouldReplaceDevMachine() throws Exception {
        presenter.onReplaceDevMachineClicked();

        verify(view).getMachineName();
        verify(machineManager).startAndBindMachine(eq(RECIPE_URL), eq(MACHINE_NAME));
        verify(view).close();
    }

    @Test
    public void shouldCloseView() throws Exception {
        presenter.onCancelClicked();

        verify(view).close();
    }
}
