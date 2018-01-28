/**
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
package ch.vorburger.minecraft.storeys.web;

import ch.vorburger.minecraft.storeys.events.ConditionService;
import ch.vorburger.minecraft.storeys.events.EventService;
import ch.vorburger.minecraft.storeys.plugin.AbstractStoreysPlugin;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import javax.inject.Inject;
import org.spongepowered.api.Game;
import org.spongepowered.api.data.type.HandType;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.event.item.inventory.ChangeInventoryEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent.Join;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = "storeys-web", name = "Vorburger.ch's Storeys with Web API", version = "1.0",
    description = "Makes entities narrate story lines so you can make your own movie in Minecraft",
    url = "https://github.com/vorburger/minecraft-storeys-maker",
    authors = "Michael Vorburger.ch")
public class StoreysWebPlugin extends AbstractStoreysPlugin {
    // do not extend StoreysPlugin, because we exclude that class in shadowJar

    @Inject
    private Game game;

    private VertxStarter vertxStarter;
    private EventService eventService;
    private ActionsConsumer actionsConsumer;

    @Override @Listener
    public void onGameStartingServer(GameStartingServerEvent event) {
        super.onGameStartingServer(event);

        int httpPort = 8080; // TODO read from some configuration
        vertxStarter = new VertxStarter();
        eventService = new EventService(this);
        try {
            actionsConsumer = new ActionsConsumer(this, game, eventService, new ConditionService(this), vertxStarter);
            vertxStarter.start(httpPort, actionsConsumer).get();
        } catch (ExecutionException  | InterruptedException e) {
            throw new IllegalStateException("Vert.x start-up failed", e);
        }
    }

    @Override @Listener
    public void onGameStoppingServer(GameStoppingServerEvent event) {
        actionsConsumer.stop();
        vertxStarter.stop();
        super.onGameStoppingServer(event);
    }

    // TODO Other Event registrations should later go up into AbstractStoreysPlugin so that Script can have Event triggers as well, but for now:

    @Listener
    public void onPlayerJoin(Join event) throws Exception {
        eventService.onPlayerJoin(event);
    }

    @Listener
    public void onInteractEntityEvent(InteractEntityEvent event) {
        eventService.onInteractEntityEvent(event);
    }

    @Listener
    public void onChangeInventoryEvent(ChangeInventoryEvent.Held event) {
    	Optional<Player> optPlayer = event.getCause().first(Player.class);
    	optPlayer.ifPresent(player -> {
    		player.getItemInHand(HandTypes.MAIN_HAND).get().getItem().;
    	});

    	logger.info("onChangeInventory event={}", event);
    	logger.info("onChangeInventory event={}", event.getTargetInventory().first().toString());
    }

//  @Listener
//  public void onInteractItemEvent(InteractItemEvent event) {
//      logger.info("InteractItemEvent: {}", event);
//  }

}
