/**
 * Created by Niki on 2016-10-28.
 */

var counter = 1;

$("#add_room").on('click', function () {
    $('#room-' + counter).after(createRoom());
});

var formGroup = $(document.createElement('div')).addClass('form-group');
var input = $(document.createElement('input')).addClass('form-control');
var label = $(document.createElement('label')).addClass('control-label');

function createRoom() {
    counter++;

    var room = formGroup.clone();

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
    div12.text('Har der været skade i lokalet?');

    div12.append(label.clone()
        .append(input.clone()
            .attr({
                type: 'radio',
                value: 'yes',
                name: 'optradio-' + counter
            })
            .removeClass('form-control'))
        .append('Ja'));

    div12.append(label.clone()
        .append(input.clone()
            .attr({
                type: 'radio',
                value: 'no',
                name: 'optradio-' + counter
            })
            .removeClass('form-control'))
        .append('Nej'));

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
            name: 'date-' + counter
        }));

    var div132 = $(document.createElement('div')).addClass('col-md-5').addClass('col-md-offset-2');
    div13.append(div132);

    var div1321 = formGroup.clone();
    div132.append(div1321);
    div1321.text('Hvor?');
    div1321.append(input.clone()
        .attr({
            type: 'text',
            name: 'location-' + counter
        }));

    var div133 = $(document.createElement('div')).addClass('col-md-5');
    div13.append(div133);

    var div1331 = formGroup.clone();
    div133.append(div1331);

    div1331.text('Hvad er der sket?');
    div1331.append(input.clone()
        .attr({
            type: 'text',
            name: 'incident-' + counter
        }));


    var div134 = $(document.createElement('div')).addClass('col-md-5').addClass('col-md-offset-2');
    div13.append(div134);

    var div1341 = formGroup.clone();
    div134.append(div1341);

    div1341.text('Hvad er repareret?');
    div1341.append(input.clone()
        .attr({
            type: 'text',
            name: 'repair-' + counter
        }));

    var div14 = formGroup.clone();
    div1.append(div14);

    div14.text('Skadetype?');
    div14.append(label.clone()
        .append(input.clone()
            .attr({
                type:'checkbox',
                name:'moisture-' + counter
            })
            .removeClass('form-control'))
        .append('Fugt'));
    div14.append(label.clone()
        .append(input.clone()
            .attr({
                type:'checkbox',
                name:'rot-' + counter
            })
            .removeClass('form-control'))
        .append('Råd og svamp'));
    div14.append(label.clone()
        .append(input.clone()
            .attr({
                type:'checkbox',
                name:'mold-' + counter
            })
            .removeClass('form-control'))
        .append('Skimmel'));
    div14.append(label.clone()
        .append(input.clone()
            .attr({
                type:'checkbox',
                name:'fire-' + counter
            })
            .removeClass('form-control'))
        .append('Brand'));
    div14.append(label.clone()
        .append(input.clone()
            .attr({
                type:'checkbox',
                name:'other-' + counter
            })
            .removeClass('form-control'))
        .append('Andet'));

    var div15 = formGroup.clone();
    div1.append(div15);

    div15.text('Bemærkninger:');
    div15.append($(document.createElement('textarea'))
        .attr({
            rows: 2,
            name:'roomComment-' + counter
        })
        .addClass('form-control'));
    div15.append($(document.createElement('span'))
        .text('Billede: ')
        .append(input.clone()
            .attr({
                type:'file',
                name:'image-' + counter
            })
            .removeClass('form-control')));

    return room.clone();
}
