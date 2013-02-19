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
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

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
 * Demo viewer based implemented as a standalone JFace application.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class ViewerApp extends ApplicationWindow {

	private ReportViewer reportViewer = new ReportViewer(SWT.BORDER);

	/**
	 * Constructs the viewer
	 */
	public ViewerApp() {
		super(null);
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		setBlockOnOpen(true);

		initMenu();
		initToolBar();
	}

	private void initMenu() {
		MenuManager mm = getMenuBarManager();

		MenuManager export = new MenuManager(Messages.getString("ViewerApp.exportMenuLabel")); //$NON-NLS-1$
		export.add(new ExportAsPdfAction(reportViewer));
        export.add(new ExportAsRtfAction(reportViewer));
        export.add(new ExportAsJasperReportsAction(reportViewer));
		export.add(new ExportAsHtmlAction(reportViewer));
		export.add(new ExportAsSingleXlsAction(reportViewer));
		export.add(new ExportAsMultiXlsAction(reportViewer));
		export.add(new ExportAsCsvAction(reportViewer));
		export.add(new ExportAsXmlAction(reportViewer));
		export.add(new ExportAsXmlWithImagesAction(reportViewer));

		MenuManager file = new MenuManager(Messages.getString("ViewerApp.fileMenuLabel")); //$NON-NLS-1$
		file.add(new ReloadAction(reportViewer));
		file.add(new Separator());
		file.add(export);
		file.add(new Separator());
		file.add(new PrintAction(reportViewer));
		mm.add(file);

		MenuManager view = new MenuManager(Messages.getString("ViewerApp.viewMenuLabel")); //$NON-NLS-1$
		view.add(new ZoomOutAction(reportViewer));
		view.add(new ZoomInAction(reportViewer));
		view.add(new Separator());
		view.add(new ZoomActualSizeAction(reportViewer));
		view.add(new ZoomFitPageAction(reportViewer));
		view.add(new ZoomFitPageWidthAction(reportViewer));
		mm.add(view);

		MenuManager nav = new MenuManager(Messages.getString("ViewerApp.navigateMenuLabel")); //$NON-NLS-1$
		nav.add(new FirstPageAction(reportViewer));
		nav.add(new PreviousPageAction(reportViewer));
		nav.add(new NextPageAction(reportViewer));
		nav.add(new LastPageAction(reportViewer));
		mm.add(nav);

	}

	/**
	 * @see org.eclipse.jface.window.ApplicationWindow#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.getString("ViewerApp.title")); //$NON-NLS-1$
		shell.setImage(new Image(null, JasperViewer.class
				.getResourceAsStream("images/jricon.GIF"))); //$NON-NLS-1$
	}

	private void initToolBar() {
		ToolBarManager tbManager = getToolBarManager();
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
	}

	/**
	 * @see org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginWidth = layout.marginHeight = 0;
		container.setLayout(layout);

		Control reportViewerControl = reportViewer.createControl(container);
		reportViewerControl.setLayoutData(new GridData(GridData.FILL_BOTH));

		StatusBar statusBar = new StatusBar();
		statusBar.setReportViewer(reportViewer);
		Control statusBarControl = statusBar.createControl(container);
		statusBarControl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		reportViewer.addHyperlinkListener(new DefaultHyperlinkHandler());

		return container;
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
		ViewerApp app = new ViewerApp();
		app.getReportViewer().loadDocument(fileName, isXMLFile);
		app.open();
	}

	private static void usage() {
		System.out.println(Messages.getString("ViewerApp.usageLabel")); //$NON-NLS-1$
		System.out.println(Messages.getString("ViewerApp.usage")); //$NON-NLS-1$
	}
}