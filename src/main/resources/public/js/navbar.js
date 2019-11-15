var subdomain = window.location.pathname;
var query;

switch (true) {

    case subdomain === '/':
        query = 'Home';
        break;

    case subdomain === '/users/register':
        query = 'Register user';
        break;

    case subdomain.indexOf('/profile') !== -1:
        query = 'My profile';
        break;

    case subdomain.indexOf('/tags') !== -1:
        query = 'Tags';
        break;
}

(function() {
    $(".nav-item").each(function () {
        if ($(this).children()[0].innerHTML === query) {
            $(this).addClass('active');
        }
    });
}(jQuery));