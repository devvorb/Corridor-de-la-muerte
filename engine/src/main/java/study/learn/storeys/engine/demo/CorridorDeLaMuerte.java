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
import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;

import study.learn.storeys.engine.Interactlet;
import study.learn.storeys.engine.Prompter;

public class CorridorDeLaMuerte extends Interactlet {

    Random rand = new Random();

    List<String> guardians = ImmutableList.of("a monstean", "a dhrek", "a spider" );
    String guardian = random(guardians);

    List<String> rewards = ImmutableList.of("55 gold", "a health potion", "an axe");
    String reward = random(rewards);

    List<String> cosmetics = ImmutableList.of("several flowers on the floor", "a small pond", "a shiny ruby", "a skeleton skull");
    String cosmetic = random(cosmetics);

    @Override public void interact(Prompter<Void> prompter) throws IOException {
        int playerHP = 10;
        int playerATT = 3;

        prompter.await(aChoice("You have " + playerHP + " HP, and " + playerATT
            + " damage.  You are in a room with " + reward + " guarded by " + guardian + " there is also " + cosmetic + ".",
            "leave", "you leave to the next room",
            "fight", "you challenge" + guardian + "to a battle"
           ));
    }

    String random(List<String> pickFrom) {
        return pickFrom.get(rand.nextInt(pickFrom.size()));
    }
}