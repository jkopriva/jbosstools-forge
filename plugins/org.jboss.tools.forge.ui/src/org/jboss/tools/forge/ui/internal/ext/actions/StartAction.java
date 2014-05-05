package org.jboss.tools.forge.ui.internal.ext.actions;

import java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.jboss.tools.forge.core.furnace.FurnaceService;
import org.jboss.tools.forge.core.runtime.ForgeRuntime;
import org.jboss.tools.forge.core.runtime.ForgeRuntimeState;
import org.jboss.tools.forge.ui.internal.ForgeUIPlugin;
import org.jboss.tools.forge.ui.internal.ext.util.FurnaceHelper;

public class StartAction extends Action {
	
	ForgeRuntime runtime;

	public StartAction(ForgeRuntime runtime) {
		super();
		this.runtime = runtime;
		setImageDescriptor(createImageDescriptor());
	}

	@Override
	public void run() {
		if (runtime == null || ForgeRuntimeState.RUNNING.equals(runtime.getState())) return;
		FurnaceHelper.createStartFurnaceJob().schedule();
	}
	
	@Override
	public boolean isEnabled() {
		return FurnaceService.INSTANCE.getContainerStatus().isStopped();
	}

	private ImageDescriptor createImageDescriptor() {
		URL url = ForgeUIPlugin.getDefault().getBundle().getEntry("icons/start.gif");
		return ImageDescriptor.createFromURL(url);
	}

}
