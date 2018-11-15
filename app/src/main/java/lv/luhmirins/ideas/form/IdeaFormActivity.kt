package lv.luhmirins.ideas.form

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_form.*
import lv.luhmirins.ideas.*

class IdeaFormActivity : AppCompatActivity() {

    private var currentIdea: Idea? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val currentNoteId = intent?.getLongExtra("idea_id", 0) ?: 0
        MyApp.IDEAS.getIdea(currentNoteId).observe(this, Observer { nullableIdea ->
            currentIdea = nullableIdea

            nullableIdea?.let { notNullIdea ->
                input_place.setText(notNullIdea.place)
                input_title.setText(notNullIdea.title)
                input_details.setText(notNullIdea.notes)
            }
        })

        fab_save.setOnClickListener {
            currentIdea
                ?.let { idea -> updateIdea(idea) }
                ?: saveIdea()
            finish()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_delete) {
            currentIdea?.let { idea ->
                DeleteAsync().execute(idea)
            }
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateIdea(idea: Idea) {
        val newIdea = Idea(
            uid = idea.uid,
            place = input_place.text.toString(),
            title = input_title.text.toString(),
            notes = input_details.text.toString()
        )
        UpdateAsync().execute(newIdea)
    }

    private fun saveIdea() {
        val newIdea = Idea(
            place = input_place.text.toString(),
            title = input_title.text.toString(),
            notes = input_details.text.toString()
        )
        SaveAsync().execute(newIdea)
    }

}
