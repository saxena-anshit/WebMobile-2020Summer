function upDate(previewPic) {
    /* In this function you should
       1) change the url for the background image of the div with the id = "image"
       to the source file of the preview image

       2) Change the text  of the div with the id = "image"
       to the alt text of the preview image
       */
    //The first two is about getting the attribute, src and alt, to use them.
    var src = previewPic.getAttribute("src");
    var alt = previewPic.getAttribute("alt");
    //The second two is to show the attribute we have already taken
    document.getElementById("image").style.backgroundImage = "url(" + src + ")";
    document.getElementById("image").innerHTML = alt;
}


function unDo(x) {
    /* In this function you should
   1) Update the url for the background image of the div with the id = "image"
   back to the orginal-image.  You can use the css code to see what that original URL was

   2) Change the text  of the div with the id = "image"
   back to the original text.  You can use the html code to see what that original text was
   */
    // Here we get the element by its id and clean it. Then we show a the pervious message.
    x = document.getElementById("image");
    x.style.backgroundImage = "url('')";
    document.getElementById("image").innerText = "Hover over an image below to display here.";

}
