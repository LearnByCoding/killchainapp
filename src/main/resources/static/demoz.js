$(function() {
    $('body').terminal(function(command, term) {
        var obj = $.post('/inside', {command: command});
    }, {
        greetings: 'Killchain v0.1'
    });
});