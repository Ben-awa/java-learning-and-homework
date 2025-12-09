package com.sxt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {

    int width = 2 * GameUtil.OFFSET +GameUtil.MAP_W * GameUtil.SQUARE_LENGTH;
    int height = 4 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH;

    Image offScreenImage = null;


    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();
    GameSelect gameSelect = new GameSelect();

    // 是否开始
    boolean begin=false;

    // launch 方法，用于启动游戏窗口
    void launch() {
        GameUtil.START_TIME = System.currentTimeMillis();
        // 设置窗口是否可见
        this.setVisible(true);
        // 设置窗口大小
        if(GameUtil.state==3){
            this.setSize(500,500);
        }
        else{
            this.setSize(width, height);
        }

        // 设置窗口位置 (居中显示)
        this.setLocationRelativeTo(null);
        // 设置窗口标题
        this.setTitle(" 扫  雷  游  戏 ");
        // 设置窗口关闭操作
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 鼠标监听器
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch(GameUtil.state){
                    case 0:
                        if(e.getButton() == 1){   // 左键
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.LEFT = true;
                        }
                        if(e.getButton() == 3){   // 右键
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.RIGHT = true;
                        }

                    case 1:


                    case 2:
                        if(e.getButton() == 1){
                            if(e.getX()>GameUtil.OFFSET+(GameUtil.MAP_W/2)*GameUtil.SQUARE_LENGTH&&
                                    e.getX()<GameUtil.OFFSET+(GameUtil.MAP_W/2)*GameUtil.SQUARE_LENGTH + GameUtil.SQUARE_LENGTH&&
                                    e.getY()>GameUtil.OFFSET&&
                                    e.getY()<GameUtil.OFFSET+GameUtil.SQUARE_LENGTH){
                                mapBottom.reGame();
                                mapTop.reGame();
                                GameUtil.FLAG_NUM = 0;
                                GameUtil.START_TIME = System.currentTimeMillis();
                                GameUtil.state = 0;
                            }
                        }
                        break;
                    case 3:
                        if(e.getButton() == 1){   // 左键
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            begin = gameSelect.hard();
                        }
                }


            }
        });



        //必要时可用的延时方法
        while (true) {
            repaint();
            begin();
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void begin(){
        if(begin){
            begin=false;
            gameSelect.hard(GameUtil.level);
            GameWin gameWin = new GameWin();
            GameUtil.START_TIME = System.currentTimeMillis();
            mapBottom.reGame();
            mapTop.reGame();
            gameWin.launch();
        }
    }

    // paint 方法，用于绘制游戏界面
    @Override
    public void paint(Graphics g) {
        if(GameUtil.state==3){
            g.setColor(Color.white);
            g.fillRect(0,0,500,500);
            gameSelect.paintSelect(g);
        }
        else{
            offScreenImage = this.createImage(width, height);
            Graphics gImage = offScreenImage.getGraphics();
            // 设置背景填充色
            gImage.setColor(Color.pink);
            gImage.fillRect(0, 0, width, height);
            mapBottom.paintSelf(gImage);
            mapTop.paintSelf(gImage);
            g.drawImage(offScreenImage, 0, 0,  null);
        }

    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();

    }

}
