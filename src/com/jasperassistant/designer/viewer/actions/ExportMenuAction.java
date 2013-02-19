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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

import com.jasperassistant.designer.viewer.IReportViewer;

/**
 * Export menu action. Provides a pull down menu that can be used to place
 * specific export action.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class ExportMenuAction extends AbstractReportViewerAction implements IMenuCreator {

	private static final ImageDescriptor ICON	=	
		ImageDescriptor.createFromFile(ExportMenuAction.class, "images/save.gif"); //$NON-NLS-1$
	private static final ImageDescriptor DISABLED_ICON	=	
		ImageDescriptor.createFromFile(ExportMenuAction.class, "images/saved.gif"); //$NON-NLS-1$

	private MenuManager menuManager = new MenuManager();
	private Menu menu;
	private IAction defaultAction;
	
	/**
	 * @see AbstractReportViewerAction#AbstractReportViewerAction(IReportViewer)
	 */
	public ExportMenuAction(IReportViewer viewer) {
		super(viewer, AS_DROP_DOWN_MENU);

		setText(Messages.getString("ExportMenuAction.label")); //$NON-NLS-1$
		setToolTipText(Messages.getString("ExportMenuAction.tooltip")); //$NON-NLS-1$
		setImageDescriptor(ICON);
		setDisabledImageDescriptor(DISABLED_ICON);
		setMenuCreator(this);
	}

	/**
	 * @see com.jasperassistant.designer.viewer.actions.AbstractReportViewerAction#calculateEnabled()
	 */
	protected boolean calculateEnabled() {
		return getReportViewer().hasDocument();
	}

	/**
	 * @see com.jasperassistant.designer.viewer.actions.AbstractReportViewerAction#run()
	 */
	public void run() {
		if(defaultAction != null && defaultAction.isEnabled())
			defaultAction.run();
	}
	
	/**
	 * Returns the menu manager associated with this action. Actions added
	 * to the menu manager will appear in the drop-down menu.
	 * @return the menu manager
	 */
	public MenuManager getMenuManager() {
		return menuManager;
	}

	/**
	 * @see com.jasperassistant.designer.viewer.actions.AbstractReportViewerAction#dispose()
	 */
	public void dispose() {
		menuManager.dispose();
	}

	/**
	 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
	 */
	public Menu getMenu(Control parent) {
		if(menu == null)
			menu = menuManager.createContextMenu(parent);
		return menu;
	}

	/**
	 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
	 */
	public Menu getMenu(Menu parent) {
		return null;
	}

	/**
	 * @return the default action
	 * @see #setDefaultAction(IAction)
	 */
	public IAction getDefaultAction() {
		return defaultAction;
	}
	
	/**
	 * Sets the default action that gets executed when the menu
	 * button is clicked. 
	 * @param defaultAction the default action
	 */
	public void setDefaultAction(IAction defaultAction) {
		this.defaultAction = defaultAction;
	}
}
