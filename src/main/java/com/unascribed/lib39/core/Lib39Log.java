package com.unascribed.lib39.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class Lib39Log {

	private static final Logger LOG = LoggerFactory.getLogger("Lib39");
	private static final boolean DEBUG_AS_INFO = Boolean.getBoolean("lib39.debug");

	// AUTOGENERATED - DO NOT EDIT

	public static boolean isTraceEnabled() {
		return LOG.isTraceEnabled();
	}

	public static void trace(String msg) {
		LOG.trace(msg);
	}

	public static void trace(String format, Object arg) {
		LOG.trace(format, arg);
	}

	public static void trace(String format, Object arg1, Object arg2) {
		LOG.trace(format, arg1, arg2);
	}

	public static void trace(String format, Object... arguments) {
		LOG.trace(format, arguments);
	}

	public static void trace(String msg, Throwable t) {
		LOG.trace(msg, t);
	}

	public static boolean isTraceEnabled(Marker marker) {
		return LOG.isTraceEnabled(marker);
	}

	public static void trace(Marker marker, String msg) {
		LOG.trace(marker, msg);
	}

	public static void trace(Marker marker, String format, Object arg) {
		LOG.trace(marker, format, arg);
	}

	public static void trace(Marker marker, String format, Object arg1, Object arg2) {
		LOG.trace(marker, format, arg1, arg2);
	}

	public static void trace(Marker marker, String format, Object... argArray) {
		LOG.trace(marker, format, argArray);
	}

	public static void trace(Marker marker, String msg, Throwable t) {
		LOG.trace(marker, msg, t);
	}

	public static boolean isDebugEnabled() {
		return DEBUG_AS_INFO || LOG.isDebugEnabled();
	}

	public static void debug(String msg) {
		if (DEBUG_AS_INFO) LOG.info(msg);
		LOG.debug(msg);
	}

	public static void debug(String format, Object arg) {
		if (DEBUG_AS_INFO) LOG.info(format, arg);
		LOG.debug(format, arg);
	}

	public static void debug(String format, Object arg1, Object arg2) {
		if (DEBUG_AS_INFO) LOG.info(format, arg1, arg2);
		LOG.debug(format, arg1, arg2);
	}

	public static void debug(String format, Object... arguments) {
		if (DEBUG_AS_INFO) LOG.info(format, arguments);
		LOG.debug(format, arguments);
	}

	public static void debug(String msg, Throwable t) {
		if (DEBUG_AS_INFO) LOG.info(msg, t);
		LOG.debug(msg, t);
	}

	public static boolean isInfoEnabled() {
		return LOG.isInfoEnabled();
	}

	public static void info(String msg) {
		LOG.info(msg);
	}

	public static void info(String format, Object arg) {
		LOG.info(format, arg);
	}

	public static void info(String format, Object arg1, Object arg2) {
		LOG.info(format, arg1, arg2);
	}

	public static void info(String format, Object... arguments) {
		LOG.info(format, arguments);
	}

	public static void info(String msg, Throwable t) {
		LOG.info(msg, t);
	}

	public static boolean isWarnEnabled() {
		return LOG.isWarnEnabled();
	}

	public static void warn(String msg) {
		LOG.warn(msg);
	}

	public static void warn(String format, Object arg) {
		LOG.warn(format, arg);
	}

	public static void warn(String format, Object... arguments) {
		LOG.warn(format, arguments);
	}

	public static void warn(String format, Object arg1, Object arg2) {
		LOG.warn(format, arg1, arg2);
	}

	public static void warn(String msg, Throwable t) {
		LOG.warn(msg, t);
	}

	public static boolean isErrorEnabled() {
		return LOG.isErrorEnabled();
	}

	public static void error(String msg) {
		LOG.error(msg);
	}

	public static void error(String format, Object arg) {
		LOG.error(format, arg);
	}

	public static void error(String format, Object arg1, Object arg2) {
		LOG.error(format, arg1, arg2);
	}

	public static void error(String format, Object... arguments) {
		LOG.error(format, arguments);
	}

	public static void error(String msg, Throwable t) {
		LOG.error(msg, t);
	}

	
	
}