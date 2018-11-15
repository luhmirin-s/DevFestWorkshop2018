package lv.luhmirins.ideas.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import lv.luhmirins.ideas.R

class IdeaListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Creating adapter with item click callback for recycler view to use.
        val adapter = IdeaListAdapter(this) { noteId ->
            openFormActivity(noteId)
        }

        // Setting recycler view to show items as list
        list_recycler.layoutManager = LinearLayoutManager(this)
        list_recycler.adapter = adapter

        // Fetch all notes from the database and update data in the adapter.
        // `getAllIdeas()` returns LiveData object that observes specific data in
        // database and calls Observer if anything changes. This way our list is
        // always up to date, even if we change items in form screens.
        ViewModelProviders.of(this).get(IdeaListViewModel::class.java)
            .getAllIdeas()
            .observe(this, Observer { ideas ->
                ideas?.let { adapter.ideaList = ideas }
            })

        list_add.setOnClickListener {
            openFormActivity(null)
        }
    }

    /*
    TODO open form activity
     1) create an `Intent` for `GiftFormActivity`
     2) put id of clicked item into extras (if it not null)
     3) call `startActivity`
      [Cheat 1]
    */
    private fun openFormActivity(ideaId: Long?) {
    }
}
