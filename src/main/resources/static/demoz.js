$(function() {
    $('body').terminal(function(command, term) {
        var obj = $.post('/process', {command: command});
    }, {
        greetings: 'Killchain v0.1'
    });
});