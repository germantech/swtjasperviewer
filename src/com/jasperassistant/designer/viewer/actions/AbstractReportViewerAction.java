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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.custom.BusyIndicator;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jasperassistant.designer.viewer.IReportViewerListener;
import com.jasperassistant.designer.viewer.ReportViewerEvent;

/**
 * Base class for report viewer actions
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public abstract class AbstractReportViewerAction extends Action implements
		IReportViewerListener {

	private IReportViewer reportViewer;

	/**
	 * Constructs the action by specifying the report viewer to associate with
	 * the action.
	 * 
	 * @param viewer
	 *            the report viewer
	 */
	public AbstractReportViewerAction(IReportViewer viewer) {
		Assert.isNotNull(viewer);
		this.reportViewer = viewer;
		viewer.addReportViewerListener(this);
		setEnabled(calculateEnabled());
	}

	/**
	 * Constructs the action by specifying the report viewer to associate with
	 * the action. An additional <code>style</code> style parameter allows to
	 * customize the action style.
	 * 
	 * @param viewer
	 *            the report viewer
	 * @param style
	 *            action style
	 * @see org.eclipse.jface.action.IAction
	 */
	public AbstractReportViewerAction(IReportViewer viewer, int style) {
		super(null, style);
		Assert.isNotNull(viewer);
		this.reportViewer = viewer;
		viewer.addReportViewerListener(this);
		setEnabled(calculateEnabled());
	}

	/**
	 * Calculates the enablement condition
	 * 
	 * @return true if the action should be enabled
	 */
	protected abstract boolean calculateEnabled();

	/**
	 * @see com.jasperassistant.designer.viewer.IReportViewerListener#viewerStateChanged(com.jasperassistant.designer.viewer.ReportViewerEvent)
	 */
	public void viewerStateChanged(ReportViewerEvent evt) {
		setEnabled(calculateEnabled());
	}

	/**
	 * Returns the reference to the report viewer associated to the action
	 * 
	 * @return the report viewer
	 */
	protected IReportViewer getReportViewer() {
		return reportViewer;
	}

	/**
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		BusyIndicator.showWhile(null, new Runnable() {
			public void run() {
				runBusy();
			}
		});
	}

	/**
	 * Subclasses can override this method to run the action with busy cursor.
	 */
	protected void runBusy() {

	}

	/**
	 * An explicit dispose method that must be called in case when the action is
	 * removed from its container dynamically (as opposed to the static
	 * toolbar/menu constructed only once). Calling this method will detach the
	 * action from the associated report viewer.
	 */
	public void dispose() {
		reportViewer.removeReportViewerListener(this);
	}
}
