package com.lexrei.easymagicbeer.mixin;

import com.breakinblocks.beer.util.BookshelfOffsetUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import fuzs.easymagic.world.inventory.ModEnchantmentMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(ModEnchantmentMenu.class)
public class ModEnchantmentMenuMixin {

    @Shadow
    @Final
    private ContainerLevelAccess access;

    @Redirect(
        method = "getEnchantingPower",
        at = @At(
            value = "FIELD",
            target = "Lnet/minecraft/world/level/block/EnchantmentTableBlock;BOOKSHELF_OFFSETS:Ljava/util/List;"
        )
    )
    private List<BlockPos> easymagicbeer$useBeerOffsets() {
        return this.access
            .evaluate(BookshelfOffsetUtil::getOffsetsForTable)
            .orElse(EnchantmentTableBlock.BOOKSHELF_OFFSETS);
    }
}