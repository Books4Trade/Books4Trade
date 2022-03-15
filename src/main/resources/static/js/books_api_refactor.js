"use strict";
$(document).ready(function() {
    //  API URL
    const GB_URL = 'https://www.googleapis.com/books/v1/volumes?q=+';
    const GB_TITLE = 'intitle:';
    const GB_AUTHOR = 'inauthor:';

    let param = $('#searchedparam').html();
    let query = $('#searchedquery').html();
    console.log(param);
    console.log(query);

    const fetchBooks = (param, query) => {
        var FULL_URL;
        switch (param) {
            case "title":
                FULL_URL = GB_URL + GB_TITLE + query.toLowerCase() + "&key=" + GB_API;
                break;
            case "author":
                FULL_URL = GB_URL + GB_AUTHOR + query.toLowerCase() + "&key=" + GB_API;
                break;
            default:
                console.log("book api search switch case fallthrough");
        }
        fetch(`${FULL_URL}`)
            .then(resp => resp.json())
            .then(data => {
                renderBooks(data);
                console.log(data);
            })
            .catch(err => console.error(err));
    }

    const renderBooks = (data) => {
        let booksHTML = "";
        booksHTML = '<div>';
        for (let i = 0; i < data.items.length; i++){
            let item = data.items[i],
                title = item.volumeInfo.title,
                author = item.volumeInfo.authors,
                preview = item.volumeInfo.previewLink,
                img = item.volumeInfo.imageLinks.smallThumbnail;

            booksHTML +=
                '<form action="/books/create" method="POST">'+
                    '<div class="book d-flex align-items-center">' +
                        "<img src='" + img + "' alt='book cover'/> " +
                        "<div class='d-inline-flex flex-column justify-content-center align-items-center'>" +
                        "<h5>" + title + "</h5>" +
                        "<p>Author: " + author + "</p>" +
                        "<a href='" + preview + "'>Preview Book</a>" +
                        '<input type="hidden" name="title" id="title" value="'+ title +'">'+
                        '<input type="hidden" name="author" id="author" value="'+ author +'">'+
                        '<input type="hidden" name="imagesrc" id="imagesrc" value="'+ img+'">'+
                        '<button type="submit" >Add Book</button>' +
                        "</div>" +
                    '</div>'+
                '</form>';
        }
        booksHTML += '</div>';
        $('#search-results').html(booksHTML);
    };
    fetchBooks(param, query);

    $('#searchsubmit').click(function(e) {
        e.preventDefault();
        var param = $('#param').val();
        var query = $('#query').val();
        fetchBooks(param, query);
    });
})