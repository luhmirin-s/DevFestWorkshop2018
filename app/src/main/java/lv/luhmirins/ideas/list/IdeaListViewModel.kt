package lv.luhmirins.ideas.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import lv.luhmirins.ideas.Idea
import lv.luhmirins.ideas.MyApp

/*
 * ViewModel is used to store data in case activity is paused or recreated.
 *
 * https://developer.android.com/topic/libraries/architecture/viewmodel.html
 */
class IdeaListViewModel : ViewModel() {

    private var data: LiveData<List<Idea>>? = null

    fun getAllIdeas(): LiveData<List<Idea>> {
        if (data == null) {
            data = MyApp.IDEAS.getAllIdeas()
        }
        return data!!
    }

    override fun onCleared() {
        data = null
    }
}
