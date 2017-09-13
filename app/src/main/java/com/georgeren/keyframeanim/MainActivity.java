package com.georgeren.keyframeanim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.facebook.keyframes.KeyframesDrawable;
import com.facebook.keyframes.KeyframesDrawableBuilder;
import com.facebook.keyframes.deserializers.KFImageDeserializer;
import com.facebook.keyframes.model.KFImage;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private KeyframesDrawable kfDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnInit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAnim();
            }
        });
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kfDrawable.startAnimation();

            }
        });
        findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kfDrawable.stopAnimation();
            }
        });
    }

    private void initAnim() {
        KFImage kfImage = null;
        try {
            InputStream stream = getResources().getAssets().open("asset_name");
            kfImage = KFImageDeserializer.deserialize(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (kfImage != null) {
            kfDrawable = new KeyframesDrawableBuilder().withImage(kfImage).build();

            ImageView imageView = (ImageView) findViewById(R.id.some_image_view);
            imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            imageView.setImageDrawable(kfDrawable);
            imageView.setImageAlpha(0);
        }
    }
}
