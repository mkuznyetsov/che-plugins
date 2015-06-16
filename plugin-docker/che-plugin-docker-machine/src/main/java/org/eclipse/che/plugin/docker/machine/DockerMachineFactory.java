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
package org.eclipse.che.plugin.docker.machine;

import com.google.inject.assistedinject.Assisted;

import org.eclipse.che.api.core.util.LineConsumer;
import org.eclipse.che.api.machine.server.exception.MachineException;
import org.eclipse.che.api.machine.server.spi.Instance;
import org.eclipse.che.api.machine.server.spi.InstanceMetadata;
import org.eclipse.che.api.machine.server.spi.InstanceProcess;

import javax.annotation.Nullable;

/**
 * Provides Docker machine implementation instances.
 *
 * @author Alexander Garagatyi
 */
public interface DockerMachineFactory {
    InstanceMetadata createInstanceMetadata(@Assisted String container) throws MachineException;

    InstanceProcess createProcess(@Assisted("container") String container,
                                  @Assisted("command") @Nullable String command,
                                  @Assisted("pid_file_path") String pidFilePath,
                                  @Assisted int pid) throws MachineException;

    Instance createInstance(@Assisted("container") String containerId,
                            @Assisted DockerNode node,
                            @Assisted("workspace") String workspaceId,
                            @Assisted boolean bindWorkspace,
                            @Assisted LineConsumer outputConsumer) throws MachineException;

    DockerNode createNode(@Assisted String containerId) throws MachineException;
}