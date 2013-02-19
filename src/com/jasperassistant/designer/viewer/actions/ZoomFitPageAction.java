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

import org.eclipse.jface.resource.ImageDescriptor;

import com.jasperassistant.designer.viewer.IReportViewer;
import com.jasperassistant.designer.viewer.ReportViewerEvent;

/**
 * Fit page zoom action
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class ZoomFitPageAction extends AbstractReportViewerAction {

	private static final ImageDescriptor ICON	=	
		ImageDescriptor.createFromFile(ZoomFitPageAction.class, "images/zoomfitpage.gif"); //$NON-NLS-1$
	private static final ImageDescriptor DISABLED_ICON	=	
		ImageDescriptor.createFromFile(ZoomFitPageAction.class, "images/zoomfitpaged.gif"); //$NON-NLS-1$
	
	/**
	 * @see AbstractReportViewerAction#AbstractReportViewerAction(IReportViewer)
	 */
	public ZoomFitPageAction(IReportViewer viewer) {
		super(viewer);
		
		setText(Messages.getString("ZoomFitPageAction.label")); //$NON-NLS-1$
		setToolTipText(Messages.getString("ZoomFitPageAction.tooltip")); //$NON-NLS-1$
		setImageDescriptor(ICON);
		setDisabledImageDescriptor(DISABLED_ICON);
		update();
	}

	private void update() {
		setChecked(getReportViewer().getZoomMode() == IReportViewer.ZOOM_MODE_FIT_PAGE);
	}
	
	/**
	 * @see com.jasperassistant.designer.viewer.actions.AbstractReportViewerAction#runBusy()
	 */
	protected void runBusy() {
		getReportViewer().setZoomMode(IReportViewer.ZOOM_MODE_FIT_PAGE);
		update();
	}

	/**
	 * @see com.jasperassistant.designer.viewer.actions.AbstractReportViewerAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		return getReportViewer().canChangeZoom();
	}
	
	/**
	 * @see com.jasperassistant.designer.viewer.actions.AbstractReportViewerAction#viewerStateChanged(com.jasperassistant.designer.viewer.ReportViewerEvent)
	 */
	public void viewerStateChanged(ReportViewerEvent evt) {
		update();
		super.viewerStateChanged(evt);
	}
}
