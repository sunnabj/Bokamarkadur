$(document).ready(function () {
    $("#first").addClass('scroll_item_active');
    var main = main = $('#scroll');
    $('.scroll_item').click(function (event) {

        event.preventDefault();
        var trgt = $(this).attr('id') + "1";
        target_offset = $('#' + trgt).offset(),
            target_top = target_offset.top;
        $('html, body').animate({
            scrollTop: target_top
        }, 500);
        main.children().removeClass('scroll_item_active');

        $(this).addClass('scroll_item_active');

    });

    $(window).scroll(function (event) {
        if ($("#first1").offset().top < $(window).scrollTop() + $(window).outerHeight()) {
            $("#first").addClass('scroll_item_active');
            $("#second").removeClass('scroll_item_active');
            $("#third").removeClass('scroll_item_active');
            $("#fourth").removeClass('scroll_item_active');
        }
        if ($("#second1").offset().top < $(window).scrollTop() + $(window).outerHeight()) {
            $("#second").addClass('scroll_item_active');
            $("#first").removeClass('scroll_item_active');
            $("#third").removeClass('scroll_item_active');
            $("#fourth").removeClass('scroll_item_active');

        }
        if ($("#third1").offset().top < $(window).scrollTop() + $(window).outerHeight()) {
            $("#third").addClass('scroll_item_active');
            $("#first").removeClass('scroll_item_active');
            $("#second").removeClass('scroll_item_active');
            $("#fourth").removeClass('scroll_item_active');
        }
        if ($("#fourth1").offset().top < $(window).scrollTop() + $(window).outerHeight()) {
            $("#fourth").addClass('scroll_item_active');
            $("#first").removeClass('scroll_item_active');
            $("#second").removeClass('scroll_item_active');
            $("#third").removeClass('scroll_item_active');
        }
    });
});