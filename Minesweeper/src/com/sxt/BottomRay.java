package com.sxt;

// 初始化地雷
public class BottomRay {

    // 存放坐标
    int[] rays = new int[GameUtil.RAY_MAX*2];
    // 地雷坐标
    int x,y;
    //是否可放置 T为可，F为不可
    boolean isPlaceable = true;

    // 生成地雷
    void newRay() {
        for (int i = 0; i < GameUtil.RAY_MAX*2; i+=2) {
            x = (int)(Math.random()*GameUtil.MAP_W+1); // 1-12
            y = (int)(Math.random()*GameUtil.MAP_H+1); // 1-12

            // 判断坐标是否重复
            for(int j = 0; j < i; j+=2){
                if(x==rays[j] && y==rays[j+1]){
                    i-=2;
                    isPlaceable = false;
                    break;
                }

            }
            // 将坐标放入数组
            if(isPlaceable){
                rays[i] = x;
                rays[i+1] = y;

            }
            isPlaceable = true;
        }

        for (int i = 0; i < GameUtil.RAY_MAX*2; i+=2) {
            GameUtil.DATA_BOTTOM[rays[i]][rays[i+1]]=-1;
        }

    }
}
