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

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRHyperlinkListener;

/**
 * The report viewer interface. A report viewer is capable of displaying
 * JasperPrint documents and its state changes are observable through a listener
 * mechanism.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public interface IReportViewer {

	/** The zoom mode constant that stands for "no zoom" */
	public static final int ZOOM_MODE_NONE = 0;

	/**
	 * The zoom mode that instructs the viewer to display report pages at their
	 * actual size.
	 */
	public static final int ZOOM_MODE_ACTUAL_SIZE = 1;

	/**
	 * The zoom mode that instructs the viewer to display report pages so that
	 * they fit the available width.
	 */
	public static final int ZOOM_MODE_FIT_WIDTH = 2;

	/**
	 * The zoom mode that instructs the viewer to display report pages so that
	 * they fit the available height.
	 */
	public static final int ZOOM_MODE_FIT_HEIGHT = 3;

	/**
	 * The zoom mode that instructs the viewer to display report pages so that
	 * they fit the available width and height.
	 */
	public static final int ZOOM_MODE_FIT_PAGE = 4;

	/**
	 * Loads the document from the given file. The file can contain either a
	 * serialized <code>JasperPrint</code> object or a <strong>jrprint
	 * </strong> xml document. The format is controlled by the <code>xml</code>
	 * boolean parameter.
	 * 
	 * @param fileName
	 *            the file name
	 * @param xml
	 *            flag that indicates the type of file type. If true, the file
	 *            contains an xml and a serialized object otherwise.
	 */
	public void loadDocument(String fileName, boolean xml);

	/**
	 * Sets the jasper print document
	 * 
	 * @param document
	 *            document to set. Must be not null.
	 */
	public void setDocument(JasperPrint document);

	/**
	 * Returns the jasper print document
	 * 
	 * @return the jasper print docuemnt or null if none
	 */
	public JasperPrint getDocument();

	/**
	 * Returns true if there is a jasper print document set
	 * 
	 * @return true if the document is set
	 */
	public boolean hasDocument();

	/**
	 * Sets to null the currently set document.
	 * 
	 * @param reason
	 *            the reason for the missing document. Can be null.
	 */
	public void unsetDocument(String reason);

	/**
	 * Returns the message that explains the reason that the document is
	 * missing.
	 * 
	 * @return the reason or null if none.
	 */
	public String getReason();

	/**
	 * Register a report viewer listener that will observe the viewer state
	 * changes
	 * 
	 * @param listener
	 *            the listener
	 */
	public void addReportViewerListener(IReportViewerListener listener);

	/**
	 * Removes a previously registered report viewer listener
	 * 
	 * @param listener
	 *            the listener
	 */
	public void removeReportViewerListener(IReportViewerListener listener);

	/**
	 * Reloads the report. The reload will be performed if only the report was
	 * loaded through the <code>loadDocument</code> method.
	 * 
	 * @see #loadDocument(String, boolean)
	 */
	public void reload();

	/**
	 * Checks whether the document can be reloaded.
	 * 
	 * @return true if the current document can be reloaded
	 */
	public boolean canReload();

	//--------------------------------------------------------------------
	// Page navigation

	/**
	 * Returns the current page index.
	 * 
	 * @return the page index
	 */
	public int getPageIndex();

	/**
	 * Sets the current page index.
	 * 
	 * @param pageIndex
	 *            the page index
	 */
	public void setPageIndex(int pageIndex);

	/**
	 * Positions the viewer on the next page in the document
	 */
	public void gotoNextPage();

	/**
	 * Checks the preconditions for next page positioning
	 * 
	 * @return true if the viewer can be positioned to the next page.
	 */
	public boolean canGotoNextPage();

	/**
	 * Positions the viewer on the previous page in the document
	 */
	public void gotoPreviousPage();

	/**
	 * Checks the preconditions for previous page positioning
	 * 
	 * @return true if the viewer can be positioned to the previous page.
	 */
	public boolean canGotoPreviousPage();

	/**
	 * Positions the viewer on the last page in the document
	 */
	public void gotoLastPage();

	/**
	 * Checks the preconditions for last page positioning
	 * 
	 * @return true if the viewer can be positioned to the last page.
	 */
	public boolean canGotoLastPage();

	/**
	 * Positions the viewer on the first page in the document
	 */
	public void gotoFirstPage();

	/**
	 * Checks the preconditions for first page positioning
	 * 
	 * @return true if the viewer can be positioned to the first page.
	 */
	public boolean canGotoFirstPage();

	//--------------------------------------------------------------------
	// Zoom management

	/**
	 * Sets the zoom level
	 * 
	 * @param zoom
	 *            zoom level
	 */
	public void setZoom(double zoom);

	/**
	 * Returns the current zoom level
	 * 
	 * @return the zoom level
	 */
	public double getZoom();

	/**
	 * Checks the necessary preconditions for zoom changing
	 * 
	 * @return true if the zoom level and mode can be changed
	 */
	public boolean canChangeZoom();

	/**
	 * Sets the zoom mode. One of the predefined <code>ZOOM_MODE</code>
	 * constants must be used.
	 * 
	 * @param zoomMode
	 *            the zoom mode
	 */
	public void setZoomMode(int zoomMode);

	/**
	 * Returns the current zoom mode
	 * 
	 * @return the zoom mode
	 */
	public int getZoomMode();

	/**
	 * Returns an array with available zoom levels
	 * 
	 * @return a non-empty array of configured zoom levels
	 */
	public double[] getZoomLevels();

	/**
	 * Sets the available zoom levels
	 * 
	 * @param zoomLevels
	 *            a non-null and non-empty array of zoom levels
	 */
	public void setZoomLevels(double[] zoomLevels);

	/**
	 * Zooms in the viewer
	 */
	public void zoomIn();

	/**
	 * Checks the zoom-in operation preconditions
	 * 
	 * @return true if the viewer can be zoomed in
	 */
	public boolean canZoomIn();

	/**
	 * Zooms out the viewer
	 */
	public void zoomOut();

	/**
	 * Checks the zoom in preconditions
	 * 
	 * @return true if the viewer can be zoomed out
	 */
	public boolean canZoomOut();

	//--------------------------------------------------------------------
	// Hyperlinks

	/**
	 * Registers a hyperlink click listener that will be notified when user
	 * clicks a hyperlink.
	 * 
	 * @param listener
	 *            the listener to add
	 */
	public void addHyperlinkListener(JRHyperlinkListener listener);

	/**
	 * Removes a previously registered hyperlink click listener
	 * 
	 * @param listener
	 *            the listener to remove
	 */
	public void removeHyperlinkListener(JRHyperlinkListener listener);

	/**
	 * Returns registered hyperlink listeners
	 * 
	 * @return a non-null array of listeners
	 */
	public JRHyperlinkListener[] getHyperlinkListeners();
}