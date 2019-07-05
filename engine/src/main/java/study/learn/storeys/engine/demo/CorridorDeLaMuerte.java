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

    List<Guardian> guardians;

    List<String> rewards = ImmutableList.of("55 gold", "a health potion", "a sword");
    String reward = random(rewards);

    List<String> cosmetics = ImmutableList.of("several flowers on the floor", "a small pond", "a shiny ruby",
            "a skeleton skull");
    String cosmetic = random(cosmetics);

    CorridorDeLaMuerte() {
        Guardian guardian1 = new Guardian();
        guardian1.name = "monstean";
        guardian1.Gdamage = 1;
        guardian1.Gwin = 4;

        Guardian guardian2 = new Guardian();
        guardian2.name = "Dhrek";
        guardian2.Gdamage = 4;
        guardian2.Gwin = 7;

        Guardian guardian3 = new Guardian();
        guardian3.name = "spider";
        guardian3.Gdamage = 7;
        guardian3.Gwin = 2;

        guardians = ImmutableList.of(guardian1, guardian2, guardian3);
    }

    @Override
    public void interact(Prompter<Void> prompter) throws IOException {
        int playerHP = 10;
        int playerATT = 3;

        Guardian guardian = random(guardians);

        prompter.await(aChoice(
                "You have " + playerHP + " HP, and " + playerATT + " damage.  You are in a room with " + reward
                        + " guarded by " + guardian.name + " there is also " + cosmetic + ".",
                "leave", "you leave to the next room", "fight", "you challenge " + guardian.name + " to a battle"))
                .await(choice -> {
                    switch (choice) {
                    case "fight": {
                        if (playerATT >= guardian.Gwin) {
                            return bye("you defeated the " + guardian.name);
                        } else if (playerATT < guardian.Gwin) {
                            return bye("you got defeated by the " + guardian.name);
                        }
                    }
                        break;
                    case "leave": {
                        return bye();
                    }
                    }
                    return bye();
                });
    }

    <T> T random(List<T> pickFrom) {
        return pickFrom.get(rand.nextInt(pickFrom.size()));
    }
}