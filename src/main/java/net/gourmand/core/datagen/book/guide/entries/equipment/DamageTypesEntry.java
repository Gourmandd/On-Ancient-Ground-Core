package net.gourmand.core.datagen.book.guide.entries.equipment;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.items.TFCItems;
import net.dries007.tfc.util.Metal;
import net.minecraft.world.item.crafting.Ingredient;

public class DamageTypesEntry extends EntryProvider {

    public DamageTypesEntry(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {

        // page 1: explain damage types.
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(TFCItems.METAL_ITEMS.get(Metal.BLUE_STEEL).get(Metal.ItemType.SWORD)))
        );

        this.pageTitle(entryName());
        this.pageText("""
                Physical Damage Types describe the nature of the damage that can be dealt and resisted by players and mobs.
                \\
                \\
                There are three damage types:   \s
                - Piercing.
                - Crushing.
                - Slashing.
               \s""");

        // page 2: explain resistances.
        this.page("page2", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Explaining Resistances");
        this.pageText("""
                Armour's resistance against damage types co-relate to its tier, with higher tier having more resistance.
                \\
                \\
                Some mobs also are more or less vulnerable to some damage types.
               \s""");

        // page 3: list resistances.
        this.page("page3", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Piercing");
        this.pageText("""
                **Piercing** is dealt by sharp and pointy weapons such as knives, javelins, and arrows, as well as by spiders and cacti.
                \\
                \\
                Resistances:   \s
                - **Black Bronze** and **Red Steel** armour resists better against it.
                - Skeletons are immune to it while zombies are more vulnerable to it.
               \s""");

        // page 4: list resistances.
        this.page("page4", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Crushing");
        this.pageText("""
                 **Crushing** is dealt by blunt weapons such as hammers, and maces, as well as by zombies.
                 \\
                 \\
                 Resistances:   \s
                - **Bismuth Bronze** and **Blue Steel** armour resists better against it.
                - Creepers have partial resistance to it while skeletons are more vulnerable to it.
               \s""");

        // page 5: list resistances.
        this.page("page5", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );

        this.pageTitle("Slashing");
        this.pageText("""
                 **Slashing** is dealt by weapons with edges such as axes, and swords, as well as by **Predators**.
                 \\
                 \\
                 Resistances:   \s
                - Creepers are more vulnerable to it.
               \s""");
    }

    @Override
    protected String entryName() {
        return "Damage Types";
    }

    @Override
    protected String entryDescription() {
        return "About Damage Types";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(TFCItems.METAL_ITEMS.get(Metal.BLUE_STEEL).get(Metal.ItemType.SWORD));
    }

    @Override
    protected String entryId() {
        return "damage_types";
    }
}
