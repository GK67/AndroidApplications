package com.example.gk.dictionary;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import java.util.List;


//read
public class MedicineListFragment extends Fragment {
    private MedicineAdapter medicineAdapter;
    RecyclerView medicineListRecyclerView;
    public MedicineListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medicine_list, container, false);


        // set up  recycler view
        // set layout manager to linear layout manager
        medicineListRecyclerView = (RecyclerView) view;
        medicineListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // update recycler view
        updateUI();

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        // get a medicine lab and pass it to an adapter
        // set an adapter to recycler view
        MedicineLab medicineLab = MedicineLab.get(getActivity());
        List<Medicine> medicines = medicineLab.getMedicines();
        medicineAdapter = new MedicineAdapter(medicines);
        medicineListRecyclerView.setAdapter(medicineAdapter);
    }
    private class MedicineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView idTextView;
        private TextView nameTextView;
        private Medicine medicine;

        public MedicineHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);  //set onclick listener to the override method
            // link variables to widgets
            idTextView = (TextView) itemView.findViewById(R.id.medicine_id);
            nameTextView = (TextView) itemView.findViewById(R.id.medicine_name);
        }

        public void bindMedicine(Medicine medicine) {
            // get a medicine from an argument and set a medicine id and name to text views
            this.medicine = medicine;
            idTextView.setText(this.medicine.getGenericName());
            nameTextView.setText(this.medicine.getBrandName());
        }

        @Override
        public void onClick(View v) {
            // show toast notifying a view holder is clicked
            Toast.makeText(getContext(), medicine.getGenericName() + " Clicked", Toast.LENGTH_SHORT).show();

            //Intent i = MedicinePagerActivity.newIntent(getContext(), this.medicine.getGenericName());
            //Intent i = new Intent(getContext(), CardActivity.class);
            Intent i = CardActivity.newIntent(getContext(), this.medicine.getGenericName());
            startActivity(i);
        }
    }
    private class MedicineAdapter extends RecyclerView.Adapter<MedicineHolder> {
        private List<Medicine> medicines;

        public MedicineAdapter(List<Medicine> medicines) {
            this.medicines = medicines;
        }

        @Override
        public MedicineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.fragment_medicine, parent, false);
            return new MedicineHolder(view);
        }
        @Override
        public void onBindViewHolder(MedicineHolder holder, int position) {
            Medicine medicine = medicines.get(position);
            // bind a medicine to a view holder
            holder.bindMedicine(medicine);
        }
        @Override
        public int getItemCount() {
            return medicines.size();
        }
    }
}
