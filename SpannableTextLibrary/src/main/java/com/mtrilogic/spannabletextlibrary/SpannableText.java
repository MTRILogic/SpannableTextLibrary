package com.mtrilogic.spannabletextlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.MaskFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

@SuppressWarnings("unused")
public class SpannableText {
    private final SpannableStringBuilder builder = new SpannableStringBuilder();
    private final TextView lbl;

    private SpannableString spannableString;

    public SpannableText(TextView lbl) {
        this.lbl = lbl;
    }

    public void insert(int position) {
        builder.insert(position, spannableString);
        lbl.setText(builder);
    }

    public void append() {
        builder.append(spannableString);
        lbl.setText(builder);
    }

    public SpannableText setText(String text) {
        spannableString = new SpannableString(text);
        return this;
    }

    public SpannableText setForegroundColor(int color) {
        return setSpannable(new ForegroundColorSpan(color));
    }

    public SpannableText setBackgroundColor(int color) {
        return setSpannable(new BackgroundColorSpan(color));
    }

    public SpannableText setAbsoluteSize(int size) {
        return setSpannable(new AbsoluteSizeSpan(size));
    }

    public SpannableText setAbsoluteSize(int size, boolean dip) {
        return setSpannable(new AbsoluteSizeSpan(size, dip));
    }

    public SpannableText setRelativeSize(float proportion) {
        return setSpannable(new RelativeSizeSpan(proportion));
    }

    public SpannableText setTypeface(Typeface typeface) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return setSpannable(new TypefaceSpan(typeface));
        }
        return this;
    }

    public SpannableText setTypeface(String family) {
        return setSpannable(new TypefaceSpan(family));
    }

    public SpannableText setNormal(int fontWeightAdjustment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return setSpannable(new StyleSpan(Typeface.NORMAL, fontWeightAdjustment));
        }
        return this;
    }

    public SpannableText setBold(int fontWeightAdjustment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return setSpannable(new StyleSpan(Typeface.BOLD, fontWeightAdjustment));
        }
        return this;
    }

    public SpannableText setItalic(int fontWeightAdjustment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return setSpannable(new StyleSpan(Typeface.ITALIC, fontWeightAdjustment));
        }
        return this;
    }

    public SpannableText setBoldItalic(int fontWeightAdjustment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return setSpannable(new StyleSpan(Typeface.BOLD_ITALIC, fontWeightAdjustment));
        }
        return this;
    }

    public SpannableText setNormal() {
        return setSpannable(new StyleSpan(Typeface.NORMAL));
    }

    public SpannableText setBold() {
        return setSpannable(new StyleSpan(Typeface.BOLD));
    }

    public SpannableText setItalic() {
        return setSpannable(new StyleSpan(Typeface.ITALIC));
    }

    public SpannableText setBoldItalic() {
        return setSpannable(new StyleSpan(Typeface.BOLD_ITALIC));
    }

    public SpannableText setUnderline() {
        return setSpannable(new UnderlineSpan());
    }

    public SpannableText setStrikethrough() {
        return setSpannable(new StrikethroughSpan());
    }

    public SpannableText setUrl(String url) {
        return setSpannable(new URLSpan(url));
    }

    public SpannableText setOnClickListener(View.OnClickListener listener) {
        lbl.setMovementMethod(new LinkMovementMethod());
        return setSpannable(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                listener.onClick(view);
            }
        });
    }

    public SpannableText setMaskFilter(MaskFilter filter) {
        return setSpannable(new MaskFilterSpan(filter));
    }

    public SpannableText setImage(Context context, Bitmap bitmap, int verticalAlignment) {
        return setSpannable(new ImageSpan(context, bitmap, verticalAlignment));
    }

    public SpannableText setImage(Context context, Bitmap bitmap) {
        return setSpannable(new ImageSpan(context, bitmap));
    }

    public SpannableText setImage(Context context, Uri uri, int verticalAlignment) {
        return setSpannable(new ImageSpan(context, uri, verticalAlignment));
    }

    public SpannableText setImage(Context context, Uri uri) {
        return setSpannable(new ImageSpan(context, uri));
    }

    public SpannableText setImage(Context context, int resourceId, int verticalAlignment) {
        return setSpannable(new ImageSpan(context, resourceId, verticalAlignment));
    }

    public SpannableText setImage(Context context, int resourceId) {
        return setSpannable(new ImageSpan(context, resourceId));
    }

    public SpannableText setImage(Drawable drawable, String source, int verticalAlignment) {
        return setSpannable(new ImageSpan(drawable, source, verticalAlignment));
    }

    public SpannableText setImage(Drawable drawable, String source) {
        return setSpannable(new ImageSpan(drawable, source));
    }

    public SpannableText setImage(Drawable drawable, int verticalAlignment) {
        return setSpannable(new ImageSpan(drawable, verticalAlignment));
    }

    public SpannableText setImage(Drawable drawable) {
        return setSpannable(new ImageSpan(drawable));
    }

    private SpannableText setSpannable(Object what) {
        spannableString.setSpan(what, 0, spannableString.length(), 0);
        return this;
    }
}
