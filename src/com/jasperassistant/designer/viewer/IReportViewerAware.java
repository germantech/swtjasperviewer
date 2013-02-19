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

/**
 * Interface implemented by classes having an association to a report viewer.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public interface IReportViewerAware {

	/**
	 * Sets the report viewer
	 * 
	 * @param viewer
	 *            the report viewer
	 */
	public void setReportViewer(IReportViewer viewer);

	/**
	 * Returns a previously set report viewer
	 * 
	 * @return report viewer or null if none set
	 */
	public IReportViewer getReportViewer();

}