package com.example.newapp.anim;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ItemLinearLayout extends LinearLayout {

        /**
         * 动画模式【true：华丽效果——缩放加方向】【false：只缩放】
         *
         * 华丽效果：
         * 		即点击控件的 上、下、左、右、中间时的效果都不一样
         *
         * 	普通效果：
         * 		即点击控件的任意部位，都只是缩放效果，与 华丽效果模式下 点击控件中间时的动画一样
         *
         **/
        private boolean superb = true;

        /** 顶点判断【0：中间】【1：上】【2：右】【3：下】【4：左】 **/
        private int pivot = 0;

    public ItemLinearLayout(Context context) {
            super(context);
            this.setClickable(true);
        }

    public ItemLinearLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
            this.setClickable(true);
        }

    public ItemLinearLayout(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            this.setClickable(true);
        }

        /**
         * 打开华丽效果
         */
        public void openSuperb(){
            superb = true;
        }
        /**
         * 关闭华丽效果
         */
        public void closeSuperb() {
            superb = false;
        }

        @Override
        @SuppressLint("ClickableViewAccessibility")
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction()) {
                // 手指按下
                case MotionEvent.ACTION_DOWN:
                    pivot = ItemAnim.startAnimDown(this, true, event.getX(), event.getY());
                    break;

                // 触摸动作取消
                case MotionEvent.ACTION_CANCEL:
                    // 手指抬起
                case MotionEvent.ACTION_UP:
                    ItemAnim.startAnimUp(this, pivot);
                    break;

                default:
                    break;
            }

            return super.onTouchEvent(event);
        }
      }
