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
package com.jasperassistant.designer.viewer;

import net.sf.jasperreports.view.JasperViewer;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.DeviceData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

import com.jasperassistant.designer.viewer.actions.ExportAsCsvAction;
import com.jasperassistant.designer.viewer.actions.ExportAsHtmlAction;
import com.jasperassistant.designer.viewer.actions.ExportAsJasperReportsAction;
import com.jasperassistant.designer.viewer.actions.ExportAsMultiXlsAction;
import com.jasperassistant.designer.viewer.actions.ExportAsPdfAction;
import com.jasperassistant.designer.viewer.actions.ExportAsRtfAction;
import com.jasperassistant.designer.viewer.actions.ExportAsSingleXlsAction;
import com.jasperassistant.designer.viewer.actions.ExportAsXmlAction;
import com.jasperassistant.designer.viewer.actions.ExportAsXmlWithImagesAction;
import com.jasperassistant.designer.viewer.actions.ExportMenuAction;
import com.jasperassistant.designer.viewer.actions.FirstPageAction;
import com.jasperassistant.designer.viewer.actions.LastPageAction;
import com.jasperassistant.designer.viewer.actions.NextPageAction;
import com.jasperassistant.designer.viewer.actions.PageNumberContributionItem;
import com.jasperassistant.designer.viewer.actions.PreviousPageAction;
import com.jasperassistant.designer.viewer.actions.PrintAction;
import com.jasperassistant.designer.viewer.actions.ReloadAction;
import com.jasperassistant.designer.viewer.actions.ZoomActualSizeAction;
import com.jasperassistant.designer.viewer.actions.ZoomComboContributionItem;
import com.jasperassistant.designer.viewer.actions.ZoomFitPageAction;
import com.jasperassistant.designer.viewer.actions.ZoomFitPageWidthAction;
import com.jasperassistant.designer.viewer.actions.ZoomInAction;
import com.jasperassistant.designer.viewer.actions.ZoomOutAction;

/**
 * Demo viewer implemented as an SWT composite. It demonstrates how the viewer
 * can be integrated into an existing SWT application or an Eclipse plug-in.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class ViewerComposite extends Composite {

	private ReportViewer reportViewer;

	/**
	 * Constructs the viewer
	 * 
	 * @param parent
	 *            the parent
	 * @param style
	 *            the style. For possible styles see {@link Composite}class.
	 */
	public ViewerComposite(Composite parent, int style) {
		super(parent, style);
		createContents();
	}

	private void createContents() {
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = layout.marginWidth = 2;
		layout.verticalSpacing = 2;
		setLayout(layout);

		reportViewer = new ReportViewer(SWT.BORDER);

		ToolBarManager tbManager = new ToolBarManager(SWT.FLAT);
		ExportMenuAction exportMenu = new ExportMenuAction(reportViewer);
		IAction pdfAction = null;
		exportMenu.getMenuManager().add(
				pdfAction = new ExportAsPdfAction(reportViewer));
        exportMenu.getMenuManager().add(
                new ExportAsRtfAction(reportViewer));
		exportMenu.getMenuManager().add(
				new ExportAsJasperReportsAction(reportViewer));
		exportMenu.getMenuManager().add(new ExportAsHtmlAction(reportViewer));
		exportMenu.getMenuManager().add(
				new ExportAsSingleXlsAction(reportViewer));
		exportMenu.getMenuManager().add(
				new ExportAsMultiXlsAction(reportViewer));
		exportMenu.getMenuManager().add(new ExportAsCsvAction(reportViewer));
		exportMenu.getMenuManager().add(new ExportAsXmlAction(reportViewer));
		exportMenu.getMenuManager().add(
				new ExportAsXmlWithImagesAction(reportViewer));
		exportMenu.setDefaultAction(pdfAction);

		tbManager.add(exportMenu);
		tbManager.add(new PrintAction(reportViewer));
		tbManager.add(new ReloadAction(reportViewer));
		tbManager.add(new Separator());
		tbManager.add(new FirstPageAction(reportViewer));
		tbManager.add(new PreviousPageAction(reportViewer));
		if (SWT.getPlatform().equals("win32")) //$NON-NLS-1$
			tbManager.add(new PageNumberContributionItem(reportViewer));
		tbManager.add(new NextPageAction(reportViewer));
		tbManager.add(new LastPageAction(reportViewer));
		tbManager.add(new Separator());
		tbManager.add(new ZoomActualSizeAction(reportViewer));
		tbManager.add(new ZoomFitPageAction(reportViewer));
		tbManager.add(new ZoomFitPageWidthAction(reportViewer));
		tbManager.add(new Separator());
		tbManager.add(new ZoomOutAction(reportViewer));
		tbManager.add(new ZoomComboContributionItem(reportViewer));
		tbManager.add(new ZoomInAction(reportViewer));

		ToolBar toolbar = tbManager.createControl(this);
		toolbar.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control reportViewerControl = reportViewer.createControl(this);
		reportViewerControl.setLayoutData(new GridData(GridData.FILL_BOTH));

		StatusBar statusBar = new StatusBar();
		statusBar.setReportViewer(reportViewer);
		Control statusBarControl = statusBar.createControl(this);
		statusBarControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		reportViewer.addHyperlinkListener(new DefaultHyperlinkHandler());
	}

	/**
	 * Returns the report viewer used for viewing reports.
	 * 
	 * @return the report viewer
	 */
	public IReportViewer getReportViewer() {
		return reportViewer;
	}

	/**
	 * Main entry point
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		String fileName = null;
		boolean isXMLFile = false;

		if (args.length == 0) {
			usage();
			return;
		}

		int i = 0;
		while (i < args.length) {
			if (args[i].startsWith("-F")) //$NON-NLS-1$
				fileName = args[i].substring(2);
			if (args[i].startsWith("-XML")) //$NON-NLS-1$
				isXMLFile = true;

			i++;
		}

		if (fileName == null) {
			usage();
			return;
		}

		openViewer(fileName, isXMLFile);

		System.exit(0);
	}

	private static void openViewer(String fileName, boolean isXMLFile) {
		Display display = null;
	    if(Boolean.getBoolean("swtdebug")) { // activate memory leak debug tool
	        DeviceData data = new DeviceData();
	        data.tracking = true;
	        data.debug = true;
	        display = new Display(data);
	        Sleak sleak = new Sleak();
	        sleak.open();	        
	    } else {
	        display = new Display();
	    }
		Shell shell = new Shell(display);
		shell.setText(Messages.getString("ViewerComposite.label")); //$NON-NLS-1$
		shell.setLayout(new FillLayout());
		shell.setImage(new Image(null, JasperViewer.class
				.getResourceAsStream("images/jricon.GIF"))); //$NON-NLS-1$
		ViewerComposite viewer = new ViewerComposite(shell, SWT.NONE);
		viewer.getReportViewer().loadDocument(fileName, isXMLFile);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private static void usage() {
		System.out.println(Messages.getString("ViewerComposite.usageLabel")); //$NON-NLS-1$
		System.out.println(Messages.getString("ViewerComposite.usage")); //$NON-NLS-1$
	}
}