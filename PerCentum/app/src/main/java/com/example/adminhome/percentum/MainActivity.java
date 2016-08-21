package com.example.adminhome.percentum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String BUNDLE_KEY_MAIN_NUMBER = "keyMainNumber";
    public final static String BUNDLE_KEY_PART_NUMBER = "keyPartNumber";
    public final static String BUNDLE_KEY_RESULT_NUMBER = "keyResultNumber";

    private final static String TAG = "=====TAG";

    /**
     * number to make multiplication with Part Number
     */
    public final static double HUNDRED = 100;

    /**
     * value of part number
     */
    private double mPartNumber;

    /**
     * value of main number
     */
    private double mMainNumber;

    /**
     * value of result
     */
    private double mResult;

    /**
     * EditText of main number
     */
    private EditText mEditMain;

    /**
     * EditText of part number
     */
    private EditText mEditPart;

    /**
     * TextView for result
     */
    private TextView mResultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditMain = (EditText) findViewById(R.id.mainNumberId);
        mEditPart = (EditText) findViewById(R.id.partNumberId);
        mResultTextView = (TextView) findViewById(R.id.resultTvId);

        if (savedInstanceState != null) {
            //get values of fields from bundle
            mMainNumber = savedInstanceState.getDouble(MainActivity.BUNDLE_KEY_MAIN_NUMBER);
            mPartNumber = savedInstanceState.getDouble(MainActivity.BUNDLE_KEY_PART_NUMBER);
            mResult = savedInstanceState.getDouble(MainActivity.BUNDLE_KEY_RESULT_NUMBER);

            //set text from bundle values of fields to all EditTexts and TextView
            mEditMain.setText(Double.toString(mMainNumber));
            mEditPart.setText(Double.toString(mPartNumber));
            mResultTextView.setText(Double.toString(mResult));
        }
    }

    public void btnClick(View view) {
        mMainNumber = Double.parseDouble(mEditMain.getText().toString());
        mPartNumber = Double.parseDouble(mEditPart.getText().toString());

        mResult = calculatePercent();

        setResultText();
    }

    /**
     * make calculation of percent
     * @return double - value of result
     */
    public double calculatePercent() {
        double resultValue = 0;
        double multiple = mPartNumber * HUNDRED;
        try {
            resultValue = multiple / mMainNumber;
        }
        catch (ArithmeticException exception) {
            Toast.makeText(this, "Error division by zero", Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG, Double.toString(multiple));
        Log.d(TAG, Double.toString(resultValue));
        return resultValue;
    }

    /**
     * set value of result to TextView
     */
    public void setResultText() {
        String resultString = Double.toString(mResult) + "%";
        mResultTextView.setText(resultString);
    }

    /**
     * saved fields in bundle
     * @param bundle object Bundle
     */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);

        bundle.putDouble(BUNDLE_KEY_MAIN_NUMBER, mMainNumber);
        bundle.putDouble(BUNDLE_KEY_PART_NUMBER, mPartNumber);
        bundle.putDouble(BUNDLE_KEY_RESULT_NUMBER, mResult);
    }
}
