package io.github.landwarderer.futon.core.ui.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Html
import androidx.annotation.WorkerThread
import coil3.ImageLoader
import coil3.executeBlocking
import coil3.request.ImageRequest
import coil3.request.allowHardware
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.landwarderer.futon.core.util.ext.drawable
import com.awxkee.jxlcoder.coil.JxlDecoder
import javax.inject.Inject

class CoilImageGetter @Inject constructor(
	@ApplicationContext private val context: Context,
	private val coil: ImageLoader,
) : Html.ImageGetter {

	@WorkerThread
	override fun getDrawable(source: String?): Drawable? {
		return coil.executeBlocking(
			ImageRequest.Builder(context)
			    .components {
                    add(JxlDecoder.Factory())
                }
				.data(source)
				.allowHardware(false)
				.build(),
		).drawable?.apply {
			setBounds(0, 0, intrinsicHeight, intrinsicHeight)
		}
	}
}
