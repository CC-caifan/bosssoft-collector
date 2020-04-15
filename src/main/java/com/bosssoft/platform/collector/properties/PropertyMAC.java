package com.bosssoft.platform.collector.properties;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bosssoft.platform.collector.Constants;

public class PropertyMAC implements ServerProperty {

	private static Logger log = LoggerFactory.getLogger(PropertyMAC.class);

	private String errorMessage;

	public String propertyName() {

		return Constants.PROPERTY_NAME_MAC;
	}

	public String propertyValue() {
		//System.out.println("start get mac info.........");
		String hostMac = null;
		try {
			java.util.Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			StringBuilder sb = new StringBuilder();
			Set<String> tmpMacs = new HashSet<String>();
			while (en.hasMoreElements()) {
				NetworkInterface iface = en.nextElement();
				List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
				for (InterfaceAddress addr : addrs) {
					InetAddress ip = addr.getAddress();
					NetworkInterface network = NetworkInterface.getByInetAddress(ip);
					if (network == null) {
						continue;
					}
					byte[] mac = network.getHardwareAddress();
					if (mac == null) {
						continue;
					}
					sb.delete(0, sb.length());
					for (int i = 0; i < mac.length; i++) {
						sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? getMacSeparator() : ""));
					}
					tmpMacs.add(sb.toString());
				}
			}
			
			hostMac = StringUtils.join(tmpMacs, ",");
			//log.info("get mac info end,mac: ["+hostMac+"]");

		} catch (Exception e) {
			errorMessage = "get mac info fail";
			log.error("get mac info fail " + e.toString());
		}

		return hostMac;
	}

	public String getErrorMessage() {

		return errorMessage;
	}
	
	private String getMacSeparator(){
		String separator="-";
		String os = System.getProperty("os.name").toUpperCase();
		if (os.startsWith("WINDOWS"))
			separator = "-";
		else if (os.indexOf("LINUX") >= 0)
			separator =":";
		else if (os.indexOf("AIX") >= 0)
			separator =":";
		else if (os.indexOf("HP-UX") >= 0)
			separator =":";
		else if (os.indexOf("SOLARIS") >= 0) {
			separator =":";
		}
		return separator;
	}

}
