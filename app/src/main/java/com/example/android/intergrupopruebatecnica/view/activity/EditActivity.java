package com.example.android.intergrupopruebatecnica.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.android.intergrupopruebatecnica.R;
import com.example.android.intergrupopruebatecnica.presenter.EditPresenter;
import com.example.android.intergrupopruebatecnica.view.base.BaseActivity;
import com.example.android.intergrupopruebatecnica.view.contract.EditView;

import butterknife.BindView;

public class EditActivity extends BaseActivity<EditPresenter> implements EditView {

    private int mStatus;
    private boolean mProspectHasChanged = false;

    @BindView(R.id.edit_progress)
    ProgressBar progressLoading;
    @BindView(R.id.edit_editId)
    TextInputEditText editId;
    @BindView(R.id.edit_editName)
    TextInputEditText editName;
    @BindView(R.id.edit_editSurname)
    TextInputEditText editSurname;
    @BindView(R.id.edit_editTelephone)
    TextInputEditText editTelephone;
    @BindView(R.id.edit_statusSpinner)
    Spinner statusSpinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupSpinner();
    }

    private void setupSpinner() {

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_status_options, android.R.layout.simple_spinner_item);

        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        statusSpinner.setAdapter(genderSpinnerAdapter);

        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.status_pending))) {
                        mStatus = 0; // 0
                    } else if (selection.equals(getString(R.string.status_approved))) {
                        mStatus = 1; // 1
                    } else if (selection.equals(getString(R.string.status_accepted))) {
                        mStatus = 2; // 2
                    } else if (selection.equals(getString(R.string.status_rejected))) {
                        mStatus = 3; // 3
                    } else {
                        mStatus = 4; // 4
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mStatus = 0; // Pending
            }
        });
    }

    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, (dialog, id) -> {
            //delete prospect here!
        });
        builder.setNegativeButton(R.string.cancel, (dialog, id) -> {

            if (dialog != null) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, (dialog, id) -> {

            if (dialog != null) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save:
                //SavePet
                return true;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case android.R.id.home:
                if (!mProspectHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditActivity.this);
                    return true;
                }
                DialogInterface.OnClickListener discardButtonClickListener =
                        (dialogInterface, i) -> NavUtils.navigateUpFromSameTask(EditActivity.this);

                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (!mProspectHasChanged) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                (dialogInterface, i) -> finish();

        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public EditPresenter createPresenter() {
        return new EditPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_edit;
    }

}
