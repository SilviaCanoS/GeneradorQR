package com.example.generadorqr_00;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textViewVersion, textViewBinario;
    RadioGroup radioGroup;
    RadioButton radioL7, radioM15, radioQ25, radioH30;
    TableLayout tabla;

    int errorCorrection = 7, version, tamanio, tamanioIndicador, bitsNecesarios, bloquesCorreccion, bloquesGrupo1,
            codewordsGrupo1, bloquesGrupo2, codewordsGrupo2, bitsExtra;
    int[] alignmentPattern;
    String cifrado, versionInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textViewVersion = findViewById(R.id.textViewVersion);
        //textViewBinario = findViewById(R.id.textViewBinario);
        radioGroup = findViewById(R.id.radioGroup);
        radioL7 = findViewById(R.id.radioL7);
        radioM15 = findViewById(R.id.radioM15);
        radioQ25 = findViewById(R.id.radioQ25);
        radioH30 = findViewById(R.id.radioH30);
        tabla = findViewById(R.id.tableLayoutQR);

        //Seleccionar el nivel de error
        radioL7.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i)
                {
                    case R.id.radioL7: errorCorrection = 7; break;
                    case R.id.radioM15: errorCorrection = 15; break;
                    case R.id.radioQ25: errorCorrection = 25; break;
                    case R.id.radioH30: errorCorrection = 30; break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabla.removeAllViews();

                version = 1;
                tamanio = 0;
                String text = "HELLO WORLD";
                if(editText.getText().toString().isEmpty()) editText.setText("HELLO WORLD"); //Control de error
                else text = editText.getText().toString();
                String binario = "";

                //Analisis de datos: numerico o alfanumerico
                if(text.matches("^[0-9]*$")) //Si es una cadena solo numerica
                {
                    cifrado = "0001"; //Agregar el indicador de modo
                    if(text.length()%3 == 1) text += "  "; //Control de errores
                    else if(text.length()%3 == 2) text += " ";
                    //Dividir cada 3 caracteres y convertir a binario
                    for(int i = 0; i < text.length(); i += 3)
                    {
                        int decimal = Integer.parseInt(text.substring(i, i+3).trim()); //Convertir a decimal
                        binario += Integer.toBinaryString(decimal); //Convertir a binario
                    }

                    switch (errorCorrection) //Determinar la versión más pequeña posible
                    {
                        case 7:
                            if(text.length() <= 41) { }
                            else if(text.length() > 41 && text.length() <= 77) version = 2;
                            else if(text.length() > 77 && text.length() <= 127) version = 3;
                            else if(text.length() > 127 && text.length() <= 187) version = 4;
                            else if(text.length() > 187 && text.length() <= 255) version = 5;
                            else if(text.length() > 255 && text.length() <= 322) version = 6;
                            else if(text.length() > 322 && text.length() <= 370) version = 7;
                            else if(text.length() > 370 && text.length() <= 461) version = 8;
                            else if(text.length() > 461 && text.length() <= 552) version = 9;
                            else if(text.length() > 552 && text.length() <= 652) version = 10;
                            else if(text.length() > 652 && text.length() <= 772) version = 11;
                            else if(text.length() > 772 && text.length() <= 883) version = 12;
                            else if(text.length() > 883 && text.length() <= 1022) version = 13;
                            else if(text.length() > 1022 && text.length() <= 1101) version = 14;
                            else if(text.length() > 1101 && text.length() <= 1250) version = 15;
                            else if(text.length() > 1250 && text.length() <= 1408) version = 16;
                            else if(text.length() > 1408 && text.length() <= 1548) version = 17;
                            else if(text.length() > 1548 && text.length() <= 1725) version = 18;
                            else if(text.length() > 1725 && text.length() <= 1903) version = 19;
                            else if(text.length() > 1903 && text.length() <= 2061) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;

                        case 15:
                            if(text.length() <= 34) { }
                            else if(text.length() > 34 && text.length() <= 63) version = 2;
                            else if(text.length() > 63 && text.length() <= 101) version = 3;
                            else if(text.length() > 101 && text.length() <= 149) version = 4;
                            else if(text.length() > 149 && text.length() <= 202) version = 5;
                            else if(text.length() > 202 && text.length() <= 255) version = 6;
                            else if(text.length() > 255 && text.length() <= 293) version = 7;
                            else if(text.length() > 293 && text.length() <= 365) version = 8;
                            else if(text.length() > 365 && text.length() <= 432) version = 9;
                            else if(text.length() > 432 && text.length() <= 513) version = 10;
                            else if(text.length() > 513 && text.length() <= 604) version = 11;
                            else if(text.length() > 604 && text.length() <= 691) version = 12;
                            else if(text.length() > 691 && text.length() <= 796) version = 13;
                            else if(text.length() > 796 && text.length() <= 871) version = 14;
                            else if(text.length() > 871 && text.length() <= 991) version = 15;
                            else if(text.length() > 991 && text.length() <= 1082) version = 16;
                            else if(text.length() > 1082 && text.length() <= 1212) version = 17;
                            else if(text.length() > 1212 && text.length() <= 1346) version = 18;
                            else if(text.length() > 1346 && text.length() <= 1500) version = 19;
                            else if(text.length() > 1500 && text.length() <= 1600) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;

                        case 25:
                            if(text.length() <= 27) { }
                            else if(text.length() > 27 && text.length() <= 48) version = 2;
                            else if(text.length() > 48 && text.length() <= 77) version = 3;
                            else if(text.length() > 77 && text.length() <= 111) version = 4;
                            else if(text.length() > 111 && text.length() <= 144) version = 5;
                            else if(text.length() > 144 && text.length() <= 178) version = 6;
                            else if(text.length() > 178 && text.length() <= 207) version = 7;
                            else if(text.length() > 207 && text.length() <= 259) version = 8;
                            else if(text.length() > 259 && text.length() <= 312) version = 9;
                            else if(text.length() > 312 && text.length() <= 364) version = 10;
                            else if(text.length() > 364 && text.length() <= 427) version = 11;
                            else if(text.length() > 427 && text.length() <= 489) version = 12;
                            else if(text.length() > 489 && text.length() <= 580) version = 13;
                            else if(text.length() > 580 && text.length() <= 621) version = 14;
                            else if(text.length() > 621 && text.length() <= 703) version = 15;
                            else if(text.length() > 703 && text.length() <= 775) version = 16;
                            else if(text.length() > 775 && text.length() <= 876) version = 17;
                            else if(text.length() > 876 && text.length() <= 948) version = 18;
                            else if(text.length() > 948 && text.length() <= 1063) version = 19;
                            else if(text.length() > 1063 && text.length() <= 1159) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;

                        case 30:
                            if(text.length() <= 17) { }
                            else if(text.length() > 17 && text.length() <= 34) version = 2;
                            else if(text.length() > 34 && text.length() <= 58) version = 3;
                            else if(text.length() > 58 && text.length() <= 82) version = 4;
                            else if(text.length() > 82 && text.length() <= 106) version = 5;
                            else if(text.length() > 106 && text.length() <= 139) version = 6;
                            else if(text.length() > 139 && text.length() <= 154) version = 7;
                            else if(text.length() > 154 && text.length() <= 202) version = 8;
                            else if(text.length() > 202 && text.length() <= 235) version = 9;
                            else if(text.length() > 235 && text.length() <= 288) version = 10;
                            else if(text.length() > 288 && text.length() <= 331) version = 11;
                            else if(text.length() > 331 && text.length() <= 374) version = 12;
                            else if(text.length() > 374 && text.length() <= 427) version = 13;
                            else if(text.length() > 427 && text.length() <= 468) version = 14;
                            else if(text.length() > 468 && text.length() <= 530) version = 15;
                            else if(text.length() > 530 && text.length() <= 602) version = 16;
                            else if(text.length() > 602 && text.length() <= 674) version = 17;
                            else if(text.length() > 674 && text.length() <= 746) version = 18;
                            else if(text.length() > 746 && text.length() <= 813) version = 19;
                            else if(text.length() > 813 && text.length() <= 919) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;
                    }
                }

                else if(text.matches("^[0-9A-Z$%*+-./: ]*$")) //Si es una cadena alfanumerica
                {
                    cifrado = "0010"; //Agregar el indicador de modo
                    int limiteFor = text.length();
                    if(text.length()%2 == 1) limiteFor = text.length() - 1; //Control de errores
                    for(int i = 0; i < limiteFor; i += 2) //Separar cada 2 caracteres
                    {
                        int alfa1 = ObtenerCodigoAlfa(text.charAt(i)); //Obtener codigo de la tabla
                        int alfa2 = ObtenerCodigoAlfa(text.charAt(i+1)); //Obtener codigo de la tabla
                        String auxBinario = Integer.toBinaryString(alfa1*45 + alfa2); //Convertir a binario
                        for(int j = auxBinario.length(); j < 11; j++) auxBinario = "0" + auxBinario; //Convertir a 11 bits
                        binario += auxBinario;
                    }
                    if(text.length()%2 == 1) //Agregar el ultimo caracter si sobra
                    {
                        int alfa = ObtenerCodigoAlfa(text.charAt(text.length()-1)); //Obtener codigo de la tabla
                        String auxBinario = Integer.toBinaryString(alfa); //Convertir a binario
                        for(int i = auxBinario.length(); i < 6; i++) auxBinario = "0" + auxBinario; //Convertir a 6 bits
                        binario += auxBinario;
                    }

                    switch (errorCorrection) //Determinar la versión más pequeña posible
                    {
                        case 7:
                            if(text.length() <= 25) { }
                            else if(text.length() > 25 && text.length() <= 47) version = 2;
                            else if(text.length() > 47 && text.length() <= 77) version = 3;
                            else if(text.length() > 77 && text.length() <= 114) version = 4;
                            else if(text.length() > 114 && text.length() <= 154) version = 5;
                            else if(text.length() > 154 && text.length() <= 195) version = 6;
                            else if(text.length() > 195 && text.length() <= 224) version = 7;
                            else if(text.length() > 224 && text.length() <= 279) version = 8;
                            else if(text.length() > 279 && text.length() <= 335) version = 9;
                            else if(text.length() > 335 && text.length() <= 395) version = 10;
                            else if(text.length() > 395 && text.length() <= 468) version = 11;
                            else if(text.length() > 468 && text.length() <= 535) version = 12;
                            else if(text.length() > 535 && text.length() <= 619) version = 13;
                            else if(text.length() > 619 && text.length() <= 667) version = 14;
                            else if(text.length() > 667 && text.length() <= 758) version = 15;
                            else if(text.length() > 758 && text.length() <= 854) version = 16;
                            else if(text.length() > 854 && text.length() <= 938) version = 17;
                            else if(text.length() > 938 && text.length() <= 1046) version = 18;
                            else if(text.length() > 1046 && text.length() <= 1153) version = 19;
                            else if(text.length() > 1153 && text.length() <= 1249) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;

                        case 15:
                            if(text.length() <= 20) { }
                            else if(text.length() > 20 && text.length() <= 38) version = 2;
                            else if(text.length() > 38 && text.length() <= 61) version = 3;
                            else if(text.length() > 61 && text.length() <= 90) version = 4;
                            else if(text.length() > 90 && text.length() <= 122) version = 5;
                            else if(text.length() > 122 && text.length() <= 154) version = 6;
                            else if(text.length() > 154 && text.length() <= 178) version = 7;
                            else if(text.length() > 178 && text.length() <= 221) version = 8;
                            else if(text.length() > 221 && text.length() <= 262) version = 9;
                            else if(text.length() > 262 && text.length() <= 311) version = 10;
                            else if(text.length() > 311 && text.length() <= 366) version = 11;
                            else if(text.length() > 366 && text.length() <= 419) version = 12;
                            else if(text.length() > 419 && text.length() <= 483) version = 13;
                            else if(text.length() > 483 && text.length() <= 528) version = 14;
                            else if(text.length() > 528 && text.length() <= 600) version = 15;
                            else if(text.length() > 600 && text.length() <= 656) version = 16;
                            else if(text.length() > 656 && text.length() <= 734) version = 17;
                            else if(text.length() > 734 && text.length() <= 816) version = 18;
                            else if(text.length() > 816 && text.length() <= 909) version = 19;
                            else if(text.length() > 909 && text.length() <= 970) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;

                        case 25:
                            if(text.length() <= 16) { }
                            else if(text.length() > 16 && text.length() <= 29) version = 2;
                            else if(text.length() > 29 && text.length() <= 47) version = 3;
                            else if(text.length() > 47 && text.length() <= 67) version = 4;
                            else if(text.length() > 67 && text.length() <= 87) version = 5;
                            else if(text.length() > 87 && text.length() <= 108) version = 6;
                            else if(text.length() > 108 && text.length() <= 125) version = 7;
                            else if(text.length() > 125 && text.length() <= 157) version = 8;
                            else if(text.length() > 157 && text.length() <= 189) version = 9;
                            else if(text.length() > 189 && text.length() <= 221) version = 10;
                            else if(text.length() > 221 && text.length() <= 259) version = 11;
                            else if(text.length() > 259 && text.length() <= 296) version = 12;
                            else if(text.length() > 296 && text.length() <= 352) version = 13;
                            else if(text.length() > 352 && text.length() <= 376) version = 14;
                            else if(text.length() > 376 && text.length() <= 426) version = 15;
                            else if(text.length() > 426 && text.length() <= 470) version = 16;
                            else if(text.length() > 470 && text.length() <= 531) version = 17;
                            else if(text.length() > 531 && text.length() <= 574) version = 18;
                            else if(text.length() > 574 && text.length() <= 644) version = 19;
                            else if(text.length() > 644 && text.length() <= 702) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;

                        case 30:
                            if(text.length() <= 10) { }
                            else if(text.length() > 10 && text.length() <= 20) version = 2;
                            else if(text.length() > 20 && text.length() <= 35) version = 3;
                            else if(text.length() > 35 && text.length() <= 50) version = 4;
                            else if(text.length() > 50 && text.length() <= 64) version = 5;
                            else if(text.length() > 64 && text.length() <= 84) version = 6;
                            else if(text.length() > 84 && text.length() <= 93) version = 7;
                            else if(text.length() > 93 && text.length() <= 122) version = 8;
                            else if(text.length() > 122 && text.length() <= 143) version = 9;
                            else if(text.length() > 143 && text.length() <= 174) version = 10;
                            else if(text.length() > 174 && text.length() <= 200) version = 11;
                            else if(text.length() > 200 && text.length() <= 227) version = 12;
                            else if(text.length() > 227 && text.length() <= 259) version = 13;
                            else if(text.length() > 259 && text.length() <= 283) version = 14;
                            else if(text.length() > 283 && text.length() <= 321) version = 15;
                            else if(text.length() > 321 && text.length() <= 365) version = 16;
                            else if(text.length() > 365 && text.length() <= 408) version = 17;
                            else if(text.length() > 408 && text.length() <= 452) version = 18;
                            else if(text.length() > 452 && text.length() <= 493) version = 19;
                            else if(text.length() > 493 && text.length() <= 557) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;
                    }
                }

                else //Bytes
                {
                    cifrado = "0100"; //Agregar el indicador de modo
                    for(int i = 0; i < text.length(); i++) //Convertir cada caracter en binario (ASCII)
                    {
                        String auxBinario = Integer.toBinaryString((int)text.charAt(i));
                        for(int j = auxBinario.length(); j <= 8; j++) auxBinario = "0" + auxBinario; //Convertir a 8 bits
                        binario += auxBinario;
                    }

                    switch (errorCorrection) //Determinar la versión más pequeña posible
                    {
                        case 7:
                            if(text.length() <= 17) { }
                            else if(text.length() > 17 && text.length() <= 32) version = 2;
                            else if(text.length() > 32 && text.length() <= 53) version = 3;
                            else if(text.length() > 53 && text.length() <= 78) version = 4;
                            else if(text.length() > 78 && text.length() <= 106) version = 5;
                            else if(text.length() > 106 && text.length() <= 134) version = 6;
                            else if(text.length() > 134 && text.length() <= 154) version = 7;
                            else if(text.length() > 154 && text.length() <= 192) version = 8;
                            else if(text.length() > 192 && text.length() <= 230) version = 9;
                            else if(text.length() > 230 && text.length() <= 271) version = 10;
                            else if(text.length() > 271 && text.length() <= 321) version = 11;
                            else if(text.length() > 321 && text.length() <= 367) version = 12;
                            else if(text.length() > 367 && text.length() <= 425) version = 13;
                            else if(text.length() > 425 && text.length() <= 458) version = 14;
                            else if(text.length() > 458 && text.length() <= 520) version = 15;
                            else if(text.length() > 520 && text.length() <= 586) version = 16;
                            else if(text.length() > 586 && text.length() <= 644) version = 17;
                            else if(text.length() > 644 && text.length() <= 718) version = 18;
                            else if(text.length() > 718 && text.length() <= 792) version = 19;
                            else if(text.length() > 792 && text.length() <= 858) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;

                        case 15:
                            if(text.length() <= 14) { }
                            else if(text.length() > 14 && text.length() <= 26) version = 2;
                            else if(text.length() > 26 && text.length() <= 42) version = 3;
                            else if(text.length() > 42 && text.length() <= 62) version = 4;
                            else if(text.length() > 62 && text.length() <= 84) version = 5;
                            else if(text.length() > 84 && text.length() <= 106) version = 6;
                            else if(text.length() > 106 && text.length() <= 122) version = 7;
                            else if(text.length() > 122 && text.length() <= 152) version = 8;
                            else if(text.length() > 152 && text.length() <= 180) version = 9;
                            else if(text.length() > 180 && text.length() <= 213) version = 10;
                            else if(text.length() > 213 && text.length() <= 251) version = 11;
                            else if(text.length() > 251 && text.length() <= 287) version = 12;
                            else if(text.length() > 287 && text.length() <= 331) version = 13;
                            else if(text.length() > 331 && text.length() <= 362) version = 14;
                            else if(text.length() > 362 && text.length() <= 412) version = 15;
                            else if(text.length() > 412 && text.length() <= 450) version = 16;
                            else if(text.length() > 450 && text.length() <= 504) version = 17;
                            else if(text.length() > 504 && text.length() <= 560) version = 18;
                            else if(text.length() > 560 && text.length() <= 624) version = 19;
                            else if(text.length() > 624 && text.length() <= 666) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;

                        case 25:
                            if(text.length() <= 11) { }
                            else if(text.length() > 11 && text.length() <= 20) version = 2;
                            else if(text.length() > 20 && text.length() <= 32) version = 3;
                            else if(text.length() > 32 && text.length() <= 46) version = 4;
                            else if(text.length() > 46 && text.length() <= 60) version = 5;
                            else if(text.length() > 60 && text.length() <= 74) version = 6;
                            else if(text.length() > 74 && text.length() <= 86) version = 7;
                            else if(text.length() > 86 && text.length() <= 108) version = 8;
                            else if(text.length() > 108 && text.length() <= 130) version = 9;
                            else if(text.length() > 130 && text.length() <= 151) version = 10;
                            else if(text.length() > 151 && text.length() <= 177) version = 11;
                            else if(text.length() > 177 && text.length() <= 203) version = 12;
                            else if(text.length() > 203 && text.length() <= 241) version = 13;
                            else if(text.length() > 241 && text.length() <= 258) version = 14;
                            else if(text.length() > 258 && text.length() <= 292) version = 15;
                            else if(text.length() > 292 && text.length() <= 322) version = 16;
                            else if(text.length() > 322 && text.length() <= 364) version = 17;
                            else if(text.length() > 364 && text.length() <= 394) version = 18;
                            else if(text.length() > 394 && text.length() <= 442) version = 19;
                            else if(text.length() > 442 && text.length() <= 482) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;

                        case 30:
                            if(text.length() <= 7) { }
                            else if(text.length() > 7 && text.length() <= 14) version = 2;
                            else if(text.length() > 14 && text.length() <= 24) version = 3;
                            else if(text.length() > 24 && text.length() <= 34) version = 4;
                            else if(text.length() > 34 && text.length() <= 44) version = 5;
                            else if(text.length() > 44 && text.length() <= 58) version = 6;
                            else if(text.length() > 58 && text.length() <= 64) version = 7;
                            else if(text.length() > 64 && text.length() <= 84) version = 8;
                            else if(text.length() > 84 && text.length() <= 98) version = 9;
                            else if(text.length() > 98 && text.length() <= 119) version = 10;
                            else if(text.length() > 119 && text.length() <= 137) version = 11;
                            else if(text.length() > 137 && text.length() <= 155) version = 12;
                            else if(text.length() > 155 && text.length() <= 177) version = 13;
                            else if(text.length() > 177 && text.length() <= 194) version = 14;
                            else if(text.length() > 194 && text.length() <= 220) version = 15;
                            else if(text.length() > 220 && text.length() <= 250) version = 16;
                            else if(text.length() > 250 && text.length() <= 280) version = 17;
                            else if(text.length() > 280 && text.length() <= 310) version = 18;
                            else if(text.length() > 310 && text.length() <= 338) version = 19;
                            else if(text.length() > 338 && text.length() <= 382) version = 20;
                            else { textViewVersion.setText("Cadena demasiado larga"); version = 0;}
                            break;
                    }
                }

                if(version > 0) //Control de errores
                {
                    tamanio = 21 + (version-1)*4; //Determinar las dimensiones del codigo

                    //Determinar el tamaño del contador
                    if(cifrado.startsWith("0001") && version <= 9) tamanioIndicador = 10;
                    else if(cifrado.startsWith("0001") && version > 9 && version <= 26) tamanioIndicador = 12;
                    else if(cifrado.startsWith("0010") && version <= 9) tamanioIndicador = 9;
                    else if(cifrado.startsWith("0010") && version > 9 && version <= 26) tamanioIndicador = 11;
                    else if(cifrado.startsWith("0100") && version <= 9) tamanioIndicador = 8;
                    else if(cifrado.startsWith("0100") && version > 9 && version <= 26) tamanioIndicador = 16;

                    //Determinar el contador
                    String contador = Integer.toBinaryString(text.length());
                    int longContador = contador.length();
                    for (int i = 0; i < tamanioIndicador-longContador; i++) contador = "0" + contador;

                    cifrado += contador + binario; //Agregar el contador y los datos cifrados

                    switch (version) //Determinar el numero de bits necesarias y el numero de bloques de correccion necesarios
                    {
                        case 1:
                            bitsExtra = 0;
                            if(errorCorrection == 7) {bitsNecesarios = 19; bloquesCorreccion = 7;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 19; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 16; bloquesCorreccion = 10;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 16; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 25) {bitsNecesarios = 13; bloquesCorreccion = 13;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 13; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 30) {bitsNecesarios = 9; bloquesCorreccion = 17;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 9; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            break;
                        case 2:
                            bitsExtra = 7; alignmentPattern = new int[]{6,18};
                            if(errorCorrection == 7) {bitsNecesarios = 34; bloquesCorreccion = 10;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 34; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 28; bloquesCorreccion = 16;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 28; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 25) {bitsNecesarios = 22; bloquesCorreccion = 22;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 22; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 30) {bitsNecesarios = 16; bloquesCorreccion = 28;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 16; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            break;
                        case 3:
                            bitsExtra = 7; alignmentPattern = new int[]{6,22};
                            if(errorCorrection == 7) {bitsNecesarios = 55; bloquesCorreccion = 15;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 55; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 44; bloquesCorreccion = 26;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 44; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 25) {bitsNecesarios = 34; bloquesCorreccion = 18;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 17; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 30) {bitsNecesarios = 26; bloquesCorreccion = 22;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 13; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            break;
                        case 4:
                            bitsExtra = 7; alignmentPattern = new int[]{6,26};
                            if(errorCorrection == 7) {bitsNecesarios = 80; bloquesCorreccion = 20;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 80; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 64; bloquesCorreccion = 18;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 32; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 25) {bitsNecesarios = 48; bloquesCorreccion = 26;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 24; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 30) {bitsNecesarios = 36; bloquesCorreccion = 16;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 9; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                        case 5:
                            bitsExtra = 7; alignmentPattern = new int[]{6,30};
                            if(errorCorrection == 7) {bitsNecesarios = 108; bloquesCorreccion = 26;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 108; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 86; bloquesCorreccion = 24;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 43; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 25) {bitsNecesarios = 62; bloquesCorreccion = 18;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 15; bloquesGrupo2 = 2; codewordsGrupo2 = 16;}
                            else if(errorCorrection == 30) {bitsNecesarios = 46; bloquesCorreccion = 22;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 11; bloquesGrupo2 = 2; codewordsGrupo2 = 12;}
                            break;
                        case 6:
                            bitsExtra = 7; alignmentPattern = new int[]{6,34};
                            if(errorCorrection == 7) {bitsNecesarios = 136; bloquesCorreccion = 18;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 68; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 108; bloquesCorreccion = 16;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 27; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 25) {bitsNecesarios = 76; bloquesCorreccion = 24;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 19; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 30) {bitsNecesarios = 60; bloquesCorreccion = 28;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 15; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            break;
                        case 7:
                            bitsExtra = 0; alignmentPattern = new int[]{6,22, 38}; versionInformation = "000111110010010100";
                            if(errorCorrection == 7) {bitsNecesarios = 156; bloquesCorreccion = 20;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 78; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 124; bloquesCorreccion = 18;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 31; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 25) {bitsNecesarios = 88; bloquesCorreccion = 18;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 14; bloquesGrupo2 = 4; codewordsGrupo2 = 15;}
                            else if(errorCorrection == 30) {bitsNecesarios = 66; bloquesCorreccion = 26;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 13; bloquesGrupo2 = 1; codewordsGrupo2 = 14;}
                            break;
                        case 8:
                            bitsExtra = 0; alignmentPattern = new int[]{6,24, 42}; versionInformation = "001000010110111100";
                            if(errorCorrection == 7) {bitsNecesarios = 194; bloquesCorreccion = 24;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 97; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 154; bloquesCorreccion = 22;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 38; bloquesGrupo2 = 2; codewordsGrupo2 = 39;}
                            else if(errorCorrection == 25) {bitsNecesarios = 110; bloquesCorreccion = 22;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 18; bloquesGrupo2 = 2; codewordsGrupo2 = 19;}
                            else if(errorCorrection == 30) {bitsNecesarios = 86; bloquesCorreccion = 26;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 14; bloquesGrupo2 = 2; codewordsGrupo2 = 15;}
                            break;
                        case 9:
                            bitsExtra = 0; alignmentPattern = new int[]{6,26, 46}; versionInformation = "001001101010011001";
                            if(errorCorrection == 7) {bitsNecesarios = 232; bloquesCorreccion = 30;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 116; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 182; bloquesCorreccion = 22;
                                bloquesGrupo1 = 3; codewordsGrupo1 = 36; bloquesGrupo2 = 2; codewordsGrupo2 = 37;}
                            else if(errorCorrection == 25) {bitsNecesarios = 132; bloquesCorreccion = 20;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 16; bloquesGrupo2 = 4; codewordsGrupo2 = 17;}
                            else if(errorCorrection == 30) {bitsNecesarios = 100; bloquesCorreccion = 24;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 12; bloquesGrupo2 = 4; codewordsGrupo2 = 13;}
                            break;
                        case 10:
                            bitsExtra = 0; alignmentPattern = new int[]{6,28, 50}; versionInformation = "001010010011010011";
                            if(errorCorrection == 7) {bitsNecesarios = 274; bloquesCorreccion = 18;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 68; bloquesGrupo2 = 2; codewordsGrupo2 = 69;}
                            else if(errorCorrection == 15) {bitsNecesarios = 216; bloquesCorreccion = 26;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 43; bloquesGrupo2 = 1; codewordsGrupo2 = 44;}
                            else if(errorCorrection == 25) {bitsNecesarios = 154; bloquesCorreccion = 24;
                                bloquesGrupo1 = 6; codewordsGrupo1 = 19; bloquesGrupo2 = 2; codewordsGrupo2 = 20;}
                            else if(errorCorrection == 30) {bitsNecesarios = 122; bloquesCorreccion = 28;
                                bloquesGrupo1 = 6; codewordsGrupo1 = 15; bloquesGrupo2 = 2; codewordsGrupo2 = 16;}
                            break;
                        case 11:
                            bitsExtra = 0; alignmentPattern = new int[]{6,30, 54}; versionInformation = "001011101111110110";
                            if(errorCorrection == 7) {bitsNecesarios = 324; bloquesCorreccion = 20;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 81; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 254; bloquesCorreccion = 30;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 50; bloquesGrupo2 = 4; codewordsGrupo2 = 51;}
                            else if(errorCorrection == 25) {bitsNecesarios = 180; bloquesCorreccion = 28;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 22; bloquesGrupo2 = 4; codewordsGrupo2 = 23;}
                            else if(errorCorrection == 30) {bitsNecesarios = 140; bloquesCorreccion = 24;
                                bloquesGrupo1 = 3; codewordsGrupo1 = 12; bloquesGrupo2 = 8; codewordsGrupo2 = 13;}
                            break;
                        case 12:
                            bitsExtra = 0; alignmentPattern = new int[]{6,32, 58}; versionInformation = "001100011101100010";
                            if(errorCorrection == 7) {bitsNecesarios = 370; bloquesCorreccion = 24;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 92; bloquesGrupo2 = 2; codewordsGrupo2 = 93;}
                            else if(errorCorrection == 15) {bitsNecesarios = 290; bloquesCorreccion = 22;
                                bloquesGrupo1 = 6; codewordsGrupo1 = 36; bloquesGrupo2 = 2; codewordsGrupo2 = 37;}
                            else if(errorCorrection == 25) {bitsNecesarios = 206; bloquesCorreccion = 26;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 20; bloquesGrupo2 = 6; codewordsGrupo2 = 21;}
                            else if(errorCorrection == 30) {bitsNecesarios = 158; bloquesCorreccion = 28;
                                bloquesGrupo1 = 7; codewordsGrupo1 = 14; bloquesGrupo2 = 4; codewordsGrupo2 = 15;}
                            break;
                        case 13:
                            bitsExtra = 0; alignmentPattern = new int[]{6,34, 62}; versionInformation = "001101100001000111";
                            if(errorCorrection == 7) {bitsNecesarios = 428; bloquesCorreccion = 26;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 107; bloquesGrupo2 = 0; codewordsGrupo2 = 0;}
                            else if(errorCorrection == 15) {bitsNecesarios = 334; bloquesCorreccion = 22;
                                bloquesGrupo1 = 8; codewordsGrupo1 = 37; bloquesGrupo2 = 1; codewordsGrupo2 = 38;}
                            else if(errorCorrection == 25) {bitsNecesarios = 244; bloquesCorreccion = 24;
                                bloquesGrupo1 = 8; codewordsGrupo1 = 20; bloquesGrupo2 = 4; codewordsGrupo2 = 21;}
                            else if(errorCorrection == 30) {bitsNecesarios = 180; bloquesCorreccion = 22;
                                bloquesGrupo1 = 12; codewordsGrupo1 = 11; bloquesGrupo2 = 4; codewordsGrupo2 = 12;}
                            break;
                        case 14:
                            bitsExtra = 3; alignmentPattern = new int[]{6,26, 46, 66}; versionInformation = "001110011000001101";
                            if(errorCorrection == 7) {bitsNecesarios = 461; bloquesCorreccion = 30;
                                bloquesGrupo1 = 3; codewordsGrupo1 = 115; bloquesGrupo2 = 1; codewordsGrupo2 = 116;}
                            else if(errorCorrection == 15) {bitsNecesarios = 365; bloquesCorreccion = 24;
                                bloquesGrupo1 = 4; codewordsGrupo1 = 40; bloquesGrupo2 = 5; codewordsGrupo2 = 41;}
                            else if(errorCorrection == 25) {bitsNecesarios = 261; bloquesCorreccion = 20;
                                bloquesGrupo1 = 11; codewordsGrupo1 = 16; bloquesGrupo2 = 5; codewordsGrupo2 = 17;}
                            else if(errorCorrection == 30) {bitsNecesarios = 197; bloquesCorreccion = 24;
                                bloquesGrupo1 = 11; codewordsGrupo1 = 12; bloquesGrupo2 = 5; codewordsGrupo2 = 13;}
                            break;
                        case 15:
                            bitsExtra = 3; alignmentPattern = new int[]{6,26, 48, 70}; versionInformation = "001111100100101000";
                            if(errorCorrection == 7) {bitsNecesarios = 523; bloquesCorreccion = 22;
                                bloquesGrupo1 = 5; codewordsGrupo1 = 87; bloquesGrupo2 = 1; codewordsGrupo2 = 88;}
                            else if(errorCorrection == 15) {bitsNecesarios = 415; bloquesCorreccion = 24;
                                bloquesGrupo1 = 5; codewordsGrupo1 = 41; bloquesGrupo2 = 5; codewordsGrupo2 = 42;}
                            else if(errorCorrection == 25) {bitsNecesarios = 295; bloquesCorreccion = 30;
                                bloquesGrupo1 = 5; codewordsGrupo1 = 24; bloquesGrupo2 = 7; codewordsGrupo2 = 25;}
                            else if(errorCorrection == 30) {bitsNecesarios = 223; bloquesCorreccion = 24;
                                bloquesGrupo1 = 11; codewordsGrupo1 = 12; bloquesGrupo2 = 7; codewordsGrupo2 = 13;}
                            break;
                        case 16:
                            bitsExtra = 3; alignmentPattern = new int[]{6,26, 50, 74}; versionInformation = "010000101101111000";
                            if(errorCorrection == 7) {bitsNecesarios = 589; bloquesCorreccion = 24;
                                bloquesGrupo1 = 5; codewordsGrupo1 = 98; bloquesGrupo2 = 1; codewordsGrupo2 = 99;}
                            else if(errorCorrection == 15) {bitsNecesarios = 453; bloquesCorreccion = 28;
                                bloquesGrupo1 = 7; codewordsGrupo1 = 45; bloquesGrupo2 = 3; codewordsGrupo2 = 46;}
                            else if(errorCorrection == 25) {bitsNecesarios = 325; bloquesCorreccion = 24;
                                bloquesGrupo1 = 15; codewordsGrupo1 = 19; bloquesGrupo2 = 2; codewordsGrupo2 = 20;}
                            else if(errorCorrection == 30) {bitsNecesarios = 253; bloquesCorreccion = 30;
                                bloquesGrupo1 = 3; codewordsGrupo1 = 15; bloquesGrupo2 = 13; codewordsGrupo2 = 16;}
                            break;
                        case 17:
                            bitsExtra = 3; alignmentPattern = new int[]{6, 30, 54, 78}; versionInformation = "010001010001011101";
                            if(errorCorrection == 7) {bitsNecesarios = 647; bloquesCorreccion = 28;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 107; bloquesGrupo2 = 5; codewordsGrupo2 = 108;}
                            else if(errorCorrection == 15) {bitsNecesarios = 507; bloquesCorreccion = 28;
                                bloquesGrupo1 = 10; codewordsGrupo1 = 46; bloquesGrupo2 = 1; codewordsGrupo2 = 47;}
                            else if(errorCorrection == 25) {bitsNecesarios = 367; bloquesCorreccion = 28;
                                bloquesGrupo1 = 1; codewordsGrupo1 = 22; bloquesGrupo2 = 15; codewordsGrupo2 = 23;}
                            else if(errorCorrection == 30) {bitsNecesarios = 283; bloquesCorreccion = 28;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 14; bloquesGrupo2 = 17; codewordsGrupo2 = 15;}
                            break;
                        case 18:
                            bitsExtra = 3; alignmentPattern = new int[]{6, 30, 56, 82}; versionInformation = "010010101000010111";
                            if(errorCorrection == 7) {bitsNecesarios = 721; bloquesCorreccion = 30;
                                bloquesGrupo1 = 5; codewordsGrupo1 = 120; bloquesGrupo2 = 1; codewordsGrupo2 = 121;}
                            else if(errorCorrection == 15) {bitsNecesarios = 563; bloquesCorreccion = 26;
                                bloquesGrupo1 = 9; codewordsGrupo1 = 43; bloquesGrupo2 = 4; codewordsGrupo2 = 44;}
                            else if(errorCorrection == 25) {bitsNecesarios = 397; bloquesCorreccion = 28;
                                bloquesGrupo1 = 17; codewordsGrupo1 = 22; bloquesGrupo2 = 1; codewordsGrupo2 = 23;}
                            else if(errorCorrection == 30) {bitsNecesarios = 313; bloquesCorreccion = 28;
                                bloquesGrupo1 = 2; codewordsGrupo1 = 14; bloquesGrupo2 = 19; codewordsGrupo2 = 15;}
                            break;
                        case 19:
                            bitsExtra = 3; alignmentPattern = new int[]{6, 30, 58, 86}; versionInformation = "010011010100110010";
                            if(errorCorrection == 7) {bitsNecesarios = 795; bloquesCorreccion = 28;
                                bloquesGrupo1 = 3; codewordsGrupo1 = 113; bloquesGrupo2 = 4; codewordsGrupo2 = 114;}
                            else if(errorCorrection == 15) {bitsNecesarios = 627; bloquesCorreccion = 26;
                                bloquesGrupo1 = 3; codewordsGrupo1 = 44; bloquesGrupo2 = 11; codewordsGrupo2 = 45;}
                            else if(errorCorrection == 25) {bitsNecesarios = 445; bloquesCorreccion = 26;
                                bloquesGrupo1 = 17; codewordsGrupo1 = 21; bloquesGrupo2 = 4; codewordsGrupo2 = 22;}
                            else if(errorCorrection == 30) {bitsNecesarios = 341; bloquesCorreccion = 26;
                                bloquesGrupo1 = 9; codewordsGrupo1 = 13; bloquesGrupo2 = 16; codewordsGrupo2 = 14;}
                            break;
                        case 20:
                            bitsExtra = 3; alignmentPattern = new int[]{6, 34, 62, 90}; versionInformation = "010100100110100110";
                            if(errorCorrection == 7) {bitsNecesarios = 861; bloquesCorreccion = 28;
                                bloquesGrupo1 = 3; codewordsGrupo1 = 107; bloquesGrupo2 = 5; codewordsGrupo2 = 108;}
                            else if(errorCorrection == 15) {bitsNecesarios = 669; bloquesCorreccion = 26;
                                bloquesGrupo1 = 3; codewordsGrupo1 = 41; bloquesGrupo2 = 13; codewordsGrupo2 = 42;}
                            else if(errorCorrection == 25) {bitsNecesarios = 485; bloquesCorreccion = 30;
                                bloquesGrupo1 = 15; codewordsGrupo1 = 24; bloquesGrupo2 = 5; codewordsGrupo2 = 25;}
                            else if(errorCorrection == 30) {bitsNecesarios = 385; bloquesCorreccion = 28;
                                bloquesGrupo1 = 15; codewordsGrupo1 = 15; bloquesGrupo2 = 10; codewordsGrupo2 = 16;}
                            break;
                    }
                    bitsNecesarios *= 8;

                    if(bitsNecesarios - cifrado.length() == 1) cifrado += "0"; //Agregar el terminador
                    else if(bitsNecesarios - cifrado.length() == 2) cifrado += "00";
                    else if(bitsNecesarios - cifrado.length() == 3) cifrado += "000";
                    else if(bitsNecesarios - cifrado.length() >= 4) cifrado += "0000";

                    while (cifrado.length()%8 != 0) cifrado += "0"; //Revisar que los bits totales sean multiplos de 8

                    while (cifrado.length() < bitsNecesarios) //Revisar que la cadena alcance el valor deseado
                    {
                        cifrado += "11101100";
                        if(cifrado.length() < bitsNecesarios) cifrado += "00010001";
                    }

                    //Preparar el mensaje polinomial, el numero es el coeficiente
                    //Seaparados en grupos, separados en bloques, separados en data codewords
                    int[][] grupo1 = new int[bloquesGrupo1][codewordsGrupo1], grupo2 = new int[bloquesGrupo2][codewordsGrupo2];
                    int[][] residuoGrupo1 = new int[bloquesGrupo1][codewordsGrupo1], residuoGrupo2 = new int[bloquesGrupo2][codewordsGrupo2];
                    int contBits = 0;
                    for(int i = 0; i < grupo1.length; i++) //Grupo1
                    {
                        for(int j = 0; j < grupo1[i].length; j++) //Bloques del Grupo1, mensajes polinomiales
                        {
                            grupo1[i][j] = Integer.parseInt(cifrado.substring(contBits, contBits+8), 2); //Data codewords
                            residuoGrupo1[i][j] = Integer.parseInt(cifrado.substring(contBits, contBits+8), 2); //Error correction codewords
                            contBits += 8;
                        }
                        //XOR el generador polinomial con el mensaje polinomial
                        for(int j = 0; j < grupo1[i].length; j++) residuoGrupo1[i] = XORprocedimiento(residuoGrupo1[i]);
                    }
                    for(int i = 0; i < grupo2.length; i++) //Grupo2
                    {
                        for(int j = 0; j < grupo2[i].length; j++) //Bloques del Grupo1, mensajes polinomiales
                        {
                            residuoGrupo2[i][j] = Integer.parseInt(cifrado.substring(contBits, contBits+8), 2); //Data codewords
                            grupo2[i][j] = Integer.parseInt(cifrado.substring(contBits, contBits+8), 2); //Error correction codewords
                            contBits += 8;
                        }
                        //XOR el generador polinomial con el mensaje polinomial
                        for(int j = 0; j < grupo2[i].length; j++) residuoGrupo2[i] = XORprocedimiento(residuoGrupo2[i]);
                    }
                    //A los indices hay que sumarles la variable bloquesCorreccion para usarlos

                    //Calcular por cuanto se debe multiplicar el generador polinomial para que el primer elemento (ultimo en el arreglo)
                    //tenga el mismo exponente de x que el primer elemento (ultimo en el arreglo) del polinomio
                    //A los indices hay que sumarles esta variable para usarlos
                    //int sumarGenPolinomial = polinomio.length-1 + bloquesCorreccion - genPolinomial.length-1;

                    //Intercalar los data codewords y los error correction codewords
                    int[] polinomio = IntercalarCodewords(grupo1, grupo2, bloquesGrupo1*codewordsGrupo1 + bloquesGrupo2*codewordsGrupo2, codewordsGrupo1, codewordsGrupo2);
                    int[] residuo = IntercalarCodewords(residuoGrupo1, residuoGrupo2, bloquesCorreccion*bloquesGrupo1 + bloquesCorreccion*bloquesGrupo2,
                            bloquesCorreccion, bloquesCorreccion);

                    //Concatenar los arreglos, primero los data codewords y luego los error correction codewords
                    String finalMessage = "";
                    for(int i = 0; i < polinomio.length; i++)
                    {
                        String auxString = Integer.toBinaryString(polinomio[i]); //Convertir a binario de 8 bits
                        for(int j = auxString.length(); j < 8; j++) auxString = "0" + auxString; //Agregar los 0 necesarios
                        finalMessage += auxString;
                    }
                    for(int i = polinomio.length; i < polinomio.length+residuo.length; i++)
                    {
                        String auxString = Integer.toBinaryString(residuo[i-polinomio.length]); //Convertir a binario de 8 bits
                        for(int j = auxString.length(); j < 8; j++) auxString = "0" + auxString; //Agregar los 0 necesarios
                        finalMessage += auxString;
                    }

                    for(int i = 0; i < bitsExtra; i++) finalMessage += "0"; //Añadir los bits extra necesarios segun la version
                    //textViewBinario.setText(finalMessage);

                    TextView[][] celdas = new TextView[tamanio+8][tamanio+8]; //Crear la tabla para el QR
                    for(int i = 0; i < tamanio+8; i++)
                    {
                        TableRow row = new TableRow(tabla.getContext());
                        row.setGravity(0);

                        for(int j = 0; j < tamanio+8; j++)
                        {
                            TextView numBin = new TextView(tabla.getContext());
                            numBin.setBackgroundColor(Color.MAGENTA); //-65281
                            numBin.setText("S");
                            numBin.setWidth(tabla.getWidth()/(tamanio+8));
                            numBin.setHeight(tabla.getWidth()/(tamanio+8));
                            celdas[i][j] = numBin;
                            row.addView(numBin);
                        }
                        tabla.addView(row);
                    }

                    for(int i = 0; i < celdas.length; i++) //Crear la quiet zone
                    {
                        for(int j = 0; j < celdas.length; j++)
                        {
                            if(i < 4 || i >= tamanio+4) {celdas[i][j].setBackgroundColor(Color.WHITE); celdas[i][j].setText("");}
                            if(j < 4 || j >= tamanio+4) {celdas[i][j].setBackgroundColor(Color.WHITE); celdas[i][j].setText("");}
                        }
                    }

                    //Crear los separadores
                    for(int i = 4; i < 12; i++) {for(int j = 4; j < 12; j++) {celdas[i][j].setBackgroundColor(Color.WHITE); celdas[i][j].setText("0");}}
                    for(int i = celdas.length-5; i >= celdas.length-12; i--) {for(int j = 4; j < 12; j++) {celdas[i][j].setBackgroundColor(Color.WHITE); celdas[i][j].setText("0");}}
                    for(int i = 4; i < 12; i++) {for(int j = celdas.length-5; j >= celdas.length-12; j--) {celdas[i][j].setBackgroundColor(Color.WHITE); celdas[i][j].setText("0");}}

                    //Crear los finder patterns, cuadro negro grande
                    for(int i = 4; i < 11; i++) {for(int j = 4; j < 11; j++) {celdas[i][j].setBackgroundColor(Color.BLACK); celdas[i][j].setText("1");}}
                    for(int i = celdas.length-5; i >= celdas.length-11; i--) {for(int j = 4; j < 11; j++) {celdas[i][j].setBackgroundColor(Color.BLACK); celdas[i][j].setText("1");}}
                    for(int i = 4; i < 11; i++) {for(int j = celdas.length-5; j >= celdas.length-11; j--) {celdas[i][j].setBackgroundColor(Color.BLACK); celdas[i][j].setText("1");}}

                    //Crear los finder patterns, cuadro blanco
                    for(int i = 5; i < 10; i++) {for(int j = 5; j < 10; j++) {celdas[i][j].setBackgroundColor(Color.WHITE); celdas[i][j].setText("0");}}
                    for(int i = celdas.length-6; i >= celdas.length-10; i--) {for(int j = 5; j < 10; j++) {celdas[i][j].setBackgroundColor(Color.WHITE); celdas[i][j].setText("0");}}
                    for(int i = 5; i < 10; i++) {for(int j = celdas.length-6; j >= celdas.length-10; j--) {celdas[i][j].setBackgroundColor(Color.WHITE); celdas[i][j].setText("0");}}

                    //Crear los finder patterns, cuadro negro chico
                    for(int i = 6; i < 9; i++) {for(int j = 6; j < 9; j++) {celdas[i][j].setBackgroundColor(Color.BLACK); celdas[i][j].setText("1");}}
                    for(int i = celdas.length-7; i >= celdas.length-9; i--) {for(int j = 6; j < 9; j++) {celdas[i][j].setBackgroundColor(Color.BLACK); celdas[i][j].setText("1");}}
                    for(int i = 6; i < 9; i++) {for(int j = celdas.length-7; j >= celdas.length-9; j--) {celdas[i][j].setBackgroundColor(Color.BLACK); celdas[i][j].setText("1");}}

                    //Crear los alignment patterns
                    if(version > 1)
                    {
                        for(int i = 0; i < alignmentPattern.length; i++)
                        {
                            for(int j = 0; j < alignmentPattern.length; j++)
                            {
                                if(celdas[alignmentPattern[i]+2][alignmentPattern[j]+2].getText() == "S"
                                        && celdas[alignmentPattern[i]+2][alignmentPattern[j]+6].getText() == "S"
                                        && celdas[alignmentPattern[i]+6][alignmentPattern[j]+2].getText() == "S"
                                        && celdas[alignmentPattern[i]+6][alignmentPattern[j]+6].getText() == "S")
                                {
                                    for(int k = alignmentPattern[i]+2; k < alignmentPattern[i]+7; k++) //Cuadro grande negro
                                    {
                                        for(int l = alignmentPattern[j]+2; l < alignmentPattern[j]+7; l++)
                                        { celdas[k][l].setBackgroundColor(Color.BLACK);celdas[k][l].setText("1"); }
                                    }
                                    for(int k = alignmentPattern[i]+3; k < alignmentPattern[i]+6; k++) //Cuadro blanco
                                    {
                                        for(int l = alignmentPattern[j]+3; l < alignmentPattern[j]+6; l++)
                                        { celdas[k][l].setBackgroundColor(Color.WHITE);celdas[k][l].setText("0"); }
                                    }
                                    celdas[alignmentPattern[i]+4][alignmentPattern[j]+4].setBackgroundColor(Color.BLACK); //centro
                                    celdas[alignmentPattern[i]+4][alignmentPattern[j]+4].setText("1"); //centro
                                }
                            }
                        }
                    }

                    for(int i = 12; i < tamanio-4; i += 2) //Crear los timing patterns
                    {
                        celdas[10][i].setBackgroundColor(Color.BLACK); celdas[10][i].setText("1"); //Horizontal negro
                        if(celdas[10][i+1].getText() == "S") {celdas[10][i+1].setBackgroundColor(Color.WHITE); celdas[10][i+1].setText("0");} //Horizontal blanco

                        celdas[i][10].setBackgroundColor(Color.BLACK); celdas[i][10].setText("1"); //Vertical negro
                        if(celdas[i+1][10].getText() == "S") {celdas[i+1][10].setBackgroundColor(Color.WHITE); celdas[i+1][10].setText("0");} //Vertical blanco
                    }

                    celdas[4*version + 13][12].setBackgroundColor(Color.BLACK); celdas[4*version + 13][12].setText("1"); //Crear el dark module

                    for(int i = 4; i < 13; i++) //Reservar la format information area
                    {
                        if(celdas[12][i].getText() == "S") {celdas[12][i].setBackgroundColor(Color.BLUE); celdas[12][i].setText("2");} //Horizontal izquierda
                        if(celdas[i][12].getText() == "S") {celdas[i][12].setBackgroundColor(Color.BLUE); celdas[i][12].setText("2");} //Vertical arriba
                    }
                    for(int i = tamanio+4; i >= tamanio-4; i--)
                    {
                        if(celdas[12][i].getText() == "S") {celdas[12][i].setBackgroundColor(Color.BLUE); celdas[12][i].setText("2"); } //Horizontal derecha
                        if(celdas[i][12].getText() == "S") {celdas[i][12].setBackgroundColor(Color.BLUE); celdas[i][12].setText("2"); } //Verical abajo
                    }

                    if(version >= 7) //Reservar la version information area para los qr a partir de la version 7
                    {
                        for(int i = tamanio-4; i >= tamanio-7; i--)
                        {
                            for(int j = 4; j < 11; i++)
                            {
                                celdas[i][j].setBackgroundColor(Color.GREEN); celdas[i][j].setText("3"); //Superior derecha
                                celdas[j][i].setBackgroundColor(Color.GREEN); celdas[j][i].setText("3"); //Inferior izquierda
                            }
                        }
                    }

                    contBits = 0; //Agregar los data bits
                    for(int i = tamanio+4; i >= 4; i -= 2)
                    {
                        if(i == 9) i--;
                        for(int j = tamanio+4; j >= 4; j--)
                        {
                            if(celdas[j][i].getText() == "S")
                            {
                                if(finalMessage.charAt(contBits) == '0') {celdas[j][i].setBackgroundColor(Color.WHITE); celdas[j][i].setText("0"); celdas[j][i].setHint("data");}
                                else {celdas[j][i].setBackgroundColor(Color.BLACK); celdas[j][i].setText("1"); celdas[j][i].setHint("data");}
                                contBits++;
                            }
                            if(celdas[j][i+1].getText() == "S")
                            {
                                if(finalMessage.charAt(contBits) == '0') {celdas[j][i+1].setBackgroundColor(Color.WHITE); celdas[j][i+1].setText("0"); celdas[j][i+1].setHint("data");}
                                else {celdas[j][i+1].setBackgroundColor(Color.BLACK); celdas[j][i+1].setText("1"); celdas[j][i+1].setHint("data");}
                                contBits++;
                            }
                        }
                    }

                    int[] scoresMasking = new int[8]; //Data masking
                    for(int i = 0; i < 8; i++) scoresMasking[i] = Evaluaciones(DataMasking(celdas, i)); //Patrones de data masking

                    contBits = scoresMasking[0]; //Encontrar el menor penalty score
                    for(int i = 1; i < scoresMasking.length; i++) contBits = scoresMasking[i] < contBits ? scoresMasking[i] : contBits;

                    //Version final del qr con el data masking
                    for(int i = 0; i < scoresMasking.length; i++) { if(scoresMasking[i] == contBits) {contBits = i; break;} }
                    celdas = DataMasking(celdas, contBits);
                    textViewVersion.setText("Versión: " + version + " - Tamaño: " + tamanio + "x" + tamanio + " - Mask Pattern: " + contBits);

                    String format = ""; //Información de formato
                    switch (errorCorrection)
                    {
                        case 7:
                            if(contBits == 0) format = "111011111000100";
                            else if(contBits == 1) format = "111001011110011";
                            else if(contBits == 2) format = "111110110101010";
                            else if(contBits == 3) format = "111100010011101";
                            else if(contBits == 4) format = "110011000101111";
                            else if(contBits == 5) format = "110001100011000";
                            else if(contBits == 6) format = "110110001000001";
                            else if(contBits == 7) format = "110100101110110"; break;
                        case 15:
                            if(contBits == 0) format = "101010000010010";
                            else if(contBits == 1) format = "101000100100101";
                            else if(contBits == 2) format = "101111001111100";
                            else if(contBits == 3) format = "101101101001011";
                            else if(contBits == 4) format = "100010111111001";
                            else if(contBits == 5) format = "100000011001110";
                            else if(contBits == 6) format = "100111110010111";
                            else if(contBits == 7) format = "100101010100000"; break;
                        case 25:
                            if(contBits == 0) format = "011010101011111";
                            else if(contBits == 1) format = "011000001101000";
                            else if(contBits == 2) format = "011111100110001";
                            else if(contBits == 3) format = "011101000000110";
                            else if(contBits == 4) format = "010010010110100";
                            else if(contBits == 5) format = "010000110000011";
                            else if(contBits == 6) format = "010111011011010";
                            else if(contBits == 7) format = "010101111101101"; break;
                        case 30:
                            if(contBits == 0) format = "001011010001001";
                            else if(contBits == 1) format = "001001110111110";
                            else if(contBits == 2) format = "001110011100111";
                            else if(contBits == 3) format = "001100111010000";
                            else if(contBits == 4) format = "000011101100010";
                            else if(contBits == 5) format = "000001001010101";
                            else if(contBits == 6) format = "000110100001100";
                            else if(contBits == 7) format = "000100000111011"; break;
                    }

                    int auxCont = 0, auxReversa = format.length()-1; //Agregar la fotmat information
                    for(int i = 4; i < 13; i++)
                    {
                        if(celdas[12][i].getText() == "2" && format.charAt(auxCont) == '0') {celdas[12][i].setBackgroundColor(Color.WHITE); auxCont++;} //Horizontal izquierda
                        else if(celdas[12][i].getText() == "2" && format.charAt(auxCont) == '1') {celdas[12][i].setBackgroundColor(Color.BLACK); auxCont++;} //Horizontal izquierda

                        if(celdas[i][12].getText() == "2" && format.charAt(auxReversa) == '0') {celdas[i][12].setBackgroundColor(Color.WHITE); auxReversa--;} //Vertical arriba
                        else if(celdas[i][12].getText() == "2" && format.charAt(auxReversa) == '1') {celdas[i][12].setBackgroundColor(Color.BLACK); auxReversa--;} //Vertical arriba
                    }
                    auxCont = 0; auxReversa = format.length()-1;
                    for(int i = tamanio+4; i >= tamanio-4; i--)
                    {
                        if(celdas[12][i].getText() == "2" && format.charAt(auxReversa) == '0') {celdas[12][i].setBackgroundColor(Color.WHITE); auxReversa--;} //Horizontal derecha
                        else if(celdas[12][i].getText() == "2" && format.charAt(auxReversa) == '1') {celdas[12][i].setBackgroundColor(Color.BLACK); auxReversa--;} //Horizontal derecha

                        if(celdas[i][12].getText() == "2" && format.charAt(auxCont) == '0') {celdas[i][12].setBackgroundColor(Color.WHITE); auxCont++;} //Vertical abajo
                        else if(celdas[i][12].getText() == "2" && format.charAt(auxCont) == '1') {celdas[i][12].setBackgroundColor(Color.BLACK); auxCont++;} //Vertical abajo
                    }

                    if(version >= 7) //Agregar la version information area para los qr a partir de la version 7
                    {
                        auxReversa = versionInformation.length()-1; auxCont = versionInformation.length()-1;
                        for(int i = tamanio-6; i >= tamanio-3; i++)
                        {
                            for(int j = 4; j < 11; i++)
                            {
                                if(celdas[i][j].getText() == "3" && versionInformation.charAt(auxReversa) == '0') {celdas[i][j].setBackgroundColor(Color.WHITE); auxReversa--;} //Superior derecha
                                else if(celdas[i][j].getText() == "3" && versionInformation.charAt(auxReversa) == '1') {celdas[i][j].setBackgroundColor(Color.BLACK); auxReversa--;} //Superior derecha

                                if(celdas[j][i].getText() == "3" && versionInformation.charAt(auxCont) == '0') {celdas[j][i].setBackgroundColor(Color.WHITE); auxCont--;} //Inferior izquierda
                                else if(celdas[j][i].getText() == "3" && versionInformation.charAt(auxCont) == '1') {celdas[j][i].setBackgroundColor(Color.BLACK); auxCont--;} //Inferior izquierda
                            }
                        }
                    }

                    for(int i = 4; i < tamanio+4; i++){ for(int j = 4; j < tamanio+4; j++) { celdas[i][j].setText(""); celdas[i][j].setHint(""); } } //Quitar el texto de los cuadros

                }
            }
        });
    }

    public int ObtenerCodigoAlfa(char c){
        switch (c)
        {
            case '0': return 0;
            case '1': return 1;
            case '2': return 2;
            case '3': return 3;
            case '4': return 4;
            case '5': return 5;
            case '6': return 6;
            case '7': return 7;
            case '8': return 8;
            case '9': return 9;
            case 'A': return 10;
            case 'B': return 11;
            case 'C': return 12;
            case 'D': return 13;
            case 'E': return 14;
            case 'F': return 15;
            case 'G': return 16;
            case 'H': return 17;
            case 'I': return 18;
            case 'J': return 19;
            case 'K': return 20;
            case 'L': return 21;
            case 'M': return 22;
            case 'N': return 23;
            case 'O': return 24;
            case 'P': return 25;
            case 'Q': return 26;
            case 'R': return 27;
            case 'S': return 28;
            case 'T': return 29;
            case 'U': return 30;
            case 'V': return 31;
            case 'W': return 32;
            case 'X': return 33;
            case 'Y': return 34;
            case 'Z': return 35;
            case ' ': return 36;
            case '$': return 37;
            case '%': return 38;
            case '*': return 39;
            case '+': return 40;
            case '-': return 41;
            case '.': return 42;
            case '/': return 43;
            case ':': return 44;
            default: return 45;
        }
    }

    public int TablaLog(int polinomio0)
    {
        if(polinomio0==1 || polinomio0==2 || polinomio0==4 || polinomio0==8 || polinomio0==16 || polinomio0==32 || polinomio0==64 || polinomio0==128)
            return (int)(Math.log10(polinomio0)/Math.log10(2));
        else
        {
            int auxPolinomio = 128;
            for(int i = 8; i < 256; i++)
            {
                auxPolinomio *= 2;
                if(auxPolinomio >= 256) auxPolinomio = auxPolinomio ^ 285;
                if(auxPolinomio == polinomio0) return i;
            }
        }
        return 0;
    }

    public int TablaAntilog(int auxGenPolinomial)
    {
        if(auxGenPolinomial < 8) return (int)Math.pow(2, auxGenPolinomial);
        else
        {
            int auxPolinomio = 128;
            for(int j = 8; j <= auxGenPolinomial; j++)
            {
                auxPolinomio *= 2;
                if(auxPolinomio >= 256) auxPolinomio = auxPolinomio ^ 285;
            }
            return auxPolinomio;
        }
    }

    public int[] DefinirGeneradorPolinomial(int bloques)
    {
        switch (bloques)
        {
            case 7: return new int[]{0, 87, 229, 146, 149, 238, 102, 21};
            case 10: return new int[]{0, 251, 67, 46, 61, 118, 70, 64, 94, 32, 45};
            case 13: return new int[]{0, 74, 152, 176, 100, 86, 100, 106, 104, 130, 218, 206, 140, 78};
            case 15: return new int[]{0, 8, 183, 61, 91, 202, 37, 51, 58, 58, 237, 140, 124, 5, 99, 105};
            case 16: return new int[]{0, 120, 104, 107, 109, 102, 161, 76, 3, 91, 191, 147, 169, 182, 194, 225, 120};
            case 17: return new int[]{0, 43, 139, 206, 78, 43, 239, 123, 206, 214, 147, 24, 99, 150, 39, 243, 163, 136};
            case 18: return new int[]{0, 215, 234, 158, 94, 184, 97, 118, 170, 79, 187, 152, 148, 252, 179, 5, 98, 96, 153};
            case 20: return new int[]{0, 17, 60, 79, 50, 61, 163, 26, 187, 202, 180, 221, 225, 83, 239, 156, 164, 212, 212, 188, 190};
            case 22: return new int[]{0, 210, 171, 247, 242, 93, 230, 14, 109, 221, 53, 200, 74, 8, 172, 98, 80, 219, 134, 160, 105, 165, 231};
            case 24: return new int[]{0, 229, 121, 135, 48, 211, 117, 251, 126, 159, 180, 169, 152, 192, 226, 228, 218, 111, 0, 117, 232, 87, 96, 227, 21};
            case 26: return new int[]{0, 173, 125, 158, 2, 103, 182, 118, 17, 145, 201, 111, 28, 165,
                    53, 161, 21, 245, 142, 13, 102, 48, 227, 153, 145, 218, 70};
            case 28: return new int[]{0, 168, 223, 200, 104, 224, 234, 108, 180, 110, 190, 195, 147, 205,
                    27, 232, 201, 21, 43, 245, 87, 42, 195, 212, 119, 242, 37, 9,123};
            case 30: return new int[]{0, 41, 173, 145, 152, 216, 31, 179, 182, 50, 48, 110, 86, 239, 96,
                    222, 125, 42, 173, 226, 193, 224, 130, 156, 37, 251, 216, 238, 40, 192, 180};
            default: return new int[0];
        }
    }

    public int[] XORprocedimiento(int[] polinomio)
    {
        //Definir el generador polinomial, el numero es el exponente de 2
        int[] genPolinomial = DefinirGeneradorPolinomial(bloquesCorreccion);

        //Convertir el primer elemento del mensaje polinomial a la notación alfa - log
        int alfaPolinomio = TablaLog(polinomio[0]);

        //Multiplicar el generador polinomial por el primer elemento del mensaje polinomial en su notacion alfa
        for(int i = 0; i < genPolinomial.length; i++)
        {
            genPolinomial[i] = genPolinomial[i] + alfaPolinomio;
            if(genPolinomial[i] >= 256) genPolinomial[i] = genPolinomial[i] % 255;

            genPolinomial[i] = TablaAntilog(genPolinomial[i]); //Convertir de la notacion alfa a decimal - Antilog
        }

        if(polinomio.length > genPolinomial.length)  //Si el generador polinomial es mas chico que el mensaje polinomial
        {//XOR el generador polinomial con el mensaje polinomial y con 0 para lo que falte
            for(int i = 0; i < genPolinomial.length; i++) polinomio[i] = genPolinomial[i] ^ polinomio[i];
            for(int i = genPolinomial.length; i < polinomio.length; i++) polinomio[i] = polinomio[i] ^ 0;
            return Arrays.copyOfRange(polinomio, 1, polinomio.length); //Eliminar el primer elemento del mensaje polinomial, que resulta en 0
        }
        else
        {//XOR el generador polinomial con el mensaje polinomial y con 0 para lo que falte
            for(int i = 0; i < polinomio.length; i++) genPolinomial[i] = polinomio[i] ^ genPolinomial[i];
            for(int i = polinomio.length; i < genPolinomial.length; i++) genPolinomial[i] = genPolinomial[i] ^ 0;
            return Arrays.copyOfRange(genPolinomial, 1, genPolinomial.length); //Eliminar el primer elemento del mensaje polinomial, que resulta en 0
        }
    }

    public int[] IntercalarCodewords(int[][] grupo1, int[][] grupo2, int tamanioPoli, int a, int b)
    {
        int[] polinomio = new int[tamanioPoli];
        int longPoli = 0, long1 = 0, long2 = 0;
        while(longPoli < polinomio.length)
        {
            if(long1 < a)
            {
                for(int i = 0; i < grupo1.length; i++)
                {
                    polinomio[longPoli] = grupo1[i][long1];
                    longPoli++;
                    long1++;
                }
            }
            if(long2 < b)
            {
                for(int i = 0; i < grupo2.length; i++)
                {
                    polinomio[longPoli] = grupo2[i][long2];
                    longPoli++;
                    long2++;
                }
            }
        }
        return polinomio;
    }

    public int Evaluacion1(int modo, TextView[][] celdas)
    {
        int penalty = 0;
        for(int i = 4; i < tamanio+4; i++)
        {
            ArrayList<String> bits = new ArrayList<>(); //Almacenar los datos en un arrayList
            for(int j = 4; j < tamanio+4; j++)
            {
                if(modo == 1) { if(celdas[i][j].getText() == "0" || celdas[i][j].getText() == "1") bits.add((String)celdas[i][j].getText()); }
                else if(modo == 2) { if(celdas[j][i].getText() == "0" || celdas[j][i].getText() == "1") bits.add((String)celdas[j][i].getText()); }
            }

            ArrayList<Integer> consecutivos = new ArrayList<>(); //Contar los caracteres consecutivos
            String ultimoBit = bits.get(0);
            int contBits = 1;
            for(int j = 1; j < bits.size(); j++)
            {
                if(bits.get(j) == ultimoBit) contBits++;
                else
                {
                    consecutivos.add(contBits);
                    contBits = 1;
                    ultimoBit = bits.get(j);
                }
            }
            consecutivos.add(contBits);

            for(int j = 0; j < consecutivos.size(); j++) //Añadir los penaltyPoints de la primera evaluacion
            {
                if(consecutivos.get(j) == 5) penalty += 3;
                else if(consecutivos.get(j) > 5) penalty += 3 + (consecutivos.get(j)-5);
            }
        }
        return penalty;
    }

    public int Evaluaciones(TextView[][] celdas)
    {
        int penaltyPoints = Evaluacion1(1, celdas) + Evaluacion1(2, celdas); //Evaluacion #1: 5 o mas modulos iguales en una fila o columna

        for(int i = 4; i < tamanio+3; i++) //Evaluacion 2: buscar cuadros de 2x2 con los mismos modulos
        {
            for(int j = 4; j < tamanio+3; j++)
            {
                if(celdas[i][j].getText() == celdas[i+1][j].getText() && celdas[i][j].getText() == celdas[i][j+1].getText()
                        && celdas[i][j].getText() == celdas[i+1][j+1].getText()) penaltyPoints += 3;
            }
        }

        for(int i = 4; i < tamanio+4; i++) //Evaluacion 3: buscar 2 patrones especificos
        {
            for(int j = 4; j < tamanio-6; j++)
            {
                if(celdas[i][j].getText() == "1" && celdas[i][j+1].getText() == "0" && celdas[i][j+2].getText() == "1" && celdas[i][j+3].getText() == "1"
                        && celdas[i][j+4].getText() == "1" && celdas[i][j+5].getText() == "0" && celdas[i][j+6].getText() == "1" && celdas[i][j+7].getText() == "0"
                        && celdas[i][j+8].getText() == "0" && celdas[i][j+9].getText() == "0" && celdas[i][j+10].getText() == "0") penaltyPoints += 40;
                if(celdas[i][j].getText() == "0" && celdas[i][j+1].getText() == "0" && celdas[i][j+2].getText() == "0" && celdas[i][j+3].getText() == "0"
                        && celdas[i][j+4].getText() == "1" && celdas[i][j+5].getText() == "0" && celdas[i][j+6].getText() == "1" && celdas[i][j+7].getText() == "1"
                        && celdas[i][j+8].getText() == "1" && celdas[i][j+9].getText() == "0" && celdas[i][j+10].getText() == "1") penaltyPoints += 40;
            }
        }

        int negros = 0, blancos = 0; //Evaluacion 4: contar si hay mas modulos negros o blancos
        for(int i = 4; i < tamanio+4; i++)
        {
            for(int j = 4; j < tamanio+4; j++)
            {
                if(celdas[i][j].getText() == "1") negros++;
                else if(celdas[i][j].getText() == "0") blancos++;
            }
        }
        float percentNegro = ((float)negros / (float)(negros+blancos)) * 100;
        int multiploBajo = (int)percentNegro, multiploAlto = (int)percentNegro;
        if(percentNegro%5 != 0)
        {
            for(int i = 0 ; i < 5; i++)
            {
                multiploAlto++;
                if(multiploAlto%5 == 0) break;
            }
            for(int i = 0 ; i < 5; i++)
            {
                multiploBajo--;
                if(multiploBajo%5 == 0) break;
            }
        }
        multiploAlto = Math.abs(multiploAlto - 50) / 5;
        multiploBajo = Math.abs(multiploBajo - 50) / 5;
        penaltyPoints += multiploAlto < multiploBajo ? multiploAlto * 10 : multiploBajo * 10;
        return penaltyPoints;
    }

    public TextView[][] DataMasking(TextView[][] celdas, int pattern)
    {
        TextView[][] auxCeldas = celdas;
        for(int j = 4; j < tamanio+4; j++) //Filas
        {
            for(int k = 4; k < tamanio+4; k++) //Columnas
            {
                if(auxCeldas[j][k].getHint() == "data")
                {
                    switch (pattern)
                    {
                        case 0:
                            if((j + k)%2 == 0)
                            {
                                if(auxCeldas[j][k].getText() == "0") {auxCeldas[j][k].setText("1"); auxCeldas[j][k].setBackgroundColor(Color.BLACK);}
                                else if(auxCeldas[j][k].getText() == "1") {auxCeldas[j][k].setText("0"); auxCeldas[j][k].setBackgroundColor(Color.WHITE);}
                            }
                            break;
                        case 1:
                            if(j%2 == 0)
                            {
                                if(auxCeldas[j][k].getText() == "0") {auxCeldas[j][k].setText("1"); auxCeldas[j][k].setBackgroundColor(Color.BLACK);}
                                else if(auxCeldas[j][k].getText() == "1") {auxCeldas[j][k].setText("0"); auxCeldas[j][k].setBackgroundColor(Color.WHITE);}
                            }
                            break;
                        case 2:
                            if(k%3 == 0)
                            {
                                if(auxCeldas[j][k].getText() == "0") {auxCeldas[j][k].setText("1"); auxCeldas[j][k].setBackgroundColor(Color.BLACK);}
                                else if(auxCeldas[j][k].getText() == "1") {auxCeldas[j][k].setText("0"); auxCeldas[j][k].setBackgroundColor(Color.WHITE);}
                            }
                            break;
                        case 3:
                            if((j + k)%3 == 0)
                            {
                                if(auxCeldas[j][k].getText() == "0") {auxCeldas[j][k].setText("1"); auxCeldas[j][k].setBackgroundColor(Color.BLACK);}
                                else if(auxCeldas[j][k].getText() == "1") {auxCeldas[j][k].setText("0"); auxCeldas[j][k].setBackgroundColor(Color.WHITE);}
                            }
                            break;
                        case 4:
                            if((Math.floor(j/2) + Math.floor(k/3))%2 == 0)
                            {
                                if(auxCeldas[j][k].getText() == "0") {auxCeldas[j][k].setText("1"); auxCeldas[j][k].setBackgroundColor(Color.BLACK);}
                                else if(auxCeldas[j][k].getText() == "1") {auxCeldas[j][k].setText("0"); auxCeldas[j][k].setBackgroundColor(Color.WHITE);}
                            }
                            break;
                        case 5:
                            if(((j * k)%2) + ((j * k)%3) == 0)
                            {
                                if(auxCeldas[j][k].getText() == "0") {auxCeldas[j][k].setText("1"); auxCeldas[j][k].setBackgroundColor(Color.BLACK);}
                                else if(auxCeldas[j][k].getText() == "1") {auxCeldas[j][k].setText("0"); auxCeldas[j][k].setBackgroundColor(Color.WHITE);}
                            }
                            break;
                        case 6:
                            if((((j * k)%2) + ((j * k)%3))%2 == 0)
                            {
                                if(auxCeldas[j][k].getText() == "0") {auxCeldas[j][k].setText("1"); auxCeldas[j][k].setBackgroundColor(Color.BLACK);}
                                else if(auxCeldas[j][k].getText() == "1") {auxCeldas[j][k].setText("0"); auxCeldas[j][k].setBackgroundColor(Color.WHITE);}
                            }
                            break;
                        case 7:
                            if((((j + k)%2) + ((j * k)%3))%2 == 0)
                            {
                                if(auxCeldas[j][k].getText() == "0") {auxCeldas[j][k].setText("1"); auxCeldas[j][k].setBackgroundColor(Color.BLACK);}
                                else if(auxCeldas[j][k].getText() == "1") {auxCeldas[j][k].setText("0"); auxCeldas[j][k].setBackgroundColor(Color.WHITE);}
                            }
                            break;
                    }
                }
            }
        }
        return auxCeldas;
    }
}

