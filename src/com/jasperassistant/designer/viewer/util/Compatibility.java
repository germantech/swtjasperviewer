package com.jasperassistant.designer.viewer.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

/**
 * Utility methods used to maintain binary compatibility accross different
 * JasperReports versions
 * 
 * @author Peter Severin (petru_severin@yahoo.com)
 */
public class Compatibility {
    private static Class classJRPrintFrame;

    private static Method classJRPrintFrame_getElements;

    static {
        try {
            classJRPrintFrame = Class.forName("net.sf.jasperreports.engine.JRPrintFrame");
            classJRPrintFrame_getElements = classJRPrintFrame.getMethod("getElements", null);
        } catch (ClassNotFoundException e) {
            // ignore. running under an old version of JasperReports
        } catch (SecurityException e) {
            compatibilityError(e);
        } catch (NoSuchMethodException e) {
            compatibilityError(e);
        }
    }

    private Compatibility() {
    }

    /**
     * Returns children of the given print element that is potentially a
     * container.
     * 
     * @param element
     *            the print element
     * @return the children list or null if the element has not children
     */
    public static List getChildren(JRPrintElement element) {
        if (classJRPrintFrame == null)
            return null;

        if (!classJRPrintFrame.isAssignableFrom(element.getClass()))
            return null;

        try {
            return (List) classJRPrintFrame_getElements.invoke(element, null);
        } catch (IllegalArgumentException e) {
            compatibilityError(e);
        } catch (IllegalAccessException e) {
            compatibilityError(e);
        } catch (InvocationTargetException e) {
            compatibilityError(e);
        }

        return null;
    }

    /**
     * Sets the <code>IS_ONE_PAGE_PER_SHEET</code> parameter for the given xls
     * exporter. This allows to overcome the binary incompatibility change
     * introduced in JasperReports 1.2.7 when the parameter's declaration was
     * moved to an abstract class.
     * 
     * @param exporter
     *            the xls exporter
     * @param value
     *            the parameter's value
     */
    public static void setOnePagePerSheetParameter(JRXlsExporter exporter, Boolean value) {
        try {
            Field field = JRXlsExporterParameter.class.getField("IS_ONE_PAGE_PER_SHEET");
            JRExporterParameter parameter = (JRExporterParameter) field.get(null);
            exporter.setParameter(parameter, value);
        } catch (SecurityException e) {
            compatibilityError(e);
        } catch (IllegalArgumentException e) {
            compatibilityError(e);
        } catch (NoSuchFieldException e) {
            compatibilityError(e);
        } catch (IllegalAccessException e) {
            compatibilityError(e);
        }
    }

    private static void compatibilityError(Throwable cause) {
        if (cause instanceof RuntimeException)
            throw (RuntimeException) cause;

        throw new RuntimeException(cause);
    }
}
