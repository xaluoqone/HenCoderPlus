package com.xaluoqone.customview.ex

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.IdRes

/**
 * 得到一个合适宽度的位图以保证图片加载性能，但又不影响在这个宽度下的图像画质
 * @param bitmapId 资源ID R.mipmap.xxx或R.drawable.xxx
 * @param width 图片宽度
 */
fun Context.getFitBitmap(@IdRes bitmapId: Int, width: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(resources, bitmapId, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = width
    return BitmapFactory.decodeResource(resources, bitmapId, options)
}