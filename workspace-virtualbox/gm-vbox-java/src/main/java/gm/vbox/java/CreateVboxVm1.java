package gm.vbox.java;

import static gm.vbox.java.CredentialStore.vBoxURL;
import static gm.vbox.java.CredentialStore.vBoxUsername;
import static gm.vbox.java.CredentialStore.vBoxPassword;

import org.virtualbox_5_0.*;
import org.virtualbox_5_0.jaxws.*;

import org.virtualbox_5_0.StorageBus;
import org.virtualbox_5_0.DeviceType;
import org.virtualbox_5_0.AccessMode;
import org.virtualbox_5_0.MediumVariant;
import org.virtualbox_5_0.LockType;

public class CreateVboxVm1 {

	private static final String machineName = "machinejava1";
	private static final String group = null;
	private static final String createFlags = null;
	private static final String baseFolder = null;
	private static final String osTypeId = "Oracle_64";
	private static final Long slot = 0L;

	public static void main(String[] args) {
		
		String machineFolder = null;
		
		// Get the IVirtualBox - that is actually, connection to your Virtualbox software
		// NOTE:  Just doing vbox = new IVirtualBox(null, null) does not work
		VirtualBoxManager mgr = VirtualBoxManager.createInstance(null);
		mgr.connect(vBoxURL, vBoxUsername, vBoxPassword);
		IVirtualBox vbox = mgr.getVBox();
		
		// Create the settings file
		String settingsFile = vbox.composeMachineFilename(machineName, group, createFlags, baseFolder);
		System.out.println ("Settings file name = " + settingsFile);

		if (null != settingsFile && settingsFile.length() > 0 )
		{
		    int endIndex = settingsFile.lastIndexOf("/");
		    if (endIndex != -1)  
		    {
		       machineFolder = settingsFile.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
		    }
		}  
		System.out.println ("Machine folder = " + machineFolder);
		
		// Create the file (aka machine - but a machine is not created immediately - only file gets created)
		// NOTE - vboxmanage list ostypes will give the list of os type id's instead of getGuesOSTypes method
		//System.out.println ("OS Type 0 = " + vbox.getGuestOSTypes().get(0).getId());
		//System.out.println ("OS Type 0 = " + vbox.getGuestOSTypes().get(0).getDescription());

		// Set os type
		IMachine myMachine = vbox.createMachine(settingsFile, machineName, null, osTypeId, "");
		//vbox.registerMachine(myMachine);
		
		// Network adapter
		myMachine.getNetworkAdapter(slot).setNATNetwork("NAT1");
		
		// Storage bus	
		myMachine.addStorageController("SATA1", StorageBus.SATA).setPortCount(2L);
		
		// Save the file
		System.out.println ("Saving the settings file");
		myMachine.saveSettings();
		
		vbox.registerMachine(myMachine);
		
		// Get a mutable/writeable machine
		ISession session = mgr.getSessionObject();
		myMachine.lockMachine(session, LockType.Write);
		IMachine myMachineMutable = session.getMachine();
		
		// Hard drive
		int controllerPort = 0;
		int deviceSlot = 0;
		//IMedium hardDisk = vbox.openMedium("C:\\vboxapivms\\machinejava1\\mainDisk.vmdk", DeviceType.HardDisk, AccessMode.ReadWrite, true);
		//IMedium hardDisk = vbox.createMedium("VMDK", "C:\\vboxapivms\\machinejava1\\mainDisk.vmdk", AccessMode.ReadWrite, DeviceType.HardDisk);
		IMedium hardDisk = vbox.createMedium("VMDK", machineFolder + "/mainDisk.vmdk", AccessMode.ReadWrite, DeviceType.HardDisk);
		hardDisk.createBaseStorage(1000000000L, null);
		
		myMachineMutable.attachDevice("SATA1", controllerPort, deviceSlot, DeviceType.HardDisk, hardDisk);

		myMachineMutable.saveSettings();
	}

}
