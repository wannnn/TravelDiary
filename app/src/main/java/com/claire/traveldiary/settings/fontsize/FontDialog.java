package com.claire.traveldiary.settings.fontsize;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.claire.traveldiary.R;

import static com.bumptech.glide.util.Preconditions.checkNotNull;

public class FontDialog extends DialogFragment implements FontContract.View {

    private static final String TAG = "FontDialog";

    private FontContract.Presenter mPresenter;

    private NumberPicker mNumberPicker;
    private Button mOK;
    private Button mCancel;

    public FontDialog() {
    }

    @Override
    public void setPresenter(FontContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View dialogView = inflater.inflate(R.layout.dialog_font, container, false);

        mNumberPicker = dialogView.findViewById(R.id.numberPicker);
        mNumberPicker.setMinValue(0);
        mNumberPicker.setMaxValue(8);
        mNumberPicker.setValue(0);
        mNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mNumberPicker.setDisplayedValues(new String[] {"Allura","Amatic","Blackjack","Brizel","Dancing",
                "Farsan","Hand Writing","Kaushan","Default"});

        mOK = dialogView.findViewById(R.id.btn_ok);
        mCancel = dialogView.findViewById(R.id.btn_cancel);
        mCancel.setOnClickListener(v -> dismiss());

        return dialogView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mOK.setOnClickListener(v -> {
            int pos = mNumberPicker.getValue();
            switch (pos) {
                case 0:
                    setFont("allura");
                    dismiss();
                    break;
                case 1:
                    setFont("amatic");
                    dismiss();
                    break;
                case 2:
                    setFont("blackjack");
                    dismiss();
                    break;
                case 3:
                    setFont("brizel");
                    dismiss();
                    break;
                case 4:
                    setFont("dancing");
                    dismiss();
                    break;
                case 5:
                    setFont("farsan");
                    dismiss();
                    break;
                case 6:
                    setFont("handwriting");
                    dismiss();
                    break;
                case 7:
                    setFont("kaushan");
                    dismiss();
                    break;
                case 8:
                    setFont("default");
                    dismiss();
                    break;

            }
        });
    }

    private void setFont(String font) {
        //save to share pref
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("FONT", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fontValue", font);
            editor.commit();
        } else {
            Toast.makeText(getContext(), "Please update your Android SDK to 26", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}