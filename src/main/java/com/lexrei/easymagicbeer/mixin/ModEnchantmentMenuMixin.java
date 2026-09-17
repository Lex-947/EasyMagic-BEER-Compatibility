package com.lexrei.easymagicbeer.mixin;

import com.breakinblocks.beer.util.BookshelfOffsetUtil;
import fuzs.easymagic.util.ChiseledBookshelfHelper;
import fuzs.puzzleslib.api.core.v1.CommonAbstractions;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import fuzs.easymagic.world.inventory.ModEnchantmentMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModEnchantmentMenu.class)
public class ModEnchantmentMenuMixin {

    @Inject(
        method = "getEnchantingPower(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)I",
        at = @At("HEAD"),
        cancellable = true,
        remap = false
    )
    private void easymagicbeer$useBeerOffsets(
        Level level,
        BlockPos pos,
        CallbackInfoReturnable<Integer> cir
    ) {
        float enchantingPower = 0.0f;
        int chiseledBookshelfBooks = 0;

        for (BlockPos offset : BookshelfOffsetUtil.getOffsetsForTable(level, pos)) {
            if (!EnchantmentTableBlock.isValidBookShelf(level, pos, offset)) {
                continue;
            }

            enchantingPower += CommonAbstractions.INSTANCE.getEnchantPowerBonus(
                level.getBlockState(pos.offset(offset)),
                level,
                pos.offset(offset)
            );

            chiseledBookshelfBooks += ChiseledBookshelfHelper.findValidBooks(
                level,
                pos,
                offset
            );
        }

        cir.setReturnValue(
            (int) enchantingPower + chiseledBookshelfBooks / 3
        );
    }
}