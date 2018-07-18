package edu.orangecoastcollege.cs273.touchgestures;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener{

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    // Member variable to detect gestures
    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = (ScrollView) findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = (TextView) findViewById(R.id.gesturesLogTextView);
        singleTapTextView = (TextView) findViewById(R.id.singleTapTextView);
        doubleTapTextView = (TextView) findViewById(R.id.doubleTapTextView);
        longPressTextView = (TextView) findViewById(R.id.longPressTextView);
        scrollTextView = (TextView) findViewById(R.id.scrollTextView);
        flingTextView = (TextView) findViewById(R.id.flingTextView);

        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
    }

    /**
     * Clears the scrollTextView and resets the counters and their TextViews
     */
    public void clearAll(View v)
    {
        gesturesLogTextView.setText("");
        singleTapTextView.setText("");
        doubleTapTextView.setText("");
        longPressTextView.setText("");
        scrollTextView.setText("");
        flingTextView.setText("");

        singleTaps = 0;
        doubleTaps = 0;
        longPresses = 0;
        scrolls = 0;
        flings = 0;
    }

    /**
     * Sending the touch event to all the children in the view group
     * e.g. ScrollView down to TextView
     * @param ev The motion event triggering the touch.
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    /**
     * Handles a single-tap gesture. Not part of a double-tap.
     * @param motionEvent The motion event triggering the gesture.
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        // Display the message and increment the counter
        gesturesLogTextView.append("\nonSingleTap gesture occurred");
        singleTapTextView.setText(String.valueOf(++singleTaps));
        return true;
    }

    /**
     * Responds ot a double-tap gesture. Double-tap is the succession of two
     * single tap gestures within a certain duration.
     * @param motionEvent The motion event triggering the gesture.
     * @return True if the event was handled, false otherwise
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nDoubleTap gesture occurred");
        doubleTapTextView.setText(String.valueOf(++doubleTaps));
        return true;
    }

    /**
     * During a double tap, another event occurs (including move).
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDoubleTap event occurred");
        return true;
    }

    /**
     * User made contact with device.
     * Every gesture begins with onDown.
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonDown event occurred");
        return true;
    }

    /**
     * Down event where user does not let go, short duration of time.
     * @param motionEvent
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonShowPress gesture occurred");
    }

    /**
     * Similar to onSingleTapConfirmed, but it could be part of a double-tap.
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonSingleUp event occurred");
        return true;
    }

    /**
     * Down event, followed by a press and lateral movement, without letting go.
     * @param motionEvent The event where the scroll started.
     * @param motionEvent1 The event where teh scroll stopped.
     * @param distanceX The distance in X direction (pixels).
     * @param distanceY The distance in Y direction (pixels).
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float distanceX, float distanceY) {
        gesturesLogTextView.append("\nScroll gesture occurred, distanceX = " + distanceX + ", distanceY = " + distanceY);
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    /**
     * Down event, followed by a long hold.
     * @param motionEvent
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {
        gesturesLogTextView.append("\nonLongPress gesture occurred");
        longPressTextView.setText(String.valueOf(++longPresses));
    }

    /**
     * Similar to scroll, with faster velocity and user releases contact with device.
     * @param motionEvent
     * @param motionEvent1
     * @param v  Velocity in the x-direction (pixels/second).
     * @param v1 Velocity in the y-direction (pixels/second).
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        gesturesLogTextView.append("\nFling gesture occurred, velocityX = " + v + ", velocityY = " + v1);
        flingTextView.setText(String.valueOf(++flings));
        return true;
    }
}
