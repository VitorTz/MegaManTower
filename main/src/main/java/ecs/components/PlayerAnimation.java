package ecs.components;

import util.Alignment;
import util.AnimationKey;
import util.Constants;
import util.MovementEnum;

public class PlayerAnimation extends Animation {

    public PlayerAnimation() {
        super("main/src/main/resources/spriteSheets/player", Constants.PLAYER_SIZE, Alignment.BOTTOM);
        this.getAnimations().get("left").getSpriteSheet().mirrorSpriteSheet();
        this.getAnimations().get("idle_left").getSpriteSheet().mirrorSpriteSheet();
        this.getAnimations().get("idle_left").setAnimationUpdateRate(3f);
        this.getAnimations().get("idle_right").setAnimationUpdateRate(3f);
        this.setKey(new AnimationKey("idle", "right", ""));
    }

    private void handleAnimation() {
        PlayerMovement movement = this.gameObj.getComponent(PlayerMovement.class);
        MovementEnum movementHorizontal = movement.movementHorizontal;
        MovementEnum movementVertical = movement.movementVertical;

        String preffix = !movement.isMoving() ? "idle" : "";
        String suffix =  movementHorizontal == null ? MovementEnum.RIGHT.toString().toLowerCase() : movementHorizontal.toString().toLowerCase();
        String alt = movementVertical == MovementEnum.UP ? "jump" : "";
        AnimationKey key = new AnimationKey(preffix, suffix, alt);
        this.setKey(key);

    }

    @Override
    public void update(double deltaTime) {
        this.handleAnimation();
        super.update(deltaTime);
    }

}
