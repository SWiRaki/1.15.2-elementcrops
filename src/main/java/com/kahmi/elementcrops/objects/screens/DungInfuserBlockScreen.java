package com.kahmi.elementcrops.objects.screens;

import com.kahmi.elementcrops.ElementCrops;
import com.kahmi.elementcrops.objects.container.DungInfuserBlockContainer;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(ElementCrops.MOD_ID)
public class DungInfuserBlockScreen extends ContainerScreen<DungInfuserBlockContainer> {
	private ResourceLocation GUI = new ResourceLocation(ElementCrops.MOD_ID, "textures/gui/dunginfuser.png");
	
	public DungInfuserBlockScreen(DungInfuserBlockContainer pScreenContainer, PlayerInventory pInventory, ITextComponent pTitleIn) {
		super(pScreenContainer, pInventory, pTitleIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float pPartialTicks, int pMouseX, int pMouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(GUI);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.blit(x, y, 0, 0, this.xSize, this.ySize);
		renderBackground();
	}
	
	@Override
	public void render(int pMouseX, int pMouseY, float pPartialTicks) {
		this.renderBackground();
		super.render(pMouseX, pMouseY, pPartialTicks);
		this.renderHoveredToolTip(pMouseX, pMouseY);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int pMouseX, int pMouseY) {
		this.font.drawString(this.title.getFormattedText(), 8.0F, 6.0F, 4210752);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
	}
}
