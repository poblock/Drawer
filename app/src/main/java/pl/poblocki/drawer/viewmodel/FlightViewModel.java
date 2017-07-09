package pl.poblocki.drawer.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import pl.poblocki.drawer.R;
import pl.poblocki.drawer.model.Flight;

/**
 * Created by krzysztof.poblocki on 2017-06-12.
 */

public class FlightViewModel {
    public final String destination;
    public final String flight;
    public final String freighter;
    public int freighterImg;
    public final String time;
    public final String exp_time;
    public final String status;
    public final boolean currentDay;

    public FlightViewModel(Flight modelFlight) {
        this.destination = modelFlight.getDestination();
        this.flight = modelFlight.getFlight();
        this.freighter = modelFlight.getFreighter();
        this.time = modelFlight.getTime();
        this.exp_time = modelFlight.getExp_time();
        this.status = modelFlight.getStatus();
        this.currentDay = modelFlight.isCurrentDay();
        setFreighterImg();
    }

    public Flight toModel() {
        return new Flight(destination, flight, freighter, time, exp_time, status, currentDay);
    }

    public void setFreighterImg() {
        String airline = this.freighter;
        int img = -1;
        if(airline.equals("Wizzair")) {
            img = R.drawable.w6;
        } else if(airline.equals("Ryanair")) {
            img = R.drawable.fr;
        } else if(airline.equals("LOT")) {
            img = R.drawable.lo;
        } else if(airline.equals("Lufthansa")) {
            img = R.drawable.lh;
        }else if(airline.equals("Air Berlin")) {
            img = R.drawable.ab;
        }else if(airline.equals("Finnair")) {
            img = R.drawable.ay;
        }else if(airline.equals("KLM")) {
            img = R.drawable.kl;
        }else if(airline.equals("Norwegian")) {
            img = R.drawable.dy;
        }else if(airline.equals("SAS")) {
            img = R.drawable.sk;
        }else if(airline.equals("UIA")) {
            img = R.drawable.ps;
        }else if(airline.equals("Travel Service")) {
            img = R.drawable.travel;
        }else if(airline.equals("7 Islands")) {
            img = R.drawable.sevenislands;
        }else if(airline.equals("Blue Bird")) {
            img = R.drawable.bluebird;
        }else if(airline.equals("Ecco")) {
            img = R.drawable.ecco;
        }else if(airline.equals("Exim")) {
            img = R.drawable.exim;
        }else if(airline.equals("Grecos")) {
            img = R.drawable.grecos;
        }else if(airline.equals("Itaka")) {
            img = R.drawable.itaka;
        }else if(airline.equals("Matimpex")) {
            img = R.drawable.matimpex;
        }else if(airline.equals("Neckermann")) {
            img = R.drawable.neckermann;
        }else if(airline.equals("Rainbow")) {
            img = R.drawable.rainbow;
        }else if(airline.equals("Small Planet")) {
            img = R.drawable.p7;
        }else if(airline.equals("SunFun")) {
            img = R.drawable.sf;
        }else if(airline.equals("TUI")) {
            img = R.drawable.tui;
        }else if(airline.equals("Wezyr")) {
            img = R.drawable.wezyr;
        }else if(airline.equals("Wizz Tours")) {
            img = R.drawable.wizztours;
        }else if(airline.equals("Sprint Air")) {
            img = R.drawable.p8;
        }else if(airline.equals("AlMasria")) {
            img = R.drawable.uj;
        }else if(airline.equals("Corendon")) {
            img = R.drawable.xc;
        }else if(airline.equals("Adria Airways")) {
            img = R.drawable.jp;
        }else if(airline.equals("Enter Air")){
            img = R.drawable.e4;
        } else {
            img = R.drawable.airline;
        }
        this.freighterImg = img;
    }

    private int getId(Context context, String name) {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name.toLowerCase(), "drawable", context.getPackageName());
        return resourceId;
    }
}
