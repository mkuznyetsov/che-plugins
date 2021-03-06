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
package org.eclipse.che.ide.extension.machine.client;

import com.google.gwt.i18n.client.Messages;

/**
 * I18n constants for the Machine extension.
 *
 * @author Artem Zatsarynnyi
 */
public interface MachineLocalizationConstant extends Messages {

    @Key("button.cancel")
    String cancelButton();

    @Key("main.menu.machine")
    String mainMenuMachine();

    @Key("control.selectCommand.text")
    String selectCommandControlTitle();

    @Key("control.selectCommand.description")
    String selectCommandControlDescription();

    @Key("control.selectCommand.emptyCurrentDevMachine.text")
    String selectCommandEmptyCurrentDevMachineText();

    @Key("control.selectCommand.emptyCurrentCommand.text")
    String selectCommandEmptyCurrentCommandText();

    @Key("control.runSelectedCommand.text")
    String executeSelectedCommandControlTitle();

    @Key("control.runSelectedCommand.description")
    String executeSelectedCommandControlDescription();

    @Key("control.runCommand.empty.params")
    String runCommandEmptyParamsMessage();

    @Key("control.runCommand.empty.name")
    String runCommandEmptyNameMessage();

    @Key("control.editCommands.text")
    String editCommandsControlTitle();

    @Key("control.editCommands.description")
    String editCommandsControlDescription();

    @Key("control.clearMachineConsole.text")
    String clearConsoleControlTitle();

    @Key("control.clearMachineConsole.description")
    String clearConsoleControlDescription();

    @Key("control.machine.create.text")
    String machineCreateTitle();

    @Key("control.machine.create.description")
    String machineCreateDescription();

    @Key("control.machine.destroy.text")
    String machineDestroyTitle();

    @Key("control.machine.destroyByName.text")
    String machineDestroyTitle(String machineName);

    @Key("control.machine.destroy.description")
    String machineDestroyDescription();

    @Key("control.terminal.create.text")
    String newTerminalTitle();

    @Key("control.terminal.create.description")
    String newTerminalDescription();


    /* Messages */
    @Key("messages.noDevMachine")
    String noDevMachine();

    @Key("messages.machine.not.found")
    String machineNotFound(String machineId);

    /* MachineStateNotifier */
    @Key("notification.creatingMachine")
    String notificationCreatingMachine(String machineId);

    @Key("notification.machineIsRunning")
    String notificationMachineIsRunning(String machineId);

    @Key("notification.destroyingMachine")
    String notificationDestroyingMachine(String machineId);

    @Key("notification.machineDestroyed")
    String notificationMachineDestroyed(String machineId);


    /* MachinePanelPresenter */
    @Key("view.machinePanel.title")
    String machinePanelTitle();

    @Key("view.machinePanel.tooltip")
    String machinePanelTooltip();


    /* MachineConsoleView */
    @Key("view.machineConsole.title")
    String machineConsoleViewTitle();

    @Key("view.machineConsole.tooltip")
    String machineConsoleViewTooltip();


    /* OutputsContainerView */
    @Key("view.outputsConsole.title")
    String outputsConsoleViewTitle();

    @Key("view.outputsConsole.tooltip")
    String outputsConsoleViewTooltip();

    @Key("view.outputsConsole.stopProcessConfirmation")
    String outputsConsoleViewStopProcessConfirmation(String processName);


    /* CreateMachineView */
    @Key("view.createMachine.title")
    String viewCreateMachineTitle();

    @Key("view.createMachine.button.create")
    String viewCreateMachineButtonCreate();

    @Key("view.createMachine.button.replace")
    String viewCreateMachineButtonReplace();

    @Key("view.createMachine.message.urlIsNotValid")
    String viewCreateMachineUrlIsNotValid();

    @Key("view.createMachine.message.noRecipe")
    String viewCreateMachineNoRecipe();

    @Key("view.createMachine.name")
    String viewCreateMachineName();

    @Key("view.createMachine.recipeURL")
    String viewCreateMachineRecipeURL();

    @Key("view.createMachine.findByTags")
    String viewCreateMachineFindByTags();


    /* EditCommandsView */
    @Key("view.editCommands.placeholder")
    String editCommandsViewPlaceholder();

    @Key("view.editCommands.title")
    String editCommandsViewTitle();

    @Key("view.editCommands.hint")
    String editCommandsViewHint();

    @Key("view.editCommands.name.text")
    String editCommandsViewNameText();

    @Key("view.editCommands.remove.title")
    String editCommandsViewRemoveTitle();

    @Key("view.editCommands.remove.confirmation")
    String editCommandsRemoveConfirmation(String commandName);

    @Key("view.editCommands.execute.message")
    String editCommandsExecuteMessage();

    @Key("view.editCommands.saveChanges.title")
    String editCommandsSaveChangesTitle();

    @Key("view.editCommands.saveChanges.text")
    String editCommandsSaveChangesConfirmation(String commandName);

    @Key("view.editCommands.saveChanges.discard")
    String editCommandsSaveChangesDiscard();


    @Key("process.active")
    String processActive();

    @Key("process.table.name")
    String processTableName();

    @Key("process.table.protocol")
    String processTableProtocol();

    @Key("process.table.port")
    String processTablePort();

    @Key("process.table.time")
    String processTableTime();

    @Key("process.table.active")
    String processTableActive();

    @Key("tab.terminal")
    String tabTerminal();

    @Key("perspective.project.action.tooltip")
    String perspectiveProjectActionTooltip();

    @Key("perspective.machine.action.tooltip")
    String perspectiveMachineActionTooltip();

    @Key("tab.info")
    String tabInfo();

    @Key("info.name")
    String infoName();

    @Key("info.machine.id")
    String infoMachineId();

    @Key("info.owner")
    String infoOwner();

    @Key("info.status")
    String infoStatus();

    @Key("info.type")
    String infoType();

    @Key("info.dev")
    String infoDev();

    @Key("info.ws.id")
    String infoWsId();

    @Key("info.server.port")
    String infoServerPort();

    @Key("info.server.address")
    String infoServerAddress();

    @Key("tab.server")
    String tabServer();

    @Key("info.server.ref")
    String infoServerRef();

    @Key("info.server.url")
    String infoServerUrl();

    @Key("unavailable.machine.info")
    String unavailableMachineInfo();

    @Key("control.machine.restart.text")
    String controlMachineRestartText();

    @Key("control.machine.restart.tooltip")
    String controlMachineRestartTooltip();

    @Key("control.machine.restart.text.by.name")
    String machineRestartTextByName(String text);

    @Key("notification.machine.restarting")
    String notificationMachineRestarting(String text);

    @Key("machine.restarted")
    String machineRestarted(String text);

    @Key("view.recipePanel.title")
    String viewRecipePanelTitle();

    @Key("view.recipePanel.tooltip")
    String viewRecipePanelTooltip();

    @Key("editor.button.new")
    String editorButtonNew();

    @Key("editor.button.save")
    String editorButtonSave();

    @Key("editor.button.delete")
    String editorButtonDelete();

    @Key("editor.button.cancel")
    String editorButtonCancel();

    @Key("tab.recipe")
    String tabRecipe();

    @Key("editor.button.clone")
    String editorButtonClone();

    @Key("connection.failed.with.terminal")
    String connectionFailedWithTerminal();

    @Key("terminal.error.connection")
    String terminalErrorConnection();

    @Key("terminal.can.not.load.script")
    String terminalCanNotLoadScript();

    @Key("failed.to.connect.the.terminal")
    String failedToConnectTheTerminal();

    @Key("terminal.restart.trying")
    String terminalTryRestarting();

    @Key("terminal.error.start")
    String terminalErrorStart();

    @Key("unavailable.machine.starting")
    String unavailableMachineStarting(String machineName);

    @Key("view.consoles.title")
    String viewConsolesTitle();

    @Key("view.processes.tooltip")
    String viewProcessesTooltip();

    @Key("view.processes.dev.title")
    String viewProcessesDevTitle();

    @Key("view.processes.command.title")
    String viewProcessesCommandTitle();

    @Key("view.processes.terminal.node.title")
    String viewProcessesTerminalNodeTitle(String terminalIndex);

    @Key("view.stop.process.tooltip")
    String viewStropProcessTooltip();

    @Key("view.new.terminal.tooltip")
    String viewNewTerminalTooltip();

    @Key("view.machine.running.tooltip")
    String viewMachineRunningTooltip();

    @Key("view.close.processOutput.tooltip")
    String viewCloseProcessOutputTooltip();

    @Key("failed.to.execute.command")
    String failedToExecuteCommand();

    @Key("failed.to.create.recipe")
    String failedToCreateRecipe();

    @Key("failed.to.save.recipe")
    String failedToSaveRecipe();

    @Key("failed.to.find.machine")
    String failedToFindMachine(String machineId);

    @Key("failed.to.get.processes")
    String failedToGetProcesses(String machineId);

    @Key("ssh.connect.info")
    String sshConnectInfo(String machineName, String machineHost, String machinePort);
}
