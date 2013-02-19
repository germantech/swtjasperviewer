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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.JRPrintHyperlink;
import net.sf.jasperreports.view.JRHyperlinkListener;

import org.eclipse.swt.custom.BusyIndicator;

import com.jasperassistant.designer.viewer.util.BrowserUtils;

/**
 * Default hyperlink listener implementation that opens links using  
 * browser.
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class DefaultHyperlinkHandler implements JRHyperlinkListener {

	/**
	 * @see net.sf.jasperreports.view.JRHyperlinkListener#gotoHyperlink(net.sf.jasperreports.engine.JRPrintHyperlink)
	 */
	public void gotoHyperlink(final JRPrintHyperlink link) throws JRException {
		if(link != null) {
			BusyIndicator.showWhile(null, new Runnable() {
				public void run() {
					gotoHyperlinkBusy(link);
				}
			});
		}
	}

	private void gotoHyperlinkBusy(JRPrintHyperlink link) {
		if(link.getHyperlinkType() == JRHyperlink.HYPERLINK_TYPE_REFERENCE) {
			openLink(link.getHyperlinkReference());
		} else if(link.getHyperlinkType() == JRHyperlink.HYPERLINK_TYPE_REMOTE_ANCHOR) {
			if (link.getHyperlinkReference() != null) {
				String href = link.getHyperlinkReference();
				if(link.getHyperlinkAnchor() != null)
				 	href += "#" + link.getHyperlinkAnchor(); //$NON-NLS-1$
				openLink(href);
			}
		} else if(link.getHyperlinkType() == JRHyperlink.HYPERLINK_TYPE_REMOTE_PAGE) {
			if (link.getHyperlinkReference() != null) {
				String href = link.getHyperlinkReference();
				if(link.getHyperlinkPage() != null)
					href += "#JR_PAGE_ANCHOR_0_" + link.getHyperlinkPage(); //$NON-NLS-1$
				openLink(href);
			}
		}
	}
	
	private void openLink(String href) {
		BrowserUtils.openLink(href);
	}
}
