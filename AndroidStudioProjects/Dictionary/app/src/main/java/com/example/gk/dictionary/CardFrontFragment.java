package com.example.gk.dictionary;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


//read
public class CardFrontFragment extends Fragment {
    private static final String ARG_MEDICINE_ID = "arg: medicine id";
    private Medicine medicine;

    public static CardFrontFragment newInstance(Medicine medicine) {
        Bundle args = new Bundle();  // a new bundle to hold arguments
        args.putSerializable(ARG_MEDICINE_ID, medicine);  // put a medicine id as an argument
        CardFrontFragment fragment = new CardFrontFragment();  // create a new detail fragment
        fragment.setArguments(args);  // put arguments to a fragment
        return fragment;
    }

    public CardFrontFragment() { }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_card_front, container, false);
        TextView mDrugName = (TextView) rootView.findViewById(R.id.medicine_name_textview);
        TextView mFlip = (TextView) rootView.findViewById(R.id.flip_text_view_front);
        mDrugName.setText(medicine.getGenericName());
        mFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //flipCard();
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                                R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                        .replace(R.id.container, CardBackFragment.newInstance(medicine))
                        .commit();

                //cardFlipped = !cardFlipped;
            }
        });



      //  ImageView playAudioButton = (ImageView) rootView.findViewById(R.id.play_audio_button_back);

           Button playAudioButton = (Button) rootView.findViewById(R.id.audio);
        try {
            String fileName = medicine.getBrandName();
            fileName = fileName.substring(0, fileName.length() - 1);
            fileName = fileName.toLowerCase();
            Log.i("Test", fileName);
            int resID = getResources().getIdentifier(fileName, "raw", getActivity().getPackageName());
            final MediaPlayer myMediaPlayer = MediaPlayer.create(getActivity(), resID);
            if (myMediaPlayer.getDuration() == 0) {
                playAudioButton.setVisibility(View.INVISIBLE);
            }
        }
        catch (Exception ex) {
            playAudioButton.setVisibility(View.INVISIBLE);
        }


        playAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String fileName = medicine.getBrandName();
                    fileName = fileName.substring(0, fileName.length() - 1);
                    fileName = fileName.toLowerCase();
                    Log.i("test", fileName);

                    if (fileName.contains("/")) {
                        StringBuilder stringBuilder = new StringBuilder(fileName);
                        stringBuilder.deleteCharAt(fileName.indexOf('/'));
                        fileName = stringBuilder.toString();
                    }
                    int resID = getResources().getIdentifier(fileName, "raw", getActivity().getPackageName());
                    final MediaPlayer myMediaPlayer = MediaPlayer.create(getActivity(), resID);
                    myMediaPlayer.start();
                }
                catch (Exception ex) {
                    Toast.makeText(getActivity(), "No audio file for this medicine", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rootView;
    }
}
