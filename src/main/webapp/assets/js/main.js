document.querySelector('.hamburger-button').addEventListener('click', function () {
    var menu = [];
    menu = document.getElementsByClassName('menu-items');
    if (document.querySelector('.menu-items').style.display == 'none') {
        for (var i = 0; i < menu.length; i++){
            menu[i].style.display = 'block';
        }
    }
    else {
        document.querySelector('.menu-items').style.display = 'none';
        for (var i = 0; i < menu.length; i++){
            menu[i].style.display = 'none';
        }
    }
 });