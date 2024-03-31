$(document).ready(function() {
    $('#addButton').click(function() {
        $('#addFriendForm').toggleClass('hidden');
    });

    $('#viewButton').click(function() {
        $('#friendsList').toggleClass('hidden');
    });

    $('#friendForm').submit(function(event) {
        event.preventDefault();
        const friendName = $('#friendName').val();
        const friendBirthday = $('#friendBirthday').val();
        $('#friends').append("<li>" + friendName + ": " + friendBirthday + "</li>");
        $('#friendForm')[0].reset();
    });
});