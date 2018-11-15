# Cheats

### Cheat 1: opening form activity
``` kotlin
private fun openFormActivity(ideaId: Long?) {
    val intent = Intent(this, IdeaFormActivity::class.java)
    ideaId?.let { intent.putExtra("idea_id", it) }
    startActivity(intent)
}
```

### Cheat 2: form activity layout
``` xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="lv.luhmirins.ideas.form.IdeaFormActivity">
    
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab_save"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        android:layout_marginBottom="16dp"
        android:layout_marginEnd="16dp"
        android:tint="@color/white_text"
        app:backgroundTint="@color/color_accent"
        app:fabSize="normal"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:srcCompat="@drawable/icon_save"/>

    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/layout_place"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginEnd="16dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        android:hint="Where I am?"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/input_place"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:singleLine="true"/>
    </com.google.android.material.textfield.TextInputLayout>

    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/layout_title"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        android:hint="Pitch"
        app:layout_constraintEnd_toEndOf="@+id/layout_place"
        app:layout_constraintStart_toStartOf="@+id/layout_place"
        app:layout_constraintTop_toBottomOf="@+id/layout_place">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/input_title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
    </com.google.android.material.textfield.TextInputLayout>

    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/layout_idea"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:hint="Details"
        app:layout_constraintEnd_toEndOf="@+id/layout_title"
        app:layout_constraintStart_toStartOf="@+id/layout_title"
        app:layout_constraintTop_toBottomOf="@+id/layout_title">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/input_details"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="top"
            android:lines="5"
            android:singleLine="false"/>
    </com.google.android.material.textfield.TextInputLayout>
</androidx.constraintlayout.widget.ConstraintLayout>
```


### Cheat 3: form layout biding to actions
``` kotlin
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
```


### Cheat 4: function to save idea
``` kotlin
private fun saveIdea() {
    val newIdea = Idea(
        place = input_place.text.toString(),
        title = input_title.text.toString(),
        notes = input_details.text.toString()
    )
    SaveAsync().execute(newIdea)
}
```


### Cheat 5: function to update idea
``` kotlin
private fun updateIdea(idea: Idea) {
    val newIdea = Idea(
        uid = idea.uid,
        place = input_place.text.toString(),
        title = input_title.text.toString(),
        notes = input_details.text.toString()
    )
    UpdateAsync().execute(newIdea)
}
```


### Cheat 6: delete button in menu
``` kotlin
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
```
