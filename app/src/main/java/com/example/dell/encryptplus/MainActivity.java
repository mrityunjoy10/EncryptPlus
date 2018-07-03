package com.example.dell.encryptplus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    Button bsend,bclear;
    ImageButton block,bunlock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.edt1);
        e2=(EditText)findViewById(R.id.edt2);
        bsend=(Button)findViewById(R.id.btn1);
        bclear=(Button)findViewById(R.id.btn2);
        block=(ImageButton)findViewById(R.id.imgbtn1);
        bunlock=(ImageButton)findViewById(R.id.imgbtn2);
        SmsManager sms=SmsManager.getDefault();
        block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int f1=0;
                if(e1.getText().length()==0)
                {
                    Toast.makeText(MainActivity.this,"Enter some text",Toast.LENGTH_SHORT).show();
                }
                else if(e2.getText().length()==0 || e2.getText().length()>4)
                {
                    Toast.makeText(MainActivity.this,"Enter a valid key",Toast.LENGTH_SHORT).show();
                }
                else{
                    int a,b,c,d,n,i=0;
                    n=Integer.parseInt(e2.getText().toString());
                    if(n<10)
                    {
                        a=0;
                        b=0;
                        c=0;
                        d=n;
                    }
                    else if(n<100)
                    {
                        a=0;
                        b=0;
                        d=n%10;
                        n=n/10;
                        c=n%10;
                    }
                    else if(n<1000)
                    {
                        a=0;
                        d=n%10;
                        n=n/10;
                        c=n%10;
                        n=n/10;
                        b=n%10;
                    }
                    else {
                        d = n % 10;
                        n = n / 10;
                        c = n % 10;
                        n = n / 10;
                        b = n % 10;
                        n = n / 10;
                        a = n;
                    }
                    int t[]={a,b,c,d};
                    f1++;
                    char l;
                    String s2;
                    int k = 0, m = 0;
                    String s1 = e1.getText().toString();
                    e1.setText("");
                    for(i=0;i<s1.length();i++) {
                        if (i % 2 == 0) {
                            m = s1.charAt(i) + t[k];
                            e1.setText(e1.getText().toString() + (char) m + "");
                            k = (k + 1) % 4;
                        } else {
                            m = s1.charAt(i) - t[k];
                            e1.setText(e1.getText().toString() + (char) m + "");
                            k = (k + 1) % 4;
                        }
                        if(i==s1.length()-1)
                        {
                            s2=e1.getText().toString() +"1"+ "";
                            e1.setText(s2);
                        }
                    }

                }
            }
        });
        bunlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int f1=1;
                //Toast.makeText(MainActivity.this,"Enter some text",Toast.LENGTH_SHORT).show();
                //Toast.makeText(MainActivity.this,e1.getText().toString().charAt(e1.getText().toString().length()-1),Toast.LENGTH_SHORT).show();
                while(f1!=0)
                {
                if(e1.getText().length()==0)
                {
                    Toast.makeText(MainActivity.this,"Enter some text",Toast.LENGTH_SHORT).show();
                }
                else if(e2.getText().length()==0 || e2.getText().length()>4)
                {
                    Toast.makeText(MainActivity.this,"Enter a valid key",Toast.LENGTH_SHORT).show();
                }
                else{
                    int a,b,c,d,n,i=0,h;
                    n=Integer.parseInt(e2.getText().toString());
                    if(n<10)
                    {
                        a=0;
                        b=0;
                        c=0;
                        d=n;
                    }
                    else if(n<100)
                    {
                        a=0;
                        b=0;
                        d=n%10;
                        n=n/10;
                        c=n%10;
                    }
                    else if(n<1000)
                    {
                        a=0;
                        d=n%10;
                        n=n/10;
                        c=n%10;
                        n=n/10;
                        b=n%10;
                    }
                    else {
                        d = n % 10;
                        n = n / 10;
                        c = n % 10;
                        n = n / 10;
                        b = n % 10;
                        n = n / 10;
                        a = n;
                    }
                    int t[]={a,b,c,d};
                    char l;
                    int k = 0, m = 0;
                    String s1 = e1.getText().toString();
                    e1.setText("");
                    for(i=0;i<s1.length()-f1;i++) {
                        if (i % 2 == 0) {
                            m = s1.charAt(i) - t[k];
                            e1.setText(e1.getText().toString() + (char) m + "");
                            k = (k + 1) % 4;
                        } else {
                            m = s1.charAt(i) + t[k];
                            e1.setText(e1.getText().toString() + (char) m + "");
                            k = (k + 1) % 4;
                        }
                    }
                }
            f1--;
                    }
            }
        });
        bclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                e1.setText("");
                e2.setText("");
            }
        });
        bsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, e1.getText().toString());
                sendIntent.setType("text/plain");

                // Do not forget to add this to open whatsApp App specifically
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);

            }
        });
    }
}
