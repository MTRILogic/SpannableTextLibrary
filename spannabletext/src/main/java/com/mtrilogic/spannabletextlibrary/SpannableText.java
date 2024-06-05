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
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * Created by RI - 2024
 */
@SuppressWarnings("unused")
public class SpannableText {
    private final SpannableStringBuilder builder = new SpannableStringBuilder();
    private final TextView lbl;

    private SpannableString spannableString;

    public SpannableText(TextView lbl) {
        this.lbl = lbl;
    }

    public SpannableText setText(String text) {
        spannableString = new SpannableString(text);
        return this;
    }

    /**
     * Inserta en el builder, a partir de la posición dada, el texto modificado con los spans
     * @param position la posición dada.
     */
    public void insert(int position) {
        lbl.setText(builder.insert(position, spannableString));
    }

    /**
     * Añade al final del builder el texto modificado con los spans.
     */
    public void append() {
        lbl.setText(builder.append(spannableString));
    }

    /**
     * Reemplaza el contenido dentro del builder a partir de la posición indicada con el texto
     * modificado con los spans.
     * @param position la posición dada.
     */
    public void replace(int position) {
        int len = spannableString.length();
        lbl.setText(builder.replace(position, position + len, spannableString, 0, len));
    }

    /**
     * Establece el color de fondo del texto.
     * @param color color integer that defines the background color.
     * @return this instance.
     */
    public SpannableText setBackgroundColor(int color) {
        return setSpannable(new BackgroundColorSpan(color));
    }

    /**
     * Establece el color del texto.
     * @param color color integer that defines the text color.
     * @return this instance.
     */
    public SpannableText setForegroundColor(int color) {
        return setSpannable(new ForegroundColorSpan(color));
    }

    /**
     * Agrega un efecto de tachado al texto.
     * @return this instance.
     */
    public SpannableText setStrikethrough() {
        return setSpannable(new StrikethroughSpan());
    }

    /**
     * Agrega un subrayado al texto.
     * @return this instance.
     */
    public SpannableText setUnderline() {
        return setSpannable(new UnderlineSpan());
    }

    /**
     * Aplica estilo de fuente normal.
     * @param fontWeightAdjustment An integer describing the adjustment to be made to the font weight.
     * @return this instance.
     */
    public SpannableText setNormal(int fontWeightAdjustment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return setSpannable(new StyleSpan(Typeface.NORMAL, fontWeightAdjustment));
        }
        return this;
    }

    /**
     * Aplica estilo de fuente negrita.
     * @param fontWeightAdjustment An integer describing the adjustment to be made to the font weight.
     * @return this instance.
     */
    public SpannableText setBold(int fontWeightAdjustment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return setSpannable(new StyleSpan(Typeface.BOLD, fontWeightAdjustment));
        }
        return this;
    }

    /**
     * Aplica estilo de fuente itálica.
     * @param fontWeightAdjustment An integer describing the adjustment to be made to the font weight.
     * @return this instance.
     */
    public SpannableText setItalic(int fontWeightAdjustment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return setSpannable(new StyleSpan(Typeface.ITALIC, fontWeightAdjustment));
        }
        return this;
    }

    /**
     * Aplica estilo de fuente itálica-negrita.
     * @param fontWeightAdjustment An integer describing the adjustment to be made to the font weight.
     * @return this instance.
     */
    public SpannableText setBoldItalic(int fontWeightAdjustment) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return setSpannable(new StyleSpan(Typeface.BOLD_ITALIC, fontWeightAdjustment));
        }
        return this;
    }

    /**
     * Aplica estilo de fuente normal.
     * @return this instance.
     */
    public SpannableText setNormal() {
        return setSpannable(new StyleSpan(Typeface.NORMAL));
    }

    /**
     * Aplica estilo de fuente negrita.
     * @return this instance.
     */
    public SpannableText setBold() {
        return setSpannable(new StyleSpan(Typeface.BOLD));
    }

    /**
     * Aplica estilo de fuente itálica.
     * @return this instance.
     */
    public SpannableText setItalic() {
        return setSpannable(new StyleSpan(Typeface.ITALIC));
    }

    /**
     * Aplica estilo de fuente itálica-negrita.
     * @return this instance.
     */
    public SpannableText setBoldItalic() {
        return setSpannable(new StyleSpan(Typeface.BOLD_ITALIC));
    }

    /**
     * Escala el tamaño del texto en relación con el tamaño predeterminado.
     * @param proportion the proportion with which the text is scaled.
     * @return this instance.
     */
    public SpannableText setRelativeSize(float proportion) {
        return setSpannable(new RelativeSizeSpan(proportion));
    }

    /**
     * Establece el tamaño del texto a un valor absoluto en píxeles.
     * @param size el tamaño del texto en pixeles físicos.
     * @return this instance.
     */
    public SpannableText setAbsoluteSize(int size) {
        return setSpannable(new AbsoluteSizeSpan(size));
    }

    /**
     * Establece el tamaño del texto a un valor absoluto en píxeles.
     * @param size el tamaño del texto.
     * @param dip Determine the text size to size physical pixels,
     *            or to size device-independent pixels if dip is true.
     * @return this instance.
     */
    public SpannableText setAbsoluteSize(int size, boolean dip) {
        return setSpannable(new AbsoluteSizeSpan(size, dip));
    }

    /**
     * Aplica una fuente personalizada al texto basado en un typeface específico.
     * @param typeface el typeface a ser usado.
     * @return this instance.
     */
    public SpannableText setTypeface(Typeface typeface) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return setSpannable(new TypefaceSpan(typeface));
        }
        return this;
    }

    /**
     * Aplica una fuente personalizada al texto basado en la familia de la fuente.
     * @param family The font family for this typeface.
     *               Examples include "monospace", "serif", and "sans-serif"
     * @return this instance.
     */
    public SpannableText setTypeface(String family) {
        return setSpannable(new TypefaceSpan(family));
    }

    /**
     * Agrega comillas alrededor del texto.
     * @param color the color of the quote stripe.
     * @return this instance.
     */
    public SpannableText setQuote(int color) {
        return setSpannable(new QuoteSpan(color));
    }

    /**
     * Agrega comillas alrededor del texto.
     * @param color the color of the quote stripe.
     * @param stripeWidth the width of the stripe.
     * @param gapWidth the width of the gap between the stripe and the text.
     * @return this instance.
     */
    public SpannableText setQuote(int color, int stripeWidth, int gapWidth) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return setSpannable(new QuoteSpan(color, stripeWidth, gapWidth));
        }
        return this;
    }

    /**
     * Hace que el texto sea clickable y abre una URL cuando se hace clic.
     * @param url the url string.
     * @return this instance.
     */
    public SpannableText setUrl(String url) {
        return setSpannable(new URLSpan(url));
    }

    /**
     * Crea un texto clickable que permite definir acciones de clic personalizadas.
     * @param listener el listener para ejecutar la acción del click sobre el texto.
     * @return this instance.
     */
    public SpannableText setOnClickListener(View.OnClickListener listener) {
        lbl.setMovementMethod(new LinkMovementMethod());
        return setSpannable(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                listener.onClick(view);
            }
        });
    }

    /**
     * Inserta una imagen en el texto.
     * @param context context used to create a drawable from @param bitmap
     *      *                based on the display metrics of the resource.
     * @param bitmap bitmap to be rendered.
     * @param verticalAlignment one of DynamicDrawableSpan.ALIGN_BOTTOM
     *                         or DynamicDrawableSpan.ALIGN_BASELINE.
     * @return this instance.
     */
    public SpannableText setImage(Context context, Bitmap bitmap, int verticalAlignment) {
        return setSpannable(new ImageSpan(context, bitmap, verticalAlignment));
    }

    /**
     * Inserta una imagen en el texto.
     * @param context context used to create a drawable from @param bitmap
     *                based on the display metrics of the resource.
     * @param bitmap bitmap to be rendered.
     * @return this instance.
     */
    public SpannableText setImage(Context context, Bitmap bitmap) {
        return setSpannable(new ImageSpan(context, bitmap));
    }

    /**
     * Inserta una imagen en el texto.
     * @param context context used to create a drawable from @param bitmap
     *                based on the display metrics of the resources.
     * @param uri Uri used to construct the drawable that will be rendered.
     * @param verticalAlignment one of DynamicDrawableSpan.ALIGN_BOTTOM
     *                         or DynamicDrawableSpan.ALIGN_BASELINE.
     * @return this instance.
     */
    public SpannableText setImage(Context context, Uri uri, int verticalAlignment) {
        return setSpannable(new ImageSpan(context, uri, verticalAlignment));
    }

    /**
     * Inserta una imagen en el texto.
     * @param context context used to create a drawable from @param bitmap
     *                based on the display metrics of the resources.
     * @param uri Uri used to construct the drawable that will be rendered.
     * @return this instance.
     */
    public SpannableText setImage(Context context, Uri uri) {
        return setSpannable(new ImageSpan(context, uri));
    }

    /**
     * Inserta una imagen en el texto.
     * @param context context used to retrieve the drawable from resource.
     * @param resourceId drawable resource id based on which the drawable is retrieved.
     * @param verticalAlignment one of DynamicDrawableSpan.ALIGN_BOTTOM
     *                         or DynamicDrawableSpan.ALIGN_BASELINE.
     * @return this instance.
     */
    public SpannableText setImage(Context context, int resourceId, int verticalAlignment) {
        return setSpannable(new ImageSpan(context, resourceId, verticalAlignment));
    }

    /**
     * Inserta una imagen en el texto.
     * @param context context used to retrieve the drawable from resource.
     * @param resourceId drawable resource id based on which the drawable is retrieved.
     * @return this instance.
     */
    public SpannableText setImage(Context context, int resourceId) {
        return setSpannable(new ImageSpan(context, resourceId));
    }

    /**
     * Inserta una imagen en el texto.
     * @param drawable drawable to be rendered.
     * @param source drawable's uri source
     * @param verticalAlignment one of DynamicDrawableSpan.ALIGN_BOTTOM
     *                         or DynamicDrawableSpan.ALIGN_BASELINE.
     * @return this instance.
     */
    public SpannableText setImage(Drawable drawable, String source, int verticalAlignment) {
        return setSpannable(new ImageSpan(drawable, source, verticalAlignment));
    }

    /**
     * Inserta una imagen en el texto.
     * @param drawable drawable to be rendered.
     * @param source drawable's uri source
     * @return this instance.
     */
    public SpannableText setImage(Drawable drawable, String source) {
        return setSpannable(new ImageSpan(drawable, source));
    }

    /**
     * Inserta una imagen en el texto.
     * @param drawable drawable to be rendered.
     * @param verticalAlignment one of DynamicDrawableSpan.ALIGN_BOTTOM
     *                         or DynamicDrawableSpan.ALIGN_BASELINE.
     * @return this instance.
     */
    public SpannableText setImage(Drawable drawable, int verticalAlignment) {
        return setSpannable(new ImageSpan(drawable, verticalAlignment));
    }

    /**
     * Inserta una imagen en el texto.
     * @param drawable drawable to be rendered.
     * @return this instance.
     */
    public SpannableText setImage(Drawable drawable) {
        return setSpannable(new ImageSpan(drawable));
    }

    /**
     * Agrega un punto de viñeta al principio de un párrafo.
     * @return this instance.
     */
    public SpannableText setBullet() {
        return setSpannable(new BulletSpan());
    }

    /**
     * Agrega un punto de viñeta al principio de un párrafo.
     * @param gapWidth the distance, in pixels, between the bullet point and the paragraph.
     * @return this instance
     */
    public SpannableText setBullet(int gapWidth) {
        return setSpannable(new BulletSpan(gapWidth));
    }

    /**
     * Agrega un punto de viñeta al principio de un párrafo.
     * @param gapWidth the distance, in pixels, between the bullet point and the paragraph.
     * @param color the bullet point color, as a color integer.
     * @return this instance.
     */
    public SpannableText setBullet(int gapWidth, int color) {
        return setSpannable(new BulletSpan(gapWidth, color));
    }

    /**
     * Agrega un punto de viñeta al principio de un párrafo.
     * @param gapWidth the distance, in pixels, between the bullet point and the paragraph.
     * @param color the bullet point color, as a color integer.
     * @param bulletRadius the radius of the bullet point, in pixels.
     * @return this instance
     */
    public SpannableText setBullet(int gapWidth, int color, int bulletRadius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return setSpannable(new BulletSpan(gapWidth, color, bulletRadius));
        }
        return this;
    }

    /**
     * Hace que el texto sea subíndice.
     * @return this instance.
     */
    public SpannableText setSubscript() {
        return setSpannable(new SubscriptSpan());
    }

    /**
     * Hace que el texto sea superíndice.
     * @return this instance.
     */
    public SpannableText setSuperscript() {
        return setSpannable(new SuperscriptSpan());
    }

    /**
     * Permite aplicar un filtro de máscara al texto.
     * @param filter the filter to be applied to the TextPaint.
     * @return this instance.
     */
    public SpannableText setMaskFilter(MaskFilter filter) {
        return setSpannable(new MaskFilterSpan(filter));
    }

    /**
     * Attach a specified markup object to the current text of the spannablestring
     * @param what the markup object.
     * @return this instance.
     */
    public SpannableText setSpannable(Object what) {
        spannableString.setSpan(what, 0, spannableString.length(), 0);
        return this;
    }
}
