package com.example.gk.dictionary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//https://stuff.mit.edu/afs/sipb/project/android/docs/training/animation/cardflip.html
//read
public class CardContainerFragment extends Fragment {
    private static final String ARG_MEDICINE_ID = "arg: medicine id";
    private static boolean cardFlipped = false;
    private Medicine medicine;

    public static CardContainerFragment newInstance(Medicine medicine) {
        Bundle args = new Bundle();  // a new bundle to hold arguments
        args.putSerializable(ARG_MEDICINE_ID, medicine);  // put a medicine id as an argument
        CardContainerFragment fragment = new CardContainerFragment();  // create a new detail fragment
        fragment.setArguments(args);  // put arguments to a fragment
        return fragment;
    }

    /*
    public CardContainerFragment() {
        setHasOptionsMenu(true);
    }
    */

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
        View rootView = inflater.inflate(R.layout.fragment_card_container, container, false);

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.container, CardFrontFragment.newInstance(medicine))
                .commit();

        return rootView;
    }
}
