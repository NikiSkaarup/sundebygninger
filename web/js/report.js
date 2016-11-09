/**
 * Created by Niki on 2016-10-28.
 */

var counter = 0;
var cc = 0;

$("#add_room").on('click', function () {
    console.log("Creating room " + counter);
    $('#roomContainer').append(createRoom());
    console.log("Created room " + counter);
});


var formGroup = $(document.createElement('div')).addClass('form-group');
var input = $(document.createElement('input')).addClass('form-control');
var label = $(document.createElement('label')).addClass('control-label');

function createRoom() {
    console.log("Incrementing counter: " + counter);
    counter++;
    console.log("counter: " + counter);

    var room = formGroup.clone().attr({id: 'room-' + counter});
    cc = 0;
    //room.attr({'data-commentCount': 2});//$('#room-'+counter).attr({'data-commentCount':1});

    room.append($(document.createElement('hr')));
    room.append(label.clone()
            .addClass('col-md-3')
            .text('Lokale ' + counter));

    var div1 = $(document.createElement('div')).addClass('col-md-9');
    room.append(div1);

    var div11 = formGroup.clone();
    div1.append(div11);

    var roomId = input.clone()
            .attr({
                type: 'text',
                placeholder: 'Lokale ID',
                name: 'room-' + counter
            });
    div11.append(roomId);

    var div12 = formGroup.clone();
    div1.append(div12);
    div12.text('Har der været skade i lokalet? ');

    div12.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'radio',
                        value: 'yes',
                        name: 'room-' + counter + '_damradio'
                    })
                    .removeClass('form-control'))
            .append('Ja '));

    div12.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'radio',
                        value: 'no',
                        name: 'room-' + counter + '_damradio'
                    })
                    .removeClass('form-control'))
            .append('Nej '));

    var div13 = $(document.createElement('div')).addClass('row');
    div1.append(div13);

    var div131 = $(document.createElement('div')).addClass('col-md-5');
    div13.append(div131);

    var div1311 = formGroup.clone();
    div131.append(div1311);

    div1311.text('Hvornår?');
    div1311.append(input.clone()
            .attr({
                type: 'date',
                name: 'room-' + counter + '_date'
            }));

    var div132 = $(document.createElement('div')).addClass('col-md-5').addClass('col-md-offset-2');
    div13.append(div132);

    var div1321 = formGroup.clone();
    div132.append(div1321);
    div1321.text('Hvor?');
    div1321.append(input.clone()
            .attr({
                type: 'text',
                name: 'room-' + counter + '_location'
            }));

    var div133 = $(document.createElement('div')).addClass('col-md-5');
    div13.append(div133);

    var div1331 = formGroup.clone();
    div133.append(div1331);

    div1331.text('Hvad er der sket?');
    div1331.append(input.clone()
            .attr({
                type: 'text',
                name: 'room-' + counter + '_incident'
            }));


    var div134 = $(document.createElement('div')).addClass('col-md-5').addClass('col-md-offset-2');
    div13.append(div134);

    var div1341 = formGroup.clone();
    div134.append(div1341);

    div1341.text('Hvad er repareret?');
    div1341.append(input.clone()
            .attr({
                type: 'text',
                name: 'room-' + counter + '_repair'
            }));

    var div14 = formGroup.clone();
    div1.append(div14);

    div14.text('Skadetype? ');
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'room-' + counter + '_moisture'
                    })
                    .removeClass('form-control'))
            .append('Fugt '));
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'room-' + counter + '_rot'
                    })
                    .removeClass('form-control'))
            .append('Råd og svamp '));
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'room-' + counter + '_mold'
                    })
                    .removeClass('form-control'))
            .append('Skimmel '));
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'room-' + counter + '_fire'
                    })
                    .removeClass('form-control'))
            .append('Brand '));
    div14.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'checkbox',
                        name: 'room-' + counter + '_other'
                    })
                    .removeClass('form-control'))
            .append('Andet '));

    div14.append($(document.createElement('hr')));

    var divComments = $(document.createElement('div'))
            .attr({id: 'room-' + counter + '_commentsContainer'});
    div1.append(divComments);

    var divCommBtn = formGroup.clone();
    div1.append(divCommBtn);

    var cBtn = $(document.createElement('a'));
    cBtn.addClass('btn').addClass('btn-default');
    cBtn.attr({id: 'add_comment-' + counter});
    cBtn.text('Tilføj bemærkninger');
    divCommBtn.append(cBtn);

    $(document).on('click', ('#add_comment-' + counter), function () {
        console.log("add comment ");
        $('#room-' + counter + '_commentsContainer').append(createComment());
        console.log("added comment ");
    });

    var div16 = formGroup.clone();
    div1.append(div16);
    div16.text('Er der foretaget fugtscanning? ');

    div16.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'radio',
                        value: 'yes',
                        name: 'room-' + counter + '_moistradio'
                    })
                    .removeClass('form-control'))
            .append('Ja '));

    div16.append(label.clone()
            .append(input.clone()
                    .attr({
                        type: 'radio',
                        value: 'no',
                        name: 'room-' + counter + '_moistradio'
                    })
                    .removeClass('form-control'))
            .append('Nej '));

    var div17 = $(document.createElement('div')).addClass('row');
    div1.append(div17);

    var div171 = $(document.createElement('div')).addClass('col-md-5');
    div17.append(div171);

    var div1711 = formGroup.clone();
    div171.append(div1711);

    div1711.text('Fugtscanning:');
    div1711.append(input.clone()
            .attr({
                type: 'text',
                name: 'room-' + counter + '_moistureScan'
            }));

    var div172 = $(document.createElement('div')).addClass('col-md-5').addClass('col-md-offset-2');
    div17.append(div172);

    var div1721 = formGroup.clone();
    div172.append(div1721);
    div1721.text('Målepunkt:');
    div1721.append(input.clone()
            .attr({
                type: 'text',
                name: 'room-' + counter + '_measureSpot'
            }));

    return room.clone();
}


function createComment() {

    cc++;
    var divComm1 = formGroup.clone();
    var array = ["Vægge", "Loft", "Gulv", "Vinduer/døre", "Andet..."];

    //Create and append select list
    var selectType = $(document.createElement("select"));
    console.log('commentCount: ' + cc);
    selectType.attr({'id': 'room-' + counter + '_commentType-' + cc});
    selectType.addClass('form-control');
    divComm1.append(selectType);

    //Create and append the options
    for (var i = 0; i < array.length; i++) {
        var option = document.createElement("option");
        option.value = array[i];
        option.text = array[i];
        selectType.append(option);
    }

    divComm1.append($(document.createElement('textarea'))
            .attr({
                rows: 2,
                name: 'room-' + counter + '_comment-' + cc,
                placeholder: 'Bemærkninger:'
            })
            .addClass('form-control'));
    divComm1.append($(document.createElement('span'))
            .text('Billede: ')
            .append(input.clone()
                    .attr({
                        type: 'file',
                        name: 'room-' + counter + '_commentImage-' + cc
                    })
                    .removeClass('form-control')));

    divComm1.append($(document.createElement('hr')));

    return divComm1.clone();
}