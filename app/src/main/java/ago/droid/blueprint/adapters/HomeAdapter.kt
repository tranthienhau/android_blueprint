package ago.droid.blueprint.adapters

import ago.droid.blueprint.R
import ago.droid.blueprint.domain.entities.DCard
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ViewListener
import me.relex.circleindicator.CircleIndicator

class HomeAdapter(private var items: List<DCard>,
                       private val context: Context
) : RecyclerView.Adapter<BaseHolder>() {

    override fun getItemViewType(position: Int): Int {
        if(items[position].images.size > 1){
            return 1
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        when(viewType){
            1 -> return CarouselviewHolder(LayoutInflater.from(context).inflate(R.layout.item_card_multi_view, parent,false))
        }
        return HomeAdapterHolder(LayoutInflater.from(context).inflate(R.layout.item_card_single_view, parent,false))
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        val card = items[position]
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
        when(holder.itemViewType){
            0 -> {
                holder as HomeAdapterHolder
                holder.tvHeader.text = card.header
                holder.tvDescription.text = card.description

                Glide.with(context)
                    .load(card.images[0])
                    .transition(DrawableTransitionOptions.withCrossFade(factory))
                    .into(holder.ivPhoto)
            }
            1 ->{
                holder as CarouselviewHolder
                holder.tvHeaderMulti.text = card.header
                holder.indicator.createIndicators(card.images.size, 0)
                holder.carouselView.pageCount = card.images.size
                holder.carouselView.setPageTransformer { page, position ->
                    holder.indicator.animatePageSelected(holder.carouselView.currentItem)
                }
                holder.carouselView.setViewListener(ViewListener {
                    val view = LayoutInflater.from(context).inflate(R.layout.item_image_view, null, false)
                    val tvNo: TextView = view.findViewById(R.id.tvNo)
                    tvNo.text = it.toString()
                    val tvDesc: TextView = view.findViewById(R.id.tvDesc)
                    tvDesc.text = card.description
                    val ivThumb: ImageView = view.findViewById(R.id.ivThumb)
                    Glide.with(context)
                        .load(card.images[it])
                        .transition(DrawableTransitionOptions.withCrossFade(factory))
                        .into(ivThumb)
                    return@ViewListener view
                })
            }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}

open class  BaseHolder(view: View): RecyclerView.ViewHolder(view){}

class HomeAdapterHolder(view: View) : BaseHolder(view){
    val tvHeader: TextView = view.findViewById(R.id.tvHeader)
    val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    val ivPhoto: ImageView = view.findViewById(R.id.ivPhoto)
}

class CarouselviewHolder(view: View) : BaseHolder(view){
    val tvHeaderMulti: TextView = view.findViewById(R.id.tvHeaderMulti)
    val carouselView: CarouselView = view.findViewById(R.id.carouselView)
    val indicator: CircleIndicator = view.findViewById(R.id.indi)
}