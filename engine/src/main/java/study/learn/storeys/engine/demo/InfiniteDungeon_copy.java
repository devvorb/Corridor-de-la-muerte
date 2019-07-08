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
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import study.learn.storeys.engine.Interactlet;
import study.learn.storeys.engine.Prompter;


// copie pour demander aux papas de faire efficacement les listes/ balancing (noter chances à la place de copier-coller)
public class InfiniteDungeon_copy extends Interactlet {

    Random rand = new Random();

    @Override
    public void interact(Prompter<Void> prompter) throws IOException {
        do {
    List<String> tiers = ImmutableList.of("Wood ", "Stone ", "Iron ", "Titanium ");

    String tier = random(tiers);

    List<String> environments = ImmutableList.of("is Burning hot (H+)", "is Cold (C)", "is Dark (D)", "is Freezing cold (C+)", "is Frozen (F)", "has a Frozen lake (Lf)", "is Hot (H)", "has a Lake (L)", "has Parkour (P)", "is Poisoned (p)", "has Titanium ore (To)", "has a Quarter Lucky Gem (1/4)", "is Webbed (W)", "is filled with Water (~)", "has a tree (T)");
    List<String> mobs = ImmutableList.of("Andidozuba (!p)", "Axter (Ax)", "Byder (sB)", "Clubster (C)", "Cow (c)", "Daggster (Dg)", "Darkow (Dw)", "Dhrek (D)", "Flion (Fl)", "Hammster (H)", "Icetry (IT)", "Lightfly (l)", "Lochdeep (L)", "a Quarter Lucky Gem (1/4)", "a Wobl (W)", "a Priz (P)", "a Poider (sP)", "a Monstean (m)", "a Monspear (mS)", "a Maunlet (M)" );
    List<String> items = ImmutableList.of("an Agility potion (pA)", "an Antidote (!p)", "a " + tier + "Armor (A)" , "an Arrow (a)", "a " + tier + "Axe (ax)", "a Bomb (b)", "a Bow (B)", "a " + tier + "Club (C)", "a " + tier + "Dagger (Dg)", "a Fire potion (pF)", "a " + tier + "Gauntlet (M)", "a Great bow (gB)", "a " + tier + "Hammer (H)", "a Health potion (pH)", "an Ice arrow (aI)", "an Ice potion (pI)", "a Quarter Lucky Gem (1/4)", "a " + tier + "War axe (Ax)", "a Torch (T)", "a swim potion (pS)", "a Swim orb (S)", "a " + tier + "Spear (mS)", "a Luck potion (pL)", "a Leather Knife (Lk)", "a Key (K)", "a Discreet potion (pD)");
    List<String> others = ImmutableList.of("a Ballista (B)", "a Blacksmith ($B)", "a Brewing stand (pB)", "a Campfire (Cf)", "a Cannon (C)", "a Carpenter table (cW)", "a Current (c←)", "a Current (c↑)", "a Current (c↓)", "a Current (c→)", "a Damage Enchanter (#D)", "an Electricity Enchanter (#E)", "an Electricity generator (Eg)", "a Fire Enchanter (#F)", "a Fire generator (Fg)", "a Fountain (f)", "Frozen (F)", "a Heal Enchanter (#H)", "an Ice Enchanter (#I)", "a Leather table (cL)", "a Locked chest (!K)", "a Locked room (rK)", "a Master Enchanter (#M)", "a Master merchant ($M)", "a Merchant of armor ($A)", "a Merchant of arrows ($a)", "a Merchant of weapon 1 ($1)", "a Merchant of weapon 2 ($2)", "a Merchant of weapon 3 ($3)", "a Merchant of weapon 4 ($4)", "a Merchant of weapon 5 ($5)", "a Merchant of weapon 6 ($6)", "Merchant of weapon 7 ($7)", "a Quarter Lucky Gem (1/4)", "Wood (W)", "a water gun (g←)", "a water gun (g↑)", "a water gun (g↓)", "a water gun (g→)" , "a Water Enchanter (#W)", "Swinging axes (T+)", "Stones (S)", "a Speed enchanter (#S)", "Saw blades (T)", "a Trap plant (tP)");
    List<String> directions = ImmutableList.of("UP", "DOWN", "LEFT", "RIGHT");




            String environment = random(environments);

            String mob = random(mobs);

            String item = random(items);

            String other = random(others);

            String direction1 = random(directions);
            String direction2 = random(directions);
            String direction3 = random(directions);
            Set<String> availableDirections = ImmutableSet.of(direction1, direction2, direction3);

            prompter.await(aChoice(
                    "This room " + environment + " and there are a " + mob + ", " + item + " and " + other + ". The paths go "
                            + availableDirections,
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