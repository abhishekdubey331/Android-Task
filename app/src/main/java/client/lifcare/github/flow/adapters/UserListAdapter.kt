package client.lifcare.github.flow.adapters

import android.app.Activity
import android.graphics.Bitmap
import android.support.annotation.NonNull
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.appgully.lifehacks.utils.TypefaceUtil
import client.lifcare.github.R
import client.lifcare.github.model.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.thefinestartist.finestwebview.FinestWebView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.*


class UserListAdapter(private val userList: List<Item>,
                      private val context: Activity,
                      private val onClick: (Item) -> Unit)
    : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(userList[position], position, context)
    }

    override fun getItemCount(): Int = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(
                R.layout.item_user, parent, false)
        return ViewHolder(itemLayoutView, onClick)
    }

    class ViewHolder(override val containerView: View, private val onClick: (Item) -> Unit) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindData(repository: Item, position: Int, context: Activity) {
            with(repository) {
                loadRound(userImage, avatar_url, context)
                loginName.text = "Username : $login"
                stars.text = "Score : " + score.toString()
                stars.typeface = TypefaceUtil.getHammerSmith
                loginName.typeface = TypefaceUtil.getHammerSmith
                link.setOnClickListener {
                    openWebview(context, html_url)
                }
                containerView.setOnClickListener { onClick(this) }
            }
        }

        private fun loadRound(imageView: ImageView, @NonNull imageUrl: String, context: Activity) {
            Glide.with(context)
                    .asBitmap()
                    .load(imageUrl)
                    .into(object : SimpleTarget<Bitmap>() {
                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                            val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(context.resources, resource)
                            circularBitmapDrawable.isCircular = true
                            imageView.setImageDrawable(circularBitmapDrawable)
                        }
                    })
        }

        private fun openWebview(context: Activity, url: String) {
            FinestWebView.Builder(context)
                    .iconDefaultColor(ContextCompat.getColor(context, R.color.white))
                    .urlColor(ContextCompat.getColor(context, R.color.white))
                    .toolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    .titleColor(ContextCompat.getColor(context, R.color.white))
                    .statusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark)).show(url)


        }
    }


}