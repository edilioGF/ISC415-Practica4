(function () {
    $(".c-pointer").click(function () {

        const tag = $(this).children()[0];

        if ($(this).hasClass('btn-primary')) {

            $(this).removeClass('btn-primary');
            $(this).addClass('btn-outline-primary');

            $(`input[name=tagName][value=${tag.innerHTML}]`).remove();
            $(`input[name=tagId][value=${tag.id}]`).remove();
        } else {

            $(this).removeClass('btn-outline-primary');
            $(this).addClass('btn-primary');

            const tagHolder = $("#tag-holder");
            $('<input>', {type: 'checkbox', name: 'tagName', value: tag.innerHTML, checked: true}).appendTo(tagHolder);
            $('<input>', {type: 'checkbox', name: 'tagId', value: tag.id, checked: true}).appendTo(tagHolder);
        }
        console.log(document.getElementById("tag-holder"));
    });
})(jQuery);