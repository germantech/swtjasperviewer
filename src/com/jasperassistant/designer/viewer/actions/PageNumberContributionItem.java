/*
 * SWTJasperViewer - Free SWT/JFace report viewer for JasperReports.
 * Copyright (C) 2004  Peter Severin (peter_p_s@users.sourceforge.net)
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 */
package com.jasperassistant.designer.viewer.actions;

import java.text.MessageFormat;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jasperassistant.designer.viewer.IReportViewerListener;
import com.jasperassistant.designer.viewer.ReportViewerEvent;

/**
 * Page number contribution item.
 * <p>
 * TODO: For the moment it seems that the layout of the text control in toolbar,
 * is acceptable only on win32 platform.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class PageNumberContributionItem extends ContributionItem implements IReportViewerListener,
        Listener {

    private IReportViewer viewer;

    private Text text;

    private ToolItem toolitem;

    /**
     * Constructs the action by specifying the report viewer to associate with
     * the item.
     * 
     * @param viewer
     *            the report viewer
     */
    public PageNumberContributionItem(IReportViewer viewer) {
        Assert.isNotNull(viewer);
        this.viewer = viewer;
        this.viewer.addReportViewerListener(this);
        refresh();
    }

    void refresh() {
        if (text == null || text.isDisposed())
            return;
        try {
            if (!viewer.hasDocument()) {
                text.setEnabled(false);
                text.setText(""); //$NON-NLS-1$
            } else {
                text.removeListener(SWT.DefaultSelection, this);
                text.setText(getPageMofNText());
                text.setEnabled(true);
                text.addListener(SWT.DefaultSelection, this);
            }
        } catch (SWTException exception) {
            if (!SWT.getPlatform().equals("gtk")) //$NON-NLS-1$
                throw exception;
        }
    }

    private Control createControl(Composite parent) {
        text = new Text(parent, SWT.BORDER | SWT.CENTER);
        text.setText(formatPageMofN(888, 888));
        text.addListener(SWT.DefaultSelection, this);
        text.pack();

        refresh();
        return text;
    }

    private static String formatPageMofN(int m, int n) {
        return MessageFormat.format(
                Messages.getString("PageNumberContributionItem.pageMofN"), new Object[] { //$NON-NLS-1$
                new Integer(m), new Integer(n) });
    }

    public void dispose() {
        viewer.removeReportViewerListener(this);
        text = null;
        viewer = null;
    }

    public final void fill(Composite parent) {
        createControl(parent);
    }

    public final void fill(Menu parent, int index) {
        Assert.isTrue(false, "Can't add page number to a menu");//$NON-NLS-1$
    }

    public void fill(ToolBar parent, int index) {
        toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
        Control control = createControl(parent);
        toolitem.setWidth(text.getSize().x);
        toolitem.setControl(control);
    }

    private void onSelection() {
        if (viewer.hasDocument()) {
            setPageAsText(text.getText());
        }
    }

    private void setPageAsText(String pageText) {
        try {
            final int pageIndex = Integer.parseInt(pageText) - 1;
            BusyIndicator.showWhile(null, new Runnable() {
                public void run() {
                    viewer.setPageIndex(pageIndex);
                }
            });
        } catch (NumberFormatException e) {
            Display.getCurrent().beep();
        }

        text.removeListener(SWT.DefaultSelection, this);
        text.setText(getPageMofNText());
        text.addListener(SWT.DefaultSelection, this);
    }

    private String getPageMofNText() {
        return formatPageMofN(viewer.getPageIndex() + 1, viewer.getDocument().getPages().size());
    }

    public void viewerStateChanged(ReportViewerEvent evt) {
        refresh();
    }

    public void handleEvent(Event event) {
        switch (event.type) {
        case SWT.Selection:
        case SWT.DefaultSelection:
            onSelection();
            break;
        }
    }
}
