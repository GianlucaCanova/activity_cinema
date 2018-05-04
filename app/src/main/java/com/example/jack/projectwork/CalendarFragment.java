package com.example.jack.projectwork;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import com.example.jack.projectwork.DescrizioneActivity;

/**
 * Created by jack on 13/04/18.
 */
    @SuppressLint("ValidFragment")
    public class CalendarFragment extends Fragment {


        TextView interrogazione;
        TextView giorno;
        TextView numGiorno;

        @SuppressLint("ValidFragment")
        public CalendarFragment(View v, View v2, View v3)
        {
            interrogazione = (TextView) v;
            giorno=(TextView) v2;
            numGiorno=(TextView) v3;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View vView = inflater.inflate(R.layout.calendarl, null);
            final CalendarView calendar = vView.findViewById(R.id.calendarView);

            calendar.startAnimation(AnimationUtils.loadAnimation(getContext(),
                    R.anim.slide_up_anim));
            //String data_corr= dat.getDate()+ “/” + dat.getMonth() + “/” + dat.getFullYear()
            //SETTA PRIMO GIORNO
            Date data = new Date();
            long mills = data.getTime();
            calendar.setMinDate(mills);
            // SETTA ULTIMO GIORNO
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date d = f.parse("25/05/2018");
                long milliseconds = d.getTime();
                calendar.setMaxDate(milliseconds);

            } catch (ParseException e) {
                Log.i("CalendarFragment:", "Data sbagliata");
            }


      //      SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        //    String formatedDate = newFormat.format(((int) calendar.getDate()));
            //text.setText(formatedDate);
            calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    interrogazione.setVisibility(View.INVISIBLE);
                    numGiorno.setText("" + dayOfMonth);
                    //int dayOfWeek=Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE");         // SI TRATTA DI UN GIORNO DELLA SETTIMANA
                    SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");  // RECUPERARE DATA CHE SIA D/M/A
                    Date d=new Date(year,(month+1),dayOfMonth);
                    try {
                        d = sdf2.parse(dayOfMonth+"/"+(month+1)+"/"+year);
                    } catch (ParseException e) {
                        Log.i("CalendarFragment: ","Errore parsing data");
                    }
                    String dayOfWeek = sdf.format(d);
                    Log.i("CalendarFragment","year: "+year+", month: "+(month+1)+", day: "+dayOfMonth+", dayOfWeek: "+dayOfWeek);
                    /*DayOfWeek dayOfWeek=LocalDate.parse( year+"-"+month+"-"+dayOfMonth ).getDayOfWeek();*/
                    dayOfWeek=dayOfWeek.toUpperCase();

                    /*SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formatedDate = newFormat.format(new Date(year,month,dayOfMonth));
                    DayOfWeek dayOfWeek=new DayOfWeek(formatedDate);*/
                    String day="";
                    switch(dayOfWeek){
                        case "MONDAY":
                            day="LUN";
                            break;
                        case "TUESDAY":
                            day="MAR";
                            break;
                        case "WEDNESDAY":
                            day="MER";
                            break;
                        case "THURSDAY":
                            day="GIO";
                            break;
                        case "FRIDAY":
                            day="VEN";
                            break;
                        case "SATURDAY":
                            day="SAB";
                            break;
                        case "SUNDAY":
                            day="DOM";
                            break;
                    }
                    giorno.setText(day);
                    giorno.setVisibility(View.VISIBLE);
                    numGiorno.setVisibility(View.VISIBLE);


                    //ANIMAZIONI APERTURA E CHIUSURA
                    Animation an = AnimationUtils.loadAnimation(getContext(),
                            R.anim.slide_down_anim);

                    an.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            ((DescrizioneActivity)getActivity()).fragmentDesc();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    view.startAnimation(an);

                }
            });
            return vView;
        }
    }
