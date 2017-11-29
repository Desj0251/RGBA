package model;

import java.util.Observable;

/*
* Purpose: Colour picker app to create colours using HSV sliders
* @author John Desjardins (desj0251)
* @File HSVModel.java
* @version 1.0.0
*/

public class HSVModel extends Observable {

    // CLASS VARIABLES
    public static final Integer MAX_HUE         = 360;
    public static final float MAX_SATURATION    = 1;
    public static final float MAX_VALUE         = 1;
    public static final Integer MIN_HSV         = 0;

    // INSTANCE VARIABLES
    private Integer hue;
    private float saturation;
    private float value;

    public HSVModel() {
        this( MAX_HUE, MAX_SATURATION, MAX_VALUE );
    }

    public HSVModel (Integer hue, float saturation, float value) {
        super();

        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    public void asBlack()   { this.setHSV(0, 0, 0); }
    public void asRed()     { this.setHSV(0, 1, 1); }
    public void asLime()    { this.setHSV(120, 1, 1); }
    public void asBlue()    { this.setHSV(240, 1, 1); }
    public void asYellow()  { this.setHSV(60, 1, 1); }
    public void asCyan()    { this.setHSV(180, 1, 1); }
    public void asMagenta() { this.setHSV(300, 1, 1); }
    public void asSilver()  { this.setHSV(0, 0, 0.75f); }
    public void asGray()    { this.setHSV(0, 0, 0.5f); }
    public void asMaroon()  { this.setHSV(0, 100, 0.5f); }
    public void asOlive()   { this.setHSV(60, 1, 0.5f); }
    public void asGreen()   { this.setHSV(120, 1, 0.5f); }
    public void asPurple()  { this.setHSV(300, 1, 0.50f); }
    public void asTeal()    { this.setHSV(180, 1, 0.50f); }
    public void asNavy()    { this.setHSV(240, 1, 0.50f); }

    //GETTERS
    public float getSaturation()    { return saturation; }
    public float getValue()         { return value; }
    public Integer getHue()         { return hue; }

    // SETTERS
    public void setHue(Integer hue) { this.hue = hue; this.updateObservers(); }
    public void setSaturation(float saturation) { this.saturation = saturation; this.updateObservers(); }
    public void setValue(float value) { this.value = value; this.updateObservers(); }
    public void setHSV (Integer hue, float saturation, float value) { this.hue = hue; this.saturation = saturation; this.value = value; this.updateObservers(); }

    private void updateObservers() {
        this.setChanged();      // sets the dirty flag on this data
        this.notifyObservers(); // broadcasts to all listeners
    }

}