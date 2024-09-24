package com.rajotiya.mytestapp.utility

import android.graphics.Typeface
import androidx.compose.runtime.Composable
import androidx.core.content.res.ResourcesCompat
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun getComposeImageFromUrl(url: String?): ImagePainter {
    return rememberImagePainter(
        url ?: R.drawable.mbimageloader_no_image_new,
        builder = {
            placeholder(R.drawable.mbimageloader_no_image_new)
            error(R.drawable.mbimageloader_no_image_new)
        }
    )
}