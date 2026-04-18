package net.gourmand.core.datagen.book.guide.entries.ores;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryBackground;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookMultiblockPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.mojang.datafixers.util.Pair;
import net.dries007.tfc.common.blocks.TFCBlocks;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.util.registry.RegistryRock;
import net.gourmand.core.AncientGroundCore;
import net.gourmand.core.registry.CoreBlocks;
import net.gourmand.core.registry.category.CoreRocks;
import net.gourmand.core.util.TextUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Objects;

public class RockEntry extends EntryProvider {

    public final String ID;
    public final BookIconModel ICON;
    public final String text;
    public final Item item;

    public RockEntry(CategoryProviderBase parent, RegistryRock rock, String text) {
        super(parent);
        this.ID = rock.getSerializedName();
        this.ICON = BookIconModel.create(Objects.requireNonNull(getLooseItem(rock)));
        this.text = text;
        this.item = getLooseItem(rock);
    }

    @Override
    protected void generatePages() {

        this.page("page1", () -> BookSpotlightPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
                .withItem(Ingredient.of(item))
        );

        this.pageTitle(entryName());
        this.pageText(text);

        this.page("page2", () -> BookMultiblockPageModel.create()
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(AncientGroundCore.MODID, "rock_preview/" + ID))
                .withVisualizeButton(false)
        );

        this.pageTitle(entryName());
    }

    @Override
    protected String entryName() {
        return TextUtil.getName(ID);
    }

    @Override
    protected String entryDescription() {
        return "Where to find: " + TextUtil.getName(ID);
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return EntryBackground.DEFAULT;
    }

    @Override
    protected BookIconModel entryIcon() {
        return ICON;
    }

    @Override
    protected String entryId() {
        return ID;
    }

    private Item getLooseItem(RegistryRock rock){

        assert rock instanceof Rock || rock instanceof CoreRocks: "rock is not from Rock (TFC) or CoreRocks (On Ancient Ground)";

        if (rock instanceof Rock){
            return TFCBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.LOOSE).holder().get().asItem();
        }

        if (rock instanceof CoreRocks){
            return CoreBlocks.ROCK_BLOCKS.get(rock).get(Rock.BlockType.LOOSE).get().asItem();
        }

        return null;
    }
}
