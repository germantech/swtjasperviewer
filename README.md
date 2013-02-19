Introduction
============

SWTJasperViewer is a JasperReports viewer component for SWT/JFace based
applications and Eclipse plug-ins. The viewer is designed with reusability in
mind so it can suit as many projects as possible.

Requirements
============
Sinces version 1.1.0, SWTJasperViewer requires JasperReports 1.2.6 or later.

Installation
============

Put the swtjasperviewer-x.x.x.jar in your classpath.

Demos
=====

Two batch scripts are provided to run the SWTJasperViewer demo under windows
and linux+gtk. For other widget toolkits and operation systems download
the corresponding version of SWT library from Eclipse.org.

Examples
========

There are two examples that demonstrate the use of SWTJasperViewer
component.

1. Viewer put inside a composite:
src/com/jasperassistant/designer/viewer/ViewerComposite.java

2. Viewer as a standalone JFace application
src/com/jasperassistant/designer/viewer/ViewerApp.java

Compiling
=========

A build file is included in the distribution of SWTJasperViewer. The only
requirement is to have ANT installed. Use the "lib" target to build the jar
file from source. Make sure you set the jdk.home property in the
build.properties file.


Enjoy!

Peter Severin (peter_p_s@users.sourceforge.net)
