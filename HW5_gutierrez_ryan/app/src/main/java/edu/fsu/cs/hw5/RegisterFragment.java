package edu.fsu.cs.hw5;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

public class RegisterFragment extends Fragment{

    private OnRegisterFragmentInteractionListener mListener;

    private View mRootView;

    private EditText empid;
    private EditText name;
    private EditText email;
    private RadioGroup gender;
    private RadioButton male;
    private RadioButton female;
    private EditText aCode;
    private EditText cCode;
    private Spinner dept;
    private CheckBox agToTerm;
    private Button reset;
    private Button register;

    private Cursor mCursor;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_register, container, false);
        // TODO: Setup UI
        empid = (EditText) mRootView.findViewById(R.id.editText_empid);
        name = (EditText) mRootView.findViewById(R.id.editText_name);
        email = (EditText) mRootView.findViewById(R.id.editText_email);
        gender = (RadioGroup) mRootView.findViewById(R.id.radioGroup_gender);
        male = (RadioButton) mRootView.findViewById(R.id.radioButton_male);         male.setId(0);
        female = (RadioButton) mRootView.findViewById(R.id.radioButton_female);     female.setId(1);
        aCode = (EditText) mRootView.findViewById(R.id.editText_aCode);
        cCode = (EditText) mRootView.findViewById(R.id.editText_cCode);
        dept = (Spinner) mRootView.findViewById(R.id.spinner_dept);
        agToTerm = (CheckBox) mRootView.findViewById(R.id.checkBox_agToTerm);
        reset = (Button) mRootView.findViewById(R.id.button_reset);
        register = (Button) mRootView.findViewById(R.id.button_register);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onReset(view);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister(view);
            }
        });

        return mRootView;
    }

    public void onReset(View v) {
        empid.setText(null);
        name.setText(null);
        email.setText(null);
        gender.clearCheck();
        aCode.setText(null);
        cCode.setText(null);
        agToTerm.setChecked(false);

        // *** prints all elements in database for testing purposes
        mCursor = getActivity().getContentResolver().query(MyContentProvider.CONTENT_URI,
                null, null, null, null);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                Log.i("RegisterFragment", mCursor.getString(mCursor.getColumnIndex("Empid")));
                Log.i("RegisterFragment", mCursor.getString(mCursor.getColumnIndex("Name")));
                Log.i("RegisterFragment", mCursor.getString(mCursor.getColumnIndex("Email")));
                Log.i("RegisterFragment", mCursor.getString(mCursor.getColumnIndex("Gender")));
                Log.i("RegisterFragment", mCursor.getString(mCursor.getColumnIndex("AccessCode")));
                Log.i("RegisterFragment", mCursor.getString(mCursor.getColumnIndex("Department")));
            }
        }
    }

    public void onRegister(View v) {
        int errorCount = 0;
        if (empid.length() == 0) {
            //Toast.makeText(getApplicationContext(),"Missing Employee ID",Toast.LENGTH_SHORT).show();
            empid.setError("Missing Employee ID");
            errorCount++;
        }
        if (name.length() == 0) {
            name.setError("Missing Name");
            errorCount++;
        }
        if (email.length() == 0) {
            email.setError("Missing Email");
            errorCount++;
        }
        if (gender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getActivity(),"You must select a gender",Toast.LENGTH_SHORT).show();
            errorCount++;
        }
        if (aCode.length() == 0) {
            aCode.setError("Missing Access Code");
            errorCount++;
        }
        if (cCode.length() == 0) {
            cCode.setError("Missing Confirm Code");
            errorCount++;
        }
        if (agToTerm.isChecked() == false) {
            Toast.makeText(getActivity(),"You must agree to the terms",Toast.LENGTH_SHORT).show();
            errorCount++;
        }

        // if the error count is zero, then there were no previous errors and we now want to check
        //  that the access code and confirm code are matching
        if (errorCount == 0) {
            if (!aCode.getText().toString().equals(cCode.getText().toString())) {
                Toast.makeText(getActivity(),"Both access codes must be the same",Toast.LENGTH_SHORT).show();
                errorCount++;
            }
        }

        // if the error count is still zero, then there were no previous errors and now must check
        //  that the database does not already have the same empid
        if (errorCount == 0) {
            //Resources temp = getResources();
            //if ( query the empid )
            mCursor = getActivity().getContentResolver().query(MyContentProvider.CONTENT_URI,
                null, null, null, null);
            if (mCursor != null) {
                while (mCursor.moveToNext()) {
                    String currentID = mCursor.getString(mCursor.getColumnIndex("Empid"));
                    Log.i("RegisterFragment", currentID);
                    if (currentID.equals(empid.getText().toString().trim())) {
                        Toast.makeText(getActivity(), "Employee ID already exists within the database", Toast.LENGTH_SHORT).show();
                        errorCount++;
                    }
                }
            }
        }

        // there are no errors and we are ready to populate the database with the new data
        if (errorCount == 0) {
            Uri mNewUri;
            ContentValues mNewValues = new ContentValues();
            mNewValues.put(MyContentProvider.COLUMN_EMPID, empid.getText().toString().trim());
            mNewValues.put(MyContentProvider.COLUMN_NAME, name.getText().toString().trim());
            mNewValues.put(MyContentProvider.COLUMN_EMAIL, email.getText().toString().trim());
            mNewValues.put(MyContentProvider.COLUMN_GENDER, gender.getCheckedRadioButtonId() == 0 ? "Male" : "Female");
            mNewValues.put(MyContentProvider.COLUMN_ACCESS_CODE, aCode.getText().toString().trim());
            mNewValues.put(MyContentProvider.COLUMN_DEPARTMENT, dept.getSelectedItem().toString().trim());

            mNewUri = getActivity().getContentResolver().insert(MyContentProvider.CONTENT_URI, mNewValues);
            mNewValues.clear();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragmentInteractionListener) {
            mListener = (OnRegisterFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegisterFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnRegisterFragmentInteractionListener {
        void onSubmit(ContentValues values);
    }

}
