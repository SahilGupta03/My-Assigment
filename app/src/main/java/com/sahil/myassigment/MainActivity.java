package com.sahil.myassigment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ColorStateList def;
    TextView item1,item2,select;

    FrameLayout frameLayout;
    Button btn;
     Spinner ccp;
    private EditText phoneno;
    EditText vemail,vpassword;
    EditText edemail,edpassword,edname,edphone;
    TextView gotologin;
    CheckBox checkBox;
    ImageView google ,facebook;
    FirebaseAuth fAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gotologin=findViewById(R.id.gotologin);
        phoneno=findViewById(R.id.edphone);
        vemail=findViewById(R.id.vemail);
        vpassword=findViewById(R.id.vpassword);
        facebook=findViewById(R.id.facebook);
        edname=findViewById(R.id.edname);
        edemail=findViewById(R.id.edemail);
        edpassword=findViewById(R.id.edphone);
        checkBox=findViewById(R.id.check);


        frameLayout=findViewById(R.id.wrapper);
        FragmentManager frm=getSupportFragmentManager();
        FragmentTransaction frt=frm.beginTransaction();
        SigninFragment obj=new SigninFragment();
        frt.add(R.id.wrapper,obj);
        frt.addToBackStack(null);
        frt.commit();





        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);

        item1.setOnClickListener(this);
        item2.setOnClickListener(this);




        select=findViewById(R.id.select);
        def=item2.getTextColors();



    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.item1){
            select.animate().x(0).setDuration(100);
            item1.setTextColor(Color.WHITE);
            item2.setTextColor(def);


            if (getSupportFragmentManager().getFragments()!=null && getSupportFragmentManager().getFragments().size()>0){
                for (int i=0;i<getSupportFragmentManager().getFragments().size();i++){
                    Fragment frg=getSupportFragmentManager().getFragments().get(i);
                    if (frg!=null){
                        getSupportFragmentManager().beginTransaction().remove(frg).commit();
                    }
                }
            }
            FragmentManager frm=getSupportFragmentManager();
            FragmentTransaction frt=frm.beginTransaction();
            SigninFragment obj=new SigninFragment();
            frt.add(R.id.wrapper,obj);
            frt.addToBackStack(null);
            frt.commit();




        }else  if (view.getId()==R.id.item2){
            item2.setTextColor(Color.WHITE);
            item1.setTextColor(def);
            int size=item2.getWidth();
            select.animate().x(size).setDuration(100);

            if (getSupportFragmentManager().getFragments()!=null && getSupportFragmentManager().getFragments().size()>0){
                for (int i=0;i<getSupportFragmentManager().getFragments().size();i++){
                    Fragment frg=getSupportFragmentManager().getFragments().get(i);
                    if (frg!=null){
                        getSupportFragmentManager().beginTransaction().remove(frg).commit();
                    }
                }
            }
            FragmentManager frm=getSupportFragmentManager();
            FragmentTransaction frt=frm.beginTransaction();
            SignupFragment obj1=new SignupFragment();
            frt.add(R.id.wrapper,obj1);
            frt.addToBackStack(null);
            frt.commit();





        }
    }


}