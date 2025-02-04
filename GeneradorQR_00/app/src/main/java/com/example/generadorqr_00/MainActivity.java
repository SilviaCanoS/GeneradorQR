package com.example.generadorqr_00;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textViewVersion, textViewBinario;
    RadioGroup radioGroup;
    RadioButton radioL7, radioM15, radioQ25, radioH30;

    int errorCorrection = 7, version, tamanio, tamanioIndicador, bitsNecesarios, bitsTotales = 0;
    String cifrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        textViewVersion = findViewById(R.id.textViewVersion);
        textViewBinario = findViewById(R.id.textViewBinario);
        radioGroup = findViewById(R.id.radioGroup);
        radioL7 = findViewById(R.id.radioL7);
        radioM15 = findViewById(R.id.radioM15);
        radioQ25 = findViewById(R.id.radioQ25);
        radioH30 = findViewById(R.id.radioH30);

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
                version = 1;
                tamanio = 0;
                String text = "hola mundo";
                if(editText.getText().toString().isEmpty()) editText.setText("hola mundo"); //Control de error
                else text = editText.getText().toString();
                String binario = "";

                //Analisis de datos: numerico o alfanumerico
                if(text.matches("^[0-9]*$")) //Si es una cadena solo numerica
                {
                    cifrado = "0001 "; //Agregar el indicador de modo
                    if(text.length()%3 == 1) text += "  "; //Control de errores
                    else if(text.length()%3 == 2) text += " ";
                    //Dividir cada 3 caracteres y convertir a binario
                    for(int i = 0; i < text.length(); i += 3)
                    {
                        int decimal = Integer.parseInt(text.substring(i, i+3).trim()); //Convertir a decimal
                        binario += Integer.toBinaryString(decimal) + " "; //Convertir a binario
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
                    cifrado = "0010 "; //Agregar el indicador de modo
                    int limiteFor = text.length();
                    if(text.length()%2 == 1) limiteFor = text.length() - 1; //Control de errores
                    for(int i = 0; i < limiteFor; i += 2) //Separar cada 2 caracteres
                    {
                        int alfa1 = ObtenerCodigoAlfa(text.charAt(i)); //Obtener codigo de la tabla
                        int alfa2 = ObtenerCodigoAlfa(text.charAt(i+1)); //Obtener codigo de la tabla
                        String auxBinario = Integer.toBinaryString(alfa1*45 + alfa2); //Convertir a binario
                        for(int j = auxBinario.length(); j < 11; j++) auxBinario = "0" + auxBinario; //Convertir a 11 bits
                        binario += auxBinario + " ";
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
                    cifrado = "0100 "; //Agregar el indicador de modo
                    for(int i = 0; i < text.length(); i++) //Convertir cada caracter en binario (ASCII)
                    {
                        String auxBinario = Integer.toBinaryString((int)text.charAt(i)) + " ";
                        for(int j = auxBinario.length(); j <= 8; j++) auxBinario = "0" + auxBinario; //Convertir a 8 bits
                        binario += auxBinario + " ";
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
                    switch (version) //Determinar las dimensiones del codigo
                    {
                        case 1: tamanio = 21; break;
                        case 2: tamanio = 25; break;
                        case 3: tamanio = 29; break;
                        case 4: tamanio = 33; break;
                        case 5: tamanio = 37; break;
                        case 6: tamanio = 41; break;
                        case 7: tamanio = 45; break;
                        case 8: tamanio = 49; break;
                        case 9: tamanio = 53; break;
                        case 10: tamanio = 57; break;
                        case 11: tamanio = 61; break;
                        case 12: tamanio = 65; break;
                        case 13: tamanio = 69; break;
                        case 14: tamanio = 73; break;
                        case 15: tamanio = 77; break;
                        case 16: tamanio = 81; break;
                        case 17: tamanio = 85; break;
                        case 18: tamanio = 89; break;
                        case 19: tamanio = 93; break;
                        case 20: tamanio = 97; break;
                    }

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

                    cifrado += contador + " " + binario; //Agregar el contador y los datos cifrados

                    switch (version) //Determinar el numero de bits necesarias
                    {
                        case 1:
                            if(errorCorrection == 7) bitsNecesarios = 19;
                            else if(errorCorrection == 15) bitsNecesarios = 16;
                            else if(errorCorrection == 25) bitsNecesarios = 13;
                            else if(errorCorrection == 30) bitsNecesarios = 9;
                            break;
                        case 2:
                            if(errorCorrection == 7) bitsNecesarios = 34;
                            else if(errorCorrection == 15) bitsNecesarios = 28;
                            else if(errorCorrection == 25) bitsNecesarios = 22;
                            else if(errorCorrection == 30) bitsNecesarios = 16;
                            break;
                        case 3:
                            if(errorCorrection == 7) bitsNecesarios = 55;
                            else if(errorCorrection == 15) bitsNecesarios = 44;
                            else if(errorCorrection == 25) bitsNecesarios = 34;
                            else if(errorCorrection == 30) bitsNecesarios = 26;
                            break;
                        case 4:
                            if(errorCorrection == 7) bitsNecesarios = 80;
                            else if(errorCorrection == 15) bitsNecesarios = 64;
                            else if(errorCorrection == 25) bitsNecesarios = 48;
                            else if(errorCorrection == 30) bitsNecesarios = 36;
                        case 5:
                            if(errorCorrection == 7) bitsNecesarios = 108;
                            else if(errorCorrection == 15) bitsNecesarios = 86;
                            else if(errorCorrection == 25) bitsNecesarios = 62;
                            else if(errorCorrection == 30) bitsNecesarios = 46;
                            break;
                        case 6:
                            if(errorCorrection == 7) bitsNecesarios = 136;
                            else if(errorCorrection == 15) bitsNecesarios = 108;
                            else if(errorCorrection == 25) bitsNecesarios = 76;
                            else if(errorCorrection == 30) bitsNecesarios = 60;
                            break;
                        case 7:
                            if(errorCorrection == 7) bitsNecesarios = 156;
                            else if(errorCorrection == 15) bitsNecesarios = 124;
                            else if(errorCorrection == 25) bitsNecesarios = 88;
                            else if(errorCorrection == 30) bitsNecesarios = 66;
                            break;
                        case 8:
                            if(errorCorrection == 7) bitsNecesarios = 194;
                            else if(errorCorrection == 15) bitsNecesarios = 154;
                            else if(errorCorrection == 25) bitsNecesarios = 110;
                            else if(errorCorrection == 30) bitsNecesarios = 86;
                            break;
                        case 9:
                            if(errorCorrection == 7) bitsNecesarios = 232;
                            else if(errorCorrection == 15) bitsNecesarios = 182;
                            else if(errorCorrection == 25) bitsNecesarios = 132;
                            else if(errorCorrection == 30) bitsNecesarios = 100;
                            break;
                        case 10:
                            if(errorCorrection == 7) bitsNecesarios = 274;
                            else if(errorCorrection == 15) bitsNecesarios = 216;
                            else if(errorCorrection == 25) bitsNecesarios = 154;
                            else if(errorCorrection == 30) bitsNecesarios = 122;
                            break;
                        case 11:
                            if(errorCorrection == 7) bitsNecesarios = 324;
                            else if(errorCorrection == 15) bitsNecesarios = 254;
                            else if(errorCorrection == 25) bitsNecesarios = 180;
                            else if(errorCorrection == 30) bitsNecesarios = 140;
                            break;
                        case 12:
                            if(errorCorrection == 7) bitsNecesarios = 370;
                            else if(errorCorrection == 15) bitsNecesarios = 290;
                            else if(errorCorrection == 25) bitsNecesarios = 206;
                            else if(errorCorrection == 30) bitsNecesarios = 158;
                            break;
                        case 13:
                            if(errorCorrection == 7) bitsNecesarios = 428;
                            else if(errorCorrection == 15) bitsNecesarios = 334;
                            else if(errorCorrection == 25) bitsNecesarios = 244;
                            else if(errorCorrection == 30) bitsNecesarios = 180;
                            break;
                        case 14:
                            if(errorCorrection == 7) bitsNecesarios = 461;
                            else if(errorCorrection == 15) bitsNecesarios = 365;
                            else if(errorCorrection == 25) bitsNecesarios = 261;
                            else if(errorCorrection == 30) bitsNecesarios = 197;
                            break;
                        case 15:
                            if(errorCorrection == 7) bitsNecesarios = 523;
                            else if(errorCorrection == 15) bitsNecesarios = 415;
                            else if(errorCorrection == 25) bitsNecesarios = 295;
                            else if(errorCorrection == 30) bitsNecesarios = 223;
                            break;
                        case 16:
                            if(errorCorrection == 7) bitsNecesarios = 589;
                            else if(errorCorrection == 15) bitsNecesarios = 453;
                            else if(errorCorrection == 25) bitsNecesarios = 325;
                            else if(errorCorrection == 30) bitsNecesarios = 253;
                            break;
                        case 17:
                            if(errorCorrection == 7) bitsNecesarios = 647;
                            else if(errorCorrection == 15) bitsNecesarios = 507;
                            else if(errorCorrection == 25) bitsNecesarios = 367;
                            else if(errorCorrection == 30) bitsNecesarios = 283;
                            break;
                        case 18:
                            if(errorCorrection == 7) bitsNecesarios = 721;
                            else if(errorCorrection == 15) bitsNecesarios = 563;
                            else if(errorCorrection == 25) bitsNecesarios = 397;
                            else if(errorCorrection == 30) bitsNecesarios = 313;
                            break;
                        case 19:
                            if(errorCorrection == 7) bitsNecesarios = 795;
                            else if(errorCorrection == 15) bitsNecesarios = 627;
                            else if(errorCorrection == 25) bitsNecesarios = 445;
                            else if(errorCorrection == 30) bitsNecesarios = 341;
                            break;
                        case 20:
                            if(errorCorrection == 7) bitsNecesarios = 861;
                            else if(errorCorrection == 15) bitsNecesarios = 669;
                            else if(errorCorrection == 25) bitsNecesarios = 485;
                            else if(errorCorrection == 30) bitsNecesarios = 385;
                            break;
                    }
                    bitsNecesarios *= 8;
                    textViewVersion.setText("Versión: " + version + " - Tamaño: " + tamanio + "x" + tamanio
                            + " - Bits: " + bitsNecesarios);

                    bitsTotales = 0;  //Medir la longitud de la cadena sin espacios
                    for (int i = 0; i < cifrado.length(); i++)
                    { if(cifrado.charAt(i) != ' ') bitsTotales++; }


                    if(bitsNecesarios - bitsTotales == 1) {cifrado += " " + "0"; bitsTotales++;}//Agregar el terminador
                    else if(bitsNecesarios - bitsTotales == 2) {cifrado += " " + "00"; bitsTotales += 2;}
                    else if(bitsNecesarios - bitsTotales == 3) {cifrado += " " + "000"; bitsTotales += 3;}
                    else if(bitsNecesarios - bitsTotales >= 4) {cifrado += " " + "0000"; bitsTotales += 4;}

                    while (bitsTotales%8 != 0)//Revisar que los bits totales sean multiplos de 8
                    {
                        cifrado += "0";
                        bitsTotales++;
                    }

                    while (bitsTotales < bitsNecesarios) //Revisar que la cadena alcance el valor deseado
                    {
                        cifrado += " " + "11101100";
                        bitsTotales += 8;
                        if(bitsTotales < bitsNecesarios)
                        {
                            cifrado += " " + "00010001";
                            bitsTotales += 8;
                        }
                    }
                    textViewBinario.setText(cifrado);
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
}