package com.haomiao.cloud.rx_zhihu.view.widget.spinkit.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;

import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.animation.SpriteAnimatorBuilder;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.RectSprite;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.Sprite;
import com.haomiao.cloud.rx_zhihu.view.widget.spinkit.sprite.SpriteGroup;


/**
 * Created by ybq.
 */
public class WanderingCubes extends SpriteGroup {

    @Override
    public Sprite[] onCreateChild() {
        return new Sprite[]{
                new Cube(),
                new Cube()
        };
    }

    @Override
    public void onChildCreated(Sprite... sprites) {
        super.onChildCreated(sprites);
        sprites[1].setAnimationDelay(-900);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        bounds = clipSquare(bounds);
        super.onBoundsChange(bounds);
        for (int i = 0; i < getChildCount(); i++) {
            Sprite sprite = getChildAt(i);
            sprite.setDrawBounds(
                    bounds.left,
                    bounds.top,
                    bounds.left + bounds.width() / 4,
                    bounds.top + bounds.height() / 4
            );
        }
    }

    class Cube extends RectSprite {
        @Override
        public ValueAnimator getAnimation() {
            float fractions[] = new float[]{0f, 0.25f, 0.5f, 0.51f, 0.75f, 1f};
            return new SpriteAnimatorBuilder(this).
                    rotate(fractions, 0, -90, -179, -180, -270, -360).
                    translateXPercentage(fractions, 0f, 0.75f, 0.75f, 0.75f, 0f, 0f).
                    translateYPercentage(fractions, 0f, 0f, 0.75f, 0.75f, 0.75f, 0f).
                    scale(fractions, 1f, 0.5f, 1f, 1f, 0.5f, 1f).
                    duration(1800).
                    easeInOut(fractions)
                    .build();
        }
    }
}
