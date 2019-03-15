package com.example.gk.dictionary;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private static final String EXTRA_TOPIC_LIST = "extra: topic list";
    private static final String EXTRA_FIELD_LIST = "extra: field list";
    private static final String EXTRA_NUM_QUIZ = "extra: num quiz";
    private static int numQuiz;
    private static int index = 0;
    private static int done = 0;
    private static int correct = 0;
    String[] topicList;
    String[] fieldList;
    List<Medicine> medicines;
    private LinearLayout mLinearLayout;
    private LinearLayout mSubmitButtonLayout;
    private TextView mDrugNameTextView;
    private TextView mScoreTextView;
    private Button[] mSubmitButton;
    private Button mNextButton;
    private Button mPreviousButton;

    TextView purposeTV;
    TextView categoryTV;
    TextView deaSchTV;
    TextView specialTV;
    EditText[] purposeET = null;
    EditText[] categoryET = null;
    EditText[] deaSchET = null;
    EditText[] specialET = null;
    boolean[] isViewCreated = null;


    // a method to create an intent with an extra
    public static Intent newIntent(Context context, String[] topicList, String[] fieldList, int numQuiz) {
        Intent i = new Intent(context, QuizActivity.class);
        i.putExtra(EXTRA_TOPIC_LIST, topicList);
        i.putExtra(EXTRA_FIELD_LIST, fieldList);
        i.putExtra(EXTRA_NUM_QUIZ, numQuiz);
        return i;
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        index = 0;
        done = 0;
        correct = 0;

        // get data from the intent
        topicList = getIntent().getStringArrayExtra(EXTRA_TOPIC_LIST);
        fieldList = getIntent().getStringArrayExtra(EXTRA_FIELD_LIST);
        numQuiz = getIntent().getIntExtra(EXTRA_NUM_QUIZ, 0);

        mSubmitButton = new Button[numQuiz];

        purposeET = new EditText[numQuiz];
        categoryET = new EditText[numQuiz];
        deaSchET = new EditText[numQuiz];
        specialET = new EditText[numQuiz];

        isViewCreated = new boolean[numQuiz];
        for (int i = 0; i < isViewCreated.length; i++) {
            isViewCreated[i] = false;
        }

        medicines = findMedicinesQuiz(topicList);
        Collections.shuffle(medicines);
        medicines = medicines.subList(0, numQuiz);
        Log.i("test1", String.valueOf(medicines.size()));

        mLinearLayout = (LinearLayout) findViewById(R.id.quiz_activity_linear_layout);
        mSubmitButtonLayout = (LinearLayout) findViewById(R.id.quiz_activity_linear_layout_submit_button);
      //  mDrugNameTextView = (TextView) findViewById(R.id.drug_name_quiz);
        mScoreTextView = (TextView) findViewById(R.id.score_quiz);

        for (Medicine medicine : medicines) {
            Log.i("test1", medicine.getGenericName());
        }
        for (int i = 0; i < fieldList.length; i++) {
            Log.i("test1", fieldList[i]);
        }

        ///////// Next button //////////
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = (index + 1) % numQuiz;
                updateUI();
            }
        });

        ///////// Previous //////////
        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0)
                    index = numQuiz;
                index = (index - 1) % numQuiz;
                updateUI();
            }
        });

        updateUI();

    }

    private void updateUI() {
        // update scores
        mDrugNameTextView.setText(medicines.get(index).getGenericName());
        mScoreTextView.setText("Index: "+index+"/"+numQuiz+"   Done: " + done + "/" + numQuiz
                + "\t\t\t" + "Correct: " + correct);

        mSubmitButtonLayout.removeAllViews();

        mLinearLayout.removeAllViews();
        mLinearLayout.addView(mDrugNameTextView);

        // if a view is not yet created, create a new view
        if (!isViewCreated[index]) {
            for (String temp : fieldList) {
                TextView textView = new TextView(this);
                mLinearLayout.addView(textView);
                textView.setText(temp + ":");


                if (temp.equals(MedicineSchema.MedicineTable.Cols.PURPOSE)) {
                    purposeET[index] = new EditText(this);
                    mLinearLayout.addView(purposeET[index]);
                } else if (temp.equals(MedicineSchema.MedicineTable.Cols.CATEGORY)) {
                    categoryET[index] = new EditText(this);
                    mLinearLayout.addView(categoryET[index]);
                } else if (temp.equals(MedicineSchema.MedicineTable.Cols.DEASCH)) {
                    deaSchET[index] = new EditText(this);

                    if (medicines.get(index).getDeaSch().equals("-")) {
                        deaSchET[index].setText("-");
                        deaSchET[index].setEnabled(false);
                        deaSchET[index].setBackgroundColor(Color.LTGRAY);
                    }

                    mLinearLayout.addView(deaSchET[index]);
                } else if (temp.equals(MedicineSchema.MedicineTable.Cols.SPECIAL)) {
                    specialET[index] = new EditText(this);

                    if (medicines.get(index).getSpecial().equals("")) {
                        specialET[index].setText("");
                        specialET[index].setEnabled(false);
                        specialET[index].setBackgroundColor(Color.LTGRAY);
                    }


                    mLinearLayout.addView(specialET[index]);
                }
            }


            ///////////// Submit Button ///////////////
            mSubmitButton[index] = new Button(this);
            mSubmitButton[index].setText("Submit");
            mSubmitButton[index].setGravity(Gravity.BOTTOM);
            mSubmitButton[index].setGravity(Gravity.CENTER_HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0 , 0, 0,
                    (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, getResources().getDisplayMetrics()));

            mSubmitButton[index].setLayoutParams(params);
            mSubmitButton[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean isPurposeCorrect = false;
                    boolean isCategoryCorrect = false;
                    boolean isDeaSchCorrect = false;
                    boolean isSpecialCorrect = false;

                    Log.i("test1", medicines.get(index).getPurpose());
                    Log.i("test1", medicines.get(index).getCategory());
                    Log.i("test1", medicines.get(index).getDeaSch());
                    Log.i("test1", medicines.get(index).getSpecial());

                    // Set the color of a text view to green if a user input correct answer
                    // otherwise, change the color to red

                    if (purposeET[index] != null) {
                        if (purposeET[index].getText().toString().equals(medicines.get(index).getPurpose())) {
                            purposeET[index].setTextColor(Color.GREEN);
                            isPurposeCorrect = true;
                        }
                        else {

                            Toast toast = Toast.makeText(getApplication(),
                                    "Wrong answer", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL,0,0);
                            toast.show();
                            purposeET[index].setTextColor(Color.RED);
                        }
                        purposeET[index].setEnabled(false);
                    }
                    else {
                        isPurposeCorrect = true;
                    }

                    if (categoryET[index] != null) {
                        if (categoryET[index].getText().toString().equals(medicines.get(index).getCategory())) {
                            categoryET[index].setTextColor(Color.GREEN);
                            isCategoryCorrect = true;
                        }
                        else {
                            categoryET[index].setTextColor(Color.RED);
                        }
                        categoryET[index].setEnabled(false);
                    }
                    else {
                        isCategoryCorrect = true;
                    }

                    if (deaSchET[index] != null) {
                        if (deaSchET[index].getText().toString().equals(medicines.get(index).getDeaSch())) {
                            deaSchET[index].setTextColor(Color.GREEN);
                            isDeaSchCorrect = true;
                        }
                        else {
                            deaSchET[index].setTextColor(Color.RED);
                        }
                        deaSchET[index].setEnabled(false);
                    }
                    else {
                        isDeaSchCorrect = true;
                    }

                    if (specialET[index] != null) {
                        if (specialET[index].getText().toString().equals(medicines.get(index).getSpecial())) {
                            specialET[index].setTextColor(Color.GREEN);
                            isSpecialCorrect = true;
                        }
                        else {
                            specialET[index].setTextColor(Color.RED);
                        }
                        specialET[index].setEnabled(false);
                    }
                    else {
                        isSpecialCorrect = true;
                    }


                    // Disable all text views





                    // Update Views
                    if (isPurposeCorrect && isCategoryCorrect && isDeaSchCorrect && isSpecialCorrect) {
                        correct++;
                        done++;
                        mSubmitButton[index].setText("Correct");
                        mSubmitButton[index].setEnabled(false);
                    }
                    else {
                        done++;
                        mSubmitButton[index].setText("Incorrect");
                        mSubmitButton[index].setEnabled(false);
                    }
                }
            });

            mSubmitButtonLayout.addView(mSubmitButton[index]);

            Log.i("test1", "ViewCreated");
            isViewCreated[index] = true;
        }
        else {
            for (int i = 0; i < fieldList.length; i++) {
                String temp = fieldList[i];

                TextView textView = new TextView(this);
                mLinearLayout.addView(textView);
                textView.setText(temp + ":");


                if (temp.equals(MedicineSchema.MedicineTable.Cols.PURPOSE)) {
                    mLinearLayout.addView(purposeET[index]);
                }
                else if (temp.equals(MedicineSchema.MedicineTable.Cols.CATEGORY)) {
                    mLinearLayout.addView(categoryET[index]);
                }
                else if (temp.equals(MedicineSchema.MedicineTable.Cols.DEASCH)) {
                    mLinearLayout.addView(deaSchET[index]);
                }
                else if (temp.equals(MedicineSchema.MedicineTable.Cols.SPECIAL)) {
                    mLinearLayout.addView(specialET[index]);
                }

            }

            mSubmitButtonLayout.addView(mSubmitButton[index]);
        }

    }

    // to find medicines from the database
    private List<Medicine> findMedicinesQuiz(String[] topicList) {

        String whereArgs = "(";
        for (int i = 0; i < topicList.length; i++) {
            whereArgs += "?";
            if (i != topicList.length - 1)
                whereArgs += ",";
        }
        whereArgs += ")";

        List<Medicine> medicinesQuiz = MedicineLab.get(this)
                .getSpecificMedicines("StudyTopic IN " + whereArgs, topicList, MedicineSchema.MedicineTable.Cols.GENERIC_NAME);
        return medicinesQuiz;
    }
}
