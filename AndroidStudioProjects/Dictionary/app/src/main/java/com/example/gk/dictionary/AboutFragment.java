package com.example.gk.dictionary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFragment extends Fragment {
    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView tvAppDescription = (TextView) view.findViewById(R.id.about_us_app_description);
        tvAppDescription.setText("PharmTech Review app is to function as a study guide for BHCC PharmTech Students " +
                "learning the most commonly prescribed drugs. The app will initially provide a way to present, review," +
                " and practice the top 200 most prescribed drugs, offering users various ways to categorize and study " +
                "these drugs. Alongside generic names, the app will feature information such as Brand name, Purpose, " +
                "DEA Schedule, Special Concerns, Drug Category, and Study Topic so that users have an easily accessible" +
                " and one stop location to all the information they need. Most notably, the app will feature playback audio" +
                " for each generic and brand drug name, to help students retain what they study, and for foreign speaking " +
                "users it will help them achieve pronunciation and recognition of these medical terms.\n");

        TextView tvClassInfo = (TextView) view.findViewById(R.id.about_us_class_information);
        tvClassInfo.setText("PharmTech app is developed as a group project in CIT 243 T1, Android Development " +
                "for Java Programmers (FALL 2016), at Bunker Hill Community College");

        TextView tvClassInstructor = (TextView) view.findViewById(R.id.about_us_class_instructor);
        tvClassInstructor.setText("Instructor: \nLiz Miller, Richard Yost");

        TextView tvDeveloper = (TextView) view.findViewById(R.id.about_us_developer);
        tvDeveloper.setText("Developers: \n" +
                "Karlisha, " +
                "Prakrit, " +
                "Shipeng, " +
                "Shradha ");


        TextView tvClassList = (TextView) view.findViewById(R.id.about_us_class_list);
        tvClassList.setText("Class list:\n\n" +
                "Saadia, " +
                "Achouham, " +
                "Ruchelly P., " +
                "Almeida, " +
                "Oleksii, " +
                "Butakov, " +
                "Lounis, " +
                "Idir, " +
                "Evan Y., " +
                "Liu, " +
                "Shradha, " +
                "Luintel, " +
                "Karlisha, " +
                "McQuiter, " +
                "Thomas A., " +
                "Newell, " +
                "Alexandre, " +
                "Pereira, " +
                "David C., " +
                "Powell, " +
                "James, " +
                "Sacchetti, " +
                "Prakrit, " +
                "Saetang, " +
                "Fikir G., " +
                "Yilma, " +
                "Shipeng, " +
                "Yu, " +
                "Edgard, " +
                "Zafack");

        TextView tvRequirement = (TextView) view.findViewById(R.id.about_us_requirement_specification);
        tvRequirement.setText("Requirement Specification by:\n" +
                "Abdulfatah, Ruilin, Oghosa, Yelsin");

        TextView tvAboutDetail = (TextView) view.findViewById(R.id.about_us_credits);
        tvAboutDetail.setText("Credits:\n" +
                "\n" +
                "Audio files:\n" +
                "http://clincalc.com/\n\n" +
                "Icons:\n" +
                "http://www.freepik.com\n" +
                "http://www.flaticon.com/\n" +
                "https://material.io/icons/\n" +
                "https://www.shareicon.net/\n");

        return view;
    }

}

