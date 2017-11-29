package mad9132.hsv;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

/*
* @author John Desjardins (desj0251)
* @File MainActivity.java
* @version 1.0.0
*/

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {

    // CLASS VARIABLES
    private static final String ABOUT_DIALOG_TAG = "About";
    private static final String LOG_TAG          = "HSV";

    // INSTANCE VARIABLES
    // Pro-tip: different naming style; the 'm' means 'member'
    private AboutDialogFragment mAboutDialog;
    private TextView mColorSwatch;
    private HSVModel mModel;

    private SeekBar             mHueSB;
    private SeekBar             mSaturationSB;
    private SeekBar             mValueSB;

    private TextView            mHueTV;
    private TextView            mSaturationTV;
    private TextView            mValueTV;

    private void createToast(String message) {
        Context c = getApplicationContext();
        CharSequence s = message;
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(c, s, duration).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a new AboutDialogFragment()
        // but do not show it (yet)
        mAboutDialog = new AboutDialogFragment();

        // Instantiate a new HSV model
        mAboutDialog = new AboutDialogFragment();
        mModel = new HSVModel();
        mModel.setHSV(HSVModel.MIN_HSV, HSVModel.MIN_HSV, HSVModel.MIN_HSV);
        mModel.addObserver(this);

        // reference each View
        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);
        mHueTV = (TextView) findViewById(R.id.hue);
        mSaturationTV = (TextView) findViewById(R.id.saturation);
        mValueTV = (TextView) findViewById(R.id.value);

        // set the domain (i.e. max) for each component
        mHueSB.setMax(HSVModel.MAX_HUE);
        mSaturationSB.setMax(100);
        mValueSB.setMax(100);

        // register the event handler for each <SeekBar>
        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);

        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int hue = mModel.getHue();
                float sat = mModel.getSaturation();
                sat = sat * 100;
                float val = mModel.getValue();
                val = val * 100;
                String messageToDisplay = "H: " + hue + "\u00B0 S: " + (int) sat + "% V: " + (int) val + "%";
                createToast(messageToDisplay);
                return true;
            }
        });

        // initialize the View to the values of the Model
        this.updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Event handler for the <SeekBar>s: hue, saturation, and value.
     */

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (fromUser == false) {
            return;
        }

        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue(mHueSB.getProgress());
                String hueString = getResources().getString(R.string.hueProgress, progress).toUpperCase() + "\u00B0";
                mHueTV.setText(hueString);
                break;

            case R.id.saturationSB:
                float sat = mSaturationSB.getProgress();
                sat = sat / 100;
                mModel.setSaturation(sat);
                String satString = getResources().getString(R.string.saturationProgress, progress).toUpperCase() + "%";
                mSaturationTV.setText(satString);
                break;

            case R.id.valueSB:
                float val = mValueSB.getProgress();
                val = val / 100;
                mModel.setValue(val);
                String valString = getResources().getString(R.string.valueProgress, progress).toUpperCase() + "%";
                mValueTV.setText(valString);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // No-Operation
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText(getResources().getString(R.string.hue));
                break;
            case R.id.saturationSB:
                mSaturationTV.setText(getResources().getString(R.string.saturation));
                break;
            case R.id.valueSB:
                mValueTV.setText(getResources().getString(R.string.value));
                break;
        }
    }

    public void changeColorPreset(View v) {
        switch (v.getId()) {
            case R.id.buttonBlack:
                mModel.asBlack();
                break;

            case R.id.buttonRed:
                mModel.asRed();
                break;

            case R.id.buttonLime:
                mModel.asLime();
                break;

            case R.id.buttonBlue:
                mModel.asBlue();
                break;

            case R.id.buttonYellow:
                mModel.asYellow();
                break;

            case R.id.buttonCyan:
                mModel.asCyan();
                break;

            case R.id.buttonMagenta:
                mModel.asMagenta();
                break;

            case R.id.buttonSilver:
                mModel.asSilver();
                break;

            case R.id.buttonGray:
                mModel.asGray();
                break;

            case R.id.buttonMaroon:
                mModel.asMaroon();
                break;

            case R.id.buttonOlive:
                mModel.asOlive();
                break;

            case R.id.buttonGreen:
                mModel.asGreen();
                break;

            case R.id.buttonPurple:
                mModel.asPurple();
                break;

            case R.id.buttonTeal:
                mModel.asTeal();
                break;

            case R.id.buttonNavy:
                mModel.asNavy();
                break;
        }
    }

    // The Model has changed state!
    // Refresh the View to display the current values of the Model.
    @Override
    public void update(Observable observable, Object data) {
        this.updateView();
    }

    private void updateHueSB() {
        mHueSB.setProgress(mModel.getHue());
    }

    private void updateColorSwatch() {
        //GET the model's r,g,b,a values, and SET the background colour of the swatch <TextView>
        float[] hsv = {mModel.getHue(), mModel.getSaturation(), mModel.getValue()};

        mColorSwatch.setBackgroundColor(Color.HSVToColor(hsv));
    }

    private void updateSaturationSB() {
        float sat = mModel.getSaturation();
        sat = sat * 100;
        mSaturationSB.setProgress((int) sat);
    }

    private void updateValueSB() {
        float val = mModel.getValue();
        val = val * 100;
        mValueSB.setProgress((int) val);
    }

    // synchronize each View component with the Model
    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }

}   // end of class
