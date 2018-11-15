package lv.luhmirins.ideas.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*
import lv.luhmirins.ideas.Idea
import lv.luhmirins.ideas.R
import kotlin.properties.Delegates.observable

/*
 * For more information on adapters you can read:
 * https://developer.android.com/guide/topics/ui/layout/recyclerview.html
 * https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.html
 */
class IdeaListAdapter(
    private val context: Context,
    private val onClickCallback: (Long) -> Unit
) : androidx.recyclerview.widget.RecyclerView.Adapter<NoteViewHolder>() {

    // Code in a block is executed every time value of this property is changed.
    // More info: https://kotlinlang.org/docs/reference/delegated-properties.html#observable
    var ideaList: List<Idea> by observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder = LayoutInflater
        .from(context)
        .inflate(R.layout.list_item, parent, false)
        .let { NoteViewHolder(it) }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(ideaList[position], onClickCallback)
    }

    override fun getItemCount(): Int = ideaList.size
}

class NoteViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    fun bind(item: Idea, onClick: (Long) -> Unit) {
        itemView.item_where.text = "At ${item.place}"
        itemView.item_idea.text = item.title
        itemView.item_card.setOnClickListener { onClick(item.uid) }
    }
}
