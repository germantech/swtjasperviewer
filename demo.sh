#!/bin/sh
#
# Demo launcher
#

java -Djava.awt.headless=true -Djava.library.path=lib/linux-gtk -classpath swtjasperviewer-1.2.0.jar:lib/jasperreports-1.2.6.jar:lib/commons-beanutils-1.5.jar:lib/commons-collections-2.1.jar:lib/commons-digester-1.7.jar:lib/commons-logging.jar:lib/itext-1.3.1.jar:lib/poi-2.0-final-20040126.jar:lib/jcommon-1.0.0.jar:lib/jfreechart-1.0.0.jar:lib/jface.jar:lib/osgi.jar:lib/runtime.jar:lib/linux-gtk/swt.jar:lib/linux-gtk/swt-mozilla.jar:lib/linux-gtk/swt-pi.jar com.jasperassistant.designer.viewer.ViewerApp -Fdemo/HyperlinkReport.jrprint
