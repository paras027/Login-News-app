package com.codingstuff.customtablayout;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFragment extends Fragment {

   TextView name,email,pass,number,signin;
   Button button;
   CheckBox cc;
   FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
   DatabaseReference databaseReference;
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        name = view.findViewById(R.id.editNameSignUp);
        email = view.findViewById(R.id.editEmailSignUp);
        pass = view.findViewById(R.id.editPassSignUp);
        number = view.findViewById(R.id.editNumberSignUp);
        signin = view.findViewById(R.id.signInText);
        mAuth=FirebaseAuth.getInstance();
        cc=view.findViewById(R.id.checkBox);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment someFragment = new SignInFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.your_placeholderi, SignInFragment.class, null);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        button = view.findViewById(R.id.signUpBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = name.getText().toString();
                String femail = email.getText().toString();
                String fnumber = number.getText().toString();
                String fpass = pass.getText().toString();


                if (!fname.isEmpty()) {
                    name.setError(null);
                    if (!femail.isEmpty()) {
                        email.setError(null);
                            if(femail.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
                                if(!fnumber.isEmpty()){
                                    number.setError(null);
                                        if(fnumber.matches("\\d{10}")){
                                            if(!fpass.isEmpty()) {
                                                pass.setError(null);
                                                if (cc.isChecked()) {
                                                        firebaseDatabase=FirebaseDatabase.getInstance();
                                                        databaseReference=firebaseDatabase.getReference("datauser");

                                                    String fnames = name.getText().toString();
                                                    String femails = email.getText().toString();
                                                    String fnumbers = number.getText().toString();
                                                    String fpasss = pass.getText().toString();

                                                    Storingdata storingdata=new Storingdata(fnames,femails,fnumbers,fpasss);
                                                    databaseReference.child(fnames).setValue(storingdata);
                                                    Toast.makeText(getActivity().getApplicationContext(),"Registered Successfuly",Toast.LENGTH_SHORT).show();

                                                    mAuth.createUserWithEmailAndPassword(femail,fpasss).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            if(task.isSuccessful()){
                                                                Toast.makeText(getActivity().getApplicationContext(),"Registered",Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                                    Fragment someFragment = new SignInFragment();
                                                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                    transaction.replace(R.id.your_placeholderi, SignInFragment.class, null);
                                                    transaction.addToBackStack(null);
                                                    transaction.commit();

                                                } else {
                                                    Toast.makeText(getActivity().getApplicationContext(),"Accept terms and conditions to continue",Toast.LENGTH_SHORT).show();

                                                }
                                            }else{
                                                pass.setError("Enter Password");
                                            }
                                        }
                                        else{
                                            number.setError("Enter Valid Number");
                                        }

                                }
                                else{
                                    number.setError("Add number");
                                }
                            }else{
                                email.setError("Enter Valid Email ID");
                            }

                    } else {
                        email.setError("bhar kuch");
                    }
                } else {
                    name.setError("Name cannot be empty");
                }
            }
        });

        return view;
    }

}