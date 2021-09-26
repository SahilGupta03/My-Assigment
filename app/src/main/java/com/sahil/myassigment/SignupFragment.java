package com.sahil.myassigment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupFragment extends Fragment {

   EditText eddemail,eddpassword,eddname,eddphone;
    CheckBox checkBox;

    ProgressBar progressBar;
    FirebaseAuth fAuth;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_signup, container, false);
     Button btn_register= (Button)view.findViewById(R.id.btn_register);
     EditText eddemail = (EditText) view.findViewById(R.id.edemail);
     EditText eddpassword= (EditText) view.findViewById(R.id.edpassword);
     EditText eddname= (EditText) view.findViewById(R.id.edname);
     EditText eddphone= (EditText) view.findViewById(R.id.edphone);
     CheckBox checkBox= (CheckBox) view.findViewById(R.id.check);

        fAuth=FirebaseAuth.getInstance();
//        if (fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getActivity(),MainActivity.class));
//
//        }
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=eddname.getText().toString().trim();
                String email=eddemail.getText().toString().trim();
                String password=eddpassword.getText().toString().trim();
                String phone=eddphone.getText().toString().trim();
                String check=checkBox.toString().trim();
                if (TextUtils.isEmpty(name)){
                    eddname.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    eddemail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    eddpassword.setError("password is required");
                    return;
                }
                if (password.length()<6){
                    eddpassword.setError("Password Must be >=6 character ");
                    return;
                }
                if (TextUtils.isEmpty(phone)){
                    eddphone.setError("Phone is required");
                    return;
                }
                if (!checkBox.isChecked()){
                    checkBox.setError("Click on box");
                }


//                progressBar.setVisibility(View.VISIBLE)



                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Sucessfull", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getActivity(),HomeActivity.class);
                            startActivity(intent);

//                            frameLayout=findViewById(R.id.wrapper);
//                            FragmentManager frm=getSupportFragmentManager();
//                            FragmentTransaction frt=frm.beginTransaction();
//                            SigninFragment obj=new SigninFragment();
//                            frt.add(R.id.wrapper,obj);
//                            frt.addToBackStack(null);
//                            frt.commit();

                        }else {
                            Toast.makeText(getActivity(), "Error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        return view;


    }
}