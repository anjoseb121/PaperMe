package co.ajbe.paperme;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Initialize paper context
        Paper.init(this);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final EditText editText = (EditText) findViewById(R.id.editText);
        FloatingActionButton fabWrite = (FloatingActionButton) findViewById(R.id.fabWrite);
        FloatingActionButton fabRead = (FloatingActionButton) findViewById(R.id.fabRead);

        fabWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();
                // Write with key - value
                Paper.book().write("text", text);
                editText.setText("");
                Snackbar.make(view, "Texto guardado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fabRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read from key - default if not found
                String textSaved = Paper.book().read("text", "Not found");
                textView.setText(textSaved);
                Snackbar.make(view, "Texto leido", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
