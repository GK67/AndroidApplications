package com.example.gk.dictionary;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.OrientationHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * To handle the quiz selection page
 */
public class SelectQuizFragment extends Fragment {

    private static final int STUDY_TOPIC_CHECKED_LIST = 0;
    private static final int STUDY_FIELD_CHECKED_LIST = 1;

    // Views & Widgets
    private TextInputLayout amountOfQuestionTextView;
    private EditText amountOfQuestionEditText;
    private TextView tvChooseQuizStyle;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_select_quiz);
    }
    @Nullable

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_select_quiz, container, false);
        return rootView;
    }

    /**
     * To set up views
     *  
     */
  

    /**
     * To set up views
     *
     * @param view
     * @param savedInstanceState
     */
   // @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView();
    }

    private void setUpView() {
        // Create a new linear layout
        LinearLayout studyTopicSection = (LinearLayout) rootView
                .findViewById(R.id.study_topics_layout);

        LinearLayout studyFieldsLayout = (LinearLayout) rootView
                .findViewById(R.id.study_fields_layout);

        amountOfQuestionTextView = (TextInputLayout) rootView.findViewById(R.id.question_qty_text);
        final EditText amountOfQuestionEditText = (EditText) rootView.findViewById(R.id.quiz_question_qty);
        /*
        * Configure checkboxes for study Topics
        */

        final ArrayList<AppCompatCheckBox> studyTopicCheckBox = new ArrayList<>();

        //final MedicineLab medicineLab = MedicineLab.get(getContext());
        final MedicineLab medicineLab = MedicineLab.get(getContext());
        List<String> studyTopic = medicineLab.getStudyTopics();
        Log.i("test", studyTopic.toString());

        for (int i = 0; i < studyTopic.size(); i++) {
            String topic = studyTopic.get(i);
            AppCompatCheckBox checkBox = new AppCompatCheckBox(getContext());
            //AppCompatCheckBox checkBox = new AppCompatCheckBox(getContext());
            checkBox.setText(topic);
            studyTopicCheckBox.add(checkBox);
        }
        // to hold checked study topics
        final ArrayList<String> studyTopicCheckedList = new ArrayList<>();

        /*
         * Set click listeners
         */
        for (int i = 0; i < studyTopicCheckBox.size(); i++) {
            studyTopicCheckBox.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckboxClicked((AppCompatCheckBox) v, studyTopicCheckedList,
                            STUDY_TOPIC_CHECKED_LIST);
                }
            });
            studyTopicSection.addView(studyTopicCheckBox.get(i));
        }
        /**
         * Configure checkboxes for study Fields
         */

        // to hold checked study fields
        final ArrayList<String> studyFieldCheckedList = new ArrayList<>();
        // new checkboxes for study fields
        final ArrayList<AppCompatCheckBox> studyFieldCheckBox = new ArrayList<>();
        {
            //AppCompatCheckBox checkBox = new AppCompatCheckBox(getContext());
            AppCompatCheckBox checkBox = new AppCompatCheckBox(getContext());

            checkBox.setText(MedicineSchema.MedicineTable.Cols.DEASCH);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckboxClicked((AppCompatCheckBox) v, studyFieldCheckedList,
                            STUDY_FIELD_CHECKED_LIST);
                }
            });
            studyFieldCheckBox.add(checkBox);
        }
        {
            //AppCompatCheckBox checkBox = new AppCompatCheckBox(this);
            AppCompatCheckBox checkBox = new AppCompatCheckBox(getContext());
            checkBox.setText(MedicineSchema.MedicineTable.Cols.PURPOSE);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckboxClicked((AppCompatCheckBox) v, studyFieldCheckedList,
                            STUDY_FIELD_CHECKED_LIST);
                }
            });
            studyFieldCheckBox.add(checkBox);
        }
        {
           // AppCompatCheckBox checkBox = new AppCompatCheckBox(this);
            AppCompatCheckBox checkBox = new AppCompatCheckBox(getContext());
            checkBox.setText(MedicineSchema.MedicineTable.Cols.SPECIAL);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckboxClicked((AppCompatCheckBox) v, studyFieldCheckedList,
                            STUDY_FIELD_CHECKED_LIST);
                }
            });
            studyFieldCheckBox.add(checkBox);
        }
        {
            //AppCompatCheckBox checkBox = new AppCompatCheckBox(this);
            AppCompatCheckBox checkBox = new AppCompatCheckBox(getContext());
            checkBox.setText(MedicineSchema.MedicineTable.Cols.CATEGORY);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCheckboxClicked((AppCompatCheckBox) v, studyFieldCheckedList,
                            STUDY_FIELD_CHECKED_LIST);
                }
            });
            studyFieldCheckBox.add(checkBox);
        }
        for (int i = 0; i < studyFieldCheckBox.size(); i++) {
            studyFieldsLayout.addView(studyFieldCheckBox.get(i));
        }
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("Multiple choice");
        spinnerArray.add("Fill in the blank");
        final Spinner spChooseQuizStyle = (Spinner) rootView.findViewById(R.id.quiz_style);
        ArrayAdapter<String> quizStyle = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, spinnerArray);
//        ArrayAdapter<String> quizStyle = new ArrayAdapter<String>(getActivity(),
//                android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spChooseQuizStyle.setAdapter(quizStyle);
        ///////////// START THE QUIZ ///////////////
        Button startQuizButton = (Button) rootView.findViewById(R.id.start_button);
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // List of chosen medicines
                List<Medicine> medicinesQuiz = findMedicinesQuiz(studyTopicCheckedList);

                // Number of questions
                int numOfQuestions;

                // Try to get number of questions from input
                try {
                    numOfQuestions = Integer
                            .parseInt(amountOfQuestionEditText.getText().toString());
                } catch (Exception ex) {
                    numOfQuestions = 0;
                }

                // Validate all inputs
                if (studyTopicCheckedList.isEmpty() || studyFieldCheckedList.isEmpty()) {
                    Toast.makeText(getActivity(),
                            "Please select at least one STUDY TOPIC and one STUDY FIELD",
                            Toast.LENGTH_SHORT).show();
                } else if (numOfQuestions > medicinesQuiz.size()
                        || numOfQuestions <= 0) {
                    Toast.makeText(getActivity(),
                            "Please enter a VALID AMOUNT OF QUESTIONS", Toast.LENGTH_SHORT).show();
                } else {

                    // Start the quiz activity
                    for (Medicine medicine : medicinesQuiz) {
                        Log.i("test", medicine.getGenericName());
                    }
                    Log.i("test", String.valueOf(medicinesQuiz.size()));

                    String[] topicList = toStringArray(studyTopicCheckedList);
                    String[] fieldList = toStringArray(studyFieldCheckedList);

                    if (spChooseQuizStyle.getSelectedItem().toString().equals("Fill in the blank")) {
                        QuizFragment fragment = QuizFragment
                                .newInstance(topicList, fieldList, numOfQuestions);
                        ReplaceFragmentCommand
                                .startNewFragment(getActivity(), fragment, true);
                    } else if (spChooseQuizStyle.getSelectedItem().toString().equals("Multiple choice")) {
                        QuizMultipleChoiceFragment fragment = QuizMultipleChoiceFragment
                                .newInstance(topicList, fieldList, numOfQuestions);
                        ReplaceFragmentCommand
                                .startNewFragment(getActivity(), fragment, true);
                    }

                }
            }
        });


    
    }

    @Override
    public void onResume() {
        super.onResume();
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void onPause() {
        super.onPause();
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
    }
        // Create a new linear layout
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.quiz_activity_linear_layout);
//        final TextView mSelectStudyTopicTextView = new TextView(this);
//        mSelectStudyTopicTextView.setText("Select Study Topics");
//        mSelectStudyTopicTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
//        mSelectStudyTopicTextView.setPadding(10, 20, 10, 20);
//        mSelectStudyTopicTextView.setGravity(Gravity.CENTER_HORIZONTAL);
//
//        linearLayout.addView(mSelectStudyTopicTextView);
//
//        // new checkboxes for study topics
//        final ArrayList<CheckBox> studyTopicCheckBox = new ArrayList<>();
//
//        final MedicineLab medicineLab = MedicineLab.get(this);
//        List<String> studyTopic = medicineLab.getStudyTopics();
//        Log.i("test", studyTopic.toString());
//
//        for (int i = 0; i < studyTopic.size(); i++) {
//            String topic = studyTopic.get(i);
//            CheckBox checkBox = new CheckBox(this);
//            checkBox.setText(topic);
//            studyTopicCheckBox.add(checkBox);
//        }
//
//        // to hold checked study topics
//        final ArrayList<String> studyTopicCheckedList = new ArrayList<>();
//
//        for (int i = 0; i < studyTopicCheckBox.size(); i++) {
//            studyTopicCheckBox.get(i).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onCheckboxClicked((CheckBox)v, studyTopicCheckedList, STUDY_TOPIC_CHECKED_LIST);
//                }
//            });
//            linearLayout.addView(studyTopicCheckBox.get(i));
//        }
//
//        linearLayout.addView(getSeparateLine());
//
//        TextView mSelectStudyFieldTextView = new TextView(this);
//        mSelectStudyFieldTextView.setText("Select Study Fields");
//        mSelectStudyFieldTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
//        mSelectStudyFieldTextView.setPadding(10, 20, 10, 20);
//        mSelectStudyFieldTextView.setGravity(Gravity.CENTER_HORIZONTAL);
//
//        linearLayout.addView(mSelectStudyFieldTextView);
//
//        // to hold checked study fields
//        final ArrayList<String> studyFieldCheckedList = new ArrayList<>();
//        // new checkboxes for study fields
//        final ArrayList<CheckBox> studyFieldCheckBox = new ArrayList<>();
//        {
//            CheckBox checkBox = new CheckBox(this);
//            checkBox.setText(MedicineSchema.MedicineTable.Cols.DEASCH);
//            checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onCheckboxClicked((CheckBox)v, studyFieldCheckedList, STUDY_FIELD_CHECKED_LIST);
//                }
//            });
//            studyFieldCheckBox.add(checkBox);
//        }
//        {
//            CheckBox checkBox = new CheckBox(this);
//            checkBox.setText(MedicineSchema.MedicineTable.Cols.PURPOSE);
//            checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onCheckboxClicked((CheckBox)v, studyFieldCheckedList, STUDY_FIELD_CHECKED_LIST);
//                }
//            });
//            studyFieldCheckBox.add(checkBox);
//        }
//        {
//            CheckBox checkBox = new CheckBox(this);
//            checkBox.setText(MedicineSchema.MedicineTable.Cols.SPECIAL);
//            checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onCheckboxClicked((CheckBox)v, studyFieldCheckedList, STUDY_FIELD_CHECKED_LIST);
//                }
//            });
//            studyFieldCheckBox.add(checkBox);
//        }
//        {
//            CheckBox checkBox = new CheckBox(this);
//            checkBox.setText(MedicineSchema.MedicineTable.Cols.CATEGORY);
//            checkBox.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onCheckboxClicked((CheckBox)v, studyFieldCheckedList, STUDY_FIELD_CHECKED_LIST);
//                }
//            });
//            studyFieldCheckBox.add(checkBox);
//        }
//
//        for (int i = 0; i < studyFieldCheckBox.size(); i++) {
//            linearLayout.addView(studyFieldCheckBox.get(i));
//        }
//
//        linearLayout.addView(getSeparateLine());
//
//        LinearLayout amountOfQuestionLayout = new LinearLayout(this);
//        amountOfQuestionLayout.setOrientation(OrientationHelper.HORIZONTAL);
//        amountOfQuestionLayout.setGravity(Gravity.CENTER_HORIZONTAL);
//        amountOfQuestionLayout.setPadding(10, 20, 10, 20);
//
//
//        amountOfQuestionTextView = new TextView(this);
//        amountOfQuestionTextView.setText("Enter amount of Questions: ");
//
//        final EditText amountOfQuestionEditText = new EditText(this);
//        amountOfQuestionEditText.setWidth(dpToPx(70));
//
//        amountOfQuestionLayout.addView(amountOfQuestionTextView);
//        amountOfQuestionLayout.addView(amountOfQuestionEditText);
//
//
//        linearLayout.addView(amountOfQuestionLayout);
//
//        ///////////// START THE QUIZ ///////////////
//        Button startQuizButton = new Button(this);
//        startQuizButton.setText("Start The Quiz");
//        startQuizButton.setGravity(Gravity.CENTER_HORIZONTAL);
//
//        startQuizButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                List<Medicine> medicinesQuiz = findMedicinesQuiz(studyTopicCheckedList);
//                int numOfQuestions;
//                try {
//                    numOfQuestions = Integer.parseInt(amountOfQuestionEditText.getText().toString());
//                }
//                catch (Exception ex) {
//                    numOfQuestions = 0;
//                }
//
//                if (studyTopicCheckedList.isEmpty() || studyFieldCheckedList.isEmpty()) {
//                    Toast.makeText(getApplication(),
//                            "Please select at least one STUDY TOPIC and one STUDY FIELD", Toast.LENGTH_SHORT).show();
//                }
//                else if (numOfQuestions > medicinesQuiz.size()
//                        || numOfQuestions <= 0) {
//                    Toast.makeText(getApplication(),
//                            "Please enter a VALID AMOUNT OF QUESTIONS", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    for (Medicine medicine: medicinesQuiz) {
//                        Log.i("test", medicine.getGenericName());
//                    }
//                    Log.i("test", String.valueOf(medicinesQuiz.size()));
//
//                    String[] topicList = toStringArray(studyTopicCheckedList);
//                    String[] fieldList = toStringArray(studyFieldCheckedList);
//
//                    Intent intent = QuizActivity.newIntent(SelectQuizFragment.this, topicList, fieldList, numOfQuestions);
//                    startActivity(intent);
//                }
//            }
//        });
//
//        linearLayout.addView(startQuizButton);


    //}

    // to display a line
    private TextView getSeparateLine() {
        LinearLayoutCompat.LayoutParams layoutParams =
                new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpToPx(1));
        TextView textView = new TextView(getActivity());
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundColor(Color.BLACK);
        return textView;
    }

    // convert dp to px
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    // On checked listener
    public void onCheckboxClicked(CheckBox checkBox, ArrayList<String> checkedList, int arrayListCode) {
        // Is the view now checked?
        boolean checked = checkBox.isChecked();

        if (checked) {
            checkedList.add(checkBox.getText().toString());
        }
        else {
            checkedList.remove(checkBox.getText().toString());
        }


        List<Medicine> tempMedicines = findMedicinesQuiz(checkedList);

        if (arrayListCode == STUDY_TOPIC_CHECKED_LIST) {
            if (tempMedicines != null) {
                String maxQuestion = String.valueOf(tempMedicines.size());
                amountOfQuestionTextView.setHelperText("Enter amount of questions (up to " + maxQuestion + "): ");
                Log.i("test", String.valueOf(findMedicinesQuiz(checkedList).size()));
            }
            else {
                amountOfQuestionTextView.setHelperText("Enter amount of Questions: ");
                Log.i("test", String.valueOf(0));
            }
        }
    }

    private List<Medicine> findMedicinesQuiz(ArrayList<String> checkedList) {
        String[] tempStrings = new String[checkedList.size()];
        tempStrings = checkedList.toArray(tempStrings);
        Log.i("test", Arrays.asList(tempStrings).toString());

        String whereArgs = "(";
        for (int i = 0; i < tempStrings.length; i++) {
            whereArgs += "?";
            if (i != tempStrings.length - 1)
                whereArgs += ",";
        }
        whereArgs += ")";
        Log.i("test", whereArgs);
        List<Medicine> medicinesQuiz = MedicineLab.get(getActivity())
                .getSpecificMedicines("StudyTopic IN " + whereArgs, tempStrings, MedicineSchema.MedicineTable.Cols.GENERIC_NAME);
        return medicinesQuiz;
    }

    private String[] toStringArray(ArrayList<String> list) {
        String[] tempStrings = new String[list.size()];
        tempStrings = list.toArray(tempStrings);

        return  tempStrings;
    }
}


