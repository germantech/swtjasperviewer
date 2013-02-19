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
package com.jasperassistant.designer.viewer.util;

import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;

/**
 * Utility class for opening urls using system browser 
 * 
 * @version $Id: BrowserUtils.java,v 1.2 2004/12/27 09:30:52 peter_p_s Exp $
 */
public class BrowserUtils {

	private static String browser;
	
	private BrowserUtils() {
	}

	private static Process launchBrowser(String url) throws IOException {
		Process process = null;

		if (browser == null) {
			try {
				browser = "netscape";  //$NON-NLS-1$
				process = Runtime.getRuntime().exec(browser + " " + url); ; //$NON-NLS-1$
			} catch (IOException e) {
				browser = "mozilla";  //$NON-NLS-1$
			}
		}

		if (process == null) {
			try {
				process = Runtime.getRuntime().exec(browser + " " + url); ; //$NON-NLS-1$
			} catch (IOException e) {
				throw e;
			}
		}

		return process;
	}

	private static void reportError(Display display) {
		display.asyncExec(new Runnable() {
				public void run() {
					MessageDialog.openError(Display.getCurrent().getActiveShell(),
						Messages.getString("BrowserUtils.error.title"), Messages.getString("BrowserUtils.error.message")); //$NON-NLS-1$ //$NON-NLS-2$
				}
			});
	}

	/**
	 * Launches the browser to open the specified url
	 * @param url the url to open
	 */
	public static void openLink(String url) {
		if (url.startsWith("file:")) {  //$NON-NLS-1$
			url = url.substring(5);

			while (url.startsWith("/")) {  //$NON-NLS-1$
				url = url.substring(1);
			}

			url = "file:///" + url;  //$NON-NLS-1$
		}

		String platform = SWT.getPlatform();

		if ("win32".equals(platform)) {  //$NON-NLS-1$
			Program.launch(url);
		} else if ("carbon".equals(platform)) {  //$NON-NLS-1$
			try {
				Runtime.getRuntime().exec("/usr/bin/open " + url);  //$NON-NLS-1$
			} catch (IOException e) {
				reportError(Display.getCurrent());
			}
		} else {
			new Thread(new Launcher(Display.getCurrent(), url), "BrowserUtils").start(); //$NON-NLS-1$
		}
	}
	
	private static class Launcher implements Runnable {

		private String url;
		private Display display;
		
		Launcher(Display display, String url) {
			this.url = url;
			this.display = display;
		}
		
		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			try {
				Process p = launchBrowser(url);

				try {
					if (p != null) {
						p.waitFor();
					}
				} catch (InterruptedException e) {
					reportError(display);
				}
			} catch (IOException e) {
				reportError(display);
			}
		}
		
	}
}
