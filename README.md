# TeatApp

https://github.com/jasotharan/TestApp.git


This my application to show Articles and its details
here im using "retrofit" to get data from server
1. In first page article list received from server and short order by date
   (chronological order by last_update ) then its shown in recycle list view
   Note :-
   #    if response is correct it will show the data and will save locally in session storage
   #    if server not response (null) or busy it will show articles from locally
        saved Articles (data)
   #    if there no local Articles (data)it will  show sample Articles
        here i have added sample data to show and testing purpose


 2. Second page shows the selected Articles and it can be edited by click "Edit"
    and the changes can be save.

    note : -
    #    user can view the new changes after clicking the save button.
         it's facilitated only locally. Edit api or update api is not captured here.


  note : -  I have adopted the following technologies in this app
            retrofit,gson,picasso,glide

 ** This project can be download form above mention link  **