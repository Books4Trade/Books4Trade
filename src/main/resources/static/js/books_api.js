"use strict";
$(document).ready(function(){
    //  API URL
    const GB_URL_TITLE = 'https://www.googleapis.com/books/v1/volumes?q=+intitle:';
    const GB_URL_AUTHOR = 'https://www.googleapis.com/books/v1/volumes?q=+inauthor:';

    //  FUNCTION TO SEARCH BY TITLE
    $('#submit').click(function(e){
        e.preventDefault();
        let title = $('#books').val();

        fetch(`${GB_URL_TITLE}${(title).toLowerCase()}&key=${GB_API}`)
            .then(resp => resp.json())
            .then(data => {
                console.log(data);
                let title = data.title;
                let content = $('#content');
                for (var i = 0; i < data.items.length; i++) {
                    var item = data.items[i];
                    // in production code, item.text should have the HTML entities escaped.
                    document.getElementById("content").innerHTML += "<br>" + item.volumeInfo.title + ", Author: " + item.volumeInfo.authors;
                    console.log(item);
                }
            })
            .catch(err => console.error(err));
    })

    //  FUNCTION TO SEARCH BY AUTHOR
    $('#submitAuthor').click(function(e){
        e.preventDefault();
        let author = $('#booksAuthor').val();

        fetch(`${GB_URL_AUTHOR}${(author).toLowerCase()}&key=${GB_API}`)
            .then(resp => resp.json())
            .then(data => {
                console.log(data);
                let title = data.title;
                let content = $('#content');
                for (var i = 0; i < data.items.length; i++) {
                    var item = data.items[i];
                    // in production code, item.text should have the HTML entities escaped.
                    document.getElementById("content").innerHTML += "<br>" + item.volumeInfo.title + ", Author: " + item.volumeInfo.authors;
                    console.log(item);
                }
            })
            .catch(err => console.error(err));
    })
});