package com.example.jack.projectwork;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class DescrizioneActivity extends AppCompatActivity {

    FragmentManager mManager;
    LinearLayout mainL;
    FrameLayout fLayout;
    Fragment_alt vFragment=new Fragment_alt();
    // FragmentDesc vFragment=new FragmentDesc();
    boolean calendario=false;
    final String GIORNO="giorno";
    final String NUM_GIORNO="num_giorno";
    final String CALENDARIO="calendario";
    TextView vGiorno,vNumGiorno, vInterrogativo;
    FragmentTransaction vTransaction;
    Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mManager =getSupportFragmentManager();
        //setLayout();
        setContentView(R.layout.layout_alternativo);
        vTransaction= mManager.beginTransaction();
        vGiorno=findViewById(R.id.giorno);
        vNumGiorno=findViewById(R.id.num_giorno);
        vInterrogativo=findViewById(R.id.txt_data);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            vTransaction.replace(R.id.fragment_zone ,new Fragment_alt());
        }
        else{

            vTransaction.replace(R.id.fragment_zone ,new FragmentDescHor());
        }
        vTransaction.commit();

        fragment=null;
        if (savedInstanceState != null) {
            String savedText = savedInstanceState.getString(GIORNO);
            Log.i("State: ",""+savedText);
            vGiorno.setText(savedText);
            vGiorno.setVisibility(View.VISIBLE);
            String savedText2 = savedInstanceState.getString(NUM_GIORNO);
            vNumGiorno.setText(savedText2);
            vNumGiorno.setVisibility(View.VISIBLE);
            Log.i("State: ",""+savedText2);
            if(savedText.length()>0 || savedText2.length()>0)
                vInterrogativo.setVisibility(View.INVISIBLE);
            boolean savedText3 = savedInstanceState.getBoolean(CALENDARIO);
            if(savedText3){
                vTransaction= mManager.beginTransaction();
                vTransaction.replace(R.id.fragment_zone ,fragment=new CalendarFragment(vInterrogativo, vGiorno, vNumGiorno));
                vTransaction.commit();
                fragment.setRetainInstance(true);
                calendario=true;
            }
        }
        else{
            Log.i("State: ","null");
        }
        /*fragmentDesc();
        FragmentTransaction vTransaction= mManager.beginTransaction();
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.layout_alternativo);

            vTransaction.replace(R.id.fragment_zone ,new Fragment_alt());
        } else {
            setContentView(R.layout.layout_alternativo);
            vTransaction.replace(R.id.fragment_zone ,new FragmentDescHor());
        }


        vTransaction.replace(R.id.fragment_zone ,vFragment);

        vTransaction.commit();*/

        //mainL=findViewById(R.id.mainL);
        //LinearLayout fragmentZone= findViewById(R.id.fragment_zone);
        ImageView mCalendar=findViewById(R.id.img_calendario);
        /*RelativeLayout relativeL= findViewById(R.id.relative_img);
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) relativeL.getLayoutParams();
        params.height=params.width;
        relativeL.setLayoutParams(params);*/
        /*DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels/100*10;
        int width = height;
        RelativeLayout.LayoutParams params=(RelativeLayout.LayoutParams) mCalendar.getLayoutParams();
        params.height=height;
        params.width=width;
        mCalendar.setLayoutParams(params);*/

        /*RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mCalendar.getLayoutParams();
        params.width = params.height;
        mCalendar.setLayoutParams(params);*/
        mCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(calendario==false) {
                    CalendarFragment fCalendar = new CalendarFragment(findViewById(R.id.txt_data), findViewById(R.id.giorno), findViewById(R.id.num_giorno));
                    fragment = fCalendar;
                    vTransaction = mManager.beginTransaction();
                    vTransaction.replace(R.id.fragment_zone, fCalendar);

                    vTransaction.commit();
                    fragment.setRetainInstance(true); // salva stato fragment
                    calendario = true;                // controllare imageview per aprirla o chiuderla ( se lo premi true se no false e lo chiude)
                }
                else{
                    android.widget.CalendarView calendar = findViewById(R.id.calendarView);
                      calendar.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_down_anim));

                    Animation an = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.slide_down_anim);

                    an.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                           fragmentDesc();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }

                    });
                    fragment.getView().startAnimation(an);


                }

            }
        });

    }

    /*@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setLayout();
    }*/

    @Override
    public void onBackPressed() {            // METODO DELL'ACTIVITY
        if(calendario){
            fragmentDesc();                 // RICHIAMA FRAGMENT

        }
        else{
            super.onBackPressed();         // TORNA ALL'INIZIO
        }


    }



    public void fragmentDesc(){
        FragmentTransaction vTransaction= mManager.beginTransaction();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {                // QUANDO è verticale mette un fragm quando è orizzontale mette un'altro

            vTransaction.replace(R.id.fragment_zone ,new Fragment_alt());
        }
        else{

            vTransaction.replace(R.id.fragment_zone ,new FragmentDescHor());
        }
        vTransaction.commit();
        fragment=null;
        calendario=false;
    }



    @Override
    protected void onSaveInstanceState (Bundle outState) {
        String s1="", s2="";
        s1=vGiorno.getText().toString();
        s2=vNumGiorno.getText().toString();

            outState.putString(GIORNO, s1);
            Log.i("onSaveInstanceState: ", outState.getString(GIORNO));
            outState.putString(NUM_GIORNO, s2);
            Log.i("onSaveInstanceState: ", outState.getString(NUM_GIORNO));
            if(fragment!=null)
                outState.putBoolean(CALENDARIO, true);
            else
                outState.putBoolean(CALENDARIO, false);
            //fragmentDesc();
            super.onSaveInstanceState(outState);
    }






}

