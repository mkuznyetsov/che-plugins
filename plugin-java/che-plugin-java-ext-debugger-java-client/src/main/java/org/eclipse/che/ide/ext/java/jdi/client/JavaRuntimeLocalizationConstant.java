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
package org.eclipse.che.ide.ext.java.jdi.client;

/**
 * Interface to represent the messages contained in resource bundle: 'JavaRuntimeLocalizationConstant.properties'.
 *
 * @author Artem Zatsarynnyi
 */
public interface JavaRuntimeLocalizationConstant extends com.google.gwt.i18n.client.Messages {

    /* Buttons */
    @Key("breakpoints")
    String breakpoints();

    @Key("debug")
    String debug();

    @Key("disconnectButton")
    String disconnectButton();

    @Key("host")
    String host();

    @Key("port")
    String port();

    @Key("removeBreakpointsButton")
    String removeBreakpointsButton();

    @Key("resumeButton")
    String resumeButton();

    @Key("variables")
    String variables();

    @Key("absentInformationVariables")
    String absentInformationVariables();

    @Key("stepInto")
    String stepInto();

    @Key("stepOver")
    String stepOver();

    @Key("stepReturn")
    String stepReturn();

    @Key("changeValue")
    String changeValue();

    @Key("evaluateExpression")
    String evaluateExpression();

    @Key("debugger.connecting")
    String debuggerConnecting();

    @Key("debugger.disconnecting")
    String debuggerDisconnecting();

    @Key("debugger.connected")
    String debuggerConnected(String address);

    @Key("debugger.disconnected")
    String debuggerDisconnected(String address);

    /* ChangeValueView */
    @Key("view.changeValue.title")
    String changeValueViewTitle();

    @Key("view.changeValue.expressionField.title")
    String changeValueViewExpressionFieldTitle(String varName);

    @Key("view.changeValue.changeButton.title")
    String changeValueViewChangeButtonTitle();

    @Key("view.changeValue.cancelButton.title")
    String changeValueViewCancelButtonTitle();

    /* EvaluateExpressionView */
    @Key("view.evaluateExpression.title")
    String evaluateExpressionViewTitle();

    @Key("view.evaluateExpression.expressionField.title")
    String evaluateExpressionViewExpressionFieldTitle();

    @Key("view.evaluateExpression.resultField.title")
    String evaluateExpressionViewResultFieldTitle();

    @Key("view.evaluateExpression.evaluateButton.title")
    String evaluateExpressionViewEvaluateButtonTitle();

    @Key("view.evaluateExpression.closeButton.title")
    String evaluateExpressionViewCloseButtonTitle();

    @Key("evaluateExpressionFailed")
    String evaluateExpressionFailed(String reason);

    @Key("connect.to.remote")
    String connectToRemote();

    @Key("connect.to.remote.description")
    String connectToRemoteDescription();

    @Key("server.log")
    String displayServerLogTitle();

    @Key("server.log.description")
    String displayServerLogDescription();

    @Key("server.log.tab.title")
    String serverLogTabTitle();

}
