# Spinner Extension
This is a repository to kotlin extensions and others stuffs related with Android Development. Created to make the development more easy and faster.
### How to use
You just need to create some model class like this
```
data class UserModel(
        var id : Int? = null,
        var name : String? = null
) {
    override fun toString(): String {
        return this.name.toString()
    }
}
```

It's important that you set the `toString` function to see the element properly in layout.
After this, you will create the Spiner Extension and add:

```
fun <V> Spinner.createAdapter(layout: Int, dropDownLayout: Int, list: ArrayList<V>, selectedListener: (position: Int) -> Unit = {}, nothingSelectedListener: () -> Unit = {}) {

    val aa = object : ArrayAdapter<V>(this.context, layout, list) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = super.getView(position, convertView, parent)
            val textview = view as TextView
            if (position == 0) {
                textview.setTextColor(Color.GRAY)
            } else {
                textview.setTextColor(Color.BLACK)
            }
            return view
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getDropDownView(position, convertView, parent)
            val textview = view as TextView
            if (position == 0) {
                textview.setTextColor(Color.GRAY)
            } else {
                textview.setTextColor(Color.BLACK)
            }
            return view
        }
    }
    aa.setDropDownViewResource(dropDownLayout)

    with(this) {
        adapter = aa
    }

    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
            nothingSelectedListener()
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (position != 0) {
                selectedListener(position)
            }
        }
    }

}
```

Let me explain each element on this extension

 - `layout` -> the layout that appears when the activity is created
 - `dropDownLayout` -> the layout that appears when you click on spinner to select an item
 - `list` -> the ArrayList of the Element
 - `selectedListener` -> the listener that will be called when you select something
 - `nothingSelectedListener` -> the listener that will be callend when nothing was selected

Then you just need to set your ArrayList on activity
```
val users = ArrayList<UserModel>()
users.add(UserModel(id = -1, name = "Select an user"))
users.add(UserModel(id = 1, name = "User 1"))
users.add(UserModel(id = 2, name = "User 2"))
users.add(UserModel(id = 3, name = "User 3"))
users.add(UserModel(id = 4, name = "User 4"))
users.add(UserModel(id = 5, name = "User 5"))
users.add(UserModel(id = 6, name = "User 6"))
```
And configure the spinner on your activity
```
spinner.createAdapter(
    R.layout.item_spinner,
    R.layout.item_spinner_dropdown,
    users,
    {
        it.let {
            Toast.makeText(this, users[it].toString() + " selected!", Toast.LENGTH_SHORT).show()
        }
    },
    { }
)
```

If you want to see this example, just click [here](https://github.com/eihror/kotlin-util/tree/master/samples/SpinnerExample) 
