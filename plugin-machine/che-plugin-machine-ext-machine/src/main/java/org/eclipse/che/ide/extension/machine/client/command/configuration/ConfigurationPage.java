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
package org.eclipse.che.ide.extension.machine.client.command.configuration;

import org.eclipse.che.ide.api.mvp.Presenter;

/**
 * Page allows to control specific parameters of the command.
 *
 * @author Artem Zatsarynnyy
 */
public interface ConfigurationPage extends Presenter {

    void reset(CommandConfiguration configuration);
}
