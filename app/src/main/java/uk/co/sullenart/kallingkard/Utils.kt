package uk.co.sullenart.kallingkard

import android.graphics.*

fun getRoundedBitmap(source: Bitmap): Bitmap {
    val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)

    val paint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    val rect = Rect(0, 0, source.width, source.height)

    val canvas = Canvas(output).apply {
        drawARGB(0, 0, 0, 0)
        drawOval(RectF(rect), paint)
        drawBitmap(source, rect, rect, paint)
    }

    //paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    //canvas.drawBitmap(source, rect, rect, paint)

    return output
    /*
            final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;

     */
}