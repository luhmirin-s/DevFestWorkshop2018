package lv.luhmirins.ideas.form

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import lv.luhmirins.ideas.Idea
import lv.luhmirins.ideas.R

class IdeaFormActivity : AppCompatActivity() {

    private var currentIdea: Idea? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /*
           TODO connect your layout with some actions:
            1) retrieve id of current idea from `intent`
            2) fetch idae from `MyApp.IDEAS` by observing changes
            3) cache data in `currentIdea` for future use
            4) if data is not empty(null) write existing values to inputs
            5) add 'save' button click listener to either update or save
                  gift to database and finish activity
            [Cheat 3]
           */
    }

    /*
     TODO write a function that:
      1) takes text from inputs
      2) creates new Idea object
      3) executes `SaveAsync` to save new idea to database
      [Cheat 4]
    */
    private fun saveIdea() {
    }

    /*
    TODO write a function that:
     1) takes text from inputs
     2) creates new Idea object with same uid
     3) executes `UpdateAsync` to save idea changes to database
     [Cheat 5]
   */
    private fun updateIdea(idea: Idea) {
    }

    /*
     TODO implement delete button
      1) override `onPrepareOptionsMenu(Menu)` method of `AppCompatActivity`
      2) using `menuInflates` inflate `R.menu.menu_form`
      3) don`t forget to add `return true` so that menu is displayed
      [Cheat 6]
    */

    /*
     TODO implement delete button click handling
      1) if `currentIdea` is not null execute `DeleteAsync`
      2) finish current activity
      3) don't forget to `return true` to show that we handled the click
      [Cheat 6]
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_delete) {
        }
        return super.onOptionsItemSelected(item)
    }
}
