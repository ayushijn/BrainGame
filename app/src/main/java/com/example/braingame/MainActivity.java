package com.example.braingame;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView question,timer,goButton;
    Button button1,button2,button3,button4;
    CountDownTimer countDownTimer;
    TextView score,result;
    Random rand;
    Button playagain;
    static String correctOption="";
    static int counter,correct;
    MediaPlayer mediaPlayer,ring;

public void reset()
{generateQuestion();
 score.setText("0/0");
 startTimer();
 correct=0;
 counter=0;
mediaPlayer.start();
}

public void start(View view)
{
    goButton.setVisibility(View.INVISIBLE);
    timer.setVisibility(View.VISIBLE);
    question.setVisibility(View.VISIBLE);
    score.setVisibility(View.VISIBLE);
    button1.setVisibility(View.VISIBLE);
    button2.setVisibility(View.VISIBLE);
    button3.setVisibility(View.VISIBLE);
    button4.setVisibility(View.VISIBLE);
    result.setVisibility(View.VISIBLE);
    reset();
}

public void generateQuestion()
{
++counter;
    int a = rand.nextInt(20) + 1;
    int b = rand.nextInt(20)+1;
int sum=a+b;
question.setText(Integer.toString(a)+"+"+Integer.toString(b));
generateOptions(sum);
score.setText(Integer.toString(correct)+"/"+Integer.toString(counter));


}
public void generateOptions(int n) {
    int tagNumber = rand.nextInt(3) + 1;
    switch (tagNumber) {
        case 1:
            button1.setText(Integer.toString(n));
            button2.setText(Integer.toString(rand.nextInt(40) + 1));
            button3.setText(Integer.toString(rand.nextInt(40) + 1));
            button4.setText(Integer.toString(rand.nextInt(40) + 1));
            correctOption = "one";
            break;
        case 2:
            button2.setText(Integer.toString(n));
            button1.setText(Integer.toString(rand.nextInt(40) + 1));
            button3.setText(Integer.toString(rand.nextInt(40) + 1));
            button4.setText(Integer.toString(rand.nextInt(40) + 1));
            correctOption = "two";
            break;

        case 3:
            button3.setText(Integer.toString(n));
            button1.setText(Integer.toString(rand.nextInt(40) + 1));
            button2.setText(Integer.toString(rand.nextInt(40) + 1));
            button4.setText(Integer.toString(rand.nextInt(40) + 1));
            correctOption = "three";

            break;

        case 4:
            button4.setText(Integer.toString(n));
            button1.setText(Integer.toString(rand.nextInt(40) + 1));
            button2.setText(Integer.toString(rand.nextInt(40) + 1));
            button3.setText(Integer.toString(rand.nextInt(40) + 1));
            correctOption = "four";
            break;

    }
}
public void startTimer()
    {
        countDownTimer=new CountDownTimer(30000+100,1000) {
            @Override
            public void onTick(long l) {
                playagain.setVisibility(View.INVISIBLE);
                if((l/1000)<=9)
                timer.setText("0:0"+Integer.toString((int)l/1000));
                else
                timer.setText("0:"+Integer.toString((int)l/1000));
            }
            @Override
            public void onFinish() {
                mediaPlayer.start();
                playagain.setVisibility(View.VISIBLE);
                result.setText("Finished ! ");
                ring.start();
            }
        }.start();
    }

public void optionClicked(View view)
{ Button buttonPressed = (Button) view;
mediaPlayer.start();
String s=buttonPressed.getTag().toString();
if(s.equalsIgnoreCase(correctOption))
{
    result.setText("Correct! Well Done :)");
    ++correct;
}
else
{
    result.setText("Wrong :(");
}
generateQuestion();
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question=(TextView)findViewById(R.id.question);
        playagain=(Button)findViewById(R.id.playagain);
        result=(TextView)findViewById(R.id.result);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        score=(TextView)findViewById(R.id.score);
        timer=(TextView)findViewById(R.id.timer);
        goButton=(TextView)findViewById(R.id.goButton);
        rand = new Random();
        mediaPlayer=MediaPlayer.create(this,R.raw.woosh);
        ring=MediaPlayer.create(this,R.raw.bell);
    }
}