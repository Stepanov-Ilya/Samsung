package com.example.coolmarket;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coolmarket.databinding.ElementsBinding;

public class MainActivity extends AppCompatActivity {

    private ElementsBinding binding;
    private AlertDialog successDialog;
    private static final float BLUR_RADIUS = 25f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ElementsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showExitConfirmationDialog();
            }
        });

        binding.buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blurBackground();
                showSuccessMessage();
            }
        });
    }

    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void blurBackground() {
        Bitmap originalBitmap = getBitmapFromView(binding.getRoot());
        Bitmap blurredBitmap = blurBitmap(originalBitmap, BLUR_RADIUS);

        binding.getRoot().setBackgroundResource(0);
        binding.getRoot().setBackground(new BitmapDrawable(getResources(), blurredBitmap));
    }

    private Bitmap blurBitmap(Bitmap bitmap, float radius) {
        RenderScript renderScript = RenderScript.create(this);

        Allocation input = Allocation.createFromBitmap(renderScript, bitmap);
        Allocation output = Allocation.createTyped(renderScript, input.getType());

        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        blurScript.setInput(input);
        blurScript.setRadius(radius);
        blurScript.forEach(output);

        Bitmap blurredBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        output.copyTo(blurredBitmap);

        renderScript.destroy();

        return blurredBitmap;
    }

    private void showSuccessMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Order placed successfully")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        successDialog.dismiss();
                    }
                });

        successDialog = builder.create();

        successDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView messageText = successDialog.findViewById(android.R.id.message);
        if (messageText != null) {
            messageText.setGravity(Gravity.CENTER);
            messageText.setTypeface(null, Typeface.BOLD);
            messageText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            messageText.setTextColor(Color.BLACK);
        }

        successDialog.show();

        final Drawable currentBackground = binding.getRoot().getBackground();

        successDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                binding.getRoot().setBackground(currentBackground);

                setContentView(binding.getRoot());

                blurBackground();
            }
        });
    }


    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}
