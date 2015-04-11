package com.clublanacion;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.clublanacion.global.Constants;
import com.clublanacion.global.Functions;
import com.clublanacion.global.GlobalFont;
import com.clublanacion.service.BlockTouchScreenService;
import com.clublanacion.util.PreferencesHelper;
import android.view.*;
import android.graphics.*;
public class MainActivity extends Activity implements OnClickListener,
        OnCheckedChangeListener {
    private CheckBox chkGastronomia,chkEntretenimiento,chkTurismo,chkCuidadoPersonal,chkModa,chkMasCategorias;
    private Button btnClasica,btnPremium;
    private SeekBar seekPorcentaje;
    private TextView txtDescuentos, txtNoticias;
    private static Context _context;
    public static Context getContext(){
        return _context;
    }
    private static void setContext(Context _Context){
        _context = _Context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.setContext(this);
        setContentView(R.layout.settings);
        overrideFonts(this, findViewById(android.R.id.content));
        initView();
        loadSetting();
    }
    private void overrideFonts(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;

                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(context, child);
                }

            } else if (v instanceof TextView ) {
                ((TextView) v).setTypeface(Typeface.createFromAsset(context.getAssets(), "HelveticaNeueLTPro-Lt.ttf"));
            }

        } catch (Exception e) {
        }
    }
    private void initView() {
        chkGastronomia = (CheckBox) getWindow().findViewById(
                R.id.chkGastronomia);
        chkEntretenimiento = (CheckBox) getWindow().findViewById(
                R.id.chkEntretenimiento);
        chkTurismo = (CheckBox) getWindow().findViewById(
                R.id.chkTurismo);
        chkCuidadoPersonal = (CheckBox) getWindow().findViewById(
                R.id.chkCuidadoPersonal);
        chkModa = (CheckBox) getWindow().findViewById(
                R.id.chkModa);
        chkMasCategorias = (CheckBox) getWindow().findViewById(
                R.id.chkMasCategorias);
        btnClasica = (Button) getWindow().findViewById(
                R.id.btnClasica);
        btnPremium = (Button) getWindow().findViewById(
                R.id.btnPremium);
        seekPorcentaje = (SeekBar) getWindow().findViewById(
                R.id.seekPorcentaje);
        txtDescuentos = (TextView) getWindow().findViewById(
                R.id.txtDescuentos);
        txtNoticias = (TextView) getWindow().findViewById(
                R.id.txtNoticias);

        chkGastronomia.setOnCheckedChangeListener(this);
        chkEntretenimiento.setOnCheckedChangeListener(this);
        chkTurismo.setOnCheckedChangeListener(this);
        chkCuidadoPersonal.setOnCheckedChangeListener(this);
        chkModa.setOnCheckedChangeListener(this);
        chkMasCategorias.setOnCheckedChangeListener(this);
        btnClasica.setOnClickListener(this);
        btnPremium.setOnClickListener(this);

        seekPorcentaje.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                txtDescuentos.setText(String.valueOf(progresValue)+"%");
                txtNoticias.setText(String.valueOf(100-progresValue)+"%");
                getHelper().setPorcentaje(progresValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Functions.logI("MainActivity onResume");
        BlockTouchScreenService.startService(this);

    }
    private void loadSetting() {
        txtDescuentos.setText(String.valueOf(getHelper().getPorcentaje()) + "%");
        txtNoticias.setText(String.valueOf(100-getHelper().getPorcentaje())+"%");
        chkGastronomia.setChecked(getHelper().getCheckGastronomia());
        chkTurismo.setChecked(getHelper().getCheckTurismo());
        chkEntretenimiento.setChecked(getHelper().getCheckEntretenimiento());
        chkModa.setChecked(getHelper().getCheckModa());
        chkCuidadoPersonal.setChecked(getHelper().getCheckCuidadoPersonal());
        chkMasCategorias.setChecked(getHelper().getCheckMasCategorias());

        if(getHelper().getTarjeta() == Constants.TARJETA_CLASICA){
            btnClasica.getBackground().setColorFilter(0xFF3189c9, PorterDuff.Mode.MULTIPLY);
            btnClasica.setTextColor(0xFFFFFFFF);
            btnClasica.invalidate();

            btnPremium.getBackground().setColorFilter(0xFFF0F0F0, PorterDuff.Mode.MULTIPLY);
            btnPremium.setTextColor(0xFF000000);
            btnPremium.invalidate();
        }else{
            btnPremium.getBackground().setColorFilter(0xFF3189c9, PorterDuff.Mode.MULTIPLY);
            btnPremium.setTextColor(0xFFFFFFFF);
            btnPremium.invalidate();

            btnClasica.getBackground().setColorFilter(0xFFF0F0F0, PorterDuff.Mode.MULTIPLY);
            btnClasica.setTextColor(0xFF000000);
            btnClasica.invalidate();
        }
    }

    private PreferencesHelper helper;

    private PreferencesHelper getHelper() {
        if (helper == null)
            helper = new PreferencesHelper(this);
        return helper;
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (compoundButton.getId() ==  chkGastronomia.getId()) {
            getHelper().setCheckGastronomia(b);
        }else if(compoundButton.getId() == chkEntretenimiento.getId()){
            getHelper().setCheckEntretenimiento(b);
        }else if(compoundButton.getId() == chkTurismo.getId()){
            getHelper().setCheckTurismo(b);
        }else if(compoundButton.getId() == chkCuidadoPersonal.getId()){
            getHelper().setCheckCuidadoPersonal(b);
        }else if(compoundButton.getId() == chkModa.getId()){
            getHelper().setCheckModa(b);
        }else if(compoundButton.getId() == chkMasCategorias.getId()){
            getHelper().setCheckMasCategorias(b);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnClasica) {
            getHelper().setTarjeta(Constants.TARJETA_CLASICA);
            btnClasica.getBackground().setColorFilter(0xFF3189c9, PorterDuff.Mode.MULTIPLY);
            btnClasica.setTextColor(0xFFFFFFFF);
            btnClasica.invalidate();

            btnPremium.getBackground().setColorFilter(0xFFF0F0F0, PorterDuff.Mode.MULTIPLY);
            btnPremium.setTextColor(0xFF000000);
            btnPremium.invalidate();
        }else{
            getHelper().setTarjeta(Constants.TARJETA_PREMIUM);
            btnPremium.getBackground().setColorFilter(0xFF3189c9, PorterDuff.Mode.MULTIPLY);
            btnPremium.setTextColor(0xFFFFFFFF);
            btnPremium.invalidate();

            btnClasica.getBackground().setColorFilter(0xFFF0F0F0, PorterDuff.Mode.MULTIPLY);
            btnClasica.setTextColor(0xFF000000);
            btnClasica.invalidate();
        }

    }
}
