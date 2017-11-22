package tour.com.co.mytour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import tourguide.tourguide.ChainTourGuide;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Sequence;
import tourguide.tourguide.ToolTip;

public class MainActivity extends AppCompatActivity {

    private Button btn1,btn2;
    private TextView txt1;
    private AlphaAnimation enterAnimation,exitAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        txt1 = findViewById(R.id.txt1);
        initAnimation();
        runAnimation();
    }

    private void initAnimation(){
        enterAnimation = new AlphaAnimation(0f,1f);
        enterAnimation.setDuration(600);
        enterAnimation.setFillAfter(true);

        exitAnimation = new AlphaAnimation(0f,1f);
        exitAnimation.setDuration(600);
        exitAnimation.setFillAfter(true);
    }

    private void runAnimation(){
        ChainTourGuide guideOne = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                            .setTitle(btn1.getText().toString())
                            .setDescription("este boton crea el usuario")
                            .setGravity(Gravity.BOTTOM)
                    ).playLater(btn1);

        ChainTourGuide guideTwo = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle(btn2.getText().toString())
                        .setDescription("este boton actualiza el usuario")
                        .setGravity(Gravity.BOTTOM)
                ).playLater(btn2);

        ChainTourGuide guideText = ChainTourGuide.init(this)
                .setToolTip(new ToolTip()
                        .setTitle("Campo de texto")
                        .setDescription("este Campo de texto es para la identificacion del usuario")
                        .setGravity(Gravity.BOTTOM)
                ).playLater(txt1);

        Sequence sequence = new Sequence.SequenceBuilder()
                .add(guideOne,guideTwo,guideText)
                .setDefaultPointer(null)
                .setDefaultOverlay(new Overlay()
                                    .setEnterAnimation(enterAnimation)
                                    .setExitAnimation(exitAnimation)
                                   )
                .setContinueMethod(Sequence.ContinueMethod.Overlay)
                .build();

        ChainTourGuide.init(this).playInSequence(sequence);


    }


}
