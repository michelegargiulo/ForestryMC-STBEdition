package forestry.core.utils;

import forestry.core.ModuleCore;
import forestry.core.config.Config;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public enum EnumCompatItem {
    ASH(ModuleCore.getItems().ash, Config.dustAshItem, Config.dustAshItemMeta),
    ;

    private ItemStack finalItemStack;

    EnumCompatItem(Item defaultItem, String configItem, int configItemMeta) {
        ResourceLocation location = new ResourceLocation(configItem);
        if (ForgeRegistries.ITEMS.containsKey(location)) {
            this.finalItemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(location));
            this.finalItemStack.setItemDamage(configItemMeta);
        } else {
            this.finalItemStack = new ItemStack(defaultItem);
        }
    }

    public Item getFinalItem() {
        return finalItemStack.getItem();
    }

    public ItemStack getFinalItemStack() {
        return getFinalItemStack(1);
    }

    public ItemStack getFinalItemStack(int amount) {
        return new ItemStack(finalItemStack.getItem(), amount);
    }
}
