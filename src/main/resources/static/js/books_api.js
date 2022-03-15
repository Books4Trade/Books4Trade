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
                let content = document.getElementById("search-results");
                for (let i = 0; i < data.items.length; i++) {
                    let item = data.items[i],
                    title = item.volumeInfo.title,
                    author = item.volumeInfo.authors,
                    preview = item.volumeInfo.previewLink,
                    img = item.volumeInfo.imageLinks.smallThumbnail;
                    content.innerHTML +=
                        "<br><li class='d-flex align-items-center'>" +
                        "<img src='" + img + "' alt='book cover'/> " +
                        "<div class='d-inline-flex flex-column justify-content-center align-items-center'>" +
                        "<h5>" + title + "</h5>" +
                        "<p>Author: " + author + "</p>" +
                        "<a href='" + preview + "'>Preview Book</a>" + " " +
                        "<input class='form-check-input' type='radio' name='flexRadio'>" +
                        "</div>" +
                        "</li><br>";
                }
                content.innerHTML +=
                    "<br>" +
                    "<div class='d-grid gap-2'><button class='btn btn-primary'>Submit</button></div></form>";
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
                let content = document.getElementById("search-results");
                for (let i = 0; i < data.items.length; i++) {
                    let item = data.items[i],
                        title = item.volumeInfo.title,
                        author = item.volumeInfo.authors,
                        preview = item.volumeInfo.previewLink,
                        img = item.volumeInfo.imageLinks.smallThumbnail;
                    content.innerHTML +=
                        "<br><li class='d-flex align-items-center'>" +
                        "<img src='" + img + "' alt='book cover'/> " +
                        "<div class='d-inline-flex flex-column justify-content-center align-items-center'>" +
                        "<h5>" + title + "</h5>" +
                        "<p>Author: " + author + "</p>" +
                        "<a href='" + preview + "'>Preview Book</a>" + " " +
                        "<input class='form-check-input' type='radio' name='flexRadio'>" +
                        "</div>" +
                        "</li><br>";
                }
                content.innerHTML +=
                    "<br>" +
                    "<div class='d-grid gap-2'><button class='btn btn-primary'>Submit</button></div></form>"
            })
            .catch(err => console.error(err));
    })
});