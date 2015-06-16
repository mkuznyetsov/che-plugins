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
package org.eclipse.che.ide.extension.machine.client.perspective.widgets.machine.appliance;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;

import org.eclipse.che.ide.extension.machine.client.MachineLocalizationConstant;
import org.eclipse.che.ide.extension.machine.client.inject.factories.EntityFactory;
import org.eclipse.che.ide.extension.machine.client.inject.factories.WidgetsFactory;
import org.eclipse.che.ide.extension.machine.client.machine.Machine;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.machine.appliance.server.ServerPresenter;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.machine.appliance.sufficientinfo.MachineInfoPresenter;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.machine.appliance.terminal.TerminalPresenter;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.tab.Tab;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.tab.container.TabContainerPresenter;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.tab.container.TabContainerView;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.tab.container.TabContainerView.TabSelectHandler;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.tab.header.TabHeader;
import org.eclipse.che.ide.part.PartStackPresenter.PartStackEventHandler;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class MachineAppliancePresenterTest {

    private final static String SOME_TEXT = "someText";

    //constructor mocks
    @Mock
    private EventBus                    eventBus;
    @Mock
    private PartStackEventHandler       partStackEventHandler;
    @Mock
    private MachineApplianceView        view;
    @Mock
    private MachineLocalizationConstant locale;
    @Mock
    private WidgetsFactory              widgetsFactory;
    @Mock
    private EntityFactory               entityFactory;
    //TODO un commit to test process tab
//    @Mock
//    private ProcessesPresenter          processesPresenter;
    @Mock
    private TerminalPresenter           terminalPresenter;
    @Mock
    private MachineInfoPresenter        infoPresenter;
    @Mock
    private ServerPresenter             serverPresenter;
    @Mock
    private TabContainerPresenter       tabContainer;

    //additional mocks
    @Mock
    private TabHeader        tabHeader;
    //    @Mock
//    private Tab              processTab;
    @Mock
    private Tab              terminalTab;
    @Mock
    private Tab              infoTab;
    @Mock
    private Tab              serverTab;
    @Mock
    private TabContainerView tabContainerView;
    @Mock
    private Machine          machine;
    @Mock
    private AcceptsOneWidget container;

    @Captor
    private ArgumentCaptor<TabSelectHandler> handlerCaptor;

    private MachineAppliancePresenter presenter;

    @Before
    public void setUp() {
        when(machine.getId()).thenReturn(SOME_TEXT);
        when(tabContainer.getView()).thenReturn(tabContainerView);

//        when(locale.tabProcesses()).thenReturn(SOME_TEXT);
        when(locale.tabTerminal()).thenReturn(SOME_TEXT);
        when(locale.tabInfo()).thenReturn(SOME_TEXT);
        when(locale.tabServer()).thenReturn(SOME_TEXT);

        when(widgetsFactory.createTabHeader(SOME_TEXT)).thenReturn(tabHeader);
//        when(entityFactory.createTab(Matchers.<TabHeader>anyObject(),
//                                     eq(processesPresenter),
//                                     Matchers.<TabSelectHandler>anyObject())).thenReturn(processTab);

        when(entityFactory.createTab(Matchers.<TabHeader>anyObject(),
                                     eq(terminalPresenter),
                                     Matchers.<TabSelectHandler>anyObject())).thenReturn(terminalTab);

        when(entityFactory.createTab(Matchers.<TabHeader>anyObject(),
                                     eq(infoPresenter),
                                     Matchers.<TabSelectHandler>anyObject())).thenReturn(infoTab);

        when(entityFactory.createTab(Matchers.<TabHeader>anyObject(),
                                     eq(serverPresenter),
                                     Matchers.<TabSelectHandler>anyObject())).thenReturn(serverTab);

        presenter = new MachineAppliancePresenter(eventBus,
                                                  partStackEventHandler,
                                                  view,
                                                  locale,
                                                  widgetsFactory,
                                                  entityFactory,
                                                  terminalPresenter,
                                                  infoPresenter,
                                                  serverPresenter,
                                                  tabContainer);
    }

    @Test
    public void constructorShouldBeVerified() {
        verify(widgetsFactory, times(3)).createTabHeader(SOME_TEXT);

//        verify(entityFactory).createTab(eq(tabHeader), eq(processesPresenter), Matchers.<TabSelectHandler>anyObject());
        verify(entityFactory).createTab(eq(tabHeader), eq(terminalPresenter), Matchers.<TabSelectHandler>anyObject());
        verify(entityFactory).createTab(eq(tabHeader), eq(infoPresenter), Matchers.<TabSelectHandler>anyObject());
        verify(entityFactory).createTab(eq(tabHeader), eq(serverPresenter), Matchers.<TabSelectHandler>anyObject());

//        verify(locale).tabProcesses();
        verify(locale).tabTerminal();
        verify(locale).tabInfo();
        verify(locale).tabServer();

//        verify(tabContainer).addTab(processTab);
        verify(tabContainer).addTab(terminalTab);
        verify(tabContainer).addTab(infoTab);
        verify(tabContainer).addTab(serverTab);

        verify(tabContainer).getView();

        verify(view).addContainer(tabContainerView);
    }

    @Test
    public void terminalHandlerShouldBePerformed() {
        callAndVerifyHandler();

        verify(locale).tabTerminal();
    }

    private void callAndVerifyHandler() {
        presenter.showAppliance(machine);

        verify(entityFactory).createTab(eq(tabHeader), eq(terminalPresenter), handlerCaptor.capture());
        handlerCaptor.getValue().onTabSelected();

        verify(machine).setActiveTabName(SOME_TEXT);
    }

    @Test
    public void infoHandlerShouldBePerformed() {
        callAndVerifyHandler();

        verify(locale).tabInfo();
    }

    @Test
    public void serverHandlerShouldBePerformed() {
        callAndVerifyHandler();

        verify(locale).tabServer();
    }

    @Test
    public void infoShouldBeShown() {
        reset(tabContainer);
//        when(machine.getId()).thenReturn(SOME_TEXT);
        when(machine.getActiveTabName()).thenReturn(SOME_TEXT);

        presenter.showAppliance(machine);

//        verify(machine).getId();
//        verify(processesPresenter).showProcesses(SOME_TEXT);
        verify(tabContainer).showTab(SOME_TEXT);
        verify(terminalPresenter).updateTerminal(machine);
        verify(infoPresenter).update(machine);
        verify(serverPresenter).updateInfo(machine);
    }

    @Test
    public void viewShouldBeSetToContainer() {
        presenter.go(container);

        verify(container).setWidget(view);
    }
}