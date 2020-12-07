package com.example.yachtdice;

public class ScoreBoard extends MainActivity {
    private int[] countlist = new int[12];
    private int[] samelist = new int[6];

    public int[] result(int values[]) {

        int small = 0;
        int large = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (i + 1 == values[j]) {
                    samelist[i]++;
                }
            }
        }
        for (int i = 0; i < 6; i++) {              // 1~6 까지 점수
            countlist[i] = (i + 1) * samelist[i];
        }
        for (int i = 0; i < 6; i++) {
            countlist[6] += (i + 1) * samelist[i];
        }
        for (int i = 0; i < 6; i++) {      // 4 of a kind
            if (samelist[i] >= 4) {
                countlist[7] = 30;
            }
        }
        for (int i = 0; i < 6; i++) {      //full house
            if (samelist[i] == 3) {
                for (int j = 0; j < 6; j++) {
                    if (samelist[j] == 2) {
                        countlist[8] = 30;
                    }
                }
            }
        }
        for (int i=0;i<3;i++){
            for (int j=0;j<4;j++){
                if (samelist[i+j]==0){
                    small += 1;
                    break;
                }
            }
        }
        for (int i=0;i<2;i++){
            for (int j=0;j<5;j++){
                if(samelist[i+j]==0){
                    large += 1;
                    break;
                }
            }
        }
        if(small<3){                //Small Straight
            countlist[9] = 15;
        }
        if(large<2){                //Large Straight
            countlist[10] = 30;
        }
        for (int i=0;i<6;i++){      //Yacht
            if(samelist[i]==5){
                countlist[11] = 50;
            }
        }
        return countlist;
    }

    public void onClickScore(View view){
        view.isClickable();
        calcScore(view.getId());
    }
    public void calcScore(int selectedCell) { //
        int[] score  = result(values);
        TextView textView = findViewById(selectedCell);
        for (int i=0;i<12;i++){
            if(textView.getId()==getResources().getIdentifier("score"+(i+1),"id","com.example.yacht")){
                textView.setText(countlist[i]);
            }
        }// switch(v.getID()){
        // case R.id.name1;
        // case R.id.name2; ...
        //  Log.i("ID값",v.getResources().getResourceEntryName(v.getID()));
        //   결과  : name1,name2,...

    }

    public void calcBonus() {  //1~6 까지 점수 합산후 63넘으면 총점에 30점 추가
        int semitotal = 0;
        TextView textView[] = new TextView[6];
        for (int i=0;i<6;i++){
            int search = getResources().getIdentifier("score"+(i+1),"id","com.example.yacht");
            textView[i].findViewById(search);
            semitotal += Integer.parseInt(textView[i].getText().toString());
        }
        if(semitotal>=63){
            Total+=semitotal;   
        }
    }

    public void calcTotal() {                   //1~12 점수 다 더한다음 보너스점수 여부에 따라 추가
        TextView textView[] = new TextView[12]; // 총점을 어디에 기입하는지 모름
        for (int i=0;i<12;i++) {
            int search = getResources().getIdentifier("score" + (i + 1), "id", "com.example.yacht");
            textView[i].findViewById(search);
            Total += Integer.parseInt((textView[i].getText().toString()));
        }
        calcBonus();
    }
    public void finishRound(int totalScore) {

    }

    public void resetGame() {

    }
}
