package de.thm.dialogexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Yannick Bals on 03.05.2018.
 */

public class MainActivity extends AppCompatActivity {

    Button confButton, editButton, customButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity_layout);

        confButton = findViewById(R.id.confDialogButton);
        editButton = findViewById(R.id.editDialogButton);
        customButton = findViewById(R.id.customDialogButton);
        ButtonListener listener = new ButtonListener();
        confButton.setOnClickListener(listener);
        editButton.setOnClickListener(listener);
        customButton.setOnClickListener(listener);

    }

    private void showConfDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm action");
        builder.setMessage("Do you really want to confirm this?");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Ok pressed", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Don't care", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Don't care pressed", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();

    }

    private void showEditDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Dialog");
        builder.setMessage("Enter something below.");

        //Create a LinearLayout to fit the EditText in. This is necessary to display margins.
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(30, 0, 30, 0);

        final EditText editText = new EditText(this);
        editText.setHint("Enter something");

        layout.addView(editText, params);
        builder.setView(layout);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText() != null && editText.getText().toString().length() > 0) {
                    Toast.makeText(MainActivity.this, "You entered: " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter something.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();

    }

    private void showCustomDialog() {

    }

    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.confDialogButton:
                    showConfDialog();
                    break;
                case R.id.editDialogButton:
                    showEditDialog();
                    break;
                case R.id.customDialogButton:
                    showCustomDialog();
                    break;
                default:
                    break;
            }

        }
    }

}
