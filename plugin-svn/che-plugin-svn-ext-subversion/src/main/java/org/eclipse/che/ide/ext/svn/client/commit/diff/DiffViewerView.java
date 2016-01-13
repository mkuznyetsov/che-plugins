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
package org.eclipse.che.ide.ext.svn.client.commit.diff;

import org.eclipse.che.ide.api.mvp.View;

/**
 * View for {@link org.eclipse.che.ide.ext.svn.client.commit.diff.DiffViewerPresenter}.
 *
 * @author Vladyslav Zhukovskyi
 */
public interface DiffViewerView extends View<DiffViewerView.ActionDelegate> {
    /** Action handler for the view actions/controls. */
    public interface ActionDelegate {
        /** Perform actions when close button clicked. */
        void onCloseClicked();
    }

    /** Set diff content for current file. */
    void setDiffContent(String content);

    /** Perform actions when close window performed. */
    void onClose();

    /** Perform actions when open window performed. */
    void onShow();
}
