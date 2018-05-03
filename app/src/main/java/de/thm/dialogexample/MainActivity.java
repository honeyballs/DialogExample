package de.thm.dialogexample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Yannick Bals on 03.05.2018.
 */

public class MainActivity extends AppCompatActivity {

    private Button confButton, editButton, customButton, choiceButton;
    private ArrayList<Integer> choices;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity_layout);

        confButton = findViewById(R.id.confDialogButton);
        editButton = findViewById(R.id.editDialogButton);
        customButton = findViewById(R.id.customDialogButton);
        choiceButton = findViewById(R.id.choiceDialogButton);
        ButtonListener listener = new ButtonListener();
        confButton.setOnClickListener(listener);
        editButton.setOnClickListener(listener);
        customButton.setOnClickListener(listener);
        choiceButton.setOnClickListener(listener);

        choices = new ArrayList<>();

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Custom Dialog");

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.custom_dialog, null);

        final EditText text = v.findViewById(R.id.dialogEditText);
        final Spinner spinner = v.findViewById(R.id.dialogSpinner);

        //Initialize the Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        builder.setView(v);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (text.getText() != null && text.getText().toString().length() >= 0) {
                    Toast.makeText(MainActivity.this, "You entered: " + text.getText().toString() + " and chose: " + spinner.getSelectedItem(), Toast.LENGTH_SHORT).show();
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

    private void showChoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Multiple Choice");

        builder.setMultiChoiceItems(R.array.choice_items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    choices.add(which);
                } else if (choices.contains(which)){
                    choices.remove(Integer.valueOf(which));
                }
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selected = "";
                for (int choice : choices) {
                    selected += choice + " ";
                }
                Toast.makeText(MainActivity.this, "You selected the choices: " + selected, Toast.LENGTH_SHORT).show();
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
                case R.id.choiceDialogButton:
                    showChoiceDialog();
                    break;
                default:
                    break;
            }

        }
    }

}
