package com.example.retrofitgithub;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CredentialsDialog extends DialogFragment {

    EditText usernameEditText;
    EditText passwordEditText;
    ICredentialsDialogListener listener;

    public interface ICredentialsDialogListener {
        void onDialogPositiveClick(String username, String password);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (getActivity() instanceof ICredentialsDialogListener) {
            listener = (ICredentialsDialogListener) getActivity();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_credentials, null);
        usernameEditText = (EditText) view.findViewById(R.id.username_edittext);
        passwordEditText = (EditText) view.findViewById(R.id.password_edittext);

        usernameEditText.setText(getArguments().getString("username"));
        passwordEditText.setText(getArguments().getString("password"));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Credentials")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (listener != null) {
                            listener.onDialogPositiveClick(
                                    usernameEditText.getText().toString(),
                                    passwordEditText.getText().toString());
                        }
                    }
                });

        return builder.create();
    }
}
