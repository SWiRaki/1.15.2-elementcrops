package com.kahmi.elementcrops;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kahmi.elementcrops.objects.blocks.Blocks;
import com.kahmi.elementcrops.objects.items.ModItems;
import com.kahmi.elementcrops.util.ClientProxy;
import com.kahmi.elementcrops.util.IProxy;
import com.kahmi.elementcrops.util.ServerProxy;

@Mod("elementcrops")
public class ElementCrops
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final RenderType CUTOUTRENDERTYPE = RenderType.getCutout();
    
    public static final String MOD_ID = "elementcrops";
    public static ElementCrops INSTANCE;
    public static IProxy PROXY = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
    
    private IEventBus mModEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public ElementCrops() {
    	mModEventBus.addListener(this::setup);
    	mModEventBus.addListener(this::doClientStuff);
        INSTANCE = this;
        
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent pEvent) {
        LOGGER.info("Initializing Kahmis ElementCrops..");
        PROXY.init(LOGGER);
    }

    private void doClientStuff(final FMLClientSetupEvent pEvent) {
    	RenderTypeLookup.setRenderLayer(Blocks.CROP_IRONFRUIT, CUTOUTRENDERTYPE);
    	RenderTypeLookup.setRenderLayer(Blocks.CROP_GOLDFRUIT, CUTOUTRENDERTYPE);
    	RenderTypeLookup.setRenderLayer(Blocks.CROP_DIAMONDFRUIT, CUTOUTRENDERTYPE);
    	RenderTypeLookup.setRenderLayer(Blocks.CROP_COALFRUIT, CUTOUTRENDERTYPE);
    	RenderTypeLookup.setRenderLayer(Blocks.CROP_ENDERFRUIT, CUTOUTRENDERTYPE);
    	RenderTypeLookup.setRenderLayer(Blocks.CROP_BLAZEFRUIT, CUTOUTRENDERTYPE);
    }
    
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent pEvent) {
        
    }
    
    public static class ELEMENTCROPS extends ItemGroup {
    	public static final ELEMENTCROPS INSTANCE = new ELEMENTCROPS(ItemGroup.GROUPS.length, "ElementCrops");
    	private ELEMENTCROPS(int pIndex, String pLabel) {
    		super(pIndex, pLabel);
    	}
    	
    	@Override
    	@OnlyIn(Dist.CLIENT)
    	public ItemStack createIcon() {
    		return new ItemStack(ModItems.ENDERFRUIT);
    	}
    }
}
