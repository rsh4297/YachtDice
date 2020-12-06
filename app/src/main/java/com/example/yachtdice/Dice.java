package com.example.yachtdice;

import android.view.animation.Animation;
import android.widget.ImageView;

import java.security.AccessControlContext;
import java.util.Random;

public class Dice extends MainActivity {
    //주사위 각각의 정보
    class DiceInfo {
        ImageView img;
        Animation anim;
        int value = 1; //주사위 눈 값
        Boolean keep = false; //킵하는지 저장

        public DiceInfo(int id) {
            value = 1;
            keep = false;
            img = (ImageView) findViewById(id);
        }
    }

    private final int diceNumber = 5;
    private int rollCount;
    private DiceInfo dice[];
    //주사위 눈 바뀌는 애니메이션
    private final int[] ani = new int[]
            {R.drawable.anim1, R.drawable.anim2, R.drawable.anim3, R.drawable.anim4, R.drawable.anim5, R.drawable.anim6};
    private Random rand;

    // 주사위 클래스 생성자
    public Dice(AccessControlContext context) {
        //주사위 5개 객체 생성
        dice = new DiceInfo[diceNumber];
        rand = new Random();
        for (int i = 0; i < diceNumber; i++) {
//            dice[i] = new DiceInfo(getResources().getIdentifier("dice1", "id", context.toString()));
        }

        rollCount = 0;
    }

   
    public void onClickDice(View view){
        view.setAnimation(SelectedAnimation); // 선택 시 애니메이션 효과 부여 
        view.setClickable(false);           
        keepDice(view.getId());
    }
    //주사위 굴리기
    public void rollDice() {
        if(rollCount==3){           // 새로운 라운드 시작 시 초기화
            for (int i=0;i<6;i++){
                dice[i].keep = false;
                dice[i].value = 1;
                dice[i].img.setClickable(true);
                dice[i].img.setImageResource(getResources().getIdentifier("dice1","drawable","com.example.yacht"));
                rollCount=0;
            }
        }
        Button btn = findViewById(R.id.btnRoll);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score[] = new int[diceNumber];
                for (int i = 0; i < diceNumber; i++) {
                    if(dice[i].keep== true) continue;
                    else {
                        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), getResources().getIdentifier("anim" + i, "drawable", getApplicationContext().getPackageName()));
                        anim.setRepeatCount(anim.INFINITE);   // getApplication(), ani[i]
                        dice[i].img.startAnimation(anim);
                        // setRepeatCount(Animation.INFINITE)  xml  android:repeatCount="infinite" 추가  //or setduration(5000)
                    }
                }
                for (int i = 0; i < diceNumber; i++) {
                    if(dice[i].keep== true) continue;
                    else {
                        score[i] = rand.nextInt(6) + 1;
                        int resID = getResources().getIdentifier("dice" + score[i], "drawbale", "com.example.yachtdice");
                        dice[i].img.getAnimation().cancel();
                        dice[i].img.setImageResource(resID);
                        dice[i].value = score[i];
                    }
                }
            }
        });
        rollCount++;  //3 번 굴리면 초기화 및 점수판에 점수입력   야추나오면 5개 고정시키고 남은 횟수만큼 주사위 굴리기
    }

    //주사위 킵
    public void keepDice(int index) {
        //if( )
        ImageView image = findViewById(index);
        for (int i=0;i<diceNumber;i++) {
            if (dice[i].img == image) {
                dice[i].keep = true;
            }
        }
    }

    //주사위 값 반환
    public int[] returnDiceValues() {
        int[] values = new int[diceNumber];
        for (int i = 0; i < diceNumber; i++) {
            values[i] = dice[i].value;
        }
        return values;
    }
}
