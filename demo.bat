REM
REM Demo launcher
REM

java -Djava.awt.headless=true -Djava.library.path=lib\win32 -classpath swtjasperviewer-1.2.0.jar;lib\jasperreports-1.2.6.jar;lib\commons-beanutils-1.5.jar;lib\commons-collections-2.1.jar;lib\commons-digester-1.7.jar;lib\commons-logging.jar;lib\itext-1.3.1.jar;lib\poi-2.0-final-20040126.jar;lib\jcommon-1.0.0.jar;lib\jfreechart-1.0.0.jar;lib\jface.jar;lib\osgi.jar;lib\runtime.jar;lib\win32\swt.jar com.jasperassistant.designer.viewer.ViewerApp -Fdemo\PieChartReport.jrprint
