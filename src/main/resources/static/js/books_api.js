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
        booksHTML = '<div class="d-flex flex-row flex-wrap justify-content-evenly">';
        for (let i = 0; i < data.items.length; i++){
            let item = data.items[i],
                title = item.volumeInfo.title,
                author = item.volumeInfo.authors,
                preview = item.volumeInfo.previewLink,
                img = "https://imgur.com/APmD3ha",
                description = "No Summary Available.";
                var summary;
                for(const key in item.volumeInfo.imageLinks){
                    img = `${item.volumeInfo.imageLinks[key]}`;
                    console.log(`${key} : ${item.volumeInfo.imageLinks[key]}`);
                }
                if(item.volumeInfo.hasOwnProperty(description)){
                    description = item.volumeInfo.description;
                    if(description.length > 500){
                        summary = (description.substring(0,450)) + "...(more details were removed)";
                        console.log("summary trimmed, length: "+ summary.length);
                    } else{
                        summary = description;
                        console.log("summary not trimmed, length: "+summary.length);
                    }
                }

            booksHTML +=
                '<form class="book-card" action="/books/create" method="GET">'+
                    '<h3 class="book-headers title-bg">' + title + '</h3>' +
                    '<h4 class="book-headers">Author:' + author + '</h4>' +
                    '<div class="text-center">'+
                        '<img src="' + img + '" alt="book cover"/>' +
                    '</div>'+
                    '<div class="text-center">' +
                        '<a href="' + preview + '" target="_blank" >Preview Book</a>' +
                    '</div>' +
                    '<input type="hidden" name="title" id="title" value="'+ title +'">'+
                    '<input type="hidden" name="author" id="author" value="'+ author +'">'+
                    '<input type="hidden" name="imagesrc" id="imagesrc" value="'+ img+'">'+
                    '<input type="hidden" name="summary" id="summary" value ="'+ summary +'">'+
                    '<br>'+
                    '<div class="text-center">'+
                        '<button type="submit" class="btn btn-primary">Add Book</button>' +
                    '</div>' +
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