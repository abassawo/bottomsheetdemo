package abasscodes.com.bottomsheetdemo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import abasscodes.com.bottomsheetdemo.databinding.ActivityMainBinding;

/* References source code from https://www.androidhive.info/2017/12/android-working-with-bottom-sheet/  */

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding rootview;
    private BottomSheetBehavior<LinearLayout> sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootview = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initializeViews();
    }

    private void initializeViews() {
        setSupportActionBar(rootview.toolbar);
        rootview.main.btnBottomSheet.setOnClickListener(v -> toggleBottomSheet());
        rootview.main.btnBottomSheetDialog.setOnClickListener(v -> showBottomSheetDialog());
        rootview.main.btnBottomSheetDialogFragment.setOnClickListener(v -> showBottomSheetFragment());

        sheetBehavior = BottomSheetBehavior.from(rootview.sheet.bottomSheet);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        rootview.main.btnBottomSheet.setText("Close Sheet");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        rootview.main.btnBottomSheet.setText("Expand Sheet");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    /**
     * manually opening / closing bottom sheet on button click
     */
    public void toggleBottomSheet() {
        if (sheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            rootview.main.btnBottomSheet.setText("Close sheet");
        } else {
            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            rootview.main.btnBottomSheet.setText("Expand sheet");
        }
    }


    public void showBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet_dialog, null);
        BottomSheetDialog dialog = new BottomSheetDialog(view.getContext());
        dialog.setContentView(view);
        dialog.show();
    }

    public void showBottomSheetFragment() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}
