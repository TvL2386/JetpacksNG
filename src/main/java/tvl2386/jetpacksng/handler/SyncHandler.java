package tvl2386.jetpacksng.handler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.HashMap;
import java.util.Map;

public class SyncHandler {
    private static final Map<EntityPlayer, Boolean> flyKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> descendKeyState = new HashMap<EntityPlayer, Boolean>();

    private static final Map<EntityPlayer, Boolean> forwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> backwardKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> leftKeyState = new HashMap<EntityPlayer, Boolean>();
    private static final Map<EntityPlayer, Boolean> rightKeyState = new HashMap<EntityPlayer, Boolean>();

    public static boolean isFlyKeyDown(EntityLivingBase user) {
        return !(user instanceof EntityPlayer) || flyKeyState.containsKey(user) && flyKeyState.get(user);
    }

    public static boolean isDescendKeyDown(EntityLivingBase user) {
        return !(user instanceof EntityPlayer) || descendKeyState.containsKey(user) && descendKeyState.get(user);
    }

    public static boolean isForwardKeyDown(EntityLivingBase user) {
        return !(user instanceof EntityPlayer) || forwardKeyState.containsKey(user) && forwardKeyState.get(user);
    }

    public static boolean isBackwardKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && backwardKeyState.containsKey(user) && backwardKeyState.get(user);
    }

    public static boolean isLeftKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && leftKeyState.containsKey(user) && leftKeyState.get(user);
    }

    public static boolean isRightKeyDown(EntityLivingBase user) {
        return user instanceof EntityPlayer && rightKeyState.containsKey(user) && rightKeyState.get(user);
    }

    public static void processKeyUpdate(EntityPlayer player, boolean keyFly, boolean keyDescend, boolean keyForward, boolean keyBackward, boolean keyLeft, boolean keyRight) {
        flyKeyState.put(player, keyFly);
        descendKeyState.put(player, keyDescend);

        forwardKeyState.put(player, keyForward);
        backwardKeyState.put(player, keyBackward);
        leftKeyState.put(player, keyLeft);
        rightKeyState.put(player, keyRight);
    }

    private static void removeFromAll(EntityPlayer player) {
        flyKeyState.remove(player);
        forwardKeyState.remove(player);
        backwardKeyState.remove(player);
        leftKeyState.remove(player);
        rightKeyState.remove(player);
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent evt) {
        removeFromAll(evt.player);
    }

    @SubscribeEvent
    public void onDimChanged(PlayerEvent.PlayerChangedDimensionEvent evt) {
        removeFromAll(evt.player);
    }
}


