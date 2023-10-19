package forestry.core.utils;

import forestry.core.ModuleCore;
import forestry.core.config.Config;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;

public enum EnumCompatItem {
    ASH(ModuleCore.getItems().ash, Config.dustAshItem, Config.dustAshItemMeta),
    ;

    private final Item defaultItem;
    private final String regName;
    private final int meta;

    @Nullable
    private Item customItem;

    EnumCompatItem(Item defaultItem, String configItem, int configItemMeta) {
        this.defaultItem = defaultItem;
        this.regName = configItem;
        this.meta = configItemMeta;
        customItem = null;
    }

    public Item getFinalItem() {
        ResourceLocation location = new ResourceLocation(regName);
        if (customItem != null) {
            return customItem;
        } else if (ForgeRegistries.ITEMS.containsKey(location)) {
            customItem = ForgeRegistries.ITEMS.getValue(location);
            return customItem;
        }
        return defaultItem;
    }

    public ItemStack getFinalItemStack() {
        return getFinalItemStack(1);
    }

    public ItemStack getFinalItemStack(int amount) {
        return new ItemStack(getFinalItem(), amount, meta);
    }
}
