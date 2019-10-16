package com.think.sparrowadmin.common.config;

import com.think.sparrowadmin.common.util.OSinfoUtil;

/**
 * Global config
 * @author map6
 */
public class Config {
	public static final String SSO_ENCODING = "UTF-8";

	public static final String WIN_UPLOAD_FOLDER = "c:/sparrowUpload/";

	public static final String LINUX_UPLOAD_FOLDER = "/opt/sparrowUpload/";

	public static final String UPLOAD_FOLDER = OSinfoUtil.isWindows() ? Config.WIN_UPLOAD_FOLDER : Config.LINUX_UPLOAD_FOLDER;
}