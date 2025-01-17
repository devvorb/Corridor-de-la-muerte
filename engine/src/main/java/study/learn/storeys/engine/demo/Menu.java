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
package study.learn.storeys.engine.demo;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import study.learn.storeys.engine.Interactlet;
import study.learn.storeys.engine.Prompter;

public class Menu extends Interactlet {

    @Override public void interact(Prompter<Void> prompter) throws IOException {
        AtomicBoolean quit = new AtomicBoolean(false);
        do {
            prompter.await(aChoice("Where do you want to go today?",
                "demo", "First Demo",
                "InfiniteDungeon", "Infinite Dungeon",
                "CorridorDeLaMuerte", "Corridor de la muerte",
                "monster1", "One Monster",
                "quit", "Stop playing"))
            .await(choice -> {
                switch (choice) {
                    case "demo": new Demo().interact(prompter); break;
                     case "InfiniteDungeon": new InfiniteDungeon().interact(prompter); break;
                    case "CorridorDeLaMuerte": new CorridorDeLaMuerte().interact(prompter); break;
                    case "monster1": new Monster().interact(prompter); break;
                    case "quit": quit.set(true);
                }
                return bye();
            });
        } while (!quit.get());
    }
}