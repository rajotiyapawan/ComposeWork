package com.rajotiya.mytestapp.utility

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.core.content.res.ResourcesCompat
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.rajotiya.mytestapp.AppContext
import com.rajotiya.mytestapp.R

/**
 * Created by Pawan Rajotiya on 24-09-2024.
 */

//TODO get font for future uses
fun getFont(font: String): Typeface {
    val id = when (font) {
        Constants.ROBOTO -> R.font.roboto
        Constants.ROBOTO_LIGHT -> R.font.roboto_light
        Constants.ROBOTO_MEDIUM -> R.font.roboto_medium
        Constants.ROBOTO_BOLD -> R.font.roboto_bold
        Constants.MONTSERRAT -> R.font.montserrat
        Constants.MONTSERRAT_REGULAR -> R.font.montserrat_regular
        Constants.MONTSERRAT_MEDIUM -> R.font.montserrat_medium
        Constants.MONTSERRAT_SEMIBOLD -> R.font.montserrat_semibold
        Constants.MONTSERRAT_BOLD -> R.font.montserrat_bold
        Constants.OPEN_SANS -> R.font.open_sans
        Constants.OPEN_SANS_BOLD -> R.font.open_sans_bold
        Constants.OPEN_SANS_SEMIBOLD -> R.font.open_sans_semibold
        Constants.PACIFICO -> R.font.pacifico
        else -> R.font.montserrat
    }
    var typeFace: Typeface? = null
    try {
        typeFace = ResourcesCompat.getFont(AppContext.getApplicationContext(), id)
    } catch (e: Exception) {
        e.printStackTrace();
        typeFace = Typeface.createFromAsset(
            AppContext.getApplicationContext().resources.assets,
            "fonts/Roboto-Medium.ttf"
        )
        // typeFace = ResourcesCompat.getFont(MagicBricksApplication.getContext(), R.font.montserrat_regular)
    }
    return typeFace!!
}

fun getFontFamily(font: String): FontFamily {
    return FontFamily(getFont(font))
}

@Composable
fun getComposeImageFromUrl(url: String?): AsyncImagePainter {
    return rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(url ?: R.drawable.mbimageloader_no_image_new)
            .apply(block = fun ImageRequest.Builder.() {
                placeholder(R.drawable.mbimageloader_no_image_new)
                error(R.drawable.mbimageloader_no_image_new)
            }).build()
    )
}

fun mbToast(context: Context?, msg: String?) {
    if (context != null && !TextUtils.isEmpty(msg)) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}

fun showErrorMessageToast(context: Context, message: String?) {
    try {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.FILL_HORIZONTAL or Gravity.BOTTOM, 0, 0)
        val toastView = toast.view //This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */
        val toastMessage = toastView!!.findViewById<TextView>(android.R.id.message)
        toastMessage.textSize = 16f
        toastMessage.setTextColor(Color.WHITE)
        toastMessage.compoundDrawablePadding = 16
        toastView.setBackgroundColor(Color.RED)
        toast.show()
    } catch (npe: NullPointerException) {
        npe.printStackTrace()
    }
}