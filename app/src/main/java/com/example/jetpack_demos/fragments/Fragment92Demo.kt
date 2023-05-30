package com.example.jetpack_demos.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.jetpack_demos.R

//最小的fragment
class Fragment92Demo : Fragment(R.layout.fragment_demo92) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //开始没有达到效果是因为过度动画只有<changeTransform/>，效果并不好
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(R.transition.share_image)

//        setEnterSharedElementCallback()
//        setExitSharedElementCallback()

        postponeEnterTransition()

        val imgView = view.findViewById<ImageView>(R.id.ivShareImage)
        val item: MyImgItem = requireArguments().getParcelable<MyImgItem>("item") as MyImgItem
        val shareTransitionName = requireArguments().getString("name")

        ViewCompat.setTransitionName(imgView, shareTransitionName)
        val itemPath = item.path.split("?thumbnail=true")[0]
        Glide.with(imgView)
            .load(itemPath)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(imgView)
    }
}