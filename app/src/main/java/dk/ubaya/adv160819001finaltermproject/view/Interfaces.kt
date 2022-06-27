package dk.ubaya.adv160819001finaltermproject.view

import android.util.Log
import android.view.View
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.model.Thesis
import dk.ubaya.adv160819001finaltermproject.model.User

interface CardViewClickListener{
    fun onCardViewClick(v:View)
}

interface AddBookClickListener{
    fun onAddBookClick(v: View)
}

interface CreateBookClickListener{
    fun onCreateBookClick(v: View, obj:Book)
}

interface BookSaveChangesListener{
    fun onBookSaveChanges(v: View, obj:Book)
}

interface EditBookClickListener{
    fun onEditBookClick(v:View,obj: Book)
}

interface MoreBookClickListener{
    fun onMoreBookClick(v: View)
}

interface DeleteBookClickListener{
    fun onDeleteBookClick(v: View,obj: Book)
}

interface RequestBookClickListener{
    fun onRequestBookClick(v:View)
}

interface DateClickListener{
    fun onDateClick(v:View)
}

interface TimeClickListener{
    fun onTimeClick(v:View)
}

interface RegisterClickListener{
    fun onRegisterClick(v:View, obj:User)
}

interface LoginClickListener{
    fun onLoginClick(v:View, obj:User)
}

interface RegisterInLoginClickListener{
    fun onRegisterInLoginClick(v:View)
}

interface CreateThesisClickListener{
    fun onCreateThesisClick(v: View, obj:Thesis)
}

interface AddThesisClickListener{
    fun onAddThesisClick(v: View)
}

interface RefreshLayoutBookListener{
    fun onRefreshLayoutBook(v:View)
}

interface ThesisCardViewClickListener{
    fun onThesisCardViewClick(v:View,obj: Thesis)
}

interface EditThesisClickListener{
    fun onEditThesisClick(v:View,obj: Thesis)
}

interface ThesisSaveChangesListener{
    fun onThesisSaveChanges(v: View, obj:Thesis)
}

interface DeleteThesisClickListener{
    fun onDeleteThesisClick(v: View,obj: Thesis)
}

interface EditUserClickListener{
    fun onEditUserClick(v:View,obj: User)
}

interface UserSaveChangesListener{
    fun onUserSaveChanges(v: View, obj:User)
}

interface LogoutClickListener{
    fun onLogoutClick(v:View)
}

interface SwitchDarkClickListener{
    fun onSwitchDarkClick(v:View, mode:Boolean)
}