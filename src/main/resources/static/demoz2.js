$(function() {
    $('body').terminal(function(command, term) {
        return $.post('/inside', {command: command});
    }, {
        greetings: 'Killchain v0.1'
    });
});