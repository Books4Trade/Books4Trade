"use strict";
$(document).ready(function(){
    //  API URL
    const API_URL = 'https://www.googleapis.com/books/v1/volumes?q=flowers+inauthor:keyes&key='+ GB_API;

        function handleResponse(response) {
            fetch()
        for (var i = 0; i < response.items.length; i++) {
        var item = response.items[i];
        // in production code, item.text should have the HTML entities escaped.
        // document.getElementById("content").innerHTML += "<br>" + item.volumeInfo.title;
            console.log(item.volumeInfo.title);
        }
    }
});

//<script>
//   function handleResponse(response) {
//     for (var i = 0; i < response.items.length; i++) {
//       var item = response.items[i];
//       // in production code, item.text should have the HTML entities escaped.
//       document.getElementById("content").innerHTML += "<br>" + item.volumeInfo.title;
//       console.log(item);
//     }
//   }
// </script>
// <script src="https://www.googleapis.com/books/v1/volumes?q=harry+potter&callback=handleResponse"></script>