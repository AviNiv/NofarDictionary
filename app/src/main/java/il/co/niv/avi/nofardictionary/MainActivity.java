package il.co.niv.avi.nofardictionary;

import android.app.Dialog;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_new :
                showChangesDialog(null);
                return true;

            case R.id.action_exit:
                finish();
                return true;

            case R.id.action_settings :
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showChangesDialog(final Word word) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_changes);

        TextView title = (TextView)dialog.findViewById(R.id.custom_title);
        final EditText editHebrew = (EditText)dialog.findViewById(R.id.custom_edit_hebrew);
        final EditText editEnglish = (EditText)dialog.findViewById(R.id.custom_edit_english);
        Button save = (Button)dialog.findViewById(R.id.custom_button_save);
        Button cancel = (Button)dialog.findViewById(R.id.custom_button_cancel);
        LinearLayout root = (LinearLayout)dialog.findViewById(R.id.custom_root_layout);
        final boolean isEditOperation = (word != null);

        //set views for edit operation (otherwise proceed as new)
        if (isEditOperation) {
            title.setText(R.string.edit_word_label);
            editHebrew.setText(word.getHebrew());
            editEnglish.setText(word.getEnglish());
            root.setBackgroundColor(getResources().getColor(R.color.blue));
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hebrew = editHebrew.getText().toString();
                String english = editEnglish.getText().toString();
                if (isEditOperation) {
                    //edit
                    Word editWord = new Word(word.getId(), word.getHebrew(), word.getEnglish());
                } else {
                    //new
                }
                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dialog.dismiss();
            }
        });

        dialog.show();
    }
}
