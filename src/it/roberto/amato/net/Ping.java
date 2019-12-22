package it.roberto.amato.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * 
 * @author Roberto
 * */
public class Ping {
	
//	private static Logger log = Logger.getLogger(Ping.class.getName());
	
	public static void main(String... args) {
		if(args.length != 1) {
			System.out.println("You must specified one argument by CLI");
			System.exit(1);
		}
		
	
		String host = args[0];
		InetAddress hostAddress = null;
		isIP4Address(host);
		try {
			if(isIP4Address(host)) {
				String[] octetsString = host.split("\\.");
				byte[] octets = {
									(byte) Byte.toUnsignedInt(Byte.parseByte(octetsString[0])), 
									(byte) Byte.toUnsignedInt(Byte.parseByte(octetsString[1])),
									(byte) Byte.toUnsignedInt(Byte.parseByte(octetsString[2])),
									(byte) Byte.toUnsignedInt(Byte.parseByte(octetsString[3]))
								};
				hostAddress = InetAddress.getByAddress(octets);
			}
			else hostAddress = InetAddress.getByName(host);
			
			boolean isReachable = hostAddress.isReachable(5000);
			System.out.println("host with name: " + host + " is Rachable? "+ isReachable);
		} catch (UnknownHostException e) {
			System.out.println("Unknown host with name: " + host );
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static boolean isIP4Address(String address) {
		boolean isIpAddress = false;
		String[] octets = address.split("\\.");
		
		if(octets.length != 4) 
			isIpAddress =  false;
		else {
			for(String str : octets) {
				if(str.length()==0 || str.length()>3) {
					isIpAddress = false;
					break;
				}
				
				for(int i=0; i<str.length(); i++) {
					if(Character.isAlphabetic(str.charAt(i))) {
						isIpAddress = false;
						break;
					}
					else isIpAddress = true;
				}
				
				if(Integer.parseInt(str) < 0  || Integer.parseInt(str) > 255) {
					isIpAddress = false;
					break;
				}
				
			}
		}
		return isIpAddress;
	}
}
