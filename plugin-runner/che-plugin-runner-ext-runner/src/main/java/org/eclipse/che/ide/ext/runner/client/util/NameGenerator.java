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
package org.eclipse.che.ide.ext.runner.client.util;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * The class contains business logic which allows us to generate names for environments
 *
 * @author Dmitry Shnurenko
 */
public class NameGenerator {

    /** Utility class */
    private NameGenerator() {

    }


    /**
     * Gets environment name which consists of string 'Copy of ' and existing name with a current date
     * If there is an existing name, add a number suffix like "Copy2 of", "Copy3 of", etc.
     *  @return
     */
    @Nonnull
    public static String generateCopy(String name, List<String> existingNames) {
        String baseName = name.replace("+", " ");
        String computeName = "Copy of ".concat(baseName);
        boolean alreadyExists = existingNames.contains(computeName);
        int index = 2;
        while (alreadyExists) {
            computeName = "Copy".concat(String.valueOf(index)).concat(" of ").concat(baseName);
            alreadyExists = existingNames.contains(computeName);
            index++;
        }
        return computeName;
    }

}