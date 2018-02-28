package uk.co.sullenart.kallingkard

import android.graphics.*

fun getRoundedBitmap(source: Bitmap): Bitmap {
    val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)

    val paint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
    }

    val rect = Rect(0, 0, source.width, source.height)

    val canvas = Canvas(output).apply {
        drawARGB(0, 0, 0, 0)
        drawOval(RectF(rect), paint)
    }

    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(source, rect, rect, paint)

    return output
}