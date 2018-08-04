package loginapp.com.files;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private final static String  FILE_NAME="bvimit.txt";
    private EditText filecontents;
    private Button save;
    private Button read;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filecontents=findViewById(R.id.edittext);
        save=findViewById(R.id.savebutton);
        read=findViewById(R.id.readbutton);
        textview=findViewById(R.id.textview);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    saveContentstoFile();
                    Toast.makeText(MainActivity.this,"contents are saved",Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    readContentstoFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


        private void saveContentstoFile() throws IOException{
        String content=filecontents.getText().toString();
            FileOutputStream stream = openFileOutput(FILE_NAME, Context.MODE_APPEND | Context.MODE_PRIVATE);
            OutputStreamWriter writer=new OutputStreamWriter(stream);
            BufferedWriter bufferedWriter=new BufferedWriter(writer);
            bufferedWriter.write(content);
            bufferedWriter.close();
            writer.close();
            stream.close();
            }



        private void readContentstoFile()throws IOException{
            FileInputStream istream = openFileInput(FILE_NAME);
            InputStreamReader reader=new InputStreamReader(istream);
            BufferedReader bufferedReader=new BufferedReader(reader);

            textview.setText(bufferedReader.readLine());
            bufferedReader.close();
            reader.close();
            istream.close();
        }

    }

