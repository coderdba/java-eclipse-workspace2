package gm.vbox.java;

import org.virtualbox_5_0.*;
import org.virtualbox_5_0.jaxws.*;

public class Example1 {

	public static void main(String[] args) {

		VirtualBoxManager mgr = VirtualBoxManager.createInstance(null);
		String url = "http://localhost:18083";
		
		//boolean ws = false; // or true, if we need the SOAP version
		//if (ws)
			url="http://localhost:18083";
			String user = "username";
			String passwd = "password";
			mgr.connect(url, user, passwd);
			IVirtualBox vbox = mgr.getVBox();
			System.out.println("VirtualBox version: " + vbox.getVersion() + "\n");
			
			// get first VM name
			IMachine machine = vbox.getMachines().get(0);
			String machineName = vbox.getMachines().get(0).getName();
			System.out.println("\nAttempting to start VM : " + machineName );
			
			// start it
    		//mgr.startVm(machineName, null, 7000);
			ISession session = mgr.getSessionObject();
			
			machine.launchVMProcess(session, machineName, null);
    		
			//if (ws) 
		    mgr.disconnect();
			mgr.cleanup();
			
	}
}
