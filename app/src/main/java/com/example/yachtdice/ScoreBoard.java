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

    public void calcScore(int selectedCell) {

    }

    public void calcBonus() {

    }

    public void calcTotal() {

    }

    public void finishRound(int totalScore) {

    }

    public void resetGame() {

    }
}
