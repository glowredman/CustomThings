package tterrag.customthings.common.config.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import tterrag.customthings.CustomThings;
import tterrag.customthings.common.item.ItemCustomArmor;
import cpw.mods.fml.common.registry.GameRegistry;

public class ArmorType extends JsonType
{
    /* JSON Fields @formatter:off */
    public int[]    armors            = {0, 1, 2, 3};
    public int      durability        = 5;
    public int[]    reductionAmounts  = {1, 3, 2, 1};
    public int      enchantability    = 15;
    /* End JSON Fields @formatter:on */

    private transient Item[] items;

    public String getTextureName(int armorType)
    {
        int num = armorType == 2 ? 2 : 1;
        return name + num;
    }

    public String getMaterialName()
    {
        return name + "Material";
    }

    public ArmorMaterial getMaterial()
    {
        return ArmorMaterial.valueOf(getMaterialName());
    }

    private static final String[] names = { "Helm", "Chest", "Legs", "Boots" };

    public void register()
    {
        items = new Item[4];
        for (int i = 0; i <= 3; i++)
        {
            if (ArrayUtils.contains(armors, i))
            {
                items[i] = new ItemCustomArmor(this, i);
                GameRegistry.registerItem(items[i], name + names[i]);
            }
        }
    }

    public String getUnlocName(int slot)
    {
        return name + names[slot];
    }

    public String getIconName(int slot)
    {
        return CustomThings.MODID.toLowerCase() + ":" + getUnlocName(slot);
    }

    public Item[] getItems()
    {
        return items;
    }

    public static final List<ArmorType> types = new ArrayList<ArmorType>();

    public static void addType(ArmorType type)
    {
        EnumHelper.addArmorMaterial(type.getMaterialName(), type.durability, type.reductionAmounts, type.enchantability);
        types.add(type);
        type.register();
    }

    public static void addAll(Collection<? extends ArmorType> col)
    {
        for (ArmorType type : col)
        {
            addType(type);
        }
    }
}
