package tvl2386.jetpacksng.input;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import tvl2386.jetpacksng.handler.SyncHandler;
import tvl2386.jetpacksng.network.PacketHandler;
import tvl2386.jetpacksng.network.PacketSendHoverKeyPress;
import tvl2386.jetpacksng.network.PacketSendMovementKeys;

public class InputHandler {
    static final Minecraft MINECRAFT = Minecraft.getMinecraft();

    private static KeyBinding keyBindJump = MINECRAFT.gameSettings.keyBindJump;
    private static KeyBinding keyBindSneak = MINECRAFT.gameSettings.keyBindSneak;
    private static KeyBinding keyBindForward = MINECRAFT.gameSettings.keyBindForward;
    private static KeyBinding keyBindBack = MINECRAFT.gameSettings.keyBindBack;
    private static KeyBinding keyBindLeft = MINECRAFT.gameSettings.keyBindLeft;
    private static KeyBinding keyBindRight = MINECRAFT.gameSettings.keyBindRight;

    private static boolean lastFlyState = false;
    private static boolean lastDescendState = false;
    private static boolean lastForwardState = false;
    private static boolean lastBackwardState = false;
    private static boolean lastLeftState = false;
    private static boolean lastRightState = false;

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.toggleHoverModeKey.isPressed()) {
            // Someone pressed our toggleHoverModeKey. We send a message
            PacketHandler.INSTANCE.sendToServer(new PacketSendHoverKeyPress());
        } else {
            handleJetpackKeys(event);
        }
    }

    @SubscribeEvent
    public void handleJetpackKeys(InputEvent.KeyInputEvent event) {
        if (MINECRAFT.thePlayer != null) {
            boolean flyState = keyBindJump.isKeyDown();
            boolean descendState = keyBindSneak.isKeyDown();

            boolean forwardState = keyBindForward.isKeyDown();
            boolean backwardState = keyBindBack.isKeyDown();
            boolean leftState = keyBindLeft.isKeyDown();
            boolean rightState = keyBindRight.isKeyDown();

            if (flyState != lastFlyState || descendState != lastDescendState || forwardState != lastForwardState || backwardState != lastBackwardState || leftState != lastLeftState || rightState != lastRightState) {
//                System.out.println("flyState: "+flyState+" lastFlyState: "+lastFlyState);
//                System.out.println("descendState: "+descendState+" lastDescendState: "+lastDescendState);
//                System.out.println("forwardState: "+forwardState+" lastForwardState: "+lastForwardState);
//                System.out.println("backwardState: "+backwardState+" lastBackwardState: "+lastBackwardState);
//                System.out.println("leftState: "+leftState+" lastLeftState: "+lastLeftState);
//                System.out.println("rightState: "+rightState+" lastRightState: "+lastRightState);

                lastFlyState = flyState;
                lastDescendState = descendState;

                lastForwardState = forwardState;
                lastBackwardState = backwardState;
                lastLeftState = leftState;
                lastRightState = rightState;

//                System.out.println("Sending PacketSendMovementKeys packet!");
                PacketHandler.INSTANCE.sendToServer(new PacketSendMovementKeys(flyState, descendState, forwardState, backwardState, leftState, rightState));
                SyncHandler.processKeyUpdate(MINECRAFT.thePlayer, flyState, descendState, forwardState, backwardState, leftState, rightState);
            }
        }
    }
}
