package com.example.gk.dictionary;

import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
//read
public class CardBackFragment extends Fragment {

    private static final String ARG_MEDICINE_ID = "arg: medicine id";
    private Medicine medicine;

    public static CardBackFragment newInstance(Medicine medicine) {
        Bundle args = new Bundle();  // a new bundle to hold arguments
        args.putSerializable(ARG_MEDICINE_ID, medicine);  // put a medicine id as an argument
        CardBackFragment fragment = new CardBackFragment();  // create a new detail fragment
        fragment.setArguments(args);  // put arguments to a fragment
        return fragment;
    }

    public CardBackFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();

        // check if a fragment contains any arguments
        // if so get a medicine id and get a medicine from a medicine lab
        if (args == null) {
            medicine = new Medicine("generic", "brand", "purpose", "deaSch", "special", "category", "studyTopic");
        }
        else {
            medicine = (Medicine) args.getSerializable(ARG_MEDICINE_ID);
            //Log.i("test2", medicine.getGenericName());
            //medicineGenericName = args.getString(ARG_MEDICINE_ID);
            //medicine = MedicineLab.get(getActivity()).getMedicine(medicineGenericName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card_back, container, false);
        TextView mBrandName = (TextView) rootView.findViewById(R.id.medicine_brand_name_textview);
        mBrandName.setText("Brand Name: " + medicine.getBrandName());

        TextView mPurpose = (TextView) rootView.findViewById(R.id.medicine_purpose_textview);
        mPurpose.setText("Purpose: " + medicine.getPurpose());

        TextView mCategory = (TextView) rootView.findViewById(R.id.medicine_category_textview);
        mCategory.setText("Category: " + medicine.getCategory());

        TextView mDeaSch = (TextView) rootView.findViewById(R.id.medicine_deaSch_textview);
        mDeaSch.setText("DeaSch: " + medicine.getDeaSch());

        TextView mSpecial = (TextView) rootView.findViewById(R.id.medicine_special_textview);
        mSpecial.setText("Special: " + medicine.getSpecial());

        TextView mStudyTopic = (TextView) rootView.findViewById(R.id.medicine_study_topic_textview);
        mStudyTopic.setText("Study Topic: " + medicine.getStudyTopic());

        TextView mFlip = (TextView) rootView.findViewById(R.id.flip_text_view_back);
        mFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //flipCard();
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                                R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                        .replace(R.id.container, CardFrontFragment.newInstance(medicine))
                        .commit();

                //cardFlipped = !cardFlipped;
            }
        });


        // Note Part // get note from database
        final TextView mNote = (TextView) rootView.findViewById(R.id.medicine_note_textview);

        try {
            mNote.setText("Note: " + NoteLab.get(getActivity()).getNote(medicine.getGenericName()).getNote());
        }
        catch (Exception ex) {
            mNote.setText("Note: ");
        }

        // Edit Note Part
        final EditText mNoteEdit = (EditText) rootView.findViewById(R.id.medicine_note_edit_textview);

        Button mEditButton = (Button) rootView.findViewById(R.id.editBtn);

        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = mNoteEdit.getText().toString();//get what user input

                mNote.setText("Note: " + temp); //set it first

                NoteLab.get(getActivity()).check(medicine, temp);// go check the datebase if the note is old or new one.insert or update
            }
        });





        return rootView;
    }
}
