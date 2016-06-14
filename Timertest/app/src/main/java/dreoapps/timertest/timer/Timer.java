package dreoapps.timertest.timer;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by avlad92 on 6/14/16.
 */
public class Timer {



    public interface OnTimerStateChangeListener {

        void onTimerTick(long time);

        void onTimerStart();

        void onTimerStop();

        void onTimerPause();

        void onTimerStopPause();

    }





    private long mElapsedTime;
    private boolean mStarted; // started or stoped
    private boolean mRunning; // running or paused


    private OnTimerStateChangeListener mOnTimerStateChangeListener;





    private Handler mHandler = new Handler(){

        public void handleMessage(Message msg) {
            updateTime();
            sendMessageDelayed(Message.obtain(this, TICK_WHAT), 1000);
        }
    };

    private int TICK_WHAT = 2;


    public Timer(){
        init();

    }

    private void init(){
        mElapsedTime = 0;
    }

    public void start(){
        if(!mStarted) {
            mStarted = true;
            mRunning = true;
            mOnTimerStateChangeListener.onTimerStart();
            updateRunning();
        }else{
            if(mStarted && !mRunning){
                mRunning = true;
                mOnTimerStateChangeListener.onTimerStopPause();
                updateRunning();
            }
        }
    }

    public void stop(){
        if(mStarted) {
            mStarted = false;
            mOnTimerStateChangeListener.onTimerStop();
            updateRunning();
        }
    }

    public void pause(){
        if(mStarted){

            mRunning = !mRunning;

            if(mRunning) {
                mOnTimerStateChangeListener.onTimerStopPause();
            }else{
                mOnTimerStateChangeListener.onTimerPause();
            }
            updateRunning();
        }
    }

    private void updateTime(){
        mElapsedTime++;
        mOnTimerStateChangeListener.onTimerTick(mElapsedTime);
    }

    private void updateRunning() {

        if(mStarted){
            if(mRunning) {
                mHandler.sendMessageDelayed(Message.obtain(mHandler, TICK_WHAT), 1000);
                mRunning = true;
            }else{
                mHandler.removeMessages(TICK_WHAT);
            }
        }else{
            mHandler.removeMessages(TICK_WHAT);
            mElapsedTime = 0L;
            mOnTimerStateChangeListener.onTimerTick(mElapsedTime);
        }
    }

    public void setOnTimerStateChangeListener(OnTimerStateChangeListener onTimerStateChangeListener) {
        mOnTimerStateChangeListener = onTimerStateChangeListener;
        mOnTimerStateChangeListener.onTimerTick(mElapsedTime);
    }
}
