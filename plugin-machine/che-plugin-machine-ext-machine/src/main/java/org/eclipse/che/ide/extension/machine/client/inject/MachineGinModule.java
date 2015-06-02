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
package org.eclipse.che.ide.extension.machine.client.inject;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.gwt.inject.client.multibindings.GinMultibinder;
import com.google.inject.Singleton;

import org.eclipse.che.ide.api.extension.ExtensionGinModule;
import org.eclipse.che.ide.extension.machine.client.command.CommandType;
import org.eclipse.che.ide.extension.machine.client.command.arbitrary.ArbitraryCommandType;
import org.eclipse.che.ide.extension.machine.client.command.edit.EditConfigurationsView;
import org.eclipse.che.ide.extension.machine.client.command.edit.EditConfigurationsViewImpl;
import org.eclipse.che.ide.extension.machine.client.command.execute.ExecuteArbitraryCommandView;
import org.eclipse.che.ide.extension.machine.client.command.execute.ExecuteArbitraryCommandViewImpl;
import org.eclipse.che.ide.extension.machine.client.inject.factories.EntityFactory;
import org.eclipse.che.ide.extension.machine.client.inject.factories.WidgetsFactory;
import org.eclipse.che.ide.extension.machine.client.machine.console.MachineConsoleToolbar;
import org.eclipse.che.ide.extension.machine.client.machine.console.MachineConsoleView;
import org.eclipse.che.ide.extension.machine.client.machine.console.MachineConsoleViewImpl;
import org.eclipse.che.ide.extension.machine.client.outputspanel.OutputsContainerView;
import org.eclipse.che.ide.extension.machine.client.outputspanel.OutputsContainerViewImpl;
import org.eclipse.che.ide.extension.machine.client.outputspanel.console.CommandConsoleFactory;
import org.eclipse.che.ide.extension.machine.client.outputspanel.console.CommandOutputConsole;
import org.eclipse.che.ide.extension.machine.client.outputspanel.console.CommandOutputConsoleView;
import org.eclipse.che.ide.extension.machine.client.outputspanel.console.OutputConsole;
import org.eclipse.che.ide.extension.machine.client.outputspanel.console.OutputConsoleView;
import org.eclipse.che.ide.extension.machine.client.perspective.MachinePerspective;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.machine.MachineWidget;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.machine.MachineWidgetImpl;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.tab.Tab;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.tab.TabImpl;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.tab.header.TabHeader;
import org.eclipse.che.ide.extension.machine.client.perspective.widgets.tab.header.TabHeaderImpl;
import org.eclipse.che.ide.ui.toolbar.ToolbarPresenter;
import org.eclipse.che.ide.workspace.perspectives.general.Perspective;

/**
 * GIN module for Machine extension.
 *
 * @author Artem Zatsarynnyy
 * @author Dmitry Shnurenko
 */
@ExtensionGinModule
public class MachineGinModule extends AbstractGinModule {

    /** {@inheritDoc} */
    @Override
    protected void configure() {
        GinMultibinder.newSetBinder(binder(), Perspective.class).addBinding().to(MachinePerspective.class);

        bind(ToolbarPresenter.class).annotatedWith(MachineConsoleToolbar.class).to(ToolbarPresenter.class).in(Singleton.class);
        bind(MachineConsoleView.class).to(MachineConsoleViewImpl.class).in(Singleton.class);

        bind(OutputConsoleView.class).to(CommandOutputConsoleView.class);
        install(new GinFactoryModuleBuilder().implement(OutputConsole.class, CommandOutputConsole.class)
                                             .build(CommandConsoleFactory.class));

        bind(OutputsContainerView.class).to(OutputsContainerViewImpl.class).in(Singleton.class);
        bind(ExecuteArbitraryCommandView.class).to(ExecuteArbitraryCommandViewImpl.class).in(Singleton.class);
        bind(EditConfigurationsView.class).to(EditConfigurationsViewImpl.class).in(Singleton.class);

        GinMultibinder.newSetBinder(binder(), CommandType.class).addBinding().to(ArbitraryCommandType.class);

        install(new GinFactoryModuleBuilder().implement(MachineWidget.class, MachineWidgetImpl.class)
                                             .implement(TabHeader.class, TabHeaderImpl.class).build(WidgetsFactory.class));
        install(new GinFactoryModuleBuilder().implement(Tab.class, TabImpl.class).build(EntityFactory.class));
    }
}
