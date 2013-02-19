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

import java.util.EventObject;

/**
 * Report viewer event is used to notify listeners that the state of a report
 * viewer has changed.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class ReportViewerEvent extends EventObject {

	/**
	 * Constructs the event with the specified event source
	 * 
	 * @param source
	 *            the source of the event
	 */
	public ReportViewerEvent(Object source) {
		super(source);
	}

}