package me.imtoggle.dreamdefender.mixin;

import cc.polyfrost.oneconfig.utils.hypixel.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(ItemStack.class)
public class ItemStackMixin {

    @Unique
    private static List<String> tooltip =
            Arrays.asList(
                    "§bhello",
                    "§athis is a custom tooltip"
            );

    @Inject(method = "getTooltip", at = @At("RETURN"), cancellable = true)
    private void replace(EntityPlayer playerIn, boolean advanced, CallbackInfoReturnable<List<String>> cir) {
        List<String> list = cir.getReturnValue();
        ItemStack stack = (ItemStack) ((Object) this);
        if (LocrawUtil.INSTANCE.isInGame() && LocrawUtil.INSTANCE.getLocrawInfo().getGameType() == LocrawInfo.GameType.BEDWARS && stack.getItem() instanceof ItemMonsterPlacer && stack.getMetadata() == 99 && !list.get(1).contains("120")) {
            int i = 0;
            for (String text : tooltip) {
                i++;
                list.add(i, text);
            }
            cir.setReturnValue(list);
        }
    }

}
