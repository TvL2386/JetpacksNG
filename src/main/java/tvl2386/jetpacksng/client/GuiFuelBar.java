package tvl2386.jetpacksng.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import tvl2386.jetpacksng.JetpacksNG;
import tvl2386.jetpacksng.item.ItemJetpack;

// Heavily based on: https://github.com/coolAlias/Forge_Tutorials/blob/master/IExtendedEntityPropertiesTutorial.java

@SideOnly(Side.CLIENT)
public class GuiFuelBar extends Gui
{
    private Minecraft mc;

    private static final ResourceLocation texturepath = JetpacksNG.location("textures/gui/fuel_bar.png");

    public GuiFuelBar(Minecraft mc)
    {
        super();
        // We need this to invoke the render engine.
        this.mc = mc;
    }

    //
    // This event is called by GuiIngameForge during each frame by
    // GuiIngameForge.pre() and GuiIngameForce.post().
    //
    // 1.6.4: @ForgeSubscribe(priority = EventPriority.NORMAL)
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderExperienceBar(RenderGameOverlayEvent event)
    {
        // We draw after the ExperienceBar has drawn.  The event raised by GuiIngameForge.pre()
        // will return true from isCancelable.  If you call event.setCanceled(true) in
        // that case, the portion of rendering which this event represents will be canceled.
        // We want to draw *after* the experience bar is drawn, so we make sure isCancelable() returns
        // false and that the eventType represents the ExperienceBar event.
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE)
        {
            return;
        }

        // get the player
        EntityPlayer player = this.mc.thePlayer;
        ItemStack chest = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);

        // check if the player is wearing our jetpack
        if(chest != null && chest.getItem() instanceof ItemJetpack) {

            // Starting position for the fuel bar - 2 pixels from the top left corner.
            int xPos = 2;
            int yPos = 2;

            // The center of the screen can be gotten like this during this event:
            // int xPos = event.resolution.getScaledWidth() / 2;
            // int yPos = event.resolution.getScaledHeight() / 2;

            // Be sure to offset based on your texture size or your texture will not be truly centered:
            // int xPos = (event.resolution.getScaledWidth() + textureWidth) / 2;
            // int yPos = (event.resolution.getScaledHeight() + textureHeight) / 2;

            // setting all color values to 1.0F will render the texture as it appears in your texture file
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            // Somewhere in Minecraft vanilla code it says to do this because of a lighting bug
            GL11.glDisable(GL11.GL_LIGHTING);

            // Bind your texture to the render engine
            this.mc.getTextureManager().bindTexture(texturepath);

            /*
            The parameters for drawTexturedModalRect are as follows:

            drawTexturedModalRect(int x, int y, int u, int v, int width, int height);

            x and y are the on-screen position at which to render.
            u and v are the coordinates of the most upper-left pixel in your texture file from which to start drawing.
            width and height are how many pixels to render from the start point (u, v)
             */
            // First draw the background layer. In my texture file, it starts at the upper-
            // left corner (x=0, y=0), is 50 pixels long and 4 pixels thick (y value)
            this.drawTexturedModalRect(xPos, yPos, 0, 0, 50, 4);
            // Then draw the foreground; it's located just below the background in my
            // texture file, so it starts at x=0, y=4, is only 2 pixels thick and 50 length
            // Why y=4 and not y=5? Y starts at 0, so 0,1,2,3 = 4 pixels for the background

            ItemJetpack jetpack = (ItemJetpack) chest.getItem();
            int fuelBarWidth = (int)(((float) jetpack.getEnergyStored(chest) / jetpack.getMaxEnergyStored(chest) * 50));

            // Now we can draw our fuel bar at yPos+1 so it centers in the background:
            this.drawTexturedModalRect(xPos, yPos + 1, 0, 4, fuelBarWidth, 2);
        }
    }
}