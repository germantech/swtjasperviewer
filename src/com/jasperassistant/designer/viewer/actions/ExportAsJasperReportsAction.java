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

import java.io.File;

import net.sf.jasperreports.engine.util.JRSaver;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.custom.BusyIndicator;

import com.jasperassistant.designer.viewer.IReportViewer;

/**
 * JasperReports export action
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class ExportAsJasperReportsAction extends AbstractExportAction {

	private static final ImageDescriptor ICON = ImageDescriptor.createFromFile(
			ExportAsJasperReportsAction.class, "images/save.gif"); //$NON-NLS-1$

	private static final ImageDescriptor DISABLED_ICON = ImageDescriptor
			.createFromFile(ExportAsJasperReportsAction.class,
					"images/saved.gif"); //$NON-NLS-1$

	/**
	 * @see AbstractExportAction#AbstractExportAction(IReportViewer)
	 */
	public ExportAsJasperReportsAction(IReportViewer viewer) {
		super(viewer);

		setText(Messages.getString("ExportAsJasperReportsAction.label")); //$NON-NLS-1$
		setToolTipText(Messages.getString("ExportAsJasperReportsAction.tooltip")); //$NON-NLS-1$
		setImageDescriptor(ICON);
		setDisabledImageDescriptor(DISABLED_ICON);

		setFileExtensions(new String[] { "*.jrprint" }); //$NON-NLS-1$
		setFilterNames(new String[] { Messages.getString("ExportAsJasperReportsAction.filterName") }); //$NON-NLS-1$
		setDefaultFileExtension("jrprint"); //$NON-NLS-1$
	}

	/**
	 * @see com.jasperassistant.designer.viewer.actions.AbstractExportAction#export(java.io.File)
	 */
	protected void export(final File file) throws Throwable {
		final Throwable[] ex = new Throwable[1];
		BusyIndicator.showWhile(null, new Runnable() {
			public void run() {
				try {
					JRSaver.saveObject(getReportViewer().getDocument(), file);
				} catch (Throwable e) {
					ex[0] = e;
				}
			}
		});
		if(ex[0] != null)
			throw ex[0];
	}
}
