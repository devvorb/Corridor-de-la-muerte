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

public class InfiniteDungeon extends Interactlet {

    Random rand = new Random();
    List<String> environments = ImmutableList.of("is dark (D)", "is cold (C)", "is hot (H)");
    List<String> mobs = ImmutableList.of("and there are a monstean (m)", "and there are a Dhrek (d)", "and there are a Byder (b)");
    List<String> items = ImmutableList.of("20 gold (20g)", "a bomb (B)", "a key (K)");
    List<String> others = ImmutableList.of("a campfire (c)", "an arrow merchant ($A)", "a monstean (m)");
    List<String> directions = ImmutableList.of(" UP", " DOWN", " LEFT", " RIGHT");

    @Override
    public void interact(Prompter<Void> prompter) throws IOException {
        do {

            String environment = random(environments);

            String mob = random(mobs);

            String item = random(items);

            String other = random(others);

            String direction1 = random(directions);
            String direction2 = random(directions);
            String direction3 = random(directions);

            prompter.await(aChoice(
                    "This room " + environment + " " + mob + ", " + item + " and " + other + ". The paths go"
                            + direction1 + direction2 + direction3,
                    "next room", "Generate a new room", "leave", "Stop playing")).await(choice -> {
                        switch (choice) {
                        case "next room": {

                        }
                            break;
                        case "leave": {
                            return bye();
                        }
                        }
                        return bye();
                    });
        } while (true);

    }

    <T> T random(List<T> pickFrom) {
        return pickFrom.get(rand.nextInt(pickFrom.size()));
    }
}