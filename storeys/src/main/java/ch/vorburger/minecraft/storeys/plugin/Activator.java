/*
 * ch.vorburger.minecraft.storeys
 *
 * Copyright (C) 2016 - 2018 Michael Vorburger.ch <mike@vorburger.ch>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.vorburger.minecraft.storeys.plugin;

import ch.vorburger.minecraft.osgi.api.CommandRegistration;
import ch.vorburger.minecraft.osgi.api.PluginInstance;
import ch.vorburger.minecraft.storeys.commands.NarrateCommand;
import ch.vorburger.minecraft.storeys.commands.StoryCommand;
import java.nio.file.Paths;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

    // Bundle-Activator is commented out in bnd.bnd,
    // because the base storeys/ VS web/ currently cannot (should not) be installed together..
    // To make this possibly cleanly, the base storeys would have to expose services which web uses, instead of subclassing the Plugin..

    private ServiceReference<PluginInstance> pluginInstanceServiceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        pluginInstanceServiceReference = context.getServiceReference(PluginInstance.class);
        PluginInstance osgiPlugin = context.getService(pluginInstanceServiceReference);

        // TODO reigster GuardGameModeJoinListener

        context.registerService(CommandRegistration.class, new StoryCommand(osgiPlugin, Paths.get("config/storeys")), null);
        context.registerService(CommandRegistration.class, new NarrateCommand(osgiPlugin), null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        context.ungetService(pluginInstanceServiceReference);
        // No need to unregister our services, because when a bundle stops,
        // any services that it registered will be automatically unregistered.
    }

}
