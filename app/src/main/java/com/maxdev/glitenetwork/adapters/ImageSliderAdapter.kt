package com.maxdev.glitenetwork.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.maxdev.glitenetwork.R

class ImageSliderAdapter(private val context: Context, private val images: IntArray) :
    PagerAdapter() {

    private val mLayoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // inflating the item.xml
        val itemView: View = mLayoutInflater.inflate(R.layout.slider_layout, container, false)
        // referencing the image view from the item.xml file
        val imageView = itemView.findViewById<ImageView>(R.id.imageViewMain)

        // setting the image in the imageView
//        imageView.setImageResource(images[position])

        Glide.with(context).load(images[position]).placeholder(R.drawable.ic_launcher_foreground)
            .into(imageView)

        // Adding the View
        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
